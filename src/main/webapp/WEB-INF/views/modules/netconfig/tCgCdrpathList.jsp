<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>CG节点话单目录管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tCgCdrpath/">CG节点话单目录</a></li>
		<shiro:hasPermission name="netconfig:tCgCdrpath:edit"><li><a href="${ctx}/netconfig/tCgCdrpath/form">添加话单目录</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCgCdrpath" action="${ctx}/netconfig/tCgCdrpath/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>目录名称:</label>
				<form:input path="path" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>网元名称</th>
			    <th>所属CG</th>
			    <th>目录名称</th>
				<shiro:hasPermission name="netconfig:tCgCdrpath:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCgCdrpath">
			<tr>
			    <td><a href="${ctx}/netconfig/tCgCdrpath/form?id=${tCgCdrpath.id}">${tCgCdrpath.netName}</a></td>
			    <td>${tCgCdrpath.cgName}</td>
			    <td>${tCgCdrpath.path}</td>
				<shiro:hasPermission name="netconfig:tCgCdrpath:edit"><td>
    				<a href="${ctx}/netconfig/tCgCdrpath/form?id=${tCgCdrpath.id}">修改</a>
					<a href="${ctx}/netconfig/tCgCdrpath/delete?id=${tCgCdrpath.id}" onclick="return confirmx('确认要删除吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>