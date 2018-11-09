<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>危险指令管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var status = '${tVisDangerStatus.status}';
			if(status == 1){
				$("input[name='radiobutton']").get(0).checked=true; 
			}else{
				$("input[name='radiobutton']").get(1).checked=true; 
			}
			$("input[name='radiobutton']").change(function(){
		        var o = $("input[name='radiobutton']:checked").val();
		        $.post('${ctx}/netconfig/tVisDanger/updateStatus', {
				       "status": o
				    }, function(data) {
				});
		        if(o==1){
		        	top.$.jBox.tip('危险指令已启用','warning');
		        }else{
		        	top.$.jBox.tip('危险指令已停用','warning');
		        }
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
		<li class="active"><a href="${ctx}/netconfig/tVisDanger/">危险指令列表</a></li>
		<shiro:hasPermission name="netconfig:tVisDanger:edit"><li><a href="${ctx}/netconfig/tVisDanger/form">危险指令添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tVisDanger" action="${ctx}/netconfig/tVisDanger/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="controls">
			    
		</div>
		<ul class="ul-form">
		    <li><label>网元类型:</label>
			    <form:select path="nettype" class="required input-xlarge" style="width:200px;">
			        <form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_pool_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		    <li><label>危险指令:</label>
				<form:input path="command" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li>
				<label><b>指令状态:</b></label>
		        <input type="radio" value="1" name="radiobutton" class="input-medium"/>
		                     <b>禁止发送危险指令</b>
                <input type="radio" value="0" name="radiobutton" class="input-medium"/>
                             <b>允许发送危险指令</b>
            </li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>网元类型</th>
			    <th>危险指令</th>
				<shiro:hasPermission name="netconfig:tVisDanger:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tVisDanger">
			<tr>
			    <td>${tVisDanger.nettype}</td>
			    <td>${tVisDanger.command}</td>
				<shiro:hasPermission name="netconfig:tVisDanger:edit"><td>
    				<a href="${ctx}/netconfig/tVisDanger/form?id=${tVisDanger.id}">修改</a>
					<a href="${ctx}/netconfig/tVisDanger/delete?id=${tVisDanger.id}" onclick="return confirmx('确认要删除该危险指令吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>