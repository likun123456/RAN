<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />
<%@include file="/WEB-INF/views/include/treetable.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		parent.$('#jbox-states').css("display","none");
		$("#treeTable").treeTable({expandLevel : 1}).show();
	});
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/netconfig/tTpXmlFileUpload/form">新增EOPS模板</a></li>
		<li class="active"><a href="${ctx}/netconfig/tTpXmlFileUpload/list">查看EOPS模板</a></li>
		<li><a href="${ctx}/netconfig/tTpXmlFileUpload/downloadExcelTemplatelist">下载EOPS模板</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="tVisExcel" action="${ctx}/netconfig/tTpXmlFileUpload/list" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		    <li><label>模板类型:</label>
				<form:select id="tpType" path="templatetype" style="width:250px;">
	              <form:option value="" label="请选择"/>           
				  <form:options items="${fns:getDictList('biz_eops_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	         	</form:select>
			</li>
			<li><label>网元类型:</label>
				<form:select id="type" path="type" style="width:250px;">
	              <form:option value="" label="请选择"/>           
				  <form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
	         	</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide">
			<thead><tr><th>名称</th><th>模板类型</th><th>网元类型</th><th>命令</th><th>操作</th></thead>
			<tbody><c:forEach items="${list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parentId ne '1'?menu.parentId:'0'}">
					<td nowrap><a href="${ctx}/netconfig/visExcelTemplate/form?id=${menu.id}">${menu.name}</a></td>
					<td>${fns:getDictLabel(menu.templatetype,'biz_eops_type','')}</td>
					<td>${fns:getDictLabel(menu.type,'biz_net_type','')}</td>
					<td title="${menu.confCmd}">${fns:abbr(menu.confCmd,30)}</td>
					<td nowrap>
						<c:if test="${fn:indexOf(menu.id, '_') eq -1}">
						<a href="${ctx}/netconfig/visExcelTemplate/delete?id=${menu.id}" onclick="return confirmx('要删除该模板及所有子模板项吗？', this.href)">删除</a>
						<a href="${ctx}/netconfig/tTpXmlFileUpload/export?id=${menu.id}&&templatename=${fns:urlEncode(menu.name)}">导出</a>
						</c:if>
					</td>
				</tr>
			</c:forEach></tbody>
		</table>
	 </form>
</body>
</html>