package com.kanion.www.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanion.www.dao.JyhTqDBMapper;
import com.kanion.www.service.JyhTqDBService;

@Service("jyhTqDBService")
public class JyhTqDBServiceImpl implements JyhTqDBService{
	private JyhTqDBMapper jyhTqDBMapper;
	
	@Autowired
	public void setJyhTqDBMapper(JyhTqDBMapper jyhTqDBMapper) {
		this.jyhTqDBMapper = jyhTqDBMapper;
	}

	@Override
	public List<String> getStage(String processName) {
		return jyhTqDBMapper.slStage(processName);
	}

	@Override
	public List<Map<String,Object>> getProcessAndBo(String productName) {
		
		return (List<Map<String,Object>>) jyhTqDBMapper.slProcessAndBo(productName);
	}

	@Override
	public List<Map<String, Object>> getStageAndParam(String processName) {
		
		return jyhTqDBMapper.slStageAndParam(processName);
	}

	@Override
	public List<String> getParam(String stageName) {
		
		return jyhTqDBMapper.slParam(stageName);
	}

	@Override
	public List<Map<String, Object>> getValueAndUnit(String productName,
			String processName, String stageName, String paraName,
			String batchName) {
		return jyhTqDBMapper.slValueAndUnit(productName, processName, stageName, paraName, batchName);
	}

	@Override
	public List<String> getDevice(String batchName) {
		
		return jyhTqDBMapper.slDevice(batchName);
	}

}
