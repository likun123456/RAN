<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈用户详细信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>



    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <caption align="top"><b>欺诈用户详细信息</b></caption>
		<thead>
			<tr>
			    <th>时间</th>
			    <th>代理服务器</th>
			    <th>用户IMSI</th>
			    <th>MSISDN</th>
			    <th>RATTYPE</th>
			    <th>欺诈类型</th>
			    <th>欺诈流量</th>
			    <th>总流量</th>
			    <th>欺诈流量占比</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="v">
			<tr>
			    <td>${v.recordtime}</td>
			    <td>${v.proxyIp}</td>
			    <td>${v.imsi}</td>
			    <td>${v.msisdn}</td>
			    <td>${v.ratType}</td>
			    <td>${v.cheatCaseChinese}</td>
			    <td><fmt:formatNumber type="number" value="${(v.freeTotal)/(1024*1024)}" pattern="0.00" maxFractionDigits="2"/>M</td>
			    <td><fmt:formatNumber type="number" value="${(v.total)/(1024*1024)}" pattern="0.00" maxFractionDigits="2"/>M</td>
			    <td><fmt:formatNumber type="number" value="${(v.percent)*100}" pattern="0.00" maxFractionDigits="2"/>%</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>
