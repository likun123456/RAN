<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>模板管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/netconfig/visExcelTemplate/checkName1?oldName=" + encodeURIComponent("${tVisExcel.name}")},
				},
				messages: {
					name: {remote: "模板名已存在"},
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/netconfig/tTpXmlFileUpload/form">新增EOPS模板</a></li>
		<li><a href="${ctx}/netconfig/tTpXmlFileUpload/list">查看EOPS模板</a></li>
		<li class="active"><a>EOPS模板修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tVisExcelModule" action="${ctx}/netconfig/visExcelTemplate/save2" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">模版:</label>
			<div class="controls">
			    <form:select path="excelId" class="required input-xlarge"
					style="width:200px;" id="excelId">
					<form:options items="${visExcelList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">模块名称:</label>
			<div class="controls">
				<form:input path="moduleName" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">模块类型:</label>
			<div class="controls">
				<form:input path="moduleType" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元类型:</label>
			<div class="controls">
				<form:input path="netType" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tCg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>