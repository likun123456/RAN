<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网元参数更新包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			//$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/paramconfig/paramPackage/">参数包列表</a></li>
		<shiro:hasPermission name="paramconfig:paramPackage:export"><li><a href="${ctx}/paramconfig/paramPackage/exportForm">导入</a></li></shiro:hasPermission>
	</ul>
	
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>参数包名称</th>
				<th>网元类型</th>
				<th>版本</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pck">
			<tr>
				<td>
					${pck.packagename}
				</td>
				<td>
					${pck.nettype}
				</td>
				<td>
					${pck.version}
				</td>
				<td>
					${pck.updatetime}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>