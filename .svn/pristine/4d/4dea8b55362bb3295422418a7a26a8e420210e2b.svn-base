<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈类型管理</title>
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
		<li><a href="${ctx}/cheat/tCheatChinese/">欺诈类型列表</a></li>
		<li class="active"><a href="${ctx}/cheat/tCheatChinese/form?id=${tCheatChinese.id}">欺诈类型<shiro:hasPermission name="cheat:tCheatChinese:edit">${not empty tCheatChinese.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cheat:tCheatChinese:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tCheatChinese" action="${ctx}/cheat/tCheatChinese/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">欺诈类型：</label>
			<div class="controls">
				<form:input path="cheatCase" htmlEscape="false" maxlength="50" class="required input-xxlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">欺诈类型中文翻译：</label>
			<div class="controls">
				<form:input path="cheatCaseChinese" htmlEscape="false" maxlength="25" class="required input-xxlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">漏洞类型：</label>
			<div class="controls">
			    <form:textarea path="loopholeType" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">欺诈原理：</label>
			<div class="controls">
				<form:textarea path="cheatPrinciple" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">欺诈场景：</label>
			<div class="controls">
				<form:textarea path="cheatScene" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">欺诈场景分析：</label>
			<div class="controls">
				<form:textarea path="cheatSceneAnalysis" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">解决方案：</label>
			<div class="controls">
				<form:textarea path="solution" htmlEscape="false" rows="3" maxlength="1000" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cheat:tCheatChinese:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>