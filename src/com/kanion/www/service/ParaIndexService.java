package com.kanion.www.service;

import java.util.HashMap;
import java.util.Map;

import com.kanion.www.model.ParaIndex;

public interface ParaIndexService {
	public ParaIndex getTableNameAndFiled(String productName, String processName, String stageName, String paraName);
}
