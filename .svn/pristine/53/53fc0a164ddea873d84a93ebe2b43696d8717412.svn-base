<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户EBM查询详细信息</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel:5}).show();
		});
	</script>
</head>
<body>
<div >
	  <div class="navbar navbar-default navbar-fixed-top">
	      <div class="navbar-inner">
	        <span class="brand" style="padding-left:40px;font-size:14px;">EBM LOG详细信息</span>
	        <span id="numAndTime" style="float:right;padding:15px;">用户IMSI:&nbsp;&nbsp;${imsi}&nbsp;&nbsp;</span>
	      </div>
	  </div>
	  <div style="margin-top:60px;">
	  <div style="float:left;overflow-y :auto;height:500px;width:50%">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tbody><c:forEach items="${ebmDetail}" var="ebmDetail">
				<tr id="${ebmDetail.id}" pId="${ebmDetail.pid}">
					<td width="30%">${ebmDetail.name}</td>
					<td width="70%">${ebmDetail.value}</td>
				</tr>
			</c:forEach></tbody>
		</table>
	 </div>
	 <div style="float:left;height:400px;width:50%">
	     <table class="table table-striped table-hover table-bordered table-condensed" >
			<tbody>
			   <tr>
				  <td width="20%"><label>时间</label></td><td>${date }</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>事件</label></td><td>${eventId }</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>事件结果</label></td><td>${eventResult }</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>小区名称</label></td><td>${eci}</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>问题定界</label></td><td>${fns:getDictLabel(domain,'domain',domain)}</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>原因码</label></td><td>${cc}</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>原因解释</label></td><td>${ccdes }</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>子原因码</label></td><td>${scc}</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>子原因解释</label></td><td>${sccdes }</td>
			   </tr>
			    <tr>
				  <td width="20%"><label>建议行动</label></td><td>${action }</td>
			   </tr>
			</tbody>
		</table>
	 </div>
	 </div>
</body>
</html>