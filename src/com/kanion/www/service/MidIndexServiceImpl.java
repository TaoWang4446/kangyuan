package com.kanion.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanion.www.dao.MidIndexMapper;
import com.kanion.www.model.MidIndex;

@Service("midIndexService")
public class MidIndexServiceImpl implements MidIndexService{
	
	
	private MidIndexMapper midIndexMapper;
	
	@Autowired
	public void setMidIndexMapper(MidIndexMapper midIndexMapper) {
		this.midIndexMapper = midIndexMapper;
	}

	@Override
	public String getTableName(String productName, String midName) {
		return midIndexMapper.selectByPnameAndMid(productName, midName);
	}

	@Override
	public List<String> getMidName(String productName) {
		return midIndexMapper.selectMidByPname(productName);
	}

	@Override
	public List<String> getParamByPmMid(String productName, String midName) {
		return midIndexMapper.selectParamByPmMid(productName, midName);
	}

}
