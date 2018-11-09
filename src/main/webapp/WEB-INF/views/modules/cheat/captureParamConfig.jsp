<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抓包参数设置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
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
		<li class="active"><a href="${ctx}/cheat/captureParam/config">计费欺诈抓包参数设置</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="captureParamConfig" action="${ctx}/cheat/captureParam/update" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">批量打包免费流量门限(byte):</label>
			<div class="controls">
			<form:hidden path="id"/>
			<form:input path="freeflowFilter" style="width:160px;" class="required digits input-small"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">免费流量门限(byte):</label>
			<div class="controls">
				<form:input path="freeflow" style="width:160px;" class="required digits input-small"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">免费流量占比门限:</label>
			<div class="controls">
				<form:input path="freePercent" style="width:160px;" class="required number input-small"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查用户数:</label>
			<div class="controls">
				<form:input path="checkUserCount" style="width:160px;" class="required digits input-small"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">抓包用户数:</label>
			<div class="controls">
				<form:input path="catchPackageUserCount" style="width:160px;" class="required digits input-small"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>