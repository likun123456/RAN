<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信令追踪IP配置</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//提交
	function doSubmit(){
	  //资源名称
		var name = document.getElementById('upload').value;
		//var regex = "^(?:\\w+\\.xlsx|\\w+\\.xls)$";
		//var reg = new RegExp(regex);
		if(name == ""){
			top.$.jBox.tip('请选择文件...','warning');
			document.getElementById("upload").focus();
			return;
		}
		/* if(!reg.test(name)){
			top.$.jBox.tip('请选择正确文件格式文件(**.xlsx.xls)','warning');
			document.getElementById("upload").focus();
			return;
		} */
		document.forms[0].action = "${ctx}/userquery/signalHostConfig/importAction";
		document.forms[0].method = "POST";
		document.getElementById('button_div').innerHTML ="文件正在解析入库中，请稍等...";
		document.forms[0].submit();
	}
	
	//关闭当前窗口
	function closewindow(){
	    window.returnValue='2';
		window.close();
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/userquery/signalHostConfig/">信令追踪IP配置</a></li>
		<shiro:hasPermission name="userquery:signalHostConfig:edit"><li><a href="${ctx}/userquery/signalHostConfig/form">添加IP配置</a></li></shiro:hasPermission>
		<shiro:hasPermission name="userquery:signalHostConfig:import"><li class="active"><a href="${ctx}/userquery/signalHostConfig/importForm">导入</a></li></shiro:hasPermission>
	</ul>
	<form:form id="inputForm" enctype="multipart/form-data" method="post" action="" class="form-horizontal">
      <div class="control-group">
			<label class="control-label">选择的文件:<font color="red">*</font>&nbsp;</label>
			<div class="controls">
				<input type="file" name="upload" id="upload"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件上传:<font color="red">*</font>&nbsp;</label>
			<div class="controls">
				<div id="button_div"  ><input type="button" name="save" value="上传文件" onclick="doSubmit()"></div>
			</div>
		</div>
	</form:form>
</body>
</html>