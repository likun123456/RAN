<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>异常原因统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    var o;
	    var netid;
	    var eventType;
	    var startTime;
	    var endTime;
	    var tac;
		$(document).ready(function() {
			/**折叠组件三角图标切换**/
			triangleIconChange();
			queryTable();
		});
		
		function queryTable(){
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/performance/tTacSuccessRate/queryEbmLogByTac',
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
		        	 o = window.opener.document.getElementById("netid");
					 netid = o.options[o.selectedIndex].value;
					 o = window.opener.document.getElementById("eventType");
					 eventType = o.options[o.selectedIndex].value;
					 startTime = window.opener.document.getElementById("startTime").value;
					 endTime = window.opener.document.getElementById("endTime").value;
					 tac = window.opener.document.getElementById("tac").value;
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   netid: netid,
				       eventType:eventType,
				       startTime:startTime,
				       endTime:endTime,
				       tac:tac
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	//列表请求成功后加载图表
		        	$("#chart_div").children().remove();
		        	showChart(data.rows);
		            $("#chart_outside_div ul li").eq(1).html("<b>"+$('#eventType option:selected').text()+"EBMLOG原因值统计</b>")
		        	$("#chart_outside_div ul li").eq(2).html("<b>TAC："+tac+"  统计时段："+startTime+"-"+endTime+"</b>")
		        	$("#table_outside_div ul li").eq(1).html("<b>"+$('#eventType option:selected').text()+"EBMLOG原因值统计</b>")
		        	$("#table_outside_div ul li").eq(2).html("<b>TAC："+tac+"  统计时段："+startTime+"-"+endTime+"</b>")
		        },
		        onLoadError: function(){
		        	$("#chart_div").children().remove();
		        },
		        columns: [
		        {
		            field: 'causecode',
                    title: 'CC(原因值)',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'cc',
		            title : '原因值描述',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'subcausecode',
		            title : 'SCC(子原因值)',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'scc',
		            title : '子原因值描述',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'failures',
		            title : '失败次数',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'domain',
		            title : '问题定界',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
                        var result = switchDomain(value);  
		          		return result;
		          	}
		        }]
		    });
		}
		
		function switchDomain(value){
			var result = value;
			if(value=="0"){
				result = "未知";
			}else if(value=="1"){
				result = "SGSM/MME 侧";
			}else if(value=="2"){
				result = "GGSN/SAEGW 侧";
			}else if(value=="3"){
				result = "HLR/HSS侧";
			}else if(value=="4"){
				result = "无线侧";
			}else if(value=="5"){
				result = "终端侧";
			}else if(value=="6"){
				result = "MSC侧";
			}else if(value=="7"){
				result = "DNS侧";
			}else if(value=="4,5"){
				result = "无线侧，终端侧";
			}else if(value=="1,7"){
				result = "SGSM/MME 侧，DNS侧";
			}else if(value=="1,3"){
				result = "SGSM/MME 侧， HLR/HSS侧";
			}
			return result;
		}
		
		function showChart(data){
			$('#chart_div').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		        	text: "" 
		        },
		        tooltip: {
		        	formatter: function() {
		        		var o = this.point.name.split("|")[0];
		        		var cc = o.split(" ")[0];
		        		var scc = o.split(" ")[1];
		        		return cc+"  ("+this.point.name.split("|")[1]+" "+scc+" ("+this.point.name.split("|")[2];
		        	} 
		        },
		        plotOptions: {
		            pie: {
                        size:300,
                        innerSize:'200',
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: 'gray',
		                    connectorColor: '#000000',
		                    formatter:function(){
		                    	return this.point.name.split("|")[0]+' 所占比例:<b>'+this.point.percentage.toFixed(2)+'%</b>';
		                    }
		                }
		            }
		        },
		        credits: {
                    enabled: false
                },
		        series: [{
	                type: 'pie',
	                name: '所占比例',
	            }]
		    });
			var chartList = eval(data);
	        var len = chartList.length;
	        var arr = new Array();
	        for(var i=0;i<len;i++){
	        	arr[i] = new Array();
	        	arr[i][0] = "CC:" + chartList[i].causecode.split("(")[0] + " SCC:" + chartList[i].subcausecode.split("(")[0] + 
	        			    "|" + chartList[i].causecode.split("(")[1] + "|" + chartList[i].subcausecode.split("(")[1] +
	        			    "|" + chartList[i].cc + "|" + chartList[i].scc + "|" + chartList[i].action + "|" + chartList[i].domain;
	        	arr[i][1] = chartList[i].failures;
	        }
	        $('#chart_div').highcharts().series[0].setData(arr);
		}
		
		
	</script>
</head>
<body>
    <ul class="nav nav-tabs">
		<li class="active" id="menu1" ><a>异常原因统计</a></li>
	</ul>
	<div id="con_menu_1" class="hover">
	
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
	</div>
</body>
</html>