<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>池组表单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					fpoolname: {remote: "${ctx}/netconfig/tPool/checkName?oldName=" + encodeURIComponent("${tPool.fpoolname}")},
				},
				messages: {
					fpoolname: {remote: "池组名已存在"},
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
		<li><a href="${ctx}/netconfig/tPool/">池组信息</a></li>
		<li class="active"><a href="${ctx}/netconfig/tPool/form?id=${tPool.id}"><shiro:hasPermission name="netconfig:tPool:edit">${not empty tPool.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tPool:edit">查看</shiro:lacksPermission>池组</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tPool" action="${ctx}/netconfig/tPool/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">池组类型:</label>
			<div class="controls">
			    <form:select path="ftype" class="required input-xlarge">
					<form:options items="${fns:getDictList('biz_pool_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">池组名称:</label>
			<div class="controls">
				<form:input path="fpoolname" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tPool:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>