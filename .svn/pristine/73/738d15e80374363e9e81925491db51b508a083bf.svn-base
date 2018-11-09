<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SAEGW节点信息管理</title>
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
		<li><a href="${ctx}/netconfig/tNewnetelement/?poolType=SAEGW">SAEGW节点信息列表</a></li>
		<li class="active"><a href="#"><shiro:hasPermission name="netconfig:tNewnetelement:edit">${not empty tNewnetelement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tNewnetelement:edit">查看</shiro:lacksPermission>SAEGW网元信息</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tNewnetelement" action="${ctx}/netconfig/tNewnetelement/save?poolType=SAEGW" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="type" value="${fns:getDictValue('SAEGW', 'biz_net_type', 2)}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">归属SAEGW池组:</label>
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
			<label class="control-label">SAEGW类型:</label>
			<div class="controls">
			    <form:select path="epgtype" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_ggsn_epg_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SAEGW HW类型:</label>
			<div class="controls">
			    <form:select path="hwtype" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_epg_hw_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SAEGW软件版本:</label>
			<div class="controls">
			    <form:select path="softwareversion" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_epg_soft_version')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备厂商:</label>
			<div class="controls">
			    <form:select path="factory" class="required input-xlarge">
			    	<form:options items="${fns:getDictList('biz_net_factory')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SAEGW O&M IP地址:</label>
			<div class="controls">
				<form:input path="ipadr" htmlEscape="false" maxlength="100" class="ip input-xlarge required"/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SAEGW 网管用户名:</label>
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
			<label class="control-label">SAEGW CDR用户名:</label>
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
			<label class="control-label">归属OSS:</label>
			<div class="controls">
				<form:select path="oss" class="input-xlarge">
					<form:options items="${ossList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SGW最大承载数:</label>
			<div class="controls">
				<form:input path="sgwmaxbearers" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">SGW最大吞吐量:</label>
			<div class="controls">
				<form:input path="sgwmaxthroughput" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">PGW最大承载数:</label>
			<div class="controls">
				<form:input path="pgwmaxbearers" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">PGW最大吞吐量:</label>
			<div class="controls">
				<form:input path="pgwmaxthroughput" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GGSN最大PDP连接数:</label>
			<div class="controls">
				<form:input path="ggsnmaxpdpcontexts" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">GGSN最大吞吐量:</label>
			<div class="controls">
				<form:input path="ggsnmaxthroughput" htmlEscape="false" maxlength="100" class="input-xlarge "/>
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