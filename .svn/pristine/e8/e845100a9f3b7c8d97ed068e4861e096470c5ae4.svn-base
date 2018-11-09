<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>节点管理管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tDns/">节点管理列表</a></li>
		<shiro:hasPermission name="netconfig:tDns:edit"><li><a href="${ctx}/netconfig/tDns/form">节点基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tDns" action="${ctx}/netconfig/tDns/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>名称:</label>
				<form:input path="fname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>名称</th>
				<th>地址</th>
				<th>用户名</th>
				<th>密码</th>
				<shiro:hasPermission name="netconfig:tDns:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tDns">
			<tr>
			    <td><a href="${ctx}/netconfig/tDns/form?id=${tDns.id}">
					${tDns.fname}
				</a></td>
				<td>
					${tDns.faddress}
				</td>
				<td>
					${tDns.fusername}
				</td>
				<td>
					${tDns.fpassword}
				</td>
				<shiro:hasPermission name="netconfig:tDns:edit"><td>
    				<a href="${ctx}/netconfig/tDns/form?id=${tDns.id}">修改</a>
					<a href="${ctx}/netconfig/tDns/delete?id=${tDns.id}" onclick="return confirmx('确认要删除该节点管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>