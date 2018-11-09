<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>EBM XML配制管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#searchForm").validate({
				submitHandler: function(form){
					if ($("#netidId").val()==""){
	                	top.$.jBox.tip('请选择网元','warning');
					}else if ($("#upload").val()==""){
	                    top.$.jBox.tip('请选择ebm.xml文件','warning');
	                }else if ($("#upload2").val()==""){
	                	top.$.jBox.tip('请选择ebm_cause_codes.xml文件','warning');
	                }else if ($("#upload3").val==""){
	                	top.$.jBox.tip('请选择scc mapping文件','warning');
	                }else{//表单提交
						loading('正在提交，请稍等...');
						form.submit();
	                }
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			$("#searchForm2").validate({
				submitHandler: function(form){
					if ($("#upload4").val==""){
	                	top.$.jBox.tip('请选择IMEI TAC翻译文件(txt)','warning');
	                }else{//表单提交
						loading('正在提交，请稍等...');
						form.submit();
	                }
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$("#searchForm3").validate({
				submitHandler: function(form){
					if ($("#upload4").val==""){
	                	top.$.jBox.tip('请选择LTE全网参数(excel)','warning');
	                }else{//表单提交
						loading('正在提交，请稍等...');
						form.submit();
	                }
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$("#searchForm4").validate({
				submitHandler: function(form){
					if ($("#netid2Id").val()==""){
	                	top.$.jBox.tip('请选择网元','warning');
					}else if ($("#upload6").val()==""){
	                    top.$.jBox.tip('请选择新ebm.xml文件','warning');
	                }else{//表单提交
						loading('正在提交，请稍等...');
						form.submit();
	                }
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		
	</script>
</head>
<body>
<%--     <form:form id="searchForm4" modelAttribute="tEbmlogStatistics" action="${ctx}/performance/ebmConfig/uploadEbmXmlFile" method="post" enctype="multipart/form-data" class="form-horizontal"> --%>
<%-- 		<sys:message content="${message}"/>	 --%>
<!-- 		<div> -->
<!-- 			<span style="font-size:15px;font-weight:bold;margin-left: 138px;"> -->
<!-- 				日常维护EBM文件上传管理 -->
<!-- 			</span> -->
<!-- 		</div>	 -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">网元:</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<sys:treeselect id="netid2" name="netid" cssStyle="width:138px;" value="${netid}" labelName="netname" labelValue="${netid}" --%>
<%-- 					title="网元名称" url="/performance/ebmConfig/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" /> --%>
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">新ebm.xml文件:</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<input type="file" name="upload6" id="upload6" label="选择xml文件"> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="form-actions"> -->
<!-- 			<input id="btnSubmit" class="btn" type="submit" value="上传文件"/> -->
<!-- 		</div> -->
<%-- 	</form:form> --%>
	<form:form id="searchForm" modelAttribute="tEbmlogStatistics" action="${ctx}/performance/ebmConfig/uploadFile" method="post" enctype="multipart/form-data" class="form-horizontal">
		<sys:message content="${message}"/>	
		<span style="font-size:15px;font-weight:bold;margin-left: 138px;">
				EBM上传文件管理
		</span>
		<div class="control-group">
			<label class="control-label">网元:</label>
			<div class="controls">
				<sys:treeselect id="netid" name="netid" cssStyle="width:138px;" value="${netid}" labelName="netname" labelValue="${netid}"
					title="网元名称" url="/performance/ebmConfig/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ebm.xml文件:</label>
			<div class="controls">
				<input type="file" name="upload" id="upload" label="选择xml文件">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ebm_cause_codes.xml文件:</label>
			<div class="controls">
				<input type="file" name="upload2" id="upload2" label="选择xml文件">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">scc mapping文件:</label>
			<div class="controls">
				<input type="file" name="upload3" id="upload3" label="选择excel文件">
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="submit" value="上传文件"/>
		</div>
	</form:form>
	<form:form id="searchForm2" modelAttribute="tEbmlogStatistics" enctype="multipart/form-data" action="${ctx}/performance/ebmConfig/uploadFileTxt" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<div>
			<span style="font-size:15px;font-weight:bold;margin-left: 138px;">
				IMEI TAC翻译信息管理
			</span>
		</div>	
		<div class="control-group">
			<label class="control-label">选择IMEI TAC翻译文件(txt):</label>
			<div class="controls">
				<input type="file" name="upload4" id="upload4" label="选择txt文件"></s:file>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="submit" value="上传文件"/>
		</div>
	</form:form>
	
	<form:form id="searchForm3" enctype="multipart/form-data" action="${ctx}/performance/ebmConfig/uploadFileExcel" method="post" class="form-horizontal">
		<sys:message content="${message}"/>	
		<div>
			<span style="font-size:15px;font-weight:bold;margin-left: 138px;">
				LTE全网参数
			</span>
		</div>	
		<div class="control-group">
			<label class="control-label">选择LTE全网参数(excel):</label>
			<div class="controls">
				<input type="file" name="upload5" id="upload5" label="选择excel文件" />
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="submit" value="上传文件"/>
		</div>
	</form:form>
</body>
</html>