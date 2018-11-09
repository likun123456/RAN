<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采集器配置管理</title>
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
		<li><a href="${ctx}/collectset/tCollectordeploy/">采集器配置列表</a></li>
		<li class="active"><a href="${ctx}/collectset/tCollectordeploy/form?id=${tCollectordeploy.id}">采集器配置<shiro:hasPermission name="collectset:tCollectordeploy:edit">${not empty tCollectordeploy.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="collectset:tCollectordeploy:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tCollectordeploy" action="${ctx}/collectset/tCollectordeploy/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">采集器名称:</label>
			<div class="controls">
				<form:input path="collectorname" htmlEscape="false" maxlength="100" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IP地址:</label>
			<div class="controls">
				<form:input path="ip" htmlEscape="false" maxlength="100" class="required input-xlarge ip"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">FTP账号:</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">FTP密码:</label>
			<div class="controls">
				<form:input path="password" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ebmlog数据源：</label>
			<div class="controls">
				<form:radiobuttons path="coredataebmlog" items="${fns:getDictList('biz_epg_datasouce_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sub数据源：</label>
			<div class="controls">
				<form:radiobuttons path="coredatasub" items="${fns:getDictList('biz_epg_datasouce_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ebmlog池组数据源：</label>
			<div class="controls">
				<form:radiobuttons path="coreebmlog" items="${fns:getDictList('biz_epg_datasouce_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="collectset:tCollectordeploy:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>