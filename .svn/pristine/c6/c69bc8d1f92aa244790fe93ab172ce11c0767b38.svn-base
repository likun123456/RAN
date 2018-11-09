<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>OSS节点基本信息管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tOss/">OSS节点基本信息列表</a></li>
		<shiro:hasPermission name="netconfig:tOss:edit"><li><a href="${ctx}/netconfig/tOss/form">OSS节点基本信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tOss" action="${ctx}/netconfig/tOss/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>OSS名称:</label>
				<form:input path="fname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>OSS名称</th>
				<th>OSS地址</th>
				<th>用户名</th>
				<th>密码</th>
				<shiro:hasPermission name="netconfig:tOss:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tOss">
			<tr>
				<td><a href="${ctx}/netconfig/tOss/form?id=${tOss.id}">
					${tOss.fname}
				</a></td>
				<td>
					${tOss.faddress}
				</td>
				<td>
					${tOss.fusername}
				</td>
				<td>
					${tOss.fpassword}
				</td>
				<shiro:hasPermission name="netconfig:tOss:edit"><td>
    				<a href="${ctx}/netconfig/tOss/form?id=${tOss.id}">修改</a>
					<a href="${ctx}/netconfig/tOss/delete?id=${tOss.id}" onclick="return confirmx('确认要删除该OSS节点基本信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>