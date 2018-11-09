<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈包提取</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cheat/cheatPackageDown/down">欺诈包提取</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="inputForm" modelAttribute="tCheatPackage" action="${ctx}/cheat/cheatPackageDown/downPackage" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">导出网元：</label>
			<div class="controls">
			    <form:select path="netId" class="input-xlarge" style="width:180px;" id="newNetelementlist">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">导出日期：</label>
			<div class="controls">
				<form:input path="dateTime" class="txt date Wdate" style="width:160px;" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="下载"/>
		</div>
	</form:form>
</body>
</html>