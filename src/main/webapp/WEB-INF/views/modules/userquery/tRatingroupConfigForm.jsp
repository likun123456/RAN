<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>池组表单管理</title>
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
		<li><a href="${ctx}/userquery/ratingroupConfig/">话单业务配置</a></li>
		<li class="active"><a href="${ctx}/userquery/ratingroupConfig/form?id=${tPool.id}"><shiro:hasPermission name="userquery:ratingroupConfig:edit">${not empty tRatingroupConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="userquery:ratingroupConfig:edit">查看</shiro:lacksPermission>话单业务</a></li>
		<shiro:hasPermission name="userquery:ratingroupConfig:import"><li><a href="${ctx}/userquery/ratingroupConfig/importForm">导入</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tRatingroupConfig" action="${ctx}/userquery/ratingroupConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">业务代码:</label>
			<div class="controls">
			    <form:input path="ratinggroup" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">业务名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="userquery:ratingroupConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>