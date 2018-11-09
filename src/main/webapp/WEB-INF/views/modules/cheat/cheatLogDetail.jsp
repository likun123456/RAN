<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量欺诈分析报告</title>
	<meta name="decorator" content="default"/>
</head>
<body>


    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <caption align="top"><h4>用户(IMSI ${imsi})流量欺诈分析报告</h4></caption>
		<thead>
			<tr>
			    <th>时间戳</th>
			    <th>用户私网IP地址 </th>
			    <th>欺诈类型</th>
			    <th>欺诈日志</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${cheatloglist}" var="item">
			<tr>
			    <td>${item.tempstamp}</td>
			    <td>${item.ip}</td>
			    <td>${item.cheatCase}</td>
			    <td>${item.cheatNote}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>
