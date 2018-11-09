<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${netType}节点信息管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tNewnetelement/">${netType}节点信息管理</a></li>
		<shiro:hasPermission name="netconfig:tNewnetelement:edit"><li><a href="${ctx}/netconfig/tNewnetelement/form/?poolType=${poolType}">添加${netType}网元信息</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tNewnetelement" action="${ctx}/netconfig/tNewnetelement/?poolType=${poolType}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>请选择池组:</label>
		        <form:select path="fnid" class="required input-xlarge" style="width:200px;">
		            <form:option value="0" label="全部"/>
					<form:options items="${poolList}" itemLabel="fpoolname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>网元名称:</label>
				<form:input path="fname" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>池组名称</th>
				<th>IP地址</th>
				<shiro:hasPermission name="netconfig:tNewnetelement:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tNewnetelement">
			<tr>
				<td><a href="${ctx}/netconfig/tNewnetelement/form?id=${tNewnetelement.id}&poolType=${poolType}">
					${tNewnetelement.fname}
				</a></td>
				<td>${tNewnetelement.poolname}</td>
				<td>${tNewnetelement.ipadr}</td>
				<shiro:hasPermission name="netconfig:tNewnetelement:edit"><td>
    				<a href="${ctx}/netconfig/tNewnetelement/form?id=${tNewnetelement.id}&poolType=${poolType}">修改</a>
					<a href="${ctx}/netconfig/tNewnetelement/delete?id=${tNewnetelement.id}&poolType=${poolType}" onclick="return confirmx('确认要删除该网元信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>