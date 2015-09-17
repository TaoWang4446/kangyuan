package com.kanion.www.dao;

import com.kanion.www.model.ParaIndex;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

public interface ParaIndexMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table KY_PARA_INDEX
     *
     * @mbggenerated Sun Aug 16 15:50:31 CST 2015
     */
    int deleteByPrimaryKey(BigDecimal id);
    int insert(ParaIndex record);
    int insertSelective(ParaIndex record);

    ParaIndex selectByPrimaryKey(BigDecimal id);
    ParaIndex selectByPnameAndProcess(@Param("productName")String productName, 
    		@Param("processName")String processName, @Param("stageName")String stageName ,@Param("paraName")String paraName
    );
    int updateByPrimaryKeySelective(ParaIndex record);
    int updateByPrimaryKey(ParaIndex record);
}