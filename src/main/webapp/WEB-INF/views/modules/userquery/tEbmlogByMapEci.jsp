<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>异常原因统计</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    var netid;
	    var eventType;
	    var startTime;
	    var endTime;
	    var eci;
	    var key;
		$(document).ready(function() {
			//$("#btnMenu").css("display","none"); 
			var parBody = $(parent.document.body);
		    parBody.find('#loadingDiv').remove();
			<c:forEach items="${pieMap}" var="temp" varStatus="st1">
				key = "${temp.key}";
			  $('#'+key+'_div').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			        	text: key +'网元统计饼形图 ',
			        	style:{
			        		fontSize:'14px'
			        	},
			        	floating: true,
			        	margin: 0
			        },
			        tooltip: {
			        	formatter: function() {
			        		return this.point.name.split("|")[0]+"<br/>"+
		                 	       this.point.name.split("|")[1]+"<br/>"+
		                 	       this.point.name.split("|")[2]+"<br/>"
		                 		   +' 所占比例:<b>'+this.point.percentage.toFixed(2)+'%</b>';
			        	} 
			        },
			        plotOptions: {
			        	pie: {
	                        size:200,
	                        innerSize:'150',
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    color: 'gray',
			                    connectorColor: '#000000',
			                    formatter:function(){
			                    	return this.point.name.split("|")[0]+"<br/>"+
			                    	       this.point.name.split("|")[1]+"<br/>"+
			                    	       this.point.name.split("|")[2]+"<br/>"
			                    		   +' 所占比例:<b>'+this.point.percentage.toFixed(2)+'%</b>';
			                    }
			                }
			            }
			        },
			        series: [{
		                type: 'pie',
		                data: [
		                	<c:forEach items="${temp.value}" var="item" varStatus="st2">
		                    <c:if test="${st2.index!=0}">
								,
							</c:if>
							   ["CC:" + '${item.causecode}' + "|SCC:" + '${item.subcausecode}' + "|"+'出现次数:${item.failures}'+"次",${item.failures}]
							</c:forEach>
		                ],
		                name: '所占比例',
		            }],
			        credits: {
	                    enabled: false
	                }
			    });
			   </c:forEach>
		});
	</script>
</head>
<body>
    <div style="margin:20px auto;width:95%;">
   	   <div style="margin-bottom:30px;">
		   <table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
					    <th>ECI</th>
					    <th>所属池组</th>
					    <th>事件类型</th>
					    <th>厂家</th>
					    <th>站点</th>
					    <th>enodebId</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					    <td>${eci}</td>
					    <td>${poolName}</td>
					    <td>${eventType}</td>
					    <td>${factory}</td>
					    <td>${stationNo}</td>
					    <td>${enodebId}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="panel panel-default" style="float:left;width:100%;">
		    <div class="panel-body" id="chart_panel" >
		   		<div>
				<c:forEach items="${pieMap}" var="map">
					<div id='${map.key}_div' name="pie" style="width:48%;float:left;margin:20px;margin-right:0;" >
				    </div>
				</c:forEach>
				</div>
		    </div>
	    </div>
	</div>
</body>
</html>