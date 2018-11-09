<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网络拓扑图管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#dialogBg{width:100%;height:100%;background-color:#000000;opacity:.6;filter:alpha(opacity=60);position:fixed;top:0;left:0;z-index:9999;display:none;}
		#dialog{width:600px;height:350px;margin:0 auto;display:none;background-color:#ffffff;position:fixed;top:35%;left:40%;margin:-120px 0 0 -150px;z-index:10000;border:1px solid #ccc;border-radius:10px;-webkit-border-radius:10px;box-shadow:3px 2px 4px #ccc;-webkit-box-shadow:3px 2px 4px #ccc;}
		#dialog1{width:600px;height:210px;margin:0 auto;display:none;background-color:#ffffff;position:fixed;top:40%;left:40%;margin:-120px 0 0 -150px;z-index:10000;border:1px solid #ccc;border-radius:10px;-webkit-border-radius:10px;box-shadow:3px 2px 4px #ccc;-webkit-box-shadow:3px 2px 4px #ccc;}
		.dialogTop{width:95%;margin:0 auto;border-bottom:1px dotted #ccc;letter-spacing:1px;padding:5px 0;text-align:right;}
		.dialogContent{
			float:left;
			width:98%;
			height:300px;
			overflow:auto;
			margin:0 auto;
		}
		
		.dialogContent ul{
			float:left;
			width:90%;
			margin-top:0px;
			list-style: none;
		}
		
		.dialogContent ul li{
			float:left;
			width:69px;
			height:120px;
			margin-left:15px;
			margin-top:12px;
			cursor: pointer;
		}
		
		.addicon {
			background:url(${ctxStatic}/images/adddlg.png) no-repeat top center;
		}
		
		.mmeicon {
			background:url(${ctxStatic}/images/cyczdlg.png) no-repeat top center;
		}
		
		.yingjiicon {
			background:url(${ctxStatic}/images/yingjidlg.png) no-repeat top center;
		}
		
		.dialogContent ul li span{
			float: left;
			margin-top: 55px;
			font-size: 14px;
		}
		
	</style>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var canvas = document.getElementById("myCanvas");
			var canvas1 = document.getElementById("myCanvas1");
			var canvas2 = document.getElementById("myCanvas2");
			if (canvas.getContext){
				var ctx = canvas.getContext('2d');
				var yunimg = "${ctxStatic}/jerichotab/images/yun.png";
				var fuqimg = "${ctxStatic}/jerichotab/images/fuq.png";
				drawImage(yunimg,ctx,430, 10,120,80);  //画云图片
				drawImage(fuqimg,ctx,330, 105,35,55);   //左侧服务器
				drawFont(ctx,"QDAFW17","#44546a","normal normal bold 10px 微软雅黑",318,174); //左侧服务器名字
				drawImage(fuqimg,ctx,625, 105,35,55);   //右侧服务器
				drawFont(ctx,"QDAFW18","#44546a","normal normal bold 10px 微软雅黑",620,174); //右侧服务器名字
				drawLine(ctx,"#6b90c0",433,78,366,107); // 云链接左侧服务器线
				drawLine(ctx,"#6b90c0",547,78,625,109); // 云链接右侧服务器线
				/*
			     * font参数的值分为
			     * font-style: normal(正常), italic(斜体字), oblique(倾斜字体) 后两种在网页端一般没什么区别
			     * font-variant: normal(正常), small-caps(英文小写字母变成小的大写)
			     * font-weight: normal(正常), bold(加粗) 100-900(一般不用)
			     * font-size: 文字大小
			     * font-family: 字体样式
			     */
				drawFont(ctx,"CMNET","#44546a","normal normal bold 18px 微软雅黑",456,66); //云中文字
				drawLine(ctx,"#6b90c0",366,133,627,133); // 两个服务器相连线
				
				drawLine(ctx,"#6b90c0",331,133,75,202); // 左侧服务器链接左侧框
				drawLine(ctx,"#6b90c0",662,133,904,202); // 右侧服务器链接右侧框
				drawLine(ctx,"#6b90c0",366,133,415,205); // 左侧服务器链接中间框
				drawLine(ctx,"#6b90c0",627,133,588,205); // 右侧服务器链接中间框
				drawLine(ctx,"#6b90c0",366,133,763,205); // 左侧服务器链接右侧框
				drawLine(ctx,"#6b90c0",627,133,240,205); // 右侧服务器链接左侧框
				
				drawFont(ctx,"SGi","#44546a","normal normal bold 9px 微软雅黑",192,160);
				drawFont(ctx,"SGi","#44546a","normal normal bold 9px 微软雅黑",392,165);
				drawFont(ctx,"SGi","#44546a","normal normal bold 9px 微软雅黑",592,165);
				drawFont(ctx,"SGi","#44546a","normal normal bold 9px 微软雅黑",802,167);
			}
			if (canvas1.getContext){
				var ctx1 = canvas1.getContext('2d');
				drawLine(ctx1,"#2e75b6",90,0,90,80); 
				drawLine(ctx1,"#2e75b6",185,0,185,80);
				drawLine(ctx1,"#2e75b6",460,0,460,80); 
				drawLine(ctx1,"#2e75b6",545,0,545,80);
				drawLine(ctx1,"#2e75b6",800,0,800,80); 
				drawLine(ctx1,"#2e75b6",880,0,880,80);
				drawFont(ctx1,"S11","#44546a","normal normal bold 9px 微软雅黑",98,40); 
				drawFont(ctx1,"S11","#44546a","normal normal bold 9px 微软雅黑",192,40); 
				drawFont(ctx1,"S11","#44546a","normal normal bold 9px 微软雅黑",468,40); 
				drawFont(ctx1,"S11","#44546a","normal normal bold 9px 微软雅黑",552,40); 
				drawFont(ctx1,"S11","#44546a","normal normal bold 9px 微软雅黑",808,40); 
				drawFont(ctx1,"S11","#44546a","normal normal bold 9px 微软雅黑",888,40); 
			}
			
			if(canvas2.getContext) {
				var ctx2 = canvas2.getContext('2d');
				drawLine(ctx2,"#2e75b6",165,0,165,60); 
				drawLine(ctx2,"#2e75b6",505,0,505,60); 
				drawLine(ctx2,"#2e75b6",840,0,840,60); 
				drawFont(ctx2,"S1-MME","#44546a","normal normal bold 9px 微软雅黑",180,37); 
				drawFont(ctx2,"S1-MME","#44546a","normal normal bold 9px 微软雅黑",520,37); 
				drawFont(ctx2,"S1-MME","#44546a","normal normal bold 9px 微软雅黑",853,37); 
				var fstimg = "${ctxStatic}/jerichotab/images/fst.png";
				drawImage(fstimg,ctx2,55, 60,224,132); 
				drawImage(fstimg,ctx2,395, 60,224,132);
				drawImage(fstimg,ctx2,733, 60,224,132);
				drawFont(ctx2,"RAN （青岛）","#44546a","normal normal bold 16px 微软雅黑",110,220); 
				drawFont(ctx2,"RAN （潍坊、日照）","#44546a","normal normal bold 16px 微软雅黑",430,220); 
				drawFont(ctx2,"RAN （烟台、威海）","#44546a","normal normal bold 16px 微软雅黑",780,220); 
			}
			
			//显示弹框
    		$('#usuallyOperator').click(function(){
    			
    			showOperatorDlg('', 'COMMONTEM');
    		});
    		
    		//关闭弹窗
    		$('.claseDialogBtn').click(function(){
    			$('#dialogBg').fadeOut(300,function(){
    				$('#dialog').addClass('bounceOutUp').fadeOut();
    			});
    		});
    		
    		$('#emergencyOperator').click(function(){
    			$('#dialogBg').fadeIn(300);
    			$('#dialog1').removeAttr('class').addClass('animated bounceIn').fadeIn();
    		});
    		
    		$('.claseDialogBtn1').click(function(){
    			$('#dialogBg').fadeOut(300,function(){
    				$('#dialog1').addClass('bounceOutUp').fadeOut();
    			});
    		});
			
		});
		
		function drawImage(url,ctx,x,y,w,h){    
	        var img = new Image(); //创建一个Image对象，实现图片的预下载    
	        img.src = url;        
	  
	        img.onload = function () {
	        	ctx.drawImage(this,x,y,w,h);
	        };    
	   } 
		
	   function drawLine(ctx,color,x1,y1,x2,y2) {
		   	ctx.beginPath();
			ctx.strokeStyle = color;
			ctx.lineWidth=3;
			ctx.moveTo(x1,y1);
			ctx.lineTo(x2,y2);
			ctx.closePath();
			ctx.stroke();
	   }
	   
	   function drawFont(ctx,text,color,font,x,y) {
		   ctx.font = font;            //设置文本大小 + 字体
		   ctx.fillStyle = color;                        //设置文本颜色
		   ctx.fillText(text, x , y); 
	   }
	   
	   function dosubmit() {
		   loading('正在查询，请稍等...');
	   }
	   
	   function queryChart(){
		    loading('正在查询，请稍等...');
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
		
		function netTopology() {
			window.location.href = "${ctx}/performance/topologyManage/topologyManageIndex";			
		}
		
		function roomTopology() {
			window.location.href = "${ctx}/performance/topologyManage/showComputerRoomTopology";
		}
		
		
		function showOperatorDlg(type,temptype) {
			var icon = "";
			if('COMMONTEM' == temptype) {
				icon = 'mmeicon';
			} else if('EMERGENCYTEM' == temptype) {
				icon = 'yingjiicon';
			}
			$('.dialogContent ul li').remove();
			var ul_1 = $('.dialogContent ul:eq(0)');
			var ul_2 = $('.dialogContent ul:eq(1)');
			
			$.ajax({
				type : "POST",
				url : "${ctx}/netconfig/tMme/getEmergencytem?type="+type+"&temptype="+temptype,
				dataType : "html",
				data : {},
				success:function(data){
					data = eval(data);
					for(var i=0; i<data.length; i++) {
						var obj = data[i];
						var li = "<li class='"+icon+"' onclick=\"showExcelTemp('"+obj.id+"')\"><span>"+obj.name+"</span></li>";
						if(obj.type == '1') {
							ul_1.append(li);
						} else if(obj.type == '2') {
							ul_2.append(li);
						}
						
					}
					ul_1.append('<li class="addicon" onclick="showXmlFileUploadPage()"><span>新增操作</span></li>');
					ul_2.append('<li class="addicon" onclick="showXmlFileUploadPage()"><span>新增操作</span></li>');
					if('EMERGENCYTEM' == temptype) {
						$('.keyword').html("应急");
					} else if('COMMONTEM' == temptype) {
						$('.keyword').html("常用");
					}
	    			$('#dialogBg').fadeIn(300);
	    			$('#dialog').removeAttr('class').addClass('animated bounceIn').fadeIn();
				}
		   });
		}
		
		function checkEmergencyCode() {
			var authcode = $("#authcode").val();
			if(authcode == "") {
				top.$.jBox.tip('请输入授权码','warning');
				return;
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/netconfig/tMme/checkEmergencyCode",
				dataType : "html",
				data : {"authcode" : authcode},
				success:function(data){
					if(data == "fail") {
						top.$.jBox.tip('授权码不正确请重新输入','warning');
					} else {
						$('#dialogBg').fadeOut(300,function(){
							$('#dialog1').addClass('bounceOutUp').fadeOut();
						});
						
						showOperatorDlg('', 'EMERGENCYTEM');
					}
				}
		   });
		}
		
		function showExcelTemp(tempId) {
			var netId = $('#netId').val();
			window.location.href= "${ctx}/netconfig/tMme/showExcelTemp?tempId="+tempId+"&netId=" + netId;
		}
		
		function showXmlFileUploadPage() {
			window.location.href= "${ctx}/netconfig/tTpXmlFileUpload/form";
		}
	</script>
