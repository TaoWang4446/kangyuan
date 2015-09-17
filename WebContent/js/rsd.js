var chart1;//全局变量
var rsdVal;//计算的rsd值
var jsonData;//保存后台传过来的值
var column = 10;//显示中间体的表格每一行的列数
var rsdData=[];//画图数据准备
var pinming;//品名
var zhongjianti;//中间体
var option = {
		chart:{
			renderTo:'tablesRsd',
			type:'line',
			//height:'500'
		},
		title:{
			text:'热毒宁注射液金青提取物'
		},
		credits:{
				href:'www.xxx.org',
				position:{
					x:-30,
					y:-30
				},
				style:{
					color:'#909090',
					fontWeight:'normal'
				},
				enable:true,
				text:''
			}
		,
		xAxis:{
			title:{
				text:'批号'
			},
			type:'linear',
			startOnTick:true,
			min:0,
			//tickInterval:2

			categories:[]

		},
		yAxis:{
			title:{
				text:''
			},
			type:'linear',
			startOnTick:true,
			min:0,
			tickInterval:100
		},
		series:[{
			name:'栀子',
			//data:[435,409,413,372,400,381,458,376,343,379,350,369,443,361,392,363,387,386,431,406]
			data:[]
		}]
	};

$(document).ready(function(){
	var $pm = $('.pinming');
	var url = $("#basePath").val()+"rsdAnalysis/rsdExtraction.json";
	$.ajax({
		async:false,
		type:'get',
		//url:'http://localhost:8080/kanion/rsd/rsdExtraction.json',
		url:url,
		dataType:'json',
		success:function(data){
			jsonData = data;
			rsdVal = rsdCalculate(data);
		},
		error:function(data){
			alert('json数据获取失败，请联系管理员');
		}
	});
	
	var isCreated = false;
	//利用jquery的事件绑定机制
	$(document).on('click','#zs_midButton',function() {
		var pm = $(".tablesA").find("option:selected").text();
		$('#zs_value').html(rsdVal + "%");
		if(!isCreated){
			createTable(jsonData);
			isCreated = true;
		}
		option.title.text = pm;
		zhongjianti = $(".zs_projA").find("option:selected").text();
		option.yAxis.title.text = zhongjianti;
		option.series[0].name = pm;
		chart1 = new Highcharts.Chart(option);
		
	})
	.on('change','.pinming', function(){
		var pm = $.trim($(this).find('option:selected').text()),
			$midPro = $('.midPro'),
			$param = $('.param'),
			url = $("#basePath").val()+"rsdAnalysis/getmidname.json",
			data = {};
		data.productName = pm;
		$midPro.html('');
		$midPro.append('<option value=""-1>请选择</option>');
		$param.html('');
		$param.append('<option value="-1">请选择</option>');
		$.ajax({
			type:'POST',
			url: url,
			data:data,
			async: false,
			dataType: 'text',
			success: function(data) {
				data = $.parseJSON(data);
				$(data).each(function(index,item) {
					$midPro.append('<option value="' + index +'">' + item + '</option>');
				})
			},
			error: function() {
				alert('获取中间体失败，请联系管理员！');
			}
		})
	})
	.on('change','.midPro', function() {
		var pm = $.trim($('.pinming').find('option:selected').text()),
			midName = $.trim($(this).find('option:selected').text()),
			$param = $('.param'),
			url = $("#basePath").val()+"rsdAnalysis/getParamByPmMid.json",
			data = {};
		data.productName = pm;
		data.midName = midName;
		$param.html('');
		$param.append('<option value="-1">请选择</option>');
		$.ajax({
			type:'POST',
			url:url,
			data:data,
			dataType: 'text',
			success: function(data) {
				data = $.parseJSON(data);
				$(data).each(function(index,item) {
					$param.append('<option value="' + index +'">' + item + '</option>');
				})
			},
			error:function() {
				alert('获取参数失败，请与管理员联系!');
			}
		})

	})
	.on('change', '.param', function() {
		var pm = $.trim($('.pinming').find('option:selected').text()),
			midName = $.trim($('.midPro').find('option:selected').text()),
			url = $("#basePath").val()+"rsdAnalysis/getTableName.json",
			data = {};
		data.productName = pm;
		data.midName = midName;
		$.ajax({
			type:'POST',
			url:url,
			data:data,
			dataType: 'text',
			success: function(data) {
				console.log(data);
				// data = $.parseJSON(data);
				// $(data).each(function(index,item) {
				// 	$param.append('<option value="' + index +'">' + item + '</option>');
				// })
			},
			error:function() {
				alert('获取数据表名失败，请与管理员联系!');
			}
		})
	})
});
//动态创建显示中间值的表格
function createTable(rsdObject) {
	var length = rsdObject.length;
	var rowNum = Math.ceil(length/column);
	//pinming = rsdObject[0].pinming;
	//zhongjianti = rsdObject[0].zhongjianti;
	
	$('.zs_mid').append("<caption>各批号中间体(Kg)</caption>");
	for(var i = 0;i < rowNum;i++) {//之前犯了一个愚蠢的不仔细的问题，把单引号写在pihao后面了，改了很久
		$('.zs_mid').append("<tr class='pihao"+ i +"'></tr>");
		$('.zs_mid').append("<tr class='pihaoval" + i + "'></tr>");
	}
	$.each(rsdObject,function(num,item){

		option.xAxis.categories.push(item.pihao);

		rsdData.push(item.pihaoValue);
		var index = Math.floor(num/column);	 
		$('.zs_mid .pihao' + index).append("<td>" + item.pihao + "</td>");
		$('.zs_mid .pihaoval' + index).append("<td>" + item.pihaoValue + "</td>");
	});
	//console.log(rsdData);
	//option.series[0].name = pinming;
	option.series[0].data = rsdData;
	
	
}

//计算rsd值
function rsdCalculate( rsdObject ) {
	var totalValue = 0.0;
	var meanValue = 0.0;
	var totalSquareValue = 0.0;
	var rsdValue = 0.0;
	var length = rsdObject.length;
	$.each(rsdObject,function(num,item) {
		totalValue += item.pihaoValue;
	});
	meanValue = (totalValue/length).toFixed(6);
	
	$.each(rsdObject,function(num,item) {
		var dif = item.pihaoValue - meanValue;
		totalSquareValue += Math.pow(dif,2);
	});
	
	rsdValue = (Math.sqrt(totalSquareValue)/(length - 1))/meanValue;
	rsdValue = (rsdValue * 100).toFixed(2);
	return rsdValue;
}