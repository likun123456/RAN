<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈用户评估表</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript">
	 $(document).ready(function(){
		  $('#pie1').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        credits: {
		             text: ''
		        },
		        title: {
		        	text: '当前用户IMSI('+${imsi}+')<br/>' +
		        	      '对应业务编码流量饼形图'
		        },
		        tooltip: {
		    	    pointFormat: ' <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		            	size:'140',
		            	innerSize:'65%',
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#BF0B23',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>百分比：{point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: 'ratingGroup',
		            data: [
		            	<c:forEach items="${list}" var="temp" varStatus="st1">
				            <c:if test="#st1.index!=0">
								,
							</c:if>
								['业务编码：${temp.ratingGroup}<br/>'+'业务名称：${temp.name} <br/>'+
								 '总流量：<fmt:formatNumber type="number" value="${temp.total}" pattern="0.00" maxFractionDigits="2"/> KB<br/>',${temp.total}]	
		            	</c:forEach>
		            ]
		        }]
		    });
		   $('#pie2').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        credits: {
		             text: ''
		        },
		        title: {
		        	text: '当前用户IMSI('+${imsi}+')<br/>' +
		        	      '对应免费流量占比饼状图'
		        },
		        tooltip: {
		    	    pointFormat: ' <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		            	size:'140',
		            	innerSize:'65%',
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#BF0B23',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>：{point.percentage:.1f} %'
		                }
		            }
		        },
		        series: [{
		            type: 'pie',
		            name: 'ratingGroup',
		            data: [
		            	['免费流量：${map.freetotal} KB <br/> 百分比',  ${map.freetotal}],
                       ['收费流量：<fmt:formatNumber type="number" value="${map.charge}" pattern="0.00" maxFractionDigits="2"/> KB <br/> 百分比',  ${map.charge}]
		            ]
		        }]
		    });
	  });
	</script>
</head>
<body>
	<div id="pie1" style="margin-bottom:50px;margin-top:50px;float:left;width:50%"></div>
    <div id="pie2" style="margin-bottom:50px;margin-top:50px;float:left;width:50%"></div>
</body>
</html>