<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />


<style type="text/css">

        #dialogBg{width:100%;height:100%;background-color:#000000;opacity:.6;filter:alpha(opacity=60);position:fixed;top:0;left:0;z-index:9999;display:none;}
		#dialog1{width:600px;height:210px;margin:0 auto;display:none;background-color:#ffffff;position:fixed;top:40%;left:40%;margin:-120px 0 0 -150px;z-index:10000;border:1px solid #ccc;border-radius:10px;-webkit-border-radius:10px;box-shadow:3px 2px 4px #ccc;-webkit-box-shadow:3px 2px 4px #ccc;}
		.dialogTop{width:90%;margin:0 auto;border-bottom:1px dotted #ccc;letter-spacing:1px;padding:5px 0;text-align:right;}
		.dialogContent{
			float:left;
			width:98%;
			height:300px;
			overflow:auto;
			margin:0 auto;
		}
</style>
<script type="text/javascript">
	var names = [];
	$(document).ready(function() {
		if($("#tpType").val()!=""){
			window.location.reload();
		}
		$("#tpType").change(function(){
			var type = $("#type option:selected").val();
			var tpType = $("#tpType option:selected").val();
			$.post('${ctx}/netconfig/tTpXmlFileUpload/queryVisExcelListByType', {
			       "type": type,
			       "tpType":tpType
			    }, function(data) {
				    	var list = eval(data);
				    	$("#templateId option:not(:first)").remove();  
				    	$("#templateId").html("<option value=''>请选择</option>")
				    	$(".select2-chosen").eq(2).html("请选择");
				    	$.each(list, function(index, o) { 
				    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
				    		$("#templateId").append(html);
						}); 
			 });
		});
		$("#type").change(function(){
			var type = $("#type option:selected").val();
			var tpType = $("#tpType option:selected").val();
			$.post('${ctx}/netconfig/tTpXmlFileUpload/queryVisExcelListByType', {
			       "type": type,
			       "tpType":tpType
			    }, function(data) {
				    	var list = eval(data);
				    	$("#templateId option:not(:first)").remove();  
				    	$("#templateId").html("<option value=''>请选择</option>")
				    	$(".select2-chosen").eq(2).html("请选择");
				    	$.each(list, function(index, o) { 
				    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
				    		$("#templateId").append(html);
						}); 
			 });
		});
		
		
	    
		$("#searchForm").validate({
			submitHandler : function(form) {
				if($("#tpType").val() == ""){
					top.$.jBox.tip('请选择EOPS模板类型','warning');
				} else if ($("#templateId option:selected").val() == "") {
					top.$.jBox.tip('请选择EOPS模板','warning');
				} else if($("#tpType").val() == 'EMERGENCYTEM'){
					$('#dialogBg').fadeIn(300);
					$('#dialog1').removeAttr('class').addClass('animated bounceIn').fadeIn();
				} else {
					    //alert($("#templateId option:selected").val());
						$("#searchForm").attr('action',"${ctx}/netconfig/tMme/showExcelTemp?tempId="+$("#templateId option:selected").val());
						//loading('正在提交，请稍等...');
						form.submit();
				}
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")|| element.is(":radio")|| element.parent().is(".input-append")) {
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
		
		//关闭弹窗
		$('.claseDialogBtn1').click(function(){
			$('#dialogBg').fadeOut(300,function(){
				$('#dialog1').addClass('bounceOutUp').fadeOut();
			});
		});
		
	});
	
	function checkEmergencyCode() {
		var authcode = $("#authcode").val();
		$.ajax({
			type : "POST",
			url : "${ctx}/netconfig/tMme/checkEmergencyCode",
			dataType : "html",
			data : {"authcode" : authcode},
			success:function(data){
				if(data == "fail") {
					top.$.jBox.tip('授权码不正确请重新输入','warning');
				} else {
					$("#searchForm").attr('action',"${ctx}/netconfig/tMme/showExcelTemp?tempId="+$("#templateId option:selected").val());
					document.forms[0].submit();
				}
			}
	   });
	}
	
</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="tVisExcel"
		action="${ctx}/netconfig/tMme/showExcelTemp" method="post"
	    class="form-horizontal">
		<sys:message content="${message}" />
		<br>
		<br>
		<div style="margin:0 auto;width:50%;">
		<div class="control-group">
			<img src="${ctxStatic}/images/eops.jpg" alt="eops" width="440" height="120" />
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 18px; font-weight: bold; ">EOPS模板类型</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<form:select id="tpType" path="type" style="width:250px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_eops_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 18px; font-weight: bold; ">EOPS网元类型</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<form:select id="type" path="type" style="width:250px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 18px; font-weight: bold; ">EOPS模板文件</label>
			<div class="controls">
			<select id="templateId" style="width:250px;"></select>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input id="btnSubmit" type="submit" style="background: url('${ctxStatic}/images/icon-select2.png'); width:100px; height:35px;border:0px" value="">
			</div>
		</div>
		</div>
		
	</form:form>
	
	<div id="dialogBg"></div>
	
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