<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网元参数修改任务状态管理</title>
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
		<li class="active"><a href="${ctx}/paramconfig/tParamCmdStatus/">网元参数修改任务状态列表</a></li>
		<shiro:hasPermission name="paramconfig:tParamCmdStatus:edit"><li><a href="${ctx}/paramconfig/tParamCmdStatus/form">网元参数修改任务状态添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tParamCmdStatus" action="${ctx}/paramconfig/tParamCmdStatus/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>网元:</label>
				<form:select path="netid" class="input-xlarge" style="width:180px;" id="netid">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
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
			   <th>网元</th>
			   <th>指令内容</th>
			   <th>执行类型</th>
			   <th>执行时间</th>
			   <th>执行状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tParamCmdStatus">
			<tr>
				<td>${tParamCmdStatus.fname}</td>
				<td>${tParamCmdStatus.cmdcontent}</td>
				<td>${fns:getDictLabel(tParamCmdStatus.executetype,'executetype',tParamCmdStatus.executetype)}</td>
				<td>${tParamCmdStatus.executetime}</td>
				<td>${fns:getDictLabel(tParamCmdStatus.executestatus,'executestatus',tParamCmdStatus.executestatus)}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>