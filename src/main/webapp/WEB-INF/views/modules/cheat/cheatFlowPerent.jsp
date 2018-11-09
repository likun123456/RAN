<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量占比评估表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		<%--设置全局变量--%>
		var netId;
		var gran;
		var startTime;
		var endTime;
		$(document).ready(function() {
			$("#startTimeLi").hide();
			$("#endTimeLi").hide();
			
			/**折叠组件三角图标切换**/
			triangleIconChange();
			$("#pastScope").change(function(){
				//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
				$("#startTimeLi").hide();
				$("#endTimeLi").hide();
				if(this.value==""){
					$("#beginDate").val("");
					$("#endDate").val("");
					return false;
				}
				if(this.value=="0"){
					$("#beginDate").val("");
					$("#endDate").val("");
					$("#startTimeLi").show();
					$("#endTimeLi").show();
					return false;
				}
				$("#startTime").val(getTacStartTime(this.value));
				$("#endTime").val(getCheatEndTime(this.value));
			});
			
			$("#searchForm").validate({
				submitHandler: function(form){
					if ($("#netId option:selected").val()==""){
	                	top.$.jBox.tip('请选择查询网元','warning');
					}else if ($("#gran option:selected").val()==""){
	                    top.$.jBox.tip('请选择粒度','warning');
	                }else if ($("#startTime").val()==""){
	                    $("#startTime").focus();
	                    top.$.jBox.tip('请选择时间段','warning');
	                }else if ($("#endTime").val()==""){
	                	$("#endTime").focus();
	                	top.$.jBox.tip('请选择时间段','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/cheat/cheatFlowPerent/exportExcel"){
	                		form.submit();
	                		$("#searchForm").attr('action',"");
	                	}else{
	                		queryTable();
	                	}
	                }
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
	    //图表自适应屏幕		
		$(window).resize(function() {    
			var width = parseInt($("#chart_div").css('width').replace("px",""))-20;
			if(typeof($("#chart_div").highcharts())!="undefined"){
				$("#chart_div").highcharts().setSize(width,this.offsetHeight-20,false)
			}
		});
		
		function showChart(data){			
			var width = parseInt($("#chart_div").css('width').replace("px",""))-20;
			$("#chart_div").highcharts({
		        title: {
		            text: '',
		            x: -20 
		        },
		        xAxis: {
		        	title:{
		        	    text:'时间'
		        	},
		        	type:'categories',
		        	labels: {
		            	formatter: function() {
		        		  var labelvalue  = this.value;
		        		  if(labelvalue.length==8){
		        			var reStr = labelvalue.split(":");
		        			return reStr[0]+":"+reStr[1];  
		        		  }
		        		  return this.value;
						}, 
	                    rotation: 0,
	                    align: 'center', 
	                    y: 30
	                },
	                tickmarkPlacement: 'on'
		        }, 
		        yAxis: {
		            title: {
		                text: '统计值 '
		            },
		            labels: {
                      formatter: function() {
                        return this.value;
                      }
                    } 
		        },
		        tooltip: {
		        	formatter: function (e) {
		        	    var f = e.chart.yAxis[0].axisTitle.textStr.split(" ")[1];
		                var s = '<b>' + this.x + '</b>';
		                $.each(this.points, function () {
		                    s += '<br/><span style=\"color:'+this.series.color+'\">●</span>' + this.series.name.split(" ")[0] + ': <b>' +
		                        this.y + ' '+f +'</b>';
		                });
		                return s;
	                },
		            crosshairs: true,
                    shared: true
		        },
		        plotOptions: {
		            spline: {
		                marker: {
		                    radius: 4,
		                    lineColor: '#666666',
		                    lineWidth: 1
		                }
		            },
		            line: {  
		                cursor: 'pointer'  
		            },
		        },
		        credits: {
                    enabled: false
                },
		         legend: {
		        	 layout: 'horizontal',
		             align: 'center',
		             verticalAlign: 'bottom',
		             borderWidth: 0
		        }
	        });
			
		    var seriesData = [];
			var dateTimeList = new Array();
			var dateTimeRemark=0;
	    	
	    	for ( var key in data) {
				var kpi=key;
				var result = new Array();
				for(var n=0;n<data[key].listCheatFlowPerent.length;n++){
					result.push(Number(data[key].listCheatFlowPerent[n].percent));
					if(dateTimeRemark==0){
						dateTimeList.push(data[key].listTime[n]);
					}
				}
				dateTimeRemark++;
				if(result.length>0){
					seriesData.push({name:'指标:'+key,data:result});
				}
	    	}
			$("#chart_div").highcharts().xAxis[0].setCategories(dateTimeList);
			
			var diff = $("#chart_div").highcharts().series.length - seriesData.length;  
		    if(diff > 0){  
		        for (var i = $("#chart_div").highcharts().series.length; i > diff; i--){  
		        	$("#chart_div").highcharts().series[i-1].remove(true);  
		      }  
		    } else if (diff < 0){  
		        for (var i = $("#chart_div").highcharts().series.length; i < seriesData.length; i ++){  
		        	$("#chart_div").highcharts().addSeries({});  
		      }  
		    }   
		    $("#chart_div").highcharts().update({  
		        series:seriesData  
		    });
		}
		
		function queryChart(){
			$.post('${ctx}/cheat/cheatFlowPerent/findChart', {
		        "netId": $("#netId option:selected").val(),
		        "startTime":$("#startTime").val(),
		        "endTime":$("#endTime").val(),
		        "gran":$("#gran option:selected").val()
		    }, function(dataQuery) {
		    	showChart(dataQuery);
		    });
		}
		
		function queryTable(){
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/cheat/cheatFlowPerent/queryList',
		        method:'POST',
		        dataType:'json',
		        cache:false,
		        contentType : "application/x-www-form-urlencoded",
		        striped: true,                      //是否显示行间隔色
		        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		        pagination:true,
		        minimumCountColumns:2,
		        pageNumber:1,                       //初始化加载第一页，默认第一页
		        pageSize: 10,                 //每页的记录行数（*）
		        pageList: [10, 25, 50, 100],   //可供选择的每页的行数（*）
		        queryParams:  function(params) {
		        	 netId = $("#netId option:selected").val();
		        	 netName=$("#netId option:selected").text();
		        	 gran = $("#gran option:selected").val();
					 startTime = $("#startTime").val();
					 endTime = $("#endTime").val();
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   sortName:this.sortName,
                       sortOrder:this.sortOrder,
		        	   netId: netId,
		        	   netName:netName,
		        	   gran:gran,
				       startTime:startTime,
				       endTime:endTime
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	//列表请求成功后加载图表
		        	$("#chart_div").children().remove();
		        	queryChart();
		        	$("#chart_outside_div ul li").eq(1).html("<b>按"+$('#gran option:selected').text()+"统计(%)</b>");
		        	$("#chart_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        	$("#table_outside_div ul li").eq(1).html("<b>按"+$('#gran option:selected').text()+"统计</b>");
		        	$("#table_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        },
		        onLoadError: function(){
		        	$("#chart_div").children().remove();
		        },
		        columns: [ 
		         {
		            field : 'recordtime',
		            title : '时间',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        },
		        {
		            field: 'netName',
                    title: '网元名称',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'freetotal',
		            title : '免费流量(KB)',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'total',
		            title : '总流量(KB)',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'percent',
		            title : '占比(%)',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'cheatCase',
		            title : '欺诈类型',
		            align : 'center',
		            valign : 'middle'
		        }]
		    });
		}
	</script>
</head>
<body>
	<form:form id="searchForm" name="searchForm" modelAttribute="tCheatFlowPerent" class="breadcrumb form-search" action="">
		<ul class="ul-form">
		    <li>
		        <label>网元</label>
		        <form:select path="netId" class="input-xlarge" style="width:180px;" id="netId">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li>
			   <label>粒度</label>
		       <form:select path="gran" class="input-xlarge" style="width:190px;" id="gran">
		             <form:option value="" label="请选择"/>
		             <form:option value="hh" label="半小时" />
					 <form:option value="dd" label="天" />
					 <form:option value="mm" label="月" />
			   </form:select>
			</li>
			<li><label>时间段</label>
		       <form:select id="pastScope" path="temp_field1" class="input-medium" style="width:160px;">
                   <form:option value="" label="请选择"/>           
 				   <form:options items="${fns:getDictList('biz_past_time_tac')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
               </form:select>
			</li>
			<li id="startTimeLi">	
				<label>开始时间</label>
				<form:input id="startTime" path="temp_field2" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li> 
			<li id="endTimeLi">
				<label>结束时间</label>
				<form:input id="endTime" path="temp_field3" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="queryBtn" class="btn-new btn-search" value="查询" type="submit"/></li>
			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/cheat/cheatFlowPerent/exportExcel';"/></li>
		</ul>
	</form:form>
	<div class="panel panel-default" id="chart_outside_div">
	   <div class="panel-heading" data-toggle="collapse" data-target="#chart_div">
	   <ul>
	      <li><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><%-- <img src='${ctxStatic}/images/p_down.png' height='10' width='10' style="float:left;margin-top:10px;"/> --%><span class="icon-h" class="panel-title"></span><p class="panel-title">图表模式</p></li>
	      <li style="float:left;margin-left:25%;line-height:38px"></li>
	      <li style="float:right;margin-right:0px;line-height:38px;"></li>
	   </ul>
	   </div>
	   <div class="panel-body" id="chart_panel">
	   		<div id="chart_div">
			</div>
	   </div>
	</div>
	<div class="panel panel-default" id="table_outside_div">
	   <div class="panel-heading" data-toggle="collapse" data-target="#table_div">
	   <ul>
	      <li><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><span class="icon-q" class="panel-title"></span><p class="panel-title">列表模式</p></li>
	      <li style="float:left;margin-left:25%;line-height:38px"></li>
	      <li style="float:right;margin-right:0px;line-height:38px;"></li>
	   </ul>
	   </div>
	   <div class="panel-body">
	   		<div id="table_div">
		   		<table id="table">
		        </table>
			</div>
	   </div>
	</div>
</body>
</html>