package com.kanion.www.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanion.www.dao.ParaIndexMapper;
import com.kanion.www.model.ParaIndex;

@Service("paraIndexService")
public class ParaIndexServiceImpl implements ParaIndexService{
	
	private ParaIndexMapper paraIndexMapper;
	@Autowired
	public void setParaIndexMapper(ParaIndexMapper paraIndexMapper) {
		this.paraIndexMapper = paraIndexMapper;
	}
	@Override
	public ParaIndex getTableNameAndFiled(String productName,
			String processName,String stageName, String paraName) {
		
		return paraIndexMapper.selectByPnameAndProcess(productName, processName,stageName, paraName);
	}

}
