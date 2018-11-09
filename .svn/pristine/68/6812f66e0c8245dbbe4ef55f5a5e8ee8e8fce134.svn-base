<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>闭锁模版管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tLockTemplate/">闭锁模版管理列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tLockTemplate" action="${ctx}/netconfig/tLockTemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>板卡名称:</label>
				<form:input path="cardname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>网元类型</th>
				<th>板卡名称</th>
				<th>闭锁模版</th>
				<th>解锁模版</th>
				<th>重启模版</th>
				<shiro:hasPermission name="netconfig:tLockTemplate:view"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tLockTemplate">
			<tr>
			    <td>
					${tLockTemplate.type}
				</td>
				<td>
					${tLockTemplate.cardname}
				</td>
				<td>
					${tLockTemplate.lockName}
				</td>
				<td>
					${tLockTemplate.unLockName}
				</td>
				<td>
					${tLockTemplate.reBootName}
				</td>
				<shiro:hasPermission name="netconfig:tLockTemplate:view"><td>
    				<a href="${ctx}/netconfig/tLockTemplate/form?id=${tLockTemplate.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>