<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计费欺诈防控分析详细信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>业务编码</th>
			    <th>业务名称</th>
			    <th>上行流量(KB)</th>
			    <th>下行流量(KB)</th>
			    <th>总流量(KB)</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="v">
			<tr>
			    <td>${v.ratingGroup}</td>
			    <td>${v.ratingGroupName}</td>
			    <td><fmt:formatNumber type="number" value="${(v.dataUp)/1024}" pattern="0.00" maxFractionDigits="2"/></td>
			    <td><fmt:formatNumber type="number" value="${(v.dataDown)/1024}" pattern="0.00" maxFractionDigits="2"/></td>
			    <td><fmt:formatNumber type="number" value="${(v.total)/1024}" pattern="0.00" maxFractionDigits="2"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
