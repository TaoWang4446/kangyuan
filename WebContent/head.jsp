<%@   page   language="java"   contentType="text/html;   charset=utf-8"%>
<%
String path2 = request.getContextPath();
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!--顶部导航-->
	<div class="Tray">
		<div class="tray">
			<a href="<%=basePath2%>index.jsp" title="康缘PKS统计挖掘系统" class="logo">康缘PKS统计挖掘系统</a>
			<ul class="nav">
				<li>
					<a href="<%=basePath2%>dataAnalysis.jsp">统计分析</a>				</li>
				<li>
					<a href="<%=basePath2%>dataMining.jsp">数据挖掘</a>	</li>
				<li>
					<a>建模预测与反馈</a>				</li>
			</ul>
			<ul class="nav nav2">
				<li>欢迎您！kanion</li>
				<li></li>   		
			</ul>			
		</div>		
	</div>
	<!--工具栏-->
	<div class="Toolbar Toolbar2">
		<div class="toolbar" id="dataAnalysis" style="display: none">
			<ul class="tools" >
				<li id="trendAnalysis" class="tool">
					<button id="analysisBtn" title="趋势显示">
						<i class="i i1"></i>
					</button>	
				</li>	
				
				<li id="yieldAnalysis" class="tool">
					<button  id="analysisBtn" title="收率分析">
						<i class="i i22"></i>
					</button>	
				</li>	
						
				<li id="transferRateAnalysis" class="tool">
					<button  id="analysisBtn" title="转移率分析">
						<i class="i i3"></i>
					</button>	
				</li>	
				
				<li id="normalDistribution" class="tool">
					<button  id="analysisBtn" title="正太分布">
						<i class="i i4"></i>
					</button>	
				</li>	
				<!-- 
				<li class="tool">
					<button  id="analysisBtn" title="趋势分析">
						<i class="i i6"></i>
					</button>	
				</li>
				 -->
				<li id="rsdAnalysis" class="tool">
					<button  id="analysisBtn" title="RSD计算">
						<i class="i i5"></i>
					</button>	
				</li>
				<li id="curvefitAnalysis" class="tool">
					<button  id="analysisBtn" title="方程拟合">
						<i class="i i7"></i>
					</button>	
				</li>
			</ul>
						
		</div>
		<div class="toolbar" id="dataMining" style="display: none">
			<ul class="tools" >

				<li class="tool" id="correlationSingleAnalysis">
					<button  id="analysisBtn" title="相关性分析(单)">
						<i class="i i8"></i>
					</button>	
				</li>
				
				<li class="tool" id="correlationMultiAnalysis">
					<button  id="analysisBtn" title="相关性分析(多)">
						<i class="i i9"></i>
					</button>	
				</li>
				
				<li class="tool" id="">
					<button  id="analysisBtn" title="预测分析">
						<i class="i i10"></i>
					</button>	
				</li>
				
				<li class="tool" id="">
					<button  id="stablityBtn" title="质量均一性">
						<i class="i i11"></i>
					</button>	
				</li>
				
				<li class="tool" id="">
					<button  id="q_statistics" title="Q统计量分析">
						<i class="i i12"></i>
					</button>	
				</li>
				
				<li class="tool" id="">
					<button  id="t_statistics" title="T统计量分析">
						<i class="i i13"></i>
					</button>	
				</li>
					
			</ul>
						
		</div>
		<input type="hidden" value="<%=basePath2%>" id="basePath2">
	</div> 
	
	
<script language="JavaScript" type="text/javascript">
function loadHead(pageName){
	if(pageName=="dataAnalysis"){
		$("#dataMining").hide();
		$("#dataAnalysis").show();
	}
	
	if(pageName=="dataMining"){
		$("#dataAnalysis").hide();
		$("#dataMining").show();
	}
		
};


$(".tool").click(function(){
	var basePath=$("#basePath2").val();
	var id = $(this).attr("id");
	if(id==""){
		alert("此模块正在开发中...")
		return;
	}
	// location.href = basePath + id + ".jsp";
	if(id=="trendAnalysis" || id=="yieldAnalysis" || id == "rsdAnalysis"){
		location.href = basePath + id + "/init.json";
	}else{
		location.href = basePath + id + ".jsp";
	}
});	



</script>