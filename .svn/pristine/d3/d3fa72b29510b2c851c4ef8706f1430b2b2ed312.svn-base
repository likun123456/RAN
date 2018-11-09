<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选择模块</title>
<meta name="decorator" content="default" />
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="tVisExcelModule" method="post" class="form-horizontal">
		<br>
		<div class="control-group">
				&nbsp;&nbsp;&nbsp;&nbsp;<label>模块:</label>
				<form:select path="temp_field1" class="required input-xlarge"
					style="width:300px;" id="moduleId" name="temp_field1" >
					<form:options items="${list}" itemLabel="moduleName"
						itemValue="temp_field1" htmlEscape="false"  />
				</form:select>
		</div>
	</form:form>
</body>
</html>