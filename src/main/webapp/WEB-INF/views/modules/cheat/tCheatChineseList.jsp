<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈类型管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				$("#searchForm").attr("action","${ctx}/cheat/tCheatChinese/export");
				$("#searchForm").submit();
			});
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
		<li class="active"><a href="${ctx}/cheat/tCheatChinese/">欺诈类型列表</a></li>
		<shiro:hasPermission name="cheat:tCheatChinese:edit"><li><a href="${ctx}/cheat/tCheatChinese/form">欺诈类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tCheatChinese" action="${ctx}/cheat/tCheatChinese/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>欺诈类型:</label>
				<form:input path="cheatCase" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label style="width:140px;">欺诈类型中文翻译:</label>
				<form:input path="cheatCaseChinese" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/></li>
				<shiro:hasPermission name="cheat:tCheatChinese:export">
					<li class="btns"><input id="btnExport" class="btn-new btn-save" type="button" value="导出"/></li>
				</shiro:hasPermission>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>欺诈类型</th>
				<th>欺诈类型中文翻译</th>
				<th>漏洞类型</th>
				<th>欺诈原理</th>
				<th>欺诈场景</th>
				<th>欺诈场景分析</th>
				<th>解决方案</th>
				<shiro:hasPermission name="cheat:tCheatChinese:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCheatChinese">
			<tr>
				<td>
					<a href="${ctx}/cheat/tCheatChinese/form?id=${tCheatChinese.id}">
						${tCheatChinese.cheatCase}
					</a>
				</td>
				<td>${tCheatChinese.cheatCaseChinese}</td>
				<td>${tCheatChinese.loopholeType}</td>
				<td>${tCheatChinese.cheatPrinciple}</td>
				<td>${tCheatChinese.cheatScene}</td>
				<td>${tCheatChinese.cheatSceneAnalysis}</td>
				<td>${tCheatChinese.solution}</td>
				<shiro:hasPermission name="cheat:tCheatChinese:edit"><td>
    				<a href="${ctx}/cheat/tCheatChinese/form?id=${tCheatChinese.id}">修改</a>
					<a href="${ctx}/cheat/tCheatChinese/delete?id=${tCheatChinese.id}" onclick="return confirmx('确认要删除该欺诈类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>