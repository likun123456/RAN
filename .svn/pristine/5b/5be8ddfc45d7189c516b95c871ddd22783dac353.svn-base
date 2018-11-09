<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>ECI维度指标查询管理</title>
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
		$(document).ready(function() {
			var nodes = new vis.DataSet([
				{id: 1, image: routerDir + 'ipczw.png', shape: 'image', size:50},
				{id: 2, image: routerDir + 'cmnet.png', shape: 'image', size:40},
				{id: 3, image: routerDir + 'gprszw.png', shape: 'image', size:50},
				{id: 4, label: '浮山路网管交换机 ', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 5, label: '浮山路计费交换机', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 6, label: 'PS-IMS-CE99-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 7, label: 'PS-IMS-CE100-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 8, label: 'QDAFW17-E8000E-X16\nGprs出口防火墙0', image: routerDir + 'fhq.png', shape: 'image', size:20},
				{id: 9, label: 'QDAFW18-E8000E-X16\nGprs出口防火墙1', image: routerDir + 'fhq.png', shape: 'image', size:20},
				{id: 10, label: 'QDART01BER-FZL04-NE40E\n福州路4层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 11, label: 'QDART01BER-DHL07-NE40E\n东海路7层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 12, label: 'FW09-FSL02-SSG550\n网管计费接入防火墙', image: routerDir + 'fhq.png', shape: 'image', size:20},
				{id: 13, label: 'FW10-FSL02-SSG550\n网管计费接入防火墙', image: routerDir + 'fhq.png', shape: 'image', size:20},
				{id: 14, label: 'NGN CE53-SE800\n浮山路3层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 15, label: 'NGN CE54-SE800\n浮山路3层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 16, label: 'IMS-CE25-SE800\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 17, label: 'PS-IMS-CE26-SE800\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 18, label: 'Gn-RT15-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 19, label: 'Gn-RT16-MX960\n浮山路2层', image: routerDir + 'router.png', shape: 'image', size:20},
				{id: 20, label: 'QDAMME01', image: routerDir + 'mme.png', shape: 'image', size:20},
				{id: 21, label: 'QDAMME02', image: routerDir + 'mme.png', shape: 'image', size:20},
				{id: 22, label: 'QDASAEGW04', image: routerDir + 'saegw.png', shape: 'image', size:20},
				{id: 23, label: 'QDASAEGW10', image: routerDir + 'saegw.png', shape: 'image', size:20},
				{id: 24, image: routerDir + 'ptncsw.png', shape: 'image', size:50},
				{id: 25, label: 'LTE-X460-FSL-01', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 26, label: 'LTE-X460-FSL-02', image: routerDir + 'switch.png', shape: 'image', size:20},
				
				{id: 27, label: 'QDACG08', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 28, label: 'QDACG09', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 29, label: 'QDACG12', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 30, label: 'QDACG13', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 31, label: 'QDACG14', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 32, label: 'QDACG15', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 33, label: 'QDACG16', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 34, label: 'QDACG17', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 35, label: 'QDACG18', image: routerDir + 'switch.png', shape: 'image', size:20},
				{id: 36, label: 'QDACG19', image: routerDir + 'switch.png', shape: 'image', size:20}
		    ]);
			
		 	var edges = new vis.DataSet([
		 		 {from: 1, to: 6},
		 		 {from: 1, to: 7}, 
		 		 {from: 2, to: 8},
		 		 {from: 2, to: 9},
		 		 {from: 3, to: 10},
		 		 {from: 3, to: 11},
		 		 {from: 10, to: 11},
		 		 {from: 4, to: 12},
		 		 {from: 5, to: 13},
		 		 {from: 1, to: 14},
		 		 {from: 1, to: 15},
		 		 {from: 6, to: 16},
		 		 {from: 7, to: 17},
		 		 {from: 16, to: 17},
		 		 {from: 8, to: 18},
		 		 {from: 9, to: 19},
		 		 {from: 18, to: 19},
		 		 {from: 18, to: 10},
		 		 {from: 19, to: 11},
		 		 {from: 20, to: 14},
		 		 {from: 20, to: 15},
		 		 {from: 20, to: 16},
		 		 {from: 20, to: 17},
		 		 {from: 20, to: 18},
		 		 {from: 20, to: 19},
		 		 {from: 21, to: 14},
		 		 {from: 21, to: 15},
		 		 {from: 21, to: 16},
		 		 {from: 21, to: 17},
		 		 {from: 22, to: 16},
		 		 {from: 22, to: 17},
		 		 {from: 22, to: 18},
		 		 {from: 22, to: 19},
		 		 {from: 23, to: 16},
		 		 {from: 23, to: 17},
		 		 {from: 23, to: 18},
		 		 {from: 23, to: 19},
		 		 {from: 24, to: 20},
		 		 {from: 24, to: 21},
		 		 {from: 24, to: 22},
		 		 {from: 24, to: 23},
		 		 {from: 25, to: 20},
		 		 {from: 25, to: 21},
		 		 {from: 25, to: 22},
		 		 {from: 25, to: 23},
		 		 {from: 25, to: 18},
		 		 {from: 25, to: 12},
		 		 {from: 26, to: 20},
		 		 {from: 26, to: 21},
		 		 {from: 26, to: 22},
		 		 {from: 26, to: 23},
		 		 {from: 26, to: 19},
		 		 {from: 26, to: 13},
		 		 
		 		 {from: 25, to: 27},
		 		 {from: 25, to: 29},
		 		 {from: 25, to: 31},
		 		 {from: 25, to: 33},
		 		 {from: 25, to: 35},
		 		 {from: 26, to: 28},
		 		 {from: 26, to: 30},
		 		 {from: 26, to: 32},
		 		 {from: 26, to: 34},
		 		 {from: 26, to: 36},
		 	]);
		 	
		    var container = document.getElementById('mynetwork');
		    var data = {
		       	nodes: nodes,
		        edges: edges
		    };
		    var options = {
		    	   /*  edges: {
		    	      font: {
		    	        size: 12
		    	      },
		    	      widthConstraint: {
		    	        maximum: 90
		    	      }
		    	    }, */
		    	    /* nodes: {
		    	    },
		    	    physics: {
		    	      enabled: false
		    	    },
		    	    interaction:{hover:true},
					manipulation: {
						enabled: false
					} */
		    };
		    network = new vis.Network(container, data, options);
		    network.on("doubleClick", function (params) {
		      debugger;
		       var msg = '<h2>doubleClick event:</h2>' + JSON.stringify(params, null, 4);
		       alert(msg);
		    });
		});
	</script>
</head>
<body>
	<div id="mynetwork"></div>
</body>
</html>