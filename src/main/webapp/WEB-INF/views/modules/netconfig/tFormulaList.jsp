<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公式配置管理</title>
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
		<li class="active"><a href="${ctx}/netconfig/tFormula/">公式配置列表</a></li>
		<shiro:hasPermission name="netconfig:tFormula:edit"><li><a href="${ctx}/netconfig/tFormula/form">公式配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tFormula" action="${ctx}/netconfig/tFormula/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>公式编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>公式名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>网元类型：</label>
				<form:select path="nettype" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_formula_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>公式编号</th>
				<th>公式名称</th>
				<th>网元类型</th>
				<th>统计类型</th>
				<th>obj类型</th>
				<shiro:hasPermission name="netconfig:tFormula:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tFormula">
			<tr>
				<td><a href="${ctx}/netconfig/tFormula/form?id=${tFormula.id}">
					${tFormula.id}
				</a></td>
				<td>
					${tFormula.name}
				</td>
				<td>
					${fns:getDictLabel(tFormula.nettype, 'biz_formula_type', '')}
				</td>
				<td>
					${fns:getDictLabel(tFormula.countertype, 'biz_forluma_counter_type', '')}
				</td>
				<td>${tFormula.objtype}
				</td>
				<shiro:hasPermission name="netconfig:tFormula:edit"><td>
    				<a href="${ctx}/netconfig/tFormula/form?id=${tFormula.id}">修改</a>
					<a href="${ctx}/netconfig/tFormula/delete?id=${tFormula.id}" onclick="return confirmx('确认要删除该公式配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>