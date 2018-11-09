<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>执行模板日志管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnDownloads").click(function(){
		    var ids = "";
		    $("[name='allIds']:checked").each(function(index,domEle){
		    	ids += domEle.value + ",";
		    });
		    if(ids==""){
		    	showTip("请选择需要下载的文件");
		    	return;
		    }
		    window.location.href= "${ctx}/netconfig/tVisExcelExcuteLog/downLoadZip.do?ids=" + ids;
	    })
	});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/netconfig/tVisExcelExcuteLog/">执行模板日志列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tVisExcelExcuteLog" action="${ctx}/netconfig/tVisExcelExcuteLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		    <li><label>模板名称:</label>
				<form:input path="excelname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>网元:</label>
			    <form:select path="netid" class="input-xlarge" style="width:180px;" id="netid">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li><label>开始时间:</label>
			    <form:input id="startTime" path="starttime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>结束时间:</label>
			    <form:input id="endTime" path="endtime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li class="btns">
			
				<input id="btnSubmit" type="submit" style="background: url('${ctxStatic}/images/icon-search.png'); width:86px;height:30px;border:0px" value="">
				
			</li>
			
			<li class="btns">
				<input id="btnDownloads" type="button" style="background: url('${ctxStatic}/images/icon-download.png'); width:135px;height:30px;border:0px" value="">
				
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th width="40"><input type="checkbox" name="allIds" onClick="checkAll(this,'checks')" class="checkbox-style"/></th>
			    <th>模板</th>
			    <th>网元</th>
			    <th>日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tVisExcelExcuteLog">
			<tr>
			    <td width="40">
			        <input type='checkbox' name='allIds' onclick='checkNode(this)' value='${tVisExcelExcuteLog.id}' class="checkbox-style"/>
			    </td>
			    <td>${tVisExcelExcuteLog.excelname}</td>
			    <td>${tVisExcelExcuteLog.netname}</td>
			    <td>${tVisExcelExcuteLog.datetime}</td>
				<td>
    				<a href="${ctx}/netconfig/tVisExcelExcuteLog/download?log=${fns:urlEncode(tVisExcelExcuteLog.log)}&excelname=${fns:urlEncode(tVisExcelExcuteLog.excelname)}&netname=${tVisExcelExcuteLog.netname}&datetime=${tVisExcelExcuteLog.datetime}">下载日志</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>