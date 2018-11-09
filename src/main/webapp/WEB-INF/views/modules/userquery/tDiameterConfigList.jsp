<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信令追踪DIAMETER端口设置管理</title>
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
		<li class="active"><a href="${ctx}/userquery/tDiameterConfig/">信令追踪DIAMETER端口设置列表</a></li>
		<shiro:hasPermission name="userquery:tDiameterConfig:edit"><li><a href="${ctx}/userquery/tDiameterConfig/form">信令追踪DIAMETER端口设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tDiameterConfig" action="${ctx}/userquery/tDiameterConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>DIAMETER上层协议类型</th>
			    <th>DIAMETER端口号</th>
				<shiro:hasPermission name="userquery:tDiameterConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tDiameterConfig">
			<tr>
			    <td>${tDiameterConfig.diameterType} </td>
			    <td>${tDiameterConfig.diameterPort}</td>
				<shiro:hasPermission name="userquery:tDiameterConfig:edit"><td>
    				<a href="${ctx}/userquery/tDiameterConfig/form?id=${tDiameterConfig.id}">修改</a>
					<a href="${ctx}/userquery/tDiameterConfig/delete?id=${tDiameterConfig.id}" onclick="return confirmx('确认要删除该信令追踪DIAMETER端口设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>