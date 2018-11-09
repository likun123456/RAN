<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OSS节点基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/netconfig/tScripts/">统计指标脚本配置列表</a></li>
		<shiro:hasPermission name="netconfig:tFormulaScripts:edit"><li><a href="${ctx}/netconfig/tScripts/form">统计指标脚本配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tFormulaScripts" action="${ctx}/netconfig/tScripts/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>脚本名称:</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>脚本名称</th>
				<th>脚本类型</th>
				<shiro:hasPermission name="netconfig:tFormulaScripts:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scripts">
			<tr>
				<td>
					${scripts.name}
				</td>
				<td>
				<c:if test="${scripts.scriptsType eq '1' }">
					预警脚本
				</c:if>
				<c:if test="${scripts.scriptsType eq '2' }">
					恢复脚本
				</c:if>
				<%-- <form:select path="scriptsType" class="required input-xlarge">
					<form:options items="${fns:getDictList('biz_scripts_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				</td>
				<shiro:hasPermission name="netconfig:tFormulaScripts:edit"><td>
    				<a href="${ctx}/netconfig/tScripts/form?id=${scripts.id}">修改</a>
					<a href="${ctx}/netconfig/tScripts/delete?id=${scripts.id}" onclick="return confirmx('确认要删除该条信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>