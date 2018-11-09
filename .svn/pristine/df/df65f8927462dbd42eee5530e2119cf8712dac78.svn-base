<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />
	<script type="text/javascript">
		$(document).ready(function() {
			var netId = "${netId}";
			if(netId != ''){
				$('#netid').val(netId);
				$(".select2-container .select2-chosen:eq(0)").html('${netName}');
			}
			
		});
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="tVisExcelModuleDetail" method="post"
		enctype="multipart/form-data" class="form-horizontal">
		<br>
		<div class="control-group">
				&nbsp;&nbsp;&nbsp;&nbsp;<label>选择网元</label>
				<form:select path="temp_field1" class="required input-xlarge"
					style="width:200px;" id="netid" name="temp_field1" >
					<form:options items="${list}" itemLabel="fname"
						itemValue="id" htmlEscape="false"  />
				</form:select>
				<form:hidden path="excelId" id="excelId"/><!-- 自定义参数ExcelId -->
				<form:hidden path="moduleId" id="moduleId"/><!-- 自定义参数moduleId -->
				<form:hidden path="temp_field2" id="temp_field2"/><!-- 自定义参数参数个数-->
				<form:hidden path="temp_field4" id="temp_field4"/><!-- 自定义参数模块总数 -->
		</div>
	</form:form>
</body>
</html>