<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>康缘PKS统计挖掘系统</title>
		<link rel="stylesheet" type="text/css" href="../CSS/global.css">
		<link rel="stylesheet" href="../CSS/global_zs.css" type="text/css">
		<link rel="stylesheet" href="../CSS/rsd.css" type="text/css" >
		<script src="../js/jquery-1.7.1.js" type="text/javascript" ></script>
		<script src="../js/highcharts.js" type="text/javascript"></script>
		<script src="../js/exporting.js" type="text/javascript" ></script>
		<script src="../js/global.js" type="text/javascript"></script>
		<script src="../js/rsd.js" type="text/javascript"></script>		
</head>
<body>
	<input id="pageName" value="dataAnalysis" type="hidden"/>
	<c:import url="head.jsp"  charEncoding="UTF-8" />	
		<div id="zs_mainbody">
			<div class="zs_bdy">
				<div class="zs_main">
					<div id="rsddiv" class="zs_rsddiv">
						<div class="zs_selectData">
							<dl>
								<dt csstxt="RSD值计算" class="zs_file-gnm">
								<i class="i iB i2"></i>
								</dt>
								<dd>
									<fieldset class="zs zs_rsd_fieldset">
										<legend>请选择 A:</legend>
										<table>
											<tr>
												<td class="zs_name"><span>品名:</span></td>
												<td><select name="tables" id="tables" class="tablesA pinming">
													<option value="-"1>请选择</option>
													<c:forEach items="${typeNames}" var="typeName">
													<option>${typeName}</option>
													</c:forEach>
													<!-- <option value="" >热毒宁注射液青金提取物</option>
													<option value="" selected>热毒宁注射液栀子提取物</option> -->
												</select></td>
												<!-- <td class="zs_name"><span>中间体:</span></td>-->
												<!--<td><select name="midSub" id="midSub" ></select></td>-->
												</tr>
												
											<tr>
											
											<tr>
												<td class="zs_name"><span>中间体:</span></td>
												<td><select name="proj" id="zs_proj" class="zs_projA midPro">
													<!-- <option value="" selected>金青回收浸膏重量</option>
													<option value="">干膏</option> -->
												</select></td>
											</tr>
											
												<td class="zs_name"><span>项目:</span></td>
												<td><select name="proj" id="zs_proj" class="param">
													<!-- <option value="" selected>重量</option>
													<option value="" selected>体积</option> -->
												</select></td>
											</tr>
											
											<tr><td class="zs_name"><span>批次:</span></td>
												<td><input type="text" id="minBatchNo" name="minBatchNo" class="zs_ser">
												</input>
												<input name="maxBatchNo" id="maxBatchNo" class="zs_ser"></input>
									</td>
												
											</tr>
										</table>
									</fieldset>
									<fieldset class="zs zs_rsd_fieldset">
										<legend>请选择 B：</legend>
										<table>
											
											<tr>
												<td class="zs_name"><span>品名:</span></td>
												<td><select name="tables" id="tables" class="pinming">
													<!-- <option value="">热毒宁注射液栀子提取物</option>
													<option value="">热毒宁注射液金青提取物</option> -->
												</select></td>
											</tr>
											<tr>
												<td class="zs_name"><span>中间体:</span></td>
												<td><select name="proj" id="zs_proj">
													<option value="" selected>金青回收浸膏重量</option>
													<option value="">干膏</option>
												</select></td>
											</tr>
											<tr>
												<td class="zs_name"><span>项目:</span></td>
												<td><select name="proj" id="zs_proj">
													<option value="">重量</option>
													<option value="">体积</option>
												</select></td>
											</tr>
											<tr>
												 <td class="zs_name"><span>时间段:</span></td>
												<td><select name="start" id="start" class="zs_ser">
													<option value="">1月</option>
													<option value="">2月</option>
													<option value="">3月</option>
													<option value="">4月</option>
													<option value="">5月</option>
													<option value="">6月</option>
													<option value="">7月</option>
													<option value="">8月</option>
													<option value="">9月</option>
													<option value="" >10月</option>
													<option value="">11月</option>
													<option value="">12月</option>
			
												</select>
												<select name="end" id="end" class="zs_ser">
													<option value="">1月</option>
													<option value="">2月</option>
													<option value="">3月</option>
													<option value="">4月</option>
													<option value="">5月</option>
													<option value="">6月</option>
													<option value="">7月</option>
													<option value="">8月</option>
													<option value="">9月</option>
													<option value="">10月</option>
													<option value="">11月</option>
													<option value="">12月</option>
					
												</select >
												</td>
											</tr>
										</table>
									</fieldset>
									<div class="zs_cal">
										<!--  <div class="zs_sr">
											<span>中间体:</span>
											<span class="zs_svalue"></span>
										</div>-->
										<div class="zs_sr">
											<span>RSD值:</span>
											<span class="zs_svalue" id="zs_value"></span>
										</div>
										<button type="button" class="zs_midButton" id="zs_midButton">RSD计算</button>
									</div>
									
								</dd>
							</dl>
						</div>
					</div>
				</div>
				
				
				<div class=" charts tablesRsd" id="tablesRsd"></div> 
				<div class="zs_midValue">
					 <table class="zs_mid"></table>
				</div>
			</div>
		</div>
		
		<input type="hidden" value="<%=basePath%>" id=basePath>
</body>
<script language="JavaScript" type="text/javascript">
$(function(){
	var pageName=$("#pageName").val();
	loadHead(pageName);
});
</script>
</html>