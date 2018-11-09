<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公式配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var ret = doSubmit();
					if(ret == true) {
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
			
			//判断上限下限
			var formulaType = "${tFormula.formulaType}";
			if(formulaType=='undefined' || formulaType=='' || formulaType== "0" ){
				$("input[name='formulaType'][value=0]").attr("checked",true); 
				$('.thresholdUp').css({display:"none"});
			}else{
				$("input[name='formulaType'][value=1]").attr("checked",true); 
				$('.thresholdUp').css({display:"block"});
			}

			$("input[name='formulaType']").click(function (){
				 if(this.value == 0){
					 $('.thresholdUp').css({display:"none"});
				 }else{
					$('.thresholdUp').css({display:"block"});
				 }
			});
		});
		
		function doSubmit(){
			var thresholdType = $("input[name='formulaType']:checked").val();
			var topThreshold = $("input[name='topThreshold']").val(); 
			var downThreshold = $("input[name='downThreshold']").val();
			
			if(thresholdType==1 && topThreshold=="" && downThreshold=="" ){
				top.$.jBox.tip('预警公式必须输入上限值或下限值','warning');
				return false;
			}
			if(thresholdType==1 && topThreshold!="" && downThreshold!="" && parseFloat(topThreshold) <= parseFloat(downThreshold)){
				top.$.jBox.tip('预警公式必须上限值必须大于下限值','warning');
				return false;
			}
			return true;
		}
		
		function keyPress(ob) {
			 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
		}
		function keyUp(ob) {
			 if (!ob.value.match(/^[\+\-]?\d*?\.?\d*?$/)) ob.value = ob.t_value; else ob.t_value = ob.value; if (ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/)) ob.o_value = ob.value;
		}
		function onBlur(ob) {
			if(!ob.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))ob.value=ob.o_value;else{if(ob.value.match(/^\.\d+$/))ob.value=0+ob.value;if(ob.value.match(/^\.$/))ob.value=0;ob.o_value=ob.value};
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/netconfig/tFormula/">公式配置列表</a></li>
		<li class="active"><a href="${ctx}/netconfig/tFormula/form?id=${tFormula.id}">公式配置<shiro:hasPermission name="netconfig:tFormula:edit">${not empty tFormula.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="netconfig:tFormula:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tFormula" action="${ctx}/netconfig/tFormula/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">公式名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="1000" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元类型：</label>
			<div class="controls">
				<form:select path="nettype" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('biz_formula_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计类型：</label>
			<div class="controls">
				<form:radiobuttons path="countertype" items="${fns:getDictList('biz_forluma_counter_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统计状态：</label>
			<div class="controls">
				<form:radiobuttons path="flag" items="${fns:getDictList('biz_epg_datasouce_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Obj类型：</label>
			<div class="controls">
				<form:input path="objtype" htmlEscape="false" maxlength="50" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公式类型：</label>
			<div class="controls">
				<form:radiobuttons  path="formulaType" items="${fns:getDictList('biz_formula_earlywarning_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<%-- <form:hidden path="earlywarning"/> --%>
			</div>
		</div>
		<div class="thresholdUp" >
			<div class="control-group" >
				<label class="control-label">预警上限：</label>
				<div class="controls">
					<form:input path="topThreshold" htmlEscape="false" maxlength="10" class="input-mini " onkeypress="keyPress(this)" onkeyup="keyUp(this)" onBlur="onBlur(this)"/>
					&nbsp;&nbsp;&nbsp;&nbsp;预警下限：
					<form:input path="downThreshold" htmlEscape="false" maxlength="10" class="input-mini " onkeypress="keyPress(this)" onkeyup="keyUp(this)" onBlur="onBlur(this)"/>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公式内容：</label>
			<div class="controls">
				<form:textarea path="formulatext" htmlEscape="false" rows="5" maxlength="1000" class="input-xxlarge required"/>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tFormula:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>