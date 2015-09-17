//package com.kanion.www.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.kanion.www.dao.JyhTqBwMapper;
//import com.kanion.www.model.JyhTqBw;
//
//@Service("jyhTqBwService")
//public class JyhTqBwServiceImpl implements JyhTqBwService{
//	private JyhTqBwMapper jyhTqBwMapper;
//	@Autowired
//	public void setJyhTqBwMapper(JyhTqBwMapper jyhTqBwMapper) {
//		this.jyhTqBwMapper = jyhTqBwMapper;
//	}
//
//	@Override
//	public List<JyhTqBw> getSomeParam(String tableName, String batchNo
//			) {
//		return jyhTqBwMapper.selectByBatchNos(tableName, batchNo);
//		
//	}
//
//	@Override
//	public int updateByPrimaryKey(JyhTqBw record) {
//		
//		return jyhTqBwMapper.updateByPrimaryKey(record);
//	}
//
//	@Override
//	public void updateDate() {
//		jyhTqBwMapper.updateDate();
//		
//	}
//
//}
