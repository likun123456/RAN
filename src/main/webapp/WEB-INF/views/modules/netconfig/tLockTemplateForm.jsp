<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>闭锁模版管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		<li><a href="${ctx}/netconfig/tLockTemplate/">闭锁模版管理列表</a></li>
		<li class="active"><a href="${ctx}/netconfig/tLockTemplate/form?id=${tLockTemplate.id}">闭锁模版信息<shiro:hasPermission name="netconfig:tLockTemplate:view">修改</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tLockTemplate" action="${ctx}/netconfig/tLockTemplate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">网元类型：</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">板卡名称：</label>
			<div class="controls">
				<form:input path="cardname" htmlEscape="false" maxlength="50" class="input-xlarge required" disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">闭锁模版：</label>
			<div class="controls">
				<form:select path="lockId" class="required input-xlarge"
					style="width:200px;" id="excelId">
					<form:options items="${visExcelList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">解锁模版：</label>
			<div class="controls">
				<form:select path="unLockId" class="required input-xlarge"
					style="width:200px;" id="excelId">
					<form:options items="${visExcelList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重启模版：</label>
			<div class="controls">
				<form:select path="reBootId" class="required input-xlarge"
					style="width:200px;" id="excelId">
					<form:options items="${visExcelList}" itemLabel="name"
						itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tLockTemplate:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>