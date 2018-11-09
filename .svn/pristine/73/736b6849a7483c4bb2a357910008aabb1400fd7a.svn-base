<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网元参数提取设置</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/paramconfig/paramCollectConfig/">网元参数提取设置</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="paramCollectConfig" action="${ctx}/paramconfig/paramCollectConfig/update" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">提取时间间隔：</label>
			<div class="controls">
			<form:hidden path="id"/>
			<form:hidden path="biztype"/>
			    <form:select path="type" class="required input-xlarge">
					<form:options items="${fns:getDictList('biz_param_collect_config')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提取时间：</label>
			<div class="controls">
				<form:input path="collecttime" class="txt date Wdate" style="width:160px;" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:true});"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>