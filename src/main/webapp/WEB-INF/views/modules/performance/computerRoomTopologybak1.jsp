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
	      width: 1000px;
	      height: 800px;
	      border: 1px solid lightgray;
	    }
	 </style>
	<script type="text/javascript">
		var routerDir = "${ctxStatic}/jerichotab/images/";
		
		var nodeArray = [
			{id: 1, image: routerDir + 'ipczw.png', shape: 'image', size:50, x: 20, y: 20},
			{id: 2, image: routerDir + 'cmnet.png', shape: 'image', size:40, x: 278, y: 3},
			{id: 3, image: routerDir + 'gprszw.png', shape: 'image', size:50, x: 450, y: 3},
			{id: 4, label: '浮山路网管交换机 ', image: routerDir + 'switch.png', shape: 'image', size:20, x: 670, y: 0},
			{id: 5, label: '浮山路计费交换机', image: routerDir + 'switch.png', shape: 'image', size:20, x: 800, y: 0},
			{id: 6, label: 'PS-IMS-CE99-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20, x: 100, y: 100},
			{id: 7, label: 'PS-IMS-CE100-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20, x: 250, y: 100},
			{id: 8, label: 'QDAFW17-E8000E-X16\nGprs出口防火墙0', image: routerDir + 'fhq.png', shape: 'image', size:20, x: 350, y: 120},
			{id: 9, label: 'QDAFW18-E8000E-X16\nGprs出口防火墙1', image: routerDir + 'fhq.png', shape: 'image', size:20, x: 400, y: 120},
			{id: 10, label: 'QDART01BER-FZL04-NE40E\n福州路4层', image: routerDir + 'router.png', shape: 'image', size:20, x: 500, y: 120},
			{id: 11, label: 'QDART01BER-DHL07-NE40E\n东海路7层', image: routerDir + 'router.png', shape: 'image', size:20, x: 600, y: 120},
			{id: 12, label: 'FW09-FSL02-SSG550\n网管计费接入防火墙', image: routerDir + 'fhq.png', shape: 'image', size:20, x: 700, y: 120},
			{id: 13, label: 'FW10-FSL02-SSG550\n网管计费接入防火墙', image: routerDir + 'fhq.png', shape: 'image', size:20, x: 800, y: 120},
			{id: 14, label: 'NGN CE53-SE800\n浮山路3层', image: routerDir + 'router.png', shape: 'image', size:20, x: 0, y: 220},
			{id: 15, label: 'NGN CE54-SE800\n浮山路3层', image: routerDir + 'router.png', shape: 'image', size:20, x: 100, y: 220},
			{id: 16, label: 'IMS-CE25-SE800\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20, x: 200, y: 220},
			{id: 17, label: 'PS-IMS-CE26-SE800\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20, x: 300, y: 220},
			{id: 18, label: 'Gn-RT15-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20, x: 400, y: 250},
			{id: 19, label: 'Gn-RT16-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20, x: 500, y: 250},
			{id: 20, label: 'QDAMME01', image: routerDir + 'mme.png', shape: 'image', size:20, x: 0, y: 400},
			{id: 21, label: 'QDAMME02', image: routerDir + 'mme.png', shape: 'image', size:20, x: 100, y: 400},
			{id: 22, label: 'QDASAEGW04', image: routerDir + 'saegw.png', shape: 'image', size:20, x: 200, y: 400},
			{id: 23, label: 'QDASAEGW10', image: routerDir + 'saegw.png', shape: 'image', size:20, x: 300, y: 400},
			{id: 24, image: routerDir + 'ptncsw.png', shape: 'image', size:50, x: 140, y: 550},
			{id: 25, label: 'LTE-X460-FSL-01', image: routerDir + 'switch.png', shape: 'image', size:20, x: 440, y: 550},
			{id: 26, label: 'LTE-X460-FSL-02', image: routerDir + 'switch.png', shape: 'image', size:20, x: 570, y: 550},
			
			{id: 27, label: 'QDACG08', image: routerDir + 'cg.png', shape: 'image', size:20, x: 0, y: 700},
			{id: 28, label: 'QDACG09', image: routerDir + 'cg.png', shape: 'image', size:20, x: 80, y: 700},
			{id: 29, label: 'QDACG12', image: routerDir + 'cg.png', shape: 'image', size:20, x: 160, y: 700},
			{id: 30, label: 'QDACG13', image: routerDir + 'cg.png', shape: 'image', size:20, x: 240, y: 700},
			{id: 31, label: 'QDACG14', image: routerDir + 'cg.png', shape: 'image', size:20, x: 320, y: 700},
			{id: 32, label: 'QDACG15', image: routerDir + 'cg.png', shape: 'image', size:20, x: 400, y: 700},
			{id: 33, label: 'QDACG16', image: routerDir + 'cg.png', shape: 'image', size:20, x: 480, y: 700},
			{id: 34, label: 'QDACG17', image: routerDir + 'cg.png', shape: 'image', size:20, x: 560, y: 700},
			{id: 35, label: 'QDACG18', image: routerDir + 'cg.png', shape: 'image', size:20, x: 640, y: 700},
			{id: 36, label: 'QDACG19', image: routerDir + 'cg.png', shape: 'image', size:20, x: 720, y: 700}
	    ];
		
		var edgeArray = [
	 		 {id:'1_6-1', from: 1, to: 6/* , color:{color:'red', highlight:'red'} */},
	 		 {id:'1_7-2', from: 1, to: 7}, 
	 		 {id:'2_8-3', from: 2, to: 8},
	 		 {id:'2_9-4', from: 2, to: 9},
	 		 {id:'3_10-5', from: 3, to: 10},
	 		 {id:'3_11-6', from: 3, to: 11},
	 		 {id:'10_11-7', from: 10, to: 11},
	 		 {id:'4_12-8', from: 4, to: 12},
	 		 {id:'5_13-9', from: 5, to: 13},
	 		 {id:'1_14-10', from: 1, to: 14},
	 		 {id:'1_15-11', from: 1, to: 15},
	 		 {id:'6_16-12', from: 6, to: 16},
	 		 {id:'7_17-13', from: 7, to: 17},
	 		 {id:'16_17-14', from: 16, to: 17},
	 		 {id:'8_18-15', from: 8, to: 18},
	 		 {id:'9_19-16', from: 9, to: 19},
	 		 {id:'18_19-17', from: 18, to: 19},
	 		 {id:'18_10-18', from: 18, to: 10},
	 		 {id:'19_11-19', from: 19, to: 11},
	 		 {id:'20_14-20', from: 20, to: 14},
	 		 {id:'20_15-21', from: 20, to: 15},
	 		 {id:'20_16-22', from: 20, to: 16},
	 		 {id:'20_17-23', from: 20, to: 17},
	 		 {id:'20_18-24', from: 20, to: 18},
	 		 {id:'20_19-25', from: 20, to: 19},
	 		 {id:'21_14-26', from: 21, to: 14},
	 		 {id:'21_15-27', from: 21, to: 15},
	 		 {id:'21_16-28', from: 21, to: 16},
	 		 {id:'21_17-29', from: 21, to: 17},
	 		 {id:'22_16-30', from: 22, to: 16},
	 		 {id:'22_17-31', from: 22, to: 17},
	 		 {id:'22_18-32', from: 22, to: 18},
	 		 {id:'22_19-33', from: 22, to: 19},
	 		 {id:'23_16-34', from: 23, to: 16},
	 		 {id:'23_17-35', from: 23, to: 17},
	 		 {id:'23_18-36', from: 23, to: 18},
	 		 {id:'23_19-37', from: 23, to: 19},
	 		 {id:'24_20-38', from: 24, to: 20},
	 		 {id:'24_21-39', from: 24, to: 21},
	 		 {id:'24_22-40', from: 24, to: 22},
	 		 {id:'24_23-41', from: 24, to: 23},
	 		 {id:'25_20-42', from: 25, to: 20},
	 		 {id:'25_21-43', from: 25, to: 21},
	 		 {id:'25_22-44', from: 25, to: 22},
	 		 {id:'25_23-45', from: 25, to: 23},
	 		 {id:'25_18-46', from: 25, to: 18},
	 		 {id:'25_12-47', from: 25, to: 12},
	 		 {id:'26_20-48', from: 26, to: 20},
	 		 {id:'26_21-49', from: 26, to: 21},
	 		 {id:'26_22-50', from: 26, to: 22},
	 		 {id:'26_23-51', from: 26, to: 23},
	 		 {id:'26_19-52', from: 26, to: 19},
	 		 {id:'26_13-53', from: 26, to: 13},
	 		 
	 		 {id:'25_27-54', from: 25, to: 27},
	 		 {id:'25_29-55', from: 25, to: 29},
	 		 {id:'25_31-56', from: 25, to: 31},
	 		 {id:'25_33-57', from: 25, to: 33},
	 		 {id:'25_35-58', from: 25, to: 35},
	 		 {id:'26_28-59', from: 26, to: 28},
	 		 {id:'26_30-60', from: 26, to: 30},
	 		 {id:'26_32-61', from: 26, to: 32},
	 		 {id:'26_34-62', from: 26, to: 34},
	 		 {id:'26_36-63', from: 26, to: 36}
	 	];
		$(document).ready(function() {
			
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
		    network.on("doubleClick", function (params) {
		       var n = params.nodes;
		       if(n.length>0) {
		    	   debugger;
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
		    	   console.log("点击了:" + id + " 关联节点:" + relid);
		    	   window.location.href= "${ctx}/netconfig/tMme";
		    	   
		       } else {
		    	   //点击了线
		    	   var lines = params.edges;
		    	   if(lines.length>=1) {
		    		   debugger;
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
		});
		
		function save() {
			var o = nodeArray[0];
			var o1 = nodeArray[1];
			var o2 = nodeArray[2];
			console.log(o);
			console.log(o1);
			console.log(o2);
		}
	</script>
</head>
<body>
	<!-- <input type="button" class="btn btn-primary" value="保存" onclick="save();"> -->
	<div id="mynetwork"></div>
</body>
</html>