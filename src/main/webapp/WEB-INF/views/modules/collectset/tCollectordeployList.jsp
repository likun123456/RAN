<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采集器配置管理</title>
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
		<li class="active"><a href="${ctx}/collectset/tCollectordeploy/">采集器配置列表</a></li>
		<shiro:hasPermission name="collectset:tCollectordeploy:edit"><li><a href="${ctx}/collectset/tCollectordeploy/form">采集器配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCollectordeploy" action="${ctx}/collectset/tCollectordeploy/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>采集器名称：</label>
				<form:input path="collectorname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>采集器名称</th>
				<th>IP地址</th>
				<th>FTP用户名</th>
				<th>FTP密码</th>
				<th>ebmlog数据源</th>
				<th>sub数据源</th>
				<th>ebmlog池组数据源</th>
				<shiro:hasPermission name="collectset:tCollectordeploy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCollectordeploy">
			<tr>
				<td>
					<a href="${ctx}/collectset/tCollectordeploy/form?id=${tCollectordeploy.id}">
						${tCollectordeploy.collectorname}
					</a>
				</td>
				<td>${tCollectordeploy.ip}</td>
				<td>${tCollectordeploy.username}</td>
				<td>${tCollectordeploy.password}</td>
				<td>${fns:getDictLabel(tCollectordeploy.coredataebmlog, 'biz_epg_datasouce_flag', '禁用')}</td>
				<td>${fns:getDictLabel(tCollectordeploy.coredatasub, 'biz_epg_datasouce_flag', '禁用')}</td>
				<td>${fns:getDictLabel(tCollectordeploy.coreebmlog, 'biz_epg_datasouce_flag', '禁用')}</td>
				<shiro:hasPermission name="collectset:tCollectordeploy:edit"><td>
    				<a href="${ctx}/collectset/tCollectordeploy/form?id=${tCollectordeploy.id}">修改</a>
					<a href="${ctx}/collectset/tCollectordeploy/delete?id=${tCollectordeploy.id}" onclick="return confirmx('确认要删除该采集器配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>