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
	 <script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
	 <script type="text/javascript" src="../js/jquery-extend.js"></script>
	 <script type="text/javascript" src="../js/highcharts.js"></script>
	 <script type="text/javascript" src="../js/exporting.js"></script>
	 <script type="text/javascript" src="../js/util.js"></script>
	 <!-- <script type="text/javascript" src="../js/yeild.js"></script>
	<!-- // <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	// <script type="text/javascript" src="js/jquery-extend.js"></script>
	// <script type="text/javascript" src="js/highcharts.js"></script>
	// <script type="text/javascript" src="js/exporting.js"></script>
	// <script type="text/javascript" src="js/util.js"></script> -->
		
</head>
<body>
	<input id="pageName" value="dataAnalysis" type="hidden"/>
	<c:import url="head.jsp"  charEncoding="UTF-8" />
	<div id="Bdy">
		<div class="bdy">
			<div class="main">	
				<div class="main_content">			
					<!-- *****************************************************趋势分析 ***************************************************** -->
					<div id="trendAnalysisDiv" >
					<!-- 分析目标表列表 -->
						<div class="intro">
							<dl>
								<dt csstxt="收率分析" class="file-gnm">
									<i class="i iB i2"></i>
								</dt>
								<dd><i class="w100 dis-ib">品名</i>
									<select class="defaultOption" id="typeNames">
										<option value="-1">请选择品名</option>
										<c:forEach items="${typeNames}" var="typeName">
											<option>${typeName}</option>
										</c:forEach> 
										<!-- <option value="">金银花</option>
										<option value="">栀子</option> -->
									</select>							
								</dd>
								<dd>								
									<i class="w100 dis-ib">中间体</i>
									<select class="defaultOption" id="intermidates">
																										
									</select>
									<button type="button" id="yieldAnalysisBtn" class="orange-btn w200 mt15 floatRight" >收率分析</button>
								</dd>
								<dd>							
									<i class="w100 dis-ib">批号</i>
									<select class="defaultOption minBatchNo">
										<option>Z150501</option>
										<option>Z150502</option>
										<option>Z150503</option>
										<option>Z150504</option>
										<option>Z150505</option>
										<option>Z150506</option>
										<option>Z150507</option>
										<option>Z150508</option>
										
									</select>
									-
									<select class="defaultOption maxBatchNo">
										<option>Z150501</option>
										<option>Z150502</option>
										<option>Z150503</option>
										<option>Z150504</option>
										<option>Z150505</option>
										<option>Z150506</option>
										<option>Z150507</option>
										<option>Z150508</option>
									</select>
								</dd>
								
								
								<!-- 
								<dd>	
									<i class="w100 dis-ib">项目</i>
									<select class="defaultOption" id="items">
										<option>体积</option>
										<option>温度</option>
										<option>重量</option>
									</select>							
								</dd>		
								 -->					
							</dl>						
						</div>	
	
					<!-- 表格展示模块 -->
						<div class="tables">
							<div id="container" class="noDis container"></div>							
							<!-- 图表说明 -->	
							<div id="chartInfo" class="chartInfo"></div>								
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="<%=basePath%>" id=basePath />

 <script type="text/javascript">
 </script>	

 <script  type="text/javascript">
	$(function(){
		var pageName=$("#pageName").val();
		loadHead(pageName);
	});

var option = {
		 chart: {
			 renderTo:'container', 
	        type: 'column'
	        },
	        title: {
	            text: '金银花提取液收率分析'
	        },
	        subtitle: {
	            text: ''
	        },
	        credits:{
	        	text:''
	        },
	        xAxis: {
	            categories: []
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '体积 (L)'
	            }
	        },
	        tooltip: {
	        	valueSuffix: 'L'
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0,
	                dataLabels:{//将值显示在柱状图的上面
	                	enabled:true,
	                	crop:false,
	                	overflow:'none'
	                }
	            }
	        },
	        series: [{
	            name: '金银花提取液体积',
	           // data: [581,720,557,604,575,632,578,609,581,658,639,575,629,621,
	             //      611,623,634,634,568,637] 
	        	data:[]
	        },{
	        	name:'金银花提取液体积均值',
	        	type:'spline',
	        	data:[]
	        }]
	},
	basePath=$("#basePath").val(),
	chart;
$("#yieldAnalysisBtn").click(function(){
	var  minBatchNo = $('.minBatchNo').val(),
		 maxBatchNo = $('.maxBatchNo').val(),
		 data = {};
	data.minBatchNo = $.trim(minBatchNo);
	data.maxBatchNo = $.trim(maxBatchNo);
	$.each(option.series,function(i,item) {
		item.data = [];
	});
	url = basePath+"yieldAnalysis/getVol.json";
	$.ajax({
		type: 'post',
		url: url,
		data : data,
		dataType: 'json',
		async : false,
		success : function(data) {
			option.xAxis.categories = [];
			option.series[0].data = [];
			$(data).each(function(i,item) {
				option.xAxis.categories.push(item.sBatchNumber)
				option.series[0].data.push(item.fZlyTotalVol);
			})
		}
	});
	avgYield();
	chart = new Highcharts.Chart(option);
	showHighcharts($('#container'));
});



//均值函数
function avgYield() {
	
	var avgData = option.series[0].data;
	var total = 0.0;
	var average;
	for(var i = 0;i < avgData.length ;i++) {
		total += avgData[i];
	}
	average = (total/avgData.length).toFixed(2);
	average = parseFloat(average);
	for(var i =0;i < avgData.length ;i++){
		option.series[1].data.push(average);
	}
}


/**
 * 点击品名显示中间体
 */
$("#typeNames").click(function(){
	var url=basePath+"yieldAnalysis/getIntermidates.json",
		typeName=$("#typeNames").val(),
		data={};
	data.typeName=typeName;
	var returnData=getJSON(url,data);
	var intermidates=returnData.intermidates;
	$("#intermidates").empty();
	$.each(intermidates,function(){
		$("#intermidates").append("<option>"+this+"</option>");
	});
});

$('#intermidates').on('change', function() {
	var typeName=$("#typeNames").val(),
		midName = $(this).val(),
		data = {},
		url = '';
	data.productName =$.trim(typeName);
	data.midName =$.trim(midName);
	url = basePath+"yieldAnalysis/getTableName.json";
	$.ajax({
		type: 'post',
		url: url,
		data : data,
		dataType: 'text',
		async : false,
		success : function(data) {
			
		}
	});
	
}) 
</script> 	
</body>

</html>