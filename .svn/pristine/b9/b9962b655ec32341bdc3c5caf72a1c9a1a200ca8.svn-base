<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>MME节点管理</title>
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
			background:url(${ctxStatic}/images/card/MKVIII/MMEMKVIIICAB.png);
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
		
		.mme_icon {
			float: left;
		}
		
		.mme_icon p {
			font-family:Arial, Helvetica, sans-serif; 
			font-size:14px; 
			margin-left:0px;
			margin-top: 0px;
			font-weight: bold;
			color: #44546a;
		}
		
		.mme_node_list {
			float: left;
			margin-left:20px;
			margin-top:0px;
			width: 290px;
		}
		
		.mme_node_list ul {
			float:left;
			padding-left: 0px;
			margin-top:0px;
			list-style: none;
		} 
		.mme_node_list ul li {
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
	    	font-size:12px;
	    	margin-top: -1px;
	    	margin-left: 2px;
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
	    
	    .cabinet table, .cabinet_MKX table {
	    	float: left;
	    	margin-left: 146px;
	    	width: 240px;
	    	height: 102px;
	    	border-collapse:collapse;
	    }
	    
	    .cabinet .t1, .cabinet_MKX .t1 {
	    	margin-top: 87px;
	    	
	    }
	    
	    .cabinet .t2, .cabinet_MKX .t2  {
	    	margin-top: 100px;
	    }
	    
	    .cabinet .t3, .cabinet_MKX .t3  {
	    	margin-top: 102px;
	    }
	    
	    .cabinet td, .cabinet_MKX td {
	    	position:relative;
	    	cursor: pointer;
	    }
	    
	     .cabinet td div, .cabinet_MKX td div {
	     	position: absolute;
	     	bottom:0px; 
	     	width: 100%; 
	    	filter:alpha( Opacity=50);-moz-opacity:0.5;opacity:0.5;
	    }
	    
	    .cabinet td span, .cabinet_MKX td span {
	     	position: absolute;
	     	top:28px; 
	     	display: block; 
	     	width: 7px; 
	     	height: 7px;
	     	border:solid 1px #ffffff;
	     	border-radius:50%;
	     	-moz-border-radius:50%;
	     	-webkit-border-radius:50%;
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
			margin-top:15px;
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
	    
	    .cabinet_MKX {
	    	float:right;
			margin-right:85px;
			width:450px;
			height:1042px;
			background:url(${ctxStatic}/images/card/MKX/MMEMKXCAB.png);
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
			$("#selStatus").change(function(e){
				$('.cabinet td div, .cabinet_MKX td div').removeAttr("style");
				$('.cabinet td div, .cabinet_MKX td div').removeAttr("title");
				$('.cabinet table td span, .cabinet_MKX table td span').css("display","none");
				var type = $(this).val();
				if(type == 0 || type == 1) {
					$('.cabinet table td span, .cabinet_MKX table td span').css("display","block");
				}
				
				if(type == 2) {
				   var data = ${cardMaxCpuMap};
				   for(var key in data) {
					   	 var key1 = 'm'+key.replace('.','_');
						 $("#"+key1).css("background","green");
						 var h = parseInt(data[key]);
						 $("#"+key1).css("height",(h*1.2)+"px");
						 $("#"+key1).attr("title",key+":"+h);
				   }
				}
				if(type == 3) {
				   var data = ${memoryMap};
				   for(var key in data) {
						 $("#"+key).css("background","blue");
						 var h = parseInt(data[key]);
						 $("#"+key).css("height",(h*1.2)+"px");
						 var k = key.substr(1,(key.length-1));
						 k = k.replace('_','.');
						 $("#"+key).attr("title",k + ":" + h);
				   }
				}
				if(type == 4) {
					var data = ${cardUserCount};
					for(var key in data) {
						 var key1 = 'm'+key.replace('.','_');
						 $("#"+key1).css("background","black");
						 var h = parseInt(data[key]);
						 var t = parseInt((h/60000)*100*1.2);
						 $("#"+key1).css("height",t+"px");
						 $("#"+key1).attr("title",key+":"+h);
					}
				}
				
			})
			
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
    			/* $('.dialogContent ul li:not(:last)').removeClass();
				$('.dialogContent ul li:not(:last)').addClass("mmeicon"); */
				showOperatorDlg('1', 'COMMONTEM');
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
			$('.cabinet td, .cabinet_MKX td').css("border",'');
			$('.cabinet td .cabinet_MKX td').css("border-top-color",'');
			$('.cabinet td .cabinet_MKX td').css("border-right-color",'');
			$('.cabinet td .cabinet_MKX td').css("border-bottom-color",'');
			$('.cabinet td .cabinet_MKX td').css("border-left-color",'');
			$('#'+key+'_c').css("border","solid 1px #000");
			$('.right').css("display","none");
			$('.right2').css("display","block");
			var cardmsg = $('#card_'+key).val();
			var cardItem = cardmsg.split(",");
			var eq = cardItem[0];
			var ProdName = cardItem[7];
			var OperState = cardItem[2];
			var adminState = cardItem[1];
			var PowerState = cardItem[3];
			$('.card_list ul li').eq(0).html("板卡槽位：" + eq);
			$('.card_list ul li').eq(1).html("板卡类型：" + ProdName);
			$('.card_list ul li').eq(2).html("板卡运行状态：" + OperState);
			$('.card_list ul li').eq(3).html("板卡管理状态：" + adminState);
			$('.card_list ul li').eq(4).html("板卡电源状态：" + PowerState);
			$('.card_img span').html(ProdName);
			
			//展示板卡基本状态信息
			var html = showCpuCardInfo(key,false);  
			$('#cardInfo').empty();
			$('#cardInfo').append(html);
			
			//显示板卡倒换启动记录信息
			var netid = $('#netId').val();
			var cardInfo = $('#cardInfo_'+key).val();
			var cardInfoItem = cardInfo.split(",");
			var eq = cardInfoItem[0];
			if(eq != null && eq != "") {
				$.ajax({
					type : "POST",
					url : "${ctx}/netconfig/tMme/showNodeCardSwitchInfo?netid="+netid+"&cardeq="+eq,
					dataType : "html",
					data : {},
					success:function(data){
						debugger;
						data = eval(data);
						if(data == "") {
							data = "暂无记录";
						}
						$('#cardStartInfo').empty();
						$('#cardStartInfo').append(data);
					}
			   });
			}
			//window.location.href= "${ctx}/netconfig/tMme/showLog";
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
			top.$.jBox.open("iframe:${ctx}/netconfig/tMme/showLog?netId="+netId+"&searchType="+searchType+"&cmdType="+type+"&dateTime="+dateTime, title,1200,600,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function showTip(key,e) {
			var sel = $('#selStatus').val();  //0,1 指示灯    2,3,4 cpu,内存,用户数
			var html;
			if(sel == 0 || sel == 1) {  //如果有指示灯则显示板卡基本信息
				html = showCardInfo(key);
			} else {
				html = showCpuCardInfo(key,true);
			}
			$('#tip').css("display","block");
			var scrollHeight = $(document).scrollTop();
			$('#tip').css("left",e.clientX+10);
			$('#tip').css("top",e.clientY+5+scrollHeight);
			$("#tip").html(html);
		}
		
		function showCardInfo(key) {
			var cardmsg = $('#card_'+key).val();
			var cardItem = cardmsg.split(",");
			var eq = cardItem[0];
			var adminState = cardItem[1];
			var OperState = cardItem[2];
			var PowerState = cardItem[3];
			var Revision = cardItem[4];
			var BootROM = cardItem[5];
			var ProdNo = cardItem[6];
			var ProdName = cardItem[7];
			var SerialNo = cardItem[8];
			var FSBRole = cardItem[9];
		    var html = '<table cellspacing="0" cellpadding="0" style="float:left;margin-top:15px;margin-left:20px;font-size:12px;font-family:微软雅黑;" width="230">' + 
						  '<tr height=12>' + 
						  '<td>板卡槽位</td>' + 
						  '<td>'+eq+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡管理状态</td>' + 
						  '<td>'+adminState+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡运行状态</td>' + 
						  '<td>'+OperState+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡电源状态</td>' + 
						  '<td>'+PowerState+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡版本</td>' + 
						  '<td>'+Revision+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>ROM版本</td>' + 
						  '<td>'+BootROM+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡产品号</td>' + 
						  '<td>'+ProdNo+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡类型</td>' + 
						  '<td>'+ProdName+'</td>' +
						  '</tr>' +
						  '<tr height=12>' + 
						  '<td>板卡序列号</td>' + 
						  '<td>'+SerialNo+'</td>' +
						  '</tr>';
						  if('-' != FSBRole) {
							  html += '<tr height=12>';
							  html += '<td>板卡主备</td>';
							  html += '<td>'+FSBRole+'</td>';
							  html += '</tr>';
						  }
			html += '</table>';
			return html;
		}
		
		function showCpuCardInfo(key,ismouseover) {
			var cardInfo = $('#cardInfo_'+key).val();
			var cardInfoItem = cardInfo.split(",");
			var eq = cardInfoItem[0];
			var prodName = cardInfoItem[1];
			var cpuAP = cardInfoItem[2];
			var cpuDP = cardInfoItem[3];
			var cpuNCB = cardInfoItem[4];
			var cpuRP = cardInfoItem[5];
			var cpuSS7FENB = cardInfoItem[6];
			var cpuSS7SCTPDP = cardInfoItem[7];
			var userCount2G = cardInfoItem[8];
			var userCount3G = cardInfoItem[9];
			var userCount4G = cardInfoItem[10];
			
			var cardUserCountMap = ${cardUserCount};
			var cardUserCount = 0;
			for(var cardKey in cardUserCountMap) {
				 var ck = 'm'+cardKey.replace('.','_');
				 if(key == ck) {
					 cardUserCount = cardUserCountMap[cardKey];
				 }
		   	}
			
			var memoryMap = ${memoryMap};
			var memoryRate = 0;
		    for(var memoryKey in memoryMap) {
		    	var mk = memoryKey.replace('.','_');
		    	if(key == mk) {
		    		memoryRate = memoryMap[memoryKey];
		    	}
		    }
			
		    	if(ismouseover) {
					var html = '<table cellspacing="0" cellpadding="0" style="float:left;margin-top:15px;margin-left:20px;font-size:12px;font-family:微软雅黑;" width="230">';
		    	} else {
		    		var html = '<table cellspacing="0" cellpadding="0" style="margin-top:5px;margin-bottom: 5px;margin-left:20px;font-size:12px;font-family:微软雅黑;" width="230">';
		    	}
				html += '<tr><td>板卡槽位</td><td>'+eq+'</td></tr>';
				html += '<tr><td>板卡类型</td><td>'+prodName+'</td></tr>';
				if(cpuAP != '0' && cpuAP != '') {
					html += '<tr  height=12><td>AP CPU</td><td>'+cpuAP+'</td></tr>';
				}
				if(cpuDP != '0' && cpuDP != '') {
					html += '<tr  height=12><td>DP CPU</td><td>'+cpuDP+'</td></tr>';
				}
				if(cpuNCB != '0' && cpuNCB != '') {
					html += '<tr  height=12><td>NCB CPU</td><td>'+cpuNCB+'</td></tr>';
				}
				if(cpuRP != '0' && cpuRP != '') {
					html += '<tr  height=12><td>RP CPU</td><td>'+cpuRP+'</td></tr>';
				}
				if(cpuSS7FENB != '0' && cpuSS7FENB != '') {
					html += '<tr  height=12><td>SS7_FE_NB</td><td>'+cpuSS7FENB+'</td></tr>';
				}
				if(cpuSS7SCTPDP != '0' && cpuSS7SCTPDP != '') {
					html += '<tr  height=12><td>SS7_SCTP_DP</td><td>'+cpuSS7SCTPDP+'</td></tr>';
				}
				if(memoryRate != '0' && memoryRate != '') {
					html += '<tr  height=12><td>内存利用率</td><td>'+memoryRate+'</td></tr>';
				}
				if(userCount2G != '0' && userCount2G != '') {
					html += '<tr  height=12><td>2G用户数</td><td>'+userCount2G+'</td></tr>';
				}
				if(userCount3G != '0' && userCount3G != '') {
					html += '<tr  height=12><td>3G用户数</td><td>'+userCount3G+'</td></tr>';
				}
				if(userCount4G != '0' && userCount4G != '') {
					html += '<tr  height=12><td>4G用户数</td><td>'+userCount4G+'</td></tr>';
				}
				if(cardUserCount != '0') {
					html += '<tr  height=12><td>总用户数</td><td>'+cardUserCount+'</td></tr>'; 
				}
				
				return html;
		}
		
		function closeTip() {
			$('#tip').css("display","none");
		}
		function refreshInfo() {
			loading('正在刷新，请稍等...');
			var netId = $('#netId').val();
			var nodeid = $('#nodeid').val();
			window.location.href= "${ctx}/netconfig/tMme/refresh?netId="+netId+"&nodeid=" + nodeid;
		}
		
		function showExcelTemp(tempId) {
			var netId = $('#netId').val();
			window.location.href= "${ctx}/netconfig/tMme/showExcelTemp?tempId="+tempId+"&netId=" + netId;
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
						
						showOperatorDlg('1', 'EMERGENCYTEM');
						/* $('.dialogContent ul li:not(:last)').removeClass();
						$('.dialogContent ul li:not(:last)').addClass("yingjiicon");
						$('.keyword').html("应急");
						$('#dialogBg').fadeIn(300);
						$('#dialog').removeAttr('class').addClass('animated bounceIn').fadeIn(); */
					}
				}
		   });
		}
		
		function showLockTemp(tempType) {
			var netId = $('#netId').val();
			var cardLocal = $('.card_list ul li').eq(0).html();
			if(cardLocal !='' && cardLocal.indexOf("板卡槽位：") != -1){
				cardLocal = cardLocal.substr(5);
			}
			var cardFullName = $('.card_list ul li').eq(1).html();
			if(cardFullName !='' && cardFullName.indexOf("板卡类型：") != -1){
				var cardName = cardFullName.substr(5);
				if(cardName !=''){
					$.get('${ctx}/netconfig/tMme/checkLockTemp' ,{cardName: cardName,tempType:tempType}, function(data) {
						if (data == 'empty'){
							top.$.jBox.tip('未找到相关模板','warning');
				            return false;
						}else{
							var tempId = data;
							window.location.href= "${ctx}/netconfig/tMme/showLockTemp?tempId="+tempId+"&netId=" + netId+"&cardLocal=" + cardLocal;
						}
				    });
				}
			}
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
	<div class="main">
		<div class="left">
			<c:if test="${hwtype=='MKVIII' }">
			<div class="cabinet">
				<table class="t1">
					<tr>
						 <c:forEach items="${card.m1}" var="item">
							<input id="card_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.adminState },${item.value.operState },${item.value.powerState },${item.value.revision },${item.value.bootRom },${item.value.prodNo },${item.value.prodName },${item.value.serialNo },${item.value.fsbRole }" />
							<input id="cardInfo_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.prodName },${item.value.cardInfo.cpuAP },${item.value.cardInfo.cpuDP },${item.value.cardInfo.cpuNCB },${item.value.cardInfo.cpuRP },${item.value.cardInfo.cpuSS7FENB },${item.value.cardInfo.cpuSS7SCTPDP },${item.value.cardInfo.userCount2G },${item.value.cardInfo.userCount3G },${item.value.cardInfo.userCount4G }"/>
							<c:set var="color" scope="page" value=""></c:set>
							<c:if test="${item.value.operState=='Down' }">
								<c:set var="color" value="red"></c:set>
							</c:if>
							<c:if test="${(item.value.adminState=='Unblocked' && item.value.operState=='Up') || (item.value.adminState=='Foreign' && item.value.operState=='Up') }">
								<c:set var="color" value="green"></c:set>
							</c:if>
							<c:if test="${item.value.adminState=='Blocked' && item.value.operState=='Up'}">
								<c:set var="color" value="#ffd303"></c:set>
							</c:if>
							<c:set var="width" scope="page" value=""></c:set>
							<c:choose>
								<c:when test="${item.key=='m1_25' || item.key=='m1_26' || item.key=='m1_27' || item.key=='m1_28'}">
									<c:set var="width" value="9"></c:set>
								</c:when> 
								<c:otherwise>
									<c:set var="width" value="17"></c:set>
								</c:otherwise>
							</c:choose>
							<td id="${item.key }_c" width="<c:out value="${width }"></c:out>" height="165" style="background:url(${ctxStatic}/images/card/MKVIII/${item.value==null ?'DUMMY':item.value.prodName}.png) no-repeat bottom;" onclick="showCard('${item.key }');" onmousemove="showTip('${item.key }',event)" onmouseout="closeTip()">
								<c:if test="${color!='' }">
									<span id="${item.key }_s" style='background: <c:out value="${color }"></c:out>; '></span>
								</c:if>
								<div id="${item.key }"></div>
							</td>
						</c:forEach>
					</tr>
				</table>
				
				<table class="t2">
					 <tr>
						<c:forEach items="${card.m2}" var="item">
							<input id="card_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.adminState },${item.value.operState },${item.value.powerState },${item.value.revision },${item.value.bootRom },${item.value.prodNo },${item.value.prodName },${item.value.serialNo },${item.value.fsbRole }">
							<input id="cardInfo_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.prodName },${item.value.cardInfo.cpuAP },${item.value.cardInfo.cpuDP },${item.value.cardInfo.cpuNCB },${item.value.cardInfo.cpuRP },${item.value.cardInfo.cpuSS7FENB },${item.value.cardInfo.cpuSS7SCTPDP },${item.value.cardInfo.userCount2G },${item.value.cardInfo.userCount3G },${item.value.cardInfo.userCount4G }"/>
							<c:set var="color1" scope="page" value=""></c:set>
							<c:if test="${item.value.operState=='Down' }">
								<c:set var="color1" value="red"></c:set>
							</c:if>
							<c:if test="${(item.value.adminState=='Unblocked' && item.value.operState=='Up') || (item.value.adminState=='Foreign' && item.value.operState=='Up') }">
								<c:set var="color1" value="green"></c:set>
							</c:if>
							<c:if test="${item.value.adminState=='Blocked' && item.value.operState=='Up'}">
								<c:set var="color1" value="#ffd303"></c:set>
							</c:if>
							<c:set var="width1" scope="page" value=""></c:set>
							<c:choose>
								<c:when test="${item.key=='m2_25' || item.key=='m2_26' || item.key=='m2_27' || item.key=='m2_28'}">
									<c:set var="width1" value="9"></c:set>
								</c:when> 
								<c:otherwise>
									<c:set var="width1" value="17"></c:set>
								</c:otherwise>
							</c:choose>
							<td id="${item.key }_c" width="<c:out value="${width1 }"></c:out>" height="165" style="background:url(${ctxStatic}/images/card/MKVIII/${item.value==null ?'DUMMY':item.value.prodName}.png) no-repeat bottom;" onclick="showCard('${item.key }');" onmousemove="showTip('${item.key }',event)" onmouseout="closeTip()">
								<c:if test="${color1!='' }">
									<span id="${item.key }_s" style='background: <c:out value="${color1 }"></c:out>; '></span>
								</c:if>
								<div id="${item.key }"></div>
							</td>
						</c:forEach>
					</tr>
				</table>
				
				<table class="t3">
					 <tr>
						<c:forEach items="${card.m3}" var="item">
						<input id="card_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.adminState },${item.value.operState },${item.value.powerState },${item.value.revision },${item.value.bootRom },${item.value.prodNo },${item.value.prodName },${item.value.serialNo },${item.value.fsbRole }">
						<input id="cardInfo_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.prodName },${item.value.cardInfo.cpuAP },${item.value.cardInfo.cpuDP },${item.value.cardInfo.cpuNCB },${item.value.cardInfo.cpuRP },${item.value.cardInfo.cpuSS7FENB },${item.value.cardInfo.cpuSS7SCTPDP },${item.value.cardInfo.userCount2G },${item.value.cardInfo.userCount3G },${item.value.cardInfo.userCount4G }"/>
						<c:set var="color2" scope="page" value=""></c:set>
							<c:if test="${item.value.operState=='Down' }">
								<c:set var="color2" value="red"></c:set>
							</c:if>
							<c:if test="${(item.value.adminState=='Unblocked' && item.value.operState=='Up') || (item.value.adminState=='Foreign' && item.value.operState=='Up') }">
								<c:set var="color2" value="green"></c:set>
							</c:if>
							<c:if test="${item.value.adminState=='Blocked' && item.value.operState=='Up'}">
								<c:set var="color2" value="#ffd303"></c:set>
							</c:if>
							<c:set var="width2" scope="page" value=""></c:set>
							<c:choose>
								<c:when test="${item.key=='m3_25' || item.key=='m3_26' || item.key=='m3_27' || item.key=='m3_28'}">
									<c:set var="width2" value="9"></c:set>
								</c:when> 
								<c:otherwise>
									<c:set var="width2" value="17"></c:set>
								</c:otherwise>
							</c:choose>
							<td id="${item.key }_c" width="<c:out value="${width2 }"></c:out>" height="165" style="background:url(${ctxStatic}/images/card/MKVIII/${item.value==null ?'DUMMY':item.value.prodName}.png) no-repeat bottom;" onclick="showCard('${item.key }');" onmousemove="showTip('${item.key }',event)" onmouseout="closeTip()">
								<c:if test="${color2!='' }">
									<span id="${item.key }_s" style='background: <c:out value="${color2 }"></c:out>; '></span>
								</c:if>
								<div id="${item.key }"></div>
							</td>
						</c:forEach>						
					</tr> 
				</table>
			</div>
			</c:if>
			<c:if test="${hwtype=='MKX' }">
			<div class="cabinet_MKX">
				<table class="t1">
					<tr>
						 <c:forEach items="${card.m1}" var="item">
						 <input id="card_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.adminState },${item.value.operState },${item.value.powerState },${item.value.revision },${item.value.bootRom },${item.value.prodNo },${item.value.prodName },${item.value.serialNo },${item.value.fsbRole }">	
						 <input id="cardInfo_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.prodName },${item.value.cardInfo.cpuAP },${item.value.cardInfo.cpuDP },${item.value.cardInfo.cpuNCB },${item.value.cardInfo.cpuRP },${item.value.cardInfo.cpuSS7FENB },${item.value.cardInfo.cpuSS7SCTPDP },${item.value.cardInfo.userCount2G },${item.value.cardInfo.userCount3G },${item.value.cardInfo.userCount4G }"/>	
						 	<c:set var="color" scope="page" value=""></c:set>
							<c:if test="${item.value.operState=='Down' }">
								<c:set var="color" value="red"></c:set>
							</c:if>
							<c:if test="${(item.value.adminState=='Unblocked' && item.value.operState=='Up') || (item.value.adminState=='Foreign' && item.value.operState=='Up') }">
								<c:set var="color" value="green"></c:set>
							</c:if>
							<c:if test="${item.value.adminState=='Blocked' && item.value.operState=='Up'}">
								<c:set var="color" value="#ffd303"></c:set>
							</c:if>
							<td id="${item.key }_c" width="17" height="165" style="background:url(${ctxStatic}/images/card/MKX/${item.value==null ?'DUMMY':item.value.prodName}.png) no-repeat bottom;" onclick="showCard('${item.key }');"  onmousemove="showTip('${item.key }',event)" onmouseout="closeTip()">
								<c:if test="${color!='' }">
								<span id="${item.key }_s" style='background: <c:out value="${color }"></c:out>; '></span>
								</c:if>
								<div id="${item.key }"></div>
							</td>
						 </c:forEach>
					</tr>
				</table>
				<table class="t2">
					<tr>
						 <c:forEach items="${card.m2}" var="item">
						 <input id="card_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.adminState },${item.value.operState },${item.value.powerState },${item.value.revision },${item.value.bootRom },${item.value.prodNo },${item.value.prodName },${item.value.serialNo },${item.value.fsbRole }">
						 <input id="cardInfo_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.prodName },${item.value.cardInfo.cpuAP },${item.value.cardInfo.cpuDP },${item.value.cardInfo.cpuNCB },${item.value.cardInfo.cpuRP },${item.value.cardInfo.cpuSS7FENB },${item.value.cardInfo.cpuSS7SCTPDP },${item.value.cardInfo.userCount2G },${item.value.cardInfo.userCount3G },${item.value.cardInfo.userCount4G }"/>	
						 	<c:set var="color1" scope="page" value=""></c:set>
							<c:if test="${item.value.operState=='Down' }">
								<c:set var="color1" value="red"></c:set>
							</c:if>
							<c:if test="${(item.value.adminState=='Unblocked' && item.value.operState=='Up') || (item.value.adminState=='Foreign' && item.value.operState=='Up') }">
								<c:set var="color1" value="green"></c:set>
							</c:if>
							<c:if test="${item.value.adminState=='Blocked' && item.value.operState=='Up'}">
								<c:set var="color1" value="#ffd303"></c:set>
							</c:if>
							<td id="${item.key }_c" width="17" height="165" style="background:url(${ctxStatic}/images/card/MKX/${item.value==null ?'DUMMY':item.value.prodName}.png) no-repeat bottom;" onclick="showCard('${item.key }');" onmousemove="showTip('${item.key }',event)" onmouseout="closeTip()">
								<c:if test="${color1!='' }">
								<span id="${item.key }_s" style='background: <c:out value="${color1 }"></c:out>; '></span>
								</c:if>
								<div id="${item.key }"></div>
							</td>
						 </c:forEach>
					</tr>
				</table>
				<table class="t3">
					<tr>
						 <c:forEach items="${card.m3}" var="item">
						 <input id="card_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.adminState },${item.value.operState },${item.value.powerState },${item.value.revision },${item.value.bootRom },${item.value.prodNo },${item.value.prodName },${item.value.serialNo },${item.value.fsbRole }">	
						 <input id="cardInfo_${item.key }" type="hidden" value="${item.value.eqlAndEqr },${item.value.prodName },${item.value.cardInfo.cpuAP },${item.value.cardInfo.cpuDP },${item.value.cardInfo.cpuNCB },${item.value.cardInfo.cpuRP },${item.value.cardInfo.cpuSS7FENB },${item.value.cardInfo.cpuSS7SCTPDP },${item.value.cardInfo.userCount2G },${item.value.cardInfo.userCount3G },${item.value.cardInfo.userCount4G }"/>	
						 	<c:set var="color2" scope="page" value=""></c:set>
							<c:if test="${item.value.operState=='Down' }">
								<c:set var="color2" value="red"></c:set>
							</c:if>
							<c:if test="${(item.value.adminState=='Unblocked' && item.value.operState=='Up') || (item.value.adminState=='Foreign' && item.value.operState=='Up') }">
								<c:set var="color2" value="green"></c:set>
							</c:if>
							<c:if test="${item.value.adminState=='Blocked' && item.value.operState=='Up'}">
								<c:set var="color2" value="#ffd303"></c:set>
							</c:if>
							<td id="${item.key }_c" width="17" height="165" style="background:url(${ctxStatic}/images/card/MKX/${item.value==null ?'DUMMY':item.value.prodName}.png) no-repeat bottom;" onclick="showCard('${item.key }');" onmousemove="showTip('${item.key }',event)" onmouseout="closeTip()">
								<c:if test="${color2!='' }">
								<span id="${item.key }_s" style='background: <c:out value="${color2 }"></c:out>; '></span>
								</c:if>
								<div id="${item.key }"></div>
							</td>
						 </c:forEach>
					</tr>
				</table>
			</div>
			</c:if>
		</div>
		<div class="right">
		<div style="float: right; "><p style="float: right;line-height: 28px"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 5px;float: left;margin-top: -2px;color: #1f4e79;font-weight: bold;">返回</span></a></p></div>
			<div class="net_node_msg">
				<div class="mme_icon">
					<img src="${ctxStatic}/images/untitled1.png" alt="mme" width="78" height="95" style="margin-left: 15px;"/>
					<p>${netParam.netName}</p>
				</div>
				<div class="mme_node_list">
					<ul>
						<li>节点软件版本 : ${netParam.swl}</li>
						<li>节点硬件版本 : ${netParam.hw}</li>
						<li>节点MMEC: ${netParam.mmec}</li>
						<li>节点NRI: ${netParam.nri}</li>
						<li>节点维护IP地址：${netParam.oamIpAddress}</li>
						<li>节点总用户数：${totalUserCount }</li>
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
					<option value="4">用户数</option>
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
			    <h3>板卡倒换启动记录</h3>
			    <div id="cardStartInfo"></div>
			  </div>
		</div>
		
	</div>
	
	<div id="tip" style="display:none ;position: absolute;background:url(${ctxStatic}/images/card.png); width:286px;height:200px"></div>
	
	<div id="dialogBg"></div>
	<div id="dialog" class="animated">
		<div class="dialogTop">
			<span style="float: left; font-size: 18px;font-weight: bold;margin-left: 170px;font-family:微软雅黑">MME<span class="keyword"></span>维护操作</span>
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