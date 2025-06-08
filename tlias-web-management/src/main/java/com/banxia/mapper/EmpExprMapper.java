package com.banxia.mapper;

import com.banxia.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存工作经历信息
     * @param exprList
     */
    void insterBatch(List<EmpExpr> exprList);

    void deleteByEmpids(List<Integer> ids);
}