</head>
<body>
<form:form id="mainIndex" method="post">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-f" style="font-size: 45px; float:left;margin-left:10px;"></span>
			<a class="brand" style="font-size: 18px; margin-left: 20px;">全网实时监控</a>
			<div style="float: right" class="form-inline">
				<a class="brand" style="font-size: 15px;">粒度:网元</a>
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
	<div style="width: 98%; height: 50px;margin-top: 60px">
		<input type="image" src="${ctxStatic}/images/xcycz.png" style="float: left; height:55px"  id="usuallyOperator" />
		<input type="image" src="${ctxStatic}/images/xyjcz.png" style="float: left; height:55px; margin-left: 10px"  id="emergencyOperator" />
		<input type="image" src="${ctxStatic}/images/icon1.png" style="float: right" onclick="netTopology()">
		<input type="image" src="${ctxStatic}/images/icon2.png" style="float: right" onclick="roomTopology()">
	</div>
	<div style="margin-left: auto;margin-right: auto;width: 1000px;height: 240px;margin-top: 5px;">
		<canvas id="myCanvas" width="1000" height="200" style="border:#06F solid 0px;margin:0 auto;">
			您的浏览器不支持 Canvas
		</canvas>
		<div style="float: left;width:1000px;border:#06F solid 0px;margin-top: -4px;">
			<div style="float: left;width:322px;min-height: 322px;background-color:#bdd7ee;border-radius: 40px;">
				<div style="float: left;margin-top: 6px; width: 322px; height: 30px;text-align:center;line-height: 30px;font-size: 16px;font-weight: bold;color: #44546a">SAEGW-POOL1 （青岛）</div>
				<div style="float: left;width: 100%;">
					<ul style="float: left;margin: 0 0 0 0; list-style: none;">
					    <c:forEach items="${SAEGWPOOL1}" var="SAEGWPOOL1">
						<li style="float: left;width: 60px;height: 75px;border:#06F solid 0px;margin-left: 15px;margin-top: 15px">
							<a href="${ctx}/netconfig/tSaegw/list?netid=${SAEGWPOOL1.id}&netType=2" onclick="dosubmit()">
							<table>
								<tr>
									<td height="40" style="vertical-align: top"><span class="icon-a10" style="float:left;font-size: 50px; margin-left: 5px;"></span></td>
								</tr>
								<tr>
									<td height="35">
									<span style="margin-top: 10px;float: left;font-size: 11px;font-family: monospace; font-weight: bold;color: #44546a;">${fn:substring(SAEGWPOOL1.fname,0,10)}</span>
									</td>
								</tr>
							</table>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div style="float: left;margin-left: 15px;width:322px;min-height: 322px;background-color:#bdd7ee;border-radius: 40px">
				<div style="float: left;margin-top: 6px; width: 322px; height: 30px;text-align:center;line-height: 30px;font-size: 16px;font-weight: bold;color: #44546a">SAEGW-POOL2 （潍坊）</div>
				<div style="float: left;width: 100%;">
					<ul style="float: left;margin: 0 0 0 0; list-style: none;">
					    <c:forEach items="${SAEGWPOOL2}" var="SAEGWPOOL2">
						<li style="float: left;width: 60px;height: 75px;border:#06F solid 0px;margin-left: 15px;margin-top: 15px">
							<a href="${ctx}/netconfig/tSaegw/list?netid=${SAEGWPOOL2.id}&netType=2" onclick="dosubmit()">
							<table>
								<tr>
									<td height="40" style="vertical-align: top"><span class="icon-a10" style="float:left;font-size: 50px; margin-left: 5px;"></span></td>
								</tr>
								<tr>
									<td height="35">
									<span style="margin-top: 10px;float: left;font-size: 11px;font-family: monospace; font-weight: bold;color: #44546a;">${fn:substring(SAEGWPOOL2.fname,0,10)}</span>
									</td>
								</tr>
							</table>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div style="float: left;margin-left: 15px;width:322px;min-height: 322px;background-color:#bdd7ee;border-radius: 40px">
				<div style="float: left;margin-top: 6px; width: 322px; height: 30px;text-align:center;line-height: 30px;font-size: 16px;font-weight: bold;color: #44546a">SAEGW-POOL3 （烟台）</div>
				<div style="float: left;width: 100%;">
					<ul style="float: left;margin: 0 0 0 0; list-style: none;">
					    <c:forEach items="${SAEGWPOOL3}" var="SAEGWPOOL3">
						<li style="float: left;width: 60px;height: 75px;border:#06F solid 0px;margin-left: 15px;margin-top: 15px">
							<a href="${ctx}/netconfig/tSaegw/list?netid=${SAEGWPOOL3.id}&netType=2" onclick="dosubmit()">
							<table>
								<tr>
									<td height="40" style="vertical-align: top"><span class="icon-a10" style="float:left;font-size: 50px; margin-left: 5px;"></span></td>
								</tr>
								<tr>
									<td height="35">
									<span style="margin-top: 10px;float: left;font-size: 11px;font-family: monospace; font-weight: bold;color: #44546a;">${fn:substring(SAEGWPOOL3.fname,0,10)}</span>
									</td>
								</tr>
							</table>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<canvas id="myCanvas1" width="1000" height="80" style="border:#06F solid 0px;margin:0 auto;">
			您的浏览器不支持 Canvas
		</canvas>
		<div style="float: left;width:1000px;border:#06F solid 0px;margin-top: -4px;">
			<div style="float: left;width:322px;min-height: 322px;background-color:#deebf7;border-radius: 40px;">
				<div style="float: left;margin-top: 6px; width: 322px; height: 30px;text-align:center;line-height: 30px;font-size: 16px;font-weight: bold;color: #44546a">MME-POOL1 （青岛）</div>
				<div style="float: left;width: 100%;">
					<ul style="float: left;margin: 0 0 0 0; list-style: none;">
					    <c:forEach items="${MMEPOOL1}" var="MMEPOOL1">
						<li style="float: left;width: 60px;height: 75px;border:#06F solid 0px;margin-left: 15px;margin-top: 15px">
							<a href="${ctx}/netconfig/tMme/list?netid=${MMEPOOL1.id}&netType=1" onclick="dosubmit()">
							<table>
								<tr>
									<td height="40" style="vertical-align: top"><span class="icon-a2" style="float:left;font-size: 50px; margin-left: 5px;"></span></td>
								</tr>
								<tr>
									<td height="35">
									<span style="margin-top: 10px;float: left;font-size: 11px;font-family: monospace; font-weight: bold;color: #44546a;">${fn:substring(MMEPOOL1.fname,0,8)}</span>
									</td>
								</tr>
							</table>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div style="float: left;margin-left: 15px;width:322px;min-height: 322px;background-color:#deebf7;border-radius: 40px">
				<div style="float: left;margin-top: 6px; width: 322px; height: 30px;text-align:center;line-height: 30px;font-size: 16px;font-weight: bold;color: #44546a">MME-POOL2 （潍坊）</div>
				<div style="float: left;width: 100%;">
					<ul style="float: left;margin: 0 0 0 0; list-style: none;">
					    <c:forEach items="${MMEPOOL2}" var="MMEPOOL2">
						<li style="float: left;width: 60px;height: 75px;border:#06F solid 0px;margin-left: 15px;margin-top: 15px">
							<a href="${ctx}/netconfig/tMme/list?netid=${MMEPOOL2.id}&netType=1" onclick="dosubmit()">
							<table>
								<tr>
									<td height="40" style="vertical-align: top"><span class="icon-a2" style="float:left;font-size: 50px; margin-left: 5px;"></span></td>
								</tr>
								<tr>
									<td height="35">
									<span style="margin-top: 10px;float: left;font-size: 11px;font-family: monospace; font-weight: bold;color: #44546a;">${fn:substring(MMEPOOL2.fname,0,8)}</span>
									</td>
								</tr>
							</table>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div style="float: left;margin-left: 15px;width:322px;min-height: 322px;background-color:#deebf7;border-radius: 40px">
				<div style="float: left;margin-top: 6px; width: 322px; height: 30px;text-align:center;line-height: 30px;font-size: 16px;font-weight: bold;color: #44546a">MME-POOL3 （烟台）</div>
				<div style="float: left;width: 100%;">
					<ul style="float: left;margin: 0 0 0 0; list-style: none;">
					    <c:forEach items="${MMEPOOL3}" var="MMEPOOL3">
						<li style="float: left;width: 60px;height: 75px;border:#06F solid 0px;margin-left: 15px;margin-top: 15px">
							<a href="${ctx}/netconfig/tMme/list?netid=${MMEPOOL3.id}&netType=1" onclick="dosubmit()">
							<table>
								<tr>
									<td height="40" style="vertical-align: top"><span class="icon-a2" style="float:left;font-size: 50px; margin-left: 5px;"></span></td>
								</tr>
								<tr>
									<td height="35">
									<span style="margin-top: 10px;float: left;font-size: 11px;font-family: monospace; font-weight: bold;color: #44546a;">${fn:substring(MMEPOOL3.fname,0,8)}</span>
									</td>
								</tr>
							</table>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<canvas id="myCanvas2" width="1000" height="250" style="border:#06F solid 0px;margin:0 auto;">
			您的浏览器不支持 Canvas
		</canvas>
	</div>
	
	
	<div id="dialogBg"></div>
	<div id="dialog" class="animated">
		<div class="dialogTop">
			<span style="float: left; font-size: 18px;font-weight: bold;margin-left: 200px;font-family:微软雅黑"><span class="keyword"></span>维护操作</span>
			<a href="javascript:;" class="claseDialogBtn"><img src="${ctxStatic}/images/closebtn.png" border="0" width="30" height="29"></a>
		</div>
		<div class="dialogContent">
			<span style="float: left; width: 90%; margin-left: 30px; font-weight: bold;color: #000">MME<span class="keyword"></span>维护操作</span>
			<ul style="border-bottom: 1px dashed ;">
				<%-- <c:forEach items="${mmeList }" var="item">
					<li class="mmeicon" onclick="showExcelTemp('${item.id }')"><span>${item.name }</span></li>
				</c:forEach>
				<li class="addicon" onclick="showXmlFileUploadPage()"><span>新增操作</span></li> --%>
			</ul>
			<span style="float: left; width: 90%; margin-left: 30px; font-weight: bold;color: #000">SAEGW<span class="keyword"></span>维护操作</span>
			<ul>
				<%-- <c:forEach items="${saegwList }" var="item">
					<li class="mmeicon" onclick="showExcelTemp('${item.id }')"><span>${item.name }</span></li>
				</c:forEach>
				<li class="addicon" onclick="showXmlFileUploadPage()"><span>新增操作</span></li> --%>
			</ul>
		</div>
		
	</div>
	
	<div id="dialog1" class="animated">
		<div class="dialogTop">
			<span style="float: left; font-size: 18px;font-weight: bold;">请输入应急维护操作授权码</span>
			<a href="javascript:;" class="claseDialogBtn1"><img src="${ctxStatic}/images/closebtn.png" border="0" width="30" height="29"></a>
		</div>
		<div class="dialogContent">
			<table style="width: 100%; height: 160px">
				<tr>
					<td>
						<input type="text" name="authcode" id="authcode" style="margin-left: 95px; width: 400px;height: 30px;border: 1px solid; margin-top: 20px">
					</td>
				</tr>
				<tr>
					<td>
						<input type="image" src="${ctxStatic}/images/emergencyOkBtn.png" style="float: right;margin-right: 20px;" onclick="checkEmergencyCode()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>