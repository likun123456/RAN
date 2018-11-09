<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#excelId").change(function(){
			    	    var excelId=$("#excelId option:selected").val();
				   		$.post('${ctx}/netconfig/excelTemplateDebug/queryModuleList', {
					       "excelId": excelId
					    }, function(data) {
						    	var moduleList = eval(data);
						    	$("#moduleId option:not(:first)").remove();  
						    	$(".select2-container:eq(3) .select2-chosen:eq(0)").html("请选择");
						    	//$("#moduleId").html("<option value=''>请选择</option>")
						    	$.each(moduleList, function(index, o) { 
						    		var html = "<option value='"+o.id+"'>"+o.moduleName+"</option>";
								    $("#moduleId").append(html);
								}); 
						    	$("#moduleId option:first").prop("selected", 'selected'); 
						    });
					});
				$("#type").change(function(){
		    	    var type=$("#type option:selected").val();
		    	    var tpType=$("#tpType option:selected").val();
			   		$.post('${ctx}/netconfig/excelTemplateDebug/queryExcelList', {
				       "type": type,
				       "tpType":tpType
				    }, function(data) {
					    	var moduleList = eval(data);
					    	$("#excelId option:not(:first)").remove();  
					    	$(".select2-container:eq(2) .select2-chosen:eq(0)").html("请选择");
					    	$.each(moduleList, function(index, o) { 
					    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
							    $("#excelId").append(html);
							}); 
					    	$("#excelId option:first").prop("selected", 'selected'); 
					    });
				});
				$("#tpType").change(function(){
		    	    var type=$("#type option:selected").val();
		    	    var tpType=$("#tpType option:selected").val();
			   		$.post('${ctx}/netconfig/excelTemplateDebug/queryExcelList', {
				       "type": type,
				       "tpType":tpType
				    }, function(data) {
					    	var moduleList = eval(data);
					    	$("#excelId option:not(:first)").remove();  
					    	$(".select2-container:eq(2) .select2-chosen:eq(0)").html("请选择");
					    	$.each(moduleList, function(index, o) { 
					    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
							    $("#excelId").append(html);
							}); 
					    	$("#excelId option:first").prop("selected", 'selected'); 
					    });
				});
			});
	function showModuleDetail1() {
		var flag;
		 var excelId=$("#excelId option:selected").val();
		 var moduleId=$("#moduleId option:selected").val();
		if(excelId=="0"){
			top.$.jBox.tip('请选择模版!','warning');
			return false;
		}
		if(moduleId=="0"){
			top.$.jBox.tip('请选择模版模块!','warning');
			return false;
		}
		$("#temp_field5").val("");
		top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateDebug/showModuleDetail?excelId="+excelId+"&moduleId="+moduleId, "请确认需要执行的网元",350,370,{
			buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
				var doc = h.find("iframe")[0].contentDocument;
				var netId = doc.getElementsByName("temp_field1")[0].value;
				if(netId == ''){
		    		top.$.jBox.tip('网元不能为空','warning');
		    		return false;
		    	}
				var temp_field2=doc.getElementById("temp_field2").value;
				var map="";
				if(temp_field2!="0"){
					top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateDebug/showParam?excelId="+excelId+"&moduleId="+moduleId, "请输入参数",300,300,{
						buttons:{"确定":"ok"}, bottomText:"",submit:function(v, h, f){
							var doc = h.find("iframe")[0].contentDocument;
							var controls = doc.getElementsByTagName('input');
							  for(var i=0; i<controls.length; i++){
							    if(controls[i].type=='text'){
							    	if(controls[i].value == ''){
							    		top.$.jBox.tip('参数不能为空','warning');
							    		return false;
							    	}
							    	if(i!=controls.length-1){
							    	    map+=(controls[i].id+":"+controls[i].value+"liuliang");
							    	}else{
							    		map+=(controls[i].id+":"+controls[i].value);
							    	}
							    }
							  } 
							  sendCommand(map,netId,excelId,moduleId,flag);
						}, loaded:function(h){
							$(".jbox-content", top.document).css("overflow-y","hidden");
						}
					});
				}else{
					sendCommand(map,netId,excelId,moduleId,flag);
				}				
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}
		});
	}
	
	function sendCommand(map,netId,excelId,moduleId,flag){
				//loading('正在执行，请稍等...');
				$('#pcrfPanel').empty();
				$('#pcrfMenu').empty();
				
				$.ajax({  
		            type:'post',
		            async:false,
		            url:'${ctx}/netconfig/excelTemplateDebug/sendCommandBefore',
		            data:{
		            	'params':map,
		            	'netId':netId,
		            	'excelId':excelId,
		            	'moduleId':moduleId,
		            	'isDebug':true
		            },
		            success:function(dataQuery){
		            	dataQuery = JSON.parse(dataQuery);
		            	$("#pcrfPanel").empty();
						$("#pcrfMenu li").remove();
						var netnames=dataQuery.netNames.split(",");
						var netids=netId.split(",");
						var arr = [];
						for(var i=0,len=netnames.length;i<len;i++){
							if(netnames[i]!=""){
								arr.push([netids[i],netnames[i]]);
							}
						}
						
						$.each(arr,function(index,element){
							if(index!=0){
								var lihtml = "<li id='li"+element[0]+"'><a href='javascript:net_item("+element[0]+");'>"+element[1]+"&nbsp;&nbsp;<font id='f"+element[0]+"' style='font-family: webdings;font-size: 12px;color:green'>n</font></a></li>";
								var divhtml = "<div id='pcrfTab"+element[0]+"' class='no'><div style='height:180px;overflow:auto;font-family: Lucida Console;'></div></div>";
							}else{ 
								var lihtml = "<li id='li"+element[0]+"' class='active'><a href='javascript:net_item("+element[0]+");'>"+element[1]+"&nbsp;&nbsp;<font id='f"+element[0]+"' style='font-family: webdings;font-size: 12px;color:green'>n</font></a></li>";
							    var divhtml = "<div id='pcrfTab"+element[0]+"' class='con'><div style='height:180px;overflow:auto;font-family: Lucida Console;'></div></div>";
							}
						    $("#pcrfMenu").append(lihtml);
						    $("#pcrfPanel").append(divhtml);
						});
						flag = window.setInterval(function(){
							showProcessText(netId.split(","),flag);
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
		            	'isDebug':true
		            },
		            success:function(dataQuery){
		            	if(dataQuery=="finish"){
			            	//parent.$('#jbox-states').css("display","none");
	            		 	clearInterval(flag);
			            	showProcessText(netId,flag);
			            	$v = $("#pcrfPanel").children();
	            			$v.each(function(index,element){
	            				if($(element).html().indexOf("EOPS RUN SUCCESS")>0){
	            					$("#f"+$(element)[0].id.replace("pcrfTab","")).css("font-size","20px");
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).html('a');
	          	           		 }else if($(element).html().indexOf("<font color=\"red\">")>0){
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).css("font-size","16px");
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).css("color","orange");
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).html('i');
	          	           		 }else{
	          	           			$("#f"+$(element)[0].id.replace("pcrfTab","")).css("color","red");
	      	           				$("#f"+$(element)[0].id.replace("pcrfTab","")).html('r');
	          	           		 }
	            			});
		            	}
		            }  
		        });
		
	}
	
	
	//自定义sleep
	function sleep(numberMillis) { 
		var now = new Date(); 
			var exitTime = now.getTime() + numberMillis; 
			while (true) { 
			now = new Date(); 
			if (now.getTime() > exitTime) 
			return; 
		} 
	}
	
	function showProcessText(netids,flag){
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
		$("#pcrfTab"+n +" div").scrollTop($("#pcrfTab"+n+" div").get(0).scrollHeight);
	}
	
	function showModel() {
		var excelId = $("#excelId").val();
		if(excelId=="0"){
			top.$.jBox.tip('请选择模版!','warning');
			return false;
		}else{
			top.$.jBox.open("iframe:${ctx}/netconfig/excelTemplateDebug/showlist?excelId="+excelId, "查看模板模块",1300,500,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	}
	function logDownload(){
		$v = $("#pcrfPanel").children();
		var i=0
		$v.each(function(index,element){
			i=1;
		});
		if(i==0){
			top.$.jBox.tip('请先执行调试!','warning');
		}else{
		 window.location.href= "${ctx}/netconfig/excelTemplateDebug/downloadZip";
		}
	}
</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="tVisExcelModuleDetail"
		action="${ctx}/netconfig/excelTemplateDebug/list" method="post"
		enctype="multipart/form-data" class="form-horizontal">
		<sys:message content="${message}" />
		<div class="control-group">
				<img src="${ctxStatic}/images/eopsdebug.png">
		</div>
		<div class="control-group">
			<label>网元类型:</label>
			<form:select id="type" path="type" class="input-xlarge" style="width:250px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>&nbsp;&nbsp;&nbsp;&nbsp;
			<label>EOPS模板类型:</label>
			<form:select id="tpType" path="tpType" class="input-xlarge" style="width:250px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_eops_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
		</div>
		<div class="control-group">
				<label>选择模版:</label>
				<form:select path="excelId" class="required input-xlarge"
					style="width:250px;" id="excelId">
					<form:option value="0" label="请选择" />
					<form:options items="${visExcelList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>&nbsp;&nbsp;&nbsp;&nbsp;
				<label>选择模版模块:</label>&nbsp;&nbsp;
				<form:select path="moduleId" class="required input-xlarge"
					style="width:250px;" id="moduleId">
					<form:option value="0" label="请选择" />
				</form:select>
			</ul>
		</div>
		  <div>
	   		 <ul class="nav nav-tabs" id="pcrfMenu"></ul>
		   </div>
		   <div class="panel-body" id="pcrfPanel" style="height:180px;overflow:auto">
				
		   </div>
	</form:form>
		<div class="control-group" style="margin-left:150px;">
			<input type="image" src="${ctxStatic}/images/icon-template.png" style="width:150px;height:35px;" onclick="showModel()">
			<input type="image" src="${ctxStatic}/images/icon-start.png" style="width:150px;height:35px;" onclick="showModuleDetail1()">
			<input type="image" src="${ctxStatic}/images/icon-log.png" style="width:150px;height:35px;" onclick="logDownload()">
		</div>
</body>
</html>