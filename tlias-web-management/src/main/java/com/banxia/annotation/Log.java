package com.banxia.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 * 用于标记需要记录操作日志的方法，通常用于标记增删改操作
 * 被此注解标记的方法将由LogAspect切面类自动记录操作日志
 *
 * @author BanXia
 * @see com.banxia.aop.LogAspect
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface Log {
}
