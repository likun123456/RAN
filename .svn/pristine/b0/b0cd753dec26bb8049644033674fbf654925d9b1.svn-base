<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备链路管理</title>
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
		<li><a href="${ctx}/netconfig/tEdges/">设备链路列表</a></li>
		<li class="active"><a href="${ctx}/netconfig/tEdges/edit?id=${tEdges.id}">设备链路<shiro:hasPermission name="netconfig:tEdges:edit">修改</shiro:hasPermission><shiro:lacksPermission name="netconfig:tEdges:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tEdges" action="${ctx}/netconfig/tEdges/update" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">机房：</label>
			<div class="controls">
				<form:input path="roomName" htmlEscape="false" maxlength="11" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">始端设备：</label>
			<div class="controls">
				<form:input path="fromEquName" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">始端设备IP：</label>
			<div class="controls">
				<form:input path="fromEquIp" htmlEscape="false" maxlength="20" class="ip input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对端设备：</label>
			<div class="controls">
				<form:input path="toEquName" htmlEscape="false" maxlength="100" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对端设备IP：</label>
			<div class="controls">
				<form:input path="toEquIp" htmlEscape="false" maxlength="20" class="ip input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tEdges:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>