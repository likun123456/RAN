<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拓扑设备管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tEquipment/">拓扑设备列表</a></li>
		<shiro:hasPermission name="netconfig:tEquipment:edit"><li><a href="${ctx}/netconfig/tEquipment/form">拓扑设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tEquipment" action="${ctx}/netconfig/tEquipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>设备名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>设备类型：</label>
			<form:select path="type" class="input-medium">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('biz_vis_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>设备名称</th>
				<th>设备类型</th>
				<th>IP地址</th>
				<th>用户名</th>
				<th>密码</th>
				<shiro:hasPermission name="netconfig:tEquipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tEquipment">
			<tr>
				<td><a href="${ctx}/netconfig/tEquipment/form?id=${tEquipment.id}">
					${tEquipment.name}
				</a></td>
				<td>${fns:getDictLabel(tEquipment.type,'biz_vis_net_type','')}</td>
				<td>${tEquipment.ip}</td>
			    <td>${tEquipment.username}</td>
			    <td>${tEquipment.passwd}</td>
				<shiro:hasPermission name="netconfig:tEquipment:edit"><td>
    				<a href="${ctx}/netconfig/tEquipment/form?id=${tEquipment.id}">修改</a>
					<a href="${ctx}/netconfig/tEquipment/delete?id=${tEquipment.id}" onclick="return confirmx('确认要删除该拓扑设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>