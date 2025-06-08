package com.banxia.aop;


import com.banxia.mapper.OperateLogMapper;
import com.banxia.pojo.OperateLog;
import com.banxia.utils.CurrentHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 操作日志记录切面
 * 用于记录系统中所有标记了@Log注解的方法的执行情况
 * 
 * <p>该切面会自动拦截所有标注了@Log注解的方法，记录方法的执行信息，
 * 包括操作人、操作时间、类名、方法名、方法参数、返回值和执行时长等信息，
 * 并将这些信息保存到数据库中，用于后续审计和问题排查。</p>
 *
 * @author BanXia
 * @see com.banxia.annotation.Log
 * @see com.banxia.pojo.OperateLog
 * @see com.banxia.mapper.OperateLogMapper
 */
@Slf4j
@Component
@Aspect // 声明为切面类
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private ObjectMapper objectMapper; // 用于JSON序列化和反序列化

    /**
     * 定义切入点，匹配所有标注了@Log注解的方法
     * 
     * <p>该切入点表达式会匹配所有被@Log注解标记的方法，无论这些方法在哪个类中定义。
     * 切入点是告诉切面在哪里执行通知的地方。</p>
     */
    @Pointcut("@annotation(com.banxia.annotation.Log)")
    public void logPointcut() {
    }

    /**
     * 环绕通知，在方法执行前后记录信息
     * 
     * <p>该方法会在目标方法执行前记录开始时间，获取操作人ID、类名、方法名和方法参数等信息；
     * 然后执行目标方法；
     * 最后在目标方法执行后记录结束时间，计算执行时长，获取返回值，并将所有信息保存到数据库中。</p>
     * 
     * <p>即使目标方法执行过程中抛出异常，也会记录日志信息，并重新抛出异常。</p>
     *
     * @param joinPoint 连接点，包含目标方法的信息和目标方法的参数
     * @return 目标方法的执行结果
     * @throws Throwable 目标方法执行过程中可能抛出的任何异常
     */
    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始时间
        long startTime = System.currentTimeMillis();
        
        // 操作人ID（从JWT令牌中获取）
        Integer operateEmpId = getOperatorId();
        
        // 目标类名
        String className = joinPoint.getTarget().getClass().getName();
        
        // 方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        
        // 方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = objectMapper.writeValueAsString(args);
        
        // 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            // 方法执行异常，也需要记录日志
            log.error("方法执行异常", e);
            throw e;
        } finally {
            // 结束时间
            long endTime = System.currentTimeMillis();
            // 执行时长
            long costTime = endTime - startTime;
            
            // 返回值（可能为null）
            String returnValue = result != null ? objectMapper.writeValueAsString(result) : null;
            
            // 记录日志
            saveLog(operateEmpId, className, methodName, methodParams, returnValue, costTime);
        }
        
        return result;
    }
    
    /**
     * 获取当前操作人ID
     * 从请求头中获取token，并解析出用户ID
     * 
     * <p>该方法通过Spring的RequestContextHolder获取当前请求，从请求头中获取token，
     * 然后使用JwtUtils解析token，从中提取用户ID。</p>
     * 
     * <p>如果在任何步骤出现异常（如请求为空、token不存在或无效等），则返回null，
     * 并记录错误日志，但不会影响主业务流程。</p>
     *
     * @return 操作人ID，如果无法获取则返回null
     * @see com.banxia.utils.JwtUtils#parseJWT(String)
     */
    private Integer getOperatorId() {
        return CurrentHolder.getCurrentId();
    }
    
    /**
     * 保存操作日志
     * 
     * <p>该方法将操作日志信息保存到数据库中。为避免参数或返回值过长导致数据库字段溢出，
     * 会对长度超过1995个字符的参数和返回值进行截断处理。</p>
     * 
     * <p>如果在保存日志过程中出现异常，会记录错误日志，但不会影响主业务流程。</p>
     *
     * @param operateEmpId 操作人ID，可能为null
     * @param className 目标类的全类名
     * @param methodName 目标方法的方法名
     * @param methodParams 方法参数的JSON字符串表示
     * @param returnValue 方法返回值的JSON字符串表示，可能为null
     * @param costTime 方法执行时长，单位为毫秒
     * @see com.banxia.pojo.OperateLog
     * @see com.banxia.mapper.OperateLogMapper#insert(OperateLog)
     */
    private void saveLog(Integer operateEmpId, String className, String methodName, 
                        String methodParams, String returnValue, long costTime) {
        try {
            // 截断过长的参数和返回值，避免数据库字段长度限制
            if (methodParams != null && methodParams.length() > 1995) {
                methodParams = methodParams.substring(0, 1995) + "...";
            }
            
            if (returnValue != null && returnValue.length() > 1995) {
                returnValue = returnValue.substring(0, 1995) + "...";
            }
            
            // 创建日志对象
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(operateEmpId);
            operateLog.setOperateTime(LocalDateTime.now());
            operateLog.setClassName(className);
            operateLog.setMethodName(methodName);
            operateLog.setMethodParams(methodParams);
            operateLog.setReturnValue(returnValue);
            operateLog.setCostTime(costTime);
            
            // 保存到数据库
            log.info("记录日志：" + operateLog);
            operateLogMapper.insert(operateLog);
        } catch (Exception e) {
            // 保存日志时出现异常，不应影响主业务流程，只记录错误
            log.error("保存操作日志失败", e);
        }
    }
}

