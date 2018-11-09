<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备链路管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tEdges/list">设备链路列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tEdges" action="${ctx}/netconfig/tEdges/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机房:</label>
			    <form:select path="roomId" class="input-xlarge">
			    	<form:option value="0" label="全部"/>
					<form:options items="${roomList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>始端设备IP:</label>
				<form:input path="fromEquIp" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>对端设备IP:</label>
				<form:input path="toEquIp" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>机房</th>
			    <th>始端设备</th>
			    <th>始端设备IP</th>
			    <th>对端设备</th>
			    <th>对端设备IP</th>
				<shiro:hasPermission name="netconfig:tEdges:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tEdges">
			<tr>
				<td>${tEdges.roomName}</td>
			    <td>${tEdges.fromEquName}</td>
			    <td>${tEdges.fromEquIp}</td>
			    <td>${tEdges.toEquName}</td>
			    <td>${tEdges.toEquIp}</td>
				<shiro:hasPermission name="netconfig:tEdges:edit"><td>
    				<a href="${ctx}/netconfig/tEdges/edit?id=${tEdges.id}&roomName=${fns:urlEncode(tEdges.roomName)}&fromEquName=${fns:urlEncode(tEdges.fromEquName)}&toEquName=${fns:urlEncode(tEdges.toEquName)}&fromEquIp=${fns:urlEncode(tEdges.fromEquIp)}&toEquIp=${fns:urlEncode(tEdges.toEquIp)}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>