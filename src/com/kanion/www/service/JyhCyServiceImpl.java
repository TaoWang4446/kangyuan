package com.kanion.www.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanion.www.dao.JyhCyMapper;
import com.kanion.www.model.JyhCy;

@Service("jyhCyService")
public class JyhCyServiceImpl implements JyhCyService{
	
	JyhCyMapper jyhCyMapper;
	@Autowired
	public void setJyhCyMapper(JyhCyMapper jyhCyMapper) {
		this.jyhCyMapper = jyhCyMapper;
	}
	@Override
	public List<JyhCy> getZlyTotalVol(String tableName,String minBatchNo,
			String maxBatchNo) {
		
		return jyhCyMapper.selectByBatchNos(tableName,minBatchNo, maxBatchNo);
		
	}
	@Override
	public List getByBatNoPm(String tableName,String minBatchNo,
			String maxBatchNo,String productName) {
		return jyhCyMapper.selectByBatNoPm(tableName, minBatchNo, maxBatchNo, productName);
	}

}
