<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>离线信令分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var path = '${requestPath}';
			if(path!=""){
				openMaxWindow(path);
			}
			//资源名称
			var name = document.getElementById('upload').value;
			var regex = ".*\\.pcap";
			var reg = new RegExp(regex);
			$("#searchForm").validate({
				submitHandler: function(form){
					 if ($("#upload").val()==""){
	                    top.$.jBox.tip('请选择抓包文件','warning');
	                 }else if(!reg.test($("#upload").val())){//表单提交
	                	 top.$.jBox.tip('请选择正确文件格式文件(**.pcap)','warning');
	                 }else{
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
	<form:form id="searchForm"  action="${ctx}/userquery/unOnline/uploadFile" method="post" enctype="multipart/form-data" class="form-horizontal">
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">抓包文件(.pcap):</label>
			<div class="controls">
				<input type="file" name="upload" id="upload" label="选择抓包文件">
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn" type="submit" value="上传文件"/>
		</div>
	</form:form>
</body>
</html>