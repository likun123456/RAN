<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户信令追踪配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/userquery/CapConfig/">单用户信令追踪配置</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="capConfig" action="${ctx}/userquery/CapConfig/update" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">CPU门限：</label>
			<div class="controls">
			<form:hidden path="id"/>
				<form:input path="cpuLimit" style="width:160px;" class="required digits input-small"/>
				<font>%</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">磁盘门限：</label>
			<div class="controls">
				<form:input path="diskLimit" style="width:160px;" class="required digits input-small" />
				<font>%</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查周期：</label>
			<div class="controls">
				<form:input path="checkPeriod" style="width:160px;" class="required digits input-small" />
				<font>单位(秒)</font>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>