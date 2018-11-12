<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>资产信息列表</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".siteNameInput").click(function(){
				$(this).removeAttr("readonly");
			});
			$(".siteNameInput").blur(function(){
				$(this).attr("readonly","readonly");
			});
			
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
		<li class="active"><a href="${ctx}/propertycheck/ranPropertyEquipment/">资产信息列表</a></li>
		<shiro:hasPermission name="propertycheck:ranPropertyEquipment:edit"><li><a href="${ctx}/propertycheck/ranPropertyEquipment/form">资产信息添加</a></li></shiro:hasPermission>
	</ul>
	<div class="panel panel-default">
	  <!-- Default panel contents -->
	  <div class="panel-heading"><h4>基站资产信息查询</h4></div>
	
	  <!-- Table -->
	  <table class="table table-responsive active">
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">站点名</td>
	  	<td>
	  	<form:form id="searchForm" class=" form-search" >
				<ul class="ul-form">
				    <li>
				        
				        <input class="siteNameInput" type="text" name="" value="" style="width: 120px" readonly="readonly"/>  
					</li>
					
					<li class="btns"><input id="btnQuery" class="btn-new btn-search" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul>
			</form:form>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">机框信息</td>
	  	<td>
	  		<table class="table table-striped table-responsive  table-bordered">
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
			</table>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">RRU信息</td>
	  	<td>
	  		<table class="table table-striped table-responsive  table-bordered">
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
			</table>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">基带信息</td>
	  	<td>
	  		<table class="table table-striped table-responsive  table-bordered">
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
			</table>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">支撑系统信息</td>
	  	<td>
	  		<table class="table table-striped table-responsive  table-bordered">
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
			</table>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">光模块信息</td>
	  	<td>
	  		<table class="table table-striped table-responsive  table-bordered">
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
			</table>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">DOT信息</td>
	  	<td>
	  		<table class="table table-striped table-responsive  table-bordered">
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
	  			<tr><td>MO</td><td>产品名称</td><td>产品号码</td><td>产品版本</td><td>生产日期</td><td>产品串号</td></tr>
			</table>
		</td>
	  </tr>
	  <tr>
	  	<td width="80px"><img height="70px" width="50px"  alt="" src="${ctxStatic}/jerichotab/images/fst.png"> </td>
	  	<td width="100px">板件更换记录查询</td>
	  	<td>
	  		<form:form id="searchForm" class="breadcrumb form-search" >
				<ul class="ul-form">
				    <li>
				        <label>板件类型：</label>
				        <input type="text" name="" value="" style="width: 120px" />  
					</li>
					<li>
				        <label>站点名：</label>
				        <input type="text" value="" name="" style="width: 120px"/>
					</li>
					
					<li id="startTimeLi">
				        <label>开始时间：</label>
				        <input type="text" style="width:160px;" class="Wdate" value="${beginday}" id="txtBeginDay" name="beginday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'txtEndDay\')}'});"/>
					</li>
					<li id="endTimeLi">
				        <label>结束时间：</label>
				        <input type="text" style="width:160px;" class="Wdate" value="${endday}" id="txtEndDay" name="endday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,minDate:'#F{$dp.$D(\'txtBeginDay\')}'});" />
					</li>
					<li class="btns"><input id="btnQuery" class="btn-new btn-search" type="submit" value="查询"/></li>
					<li class="clearfix"></li>
				</ul>
			</form:form>
		</td>
	  </tr>
	  </table>
	</div>
	
	
	
	<%-- <form:form id="searchForm" modelAttribute="ranPropertyEquipment" action="${ctx}/propertycheck/ranPropertyEquipment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form> --%>
<%-- 	<form:form id="searchForm" modelAttribute="ranPropertyEquipment" action="${ctx}/propertycheck/ranPropertyEquipment/insertBatch" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			 <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="入库"/></li>
			 
			 <li class="clearfix"></li>
		</ul>
	</form:form> --%>
	<%-- <sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<shiro:hasPermission name="propertycheck:ranPropertyEquipment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ranPropertyEquipment">
			<tr>
				<shiro:hasPermission name="propertycheck:ranPropertyEquipment:edit"><td>
    				<a href="${ctx}/propertycheck/ranPropertyEquipment/form?id=${ranPropertyEquipment.id}">修改</a>
					<a href="${ctx}/propertycheck/ranPropertyEquipment/delete?id=${ranPropertyEquipment.id}" onclick="return confirmx('确认要删除该对象类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table> --%>
	<div class="pagination">${page}</div>
</body>
</html>