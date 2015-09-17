package com.kanion.www.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class CreateTable {
	/**
	 * 
	 * @param tableName
	 * 创建表
	 */
	public void create(String tableName,JdbcTemplate jdbcTemplate){
		jdbcTemplate.execute("create table "+tableName+"(id int,name varchar2(100),age int,primary key(id))");
	}
	/**
	 * 
	 * @param seq
	 * 创建序列
	 */
	public void createSequence(String seq,JdbcTemplate jdbcTemplate){
		String create_seq="create sequence "+seq +" start with 1 "
				+ "increment by 1 minvalue 1 maxvalue 999999 nocycle nocache";
		jdbcTemplate.execute(create_seq);
	}
	/**
	 * 为id设置自增
	 */
	
	public void idIncrement(String tablename,String seq,JdbcTemplate jdbcTemplate){
		String sql="insert into "+ tablename+"(id) values("+seq+".NEXTVAL)";
		jdbcTemplate.execute(sql);
	}
	/**
	 * 创建触发器
	 */
	public void create_trigger(String tablename,String seq,String triggerName,JdbcTemplate jdbcTemplate){
		String creat_trig="create  trigger "+triggerName+" before insert on "+tablename+" for each row begin select "+seq+".NEXTVAL into :new.id from dual;end;";
		jdbcTemplate.execute(creat_trig);
	}
}
