<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>异常原因多维分析</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.focus{
	    border-color: rgba(82, 168, 236, 0.8);
	    outline: 0;
	    outline: thin dotted \9;
	    /* IE6-9 */
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
		   -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
		        box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
	}
	</style>
	<script type="text/javascript">
	    var key;
		$(document).ready(function() {
			var parBody = $(parent.document.body);
		     parBody.find('#loadingDiv').remove();
			<c:forEach items="${pieMap}" var="temp" varStatus="st1">
				key = "${temp.key}";
			  $('#'+key+'_pie').highcharts({
			        chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: false
			        },
			        title: {
			        	text: "${fns:getDictLabel(temp.key,'wd',temp.key)} " +'统计饼形图 ',
			        	style:{
			        		fontSize:'14px'
			        	},
			        	floating: true,
			        	margin: 0
			        	
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
			                    color: 'gray',
			                    connectorColor: '#000000',
			                    useHTML: true,//, // 一定要加上  
			                    formatter : function() { 
			                    	$("div.highcharts-tooltip span").css("white-space", "inherit"); 
		                            return "<p style='width: 100px; display:inline-block; white-space:pre-wrap;'><b>"+this.point.name+"</b>: "+this.percentage.toFixed(2)+" %</p>"; // 重点在white-space:pre-wrap  
		                        }   
			                } 
			            }
			        },
			        series: [{
			            type: 'pie',
			            data: [
			            	 <c:forEach begin="0" end="10" items="${temp.value}" var="item" varStatus="st2">
					                    <c:if test="${st2.index!=0}">
											,
										</c:if>
										   ['${item.obj}',${item.failures}]
							 </c:forEach>
			            ]
			        }],
			        credits: {
	                    enabled: false
	                }
	                
			    });
			   </c:forEach>
			   
			   $("div[name='table']").mouseover(function(){
				   $(this).addClass("focus");
			   });
			   
			   $("div[name='table']").mouseleave(function(){
				   $(this).removeClass("focus");
			   });
			   
			   $("div[name='pie']").mouseover(function(){
				   $(this).addClass("focus");
			   });
			   
			   $("div[name='pie']").mouseleave(function(){
				   $(this).removeClass("focus");
			   });
			   
			   $("div[name='table']").click(function(){
				  var key = $(this).find("b").html().split(" ")[0];
				  if(key=="终端型号"){
					  key = "IMEITAC";
				  }else if(key=="IMSI号段"){
					  key = "IMSISERIES";
				  }else if(key=="基站"){
					  key = "ECI";
				  }
				  $("#"+key+"_table").css("display","none");
				  $("#"+key+"_pie").css("display","block"); 
			   });
			   
			   $("div[name='pie']").click(function(){
				   var title = $(this).highcharts().options.title.text;
				   var l = title.length;
				   var key = title.substring(0,l-7);
				   if(key=="终端型号"){
					  key = "IMEITAC";
				   }else if(key=="IMSI号段"){
					  key = "IMSISERIES";
				   }else if(key=="基站"){
					  key = "ECI"; 
				   }
				   $("#"+key+"_table").css("display","block");
				   $("#"+key+"_pie").css("display","none"); 
			   });
			   
		});
		
		
		function queryChartData(){
			$("div[name='pie']").css("display","block");
		    $("div[name='table']").css("display","none");
		    
		}
		
		function queryListData(){
			$("div[name='table']").css("display","block");
		    $("div[name='pie']").css("display","none");
		}
		
		//${ctx}/userquery/tEbmSingleSearch" 
		function openEbmSingleSearchWindow(value,key){
			var url;
			var isImsi = false;
			var endTime = $('#endTime', window.parent.document).val();
			var startTime = $('#startTime', window.parent.document).val();
			if(key=="IMSI"){
				isImsi = true;
				url = "${ctx}/userquery/tEbmSingleSearch?imsi="+value+"&isImsi="+isImsi+"&startTime="+startTime+"&endTime="+endTime;
			}else{
				url = "${ctx}/userquery/tEbmSingleSearch?msisdn="+value+"&isImsi="+isImsi+"&startTime="+startTime+"&endTime="+endTime;
			}
			openMaxWindow(url);
		}
		
		function exportTable(){
			window.location.href= "${ctx}/performance/tEbmlogStatistics/exportAnalysisExcel.do?&cc="+${cc}+"&scc="+${scc}
		}
	</script>
