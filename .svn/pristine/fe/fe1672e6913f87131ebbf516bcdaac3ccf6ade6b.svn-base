<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>liuliang管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 1}).show();
		});
		
		function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/netconfig/visExcelTemplate/updateSort");
	    	$("#listForm").submit();
    	}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/netconfig/visExcelTemplate">模板列表</a></li>
		<li><a href="${ctx}/netconfig/visExcelTemplate/form">模板添加</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed hide">
			<thead><tr><th>名称</th><th style="text-align:center;">排序</th><th>命令</th><th>操作</th></thead>
			<tbody><c:forEach items="${list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parentId ne '1'?menu.parentId:'0'}">
					<td nowrap><a href="${ctx}/netconfig/visExcelTemplate/form?id=${menu.id}">${menu.name}</a></td>
					<td style="text-align:center;">
						<input type="hidden" name="ids" value="${menu.id}"/>
						<input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
					</td>
					<td title="${menu.confCmd}">${fns:abbr(menu.confCmd,30)}</td>
					<td nowrap>
						<a href="${ctx}/netconfig/visExcelTemplate/form?id=${menu.id}">修改</a>
						<a href="${ctx}/netconfig/visExcelTemplate/delete?id=${menu.id}" onclick="return confirmx('要删除该模板及所有子模板项吗？', this.href)">删除</a>
						<c:if test="${menu.level != '3'}">
							<a href="${ctx}/netconfig/visExcelTemplate/childform?parentId=${menu.id}">添加下级</a> 
						</c:if>
					</td>
				</tr>
			</c:forEach></tbody>
		</table>
	 </form>
	 <div class="form-actions pagination-left">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
	 </div>
</body>
</html>