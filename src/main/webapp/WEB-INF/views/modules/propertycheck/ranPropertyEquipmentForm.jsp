<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产信息管理</title>
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
		<li><a href="${ctx}/propertycheck/ranPropertyEquipment/">资产信息列表</a></li>
		<li class="active"><a href="${ctx}/propertycheck/ranPropertyEquipment/form?id=${ranPropertyEquipment.id}">资产信息<shiro:hasPermission name="propertycheck:ranPropertyEquipment:edit">${not empty ranPropertyEquipment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="propertycheck:ranPropertyEquipment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<%-- <form:form id="inputForm" modelAttribute="ranPropertyEquipment" action="${ctx}/propertycheck/ranPropertyEquipment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">串口序列号：</label>
			<div class="controls">
				<form:input path="serialnumber" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">基站点：</label>
			<div class="controls">
				<form:input path="sitename" htmlEscape="false" maxlength="12" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">管理对象：</label>
			<div class="controls">
				<form:input path="managerobject" htmlEscape="false" maxlength="205" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="productname" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品型号：</label>
			<div class="controls">
				<form:input path="productnumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品修订：</label>
			<div class="controls">
				<form:input path="productionrevision" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品出厂日期：</label>
			<div class="controls">
				<form:input path="productiondate" htmlEscape="false" maxlength="8" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三方厂商ID：</label>
			<div class="controls">
				<form:input path="manufacturerid" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三制造方修订：</label>
			<div class="controls">
				<form:input path="manufacturerrevision" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">第三方协定比特率：</label>
			<div class="controls">
				<form:input path="negotiatedbitrate" htmlEscape="false" maxlength="5" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">设备状态：0:无效设备，1：新设备，2:复用设备：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">log日志的生成时间：</label>
			<div class="controls">
				<input name="logdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${ranPropertyEquipment.logdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对象类型：</label>
			<div class="controls">
				<form:input path="mocategory" htmlEscape="false" maxlength="1" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">扩展字段：</label>
			<div class="controls">
				<form:input path="extendfield" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="propertycheck:ranPropertyEquipment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form> --%>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-0" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;0</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-1" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;1</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-2" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;2</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-3" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;3</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-4" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;4</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-5" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;5</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-6" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;6</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-7" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;7</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-8" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;8</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-9" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;9</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-a" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;a</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-b" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;b</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-c" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;c</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-d" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;d</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-e" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;e</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-f" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;f</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-g" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;g</span>
										</a></br>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-h" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;h</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-i" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;i</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-j" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;j</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-k" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;k</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-l" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;l</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-m" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;m</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-n" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;n</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-o" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;o</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-p" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;p</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-q" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;q</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-r" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;r</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-s" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;s</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-t" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;t</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-u" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;u</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-v" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;v</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-w" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;w</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-x" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;x</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-y" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;y</span>
										</a>
<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="icon-z" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;z</span>
										</a>
</body>
</html>