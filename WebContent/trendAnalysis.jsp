<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
							<dt csstxt="趋势显示" class="file-gnm">
								<i class="i iB i2"></i>
							</dt>
							<dd><i class="w100 dis-ib">品名</i>
								<select class="defaultOption" id="typeNames">
									<option value="-1">请选择</option>
									<c:forEach items="${typeNames}" var="typeName">
										<option>${typeName}</option>
									</c:forEach>
								</select>
							</dd>
							
							<dd>								
								<i class="w100 dis-ib">工段</i>
								<select class="defaultOption" id="processes">
								</select>
							</dd>
							<dd>	
								<i class="w100 dis-ib">阶段</i>
								<!-- <select class="defaultOption" id="phases" multiple="multiple" SIZE="2" style="height:40px;"> -->
								<select class="defaultOption" id="phases">
								</select>
								<!-- <button type="button" id="phaseSelectBtn" class="orange-btn w200 mt15 floatRight" >选择阶段</button> -->
							</dd>

							<dd>	
								<i class="w100 dis-ib">生产参数选取</i>
								<select class="defaultOption" id="arguments">
								</select>							
								<button type="button" id="trendAnalysisBtn" class="orange-btn w200 mt15 floatRight" >趋势显示</button>
							</dd>
							<dd>							
								<i class="w100 dis-ib">批号</i>
								<!-- <select type="text" class="defaultOption" id="batchNos">
									<option >请选择</option>
								</select> -->
								<input type="text" class="defaultOption" id="batchNos">
							</dd>
							<dd>

								<input type="button" class="w100 dis-ib guan-btn" value="获取罐">
								<!-- <button class="w100 dis-ib">提取罐选取</button> -->
								<ul class="guan-list">
									<!-- <li>罐1 <input type="checkbox" name="t3001" value="T3001A"></li>
									<li>罐2 <input type="checkbox" name="t3001" value="T3001B"></li>
									<li>罐3 <input type="checkbox" name="t3001" name="t3001" value="T3001C"></li>
									<li>罐4 <input type="checkbox" name="t3001" value="T3001D"></li>
									<li>罐5 <input type="checkbox" name="t3001" value="T3001E"></li>
									<li>罐6 <input type="checkbox" name="t3001" value="T3001F"></li>
									<li>罐7 <input type="checkbox" name="t3001" value="T3001G"></li>
									<li>罐8 <input type="checkbox" name="t3001" value="T3001H"></li>
									<li>罐9 <input type="checkbox" name="t3001" value="T3001I"></li> -->
								</ul>
	 						</dd>							
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
	<input type="hidden" value="<%=basePath%>" id=basePath>
 	



<script type="text//javascript"></script>
<script language="JavaScript" type="text/javascript">
	var basePath=$("#basePath").val(),
		params = '',
		optionData = [],
		cagries = [],
		yTitle = '', 
		seriesName = '',
		title = '',
		unit = '',
		$typeName = $('#typeNames'),
		$process = $("#processes"),
		$phases = $('#phases'),
		$arguments = $('#arguments'),
		$batchNos = $('#batchNos'),
		series = [];
	$(function(){
		var pageName=$("#pageName").val();
		loadHead(pageName);
	});


//点击趋势显示按钮

$("#trendAnalysisBtn").click(function(){		
	var url=basePath+"trendAnalysis/drawChart.json",
		typeName = $.trim($typeName.val()),
		process = $.trim($process.val()),
		stage = $.trim($phases.val()),
		argument = $.trim($arguments.val()),
		batchNo = $.trim($batchNos.val()),
		data = {},
	    $guansSelect = $('.guan-list').find('input:checked');
	    series = [];
	$guansSelect.each(function(i, item) {
		var obj = {
			name:$(this).val(),
			data:[]
		}
		series.push(obj);
	});

	if(parseInt(typeName) < 0) {
		alert('请选择品名');
		return;
	}
	if(parseInt(process) < 0) {
		alert('请选择工段');
		return;
	}
	if(stage === '请选择') {
		alert('请选择阶段');
		return;
	}
	if(parseInt(argument) < 0) {
		alert('请选择生产参数');
		return;
	}
	if(parseInt(batchNo) < 0) {
		alert('请选择批号');
		return;
	}
	data.productName = typeName;
	data.processName = process;
	data.stageName = stage;
	data.paraName = argument;
	data.batchName = batchNo;
	title = typeName;
	yTitle = argument;
	$.ajax({
		type:'POST',
		url: url,
		data: data,
		async: false,
		dataType: 'text',
		success: function(data) {
			data = $.parseJSON(data);
			//console.log(data);
			optionData = [];
			cagries = [];
			unit = data[0].S_UNIT;
			 yTitle += ' (' + unit + ')';
			$(data).each(function(i, item) {
				$(series).each(function(index) {
					if(item.S_DEVICE_CODE === series[index].name) {
						series[index].data.push(item.N_VALUE);
					}
				})
				optionData.push(item.N_VALUE);
				cagries.push(timeFormat(item.TM_CURT));
			})
			console.log(series);
			drawAction();
			showHighcharts($('#container'));

		}
	})
	//showHighcharts($('#container'));
});	



