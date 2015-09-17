package com.kanion.www.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MidIndexService {
	public String getTableName(String productName, String midName);
	public List<String> getMidName(String productName);
	public List<String> getParamByPmMid(String productName,String midName );
}
