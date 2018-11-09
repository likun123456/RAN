<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网元参数信息报表下载管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnDownloads").click(function(){
			    var paths = "";
			    $("[name='allIds']:checked").each(function(index,domEle){
			    	paths += domEle.value + ",";
			    });
			    if(paths==""){
			    	showTip("请选择需要下载的文件");
			    	return;
			    }
			    window.location.href= "${ctx}/paramconfig/tParamExportedExcel/downLoadZip.do?paths=" + paths;
		    })
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style type="text/css">
	.checkbox-style{
	    vertical-align: middle;
	    align:center;
	}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/paramconfig/tParamExportedExcel/">网元参数信息报表下载列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tParamExportedExcel" action="${ctx}/paramconfig/tParamExportedExcel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>开始时间:</label>
				<form:input path="startTime" htmlEscape="false" maxlength="200" onFocus="WdatePicker()" class="Wdate"/>
			</li>
			<li><label>结束时间:</label>
				<form:input path="endTime" htmlEscape="false" maxlength="200" onFocus="WdatePicker()" class="Wdate"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnDownloads" class="btn btn-primary" type="button" value="批量下载"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th width="40"><input type="checkbox" name="allIds" onClick="checkAll(this,'checks')" class="checkbox-style"/></th>
			    <th>文件</th>
			    <th>日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tParamExportedExcel">
			<tr>
			    <td width="40">
			        <input type='checkbox' name='allIds' onclick='checkNode(this)' value='${tParamExportedExcel.path}' class="checkbox-style"/>
			    </td>
			    <td>${tParamExportedExcel.path}</td>
			    <td>${tParamExportedExcel.datetime}</td>
				<td>
					<a href="${ctx}/paramconfig/tParamExportedExcel/downloadExcel?path=${tParamExportedExcel.path}">下载</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>