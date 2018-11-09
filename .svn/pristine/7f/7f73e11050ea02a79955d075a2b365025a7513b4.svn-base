<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>免费业务代码设置</title>
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
		<li class="active"><a href="${ctx}/cheat/freeRatingGroup/list">免费业务代码设置</a></li>
		<shiro:hasPermission name="cheat:freeRatingGroup:edit"><li><a href="${ctx}/cheat/freeRatingGroup/form">添加业务代码</a></li></shiro:hasPermission>
		<shiro:hasPermission name="cheat:freeRatingGroup:import"><li><a href="${ctx}/cheat/freeRatingGroup/importForm">导入</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="freeRatingGroup" action="${ctx}/cheat/freeRatingGroup/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>业务名称:</label>
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
				<shiro:hasPermission name="cheat:freeRatingGroup:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="freeRatingGroup">
			<tr>
			    <td>${freeRatingGroup.ratingGroup}</td>
			    <td><a href="${ctx}/cheat/freeRatingGroup/form?id=${freeRatingGroup.id}">${freeRatingGroup.name}</a></td>
				<shiro:hasPermission name="cheat:freeRatingGroup:edit"><td>
    				<a href="${ctx}/cheat/freeRatingGroup/form?id=${freeRatingGroup.id}">修改</a>
					<a href="${ctx}/cheat/freeRatingGroup/delete?id=${freeRatingGroup.id}" onclick="return confirmx('确认要删除此业务代码吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>