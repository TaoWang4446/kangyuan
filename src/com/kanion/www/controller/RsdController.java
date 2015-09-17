package com.kanion.www.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.kanion.www.model.JyhCy;
import com.kanion.www.model.RsdExtraction;
import com.kanion.www.service.JyhCyService;
import com.kanion.www.service.MidIndexService;
import com.kanion.www.service.YieldAnalysisService;

/**
 * 
 * @author zhangsi
 *
 */

@Controller
@RequestMapping("rsdAnalysis")
public class RsdController {
	private String tableName = "";
	@Autowired
	private YieldAnalysisService mYieldAnalysisService;
	@Autowired
	private MidIndexService midIndexService;
	
	@Autowired
	private JyhCyService jyhCyService;
	
	@RequestMapping("init")
	public ModelAndView init(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView returnView=new ModelAndView("rsdAnalysis");
		List<String> typeNames=mYieldAnalysisService.getTypeNames();
		returnView.addObject("typeNames", typeNames);
		return returnView;
	}
	//获取中间体
	@RequestMapping("getmidname")
	@ResponseBody
	public String getMidName(@RequestParam(value="productName",required=true)String productName ) {
		List<String>midNames = midIndexService.getMidName(productName);
		return JSONArray.toJSONString(midNames);
	}
	//获取表名
	@RequestMapping("getTableName")
	@ResponseBody
	public String getTableName(@RequestParam(value="productName", required=true)String productName,
			@RequestParam(value="midName", required=true)String midName) {
		tableName = midIndexService.getTableName(productName, midName);
		System.out.println(tableName);
		return tableName;
	}
	
	@RequestMapping("getParamByPmMid")
	@ResponseBody
	public String getParamByPmMid(@RequestParam(value="productName", required=true)String productName,
			@RequestParam(value="midName", required=true)String midName) {
		List<String>paramNames = midIndexService.getParamByPmMid(productName, midName);
		return JSONArray.toJSONString(paramNames);
	}
	
	@RequestMapping("getData")
	@ResponseBody
	public String getData(@RequestParam(value="minBatchNo",required=true)String minBatchNo,
			@RequestParam(value="maxBatchNo", required=true)String maxBatchNo,HttpServletRequest request){
		List<JyhCy> volList = jyhCyService.getZlyTotalVol("KANION." + tableName, "'"+minBatchNo+"'", "'"+maxBatchNo+"'");
		String volString = JSONArray.toJSONString(volList);
		return volString;
	}
	
	
	@RequestMapping("/rsdExtraction")
	@ResponseBody
	public String rsdExtraction(HttpServletRequest request,HttpServletResponse response){
		String pihao[]={"Z150501","Z150502","Z150503","Z150504","Z150505","Z150506",
				"Z150507","Z150508"};
		//double value []= {478,490,473,490,480,490,480,485,475,480,470,484,489,499,483,
				//490,480,470,485,490,477,480,472,480,490};
		double value [] ={380,390,385,395,400,398,390,400};

		JSONArray jsonArray = new JSONArray();
		for(int i = 0;i < value.length;i++) {
			RsdExtraction rsdExtraction = new RsdExtraction();
			rsdExtraction.setPinming("金青");
			rsdExtraction.setZhongjianti("金青回收浸膏重量(Kg)");
			rsdExtraction.setPihao(pihao[i]);
			rsdExtraction.setPihaoValue(value[i]);
			jsonArray.add(rsdExtraction);
		}
		return jsonArray.toString();
	}
}
