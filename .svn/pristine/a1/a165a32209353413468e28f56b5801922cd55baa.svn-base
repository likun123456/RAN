<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>池组管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tPool/">池组列表</a></li>
		<shiro:hasPermission name="netconfig:tPool:edit"><li><a href="${ctx}/netconfig/tPool/form">添加池组</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tPool" action="${ctx}/netconfig/tPool/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>池组名称:</label>
				<form:input path="fpoolname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>池组名称</th>
			    <th>池组类型</th>
				<shiro:hasPermission name="netconfig:tPool:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tPool">
			<tr>
			    <td><a href="${ctx}/netconfig/tPool/form?id=${tPool.id}">${tPool.fpoolname}</a></td>
			    <td>${tPool.ftype}</td>
				<shiro:hasPermission name="netconfig:tPool:edit"><td>
    				<a href="${ctx}/netconfig/tPool/form?id=${tPool.id}">修改</a>
					<a href="${ctx}/netconfig/tPool/delete?id=${tPool.id}" onclick="return confirmx('确认要删除该池组吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>