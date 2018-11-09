<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>MME节点管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.main {
			width:99%
		}
		.title {
			float: left;
			width: 100%;
			height: 10px;
			padding-top: 10px;
		}
		.title p {
			text-align: center;
			font-weight: bold;
    		font-size: 18px;
    		color: #626262;
		}
		.center {
			float: left;
			width: 100%;
			margin-top: 30px;
		}
		
		.module_btn_div {
			float: left;
			width: 35%;
		}
		
		.module_btn {
			float: right;
			margin-right:30px;
			width:300px;
			height:48px;
			background:url(${ctxStatic}/images/module_button.png);
			cursor:pointer;
		}
		
		.module_current {
			float: right;
			margin-right:30px;
			width:300px;
			height:48px;
			background:url(${ctxStatic}/images/module_button_current.png);
			cursor:pointer;
		}
		
		.jiantou {
			float: left;
			margin-left:90px;
			width:300px;
			height:44px;
			background:url(${ctxStatic}/images/jiantou.png) no-repeat center;
		}
		
		.module_btn p, .module_current p{
			float: left;
		    margin-top: 13px;
		    width: 225px;
		    text-align: center;
		    color: #fff;
		}
		
		.cmd_div {
			float: left;
			width: 65%;
		}
		
		.cmd_content {
			float: left;
		    margin-left: 50px;
		    width: 90%;
		    height: 500px;
		    border: 1px solid #000;
		}
		
		.exec_btn {
			float: right;
    		margin-right: 20px;
    		margin-top: 20px;
		}
		
		.exec_text {
			float: left;
    		margin-right: 20px;
    		margin-top: 200px;
		}
		
		.btn-new {
		  border-radius: 4px;
		  display: inline-block;
		  padding: 4px 12px;
		  margin-bottom: 0;
		  font-size: 14px;
		  line-height: 20px;
		  color: #fff;
		  padding: 0 10px 0 30px;
		  vertical-align: middle;
		  height: 30px;
		  cursor: pointer;
		  background-color: #1f4e79;
		
		  border: 1px solid #ccc;
		  border-color: #e6e6e6 #e6e6e6 #bfbfbf;
		  border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
		  border-bottom-color: #b3b3b3;
		  -webkit-border-radius: 4px;
		  -moz-border-radius: 4px;
		  border-radius: 4px;
		
		}
		
		.nok {
			font-family: webdings;
			font-size: 16px;
			color:red;
		}
		
		.ok {
			font-family: webdings;
			font-size: 16px;
			color:green;
		}
		
		.pause {
			font-family: webdings;
			font-size: 16px;
			color:orange;
		}
		
		.running {
			font-family: webdings;
			font-size: 16px;
			color:green;
		}
		
		.warning {
			font-family: webdings;
			font-size: 16px;
			color:orange;
		}
		.confirmBox{
			width: 768px;
			height: 266px;
			display:none;
			position: absolute;
			top:70%;
			left: 50%;
			margin-left: -384px;
			margin-top: -133px; 
			z-index:2000;
			border:1px solid #000;
			-moz-box-shadow:3px 3px 4px #000;
			-webkit-box-shadow:3px 3px 4px #000;
			box-shadow:3px 3px 4px #000;
			background: #f2f2f2;
		}
		
		.btn_disable { 
		    -webkit-filter: grayscale(100%);
		    -moz-filter: grayscale(100%);
		    -ms-filter: grayscale(100%);
		    -o-filter: grayscale(100%);
		    filter: grayscale(100%);
		    filter: gray;
		}
		
	</style>
	<script type="text/javascript">
		var stopflag = false;
		var img_finished = '<img src="${ctxStatic}/images/duihao.png">';
		var img_loading = '<img src="${ctxStatic}/images/tuqu.gif">';
		var currentText = [];
		var countdown = 0;
		function btnClick(excelId, id) {
			$('.module_btn_div div').each(function() {
				if($(this).hasClass("module_current")) {
					$(this).removeClass("module_current");
					$(this).addClass("module_btn");
				}
			});		
			$('#b_'+id).removeClass("module_btn");
			$('#b_'+id).addClass("module_current");
			
			var data = ${detailMap };
			var currKey = "d_"+excelId+"_"+id;
			var html = '<table style="width: 100%;">';
			for(var key in data) {
				if(currKey == key) {
					var dataArray = data[key];
					for(var i=0; i<dataArray.length; i++) {
						var item = dataArray[i];
						var commandName = item.commandName;
						var confCmd = item.confCmd;
						html += '<tr><td style="width:30%; border: 1px solid;">'+commandName+'</td><td style="width:70%; border: 1px solid;">'+confCmd+'</td></tr>';
					}
					break;
				}
			}
			 html += '</table>';
			 top.$.jBox.open(html, "<font size='2'>详细信息</font>",550,350,{
				   buttons:{"关闭":false}, bottomText:"",submit:function(v, h, f){
				   }, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","scroll");
				   }
			 });
		}
		
		function showModuleDetail(moduleIndex,isChoice) {
			stopflag = false;
			var flag;
			var excelId=$("#excelId").val();
			$.get('${ctx}/netconfig/excelTemplateAuto/executeFlow' ,{datetime:new Date().getTime(),excelId: excelId,moduleIndex:moduleIndex,isChoice:isChoice}, function(data) {
				if(data == 'no_modules'){
		        	top.$.jBox.tip('模板中未找到可执行的模块','warning');
				}else if(data == 'error'){
					top.$.jBox.tip('执行过程中出错','warning');
				}else if(data.indexOf("param")!= -1 && data.indexOf("danger")!= -1 && data.indexOf("next")!= -1){//弹出参数输入窗口和危险指令确认窗口
					//上一个节点网元ID
					var netId = $("#lastNetId").val();
					var cardLocal = $('#cardLocal').val();
					top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showParam?excelId="+excelId+"&moduleIndex="+moduleIndex+"&cardLocal="+cardLocal, "请输入参数",320,400,{
						persistent:true,
						buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
							var doc = h.find("iframe")[0].contentDocument;
							var moduleId = doc.getElementById("moduleId").value;
							var moduleSize = doc.getElementById("temp_field4").value;
							var controls = doc.getElementsByTagName('input');
							  for(var i=0; i<controls.length; i++){
							    if(controls[i].type=='text'){
							    	if($.trim(controls[i].value) == ''){
							    		top.$.jBox.tip('参数不能为空','warning');
							    		return false;
							    	}
							    	if(i!=controls.length-1){
							    	    map+=(controls[i].id+":"+controls[i].value+",");
							    	}else{
							    		map+=(controls[i].id+":"+controls[i].value);
							    	}
							    }
							  }
							  
							  //弹出危险指令确认窗口
							  top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showCommand?excelId="+excelId+"&moduleIndex="+moduleIndex, "以下指令对网元业务有影响，请确认",800,400,{
									persistent:true,
									buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
										  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
									}, loaded:function(h){
										$(".jbox-content", top.document).css("overflow-y","hidden");
									}
							   });
							  
						}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
					
				}else if(data.indexOf("param")!= -1 && data.indexOf("next")!= -1 && data.indexOf("danger")== -1){//只弹出参数输入窗口
					//上一个节点网元ID
					var netId = $("#lastNetId").val();
					var cardLocal = $('#cardLocal').val();
					top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showParam?excelId="+excelId+"&moduleIndex="+moduleIndex+"&cardLocal="+cardLocal, "请输入参数",320,400,{
						persistent:true,
						buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
							var doc = h.find("iframe")[0].contentDocument;
							var moduleId = doc.getElementById("moduleId").value;
							var moduleSize = doc.getElementById("temp_field4").value;
							var controls = doc.getElementsByTagName('input');
							  for(var i=0; i<controls.length; i++){
							    if(controls[i].type=='text'){
							    	if($.trim(controls[i].value) == ''){
							    		top.$.jBox.tip('参数不能为空','warning');
							    		return false;
							    	}
							    	if(i!=controls.length-1){
							    	    map+=(controls[i].id+":"+controls[i].value+",");
							    	}else{
							    		map+=(controls[i].id+":"+controls[i].value);
							    	}
							    }
							  }
							  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
						}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
					
				}else if(data.indexOf("next")!= -1 && data.indexOf("param")== -1 && data.indexOf("danger")!= -1){//只弹出危险指令确认窗口继续执行
					var map="";
					var netId = $("#lastNetId").val();
					var moduleId = $("#nextModuleId").val();
					var moduleSize = $('#moduleSize').val();
					
					 //弹出危险指令确认窗口
					 top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showCommand?excelId="+excelId+"&moduleIndex="+moduleIndex, "以下指令对网元业务有影响，请确认",800,400,{
						persistent:true,
						buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
							  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
						}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					  });
					
				}else if(data.indexOf("next")!= -1 && data.indexOf("param")== -1 && data.indexOf("danger") == -1){//不弹出任何窗口继续执行
					var map="";
					var netId = $("#lastNetId").val();
					var moduleId = $("#nextModuleId").val();
					var moduleSize = $('#moduleSize').val();
					sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
				}else if(data.indexOf("open")!= -1){//弹出选择网元窗口
					var netId=$("#netId").val();
					var cardLocal = $('#cardLocal').val();
					top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showModuleDetail?excelId="+excelId+"&netId="+netId+"&moduleIndex="+moduleIndex, "请确认需要执行的网元",320,400,{
						persistent:true,
						buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
							var doc = h.find("iframe")[0].contentDocument;
							var netId = doc.getElementsByName("temp_field1")[0].value;
							if(netId == ''){
					    		top.$.jBox.tip('网元不能为空','warning');
					    		return false;
					    	}
							var temp_field2=doc.getElementById("temp_field2").value;
							var moduleId=doc.getElementById("moduleId").value;
							var moduleSize=doc.getElementById("temp_field4").value;//模块个数
							$('#moduleSize').val(moduleSize);
							var map="";
							if(temp_field2!="0"){
								top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateDebug/showParam?excelId="+excelId+"&moduleId="+moduleId+"&cardLocal="+cardLocal, "请输入参数",320,400,{
									persistent:true,
									buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
										var doc = h.find("iframe")[0].contentDocument;
										var controls = doc.getElementsByTagName('input');
										  for(var i=0; i<controls.length; i++){
										    if(controls[i].type=='text'){
										    	if($.trim(controls[i].value) == ''){
										    		top.$.jBox.tip('参数不能为空','warning');
										    		return false;
										    	}
										    	if(i!=controls.length-1){
										    	    map+=(controls[i].id+":"+controls[i].value+",");
										    	}else{
										    		map+=(controls[i].id+":"+controls[i].value);
										    	}
										    }
										  }
										  if(data.indexOf("danger")!= -1){
											//弹出危险指令确认窗口
											 top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showCommand?excelId="+excelId+"&moduleIndex="+moduleIndex, "以下指令对网元业务有影响，请确认",800,400,{
												persistent:true,
												buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
													  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
												}, loaded:function(h){
													$(".jbox-content", top.document).css("overflow-y","hidden");
												}
											  });
										  }else{
											  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
										  }
										  
									}, loaded:function(h){
										$(".jbox-content", top.document).css("overflow-y","hidden");
									}
								});
							}else{
								if(data.indexOf("danger")!= -1){
									//弹出危险指令确认窗口
									 top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showCommand?excelId="+excelId+"&moduleIndex="+moduleIndex, "以下指令对网元业务有影响，请确认",800,400,{
										persistent:true,
										buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
											  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
										}, loaded:function(h){
											$(".jbox-content", top.document).css("overflow-y","hidden");
										}
									  });
								  }else{
									  sendCommand(map,netId,excelId,moduleId,flag,Number(moduleIndex)+1,moduleSize);
								  }
							}				
						}, loaded:function(h){
							
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
				}
		    });
		}
		
		var startTime = 0;
		function startEexcute() {
			$('.module_btn_div table tr td:nth-child(1)').find("img").remove();
			$("#pcrfPanel").empty();
			$("#pcrfMenu li").remove();
			startTime = new Date().getTime();
			showModuleDetail(0,false);
		}
		
		function choiceEexcute() {
			countdown = -1;
			stopflag = true;
			$('.module_btn_div table tr td:nth-child(1)').find("img").remove();
			startTime = new Date().getTime();
			var excelId = $('#excelId').val();
			top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateAuto/showModules?excelId="+excelId, "请选择模块",500,500,{
				persistent:true,
				buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
					
					var doc = h.find("iframe")[0].contentDocument;
					
					var moduleIndex = doc.getElementsByName("temp_field1")[0].value;
					if(moduleIndex == ''){
			    		top.$.jBox.tip('请选择模块','warning');
			    		return false;
			    	}
					showModuleDetail(moduleIndex,true);
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
			
		}
		
		function stopEexcute(){
			countdown = 0;
			stopflag = true;
			$v = $("#pcrfPanel").children();
 			$v.each(function(index,element){
 				$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
 				$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("pause");
       			$("#f"+$(element)[0].id.replace("pcrfTab","")).html(';');
 			});
			top.$.jBox.tip('执行已中断','warning');
		}
		function saveLog(excelId){
			var netId_logs = [];
			$v = $("#pcrfPanel").children();
			$v.each(function(index,element){
				var netId_log = {};
				netId_log.netid = $(element)[0].id.replace("pcrfTab","");
				netId_log.log = $(element).children().eq(0).html();
				netId_logs.push(netId_log);
			});
			
			$.ajax({  
	            type:'post',
	            url:'${ctx}/netconfig/excelTemplateAuto/saveLog',
	            data:{
	            	'datetime':startTime,
	            	'excelId':excelId,
	            	'netIdLogs':JSON.stringify(netId_logs)
	            },
	            success:function(data){
	            	if(data =='error'){
	            		top.$.jBox.tip('日志保存失败','warning');
	            	}
	            }  
	        });
		}
		
		
		function sendCommand(map,netId,excelId,moduleId,flag,moduleIndex,moduleSize){
			$.ajax({  
	            type:'post',
	            async:false,
	            url:'${ctx}/netconfig/excelTemplateDebug/sendCommandBefore',
	            data:{
	            	'params':map,
	            	'netId':netId,
	            	'excelId':excelId,
	            	'moduleId':moduleId,
	            	'moduleIndex':moduleIndex,
	            	'isDebug':false
	            },
	            success:function(dataQuery){
	            	$("#startEexcuteButton").attr("disabled",true);
	    			$("#choiceEexcuteButton").attr("disabled",true);
	            	$("#td_"+moduleId).empty();
	            	$("#td_"+moduleId).append(img_loading);
	            	dataQuery = JSON.parse(dataQuery);
	            	$('#lastNetId').val(dataQuery.lastNetId);
	            	$('#nextModuleId').val(dataQuery.nextModuleId);
					var netnames=dataQuery.netNames.split(",");
					var netids=netId.split(",");
					var arr = [];
					for(var i=0,len=netnames.length;i<len;i++){
						if(netnames[i]!=""){
							arr.push([netids[i],netnames[i]]);
						}
					}
					
					var licount = $('#pcrfMenu li').length;
					$.each(arr,function(index,element){
						
						if(licount!=0 && $('#li'+element[0]).length==0){
							var lihtml = "<li id='li"+element[0]+"'><a href='javascript:net_item("+element[0]+");'>"+element[1]+"&nbsp;&nbsp;<font id='f"+element[0]+"' class='running'>n</font></a></li>";
							var divhtml = "<div id='pcrfTab"+element[0]+"' class='no'><div style='font-family: Lucida Console;height:380px;overflow:auto'></div></div>";
						}else if (licount==0 && $('#li'+element[0]).length==0){ 
							var lihtml = "<li id='li"+element[0]+"'><a href='javascript:net_item("+element[0]+");'>"+element[1]+"&nbsp;&nbsp;<font id='f"+element[0]+"' class='running'>n</font></a></li>";
						    var divhtml = "<div id='pcrfTab"+element[0]+"' class='con'><div style='font-family: Lucida Console;height:380px;overflow:auto'></div></div>";
						}
						
					    $("#pcrfMenu").append(lihtml);
					    $("#pcrfPanel").append(divhtml);
					    
					});
					$('#pcrfMenu li').removeClass();
					$('#pcrfMenu li').eq(0).addClass("active");
					currentText.splice(0,currentText.length);
					flag = window.setInterval(function(){
						showProcessText(netId.split(","),flag,moduleIndex,moduleSize);
					},500); 
	            }  
	        });
			
			$.ajax({  
	            type:'post',
	            url:'${ctx}/netconfig/excelTemplateDebug/sendCommand',
	            data:{
	            	'params':map,
	            	'netId':netId,
	            	'excelId':excelId,
	            	'moduleId':moduleId,
	            	'moduleIndex':moduleIndex,
	            	'isDebug':false
	            },
	            success:function(dataQuery){
	            	var failCount = 0;
	            	if(dataQuery=="finish"){
	            		clearInterval(flag);
		            	showProcessText(netId,flag,moduleIndex,moduleSize);
		            	
		            	$v = $("#pcrfPanel").children();
            			$v.each(function(index,element){
            				 var content="";
            				 for(var w=0; w<currentText.length; w++) {
            					 var currObj = currentText[w];
            					 if(currObj.key == $(element)[0].id.replace("pcrfTab","")) {
            						 content = currObj.value;
            					 }
            				 }
            				 if(content !=''){
	            				 if(content.indexOf("<font color=\"red\">")>0){
	          	           			failCount++;
		          	           		$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
									$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("warning");
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).html('i');
	          	           		 }else if(content.indexOf("EOPS RUN SUCCESS")<0){
	          	           			failCount++;
	            					$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
	            					$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("nok");
	      	           				$("#f"+$(element)[0].id.replace("pcrfTab","")).html('r');
	          	           		 }else{
		          	           		$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
			          	           	$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("ok");
		      	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).html('a');
	          	           		 }
            				 }
            			});
            		 //指令执行成功后自动调用下个模块
            		 if(!stopflag && failCount==0 && moduleIndex < moduleSize){
            			$("#td_"+moduleId).empty();
     	            	$("#td_"+moduleId).append(img_finished);
     	            	$("#startEexcuteButton").attr("disabled",false);
     	  				$("#choiceEexcuteButton").attr("disabled",false);
            		 	showModuleDetail(moduleIndex,false);
            		 }else{
            			 $("#td_"+moduleId).empty();
      	            	 $("#td_"+moduleId).append(img_finished);
      	            	 saveLog(excelId);
      	            	 $("#startEexcuteButton").attr("disabled",false);
      	  				 $("#choiceEexcuteButton").attr("disabled",false);
            			 top.$.jBox.tip('自动化执行结束');
            		 }
        			 stopflag = false;
	            	}else if(!isNaN(dataQuery)){
	            		clearInterval(flag);
		            	showProcessText(netId,flag,moduleIndex,moduleSize);
		            	
		            	$v = $("#pcrfPanel").children();
            			$v.each(function(index,element){
            				 var content="";
            				 for(var w=0; w<currentText.length; w++) {
            					 var currObj = currentText[w];
            					 if(currObj.key == $(element)[0].id.replace("pcrfTab","")) {
            						 content = currObj.value;
            					 }
            				 }
            				 if(content !=''){
	            				 if(content.indexOf("<font color=\"red\">")>0){
	          	           			failCount++;
		          	           		$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
									$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("warning");
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).html('i');
	          	           		 }else if(content.indexOf("EOPS RUN SUCCESS")<0){
	          	           			failCount++;
	            					$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
	            					$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("nok");
	      	           				$("#f"+$(element)[0].id.replace("pcrfTab","")).html('r');
	          	           		 }else{
		          	           		$("#f"+$(element)[0].id.replace("pcrfTab","")).removeClass();
			          	           	$("#f"+$(element)[0].id.replace("pcrfTab","")).addClass("ok");
		      	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).html('a');
	          	           		 }
            				 }
            			});
            			
            			$("#td_"+moduleId).empty();
       	            	$("#td_"+moduleId).append(img_finished);
       	            	$("#moduleName").html($('#td_'+moduleId).next().find('p').eq(0).html());
       	            	$("#startEexcuteButton").attr("disabled",false);
       	  				$("#choiceEexcuteButton").attr("disabled",false);
            			
       	  				countdown = parseInt(dataQuery);
            			$("#confirmBox").css("display","block");
            			countSecond(excelId,moduleId,moduleIndex,moduleSize,failCount);
            		
	            	}
	            	
	            }  
	        });
		}
		
		
		function showProcessText(netids,flag,moduleIndex,moduleSize){
			 $.ajax({  
	             type:'post',
	             async:false,
	             url:'${ctx}/netconfig/excelTemplateDebug/queryProcessText',
	             data:{
	            	 'netIds':netids.toString()		                        	
	             },
	             success:function(data){
	            	 var result=eval(data);
	            		 for(var i=0;i<result.length;i++){
	       					$("#pcrfTab"+result[i].netId+" div").append(result[i].text);
	       					var remark=0;
	       					for(var z=0; z<currentText.length; z++) {
	       						var currObj = currentText[z];
	       						if(currObj.key == result[i].netId) {
	       							currObj.value += result[i].text;
	       							remark++;
	       						}
	       					}
	       					if(remark==0){
		       					var obj = {};
		       					obj.key = result[i].netId;
		       					obj.value = result[i].text;
		       					currentText.push(obj);
	       					}
	       					if(typeof($("#pcrfTab"+result[i].netId+" div").get(0))!="undefined"){
	       						$("#pcrfTab"+result[i].netId +" div").scrollTop($("#pcrfTab"+result[i].netId+" div").get(0).scrollHeight);
	       					}
	      	           		 if(i==0&&$("#li"+result[i].netId).hasClass("active")){
	      	           			   $("#pcrfTab"+result[i].netId).css("display","block");
	      	           		 }else{
	      	           			 if(!$("#li"+result[i].netId).hasClass("active")){
	      	           			   $("#pcrfTab"+result[i].netId).css("display","none");
	      	           			 }
	      	           		 }
		      	           	if(!stopflag){
			      	           	$v = $("#pcrfPanel").children();
								$v.each(function(index,element){
									$("#f"+result[i].netId.replace("pcrfTab","")).removeClass();
									$("#f"+result[i].netId.replace("pcrfTab","")).addClass("running");
									$("#f"+result[i].netId.replace("pcrfTab","")).html('n');
								});
		      	           	}
	       				}
	              } 
	         });
		}
		
		
		function net_item(n){
			var menuli = $("#pcrfMenu li");
			menuli.each(function(index,element){
				$(element).removeClass();
			});
			$v = $("#pcrfPanel").children();
			$v.each(function(index,element){
				$(element).attr("class","no");
				$(element).css("display","none");
			});
			$("#li"+n).attr("class","active");
			$("#pcrfTab"+n).attr("class","con");
			$("#pcrfTab"+n).css("display","block");
		}
		
		function logDownload(){
			$v = $("#pcrfPanel").children();
			var i=0;
			$v.each(function(index,element){
				i++;
			});
			if(i==0){
				top.$.jBox.tip('无可下载的日志!','warning');
			}else{
				var netId_logs = [];
				$v.each(function(index,element){
					var netId_log = {};
					netId_log.netid = $(element)[0].id.replace("pcrfTab","");
					netId_log.log = $(element).children().eq(0).html();
					netId_logs.push(netId_log);
				});
				var excelId = $('#excelId').val();
				var netIdLogs = JSON.stringify(netId_logs);
				$('#netIdLogs').val(netIdLogs);
				document.forms[0].action = "${ctx}/netconfig/excelTemplateAuto/downloadZip?datetime="+startTime + "&excelId="+excelId;
				document.forms[0].method = "POST";
				document.forms[0].submit();
			}
		}
		
		
		/*继续执行*/
		function continueEexcute(){
			countdown = 0;
		}
		
		function countSecond(excelId,moduleId,moduleIndex,moduleSize,failCount) {
			if (countdown > 0) {
				$("#secondTime").html(countdown);
				countdown--;
				setTimeout(function() {countSecond(excelId,moduleId,moduleIndex,moduleSize,failCount)}, 1000);
			}else if(countdown == 0){
				$("#secondTime").html(0);
				continueExec(excelId,moduleId,moduleIndex,moduleSize,failCount);
			}else{
				$("#confirmBox").css("display","none");
			}
		}
		
		function continueExec(excelId,moduleId,moduleIndex,moduleSize,failCount){
		 	$("#confirmBox").css("display","none");
		 	 //指令执行成功后自动调用下个模块
   	   		 if(!stopflag && failCount==0 && moduleIndex < moduleSize){
   	   		 	 showModuleDetail(moduleIndex,false);
   	   		 }else{
            	 saveLog(excelId);
   	   			 top.$.jBox.tip('自动化执行结束');
   	   		 }
   			 stopflag = false;
		}
		
	</script>
