/**
* Filename: MybatisTest.java
* Project Name: kanion
* @author: cyz	imchyz(at)qq.com
* @version: 1.0
* @since: JDK 1.7.0_45
* Copyright © 2014 MZStudio. All Rights Reserved
* Company: www.MZStudio.com
* Create at: 2014-8-24  下午4:55:15
* Description:
*
* Modification History:
* Date			Author		Version		Description
* ------------------------------------------------------------------
* 2014-8-24	cyz    		1.0			1.0 Version
*/
package com.kanion.www.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.kanion.www.model.JyhCy;
import com.kanion.www.model.JyhTqBw;
import com.kanion.www.model.ParaIndex;
import com.kanion.www.service.JyhCyService;
import com.kanion.www.service.JyhTqBwService;
import com.kanion.www.service.JyhTqDBService;
import com.kanion.www.service.MidIndexService;
import com.kanion.www.service.ParaIndexService;
import com.kanion.www.service.UserService;
import com.kanion.www.util.CreateTable;

/**
 * @ClassName: MybatisTest
 * @Description: TODO
 * @author CYZ	imchyz@qq.com
 * @date 2014-8-24 下午4:55:15
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "/WEB-INF/cfg/spring.xml","/WEB-INF/cfg/spring-mybatis.xml"})
public class MybatisTest {
	private static MidIndexService midIndexService;
	@Autowired
	public void setMidIndexService(MidIndexService midIndexService) {
		this.midIndexService = midIndexService;
	}
	private JyhCyService jyhCyService;
	
	@Autowired
	public void setJyhCyService(JyhCyService jyhCyService) {
		this.jyhCyService = jyhCyService;
	}
	private UserService userService;
	private JdbcTemplate jdbcTemplate;
	
	private ParaIndexService paraIndexService;
	@Autowired
	public void setParaIndexService(ParaIndexService paraIndexService) {
		this.paraIndexService = paraIndexService;
	}
	
	@Resource
	private DataSource dataSource;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Autowired
	public void setUserService(UserService userService){
		this.userService=userService;
	}
	
	
	private JyhTqDBService jyhTqDBService;
	@Autowired
	public void setJyhTqDBService(JyhTqDBService jyhTqDBService) {
		this.jyhTqDBService = jyhTqDBService;
	}

	@Test
	public void testGetUser(){
		BigDecimal i=new BigDecimal(1);
		//String tableName = midIndexService.getTableName("热毒宁注射液青金提取物", "金银花第二次提取液");
//		System.out.println(userService.getUser(i).getUsername());
		//System.out.println(tableName);
		//这里注意传入的表名的写法，之前一直报错
		//List<JyhCy> vol = jyhCyService.getZlyTotalVol("KANION."+tableName,"'Z150501'", "'Z150506'");
		//System.out.println(JSONArray.toJSONString(vol));
		
		//ParaIndex index = paraIndexService.getTableNameAndFiled("热毒宁注射液青金提取物", "金银花提取","金银花第一次提取", 
				//"保温-蒸汽压力");
		//String tableName = index.getsTableName();
		//System.out.println(index.getsTableName());
		//System.out.println(index.getsParaField());
		

		//List<Map<String,Object>> stages =  jyhTqDBService.getStageAndParam("金银花提取");
		//System.out.println(JSONArray.toJSONString(stages));
		
		//List<String> stage = jyhTqDBService.getStage("金银花提取");
		//System.out.println(JSONArray.toJSONString(stage));
		
		//List<String> stage = jyhTqDBService.getStage("热毒宁注射液青金提取物");
		//System.out.println(JSONArray.toJSONString(stage));
		
		List obj = jyhCyService.getByBatNoPm("KY_GY_DB","Z150812-1", "Z150812-2", "热毒宁注射液青金提取物");
		System.out.println(JSONArray.toJSONString(obj));
		
		
		/**
		 * 创建id自增的数据库的执行语句块
		 */
//		jdbcTemplate=new JdbcTemplate(dataSource);
//		CreateTable create=new CreateTable();
//		create.create("kengdieky", jdbcTemplate);
//		create.createSequence("zhii", jdbcTemplate);
//		create.idIncrement("kengdieky","zhii", jdbcTemplate);
//		create.create_trigger("kengdieky", "zhii","trss", jdbcTemplate);
	}

}
