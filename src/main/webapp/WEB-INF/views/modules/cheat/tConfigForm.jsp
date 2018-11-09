<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抓包参数设置管理</title>
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
		<li><a href="${ctx}/cheat/tConfig/">抓包参数设置列表</a></li>
		<li class="active"><a href="${ctx}/cheat/tConfig/form?id=${tConfig.id}">抓包参数设置<shiro:hasPermission name="cheat:tConfig:edit">${not empty tConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cheat:tConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tConfig" action="${ctx}/cheat/tConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">批量打包免费流量门限(byte)：</label>
			<div class="controls">
				<form:input path="freeflow" htmlEscape="false" maxlength="15" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">免费流量门限(byte)：</label>
			<div class="controls">
				<form:input path="freeflowfilter" htmlEscape="false" maxlength="15" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">免费流量占比门限：</label>
			<div class="controls">
				<form:input path="freepercent" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">checkusercount：</label>
			<div class="controls">
				<form:input path="checkusercount" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">抓包用户数：</label>
			<div class="controls">
				<form:input path="catchpackageusercount" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">currentprogramexecutiontime：</label>
			<div class="controls">
				<form:input path="currentprogramexecutiontime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">finishcatchpackagetime：</label>
			<div class="controls">
				<form:input path="finishcatchpackagetime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<shiro:hasPermission name="cheat:tConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>