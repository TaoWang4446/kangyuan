package com.kanion.www.service;

import java.util.List;
import java.util.Map;

public interface JyhTqDBService {
	public List<String> getStage(String processName);
	public List<Map<String,Object>> getProcessAndBo(String productName);
	public List<Map<String, Object>> getStageAndParam(String processName);
	public List<String> getParam(String stageName);
	public List<Map<String , Object>> getValueAndUnit(String productName,String processName,
			String stageName, String paraName,String batchName);
	public List<String>getDevice(String batchName);
}
