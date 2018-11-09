<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>CG节点话单目录表单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
		<li><a href="${ctx}/netconfig/tCgCdrpath/">CG节点话单目录</a></li>
		<li class="active"><a href="${ctx}/netconfig/tCgCdrpath/form?id=${tCgCdrpath.id}"><shiro:hasPermission name="netconfig:tCgCdrpath:edit">${not empty tCgCdrpath.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tCgCdrpath:edit">查看</shiro:lacksPermission>话单目录</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tCgCdrpath" action="${ctx}/netconfig/tCgCdrpath/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">网元名称:</label>
			<div class="controls">
			    <form:select path="netId" class="input-xlarge">
					<form:options items="${netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属CG:</label>
			<div class="controls">
			    <form:select path="cgId" class="input-xlarge">
					<form:options items="${cgList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目录名称:</label>
			<div class="controls">
				<form:input path="path" htmlEscape="false" maxlength="200" class="required input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tCgCdrpath:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>