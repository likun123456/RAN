<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>PCRF节点信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					fname: {remote: "${ctx}/netconfig/tNewnetelement/checkName?oldName=" + encodeURIComponent("${tNewnetelement.fname}")},
				},
				messages: {
					fname: {remote: "网元名已存在"},
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
		<li><a href="${ctx}/netconfig/tNewnetelement/?poolType=PCRF">PCRF节点信息列表</a></li>
		<li class="active"><a href="#"><shiro:hasPermission name="netconfig:tNewnetelement:edit">${not empty tNewnetelement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tNewnetelement:edit">查看</shiro:lacksPermission>PCRF网元信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tNewnetelement" action="${ctx}/netconfig/tNewnetelement/save?poolType=PCRF" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="type" value="${fns:getDictValue('PCRF', 'biz_net_type', 3)}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属PCRF池组:</label>
			<div class="controls">
			    <form:select path="fnid" class="required input-xlarge">
					<form:options items="${poolList}" itemLabel="fpoolname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元名称:</label>
			<div class="controls">
				<form:input path="fname" htmlEscape="false" maxlength="20" class="required input-xlarge "/><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">PCRF软件版本:</label>
			<div class="controls">
			    <form:select path="softwareversion" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_pcrf_soft_version')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">板卡标识:</label>
			<div class="controls">
			    <form:input path="cardcode" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
			    <font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">IO2 IP地址:</label>
			<div class="controls">
				<form:input path="ipadr" htmlEscape="false" maxlength="100" class="ip input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<form:input path="username1" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<form:input path="password1" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网管 IP地址:</label>
			<div class="controls">
				<form:input path="adminipadr" htmlEscape="false" maxlength="100" class="ip input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<form:input path="adminname" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<form:input path="adminpassword" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计文件FTP路径:</label>
			<div class="controls">
				<form:input path="countftpurl" htmlEscape="false" maxlength="100" style="width:600px;" class="required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属OSS:</label>
			<div class="controls">
				<form:select path="oss" class="input-xlarge">
					<form:options items="${ossList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属EPA:</label>
			<div class="controls">
				<form:select path="epa" class="input-xlarge">
					<form:options items="${epaList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">EBM LOG路径:</label>
			<div class="controls">
				<form:input path="ebmlog" htmlEscape="false" maxlength="100" class="input-xlarge " style="width:600px;"/>
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
			<shiro:hasPermission name="netconfig:tNewnetelement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>