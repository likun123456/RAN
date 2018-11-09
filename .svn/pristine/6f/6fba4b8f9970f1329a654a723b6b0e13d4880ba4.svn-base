<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拓扑图</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/vis/vis.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/vis/vis-network.min.css" />
	<style type="text/css">
	    #mynetwork {
	      float: right;
	      width: 98%;
	      border: 1px solid lightgray;
	    }
	    
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
		var routerDir = "${ctxStatic}/jerichotab/images/";
		var nodeArray;
		var edgeArray;
		var roomId = '${roomId}';
		$(document).ready(function() {
			//设置tab被选择事件样式
			$("#room_"+roomId).addClass("active");
			
			changeTableHeight();
			window.onresize=function(){
				changeTableHeight();
			}
			
			initTopology("all","-1",false);
			
			
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
		
		function initTopology(netType,nodeId,isDoubleClick) {
			$.ajax({
				type : "POST",
				url : "${ctx}/performance/topologyManage/loadData?roomId="+roomId+"&netType="+netType+"&nodeId="+nodeId,
				dataType : "html",
				data : {},
				success:function(data){
					data = eval(data);
					nodeArray = data[0];
					edgeArray = data[1];
					
					for(var i=0; i<nodeArray.length; i++) {
						var obj = nodeArray[i];
						obj.image = routerDir + obj.image;
					}
					showTopolog(isDoubleClick);
				}
		   });
		}
		
		function showTopolog(isDoubleClick){
			var nodes = new vis.DataSet(nodeArray);
		 	var edges = new vis.DataSet(edgeArray);
		    var container = document.getElementById('mynetwork');
		    var data = {
		       	nodes: nodes,
		        edges: edges
		    };
		    var options = {
		    	    edges: {
		    	      font: {
		    	        size: 12
		    	      },
		    	      widthConstraint: {
		    	        maximum: 90
		    	      }
		    	    },
		    	    nodes: {
		    	    },
		    	    physics: {
		    	      enabled: false
		    	    },
					manipulation: {
						enabled: false
					}
		    };
		    network = new vis.Network(container, data, options);
		    
		    if(!isDoubleClick) {
		    	 network.on("doubleClick", function (params) {
				   		var n = params.nodes;
				   		if(n.length>0) {
				   			//点击了节点
					    	var id = n[0];
				   			
					    	for(var i=0; i<nodeArray.length; i++) {
				    		   var node = nodeArray[i];
				    		   if(node.id == id) {
				    			   var label = node.label;
				    			   if(label.indexOf("MME") > -1) {
				    				   initTopology("MME",id,true);
				    			   }
				    			   if(label.indexOf("SAEGW") > -1) {
				    				   initTopology("SAEGW",id,true);
				    			   }
				    			   if(label.indexOf("PCRF") > -1) {
				    				   initTopology("PCRF",id,true);
				    			   }
				    			   
				    		   }
					    	}
				   			
				   		}
				   	});
		    } else {
		    	$('#saveBtn').css("display","none");
		    	$('#backBtn').css("display","block");
		    	network.on("doubleClick", function (params) {
				       var n = params.nodes;
				       if(n.length>0) {
				    	   //点击了节点
				    	   var id = n[0]; 
				    	   //关联节点id
				    	   var relid="";
				    	   //循环连线数组找出所有相连的节点
				    	   var lines = params.edges;
				    	   for(var i=0; i<lines.length; i++) {
				    		   var line = lines[i];
				    		   var ids = line.substring(0,line.lastIndexOf('-'));
				    		   ids = ids.split('_');
				    		   if(ids[0] == id) {
				    			   relid += ids[1] + " ";
				    		   } else {
				    			   relid += ids[0] + " ";
				    		   }
				    	   }
				    	   for(var i=0; i<nodeArray.length; i++) {
				    		   var node = nodeArray[i];
				    		   if(node.id == id) {
				    			   var label = node.label;
				    			   if(label.indexOf("MME") > -1) {
				    				   loading('正在查询，请稍等...');
				    				   window.location.href= "${ctx}/netconfig/tMme/list?nodeid="+id;
				    			   }
				    			   if(label.indexOf("SAEGW") > -1) {
				    				   loading('正在查询，请稍等...');
				    				   window.location.href= "${ctx}/netconfig/tSaegw/list?nodeid="+id;
				    			   }
				    			   
				    		   }
				    	   }
				    	   
				    	   
				       } else {
				    	   //点击了线
				    	   var lines = params.edges;
				    	   if(lines.length>=1) {
				    		   var line = lines[0];
				    		   var ids = line.substring(0,line.lastIndexOf('-'));
				    		   var idArr = ids.split('_');
				    		   var netName;
				    		   var nextNetName;
				    		   for(var i=0; i<nodeArray.length; i++) {
				    			   var node = nodeArray[i];
				    			   if(node.id == idArr[0]) {
				    				   netName = node.label;
				    			   } else if(node.id == idArr[1]) {
				    				   nextNetName = node.label;
				    			   }
				    		   }
				    		   var html = '<div style="width:350px;height:160px;background-color:#f2f2f2;padding-left:20px;padding-top:10px">'+
				    		   				'<table><tr><td style="height: 20px">网元名称：'+netName+'</td></tr><tr><td style="height: 20px">下一跳名称：'+nextNetName+'</td></tr><tr><td style="height: 20px">网元侧IP地址：192.168.0.1</td></tr><tr><td style="height: 20px">对端IP地址：192.168.0.2</td></tr><tr><td style="height: 20px">链路类型：</td></tr><tr><td style="height: 20px">链路状态：正常</td></tr></table>' + 
				    		   			  '</div>';
				    		   top.$.jBox.open(html, "<font size='2'>详细信息</font>",350,240,{
					   			   buttons:{"关闭":false}, bottomText:"",submit:function(v, h, f){
					   			   }, loaded:function(h){
					   					$(".jbox-content", top.document).css("overflow-y","hidden");
					   			   }
				   			   });
				    	   }
				       }
				    });
		    }
		    
		    network.on("dragEnd", function (params) {
		    	var n = params.nodes;
		    	if(n.length>0) {
		    		var id = n[0];
		    		var c = params.pointer.canvas;
		    		var x = parseInt(c.x);
		    		var y = parseInt(c.y);
		    		for(var i=0; i<nodeArray.length; i++) {
		    			var obj = nodeArray[i];
		    			if(obj.id == id) {
		    				obj.x = x;
		    				obj.y = y;
		    				break;
		    			}
		    		}
		    		
		    	}
		    });
		}
		
		function save() {
			
			$.ajax({
				type : "POST",
				url : "${ctx}/performance/topologyManage/savePosition",
				dataType : "html",
				traditional:true, //阻止深度序列化
				data : {"nodeArray":JSON.stringify(nodeArray)}, // 转成字符串形式
				success:function(dataQuery){
					if(dataQuery=="success"){
			    		top.$.jBox.tip('拓扑图保存成功','warning');	
			    	}else{
			    		top.$.jBox.tip('拓扑图保存失败','warning');
			    	}
				}
		   });
			
		}
		
		function back() {
			$('#saveBtn').css("display","block");
	    	$('#backBtn').css("display","none");
			initTopology("all","-1",false);
		}
		
		function changeTableHeight(){
		    var table= $("#mynetwork"); 
		    var height = document.documentElement.clientHeight;
		    /* if(height < 500){
		    	table.height(document.documentElement.clientHeight * 0.86);
		    }else if(height >=500){
		    	table.height(document.documentElement.clientHeight * 0.86);
		    } */
		    table.height(document.documentElement.clientHeight * 0.9);
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
	<div style="width: 98%; height: 50px;margin-top: 60px; margin-bottom: 10px">
		<input type="image" src="${ctxStatic}/images/xcycz.png" style="float: left; height:55px"   id="usuallyOperator" />
		<input type="image" src="${ctxStatic}/images/xyjcz.png" style="float: left; height:55px; margin-left: 10px"  id="emergencyOperator" />
		<input type="image" src="${ctxStatic}/images/icon3.png" style="float: right" onclick="netTopology()">
		<input type="image" src="${ctxStatic}/images/icon4.png" style="float: right" onclick="roomTopology()">
	</div>
	<ul class="nav nav-tabs">
	<!--  class="active" -->
		<c:forEach items="${roomList }" var="item">
			<li id="room_${item.id }"><a href="${ctx}/performance/topologyManage/showComputerRoomTopology?roomId=${item.id}">${item.name }</a></li>
		</c:forEach>
	</ul>
	<input id="saveBtn" type="button" class="btn btn-primary" style="float: right;" value="保存" onclick="save();">
	<input id="backBtn" type="button" class="btn btn-primary" style="float: right; display: none;" value="返回" onclick="back();">
	<div id="mynetwork"></div>
	
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