<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>话单业务配置</title>
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
		<li class="active"><a href="${ctx}/userquery/ratingroupConfig/">话单业务配置</a></li>
		<shiro:hasPermission name="userquery:ratingroupConfig:edit"><li><a href="${ctx}/userquery/ratingroupConfig/form">添加话单业务</a></li></shiro:hasPermission>
		<shiro:hasPermission name="userquery:ratingroupConfig:import"><li><a href="${ctx}/userquery/ratingroupConfig/importForm">导入</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tRatingroupConfig" action="${ctx}/userquery/ratingroupConfig/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>话单业务名:</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>业务代码</th>
			    <th>业务名称</th>
				<shiro:hasPermission name="userquery:ratingroupConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tRatingroupConfig">
			<tr>
			    <td>${tRatingroupConfig.ratinggroup}</td>
			    <td>${tRatingroupConfig.name}</td>
				<shiro:hasPermission name="userquery:ratingroupConfig:edit"><td>
    				<a href="${ctx}/userquery/ratingroupConfig/form?id=${tRatingroupConfig.id}">修改</a>
					<a href="${ctx}/userquery/ratingroupConfig/delete?id=${tRatingroupConfig.id}" onclick="return confirmx('确认要删除话单业务配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>