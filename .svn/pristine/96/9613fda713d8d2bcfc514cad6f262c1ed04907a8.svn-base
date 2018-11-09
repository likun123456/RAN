<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OSS节点基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					fname: {remote: "${ctx}/netconfig/tOss/checkName?oldName=" + encodeURIComponent("${tOss.fname}")},
				},
				messages: {
					fname: {remote: "OSS名已存在"},
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
		<li><a href="${ctx}/netconfig/tOss/">OSS节点基本信息列表</a></li>
		<li class="active"><a href="${ctx}/netconfig/tOss/form?id=${tOss.id}">OSS节点基本信息<shiro:hasPermission name="netconfig:tOss:edit">${not empty tOss.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tOss:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tOss" action="${ctx}/netconfig/tOss/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">OSS名称:</label>
			<div class="controls">
				<form:input path="fname" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">OSS地址:</label>
			<div class="controls">
				<form:input path="faddress" htmlEscape="false" maxlength="50" class="required input-xlarge ip"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<form:input path="fusername" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<form:input path="fpassword" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tOss:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>