//显示品名和工段
$("#typeNames").on('change', function() {
	
	var url = basePath + "trendAnalysis/getProcessAndBo.json",
		typeName = $("#typeNames").val(),
		$batchNos = $('#batchNos'),
		data = {},
		proName = '';
	data.productName = $.trim(typeName);
	$phases.empty();
	$process.empty();
	$batchNos.empty();
	$arguments.empty();
	$('.guan-list').html('');
	$process.append('<option value="-1">请选择</option>');
	$batchNos.append('<option value="-1">请选择</option>');
	$phases.append('<option value="-1">请选择</option>');
	$arguments.append('<option value="-1">请选择</option>');
	$.ajax({
		type:'POST',
		url: url,
		data: data,
		async: false,
		dataType: 'text',
		success: function(data){
			data = $.parseJSON(data);
			$(data).each( function(index, item) {
				if(item.S_PROCESS_NAME !== proName) {
					$process.append('<option>' + $.trim(item.S_PROCESS_NAME) + '<option/>');
					proName = item.S_PROCESS_NAME;
				}
				// $batchNos.append('<option>' + $.trim(item.S_BATCH_NUMBER) + '<option/>');
				console.log(item.S_BATCH_NUMBER);
			})
		}
	})
	
});

$('.intro').on('change', '#processes', function() {
	var url = basePath + "trendAnalysis/getStageName.json",
		data = {},
		processName = $.trim($process.val());
	data.processName = processName;
	$phases.empty();
	$('.guan-list').html('');
	$phases.append("<option>请选择</option>");
	$.ajax({
		type:'POST',
		url: url,
		data: data,
		async: false,
		dataType: 'text',
		success: function(data) {
			data = $.parseJSON(data);
			$(data).each(function(index,item) {
				$phases.append("<option>" + item + "</option");
			})
		}
	})
})
.on('change', '#phases', function() {
	var url = basePath + "trendAnalysis/getParam.json",
		stageName = $.trim( $phases.val() ),
		data = {};
	data.stageName = stageName;
	$arguments.empty();
	$('.guan-list').html('');
	$arguments.append('<option value="-1">请选择</option>');
	$.ajax({
		type:'POST',
		url: url,
		data: data,
		async: false,
		dataType: 'text',
		success : function(data) {
			data = $.parseJSON(data);
			$(data).each(function(index, item) {
				$arguments.append('<option>' + item +'</option>');
			})
		}
	})

})
.on('click','#batchNos', function() {
	$('.guan-list').html('');
})
.on('click','.guan-btn', function() {
	var batchNo = $.trim($('#batchNos').val()),
		data = {},
		url = '';
	if(!batchNo) {
		alert('请先输入批号');
		return;
	}
	data.batchName = batchNo;
	url = basePath + "trendAnalysis/getDeviceName.json";
	$('.guan-list').empty();
	$.ajax({
		type:'POST',
		url: url,
		data: data,
		async: false,
		dataType: 'text',
		success : function(data) {
			data = $.parseJSON(data);
			if(!data.length) {
				alert('该批号不存在，请重新输入');
				return;
			}
			$(data).each(function(index, item) {
				$('.guan-list').append('<li>罐' + item + '  <input type="checkbox" name="t3001" value="'+ item +'"></li>')
			})
		}
	})
})

function timeFormat(datetime) {
	// var tt=new Date(parseInt(datetime)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ") ;
	var tt = new Date(parseInt(datetime));
	return tt.getFullYear() + "/" + (tt.getMonth() + 1) + "/" + tt.getDate() +
			"_" + tt.getHours() + ":" + tt.getMinutes() + ":" + tt.getSeconds();
	
}

function drawAction() {
    $('#container').highcharts({
    	chart: {
            type: 'spline',
        },
        title: {
        	margin:40,
            text: title,
            style:{
        		color:'#64B9C9',
        		fontSize:'18px',
			    fontFamily:'微软雅黑'
        	}
        },
        xAxis: {
        	title: {
                text: '生产过程时间点',
                style:{
	        		color:'#64B9C9',
	        		fontSize:'14px',
				    fontFamily:'微软雅黑'
	        	}
            },
            categories: cagries
        },
        tooltip: {
        	valueSuffix: ' (' + unit + ')'
        },
        yAxis: {
        	min:0,
            title: {
                text: yTitle
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#64B9C9'
            }]
        },
        credits:{
            enabled:false // 禁用版权信息
        },
        // tooltip: {
        //     valueSuffix: '°C'
        // },
        // series:[{},{},{},{},{},{}],
        series: series
    });
}; 
			
</script>  	
</body>
</html>