<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户信令追踪</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	        
	    .no {
	        height:180px;overflow-y:scroll;display:none;
	    }  
	    .con {
	        height:180px;overflow-y:scroll;
	    }
	    .pooldiv{
	    	height:100px;overflow-y:scroll;
	    }
        
	</style>
	
	<script type="text/javascript">
	    var countdown;
	    var f = true;
	    var tempStr = "0";
	    var patrn = /^[0-9]*$/; 
		 $(document).ready(function() {
			 
			var isRedirect = '${isRedirect}';
			if('true'==isRedirect) {
				var searchType = '${searchType}';
				var searchValue = '${searchValue}';
				var captureDuration = '${captureDuration}';
				var captureScope = '${captureScope}';
				
				$("#numberType").val(searchType);
				var label = '${fns:getDictLabel(searchType,'biz_userquery_type','')}';
				$(".select2-container .select2-chosen:eq(0)").html(label);
				
				$('#number').val(searchValue);
				
				$("#captureDuration").val(captureDuration);   //抓包时长
				var label2 = '${fns:getDictLabel(captureDuration,'biz_capture_duration','')}';
				$(".select2-container .select2-chosen:eq(1)").html(label2);
				
				$("#captureScope").val(captureScope);   //抓包范围
				var label3 = '${fns:getDictLabel(captureScope,'biz_capture_scope','')}';
				$(".select2-container .select2-chosen:eq(2)").html(label3);
				
				if('0' == captureScope) {
					$("#elementSelect").show();
				}
				countdown=parseInt($("#captureDuration").val())*60;
			}
			
			$("#inputForm").validate({
				submitHandler: function(form){
					if($("#captureScope").val()!="0"&&$("#captureScope").val()!=""){
						if($("#number").val()==""){
							top.$.jBox.tip('MME、SAEGW网元抓包必须输入msisdn号或imsi号','warning');
						}else if($("#captureDuration").val()==""){
				            top.$.jBox.tip('请选择抓包时长','warning');
						}else if(!patrn.test($("#number").val())){
				            top.$.jBox.tip('imsi或msisdn号码必须是纯数字','warning');
			            }else{
	                        sendPost()
			            }
					}else{
						if ($("#mmePoolsId").val()=="" && $("#saegwPoolsId").val()=="" && $("#pcrfNetElementsId").val()==""){
	                        top.$.jBox.tip('MME网元、SAEGW网元、PCRF网元不能同时为空','warning');
	                    }else if ($("#number").val()=="" && $("#mmePoolsId").val()!=""){
	                    	$("#number").focus();
	                    	top.$.jBox.tip('MME网元抓包必须输入msisdn号或imsi号','warning');
	                    }else if ($("#number").val()=="" && $("#saegwPoolsId").val()!=""){
	                    	$("#number").focus();
	                       	top.$.jBox.tip('SAEGW网元抓包必须输入msisdn号或者imsi号','warning');
	                    }else if ($("#captureDuration").val()==""){
	                    	top.$.jBox.tip('请选择抓包时长','warning');
	                    }else{
	                        sendPost();
	                    }
					}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$("#captureDuration").change(function(){
				countdown=parseInt($("#captureDuration").val())*60;
			});
			
			$("#captureScope").change(function(){
				if($(this).val()=="0"){
					$("#elementSelect").show();
				}else{
					$("#elementSelect").hide();
				}
			}); 
			
		});
		
		function sendPost(){
			
			$.get('${ctx}/userquery/signal/checkCaptureStatus',{datetime:new Date().getTime()}, function(data){
				if (data=="running"){
					top.$.jBox.tip('抓包正在进行中，不允许重复执行!','warning');
				}else{
					showMask(); // 显示遮罩
					//显示指令下发图片
					$("#img_wrap", parent.document).show();
					//清空上一次请求输出的日志
					$("#mmePoolPanel div").empty();
					$("#epgPoolPanel div").empty();
					$("#pcrfPanel").empty();
					$("#pcrfMenu li").remove();
					// 动态创建tab页面
					var arr = [];
					var netids = $("#pcrfNetElementsId").val().split(",");
					var netnames = $("#pcrfNetElementsName").val().split(",");
					if($("#pcrfNetElementsId").val()==""){
						var lihtml = "<li id='liPcrf' class='active'></li>";
					    var divhtml = "<div id='pcrfTabPcrf' class='con'><div></div></div>";
					    $("#pcrfMenu").append(lihtml);
					    $("#pcrfPanel").append(divhtml);
					}else{
						for(var i=0,len=netnames.length;i<len;i++){
							arr.push([netids[i],netnames[i]]);
						}
						
						$.each(arr,function(index,element){
							if(index!=0){
								var lihtml = "<li id='li"+element[0]+"'><a href='javascript:net_item("+element[0]+");'>"+element[1]+"</a></li>";
								var divhtml = "<div id='pcrfTab"+element[0]+"' class='no'><div></div></div>";
							}else{
								var lihtml = "<li id='li"+element[0]+"' class='active'><a href='javascript:net_item("+element[0]+");'>"+element[1]+"</a></li>";
							    var divhtml = "<div id='pcrfTab"+element[0]+"' class='con'><div></div></div>";
							}
						    $("#pcrfMenu").append(lihtml);
						    $("#pcrfPanel").append(divhtml);
						});
					}
					var inputForm = $("#inputForm");
					//$("#loadingImg", parent.document).attr("src","${ctxStatic}/images/capture1.png");
				    //$("#img_wrap", parent.document).hide();
				    setTimeout(function(){
				    	var btnObj = document.getElementById('btnExecture');
						settime(btnObj);
				    },5000);
					
					$.post(inputForm.attr('action'), inputForm.serializeArray(), function(data){
						    clearInterval(flag);// 清除定时器
						    f = true;
						    $("#loadingImg", parent.document).attr("src","${ctxStatic}/images/capture1.png");
						    $("#img_wrap", parent.document).hide();
							if(data == "empty"){
								top.$.jBox.tip("全量包生成，用户包未检索到！");
							}else if(data == "error"){
								top.$.jBox.tip("抓包过程中断，请检查抓包号码是否正确！");
								//$("#loadingImg", parent.document).attr("src","${ctxStatic}/images/capture4.png");
							}else{
								top.$.jBox.open('iframe:${ctx}/userquery/signal/showCaptureResult?id='+data,'单用户抓包结果',$(top.document).width()-800,$(top.document).height()-700,{
									buttons:{"关闭":true},
									loaded:function(h){
										$(".jbox-content", top.document).css("overflow-y","hidden");
										$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
										$("body", h.find("iframe").contents()).css("margin","10px");
									}
								});
								
							}
							hideMask();// 隐藏遮罩层
						});
					flag = setInterval(showProcessText,500);
					}
				
				});
			}
		//自定义sleep
		function sleep(d){
		  for(var t = Date.now();Date.now() - t <= d;);
		}
		
		//自动切换至detail页
		function switchTab(){
			$("#con_menu_2").show(); 
			$("#con_menu_1").hide();
			
			$("#menu1").removeClass("active"); 
			$("#menu2").addClass("active"); 
		}
		
		//切换标签
		function setTab(name,cursel,n){ 
			for(i=1;i<=n;i++){ 
				var menu=$("#"+name+i); 
				var con=$("#con_"+name+"_"+i); 
				i==cursel?menu.addClass("active"):menu.removeClass("active"); 
				i==cursel?con.show():con.hide(); 
			} 
		}
		
		<%--PCRF网元动态切换tab页面--%>
		function net_item(n){
			var menuli = $("#pcrfMenu li");
			menuli.each(function(index,element){
				$(element).removeClass();
			});
			$v = $("#pcrfPanel").children();
			$v.each(function(index,element){
				$(element).attr("class","no");
			});
			$("#li"+n).attr("class","active");
			$("#pcrfTab"+n).attr("class","con");
		}
		
		function showProcessText(){
			 
			 $.getJSON("${ctx}/userquery/signal/queryProcessText",{datetime:new Date().getTime(),netIds:$("#pcrfNetElementsId").val()},function(data){
				 if($("#mmePoolsId").val()!=""){
					 $("#mmePoolPanel").attr("class","pooldiv");
			 	 }
				 if($("#saegwPoolsId").val()!=""){
				 	 $("#epgPoolPanel").attr("class","pooldiv");
				 }
				 
				 for(var i=0;i<data.length;i++){
					if(f){
						if(data[i].text!=""&&data[i].text!="error"){
							var btnObj = document.getElementById('btnExecture');
							settime(btnObj);
							f = false;
						} 
						if(data[i].text=="error"){
							$("#loadingImg", parent.document).attr("src","${ctxStatic}/images/capture4.png");
						    return;
						}
					}
					if($("#pcrfNetElementsId").val()==""){
						if(data[i].text!=tempStr&&$("#captureScope").val()=="3"){
							tempStr = data[i].text;
							$("#pcrfTabPcrf div").append(tempStr);
							$("#pcrfTabPcrf").scrollTop($("#pcrfTabPcrf div").height() - $("#pcrfTabPcrf").height()+100);
						}
					}else{
						$("#pcrfTab"+data[i].netId+" div").append(data[i].text);
						$("#pcrfTab"+data[i].netId).scrollTop($("#pcrfTab"+data[i].netId+" div").height() - $("#pcrfTab"+data[i].netId).height()+100);
					}
				    if(data[i].netId == "mmePool"){
				    	 $("#mmePoolPanel div").append(data[i].text);
				    	 $("#mmePoolPanel").scrollTop($("#mmePoolPanel div").height() - $("#mmePoolPanel").height()+100);
				    }
				    if(data[i].netId == "epgPool"){
				    	 $("#epgPoolPanel div").append(data[i].text);
				    	 $("#epgPoolPanel").scrollTop($("#epgPoolPanel div").height() - $("#epgPoolPanel").height()+100);
				    }
				}
			});
		}
		
		
	</script>
	<script type="text/javascript"> 
	function settime(val) {
		if (countdown == 0) {
			$("#showtime", parent.document).text("");
			$("#loadingImg", parent.document).attr("src","${ctxStatic}/images/capture3.png");
			val.removeAttribute("disabled");
			val.value="开始追踪";
			countdown=parseInt($("#captureDuration").val())*60;
			if($("#pcrfNetElementsId").val()==""){
				if($("#mmePoolPanel div").text()!=""){
					$("#mmePoolPanel div").append("抓包完毕，生成并下载抓包文件...<br/>");
				}
				if($("#epgPoolPanel div").text()!=""){
					$("#epgPoolPanel div").append("抓包完毕，生成并下载抓包文件...<br/>");
				}
				if($("#captureScope").val()=="3"){
					$("#pcrfPanel div").eq(0).append("抓包完毕，生成并下载抓包文件...<br/>");
				}
			}else{
				if($("#mmePoolsId").val()!=""){
					$("#mmePoolPanel div").append("抓包完毕，生成并下载抓包文件...<br/>");
				}  
				if($("#saegwPoolsId").val()!=""){
					$("#epgPoolPanel div").append("抓包完毕，生成并下载抓包文件...<br/>");
				}
				if($("#pcrfNetElementsId").val()!=""){
					$("#pcrfPanel div").eq(0).append("抓包完毕，生成并下载抓包文件...<br/>");
				}
			}
		}   
		else {
			$("#loadingImg", parent.document).attr("src","${ctxStatic}/images/capture2.png");
			$("#showtime", parent.document).text("抓包剩余时间" + countdown + "秒");
			//val.setAttribute("disabled", true);
			val.value="抓包剩余时间" + countdown + "s";
			countdown--;
			setTimeout(function() {
				settime(val)
			},1000)
		}
	}
</script> 
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active" id="menu1" onclick="setTab('menu',1,2)"><a>单用户信令追踪</a></li>
		<li id="menu2" onclick="setTab('menu',2,2)"><a>详细日志</a></li>
	</ul>
	<div id="con_menu_1" class="hover"> 
		<form:form id="inputForm" modelAttribute="userSignalTraceEntity" action="${ctx}/userquery/signal/trace" method="post" class="breadcrumb form-search">
			<ul class="ul-form">
			    <li>
				   <form:select path="numberType" items="${fns:getDictList('biz_userquery_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-medium" style="margin-left:26px;width:120px;"/>
				   <form:input id="number" path="number" htmlEscape="false" maxlength="50" class="input-medium" style="width:130px;"/>
				</li>
				<li>
				   <label>抓包时长:</label>
				   <form:select id="captureDuration" path="captureDuration" class="input-medium">
	                   <form:option value="" label="请选择"/>
	 				   <form:options items="${fns:getDictList('biz_capture_duration')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	               </form:select>
				</li>
				
				<li>
				   <label>抓包范围:</label>
				   <form:select id="captureScope" path="captureScope" class="input-medium">
	                   <form:option value="" label="请选择"/>
	 				   <form:options items="${fns:getDictList('biz_capture_scope')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	               </form:select>
				</li>
				
				<li class="btns">
					<shiro:hasPermission name="userquery:signal:trace"><input id="btnExecture" class="btn-new btn-save" type="submit" value="开始追踪"/></shiro:hasPermission>
				</li>
				<c:if test="${isRedirect == 'true'}">
				<li style="float: right">
				    <span style="float: right;line-height: 28px;margin-top: 10px;"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 15px;float: left;margin-top: -2px;">返回</span></a></span>
				</li>
				</c:if>
			</ul>
			<ul class="ul-form hide" id="elementSelect">
				<li>
				   <label>MME网元:</label>
			       <sys:treeselect id="mmePools" name="mmePools" cssStyle="width:138px;" value="${userSignalTraceEntity.mmePools.id}" labelName="mmePools.fpoolname" labelValue="${userSignalTraceEntity.mmePools.fpoolname}"
					   title="MME" url="/userquery/signal/mmeElements?type=1" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
				</li>
				<li>
				   <label>SAEGW网元:</label>
				   <sys:treeselect id="saegwPools" name="saegwPools" cssStyle="width:138px;" value="${userSignalTraceEntity.saegwPools.id}" labelName="saegwPools.fpoolname" labelValue="${userSignalTraceEntity.saegwPools.fpoolname}"
				       title="SAEGW" url="/userquery/signal/saegwElements?type=2" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
				</li>
				<li>
				   <label>PCRF网元:</label>
				   <sys:treeselect id="pcrfNetElements" name="pcrfNetElements" cssStyle="width:138px;" value="${userSignalTraceEntity.pcrfNetElements.id}" labelName="pcrfNetElements.fname" labelValue="${userSignalTraceEntity.pcrfNetElements.fname}"
					   title="PCRF" url="/userquery/signal/pcrfElements?type=3" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
				</li>
				
			 </ul>
		</form:form>
		<sys:message content="${message}"/>
		<div class="panel panel-default" id="mmePool_outside_div">
		   <div class="panel-heading" >
		   <ul style="list-style:none;height:28px;">
		      <li style="float:left;line-height:38px">
		      	<p class="panel-title" style="font-size: 14px; float:left; margin-left: 15px;">MME网元追踪日志</p>
		      </li>
		      <li style="float:left;margin-left:30%;line-height:38px"></li>
		      <li style="float:right;margin-right:30px;line-height:38px;">
			      <a href="${ctx}/userquery/signal/detailLog.do?logType=MME" style="cursor:hand;" onclick="switchTab();" target="logFrame">
			      <span class="icon-q" class="panel-title" style="margin-top:6px;"></span>
			      <p class="panel-title">切换详细日志</p></a>
		      </li>
		   </ul>
		   </div>
		   <div class="panel-body" id="mmePoolPanel" style="height:100px;">
		   	<div></div>
		   </div>
		</div>
		<div class="panel panel-default" id="epgPool_outside_div">
		   <div class="panel-heading" >
		   <ul style="list-style:none;height:28px;">
		      <li style="float:left;line-height:38px">
		      		<p class="panel-title" style="font-size: 14px; float:left; margin-left: 15px;">SAEGW网元追踪日志</p>
		      </li>
		      <li style="float:left;margin-left:30%;line-height:38px"></li>
		      <li style="float:right;margin-right:30px;line-height:38px">
		        <a href="${ctx}/userquery/signal/detailLog.do?logType=EPG" style="cursor:hand;" onclick="switchTab();" target="logFrame">
		      	<span class="icon-q" class="panel-title" style="margin-top:6px;"></span>
			    <p class="panel-title">切换详细日志</p>
			    </a>
		      </li>
		   </ul>
		   </div>
		   <div class="panel-body" id="epgPoolPanel" style="height:100px;">
		   	<div></div>
		   </div>
		</div>
		
		<div class="panel panel-default" id="pcrfPool_outside_div">
		   <div class="panel-heading" >
		   <ul style="list-style:none;height:28px;">
		      <li style="float:left;line-height:38px">
			      <p class="panel-title" style="font-size: 14px; float:left; margin-left:15px;">PCRF网元追踪日志</p>
		      </li>
		      <li style="float:left;margin-left:30%;line-height:38px"></li>
		      <li style="float:right;margin-right:30px;line-height:38px;font-size:38px;">
		        <a href="${ctx}/userquery/signal/detailLog.do?logType=PCRF" style="cursor:hand;" onclick="switchTab();" target="logFrame">
		      	<span class="icon-q" class="panel-title" style="margin-top:6px;"></span>
			    <p class="panel-title">切换详细日志</p>
			    </a>
		      </li>
		   </ul>
		   </div>
		   <div>
	   		 <ul class="nav nav-tabs" id="pcrfMenu"></ul>
		   </div>
		   <div class="panel-body" id="pcrfPanel" style="height:180px;">
				
		   </div>
		</div>
	
	</div>
	
	<div id="con_menu_2" style="display:none">
		<iframe id="logFrame" name="logFrame" scrolling="yes" frameborder="0" style="width:100%;height:600px;" ></iframe> 
	</div>
</body>
</html>