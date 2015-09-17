package com.kanion.www.model.analysis.yield;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.kanion.www.service.MidIndexService;
import com.kanion.www.util.Util;

public class YieldAnalysisSelect {
	

	/**
	 * 类初始化操作，在类初始化的时候实例化单例。
	 */
	public static final YieldAnalysisSelect INSTANCE;
	static{
		String jsonString=Util.readFile("com/kanion/www/json/yield.json");
		INSTANCE=JSON.parseObject(jsonString,YieldAnalysisSelect.class);
	}
	
	private List<TypeName> typeNames;
	
	/**
	 * json转换需要默认构造器
	 */
	private YieldAnalysisSelect(){
	}
	
	public List<TypeName> getTypeNames() {
		return typeNames;
	}
	/**
	 * 
	* @Title: setTypeNames
	* @Description: 在类刚加载进来时，INSTANCE为空，此时可以通过fastjson来设置typeName.
	* 一旦单例对象生成，任何人都无法修改typeName,以此来保证整个工程只有一个对象正确地表达表间关系。
	* 因为setTypeNames只有在对象生成后才会被调用，故在对象生成前不会有null==INSTANCE的情况，如此
	* 即使多个程序同时调用该方法也不会有不同步的问题。
	* @param @param typeNames
	* @param @throws Exception    
	* @return void    
	* @throws
	 */
	//@JSONField(name="typeNames") 可以通过注解来指定目标属性
	public void setTypeNames(List<TypeName> typeNames) throws Exception {
		if(null==this.typeNames){
			this.typeNames = typeNames;
		}else{
			throw new Exception("set typenames unsupported!");
		}
	}

	@Override
	public String toString(){
		StringBuilder str=new StringBuilder();
		str.append("{'typeNames':[");
		boolean isFirst=true;
		for(TypeName each:typeNames){
			if(isFirst){
				isFirst=false;
			}else
				str.append(",");
			str.append(each.toString());
		}
		str.append("]}");
		return str.toString();
	}
	
	public static void main(String[] args){
		System.out.println(YieldAnalysisSelect.INSTANCE);
	}
}