</head>
<body>
	<div style="float:left;margin-top:-20px;margin-left:40px;"><img src="${ctxStatic}/images/eops.jpg" style="width: 330px; height: 90px;" ></div>
	<form:form id="mainIndex" method="post">
		<input id="netIdLogs" name="netIdLogs" type="hidden" >
	</form:form>
	<input id="cardLocal" type="hidden" value="${cardLocal}">
	<input id="excelId" type="hidden" value="${excelId}">
	<input id="netId" type="hidden" value="${netId}">
	<input id="moduleSize" type="hidden" >
	<input id="lastNetId" type="hidden" value="${lastNetId}" >
	<input id="nextModuleId" type="hidden" value="${nextModuleId}">
	<p style="float: right;line-height: 28px"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 14px;float: left;margin-right:18px;margin-top: -1px;color: #1f4e79;font-weight: bold;">返回</span></a></p>
	<div class="main">
		<div class="title">
			<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			${excelName }
			<%-- <c:if test='${cardLocal != null && cardLocal != ""}'>
			(${cardLocal})
			</c:if> --%>
			</p>
		</div>
		<div class="center">
			<div class="module_btn_div">
				<c:forEach items="${moduleList }" var="item" varStatus="stat">
					<table style="float: left; margin-left: 60px">
					<tr>
						<td id="td_${item.id }" style="width:48px"></td>
						<td>
							<div id="b_${item.id }" class="module_btn" onclick="btnClick(${item.excelId},${item.id})">
								<p>${item.moduleName }</p>
							</div>
						</td>
					</tr>
					</table>
					<c:if test="${!stat.last }">
						<span class="jiantou"></span>
					</c:if>
				</c:forEach>
			</div>
			<div class="cmd_div">
				<div>
		   		 <ul class="nav nav-tabs" id="pcrfMenu" style="border-bottom: 0px solid #ddd;"></ul>
			   </div>
			   <div class="panel-body" id="pcrfPanel" style="height:380px;overflow:auto;border: 1px solid #c5c5c5;margin-top: -8px">
					
			   </div>
				<div class="exec_btn">
					<input id="startEexcuteButton" type="image" src="${ctxStatic}/images/icon-start.png" style="width:150px;height:35px;" onclick="startEexcute()">
					<input type="image" src="${ctxStatic}/images/icon-stop.png" style="width:150px;height:35px;" onclick="stopEexcute()">
					<input id="choiceEexcuteButton" type="image" src="${ctxStatic}/images/icon-select.png" style="width:150px;height:35px;" onclick="choiceEexcute()">
					<input type="image" src="${ctxStatic}/images/icon-log.png" style="width:150px;height:35px;" onclick="logDownload()">
				</div>
			</div>
			
		</div>
		
	</div>
	
	<div id="confirmBox" class="confirmBox">
		<table border="0" style="width: 750px;">
			<tr style="height: 200px">
				<td style="padding-left: 180px; font-size: 18px;"><span id="moduleName"></span>模块执行完成，<span id="secondTime" style="font-weight: bold;color: green;"></span>秒后继续执行下一个模块</td>
			</tr>
			<tr>
				<td style="padding-left: 280px;">
					<input type="image" src="${ctxStatic}/images/icon-start.png" style="width:150px;height:35px;" onclick="continueEexcute()">
					<input type="image" src="${ctxStatic}/images/icon-stop.png" style="width:150px;height:35px;" onclick="stopEexcute()">
					<input type="image" src="${ctxStatic}/images/icon-select.png" style="width:150px;height:35px;" onclick="choiceEexcute()">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>