</head>
<body>
   
   <div style="margin:20px auto;width:95%;">
   	   <div style="margin-bottom:30px;">
		   <table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
					    <th>CC(原因值)</th>
					    <th>SCC(子原因值)</th>
					    <th>原因值描述</th>
					    <th>子原因值描述</th>
					    <th>问题定界</th>
					    <th>建议操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					    <td>${cc}</td>
					    <td>${scc}</td>
					    <td>${ccdes}</td>
					    <td>${sccdes}</td>
					    <td>${fns:getDictLabel(domain,'domain',domain)}</td>
					    <td>${action}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="panel panel-default" style="float:left;">
		    <div class="panel-heading" data-toggle="collapse" data-target="#chart_div">
			    <ul style="list-style:none;height:28px;">
			      <li style="float:right;margin-right:100px;line-height:38px;">
			           <span class="icon-h"  style="font-size: 30px; margin-top: 7px;cursor:pointer;"onclick="queryChartData()"></span><p style="cursor:pointer;display:inline-block;font-size: 15px; margin-right: 5px;margin-top: 2px" onclick="queryChartData()">图表模式</p>
	        	       <span class="icon-q"  style="font-size: 30px; margin-top: 7px;cursor:pointer;"onclick="queryListData()"></span><p style="cursor:pointer;display:inline-block;font-size: 15px; margin-right: 5px;margin-top: 2px" onclick="queryListData()">列表模式</p>
	        	       <span class="icon-q"  style="font-size: 30px; margin-top: 7px;cursor:pointer;"onclick="exportTable()"></span><p style="cursor:pointer;display:inline-block;font-size: 15px; margin-right: 5px;margin-top: 2px" onclick="exportTable()">列表导出</p>
			      </li>
			    </ul>
		    </div>
		    <div class="panel-body" id="chart_panel" >
		   		<div style="margin:0 auto;">
				<c:forEach items="${pieMap}" var="map">
				    <div id="${map.key}_table" name="table" style="width:30%;float:left;margin:20px;margin-right:0;cursor:pointer;">
					<table name="table" class="table table-striped table-bordered table-condensed">
					    <caption align="top"><b>${fns:getDictLabel(map.key,'wd',map.key)}  维度分析</b></caption>
					    <colgroup>
				           <col style="width:auto;" />
				           <col style="width:170px;" />
				        </colgroup>
						<thead>
							<tr>
							    <th>${fns:getDictLabel(map.key,'biz_ebmlog_analysis',map.key)}</th>
							    <th>失败次数</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<td colspan="2" style="border:0;padding:0;">
								<div style="height:342px;overflow-y:scroll;">
									<table class="table table-striped table-bordered table-condensed">
									<colgroup>
							           <col style="width:auto;" />
				           			   <col style="width:152px;" />
							        </colgroup>
										<tbody>
										<c:forEach items="${map.value}" var="o">
										    <tr>
										        <c:choose>
											        <c:when test="${map.key=='IMSI'||map.key=='MSISDN'}">
											           <td style="padding:5;">
											               <c:choose>
											                   <c:when test="${o.obj!='undefined'}">
											                      <a href="javascript:void(0);"  onclick="openEbmSingleSearchWindow('${o.obj}','${map.key}')" style="cursor: pointer;">
												                     <c:out value="${o.obj}"></c:out>
												                  </a>
											                   </c:when>
											                   <c:otherwise>
											                         <c:out value="${o.obj}"></c:out>
											                   </c:otherwise>
											                </c:choose> 
											           </td>
											        </c:when>
											        <c:otherwise>
											           <td style="padding:5;"><c:out value="${o.obj}"></c:out></td>
											        </c:otherwise>
											    </c:choose>
											    <td style="padding:5;"><c:out value="${o.failures}"></c:out></td>
											</tr>
								        </c:forEach>
										</tbody>
									</table>
								</div>
							</td>
							</tr>
						</tbody>
					</table>
					</div>
					<div id='${map.key}_pie' name="pie" style="display:none;width:28%;float:left;margin:20px;margin-right:0;" >
				    </div>
				</c:forEach>
				</div>
		    </div>
	    </div>
	</div>
</body>
</html>