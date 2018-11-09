<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>EXCEL模版管理</title>
	<meta name="decorator" content="default"/>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<!-- <li ><a href="${ctx}/netconfig/excelTemplateDebug/list">查看模版</a></li>-->
		<!-- <li class="active"><a href="${ctx}/netconfig/excelTemplateDebug/form?id=${tVisExcelModuleDetail.id}">${not empty tVisExcelModuleDetail.id?'修改':'添加'}指令</a></li> -->
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tVisExcelModuleDetail" action="${ctx}/netconfig/excelTemplateDebug/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">执行序号:</label>
			<div class="controls">
				<form:input path="executeNo" htmlEscape="false" maxlength="50" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">指令名称:</label>
			<div class="controls">
				<form:input path="commandName" htmlEscape="false" maxlength="50" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">前台提示:</label>
			<div class="controls">
				<form:input path="formRemark" htmlEscape="false" maxlength="255" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">调试标识:</label>
			<div class="controls">
				<form:input path="debugRemark" htmlEscape="false" maxlength="255" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">指令:</label>
			<div class="controls">
				<form:input path="confCmd" htmlEscape="true" maxlength="500" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提示符(指令执行前):</label>
			<div class="controls">
				<form:input path="beforePrompt" htmlEscape="true" maxlength="20" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提示符(指令执行后):</label>
			<div class="controls">
				<form:input path="afterPrompt" htmlEscape="true" maxlength="20" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结果正则表达式:</label>
			<div class="controls">
				<form:input path="checkRegexp" htmlEscape="true" maxlength="200" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">超时时间(秒):</label>
			<div class="controls">
				<form:input path="timeout" htmlEscape="false" maxlength="20" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">错误处理方式:</label>
			<div class="controls">
				<form:input path="errorHandleMode" htmlEscape="true" maxlength="200" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变量正则表达式:</label>
			<div class="controls">
				<form:input path="varArrayRegexp" htmlEscape="true" maxlength="100" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">变量名称:</label>
			<div class="controls">
				<form:input path="varArray" htmlEscape="false" maxlength="200" class="input-xlarge " style="width:900px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="5" maxlength="300" class="input-xxlarge" style="width:900px;"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>