package com.kanion.www.service;

import java.math.BigDecimal;
import java.util.List;

import com.kanion.www.model.JyhCy;
/**
 * 
 * @author xiaosisi
 * 金银花提取_出液
 * 蒸馏液总体积
 */
public interface JyhCyService {
	List<JyhCy> getZlyTotalVol(String tableName,String minBatchNo, String maxBatchNo);
	public List getByBatNoPm(String tableName,String minBatchNo,
			String maxBatchNo,String productName);
}

