<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
window.onload=setInterval(function() {
	queryChart();
 	}, 300000);
	function queryChart(){
		document.forms[0].action = "${ctx}/performance/index/mainChart";
		document.forms[0].submit();
	}
	function mainIndex(){
		document.forms[0].action = "${ctx}/performance/index/mainIndex";
		document.forms[0].submit();
	}
</script>
</head>
<body>
<form:form id="mainIndex" method="post">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-c" style="font-size: 45px; float:left;margin-left:10px;"></span>
			<a class="brand" style="font-size: 18px; margin-left: 20px;">全网指标</a>
			<div style="float: right" class="form-inline">
				<a class="brand" style="font-size: 15px;">粒度:池组</a>
				<span id="queryTimeScope" style="padding: 15px;"></span> <span
					class="icon-5"
					style="font-size: 30px; margin-top: -5px; cursor: pointer; margin-right: 15px"
					onclick="mainIndex()"></span>
				<p
					style="cursor: pointer; display: inline-block; font-size: 15px; margin-right: 10px; margin-top: 10px"
					onclick="mainIndex()">表盘模式</p>
				<span class="icon-h"
					style="font-size: 30px; margin-top: -5px; cursor: pointer; margin-right: 15px"
					onclick="queryChart()"></span>
				<p
					style="cursor: pointer; display: inline-block; font-size: 15px; margin-right: 10px; margin-top: 10px"
					onclick="queryChart()">图表模式</p>
			</div>
			<div style="width: 100%;height:400px;overflow-y:scroll;">
			<table style="width: 100%;height:100%">
				<tr>
					<c:forEach items="${mainChartData}" var="entry" varStatus="status">
						<c:if test="${status.index%2 == 0 and status.index!=0}">
							<tr>
							</tr>
						</c:if>
						<td style="width: 50%;">
							<table style="width: 99%; height: 135px">
								<tr style="background: #F5F5F5">
									<td class="navbar-inner-main" style="background-color: #eeeeee"><span style="float: left;"><a
											style="font-size: 12px;" href="${ctx}/performance/index/netelementChart?poolName=${entry.key}&datetime=${datetime}">&nbsp;&nbsp;${entry.key}池组指标</a></span> <span
										style="float: right;"><a
											style="font-size: 12px; margin-right: 10px">统计时段：${timeInterval}</a></span>
									</td>
								</tr>
								<tr style="background: white">
									<td>
										<div id="${entry.key}" style="float:left;width:500px;margin-left:3px;text-align:center">
										
										</div>
										<script type="text/javascript">
										    var $chart = $("#${entry.key}");
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
										            }
										        },
										        credits: {
								                    enabled: false
								                },
										         legend: {
										            layout: 'vertical',
										            backgroundColor: '#FFFFFF',
										            align: 'top',
										            verticalAlign: 'top',
										            floating: true,
										            x: 90,
										            y: -10,
										            enabled:false
										        }
									        });
											<c:forEach items="${entry.value}" var="comment">
												var kpi=${comment.key};
												var dateTimeList = new Array();
												var result = new Array();
												<c:forEach items="${comment.value}" var="common">
													kpi='${common.kpiName}';
													result.push(${common.result});
													dateTimeList.push('${common.dateTime}');
												</c:forEach>
												if(result.length>0){
												$chart.highcharts().addSeries({name:'指标:'+kpi,data:result});
												$chart.highcharts().xAxis[0].setCategories(dateTimeList);
												}
											</c:forEach>
										</script>
									</td>
								</tr>
							</table>
						</td>
					</c:forEach>
				</tr>
			</table>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>