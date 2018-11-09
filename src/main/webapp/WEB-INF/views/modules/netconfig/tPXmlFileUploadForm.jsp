<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	var names = [];
	$(document).ready(function() {
		$("#tpType").change(function(){
			var type = $("#tpType option:selected").val();
			$.post('${ctx}/netconfig/tTpXmlFileUpload/queryVisExcelListByType', {
			       "type": type
			    }, function(data) {
				    	var list = eval(data);
				    	$.each(list, function(index, o) { 
				    		names.push(o.name);
						}); 
			 });
		});
		
	    Array.prototype.contains = function(e){
	    	for(var i=0;i<this.length;i++){
	    		if(this[i] == e){
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    
		$("#searchForm").validate({
			submitHandler : function(form) {
				var uploadNames = $("#upload").val().split("\\");
				var uploadName = uploadNames[uploadNames.length-1];
				var uname = uploadName.substring(0,uploadName.indexOf("."));
				if($("#tpType").val() == ""){
					top.$.jBox.tip('请选择EOPS模板类型','warning');
				} else if ($("#upload").val() == "") {
					top.$.jBox.tip('请选择Excel文件','warning');
				} else if(names.contains(uname)){
					confirmx('已经上传过该模板，继续上传会将原模板覆盖',function(){
						loading('正在提交，请稍等...');
						form.submit();
					}); 
				} else{//表单提交
					loading('正在提交，请稍等...');
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
	});
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/netconfig/tTpXmlFileUpload/form">新增EOPS模板</a></li>
		<li><a href="${ctx}/netconfig/tTpXmlFileUpload/list">查看EOPS模板</a></li>
		<li><a href="${ctx}/netconfig/tTpXmlFileUpload/downloadExcelTemplatelist">下载EOPS模板</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tVisExcel"
		action="${ctx}/netconfig/tTpXmlFileUpload/uploadFile" method="post"
		enctype="multipart/form-data" class="form-horizontal">
		<sys:message content="${message}" />
		<br>
		<br>
		<div style="margin:0 auto;width:50%;">
		<div class="control-group">
			<img src="${ctxStatic}/images/eops.jpg" alt="eops" width="440" height="120" />
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 18px; font-weight: bold; ">网元类型</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<form:select id="type" path="type" style="width:250px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 18px; font-weight: bold; ">EOPS模板类型</label>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<form:select id="tpType" path="templatetype" style="width:250px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_eops_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
		</div>
		<div class="control-group">
			<label class="control-label" style="font-size: 18px; font-weight: bold; ">EOPS模板文件</label>
			<div class="controls">
				<input type="file" name="upload" id="upload" label="选拓扑xml文件">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btnSubmit" type="submit" style="background: url('${ctxStatic}/images/icon-import.png'); width:100px; height:35px;border:0px" value="">
			</div>
		</div>
		</div>
		
	</form:form>
</body>
</html>