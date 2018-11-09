<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>地图模式</title>
	<meta name="decorator" content="default"/>
	  <link rel="stylesheet" href="https://unpkg.com/leaflet@1.3.1/dist/leaflet.css"
   integrity="sha512-Rksm5RenBEKSKFjgI3a41vrjkw4EVPlJ3+OiI65vTjIdo9brlAacEuKOiQ5OFh7cOI1bkDwLqdLw3Zg0cRJAAQ=="
   crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js"
   integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw=="
   crossorigin=""></script>
	<%-- <script src="${ctxStatic}/leaflet/heatmap.js" type="text/javascript"></script>
	<script src="${ctxStatic}/leaflet/leaflet-heatmap.js" type="text/javascript"></script> --%>
	<script type="text/javascript">	
	    var map;
	    var g = {};
		$(document).ready(function() {
			
			changeTableHeight();
			window.onresize=function(){
				changeTableHeight();
			}
			//$("#startTimeLi").hide();
			//$("#endTimeLi").hide();
			/* $("#pastScope").change(function(){
				//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
				$("#startTimeLi").hide();
				$("#endTimeLi").hide();
				if(this.value==""){
					$("#startTime").text("2017-08-25 09:45:00");
					$("#endTime").text("2017-08-25 10:45:00");
					$("#startTime").val("2017-08-25 09:45:00");
					$("#endTime").val("2017-08-25 10:45:00");
					return false;
				}
				if(this.value=="0"){
					$("#startTime").val("");
					$("#endTime").val("");
					$("#startTimeLi").show();
					$("#endTimeLi").show();
					return false;
				}
				$("#startTime").val(getTacStartTime(this.value));
				$("#endTime").val(getTacEndTime(this.value));
			}); */
			
			map = L.map('map').setView([36.07,120.38], 14);
			/* var southWest = L.latLng(31.95, 106.89),
			northEast = L.latLng(40.98, 126.56),
			bounds = L.latLngBounds(southWest, northEast); */
	        //map.setMaxBounds(map.getBounds());
			
			 
			 L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
				attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
				minZoom: 9,
				maxZoom: 16,
				id: 'mapbox.streets',
				accessToken: 'pk.eyJ1IjoiY2hlbmhvbmdibyIsImEiOiJjamh6eXFzODYxMmc2M3dwYmxic2F5MXYyIn0.RmcMhj5KaD_ia8A2CRNh6A'
			}).addTo(map);
			// L.tileLayer('http://{s}.openstreetmap.org/{z}/{x}/{y}.png',{/* maxBounds: bounds, */minZoom: 9,maxZoom: 16}).addTo(map);	        //setHeatMap();



	        //http://localhost:8888/tiles1/{z}/{x}/{y}.png
			 //map.zoomControl.setPosition('bottomright');
	    	map.attributionControl.setPrefix('');
         	//drawCircle(map);
         	map.on("zoomend",function(){
         		map.removeLayer(g);
         		drawCircle();
         	});
            map.on("dragend",function(){
         		map.removeLayer(g);
         		drawCircle();
            });
            
            
        
            map.removeLayer(g);
        	drawCircle();
		})
		
		function search() {
			if ($("#eventType option:selected").val()==""){
                top.$.jBox.tip('请选择事件类型','warning');
            /* }else if ($("#startTime").val()==""){
                $("#startTime").focus();
                top.$.jBox.tip('请选择开始时间','warning');
            }else if ($("#endTime").val()==""){
            	$("#endTime").focus();
            	top.$.jBox.tip('请选择结束时间','warning'); */
            }else if ($("#pool option:selected").val()==""){
            	top.$.jBox.tip('请选择池组','warning');
            }else{//表单提交
            	map.removeLayer(g);
            	drawCircle();
            }
		}
		
		function drawCircle() {
			 var group=new L.layerGroup();
	         var circleRadius=_getRadius(map);
	         var obj=map.getBounds();
	         var maxLongitude=obj._northEast.lng;
	     	 var minLongitude=obj._southWest.lng;
	     	 var maxLatitude=obj._northEast.lat;
	     	 var minLatitude=obj._southWest.lat;
	     	 var pool = $("#pool option:selected").val();
	     	 var eventType = $("#eventType option:selected").val();
			 /* var startTime = $("#startTime").val();
			 var endTime = $("#endTime").val(); */
	         $.ajax({
					type : "POST",
					url : "${ctx}/userquery/MapManager/query",
					dataType : "html",
					data : {'minLongitude':minLongitude,'maxLongitude':maxLongitude,'minLatitude':minLatitude,'maxLatitude':maxLatitude,'pool':pool,'eventType':eventType/* 'startTime':startTime,'endTime':endTime */},
					success:function(o){
						var _obj = JSON.parse(o);
						data = _obj.list;
						for(var i=0; i<data.length; i++) {
							var obj = data[i];
							var successRate = obj.successRate;
							var color = _getColor(successRate);
							var latitude = obj.latitude;
							var longitude = obj.longitude
							L.circleMarker([latitude,longitude],{
								color: color,
				          	fillColor: color,
				          	opacity:1,
				          	fillOpacity: 1,
				          	radius:circleRadius,
				          	eci:obj.eci,
				          	factory:obj.factory,
				          	stationNo:obj.stationNo,
				          	stationName:obj.stationName,
				          	enodebId:obj.enodebId,
				          	longitude:longitude,
							latitude:latitude,
							failureCount:obj.failureCount,
							totalCount:obj.totalCount,
							successRate:obj.successRate,
							pool:pool,
							eventType:eventType
							/* startTime:startTime,
							endTime:endTime */
				          }).on('click', function(e) { 
				        	  if(this.options.color == '#ff0000') {
				        		  showErrorDlg(this.options.pool,this.options.eventType,_obj.recentMoment,_obj.recentMoment,this.options.eci,
				        				  	this.options.factory,this.options.stationNo,this.options.stationName,this.options.enodebId);
				        	  } else {
					        	  showSuccessDlg(this.options.eci,this.options.factory,this.options.stationNo,this.options.stationName,this.options.enodebId,this.options.longitude,this.options.latitude,this.options.failureCount,this.options.totalCount,this.options.successRate);
				        	  }
						  }).addTo(group);
						}
					}
			   });
	         group.addTo(map);
	         g = group;
		}
		
		function showErrorDlg(pool,eventType,startTime,endTime,eci,factory,stationNo,stationName,enodebId) {
			var poolName = $("#pool option:selected").text();
			var eventFullType = $("#eventType option:selected").text();
			openNewWindow("${ctx}/userquery/MapManager/queryStatisticalPage?poolId="+pool+"&eventType="+eventType+"&startTime="+startTime+"&endTime="+endTime+
			"&eci="+eci+"&factory="+factory+"&stationNo="+stationNo+"&stationName="+stationName+"&enodebId="+enodebId+"&poolName="+poolName+"&eventFullType="+eventFullType,1200,800);
		}
		
		function showSuccessDlg(eci,factory,stationNo,stationName,enodebId,longitude,latitude,failureCount,totalCount,successRate) {
			var html = '<table class="table table-striped table-bordered table-condensed" >' + 
							'<tr>' +
								'<td> eci </td>' + 
								'<td> '+eci+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 厂家 </td>' + 
								'<td> '+factory+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 站点 </td>' + 
								'<td> '+stationNo+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 站名 </td>' + 
								'<td> '+stationName+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> enodebId </td>' + 
								'<td> '+enodebId+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 经度 </td>' + 
								'<td> '+longitude+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 纬度 </td>' + 
								'<td> '+latitude+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 失败数 </td>' + 
								'<td> '+failureCount+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 请求数 </td>' + 
								'<td> '+totalCount+' </td>' + 
							'</tr>' +
							'<tr>' +
								'<td> 成功率 </td>' + 
								'<td> '+successRate+' </td>' + 
							'</tr>' +
						'</table>';
			top.$.jBox.open(html, "详细信息",440,400,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function _getRadius(map){
			var radius = 0;			
			var lzoom=map.getZoom();
			if(lzoom == 12) {
				radius = 2;
			} else if(lzoom == 13) {
				radius = 3;
			} else if(lzoom == 14) {
				radius = 4;
			} else if(lzoom == 15) {
				radius = 5;
			} else if(lzoom == 16) {
				radius = 6;
			} else {
				radius = 1;
			}
			return radius;
			//return (lzoom>=13&&6)||(lzoom<=8&&1)||(lzoom-7);
			//return (lzoom-8)*2 + 1;
		}
		
		function _getColor(successRate){
			if(parseFloat(successRate) >= 80) {
				return "#00923e";
			} else {
				return "#ff0000";
			}
		}
		
		function queryChart(){
			document.forms[0].action = "${ctx}/performance/index/mainChart";
			document.forms[0].submit();
		}
		function mainIndex(){
			document.forms[0].action = "${ctx}/performance/index/mainIndex";
			document.forms[0].submit();
		}
		function queryMap(){
			document.forms[0].action = "${ctx}/userquery/MapManager";
			document.forms[0].submit();
		}
		
		function queryTopology(){
			document.forms[0].action = "${ctx}/performance/topologyManage/topologyManageIndex";
			document.forms[0].submit();
		}
		
		function changeTableHeight(){
		    var table= $("#map"); 
		    var height = document.documentElement.clientHeight;
		    /* if(height < 500){
		    	table.height(document.documentElement.clientHeight * 0.86);
		    }else if(height >=500){
		    	table.height(document.documentElement.clientHeight * 0.86);
		    } */
		    table.height(document.documentElement.clientHeight * 0.8);
		}
		
		/* function setHeatMap(){
			var testData = {
					  max: 8,
					  data: [{lat: 36.17, lng:120.30, count: 100}]
			};
			
			var cfg = {
					  // radius should be small ONLY if scaleRadius is true (or small radius is intended)
					  // if scaleRadius is false it will be the constant radius used in pixels
					  "radius": 0.05,
					  "maxOpacity": 0.8, 
					  // scales the radius based on map zoom
					  "scaleRadius": true, 
					  blur: 0.95, 
					  //系数越高，渐变越平滑，默认是0.85,
					  // if set to false the heatmap uses the global maximum for colorization
					  // if activated: uses the data maximum within the current map boundaries 
					  //   (there will always be a red spot with useLocalExtremas true)
					  "useLocalExtrema": true,
					  // which field name in your data represents the latitude - default "lat"
					  latField: 'lat',
					  // which field name in your data represents the longitude - default "lng"
					  lngField: 'lng',
					  // which field name in your data represents the data value - default "value"
					  valueField: 'count',
					//热力点的值
				        gradient: {
				            "0.99": "rgba(255,0,0,1)",
				            "0.9": "rgba(255,255,0,1)",
				            "0.8": "rgba(0,255,0,1)",
				            "0.5": "rgba(0,255,255,1)",
				            "0": "rgba(0,0,255,1)"
				        }
			};
			var heatmapLayer = new HeatmapOverlay(cfg);
			$(".leaflet-overlay-pane").empty();
			map.addLayer(heatmapLayer);
			heatmapLayer.setData(testData);
	    } */
		
	</script>
</head>
<body>
    <form:form id="mainIndex" method="post">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-c" style="font-size: 45px; float:left;margin-left:10px;"></span>
			<a class="brand" style="font-size: 18px; margin-left: 20px;">全网指标</a>
			<div style="float: right" class="form-inline">
				<a class="brand" style="font-size: 15px;">粒度:基站</a>
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
				<span class="icon-a8"
					style="font-size: 30px; margin-top: -5px; cursor: pointer; margin-right: 15px"
					onclick="queryMap()"></span>
				<p
					style="cursor: pointer; display: inline-block; font-size: 15px; margin-right: 10px; margin-top: 10px"
					onclick="queryMap()">地图模式</p>
				<span class="icon-a20"
					style="font-size: 30px; margin-top: -5px; cursor: pointer; margin-right: 15px"
					onclick="queryTopology()"></span>
				<p
					style="cursor: pointer; display: inline-block; font-size: 15px; margin-right: 10px; margin-top: 10px"
					onclick="queryTopology()">拓扑模式</p>
			</div>
		</div>
	</div>
	</form:form>
	<form:form id="searchForm" method="post" class="breadcrumb form-search" style="margin-top:50px;">
		<ul class="ul-form">
		    <li><label>请选择池组:</label>
		        <select class="required input-xlarge" style="width:200px;" id="pool">
		        	<c:forEach items="${poolList}" var="pool">
		        	    <c:choose>
				           <c:when test="${pool.fpoolname==poolName}">
				                <option value="${pool.id}"  selected>${pool.fpoolname}</option>
				           </c:when>
				           <c:otherwise>
				           		<c:if test="${pool.fpoolname=='MME-POOL1'}">
				               	 <option value="${pool.id}">${pool.fpoolname}</option>
				                </c:if>
				           </c:otherwise>
				        </c:choose> 
					</c:forEach>
				</select>
			</li>
			<li>
			   <label>事件类型</label>
		       <select class="input-xlarge" style="width:190px;" id="eventType">
		             <c:forEach items="${ebmEventlist}" var="event">
						<option value="${event.eventname}">${event.eventfullname}</option>
					 </c:forEach>
			   </select>
			</li>
			 <%--<li><label>时间段</label>
		       <select id="pastScope" class="input-medium" style="width:160px;">
                   <option value="">请选择</option>     
              		<c:forEach items="${fns:getDictList('biz_past_time_tac')}" var="scope">
						<option value="${scope.value}">${scope.label}</option>
				    </c:forEach>
               </select> 
			</li>--%>
			<!-- <li id="startTimeLi">	
				<label>开始时间</label>
				<input id="startTime" type="text" readonly="readonly" maxlength="20" class="txt  Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});" value="2017-08-25 09:45:00"/>
			</li> 
			<li id="endTimeLi">
				<label>结束时间</label>
				<input id="endTime" type="text" readonly="readonly" maxlength="20" class="txt  Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});" value="2017-08-25 10:45:00"/>
			</li> -->
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="button" value="查询" onclick="search()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="map" style="width: 100%"></div>
</body>
</html>