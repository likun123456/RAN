<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机房信息管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tComputerroom/">机房信息列表</a></li>
		<shiro:hasPermission name="netconfig:tComputerroom:edit"><li><a href="${ctx}/netconfig/tComputerroom/form">机房信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tComputerroom" action="${ctx}/netconfig/tComputerroom/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机房名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="300" class="input-xlarge"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机房名称</th>
				<shiro:hasPermission name="netconfig:tComputerroom:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tComputerroom">
			<tr>
				<td><a href="${ctx}/netconfig/tComputerroom/form?id=${tComputerroom.id}">
					${tComputerroom.name}
				</a></td>
				<shiro:hasPermission name="netconfig:tComputerroom:edit"><td>
    				<a href="${ctx}/netconfig/tComputerroom/form?id=${tComputerroom.id}">修改</a>
<%-- 					<a href="${ctx}/netconfig/tComputerroom/delete?id=${tComputerroom.id}" onclick="return confirmx('确认要删除该机房信息吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>