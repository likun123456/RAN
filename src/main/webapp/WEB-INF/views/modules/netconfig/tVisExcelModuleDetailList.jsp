<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>EXCEL模版管理</title>
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
		<!-- <li  class="active"><a id="add" href="${ctx}/netconfig/excelTemplateDebug/list">查看模块</a></li> -->
		<!-- <li><a id="add" href="${ctx}/netconfig/excelTemplateDebug/form">添加指令</a></li> -->
	</ul>
	<form:form id="searchForm" modelAttribute="tVisExcelModuleDetail" action="${ctx}/netconfig/excelTemplateDebug/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>执行序号</th>
			    <th>指令名称</th>
			    <th>前台提示</th>
			    <th>调试标识</th>
				<th>指令</th>
				<th>提示符(指令执行前)</th>
				<th>提示符(指令执行后)</th>
				<th>结果正则表达式</th>
				<th>超时时间(秒)</th>
				<th>错误处理方式</th>
				<th>变量正则表达式</th>
				<th>变量名称</th>
				<!-- <th>操作</th> -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tVisExcelModuleDetail">
			<tr>
			    <td><a href="${ctx}/netconfig/excelTemplateDebug/form?id=${tVisExcelModuleDetail.id}">
			    ${tVisExcelModuleDetail.executeNo}</a>
			    </td>
			    <td>${tVisExcelModuleDetail.commandName}</td>
			    <td>${tVisExcelModuleDetail.formRemark}</td>
			    <td>${tVisExcelModuleDetail.debugRemark}</td>
			    <td>${tVisExcelModuleDetail.confCmd}</td>
			    <td>${tVisExcelModuleDetail.beforePrompt}</td>
			    <td>${tVisExcelModuleDetail.afterPrompt}</td>
			    <td>${tVisExcelModuleDetail.checkRegexp}</td>
			    <td>${tVisExcelModuleDetail.timeout}</td>
			    <td>${tVisExcelModuleDetail.errorHandleMode}</td>
			    <td>${tVisExcelModuleDetail.varArrayRegexp}</td>
			    <td>${tVisExcelModuleDetail.varArray}</td>
				<!-- 
				<td>
    				<a href="${ctx}/netconfig/excelTemplateDebug/form?id=${tVisExcelModuleDetail.id}">修改</a>
					<a href="${ctx}/netconfig/excelTemplateDebug/delete?id=${tVisExcelModuleDetail.id}" onclick="return confirmx('确认要删除该命令吗？', this.href)">删除</a>
				</td>
				 -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>