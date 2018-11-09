<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>saegw节点管理</title>
	<meta name="decorator" content="mme_config"/>
	<style type="text/css">
		.main {
			float:left;
			width:99%
		}
		.left {
			float:left;
			width:50%;
			height:1042px;
			border-right: solid #000 1px;
		}
		.cabinet {
			float:right;
			margin-right:85px;
			width:450px;
			height:1042px;
			background:url(${ctxStatic}/images/card/SSR/SSR8020CAB.png);
		}
		
		.cabinet table {
	    	float: left;
	    	margin-left: 159px;
	    	width: 216px;
	    	height: 202px;
	        border-collapse:collapse;
	    }
	    
	    .cabinet td {
	    	/* border: 1px solid #000; */
	    }
	    
	     .cabinet .t1 {
	    	margin-top: 270px;
	    	
	    }
	     .cabinet .t2 {
	    	margin-top: 3px;
	    	
	    }
	    
	    .cabinet td {
	    	position:relative;
	    	cursor: pointer;
	    }
	    
	    .cabinet td span{
	     	position: absolute;
	     	top:12px; 
	     	left:7px;
	     	display: block; 
	     	width: 7px; 
	     	height: 7px;
	     	border:solid 1px #ffffff;
	     	border-radius:50%;
	     	-moz-border-radius:50%;
	     	-webkit-border-radius:50%;
	    }
	    
	    .cabinet td div{
	     	position: absolute;
	     	bottom:0px; 
	     	width: 100%; 
	    	filter:alpha( Opacity=50);-moz-opacity:0.5;opacity:0.5;
	    }
		
		.right {
			float:left;
			width:49%;
		}
		
		.net_node_msg {
			float: left;
			margin-top: 20px;
			margin-left: 80px;
		}
		
		.saegw_icon {
			float: left;
		}
		
		.saegw_icon p {
			font-family:Arial, Helvetica, sans-serif; 
			font-size:14px; 
			margin-left:0px;
			margin-top: 0px;
			font-weight: bold;
			color: #44546a;
		}
		
		.saegw_node_list {
			float: left;
			margin-left:20px;
			margin-top:0px;
			width: 270px;
		}
		
		.saegw_node_list ul {
			float:left;
			padding-left: 0px;
			margin-top:0px;
			list-style: none;
		} 
		.saegw_node_list ul li {
			float: left;
			width: 350px;
			font-family:Arial, Helvetica, sans-serif; 
			font-weight: bold;
			color: #1f4e79;
			font-size: 12px;
		}
		.card_model {
			float: left;
			margin-left:60px;
			width:470px;
			height:80px;
			border: solid #bfbfbf 1px;
		}
		
		.card_model img {
			float: left;
			margin-left: 20px;
			margin-top: 15px;
		}
		
		.card_model p {
			float: left;
			font-size: 14px;
			margin-top: 22px;
   			margin-left: 20px;
		}
		
		.card_model input {
			float: left;
			margin-top: 22px;
   			margin-left: 20px;
		}
		
		.card_model select {
			float: left;
			margin-top: 30px;
   			margin-left: 20px;
		}
		
		.menubar {
			float: left;
			margin-left:60px;
			margin-top:20px;
			width:470px;
			height:35px;
			line-height: 25px;
		}

 		.menubar .menuend {
	    	clear: both;
	    }
	    .menuitem
	        {background: #fff; position: relative; float: left; margin-right: 1em;}
	    .menuitem .submenu {
	    	 position: absolute; top: 35px; left: -1px; width: 230px;
	    }
	    .menuitem .submenu ul {
	    	padding-left:0px;
	    	margin-top: 0px;
	    	list-style: none;
	    }
	    .menuitem .submenu ul li {
	    	padding-left:5px;
	    	margin-top: -1px;
	    	margin-left: 2px;
	    	font-size:12px;
	    	background: #f6f6f6;
	    	border: solid #bfbfbf 1px;
	    	cursor:pointer;
	    }
	    /** 下面的控制显示和隐藏 **/
	    .menuitem .submenu {
	    	display: none;
	    }
	    .menuitem:hover .submenu {
	    	display: block;
	    }
	    
	    .tab_div {
			float:left;
			margin-top:20px;
			margin-left:60px;
			width:470px;
			height: 350px;
			border: 1px solid #bfbfbf;
		}
		
		#tab {float:left;overflow:hidden;zoom:1;background:#fff;width: 150px;margin-top: 0px;}
		#tab li {float:left;color:#000;height:30px;list-style:none; cursor:pointer; line-height:30px;padding:0 20px;}
		#tab li.current {
			color:#fff;background:#44546a;
			border-radius: 0 0 10px 10px;
			-moz-border-radius: 0 0 10px 10px;
			-webkit-border-radius: 0 0 10px 10px;
		}
		#content {border-top-width:0;}
		#content ul {line-height:25px;display:none; margin:0 30px;}
		
		.button_div {
	    	float: left;
	    	margin-left:60px;
	    	width: 300px;
	    }
	    
	    .button {
	    	float:left;
	    	margin-top:10px;
	    	width:300px;
	    	height:44px;
	    	cursor:pointer;
	    }
	    
	    .button span {
	    	float:left;
	    	margin-left:80px;
	    	margin-top:12px;
	    	font-family:Arial, Helvetica, sans-serif; 
	    	color: #fff;
	    	font-size: 15px;
	    	font-weight: bold;
	    }
	    
	    .shadow_font{
	    	position: absolute;
	    	top:30px;
	    	left:2px;
	    	display:block;
	    	font-weight:bold;
	    	color:#000;
	    	text-shadow: #fff 0px 0px 2px;
	    }
	    
	    .crash_font{
	    	position: absolute;
	    	top:62px;
	    	left:6px;
	    	display:block;
	    	font-weight:bold;
	    	color:red;
	    	text-shadow: #fff 0px 0px 2px;
	    }
	    
	    .right2 {
			float:left;
			width:49%;
			display: none;
		}
		
		.card_msg {
			float: left;
			margin-top: 20px;
			margin-left: 30px;
		}
		
		.card_img {
			float: left;
			margin-top:10px;
			width:130px;
			height:89px;
			background:url(${ctxStatic}/images/cardborder.png);
		}
		
		.card_img span {
			float: left;
		    width:130px;
		    margin-top:30px;
		    font-weight: bold;
		    font-size: 18px;
		    text-align:center;
		    font-family: Ericsson Capital TT;
		    color: #1f4e79;
		}
		
		.card_msg .card_btn {
			float: left;
			margin-left:10px;
			margin-top:10px;
			width: 130px;
			height: 84px;
		}
		
		.card_msg .card_list {
			float: left;
			margin-top:10px;
			width: 200px;
			height: 100px;
		}
		
		.card_msg ul {
			float:left;
			padding-left: 0px;
			margin-top:0px;
			list-style: none;
		} 
		.card_msg ul li {
			float: left;
			width: 250px;
			margin-left:8px;
			font-family:Arial, Helvetica, sans-serif; 
			font-weight: bold;
			color: #1f4e79;
			font-family:"微软雅黑";
			font-size: 12px;
		}
		
		.card_collapse {
			float: left;
			margin-top: 20px;
			margin-left: 30px;
			width: 470px;
		}
		
		.active {
			font-weight: bold;
		}
		
		.card_collapse div {
			font-size:13px;
			
		}
		
		.menutools {
			float:left;
			margin-top:20px;
			margin-left:60px;
			width:470px;
			height: 61px;
		}
		
		.menutools ul {
			float:left;
			padding-left: 0px;
			margin-top:0px;
			list-style: none;
		} 
		.menutools ul li {
			float: left;
			width: 140px;
		}
		
		.cyczTip {
			float:left;
			margin-top:20px;
			margin-left:60px;
			width:470px;
			height: 61px;
		}
		
		.menutools ul {
			float:left;
			padding-left: 0px;
			margin-top:0px;
			list-style: none;
		} 
		.menutools ul li {
			float: left;
			width: 140px;
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
			parent.$('#jbox-states').css("display","none");
			/* $.post('${ctx}/netconfig/tSaegw/getCardList', {
			       "nodeid":${nodeid}
			    }, function(data) {
			    	debugger;
			    	var saegw = eval(data);
					for(var i=0; i<saegw.length; i++) {
						var obj = saegw[i];
						$('#card_'+obj.slot).css("background-image","url(${ctxStatic}/images/card/SSR/"+obj.installedType+".png)");
					}
				}); */
			
			$('.submenu ul').on("mouseover","li", function(){
				$(this).addClass("active");
			});
			
			$('.submenu ul').on("mouseout","li", function(){
				$(this).removeClass("active");
			});
				
			var $li = $('#tab li');
            var $ul = $('#content ul');
            $li.click(function(){
                var $this = $(this);
                var $t = $this.index();
                $li.removeClass();
                $this.addClass('current');
                $ul.css('display','none');
                $ul.eq($t).css('display','block');
            });
            
          //显示弹框
    		$('#usuallyOperator').click(function(){
    			
    			showOperatorDlg('2', 'COMMONTEM');
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
    		
    		
            
            $("#selStatus").change(function(e){
				$('.cabinet td div').removeAttr("style");
				$('.cabinet td div').removeAttr("title");
				$('.cabinet table td span').css("display","none");
				$('.crash_font').css("display","none");
				//$('.shadow_font').css("display","none");
				var type = $(this).val();
				if(type == 0 || type == 1) {
					$('.cabinet table td span').css("display","block");
					$('.crash_font').css("display","block");
					//$('.shadow_font').css("display","block");
				}
				if(type == 2) {
				   var data = ${cpuMap};
				   for(var key in data) {
						 $("#card_"+key+"_c").css("background","green");
						 var h = parseInt(data[key]);
						 $("#card_"+key+"_c").css("height",(h*1.2)+"px");
						 $("#card_"+key+"_c").attr("title",key+":"+h);
				   }
				}
				if(type == 3) {
				   var data = ${memoryMap};
				   for(var key in data) {
					     $("#card_"+key+"_c").css("background","blue");
						 var h = parseInt(data[key]);
						 $("#card_"+key+"_c").css("height",(h*1.2)+"px");
						 $("#card_"+key+"_c").attr("title",key + ":" + h);
				   }
				}
				
				if(type == 4) {
				   var data = ${pgwUserCountMap};
				   for(var key in data) {
					     $("#card_"+key+"_c").css("background","black");
						 var h = parseInt(data[key]);
						 $("#card_"+key+"_c").css("height",((h/300000)*100*1.2)+"px");
						 $("#card_"+key+"_c").attr("title",key + ":" + h);
				   }
				}
				
				if(type == 5) {
				   var data = ${sgwUserCountMap};
				   for(var key in data) {
					     $("#card_"+key+"_c").css("background","gray");
						 var h = parseInt(data[key]);
						 $("#card_"+key+"_c").css("height",((h/300000)*100*1.2)+"px");
						 $("#card_"+key+"_c").attr("title",key + ":" + h);
				   }
				}
				
			})
			
		});
		
		
		function showOperatorDlg(type,temptype) {
			var icon = "";
			if('COMMONTEM' == temptype) {
				icon = 'mmeicon';
			} else if('EMERGENCYTEM' == temptype) {
				icon = 'yingjiicon';
			}
			$('.dialogContent ul li').remove();
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
						$('.dialogContent ul').append(li);
					}
					$('.dialogContent ul').append('<li class="addicon" onclick="showXmlFileUploadPage()"><span>新增操作</span></li>');
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
		
		function showCard(key) {
			$('.cabinet td').css("border",'');
			$('.cabinet td').css("border-top-color",'');
			$('.cabinet td').css("border-right-color",'');
			$('.cabinet td').css("border-bottom-color",'');
			$('.cabinet td').css("border-left-color",'');
			$('#'+key).css("border","solid 1px #000");
			$('.right').css("display","none");
			$('.right2').css("display","block");
			
			$('#cardInfo').empty();
			$('#memoryStatus').empty();
			$('#diskStatus').empty();
			var cardJson = ${cardMapJson};
			var item = cardJson[key];
			$('.card_img span').html(item.configuredType);
			$('#installedType').val(item.installedType);
			$('#cardLocal').val(item.slot);
			
			$('.card_list ul li').eq(0).html("板卡槽位：" + item.slot);
			$('.card_list ul li').eq(1).html("板卡类型：" + item.installedType);
			$('.card_list ul li').eq(2).html("运行状态：" + item.operationalState);
			$('.card_list ul li').eq(3).html("管理状态：" + item.adminState);
			if(item != null && item != "" && item != 'undefined') {
				var slot = item.slot;
				var netid = $('#netId').val();
				if(item.configuredType.indexOf("ssc") > -1) {
					var boardInfo = item.cardInfo.boardInfo;
					$('#cardInfo').append(boardInfo);
				}else if(item.configuredType.indexOf("port") > -1) {
					
					$.ajax({
						type : "POST",
						url : "${ctx}/netconfig/tSaegw/nodeCardStatus?netid="+netid+"&cardeq="+slot+"&type=port",
						dataType : "html",
						data : {},
						success:function(data){
							var msg = "";
							data = eval(data);
							for(var i=0; i<data.length; i++) {
								msg += data[i] + "</br>";
							}
							$('#cardInfo').append(msg);
						}
				   });
					
				} else if(item.slot.indexOf("RPSW") > -1) {
					$.ajax({
						type : "POST",
						url : "${ctx}/netconfig/tSaegw/nodeCardStatus?netid="+netid+"&cardeq="+slot+"&type=rpsw",
						dataType : "html",
						data : {},
						success:function(data){
							var msg = "";
							data = eval(data);
							for(var i=0; i<data.length; i++) {
								msg += data[i] + "</br>";
							}
							$('#cardInfo').append(msg);
						}
				   });
				}
				
				$.ajax({
					type : "POST",
					url : "${ctx}/netconfig/tSaegw/showNodeCardMemory?netid="+netid+"&cardeq="+slot,
					dataType : "html",
					data : {},
					success:function(data){
						var msg = "";
						data = eval(data);
						for(var i=0; i<data.length; i++) {
							msg += data[i] + "</br>";
						}
						$('#memoryStatus').append(msg);
					}
			   });
			
				$.ajax({
					type : "POST",
					url : "${ctx}/netconfig/tSaegw/showNodeCardDisk?netid="+netid+"&cardeq="+slot,
					dataType : "html",
					data : {},
					success:function(data){
						var msg = "";
						data = eval(data);
						for(var i=0; i<data.length; i++) {
							msg += data[i] + "</br>";
						}
						$('#diskStatus').append(msg);
					}
			   });
			}
			
		}
		
		function showDlg(type,title) {
			var haclass = $('#tab li').eq(1).hasClass("current");
			var searchType;
			if(haclass) {
				searchType=1;
			} else {
				searchType=0;
			}
			//var searchType = $("input[name='searchType']:checked").val();
			var dateTime = $("#dateTime").val();
			var netId = $("#netId").val();
			if(haclass && dateTime==''){
				$("#dateTime").focus();
                top.$.jBox.tip('请选择日期','warning');
                return false;
			}
			top.$.jBox.open("iframe:${ctx}/netconfig/tSaegw/showLog?netId="+netId+"&searchType="+searchType+"&cmdType="+type+"&dateTime="+dateTime, title,1200,600,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function refreshInfo() {
			loading('正在刷新，请稍等...');
			var netId = $('#netId').val();
			var nodeid = $('#nodeid').val();
			window.location.href= "${ctx}/netconfig/tSaegw/refresh?netId="+netId+"&nodeid=" + nodeid;
		}
		
		

		function showExcelTemp(tempId) {
			var netId = $('#netId').val();
			window.location.href= "${ctx}/netconfig/tMme/showExcelTemp?tempId="+tempId+"&netId=" + netId;
		}
		function showLockTemp(tempType) {
			var netId = $('#netId').val();
			var cardName = $('#installedType').val();
			var cardLocal = $('#cardLocal').val();
			if(cardName !=''){
				$.get('${ctx}/netconfig/tSaegw/checkLockTemp' ,{cardName: cardName,tempType:tempType}, function(data) {
					if (data == 'empty'){
						top.$.jBox.tip('未找到相关模板','warning');
			            return false;
					}else{
						var tempId = data;
						window.location.href= "${ctx}/netconfig/tSaegw/showLockTemp?tempId="+tempId+"&netId=" + netId+"&cardLocal=" + cardLocal;
					}
			    });
				
			}
		}
		
		function showTip(key,e) {
			var sel = $('#selStatus').val();
			var cardJson = ${cardMapJson};
			var item = cardJson[key];
			var html = "";
			if(sel == 0 || sel == 1) {  //如果有指示灯则显示板卡基本信息
				if(item != null) {
					var installedType = item.installedType;
					var operationalState = item.operationalState;
					var adminState = item.adminState;
					html += '<table cellspacing="0" cellpadding="0" style="float:left;margin-top:15px;margin-left:20px;font-size:12px;font-family:微软雅黑;" width="230">';
					html += '<tr height=12>';
					html += '<td>板卡类型</td>'; 
					html += '<td>'+installedType+'</td>';
					html += '</tr>';
					html += '<tr height=12>';
					html += '<td>运行状态</td>'; 
					html += '<td>'+operationalState+'</td>';
					html += '</tr>';
					html += '<tr height=12>';
					html += '<td>管理状态</td>'; 
					html += '<td>'+adminState+'</td>';
					html += '</tr>';
					html += '</table>';
				}
				
			} else {
				if(item != null && item.cardInfo != null) {
					var cpu = item.cardInfo.cpu;
					var memory = item.cardInfo.memory;
					var sgwUserCount = item.cardInfo.sgwUserCount;
					var pgwUserCount = item.cardInfo.pgwUserCount;
					html += '<table cellspacing="0" cellpadding="0" style="float:left;margin-top:15px;margin-left:20px;font-size:12px;font-family:微软雅黑;" width="230">';
					html += '<tr height=12>';
					html += '<td>CPU利用率</td>'; 
					html += '<td>'+cpu+'</td>';
					html += '</tr>';
					html += '<tr height=12>';
					html += '<td>内存利用率</td>'; 
					html += '<td>'+memory+'</td>';
					html += '</tr>';
					html += '<tr height=12>';
					html += '<td>SGW用户数</td>'; 
					html += '<td>'+sgwUserCount+'</td>';
					html += '</tr>';
					html += '<tr height=12>';
					html += '<td>PGW用户数</td>'; 
					html += '<td>'+pgwUserCount+'</td>';
					html += '</tr>';
					html += '</table>';
				}
			}
			
			$('#tip').css("display","block");
			var scrollHeight = $(document).scrollTop();
			$('#tip').css("left",e.clientX+10);
			$('#tip').css("top",e.clientY+5+scrollHeight);
			$("#tip").html(html);
		}
		function closeTip() {
			$('#tip').css("display","none");
		}
		
		function showExcelTemp(tempId) {
			window.location.href= "${ctx}/netconfig/tMme/showExcelTemp?tempId="+tempId;
		}
		
		function showXmlFileUploadPage() {
			window.location.href= "${ctx}/netconfig/tTpXmlFileUpload/form";
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
						showOperatorDlg('2', 'EMERGENCYTEM');
					}
				}
				
		   });
		}
		
		function redirectNetParam(){
			var netId = $('#netId').val();
			window.location.href= "${ctx}/paramconfig/netParamModify/redirectNetParam?netId="+netId;
		}
	</script>
