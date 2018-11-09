<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网元参数更新包管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	//提交
	function doSubmit(){

		//资源名称
		var name = document.getElementById('upload').value;
		var regex = ".*\\.tar\\.gz";
		var reg = new RegExp(regex);
		if(name == ""){
			top.$.jBox.tip('请选择文件...','warning');
			document.getElementById("upload").focus();
			return;
		}
		if(!reg.test(name)){
			top.$.jBox.tip('请选择正确文件格式文件(**.tar.gz)','warning');
			document.getElementById("upload").focus();
			return;
		}
		
		
		/* var files = document.getElementById('upload').files;
		var regex = ".*\\.tar\\.gz";
		for(var i=0; i<files.length; i++) {
			var name = files[i].name;
			var reg = new RegExp(regex);
			if(name == ""){
				top.$.jBox.tip('请选择文件...','warning');
				document.getElementById("upload").focus();
				return;
			}
			if(!reg.test(name)){
				top.$.jBox.tip('请选择正确文件格式文件(**.tar.gz)','warning');
				document.getElementById("upload").focus();
				return;
			}
		} */
		
		document.forms[0].action = "${ctx}/paramconfig/paramPackage/export";
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
		<li><a href="${ctx}/paramconfig/paramPackage/">参数包列表</a></li>
		<li class="active"><a href="${ctx}/paramconfig/paramPackage/exportForm"><shiro:hasPermission name="paramconfig:paramPackage:export">导入</shiro:hasPermission></a></li>
	</ul>
	<form:form id="inputForm" enctype="multipart/form-data" method="post" action="" class="form-horizontal">
		<%-- <table align="center" style="border-collapse: collapse;width: 480px;margin-top: 20px; font-size:12px; line-height:28px;">
			<tr>
				<td>
				 网元：<sys:treeselect id="netid" name="netid" cssStyle="width:138px;" value="${netid}" labelName="netname" labelValue="${netid}"
					title="网元名称" url="/paramconfig/paramPackage/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
				</td>
			</tr>
			
			<tr style="height: 10%;">
  				<td colspan="2" style="font-size: 14px;text-align: center;font-weight: bold;">更新包上传</td>
  			</tr>
  			<tr style="height: 10%;">
  				<td style="border: 1px solid #AACBDB;width: 340px;background-color: #F0FAFF;text-align: center;">选择的文件:<font color="red">*</font>&nbsp;</td>
		    	<td style="border: 1px solid #AACBDB;width: 140px;background-color: #F0FAFF;text-align: left;">
		    		<input type="file" name="upload" id="upload" />
		    	</td>
		    </tr>
		    <tr >
		    	<td style="border: 1px solid #AACBDB;width: 340px;background-color: #F0FAFF;text-align: center;" >文件上传:<font color="red">*</font>&nbsp;</td>
				<td style="border: 1px solid #AACBDB;width: 140px;background-color: #F0FAFF;text-align: left;" >
		        	<div id="button_div"  ><input type="button" name="save" value="上传文件" onclick="doSubmit()"></div>
		        </td>
		    </tr>
      </table> --%>
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