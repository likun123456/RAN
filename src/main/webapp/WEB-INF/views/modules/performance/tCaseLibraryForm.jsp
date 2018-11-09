<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案例库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/performance/tCaseLibrary/">案例库管理</a></li>
		<li class="active"><a href="${ctx}/performance/tCaseLibrary/form?id=${tCaseLibrary.id}"><shiro:hasPermission name="performance:tCaseLibrary:edit">${not empty tCaseLibrary.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="performance:tCaseLibrary:edit"></shiro:lacksPermission>案例库</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tCaseLibrary" action="${ctx}/performance/tCaseLibrary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">CC(原因值):</label>
			<div class="controls">
				<form:input path="cc" htmlEscape="false" maxlength="15" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SCC(子原因值):</label>
			<div class="controls">
				<form:input path="scc" htmlEscape="false" maxlength="25" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题描述:</label>
			<div class="controls">
			    <form:textarea path="questiondescrible" htmlEscape="false" rows="6" maxlength="400" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原因描述:</label>
			<div class="controls">
			    <form:textarea path="reasondescrible" htmlEscape="false" rows="6" maxlength="400" class="input-xxlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="performance:tCaseLibrary:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>