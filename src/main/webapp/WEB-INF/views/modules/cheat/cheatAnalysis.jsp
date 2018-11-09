<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计费欺诈防控分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		<%--设置全局变量--%>
		var netid;
		var freePrecentThreshold;
		var freeThreshold;
		var startTime;
		var endTime;
		var patrn = /^[0-9]*$/; 
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
				$("#endTime").val(getTacEndTime(this.value));
			});
			
			$("#searchForm").validate({
				submitHandler: function(form){
					netid = $("#netid option:selected").val();
					freePrecentThreshold = $("#freePrecentThreshold").val();
					freeThreshold = $("#freeThreshold").val();
					startTime = $("#startTime").val();
					endTime = $("#endTime").val();
					if (netid==""){
	                	top.$.jBox.tip('请选择查询网元','warning');
					}else if ($("#freePrecentThreshold").val()==""){
	                    top.$.jBox.tip('免费流量占比门限不能为空','warning');
					}else if(!patrn.test(freePrecentThreshold)&&
							(Number(freePrecentThreshold)<=100)&&
							(Number(freePrecentThreshold)>=0)){
			            top.$.jBox.tip('免费流量占比门限必须为0到100以内的数字','warning');
					}else if ($("#freeThreshold").val()==""){
	                    top.$.jBox.tip('免费流量绝对值不能为空','warning');
					}else if(!patrn.test(freePrecentThreshold)&&
							(Number(freePrecentThreshold)<=100)&&
							(Number(freePrecentThreshold)>=0)){
			            top.$.jBox.tip('免费流量绝对值必须为0到100以内的数字','warning');
	                }else if (startTime==""){
	                    $("#startTime").focus();
	                    top.$.jBox.tip('请选择开始时间','warning');
	                }else if (endTime==""){
	                	$("#endTime").focus();
	                	top.$.jBox.tip('请选择结束时间','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/cheat/cheatAnalysis/exportExcel"){
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
		
		function queryTable(){
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/cheat/cheatAnalysis/queryList',
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
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   netid: netid,
		        	   freePrecentThreshold:freePrecentThreshold,
		        	   freeThreshold:freeThreshold,
				       startTime:startTime,
				       endTime:endTime
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	//列表请求成功后加载图表
		        	$("#chart_div").children().remove();
		        	showChart(data.rows);
		        	$("#chart_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        	$("#table_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>");
		        },
		        onLoadError: function(){
		        	$("#chart_div").children().remove();
		        },
		        columns: [
		        {
		            field: 'imsi',
                    title: '用户IMSI',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'ratType',
		            title : 'RAT类型',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'freeTotal',
		            title : '免费流量(KB)',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value){
		            	return (value/1024).toFixed(2);
		            }
		        }, {
		            field : 'total',
		            title : '总流量(KB)',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value){
		            	return (value/1024).toFixed(2);
		            }
		        }, {
		            field : 'percent',
		            title : '免费流量占比',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value){
		            	return (value*100).toFixed(2)+"%";
		            }
		        }, {
		            field : '',
		            title : '操作',
		            align : 'center',
		            width : '100',
		            valign : 'middle',
		            formatter:function(value,row,index){
                           return "<a href='javascript:void(0);' style='cursor:hand' onclick=\"detailInfo('"+row.imsi+"')\">详细信息</a>";
		          	}
		        }]
		    });
		}
		
		function detailInfo(imsi){
			top.$.jBox.open('iframe:${ctx}/cheat/cheatAnalysis/showDetailInfo?netid='+netid+'&imsi='+imsi+'&startTime='+startTime+
					'&endTime='+endTime,'详细信息',$(top.document).width()-800,$(top.document).height()-700,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
		}
		
		function showChart(data){
			//定义图表需要的数据变量
			var x_servedIMSI = [];
			var y_total = [];
			var y_freetotal = [];
			var temptotal;
			var tempfreetotal;
			var chartList = eval(data);
			for(var i=0;i<chartList.length;i++){
				x_servedIMSI.push(chartList[i].imsi + '（' + chartList[i].ratType + "）");
				temptotal = (parseFloat(chartList[i].total)/1024);
				y_total.push(parseFloat(temptotal.toFixed(2)));
				tempfreetotal = (parseFloat(chartList[i].freeTotal)/1024);
				y_freetotal.push(parseFloat(tempfreetotal.toFixed(2)));
			}
			$("#chart_div").highcharts({
	        	title: {
		            text: '用户IMSI免费流量统计柱形图',
		            x: -20 
	            },
	            credits: {
		             text: ''
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
		                text: '流量（单位KB）'
		            },
		            plotLines: [{
		                value: 0,
		                width: 1,
		                color: '#808080'
		            }]
		        }],
		        tooltip: {
		            formatter : function() {
	       				var content = '<span>' + this.x + '</span><br>';
	       				for (var i = 0; i < 2; i++) {
				        	content += '<br>'+'<span style="color: ' + this.points[i].series.color + '">' + this.points[i].series.name + '</span>:<b> ' + 
				        	this.points[i].y + ' KB <b/><br>';
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
		        	           var imsi = e.point.category.split("（")[0];
		        	           var index = e.point.index;
		        	           var freeTotal = y_freetotal[index];
		        	           var charge = y_total[index]-freeTotal;
		        	           top.$.jBox.open('iframe:${ctx}/cheat/cheatAnalysis/showPieChart?netid='+netid+'&imsi='+imsi+'&startTime='+startTime+
		        						'&endTime='+endTime+"&freeTotal="+freeTotal+"&charge="+charge,'详细信息',$(top.document).width()-800,$(top.document).height()-300,{
		        					buttons:{"关闭":true},
		        					loaded:function(h){
		        						$(".jbox-content", top.document).css("overflow-y","hidden");
		        						$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
		        						$("body", h.find("iframe").contents()).css("margin","10px");
		        					}
		        				});
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
		        }]
	        });
		}
		
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="cheatAnalysis" action="" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		    <li>
		        <label>网元</label>
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
		        <label style="width:150px;">免费流量占比门限(%)</label>
		        <form:input id="freePrecentThreshold" path="" htmlEscape="false" maxlength="100" class="input-mini" value="50"/>
			</li>
			<li>
		        <label style="width:150px;">免费流量绝对值(KB)</label>
		        <form:input id="freeThreshold" path="" htmlEscape="false" maxlength="100" class="input-mini" value="1024"/>
			</li>
			<li><label>时间段</label>
		       <form:select id="pastScope" path="temp_field1" class="input-medium" style="width:160px;">
                   <form:option value="" label="请选择"/>           
 				   <form:options items="${fns:getDictList('biz_past_time_tac')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
               </form:select>
			</li>
			<li id="startTimeLi">	
				<label>开始时间</label>
				<form:input id="startTime" path="startTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li> 
			<li id="endTimeLi">
				<label>结束时间</label>
				<form:input id="endTime" path="endTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'HH:mm:00',isShowClear:true});"/>
			</li>
			
			<li class="btns"><input id="queryBtn" class="btn-new btn-search" value="查询" type="submit"/></li>
			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/cheat/cheatAnalysis/exportExcel';"/></li>
		</ul>
	</form:form>
	<div class="panel panel-default" id="chart_outside_div">
	   <div class="panel-heading" data-toggle="collapse" data-target="#chart_div">
	   <ul style="list-style:none;height:28px;">
	      <li style="float:left;line-height:38px"><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><%-- <img src='${ctxStatic}/images/p_down.png' height='10' width='10' style="float:left;margin-top:15px;"/> --%><span class="icon-h" class="panel-title" style="font-size: 30px; float:left; margin-top: 7px;margin-left:10px;"></span><p class="panel-title" style="font-size: 14px; float:left; margin-left: 15px;">图表模式</p></li>
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
	   <ul style="list-style:none;height:28px;">
	      <li style="float:left;line-height:38px"><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><%-- <img src='${ctxStatic}/images/p_down.png' height='10' width='10' style="float:left;margin-top:15px;"/> --%><span class="icon-q" class="panel-title" style="font-size: 30px; float:left; margin-top: 7px;margin-left:10px;"></span><p class="panel-title" style="font-size: 14px; float:left; margin-left: 15px;">列表模式</p></li>
	      <li style="float:left;margin-left:25%;line-height:38px"></li>
	      <li style="float:right;margin-right:0px;line-height:38px"></li>
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