<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>案例库信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
</head>
<body>
   <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>CC(原因值)</th>
			    <th>SCC(子原因值)</th>
			    <th>问题描述</th>
			    <th>原因描述</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tCaseLibrary">
			<tr>
			    <td>${tCaseLibrary.cc}</td>
			    <td>${tCaseLibrary.scc}</td>
			    <td>${tCaseLibrary.questiondescrible}</td>
			    <td>${tCaseLibrary.questiondescrible}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>