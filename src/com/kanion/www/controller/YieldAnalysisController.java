package com.kanion.www.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.kanion.www.model.JyhCy;
import com.kanion.www.service.JyhCyService;
import com.kanion.www.service.MidIndexService;
import com.kanion.www.service.YieldAnalysisService;
@Controller
@RequestMapping("yieldAnalysis")
public class YieldAnalysisController {
	private String tableName="";
	private static MidIndexService midIndexService;
	@Autowired
	public void setMidIndexService(MidIndexService midIndexService) {
		this.midIndexService = midIndexService;
	}
	static Logger logger=Logger.getLogger(YieldAnalysisController.class.getName());
	
	@Autowired
	private YieldAnalysisService mYieldAnalysisService;
	
	private JyhCyService jyhCyService;
	
	@Autowired
	public void setJyhCyService(JyhCyService jyhCyService) {
		this.jyhCyService = jyhCyService;
	}
	/**
	 * 
	* @Title: init
	* @Description: 收率分析页面的初始化，读取品名
	* @param @param request
	* @param @param response
	* @param @return    
	* @return ModelAndView  trendAnalysis.jsp  
	* @throws
	 */
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView returnView=new ModelAndView("yieldAnalysis");
		List<String> typeNames=mYieldAnalysisService.getTypeNames();
		returnView.addObject("typeNames", typeNames);
		return returnView;
	}

	@RequestMapping("getIntermidates")
	@ResponseBody
	public Map<String,List<String>> getIntermidates(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,List<String>> ret=new HashMap<String,List<String>>();
		String typeName=request.getParameter("typeName");
		List<String> intermidates=mYieldAnalysisService.getIntermidates(typeName);
		ret.put("intermidates", intermidates);
		return ret;
	}
	@RequestMapping("getTableName")
	@ResponseBody
	public String getTableName(@RequestParam(value="productName",required=true)String productName,
			@RequestParam(value="midName",required=true)String midName,HttpServletRequest request) {
		tableName = midIndexService.getTableName(productName, midName);
		return tableName;
	}
	@RequestMapping("getVol")
	@ResponseBody
	public String getVol(@RequestParam(value="minBatchNo",required=true)String minBatchNo,
			@RequestParam(value="maxBatchNo", required=true)String maxBatchNo,HttpServletRequest request){
		List<JyhCy> volList = jyhCyService.getZlyTotalVol("KANION." + tableName, "'"+minBatchNo+"'", "'"+maxBatchNo+"'");
		String volString = JSONArray.toJSONString(volList);
		return volString;
	}
}
