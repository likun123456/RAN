<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>EXCEL模版导入</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function(){
		$("#netidepc").val($("#temp_field3").val());
	});
</script>
</head>
<body onload="tt()">
	<form:form id="searchForm" modelAttribute="tVisExcelModuleDetail" method="post"
		enctype="multipart/form-data" class="form-horizontal">
		<br>
		<div class="control-group">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>选择网元</label>				
				<sys:treeselect id="netid" name="temp_field1" cssStyle="width:150px;" value="${netid}" labelName="netid" labelValue="${netid}"
					title="网元名称" url="/netconfig/excelTemplateDebug/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
				<form:hidden path="excelId" id="excelId"/>
				<form:hidden path="moduleId" id="moduleId"/>
				<form:hidden path="temp_field2" id="temp_field2"/><!-- 自定义参数个数 -->
				<form:hidden path="temp_field3" id="temp_field3"/>
				<form:hidden path="temp_field4" id="temp_field4"/>
		</div>
	</form:form>
</body>
</html>