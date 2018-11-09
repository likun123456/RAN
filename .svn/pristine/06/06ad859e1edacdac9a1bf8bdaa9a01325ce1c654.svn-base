<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版指令提示确认</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table class="table table-bordered table-condensed" >
		<thead>
			<tr>
				<th style="width:40%">指令名称</th>
				<th >指令</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="modelDetail">
				<c:if test="${modelDetail.confCmd ne 'SYSTEM-INPUT'}">
					<tr <c:if test="${modelDetail.temp_field1 eq 'true'}">bgcolor="yellow"</c:if>>
						<td>
							${modelDetail.commandName}
						</td>
						<td>
							${modelDetail.confCmd}
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>