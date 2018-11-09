<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抓包参数设置管理</title>
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
		<li class="active"><a href="${ctx}/cheat/tConfig/">抓包参数设置列表</a></li>
<%-- 		<shiro:hasPermission name="cheat:tConfig:edit"><li><a href="${ctx}/cheat/tConfig/form">抓包参数设置添加</a></li></shiro:hasPermission> --%>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>网元</th>
			    <th>批量打包免费流量门限(byte)</th>
			    <th>免费流量门限(byte)</th>
			    <th>免费流量占比门限</th>
			    <th>抓包用户数</th>
				<shiro:hasPermission name="cheat:tConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tConfig">
			<tr>
			    <td><a href="${ctx}/cheat/tConfig/form?id=${tConfig.id}">${tConfig.netName}</a></td>
			    <td>${tConfig.freeflowfilter}</td>
			    <td>${tConfig.freeflow}</td>
			    <td>${tConfig.freepercent}</td>
			    <td>${tConfig.catchpackageusercount}</td>
				<shiro:hasPermission name="cheat:tConfig:edit"><td>
    				<a href="${ctx}/cheat/tConfig/form?id=${tConfig.id}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>