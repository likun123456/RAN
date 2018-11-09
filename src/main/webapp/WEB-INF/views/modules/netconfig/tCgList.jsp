<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>CG管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tCg/">CG列表</a></li>
		<shiro:hasPermission name="netconfig:tCg:edit"><li><a href="${ctx}/netconfig/tCg/form">添加CG</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCg" action="${ctx}/netconfig/tCg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>CG名称:</label>
				<form:input path="fname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>CG名称</th>
			    <th>CG地址</th>
			    <th>登录账号</th>
			    <th>登录密码</th>
				<shiro:hasPermission name="netconfig:tCg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCg">
			<tr>
			    <td><a href="${ctx}/netconfig/tCg/form?id=${tCg.id}">
			    ${tCg.fname}</a>
			    </td>
			    <td>${tCg.faddress}</td>
			    <td>${tCg.fusername}</td>
			    <td>${tCg.fpassword}</td>
				<shiro:hasPermission name="netconfig:tCg:edit"><td>
    				<a href="${ctx}/netconfig/tCg/form?id=${tCg.id}">修改</a>
					<a href="${ctx}/netconfig/tCg/delete?id=${tCg.id}" onclick="return confirmx('确认要删除该CG吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>