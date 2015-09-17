package com.kanion.www.service;

import java.util.List;

import com.kanion.www.model.JyhTqBw;

public interface JyhTqBwService {
	public List<JyhTqBw> getSomeParam (String tableName,String batchNo);
	public int updateByPrimaryKey(JyhTqBw record);
	public void updateDate();
}
