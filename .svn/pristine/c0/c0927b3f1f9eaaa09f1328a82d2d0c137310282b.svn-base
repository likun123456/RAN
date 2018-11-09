<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信令追踪DIAMETER端口设置管理</title>
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
		<li><a href="${ctx}/userquery/tDiameterConfig/">信令追踪DIAMETER端口设置列表</a></li>
		<li class="active"><a href="${ctx}/userquery/tDiameterConfig/form?id=${tDiameterConfig.id}">信令追踪DIAMETER端口设置<shiro:hasPermission name="userquery:tDiameterConfig:edit">${not empty tDiameterConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="userquery:tDiameterConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tDiameterConfig" action="${ctx}/userquery/tDiameterConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">DIAMETER 上层协议类型：</label>
			<div class="controls">
				<form:select path="diameterType" class="input-xlarge ">
					<form:options items="${fns:getDictList('biz_diameter_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">DIAMETER 端口号：</label>
			<div class="controls">
				<form:input path="diameterPort" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="userquery:tDiameterConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>