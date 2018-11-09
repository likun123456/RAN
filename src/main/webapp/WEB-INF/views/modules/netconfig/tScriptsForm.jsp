<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>统计指标脚本配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate({
				rules: {
					name: {remote: "${ctx}/netconfig/tScripts/checkName?oldName=" + encodeURIComponent("${tFormulaScripts.name}")},
				},
				messages: {
					name: {remote: "脚本名已存在"},
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
			
			//指令内容动态增加事件处理
			$('#commandUL').on('click','.addCmdItem',function(){
			    var cerLi = $("#commandUL li");
				if(cerLi.length > 49){
					top.$.jBox.tip('每个脚本最多添加50条指令','warning');
					return;
				}
				var dataid = $('#commandUL input:last-child').attr('data-id');
				//alert(dataid);
				$('#commandUL').append('<li style="list-style-type:none;margin: 0 0 10px 0px;">'+
	                       '<label class="control-label">指令内容:</label>'+
	                       '<div class="controls">'+
	                       '<textarea id="commandsList'+(parseInt(dataid)+1)+'.command" name="commandsList['+(parseInt(dataid)+1)+'].command"></textarea>'+
	                       '<a  class ="removeCmdItem"><img src="${ctxStatic}/jerichotab/images/list_07.jpg" style="cursor:pointer;margin-left:4px" /></a>'+
	                       '</div>'+
	                     	'</li>' +
	                     	'<input type="hidden" name="commandsList['+(parseInt(dataid)+1)+'].id"  data-id='+(parseInt(dataid)+1)+'>');
				 
			});
			
			//指令内容动态删除事件处理
			$('#commandUL').on('click','.removeCmdItem',function(){
				$(this).parents('li').remove();
				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/netconfig/tScripts">统计指标脚本配置列表</a></li>
		<li class="active"><a href="${ctx}/netconfig/tScripts/form?id=${tFormulaScripts.id}">统计指标脚本配置<shiro:hasPermission name="netconfig:tFormulaScripts:edit">${not empty tFormulaScripts.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tFormulaScripts:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tFormulaScripts" action="${ctx}/netconfig/tScripts/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">脚本名称:</label>
			<div class="controls">
			   <form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge "/>
				<font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">脚本类型:</label>
			<div class="controls">
				 <form:select path="scriptsType" class="required input-xlarge">
					<form:options items="${fns:getDictList('biz_scripts_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<div style="text-align: left;overflow-x: auto;overflow-y: hidden; margin-left: 0px;">
			 <font color="red"><p style="padding-left: 120px">备注 * 指令的执行顺序将按照填写的顺序执行,请谨慎填写!</p></font>
				<ul id="commandUL" style="padding: 0; margin: 0 0 10px 0px;">
                   	<c:if test="${fn:length(tFormulaScripts.commandsList) == 0}">
                   		<li style="list-style-type:none;margin: 0 0 10px 0px;">
	                   		<label class="control-label">指令内容:</label>
	                   		<div class="controls">
	                   			<%-- <form:textarea path="commandsList[0].command" /> --%>
	                   			    <textarea id="commandsList0.command" name="commandsList[0].command"></textarea>
			                       	<a  class ="addCmdItem"><img src="${ctxStatic}/jerichotab/images/list_03.jpg" style="cursor:pointer;" /></a>
	                   		</div>
	                    </li>
	                    <input type="hidden" name="commandsList[0].id" data-id="0">
                   	</c:if>
                   	
                   	<c:forEach items="${tFormulaScripts.commandsList}" var="command" begin="0" step="1" varStatus="status">
	                   	<li style="list-style-type:none;margin: 0 0 10px 0px;">
	                   		<label class="control-label">指令内容:</label>
	                   		<div class="controls">
	                   			<%-- <form:textarea path="commandsList[0].command" /> --%>
	                   			    <textarea id="commandsList${status.index}.command" name="commandsList[${status.index}].command">${command.command}</textarea>
		                       		<c:choose>
			                       		<c:when test="${status.index == 0}">
			                       			<a  class ="addCmdItem"><img src="${ctxStatic}/jerichotab/images/list_03.jpg" style="cursor:pointer;" /></a>
		                   				</c:when>
		                   				 <c:otherwise> 
		                   				 	<a  class ="removeCmdItem"><img src="${ctxStatic}/jerichotab/images/list_07.jpg" style="cursor:pointer;" /></a>
   										</c:otherwise>
	                   				</c:choose>
	                   		</div>
	                    </li>
	                    <input type="hidden" name="commandsList[${status.index}].id" value="${command.id}" data-id="${status.index}">
                   	</c:forEach>
            	</ul>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tFormulaScripts:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		
	</form:form>
</body>
</html>