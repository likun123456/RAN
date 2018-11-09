<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ECI维度指标查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		<%--设置全局变量--%>
		var netid;
		var eventType;
		var startTime;
		var endTime;
		$(document).ready(function() {
			$("#startTimeLi").hide();
			$("#endTimeLi").hide();
			//ebmlog事件类型随网元id联动
			/* $("#netid").change(function(){
		   		netid = $(this).find("option:selected").val();
		   		$.post('${ctx}/performance/tEciSuccessRate/queryEbmEvent', {
			       "netid": netid
			    }, function(data) {
				    	var ebmEventList = eval(data);
				    	$("#eventType option:not(:first)").remove();  
				    	$(".select2-chosen").eq(1).html("请选择");
				    	$("#eventType").html("<option value=''>请选择</option>")
				    	$.each(ebmEventList, function(index, o) { 
				    		var html = "<option value='"+o.eventname+"'>"+o.eventfullname+"</option>";
						    $("#eventType").append(html);
						}); 
				    	$("#eventType option:first").prop("selected", 'selected'); 
				    });
		   	}); */
			 
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
				$("#endTime").val(getTacEndTime(this.value));
			});
			
			$("#searchForm").validate({
				submitHandler: function(form){
					if ($("#netid option:selected").val()==""){
	                	top.$.jBox.tip('请选择查询网元','warning');
					}else if ($("#eventType option:selected").val()==""){
	                    top.$.jBox.tip('请选择事件类型','warning');
	                }else if ($("#startTime").val()==""){
	                    $("#startTime").focus();
	                    top.$.jBox.tip('请选择开始时间','warning');
	                }else if ($("#endTime").val()==""){
	                	$("#endTime").focus();
	                	top.$.jBox.tip('请选择结束时间','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/performance/tEciSuccessRate/exportExcel"){
	                		form.submit();
	                		$("#searchForm").attr('action',"");
	                	}else{
	                		queryTable()
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
			    chart: {
		            type: 'column',
		            width:width
		        },
		        xAxis: {
		        	type:'categories',
                    labels: {
		            	formatter: function() {
							var labelVal = this.value;
							var reallyVal = labelVal;
							if(labelVal.length > 15 && labelVal.length<30){
								reallyVal = labelVal.substr(0,(labelVal.length/2)) +'<br/>'+labelVal.substring((labelVal.length/2),labelVal.length-1);
							}else if(labelVal.length>30 && labelVal.length<45){
								reallyVal = labelVal.substr(0,15)+'<br/>'+labelVal.substring(15,30)+'<br/>'+labelVal.substring(30,labelVal.length-1);
							}else if(labelVal.length>45){
								reallyVal = labelVal.substr(0,15)+'<br/>'+labelVal.substring(15,30)+'<br/>'+labelVal.substring(30,45)+'<br/>'+labelVal.substring(45,labelVal.length-1);
							}
							return reallyVal;
						}, 
                        rotation: 0,
                        align: 'center',
                    },
                    max:9
		        }, 
		        scrollbar: {
		            enabled: true
		        },
		        yAxis: {
		            title: {
		                text: '成功率(%)'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        },
		        plotOptions: {
		            column: {
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: 'gray',
		                    style: {
		                        fontWeight: 'bold'
		                    },
		                    formatter: function() {
		                        return this.y +'%';
		                    }
		                },
		                events: {
				            click: function(e) {
				            	 var eci = e.point.category;
								 $("#eci").val(eci);
								 openNewWindow('${ctx}/performance/tEciSuccessRate/showEbmPieListWindow',1200,800);
						    }
						}
		            }
		        },
		        tooltip: {
		            formatter: function() {
		            		valueT=this.point.y;
		            		return '<b>'+ this.series.name +'</b>'+':'+' 当前值:'+ this.y+'%';
		            	}
		        },
		        credits: {
                    enabled: false
                },
                title: {
		        	text:""
		        }
	        });
			var chartList = eval(data);
		    var len = chartList.length;
		    var success_rate = new Array();
		    var eci = new Array();
		    for(var i=0;i<len;i++){
		    	success_rate[i] = Number(parseFloat(chartList[i].successRate).toFixed(2));
		    	eci[i] = chartList[i].eci;
		    }
		    $("#chart_div").highcharts().addSeries({name:"ECI",data:success_rate});
		    $("#chart_div").highcharts().xAxis[0].setCategories(eci);
		}
		
		function queryTable(){
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/performance/tEciSuccessRate/queryList',
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
		        	 netid = $("#netid option:selected").val();
					 eventType = $("#eventType option:selected").val();
					 startTime = $("#startTime").val();
					 endTime = $("#endTime").val();
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   sortName:this.sortName,
                       sortOrder:this.sortOrder,
		        	   netid: netid,
				       eventType:eventType,
				       startTime:startTime,
				       endTime:endTime
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	//列表请求成功后加载图表
		        	$("#chart_div").children().remove();
		        	showChart(data.rows);
		        	$("#chart_outside_div ul li").eq(1).html("<b>"+$('#eventType option:selected').text()+"成功率统计(ECI维度)</b>");
		        	$("#chart_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        	$("#table_outside_div ul li").eq(1).html("<b>"+$('#eventType option:selected').text()+"成功率统计(ECI维度)</b>");
		        	$("#table_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        },
		        onLoadError: function(){
		        	$("#chart_div").children().remove();
		        },
		        columns: [
		        {
		            field: 'fname',
                    title: '网元名称',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'factory',
		            title : '厂家',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        },   {
		            field : 'stationName',
		            title : '站名',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        },  {
		            field : 'stationNo',
		            title : '站号',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        },  {
		            field : 'enodebId',
		            title : 'enodebid',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        },  {
		            field : 'eci',
		            title : 'ECI',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'failureCount',
		            title : '失败数',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'totalCount',
		            title : '总数',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'successRate',
		            title : '成功率',
		            align : 'center',
		            valign : 'middle',
		            sortable : true,
		            formatter:function(value,row,index){
		            	return parseFloat(value).toFixed(2)+"%";
		            }
		        }]
		    });
		}
	</script>
</head>
<body>
    <input type="hidden" value="" id="eci"/>
	<form:form id="searchForm" modelAttribute="tEciSuccessRate" class="breadcrumb form-search" action="">
		<ul class="ul-form">
		    <li>
		        <label>查询网元</label>
		        <form:select path="netid" class="input-xlarge" style="width:180px;" id="netid">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li>
			   <label>事件类型</label>
		       <form:select path="eventType" class="input-xlarge" style="width:190px;" id="eventType">
		             <form:option value="" label="请选择"/>
		             <form:options items="${ebmEventlist}" itemLabel="eventfullname" itemValue="eventname" htmlEscape="false"/>
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
				<form:input id="startTime" path="temp_field2" type="text" readonly="readonly" maxlength="20" class="txt  Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li> 
			<li id="endTimeLi">
				<label>结束时间</label>
				<form:input id="endTime" path="temp_field3" type="text" readonly="readonly" maxlength="20" class="txt  Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'HH:mm:00',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="queryBtn" class="btn-new btn-search" value="查询" type="submit"/></li>
			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/performance/tEciSuccessRate/exportExcel';"/></li>
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