</head>
<body>
	<input id="netId" type="hidden" value="${netId}">
	<input id="nodeid" type="hidden" value="${nodeid}">
	<input id="cardLocal" type="hidden">
	<input type="hidden" id="installedType">
	<div class="main">
		<div class="left">
			<div class="cabinet">
			
				<table class="t1">
					<tr>
						<td id="card_1" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_1==null ?'dummy':cardMap.card_1.installedType }.png) no-repeat bottom;" onclick="showCard('card_1');" onmousemove="showTip('card_1',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_1.color!=null }">
								<span id="card_1_s" style='background: <c:out value="${cardMap.card_1.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_1.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_1.cardInfo.cardType }"></c:out></p>
							<div id="card_1_c"></div>
						</td>
						<td id="card_2" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_2==null ?'dummy':cardMap.card_2.installedType }.png) no-repeat bottom;" onclick="showCard('card_2');" onmousemove="showTip('card_2',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_2.color!=null }">
								<span id="card_2_s" style='background: <c:out value="${cardMap.card_2.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_2.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_2.cardInfo.cardType }"></c:out></p>
							<div id="card_2_c"></div>
						</td>
						<td id="card_3" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_3==null ?'dummy':cardMap.card_3.installedType }.png) no-repeat bottom;" onclick="showCard('card_3');" onmousemove="showTip('card_3',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_3.color!=null }">
								<span id="card_3_s" style='background: <c:out value="${cardMap.card_3.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_3.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_3.cardInfo.cardType }"></c:out></p>
							<div id="card_3_c"></div>
						</td>
						<td id="card_4" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_4==null ?'dummy':cardMap.card_4.installedType }.png) no-repeat bottom;" onclick="showCard('card_4');" onmousemove="showTip('card_4',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_4.color!=null }">
								<span id="card_4_s" style='background: <c:out value="${cardMap.card_4.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_4.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_4.cardInfo.cardType }"></c:out></p>
							<div id="card_4_c"></div>
						</td>
						<td id="card_5" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_5==null ?'dummy':cardMap.card_5.installedType }.png) no-repeat bottom;" onclick="showCard('card_5');" onmousemove="showTip('card_5',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_5.color!=null }">
								<span id="card_5_s" style='background: <c:out value="${cardMap.card_5.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_5.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_5.cardInfo.cardType }"></c:out></p>
							<div id="card_5_c"></div>
						</td>
						<td id="card_SW1" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_SW1==null ?'dummy':cardMap.card_SW1.installedType }.png) no-repeat bottom;" onmousemove="showTip('card_SW1',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_SW1.color!=null }">
								<span id="card_SW1_s" style='background: <c:out value="${cardMap.card_SW1.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_SW1.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_SW1.cardInfo.cardType }"></c:out></p>
						</td>
						<td id="card_SW2" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_SW2==null ?'dummy':cardMap.card_SW2.installedType }.png) no-repeat bottom;" onmousemove="showTip('card_SW2',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_SW2.color!=null }">
								<span id="card_SW2_s" style='background: <c:out value="${cardMap.card_SW2.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_SW2.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_SW2.cardInfo.cardType }"></c:out></p>
						</td>
						<td id="card_6" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_6==null ?'dummy':cardMap.card_6.installedType }.png) no-repeat bottom;" onclick="showCard('card_6');" onmousemove="showTip('card_6',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_6.color!=null }">
								<span id="card_6_s" style='background: <c:out value="${cardMap.card_6.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_6.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_6.cardInfo.cardType }"></c:out></p>
							<div id="card_6_c"></div>
						</td>
						<td id="card_7" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_7==null ?'dummy':cardMap.card_7.installedType }.png) no-repeat bottom;" onclick="showCard('card_7');" onmousemove="showTip('card_7',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_7.color!=null }">
								<span id="card_7_s" style='background: <c:out value="${cardMap.card_7.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_7.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_7.cardInfo.cardType }"></c:out></p>
							<div id="card_7_c"></div>
						</td>
						<td id="card_8" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_8==null ?'dummy':cardMap.card_8.installedType }.png) no-repeat bottom;" onclick="showCard('card_8');" onmousemove="showTip('card_8',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_8.color!=null }">
								<span id="card_8_s" style='background: <c:out value="${cardMap.card_8.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_8.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_8.cardInfo.cardType }"></c:out></p>
							<div id="card_8_c"></div>
						</td>
						<td id="card_9" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_9==null ?'dummy':cardMap.card_9.installedType }.png) no-repeat bottom;" onclick="showCard('card_9');" onmousemove="showTip('card_9',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_9.color!=null }">
								<span id="card_9_s" style='background: <c:out value="${cardMap.card_9.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_9.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_9.cardInfo.cardType }"></c:out></p>
							<div id="card_9_c"></div>
						</td>
						<td id="card_10" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_10==null ?'dummy':cardMap.card_10.installedType }.png) no-repeat bottom;" onclick="showCard('card_10');" onmousemove="showTip('card_10',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_10.color!=null }">
								<span id="card_10_s" style='background: <c:out value="${cardMap.card_10.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_10.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_10.cardInfo.cardType }"></c:out></p>
							<div id="card_10_c"></div>
						</td>
					</tr>
					<tr>
						<td id="card_SW3" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_SW3==null ?'dummy':cardMap.card_SW3.installedType }.png) no-repeat bottom;" onmousemove="showTip('card_SW3',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_SW3.color!=null }">
								<span id="card_SW3_s" style='background: <c:out value="${cardMap.card_SW3.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_SW3.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_SW3.cardInfo.cardType }"></c:out></p>
						</td>
						<td id="card_SW4" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_SW4==null ?'dummy':cardMap.card_SW4.installedType }.png) no-repeat bottom;" onmousemove="showTip('card_SW4',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_SW4.color!=null }">
								<span id="card_SW4_s" style='background: <c:out value="${cardMap.card_SW4.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_SW4.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_SW4.cardInfo.cardType }"></c:out></p>
						</td>
					</tr>
				</table>
				<table class="t2">
					<tr>
						<td id="card_11" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_11==null ?'dummy':cardMap.card_11.installedType }.png) no-repeat bottom;" onclick="showCard('card_11');" onmousemove="showTip('card_11',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_11.color!=null }">
								<span id="card_11_s" style='background: <c:out value="${cardMap.card_11.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_11.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_11.cardInfo.cardType }"></c:out></p>
							<div id="card_11_c"></div>
						</td>
						<td id="card_12" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_12==null ?'dummy':cardMap.card_12.installedType }.png) no-repeat bottom;" onclick="showCard('card_12');" onmousemove="showTip('card_12',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_12.color!=null }">
								<span id="card_12_s" style='background: <c:out value="${cardMap.card_12.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_12.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_12.cardInfo.cardType }"></c:out></p>
							<div id="card_12_c"></div>
						</td>
						<td id="card_13" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_13==null ?'dummy':cardMap.card_13.installedType }.png) no-repeat bottom;" onclick="showCard('card_13');" onmousemove="showTip('card_13',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_13.color!=null }">
								<span id="card_13_s" style='background: <c:out value="${cardMap.card_13.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_13.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_13.cardInfo.cardType }"></c:out></p>
							<div id="card_13_c"></div>
						</td>
						<td id="card_14" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_14==null ?'dummy':cardMap.card_14.installedType }.png) no-repeat bottom;" onclick="showCard('card_14');" onmousemove="showTip('card_14',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_14.color!=null }">
								<span id="card_14_s" style='background: <c:out value="${cardMap.card_14.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_14.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_14.cardInfo.cardType }"></c:out></p>
							<div id="card_14_c"></div>
						</td>
						<td id="card_15" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_15==null ?'dummy':cardMap.card_15.installedType }.png) no-repeat bottom;" onclick="showCard('card_15');" onmousemove="showTip('card_15',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_15.color!=null }">
								<span id="card_15_s" style='background: <c:out value="${cardMap.card_15.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_15.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_15.cardInfo.cardType }"></c:out></p>
							<div id="card_15_c"></div>
						</td>
						<td id="card_ALSW1" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_ALSW1==null ?'dummy':cardMap.card_ALSW1.installedType }.png) no-repeat bottom;" onmousemove="showTip('card_ALSW1',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_ALSW1.color!=null }">
								<span id="card_ALSW1_s" style='background: <c:out value="${cardMap.card_ALSW1.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_ALSW1.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_ALSW1.cardInfo.cardType }"></c:out></p>
						</td>
						<td id="card_ALSW2" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_ALSW2==null ?'dummy':cardMap.card_ALSW2.installedType }.png) no-repeat bottom;" onmousemove="showTip('card_ALSW2',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_ALSW2.color!=null }">
								<span id="card_ALSW2_s" style='background: <c:out value="${cardMap.card_ALSW2.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_ALSW2.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_ALSW2.cardInfo.cardType }"></c:out></p>
						</td>
						<td id="card_16" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_16==null ?'dummy':cardMap.card_16.installedType }.png) no-repeat bottom;" onclick="showCard('card_16');" onmousemove="showTip('card_16',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_16.color!=null }">
								<span id="card_16_s" style='background: <c:out value="${cardMap.card_16.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_16.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_16.cardInfo.cardType }"></c:out></p>
							<div id="card_16_c"></div>
						</td>
						<td id="card_17" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_17==null ?'dummy':cardMap.card_17.installedType }.png) no-repeat bottom;" onclick="showCard('card_17');" onmousemove="showTip('card_17',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_17.color!=null }">
								<span id="card_17_s" style='background: <c:out value="${cardMap.card_17.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_17.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_17.cardInfo.cardType }"></c:out></p>
							<div id="card_17_c"></div>
						</td>
						<td id="card_18" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_18==null ?'dummy':cardMap.card_18.installedType }.png) no-repeat bottom;" onclick="showCard('card_18');" onmousemove="showTip('card_18',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_18.color!=null }">
								<span id="card_18_s" style='background: <c:out value="${cardMap.card_18.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_18.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_18.cardInfo.cardType }"></c:out></p>
							<div id="card_18_c"></div>
						</td>
						<td id="card_19" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_19==null ?'dummy':cardMap.card_19.installedType }.png) no-repeat bottom;" onclick="showCard('card_19');" onmousemove="showTip('card_19',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_19.color!=null }">
								<span id="card_19_s" style='background: <c:out value="${cardMap.card_19.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_19.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_19.cardInfo.cardType }"></c:out></p>
							<div id="card_19_c"></div>
						</td>
						<td id="card_20" width=18 rowspan="2" style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_20==null ?'dummy':cardMap.card_20.installedType }.png) no-repeat bottom;" onclick="showCard('card_20');" onmousemove="showTip('card_20',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_20.color!=null }">
								<span id="card_20_s" style='background: <c:out value="${cardMap.card_20.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_20.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_20.cardInfo.cardType }"></c:out></p>
							<div id="card_20_c"></div>
						</td>
					</tr>
					<tr>
						<td id="card_RPSW1" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_RPSW1==null ?'dummy':cardMap.card_RPSW1.installedType }.png) no-repeat bottom;" onclick="showCard('card_RPSW1');" onmousemove="showTip('card_RPSW1',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_RPSW1.color!=null }">
								<span id="card_RPSW1_s" style='background: <c:out value="${cardMap.card_RPSW1.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_RPSW1.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_RPSW1.cardInfo.cardType }"></c:out></p>
						</td>
						<td id="card_RPSW2" width=18  style="background:url(${ctxStatic}/images/card/SSR/${cardMap.card_RPSW2==null ?'dummy':cardMap.card_RPSW2.installedType }.png) no-repeat bottom;" onclick="showCard('card_RPSW2');" onmousemove="showTip('card_RPSW2',event)" onmouseout="closeTip()">
							<c:if test="${cardMap.card_RPSW2.color!=null }">
								<span id="card_RPSW2_s" style='background: <c:out value="${cardMap.card_RPSW2.color }"></c:out>; '></span>
							</c:if>
							<p class="crash_font"><c:out value="${cardMap.card_RPSW2.crash }"></c:out></p>
							<p class="shadow_font"><c:out value="${cardMap.card_RPSW2.cardInfo.cardType }"></c:out></p>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="right">
		<div style="float: right; "><p style="float: right;line-height: 28px"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 5px;float: left;margin-top: -2px;color: #1f4e79;font-weight: bold;">返回</span></a></p></div>
			<div class="net_node_msg">
				<div class="saegw_icon">
					<img src="${ctxStatic}/images/saegw_icon.png" alt="saegw" width="78" height="95" style="margin-left: 22px"/>
					<p>${netParam.netName}</p>
				</div>
				<div class="saegw_node_list">
					<ul>
						<li>节点软件版本: ${netParam.sw}</li>
						<li>节点硬件版本 : ${netParam.hw}</li>
						<li>节点维护IP地址: ${netParam.oamIpAddress}</li>
					</ul>
				</div>
			</div>
			<div class="card_model">
				<img src="${ctxStatic}/images/cardstatus.png" width="45" height="47">
				<p>板卡状态<br/>显示模式</p>
				<select id="selStatus">
					<option value="0">--------请选择-------</option>
					<option value="1">指示灯</option>
					<option value="2">CPU</option>
					<option value="3">内存</option>
					<option value="4">PGW用户数</option>
					<option value="5">SGW用户数</option>
				</select>
				<input type="image" src="${ctxStatic}/images/flush.png" width="55" height="32" onclick="refreshInfo()">
			</div>
			<%-- <div class="menubar">
				<div class="menuitem">
					<div><img src="${ctxStatic}/images/zdymb.png" width="230" height="35" /></div>
					<div class="submenu">
			            <ul>
			            	<c:forEach items="${excelList }" var="item">
			            		<li onclick="showExcelTemp('${item.id }')">${item.name }</li>
			            	</c:forEach>
			            </ul>
		        	</div>
				</div>
		        <div style="float: left;margin-left: 30px"><input type="image" src="${ctxStatic}/images/pzgl.png" width="180" height="40" onclick="redirectNetParam()"/></div>
			</div> --%>
			
			<div class="menutools">
				<ul>
					<li><input type="image" src="${ctxStatic}/images/xcycz.png" id="usuallyOperator"/></li>
					<li style="margin-left:24px;"><input type="image" src="${ctxStatic}/images/xyjcz.png" id="emergencyOperator"/></li>
					<li style="margin-left:24px;"><input type="image" src="${ctxStatic}/images/xpzgl.png"  onclick="redirectNetParam()"/></li>
				</ul>
			</div>
			
			<div class="tab_div">
				<ul id="tab">
			        <li class="current">当前</li>
			        <li>历史</li>
			    </ul>
			    <div id="content">
			        <ul style="display:block;">
			            
			        </ul>
			        <ul>
			            <input id="dateTime" name="dateTime" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
	                 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			        </ul>
			    </div>
			    <div class="button_div">
			    	<div class="button" style="background:url(${ctxStatic}/images/wyztxx.png);"  onclick="showDlg('status','节点状态信息')">
			    		<span>网元状态信息查询</span>
			    	</div>
			    	<div class="button" style="background:url(${ctxStatic}/images/wyxnzb.png);" onclick="showDlg('kpi','节点性能指标')">
			    		<span>网元性能指标查询</span>
			    	</div>
			    	<div class="button" style="background:url(${ctxStatic}/images/wygjxx.png);" onclick="showDlg('alarm','节点当前告警')">
			    		<span>网元告警信息查询</span>
			    	</div>
			    	<div class="button" style="background:url(${ctxStatic}/images/wyjkjc.png);" onclick="showDlg('health','健康检查结果')">
			    		<span>网元健康检查结果</span>
			    	</div>
			    	<div class="button" style="background:url(${ctxStatic}/images/wypzxx.png);" onclick="showDlg('config','节点当前配置')">
			    		<span>网元配置信息查询</span>
			    	</div>
			    	
			    </div>
			</div>
		</div>
		<div class="right2">
		<div style="float: right; "><p style="float: right;line-height: 28px"><a href="javascript:" onclick="history.go(0);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 5px;float: left;margin-top: -2px;color: #1f4e79;font-weight: bold;">返回</span></a></p></div>
			<div class="card_msg">
				<div class="card_img"><span></span></div>
				<div class="card_btn">
					<input type="image" src="${ctxStatic}/images/icon-lock.png" width="130" height="29" onclick="showLockTemp('lock')">
					<input type="image" src="${ctxStatic}/images/icon-unlock.png" width="130" height="29" onclick="showLockTemp('unlock')">
					<input type="image" src="${ctxStatic}/images/icon-restart.png" width="130" height="29" onclick="showLockTemp('reboot')">
				</div>
				<div class="card_list">
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div>
			 <div class="card_collapse" data-collapse="accordion">
			    <h3>板卡基本状态</h3>
			    <div id="cardInfo"></div>
			    <h3>板卡内存状态</h3>
			    <div id="memoryStatus"></div>
			    <h3>板卡磁盘状态</h3>
			    <div id="diskStatus"></div>
			  </div>
		</div>
		
	</div>
	<div id="tip" style="display:none ;position: absolute;background:url(${ctxStatic}/images/card.png); width:286px;height:200px"></div>
	
	<div id="dialogBg"></div>
	<div id="dialog" class="animated">
		<div class="dialogTop">
			<span style="float: left; font-size: 18px;font-weight: bold;margin-left: 170px;font-family:微软雅黑">SAEGW<span class="keyword"></span>维护操作</span>
			<a href="javascript:;" class="claseDialogBtn"><img src="${ctxStatic}/images/closebtn.png" border="0" width="30" height="29"></a>
		</div>
		<div class="dialogContent">
			<ul>
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