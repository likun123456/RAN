<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />
</head>
<body>
	<form:form id="searchForm" modelAttribute="tVisExcelModuleDetail"
		action="${ctx}/netconfig/excelTemplateDebug/list" method="post"
		enctype="multipart/form-data" class="form-horizontal">
		<form:hidden path="excelId" id="excelId"/>
		<form:hidden path="moduleId" id="moduleId"/>
		<form:hidden path="temp_field4" id="temp_field4"/>
		<br>
		<div class="control-group">
			<table>
				<c:forEach items="${list}" var="modelDetail" varStatus="stat">
					<tr>
						<td>
							<label>${modelDetail.commandName}(${modelDetail.varArray})</label>
							<br>
							<input type="text" id="${modelDetail.varArray}" name="input" value="${cardLocal[stat.index] }"/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form:form>
</body>
</html>