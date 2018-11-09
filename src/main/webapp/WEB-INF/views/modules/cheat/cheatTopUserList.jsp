<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>TOP欺诈用户查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		<%--设置全局变量--%>
		var netId;
		var netName;
		var top;
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
					}else if ($("#top option:selected").val()==""){
	                    top.$.jBox.tip('请选择TOP','warning');
	                }else if ($("#startTime").val()==""){
	                    $("#startTime").focus();
	                    top.$.jBox.tip('请选择开始时间','warning');
	                }else if ($("#endTime").val()==""){
	                	$("#endTime").focus();
	                	top.$.jBox.tip('请选择结束时间','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/cheat/cheatTopUser/exportExcel"){
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
			
			//定义图表需要的数据变量
			var x_servedIMSI = [];
			var y_total = [];
			var y_freetotal = [];
			var y_percent = [];
			var olist = eval(data);
			for(var i=0;i<olist.length;i++){
				x_servedIMSI.push(olist[i].servedIMSI + '（' + olist[i].ratType + "）");
				y_total.push(parseFloat(olist[i].total));
				y_freetotal.push(parseFloat(olist[i].freetotal));
				y_percent.push(parseFloat(olist[i].percent));
			}
	        $("#chart_div").highcharts({
	        	title: {
		            text: 'TOP流量欺诈用户流量评估表',
		            x: -20 
	            },
		        xAxis: {
		        	title:{
		        	   text:'用户IMSI（RAT类型）',
		        	   style: {
	                      fontWeight: 'normal'
	                   }
		        	},
		        	categories:x_servedIMSI,
	                labels: {
		            	formatter: function() {
							var labelVal = this.value;
							var reallyVal = labelVal;
							if(labelVal.length > 15 && labelVal.length<30){
								reallyVal = labelVal.substr(0,(labelVal.length/2)) +'<br/>'+labelVal.substring((labelVal.length/2),labelVal.length);
							}else if(labelVal.length>30 && labelVal.length<45){
								reallyVal = labelVal.substr(0,15)+'<br/>'+labelVal.substring(15,30)+'<br/>'+labelVal.substring(30,labelVal.length);
							}else if(labelVal.length>45){
								reallyVal = labelVal.substr(0,15)+'<br/>'+labelVal.substring(15,30)+'<br/>'+labelVal.substring(30,45)+'<br/>'+labelVal.substring(45,labelVal.length);
							}
							return reallyVal;
						}, 
	                    rotation: 0,
	                    align: 'center', 
	                    y: 30
	                },
	                max:9
		        }, 
		        scrollbar: {
		            enabled: true
		        },
		        yAxis: [{
		        	labels:{
		        	    formatter: function() {
	                    return this.value +' KB';
	                },
	                style: {
	                    color: '#89A54E'
	                }
		        	
		        	},
		            title: {
		                text: '流量（单位kb）'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        }
		        ],
		        tooltip: {
		            formatter : function() {
	       				var content = '<span>' + this.x + '</span><br>';
	       				for (var i = 0; i < 2; i++) {
				        	content += '<br>'+'<span style="color: ' + this.points[i].series.color + '">' + this.points[i].series.name + '</span>:<b> ' + 
				        	this.points[i].y + ' kb <b/><br>';
			        	}
	       				var _percent = (this.points[0].y/this.points[1].y*100).toFixed(2);
	       				    content += '<br>'+'<span style="color: ' + '#4572A7' + '">' + '免费流量占比' + '</span>:<b> ' + 
				        	_percent + ' % <b/><br>';
			        	return content;
			        },
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
				    series: {
				        cursor: 'pointer',
				        events: {
				            click: function(e) {
				            }
				        },
				    }
				},
				series: [{
					type:'column',
		            name: '免费流量',
		            data:y_freetotal,
		            color:'#f7a35c'
		        }, {
		        	
		        	type:'column',
		            name: '总流量',
		            data:y_total,
		            color:'#8085e9'
		        }
		        ]
	        });
		}
		
		function queryTable(){
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/cheat/cheatTopUser/queryList',
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
					 top = $("#top option:selected").val();
					 startTime = $("#startTime").val();
					 endTime = $("#endTime").val();
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   sortName:this.sortName,
                       sortOrder:this.sortOrder,
		        	   netId: netId,
				       top:top,
				       startTime:startTime,
				       endTime:endTime,
				       netName:netName
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	//列表请求成功后加载图表
		        	$("#chart_div").children().remove();
		        	showChart(data.rows);
		        	$("#chart_outside_div ul li").eq(1).html("<b>欺诈用户</b>");
		        	$("#chart_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        	$("#table_outside_div ul li").eq(1).html("<b>欺诈用户</b>");
		        	$("#table_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        },
		        onLoadError: function(){
		        	$("#chart_div").children().remove();
		        },
		        columns: [
		        {
		            field: 'netName',
                    title: '网元名称',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'servedIMSI',
		            title : '用户IMSI',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'freetotal',
		            title : '免费流量',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'total',
		            title : '总流量',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'percent',
		            title : '免费流量占比',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }]
		    });
		}
	</script>
</head>
<body>
	<form:form id="searchForm" name="searchForm" modelAttribute="cheatTopUser" class="breadcrumb form-search" action="">
		<ul class="ul-form">
		    <li>
		        <label>查询网元</label>
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
			   <label>TOP</label>
		       <form:select path="top" class="input-xlarge" style="width:190px;" id="top">
		             <form:option value="" label="请选择"/>
		             <form:option value="10" label="10" />
					 <form:option value="20" label="20" />
					 <form:option value="50" label="50" />
					 <form:option value="100" label="100" />
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
			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/cheat/cheatTopUser/exportExcel';"/></li>
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
	      <li><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><%-- <img src='${ctxStatic}/images/p_down.png' height='10' width='10' style="float:left;margin-top:10px;"/> --%><span class="icon-q" class="panel-title"></span><p class="panel-title">列表模式</p></li>
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