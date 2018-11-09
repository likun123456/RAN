<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>MME节点信息管理</title>
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
		<li><a href="${ctx}/netconfig/tNewnetelement/?poolType=MME">MME节点信息列表</a></li>
		<li class="active"><a href="#"><shiro:hasPermission name="netconfig:tNewnetelement:edit">${not empty tNewnetelement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tNewnetelement:edit">查看</shiro:lacksPermission>MME网元信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tNewnetelement" action="${ctx}/netconfig/tNewnetelement/save?poolType=MME" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="type" value="${fns:getDictValue('MME', 'biz_net_type', 1)}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属MME池组:</label>
			<div class="controls">
			    <form:select path="fnid" class="required input-xlarge">
					<form:options items="${poolList}" itemLabel="fpoolname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元名称:</label>
			<div class="controls">
				<form:input path="fname" htmlEscape="false" maxlength="20" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MME HW类型:</label>
			<div class="controls">
			    <form:select path="hwtype" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_mme_hw_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MME软件版本:</label>
			<div class="controls">
			    <form:select path="softwareversion" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_mme_soft_version')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MME O&M IP地址:</label>
			<div class="controls">
				<form:input path="ipadr" htmlEscape="false" maxlength="100" class="ip input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MME 网管用户名:</label>
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
			<label class="control-label">MME ROOT用户名:</label>
			<div class="controls">
				<form:input path="username2" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码:</label>
			<div class="controls">
				<form:input path="password2" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
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
			<label class="control-label">EBM LOG路径:</label>
			<div class="controls">
				<form:input path="ebmlog" htmlEscape="false" maxlength="100" style="width:600px;" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">MME最大附着用户数:</label>
			<div class="controls">
				<form:input path="mmemaxuserattached" htmlEscape="false" maxlength="100" class="input-xlarge "/>
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