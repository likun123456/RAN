<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案例库管理</title>
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
		<li class="active"><a href="${ctx}/performance/tCaseLibrary/">案例库管理</a></li>
		<shiro:hasPermission name="performance:tCaseLibrary:edit"><li><a href="${ctx}/performance/tCaseLibrary/form">添加案例库</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCaseLibrary" action="${ctx}/performance/tCaseLibrary/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>CC(原因值):</label>
				<form:input path="cc" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>SCC(原因值):</label>
				<form:input path="scc" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>CC(原因值)</th>
			    <th>SCC(子原因值)</th>
			    <th>问题描述</th>
			    <th>原因描述</th>
				<shiro:hasPermission name="performance:tCaseLibrary:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCaseLibrary">
			<tr>
			    <td>${tCaseLibrary.cc}</td>
			    <td>${tCaseLibrary.scc}</td>
			    <td>${tCaseLibrary.questiondescrible}</td>
			    <td>${tCaseLibrary.questiondescrible}</td>
				<shiro:hasPermission name="performance:tCaseLibrary:edit"><td>
    				<a href="${ctx}/performance/tCaseLibrary/form?id=${tCaseLibrary.id}">修改</a>
					<a href="${ctx}/performance/tCaseLibrary/delete?id=${tCaseLibrary.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>