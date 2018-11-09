<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>CG管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					fname: {remote: "${ctx}/netconfig/tCg/checkName?oldName=" + encodeURIComponent("${tCg.fname}")},
				},
				messages: {
					fname: {remote: "CG名已存在"},
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
		<li><a href="${ctx}/netconfig/tCg/">CG列表</a></li>
		<li class="active"><a href="${ctx}/netconfig/tCg/form?id=${tCg.id}"><shiro:hasPermission name="netconfig:tCg:edit">${not empty tCg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tCg:edit">查看</shiro:lacksPermission>CG</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tCg" action="${ctx}/netconfig/tCg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">CG名称:</label>
			<div class="controls">
				<form:input path="fname" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">CG地址:</label>
			<div class="controls">
				<form:input path="faddress" htmlEscape="false" maxlength="50" class="required input-xlarge ip"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录用户名:</label>
			<div class="controls">
				<form:input path="fusername" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录密码:</label>
			<div class="controls">
				<form:input path="fpassword" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属机房:</label>
			<div class="controls">
				<form:select path="croom" class="input-xlarge">
					<form:options items="${crList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tCg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>