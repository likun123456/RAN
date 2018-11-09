<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>性能指标综合查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#netType").change(function(){
				$("#_netType").val(888888);
				$("#netidepc").val($(this).find("option:selected").val());
				$("#formulaTypeepc").val($(this).find("option:selected").text());
		   	   });
			var _netType=$("#_netType").val();
			var _pastScope=$("#_pastScope").val();
			$("#netType").val(_netType);
			$(".select2-container .select2-chosen:eq(0)").html(_netType);
			$("#pastScope").val(_pastScope);
			$(".select2-container .select2-chosen:eq(1)").html("手动选择");
			$("#netidepc").val($(this).find("option:selected").val());
			$("#formulaTypeepc").val(_netType);
			var startTime = '${startTime}';
			var endTime = '${endTime}';
			$("#beginDate").val(startTime);
			$("#endDate").val(endTime);
			$("#timeScope").show();
			$("#queryTimeScope").html("下载时段："+$("#beginDate").val()+" 至 "+$("#endDate").val());
			queryChartData(_netType);
			$("#pastScope").change(function(){
				//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
				$("#timeScope").hide();
				if(this.value==""){
					$("#beginDate").val("");
					$("#endDate").val("");
					return false;
				}
				if(this.value=="0"){
					$("#beginDate").val("");
					$("#endDate").val("");
					$("#timeScope").show();
					return false;
				}
				$("#beginDate").val(getWholePointTime(-1 * (this.value/24)));
				$("#endDate").val(getWholePointTime(0));
				});

			
			$("#searchForm").validate({
				submitHandler: function(form){
					if ($("#netidId").val()==""){
	                	top.$.jBox.tip('请选择网元名称','warning');
					}else if ($("#formulaTypeId").val()==""){
	                    top.$.jBox.tip('请选择统计指标','warning');
	                }else if ($("#beginDate").val()==""){
	                    $("#beginDate").focus();
	                    top.$.jBox.tip('请选择时间范围','warning');
	                }else if ($("#endDate").val()==""){
	                	$("#endDate").focus();
	                	top.$.jBox.tip('请选择时间范围','warning');
	                }else if ($("#endDate").val() < $("#beginDate").val()){
	                	top.$.jBox.tip('时间范围输入数据不正确','warning');
	                }else{//表单提交
	        			$("#queryTimeScope").html("下载时段："+$("#beginDate").val()+" 至 "+$("#endDate").val());
	        			form.submit();
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
		function toThousands(num) {
			   if(num>1){
				   var num = (num || 0).toString(), result = '';
				    while (num.length > 3) {
				        result = ',' + num.slice(-3) + result;
				        num = num.slice(0, num.length - 3);
				    }
				    if (num) { result = num + result; }
			   }else{
				   result = num;
			   }
		    return result;
		}
		function queryChartData(tempNetType){
			var type;
			$("#table_outside_div_chart").show();
			$("#btnSubmit").show();
			$("#table_outside_div_list").hide();
			$("#exportBtn").hide();
			$("#btnSubmitList").hide();
			//查询时段赋值
			$("#queryTimeScope").html("查询时段："+$("#beginDate").val()+" 至 "+$("#endDate").val());
			if($("#_netType").val()==888888){
				type=$("#netType").val();
			}else{
				type=$("#_netType").val();
			}
			$.post('${ctx}/performance/multipleIndex/findChart', {
		        "netid": $("#netidId").val(),
		        "netType":type,
		        "startTime":$("#beginDate").val(),
		        "endTime":$("#endDate").val(),
		        "formulaType":$("#formulaTypeId").val()
		    }, function(dataQuery) {
		        queryByComprehensive(dataQuery)
		    });
		}
		//综合显示
		function queryByComprehensive(dataQuery){
			//预先清空所有图表
	        if($("#chart_table").children().length!=0){
	        	$("#chart_table").children().remove();
	        }
	        //下面开始循环遍历，外层list为以公式区分的列表，按照公式个数遍历
	        var kpi_length = dataQuery.length;
	        var kpinames = $("#formulaTypeName").val().split(",");
	        var formula_ids= $("#formulaTypeId").val().split(",");
	        var netIds=$("#netidId").val().split(",");
	        var flag = "";
	        var countChart=0
			for(var k=0;k<kpi_length;k++){
	            (parseInt(dataQuery[k][0][0].countertype)==0)?(flag = "%"):(flag = "");	
	            	//将数据拼装成highcharts需要的格式
					var chartlist = dataQuery[k];
		            //动态加载图表div
					//var html = "<div class='panel panel-default' style='height: 350px; width:100%; margin: 0 auto'></div>";
					var html="<div class='panel panel-default' id='table_outside_div'>"+
							  	"<div class='panel-heading' data-toggle='collapse' data-target='#table_div_"+k+"'>"+
							  		"<ul>"+
							  			//"<li><img src='${ctxStatic}/images/p_down.png' height='5' width='5' style='float:left;margin-top:10px;'/><span class='icon-h' class='panel-title'></span><p class='panel-title'>图表模式</p></li>"+
							  			"<li><span class='icon-down' style='font-size: 12px;float:left;margin-top:10px;'></span><span class='icon-h' class='panel-title'></span><p class='panel-title'>图表模式</p></li>"+
							  			"<li style='float:left;margin-left:20%;line-height:38px;' id='li_"+k+"'></li>"+
							  			"<li style='float:right;margin-right:40px;line-height:38px;'><b>查询时段："+$("#beginDate").val()+" 至  "+$("#endDate").val()+"</b></li>"+
							  		"</ul>"+
							  	"</div>"+
							  	"<div class='panel-body'>"+
							  		"<div id='table_div_"+k+"'>"+
							  		"</div>"+
							  	"</div>"+
							  "</div>";
					$("#chart_table").append(html);
					var $chart = $("#chart_table").find("#table_div_"+k);
					countChart++;
		            //加载图表 
					$chart.highcharts({
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
				        		  return this.value;
								}, 
			                    rotation: 0
			                },
		                    tickmarkPlacement: 'on'
				        }, 
				        yAxis: {
				            title: {
				                text: '统计值 '+flag
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
				                cursor: 'pointer',  
				                events: {  
				                    click: function(e) {  
				                    	    var pn = e.point.series.name;
				                    	    if(pn.indexOf('MME') > -1 ) {
						                    	var formula_name = "X:"+'${formulaName}';
						                    	var net = pn.split(":")[1].split("_")[0];
						                    	var startTime = e.point.category+":00";
						                        openNewWindow('${ctx}/performance/tEbmlogStatistics/redirectTEbmlogStatistics?formulaName='+encodeURIComponent(formula_name)+'&net='+net+'&startTime='+startTime,1200,800);
				                    	    }
				                    }  
				                }  
				            }
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
					for(var i=0;i<chartlist.length;i++){
					var dateTimeList = new Array();
					var result = new Array();
					var dresult = new Array();
					var wresult = new Array();
					var awresult = new Array();
					var names = new Array();
							dateTimeList[i] = new Array();
							result[i] = new Array();
							dresult[i]=new Array();
							wresult[i]=new Array();
							awresult[i]=new Array();
							names[i] = new Array();
							var clength = chartlist[i].length;
								for(var j=0;j<clength;j++){
									dateTimeList[i][j] =chartlist[i][j].temp_field1.substring(11,16);
									result[i][j] = chartlist[i][j].result;
									dresult[i][j]=chartlist[i][j].dresult;
									wresult[i][j]=chartlist[i][j].wresult;
									awresult[i][j]=chartlist[i][j].awresult;
									names[i][j] = chartlist[i][j].fname;
								}
							if(clength!=0){
								seriesData.push({name:'网元:'+names[i][0]+"_当天值",data:result[i]});
								if(netIds.length==1){
									seriesData.push({name:'网元:'+names[i][0]+"_前一天值",data:dresult[i]});
								}
								if(netIds.length==1){
									seriesData.push({name:'网元:'+names[i][0]+"_前一周值",data:wresult[i]});
								}
								if(netIds.length==1){
									seriesData.push({name:'网元:'+names[i][0]+"_前一周平均值",data:awresult[i]});
								}
							}
							$chart.highcharts().xAxis[0].setCategories(dateTimeList[i]);
							var temp = chartlist[i][0].kpi;
			                //根据temp返回的当前KPI的id值，匹配相应的KPI名称，动态给highcharts赋值title
			                for(var t=0;t<formula_ids.length;t++){
			                	if(formula_ids[t]==temp){
			                		$chart.highcharts().setTitle({text: ''});
			                		$("#chart_table").find("#li_"+k).html("<b>统计指标信息—"+ kpinames[t]+"</b>");
			                	}
			                }
					}
					
					var diff = $chart.highcharts().series.length - seriesData.length;  
				    if(diff > 0){  
				        for (var i = $chart.highcharts().series.length; i > diff; i--){  
				        	$chart.highcharts().series[i-1].remove(true);  
				      }  
				    } else if (diff < 0){  
				        for (var i = $chart.highcharts().series.length; i < seriesData.length; i ++){  
				        	$chart.highcharts().addSeries({});  
				      }  
				    }  
				              
				    $chart.highcharts().update({  
				        series:seriesData  
				    }); 
			}
			/**折叠组件三角图标切换**/
			triangleIconChange();
		}
		function queryListData(){
			$("#table_outside_div_chart").hide();
			$("#btnSubmit").hide();
			$("#table_outside_div_list").show();
			$("#exportBtn").show();
			$("#btnSubmitList").show();
			//查询时段赋值
			$("#queryTimeScope").html("查询时段："+$("#beginDate").val()+" 至 "+$("#endDate").val());
			var netidTemp=$("#netidId").val();
			var netType;
			if($("#_netType").val()==888888){
				netType=$("#netType").val();
			}else{
				netType=$("#_netType").val();
			}
			var formulaType=$("#formulaTypeId").val();
			var startTime=$("#beginDate").val();
			var endTime=$("#endDate").val();
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/performance/multipleIndex/findList',
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
		        	   sortName:this.sortName,
                       sortOrder:this.sortOrder,
		        	   netType: netType,
		        	   netid: netidTemp,
		        	   formulaType:formulaType,
				       startTime:startTime,
				       endTime:endTime
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        },
		        onLoadError: function(){
		        },
		        columns: [
		        {
		            field: 'fname',
                    title: '网元名称',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'name',
		            title : 'KPI公式',
		            align : 'center',
		            valign : 'middle'
		        }, {
		        	field : 'temp_field1',
		            title : '时间',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'result',
		            title : '当天值',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'dresult',
		            title : '前一天值',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'wresult',
		            title : '前一周值',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'awresult',
		            title : '前一周平均值',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }]
		    });
		}
	</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
      <div class="navbar-inner">
      	<span class="icon-5" style="font-size: 45px; float:left;margin-left:10px;"></span>
        <a class="brand" style="font-size: 18px; margin-left: 20px;">性能指标综合查询</a>
        <div style="float:right" class="form-inline">
        	<span id="queryTimeScope" style="padding:15px;"></span>
        	<span class="icon-h"  style="font-size: 30px; margin-top: -5px;cursor:pointer;margin-right: 15px" onclick="queryChartData(0)"></span><p style="cursor:pointer;display:inline-block;font-size: 15px; margin-right: 10px;margin-top: 10px" onclick="queryChartData(0)">图表模式</p>
        	<span class="icon-q"  style="font-size: 30px; margin-top: -5px;cursor:pointer;margin-right: 15px" onclick="queryListData()"></span><p style="cursor:pointer;display:inline-block;font-size: 15px; margin-right: 10px;margin-top: 10px" onclick="queryListData()">列表模式</p>
        	<span style="float: right;line-height: 28px;margin-top: 10px;"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 15px;float: left;margin-top: -2px;">返回</span></a></span>
        </div>
      </div>
    <input type="hidden" id="_netType"  value="${netType}"/>
    <input type="hidden" id="_pastScope"  value="${pastScope}"/>
	<form:form id="searchForm" modelAttribute="tMultipleIndex" action="${ctx}/performance/multipleIndex/exportExcel" method="post" class="breadcrumb form-search" style="margin-top:5px;">
		<div style="margin-left:14px;">
			查询类型:
		       <form:select path="netType" class="input-xlarge" style="width:150px;" id="netType">
					<form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			
			网元名称:
                <sys:treeselect id="netid" name="netid" cssStyle="width:100px;" value="${netid}" labelName="netid" labelValue="${netNames}"
					title="网元名称" url="/performance/multipleIndex/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
			
			统计指标:
				<sys:treeselect id="formulaType" name="formulaType" cssStyle="width:100px;" value="${formulaType}" labelName="formulaType" labelValue="${formulaName}"
					title="统计指标" url="/performance/multipleIndex/treeDataFormula" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
			
			时间段:
		       <form:select id="pastScope" path="temp_field1" class="input-medium" cssStyle="width:138px;">
 				   <form:options items="${fns:getDictList('biz_past_time_scope')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
               </form:select>

			<div class="pull-right">
				<input id="btnSubmit" class="btn-new btn-search" type="button" onclick="queryChartData(0)" value="查询"/>
				<input id="btnSubmitList" class="btn-new btn-search" type="button" onclick="queryListData()" value="查询" style="display:none;"/>
				<input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" style="display:none;" />

			</div>
		</div>
		<div id="timeScope" class="form-inline hide" style="margin-left:14px;margin-top:5px;">
			开始时间:
			<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" style="width:138px;" class="input-mini Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:false,startDate:'%y-%M-%d 00:00:00',hmsMenuCfg:{ H: [1, 6], m: [15, 4] }});"/>
			结束时间:		
			<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" style="width:138px;" class="input-mini Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:false,startDate:'%y-%M-%d 00:00:00',hmsMenuCfg:{ H: [1, 6], m: [15, 4] }});"/>
		 </div>
	</form:form>	
    </div>
	<sys:message content="${message}"/>
	<div id="table_outside_div_chart" style="display:none;">
		   	<div id="chart_table" style='height: 400px; width: 100%; margin: 0 auto;margin-top:180px;'>
			</div>
	   </div>
	</div>
	<div id="table_outside_div_list" style="display:none;">
	   		<div id="table_div" style='height: 400px; width: 100%; margin: 0 auto;margin-top:180px;'>
		   		<table id="table" >
		        </table>
			</div>
	</div>
</body>
</html>