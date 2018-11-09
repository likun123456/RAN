<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>首页设置</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
$(document).ready(function() {
		$("#formulaTypeepc").val("MME,EPG-PGW,EPG-SGW,MSC,MGW,HSS,HLRFE,CUDB,PG");
	});
	function saveFormula(){
		$.post('${ctx}/performance/indexsetting/saveFormula', {
	        "mmekpione": $("#mmekpione option:selected").val(),
	        "mmekpitwo": $("#mmekpitwo option:selected").val(),
	        "mmekpithree": $("#mmekpithree option:selected").val(),
	        "mmekpifour": $("#mmekpifour option:selected").val(),
	        "mmekpifive": $("#mmekpifive option:selected").val(),
	        "mmekpisix": $("#mmekpisix option:selected").val(),
	        "saegwkpione": $("#saegwkpione option:selected").val(),
	        "saegwkpitwo": $("#saegwkpitwo option:selected").val(),
	        "saegwkpithree": $("#saegwkpithree option:selected").val(),
	        "saegwkpifour": $("#saegwkpifour option:selected").val(),
	        "saegwkpifive": $("#saegwkpifive option:selected").val(),
	        "saegwkpisix": $("#saegwkpisix option:selected").val(),
	        "msckpione": $("#msckpione option:selected").val(),
	        "msckpitwo": $("#msckpitwo option:selected").val(),
	        "msckpithree": $("#msckpithree option:selected").val(),
	        "msckpifour": $("#msckpifour option:selected").val(),
	        "msckpifive": $("#msckpifive option:selected").val(),
	        "msckpisix": $("#msckpisix option:selected").val(),
	        "pcrfkpione": $("#pcrfkpione option:selected").val(),
	        "pcrfkpitwo": $("#pcrfkpitwo option:selected").val(),
	        "pcrfkpithree": $("#pcrfkpithree option:selected").val(),
	        "pcrfkpifour": $("#pcrfkpifour option:selected").val(),
	        "pcrfkpifive": $("#pcrfkpifive option:selected").val(),
	        "pcrfkpisix": $("#pcrfkpisix option:selected").val(),
	        "mgwkpione": $("#mgwkpione option:selected").val(),
	        "mgwkpitwo": $("#mgwkpitwo option:selected").val(),
	        "mgwkpithree": $("#mgwkpithree option:selected").val(),
	        "mgwkpifour": $("#mgwkpifour option:selected").val(),
	        "mgwkpifive": $("#mgwkpifive option:selected").val(),
	        "mgwkpisix": $("#mgwkpisix option:selected").val(),
	        "hlrfekpione": $("#hlrfekpione option:selected").val(),
	        "hlrfekpitwo": $("#hlrfekpitwo option:selected").val(),
	        "hlrfekpithree": $("#hlrfekpithree option:selected").val(),
	        "hlrfekpifour": $("#hlrfekpifour option:selected").val(),
	        "hlrfekpifive": $("#hlrfekpifive option:selected").val(),
	        "hlrfekpisix": $("#hlrfekpisix option:selected").val(),
	        "hsskpione": $("#hsskpione option:selected").val(),
	        "hsskpitwo": $("#hsskpitwo option:selected").val(),
	        "hsskpithree": $("#hsskpithree option:selected").val(),
	        "hsskpifour": $("#hsskpifour option:selected").val(),
	        "hsskpifive": $("#hsskpifive option:selected").val(),
	        "hsskpisix": $("#hsskpisix option:selected").val(),
	        "cudbkpione": $("#cudbkpione option:selected").val(),
	        "cudbkpitwo": $("#cudbkpitwo option:selected").val(),
	        "cudbkpithree": $("#cudbkpithree option:selected").val(),
	        "cudbkpifour": $("#cudbkpifour option:selected").val(),
	        "cudbkpifive": $("#cudbkpifive option:selected").val(),
	        "cudbkpisix": $("#cudbkpisix option:selected").val(),
	        "pgkpione": $("#pgkpione option:selected").val(),
	        "pgkpitwo": $("#pgkpitwo option:selected").val(),
	        "pgkpithree": $("#pgkpithree option:selected").val(),
	        "pgkpifour": $("#pgkpifour option:selected").val(),
	        "pgkpifive": $("#pgkpifive option:selected").val(),
	        "pgkpisix": $("#pgkpisix option:selected").val(),
	        "starttime":$("#starttime option:selected").val()
	    }, function(dataQuery) {
	    	if(dataQuery=="success"){
	    		top.$.jBox.tip('数据保存成功','warning');	
	    	}else{
	    		top.$.jBox.tip('数据保存失败','warning');
	    	}
	    });
	}
	function saveKpiParam(){
		$.post('${ctx}/performance/indexsetting/saveKpiParam', {
	        "excellentUp": $("#excellentUp").val(),
	        "excellentDown": $("#excellentDown").val(),
	        "goodUp": $("#goodUp").val(),
	        "goodDown": $("#goodDown").val(),
	        "wellUp": $("#wellUp").val(),
	        "wellDown": $("#wellDown").val(),
	        "badUp": $("#badUp").val(),
	        "badDown": $("#badDown").val(),
	        "alarmUp": $("#alarmUp").val(),
	        "alarmDown": $("#alarmDown").val(),
	        "formulaType":$("#formulaTypeId").val(),
	        "chk":$("#chk")[0].checked
	    }, function(dataQuery) {
	    	if(dataQuery=="success"){
	    		top.$.jBox.tip('数据保存成功','warning');	
	    	}else{
	    		top.$.jBox.tip(dataQuery,'warning');
	    	}
	    });
	}
	function saveKpiEvent(){
		$.post('${ctx}/performance/indexsetting/saveKpiEvent', {
	        "event": $("#kpievent option:selected").val(),
	        "formulaType":$("#formulaTypeEventId").val(),
	        "chk":$("#chkEvent")[0].checked
	    }, function(dataQuery) {
	    	if(dataQuery=="success"){
	    		top.$.jBox.tip('数据保存成功','warning');	
	    	}else{
	    		top.$.jBox.tip(dataQuery,'warning');
	    	}
	    });
	}
	function checkChange(){
		if($("#chk")[0].checked){
			$("#formulaTypeName")[0].disabled=true;
			$("#formulaTypeButton")[0].disabled=true;
		}else{
			$("#formulaTypeName")[0].disabled=false;
			$("#formulaTypeButton")[0].disabled=false;
		}
	}
	function checkEventChange(){
		if($("#chkEvent")[0].checked){
			$("#formulaTypeName")[0].disabled=true;
			$("#formulaTypeButton")[0].disabled=true;
		}else{
			$("#formulaTypeName")[0].disabled=false;
			$("#formulaTypeButton")[0].disabled=false;
		}
	}
	function kpiParam(){
		var kpiArr=$("#formulaTypeId").val().split(',');
		if(kpiArr.length==1){
			$.post('${ctx}/performance/indexsetting/kpiParamOld', {
		        "kpiId": kpiArr[0]
		    }, function(dataQuery) {		    	
		    	$("#excellentUp").val(dataQuery.excellentUp);
		        $("#excellentDown").val(dataQuery.excellentDown);
		        $("#goodUp").val(dataQuery.goodUp);
		        $("#goodDown").val(dataQuery.goodDown);
		        $("#wellUp").val(dataQuery.wellUp);
		        $("#wellDown").val(dataQuery.wellDown);
		        $("#badUp").val(dataQuery.badUp);
		        $("#badDown").val(dataQuery.badDown);
		        $("#alarmUp").val(dataQuery.alarmUp);
		        $("#alarmDown").val(dataQuery.alarmDown);
		     });
		}
	}
	
	function kpiEvent(){
		debugger;
		var kpiArr=$("#formulaTypeEventId").val().split(',');
		if(kpiArr.length==1){
			$.post('${ctx}/performance/indexsetting/kpiEventOld', {
		        "kpiId": kpiArr[0]
		    }, function(dataQuery) {	
		    	$("#kpievent").val(dataQuery.event);
				$("span[class='select2-chosen']").get(49).innerHTML=dataQuery.temp_field1;
		     });
		}
	}
</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-j" style="font-size: 45px; float:left;margin-left:10px;"></span>
			<a class="brand" style="font-size: 15px; margin-left: 30px;">首页设置</a>
		</div>
		<form:form modelAttribute="tIndexSettingVO">
		<div  style="height:360px;overflow-y:scroll;margin-left:30px;">
			</br>
			&nbsp;&nbsp;<label><strong>首页显示 MME指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>MME 指标1</strong></label>
					</td>
					<td>
						<form:select path="mmekpione" class="input-xlarge" style="width:168px;" id="mmekpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${mmeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MME 指标2</strong></label>
					</td>
					<td>
						<form:select path="mmekpitwo" class="input-xlarge" style="width:168px;" id="mmekpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${mmeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MME 指标3</strong></label>
					</td>
					<td>
						<form:select path="mmekpithree" class="input-xlarge" style="width:168px;" id="mmekpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${mmeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>MME 指标4</strong></label>
					</td>
					<td>
						<form:select path="mmekpifour" class="input-xlarge" style="width:168px;" id="mmekpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${mmeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MME 指标5</strong></label>
					</td>
					<td>
						<form:select path="mmekpifive" class="input-xlarge" style="width:168px;" id="mmekpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${mmeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MME 指标6</strong></label>
					</td>
					<td >
						<form:select path="mmekpisix" class="input-xlarge" style="width:168px;" id="mmekpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${mmeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			</br>
			&nbsp;&nbsp;<label><strong>首页显示 SAEGW指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>SAEGW 指标1</strong></label>
					</td>
					<td>
						<form:select path="saegwkpione" class="input-xlarge" style="width:168px;" id="saegwkpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${saegwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>SAEGW 指标2</strong></label>
					</td>
					<td>
						<form:select path="saegwkpitwo" class="input-xlarge" style="width:168px;" id="saegwkpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${saegwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>SAEGW 指标3</strong></label>
					</td>
					<td>
						<form:select path="saegwkpithree" class="input-xlarge" style="width:168px;" id="saegwkpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${saegwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>SAEGW 指标4</strong></label>
					</td>
					<td>
						<form:select path="saegwkpifour" class="input-xlarge" style="width:168px;" id="saegwkpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${saegwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>SAEGW 指标5</strong></label>
					</td>
					<td>
						<form:select path="saegwkpifive" class="input-xlarge" style="width:168px;" id="saegwkpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${saegwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>SAEGW 指标6</strong></label>
					</td>
					<td >
						<form:select path="saegwkpisix" class="input-xlarge" style="width:168px;" id="saegwkpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${saegwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			</br>
			&nbsp;&nbsp;<label><strong>首页显示 PCRF指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>PCRF 指标1</strong></label>
					</td>
					<td>
						<form:select path="pcrfkpione" class="input-xlarge" style="width:168px;" id="pcrfkpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${pcrfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PCRF 指标2</strong></label>
					</td>
					<td>
						<form:select path="pcrfkpitwo" class="input-xlarge" style="width:168px;" id="pcrfkpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${pcrfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PCRF 指标3</strong></label>
					</td>
					<td>
						<form:select path="pcrfkpithree" class="input-xlarge" style="width:168px;" id="pcrfkpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${pcrfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>PCRF 指标4</strong></label>
					</td>
					<td>
						<form:select path="pcrfkpifour" class="input-xlarge" style="width:168px;" id="pcrfkpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${pcrfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PCRF 指标5</strong></label>
					</td>
					<td>
						<form:select path="pcrfkpifive" class="input-xlarge" style="width:168px;" id="pcrfkpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${pcrfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PCRF 指标6</strong></label>
					</td>
					<td >
						<form:select path="pcrfkpisix" class="input-xlarge" style="width:168px;" id="pcrfkpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${pcrfList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			<!--
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 MSC指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>MSC 指标1</strong></label>
					</td>
					<td>
						<form:select path="msckpione" class="input-xlarge" style="width:168px;" id="msckpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${mscList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MSC 指标2</strong></label>
					</td>
					<td>
						<form:select path="msckpitwo" class="input-xlarge" style="width:168px;" id="msckpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${mscList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MSC 指标3</strong></label>
					</td>
					<td>
						<form:select path="msckpithree" class="input-xlarge" style="width:168px;" id="msckpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${mscList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>MSC 指标4</strong></label>
					</td>
					<td>
						<form:select path="msckpifour" class="input-xlarge" style="width:168px;" id="msckpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${mscList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MSC 指标5</strong></label>
					</td>
					<td>
						<form:select path="msckpifive" class="input-xlarge" style="width:168px;" id="msckpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${mscList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MSC 指标6</strong></label>
					</td>
					<td >
						<form:select path="msckpisix" class="input-xlarge" style="width:168px;" id="msckpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${mscList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 MGW指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>MGW 指标1</strong></label>
					</td>
					<td>
						<form:select path="mgwkpione" class="input-xlarge" style="width:168px;" id="mgwkpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${mgwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MGW 指标2</strong></label>
					</td>
					<td>
						<form:select path="mgwkpitwo" class="input-xlarge" style="width:168px;" id="mgwkpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${mgwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MGW 指标3</strong></label>
					</td>
					<td>
						<form:select path="mgwkpithree" class="input-xlarge" style="width:168px;" id="mgwkpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${mgwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>MGW 指标4</strong></label>
					</td>
					<td>
						<form:select path="mgwkpifour" class="input-xlarge" style="width:168px;" id="mgwkpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${mgwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MGW 指标5</strong></label>
					</td>
					<td>
						<form:select path="mgwkpifive" class="input-xlarge" style="width:168px;" id="mgwkpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${mgwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>MGW 指标6</strong></label>
					</td>
					<td >
						<form:select path="mgwkpisix" class="input-xlarge" style="width:168px;" id="mgwkpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${mgwList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			
			
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 HLR-FE指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>HLR-FE 指标1</strong></label>
					</td>
					<td>
						<form:select path="hlrfekpione" class="input-xlarge" style="width:168px;" id="hlrfekpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${hlrfeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HLR-FE 指标2</strong></label>
					</td>
					<td>
						<form:select path="hlrfekpitwo" class="input-xlarge" style="width:168px;" id="hlrfekpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${hlrfeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HLR-FE 指标3</strong></label>
					</td>
					<td>
						<form:select path="hlrfekpithree" class="input-xlarge" style="width:168px;" id="hlrfekpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${hlrfeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>HLR-FE 指标4</strong></label>
					</td>
					<td>
						<form:select path="hlrfekpifour" class="input-xlarge" style="width:168px;" id="hlrfekpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${hlrfeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HLR-FE 指标5</strong></label>
					</td>
					<td>
						<form:select path="hlrfekpifive" class="input-xlarge" style="width:168px;" id="hlrfekpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${hlrfeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HLR-FE 指标6</strong></label>
					</td>
					<td >
						<form:select path="hlrfekpisix" class="input-xlarge" style="width:168px;" id="hlrfekpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${hlrfeList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 HSS指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>HSS 指标1</strong></label>
					</td>
					<td>
						<form:select path="hsskpione" class="input-xlarge" style="width:168px;" id="hsskpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${hssList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HSS 指标2</strong></label>
					</td>
					<td>
						<form:select path="hsskpitwo" class="input-xlarge" style="width:168px;" id="hsskpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${hssList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HSS 指标3</strong></label>
					</td>
					<td>
						<form:select path="hsskpithree" class="input-xlarge" style="width:168px;" id="hsskpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${hssList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>HSS 指标4</strong></label>
					</td>
					<td>
						<form:select path="hsskpifour" class="input-xlarge" style="width:168px;" id="hsskpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${hssList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HSS 指标5</strong></label>
					</td>
					<td>
						<form:select path="hsskpifive" class="input-xlarge" style="width:168px;" id="hsskpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${hssList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>HSS 指标6</strong></label>
					</td>
					<td >
						<form:select path="hsskpisix" class="input-xlarge" style="width:168px;" id="hsskpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${hssList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 CUDB指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>CUDB 指标1</strong></label>
					</td>
					<td>
						<form:select path="cudbkpione" class="input-xlarge" style="width:168px;" id="cudbkpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${cudbList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>CUDB 指标2</strong></label>
					</td>
					<td>
						<form:select path="cudbkpitwo" class="input-xlarge" style="width:168px;" id="cudbkpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${cudbList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>CUDB 指标3</strong></label>
					</td>
					<td>
						<form:select path="cudbkpithree" class="input-xlarge" style="width:168px;" id="cudbkpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${cudbList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>CUDB 指标4</strong></label>
					</td>
					<td>
						<form:select path="cudbkpifour" class="input-xlarge" style="width:168px;" id="cudbkpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${cudbList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>CUDB 指标5</strong></label>
					</td>
					<td>
						<form:select path="cudbkpifive" class="input-xlarge" style="width:168px;" id="cudbkpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${cudbList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>CUDB 指标6</strong></label>
					</td>
					<td >
						<form:select path="cudbkpisix" class="input-xlarge" style="width:168px;" id="cudbkpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${cudbList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			
			<br>
			&nbsp;&nbsp;<label><strong>首页显示PG指标设置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>PG 指标1</strong></label>
					</td>
					<td>
						<form:select path="pgkpione" class="input-xlarge" style="width:168px;" id="pgkpione">
				            <form:option value="" label="请选择"/>
							<form:options items="${pgList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PG 指标2</strong></label>
					</td>
					<td>
						<form:select path="pgkpitwo" class="input-xlarge" style="width:168px;" id="pgkpitwo">
				            <form:option value="" label="请选择"/>
							<form:options items="${pgList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PG 指标3</strong></label>
					</td>
					<td>
						<form:select path="pgkpithree" class="input-xlarge" style="width:168px;" id="pgkpithree">
				            <form:option value="" label="请选择"/>
							<form:options items="${pgList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>PG 指标4</strong></label>
					</td>
					<td>
						<form:select path="pgkpifour" class="input-xlarge" style="width:168px;" id="pgkpifour">
				            <form:option value="" label="请选择"/>
							<form:options items="${pgList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PG 指标5</strong></label>
					</td>
					<td>
						<form:select path="pgkpifive" class="input-xlarge" style="width:168px;" id="pgkpifive">
				            <form:option value="" label="请选择"/>
							<form:options items="${pgList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
					<td align="center" width="100px">
						<label><strong>PG 指标6</strong></label>
					</td>
					<td >
						<form:select path="pgkpisix" class="input-xlarge" style="width:168px;" id="pgkpisix">
				            <form:option value="" label="请选择"/>
							<form:options items="${pgList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			-->
			<div class="form-actions">
			<input id="saveBtn" class="btn" type="button" value="保存" onclick="saveFormula()"/>
			</div>
		</form:form>
		<form:form modelAttribute="tIndexSettingVO">
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 指标表盘配置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>选择指标</strong></label>
					</td>
					<td>
						<sys:treeselectCallBack id="formulaType" name="formulaType" cssStyle="width:138px;" value="${formulaType}" labelName="formulaType" labelValue="${formulaType}"
							title="指标" url="/performance/indexsetting/treeDataFormula" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" 
							ajaxCall="true"	ajaxCallMethod="kpiParam"/>
					</td>
					<td align="center" width="100px">
						<input type="checkbox" id="chk" onchange="checkChange()"> 
					</td>
					<td colspan="3">
						<label><strong>将以下门限定义为默认门限</strong></label>
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>级别 优 上限</strong></label>
					</td>
					<td>
						<input type="textfield" path="excellentUp" id="excellentUp">
					</td>
					<td align="center" width="100px">
						<label><strong>下限</strong></label>
					</td>
					<td>
						<input type="textfield" path="excellentDown" id="excellentDown">
					</td>
					<td align="center" width="100px">
						<label><strong>对应表盘</strong></label>
					</td>
					<td>
						<span class="icon-blue-5"  style="font-size: 30px; margin-top: -5px;margin-right: 15px"></span>(蓝色)
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>级别 良 上限</strong></label>
					</td>
					<td>
						<input type="textfield" path="goodUp" id="goodUp">
					</td>
					<td align="center" width="100px">
						<label><strong>下限</strong></label>
					</td>
					<td>
						<input type="textfield" path="goodDown" id="goodDown">
					</td>
					<td align="center" width="100px">
						<label><strong>对应表盘</strong></label>
					</td>
					<td>
						<span class="icon-green-4"  style="font-size: 30px; margin-top: -5px;margin-right: 15px"></span>(绿色)
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>级别 中 上限</strong></label>
					</td>
					<td>
						<input type="textfield" path="wellUp" id="wellUp">
					</td>
					<td align="center" width="100px">
						<label><strong>下限</strong></label>
					</td>
					<td>
						<input type="textfield" path="wellDown" id="wellDown">
					</td>
					<td align="center" width="100px">
						<label><strong>对应表盘</strong></label>
					</td>
					<td>
						<span class="icon-yellow-3"  style="font-size: 30px; margin-top: -5px;margin-right: 15px"></span>(黄色)
					</td>
				</tr>、
				<tr>
					<td align="center" width="100px">
						<label><strong>级别 差 上限</strong></label>
					</td>
					<td>
						<input type="textfield" path="badUp" id="badUp">
					</td>
					<td align="center" width="100px">
						<label><strong>下限</strong></label>
					</td>
					<td>
						<input type="textfield" path="badDown" id="badDown">
					</td>
					<td align="center" width="100px">
						<label><strong>对应表盘</strong></label>
					</td>
					<td>
						<span class="icon-orange-2"  style="font-size: 30px; margin-top: -5px;margin-right: 15px"></span>(橙色)
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>级别 预警 上限</strong></label>
					</td>
					<td>
						<input type="textfield" path="alarmUp" id="alarmUp">
					</td>
					<td align="center" width="100px">
						<label><strong>下限</strong></label>
					</td>
					<td>
						<input type="textfield" path="alarmDown" id="alarmDown">
					</td>
					<td align="center" width="100px">
						<label><strong>对应表盘</strong></label>
					</td>
					<td>
						<span class="icon-red-1"  style="font-size: 30px; margin-top: -5px;margin-right: 15px"></span>(红色)
					</td>
				</tr>
			</table>
			</div>
			<div class="form-actions">
			<input id="saveBtn" class="btn" type="button" value="保存" onclick="saveKpiParam()"/>
			</div>
		</form:form>
		<form:form modelAttribute="tIndexSettingVO">
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 统计时段配置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>最近小时</strong></label>
					</td>
					<td>
						<form:select path="starttime" class="input-xlarge" style="width:168px;" id="starttime">
				            <form:option value="" label="请选择"/>
							<form:option value="4" label="4"/>
							<form:option value="8" label="8"/>
							<form:option value="12" label="12"/>
							<form:option value="24" label="24"/>
						</form:select>
					</td>
				</tr>
			</table>
			</div>
			<div class="form-actions">
			<input id="saveBtn" class="btn" type="button" value="保存" onclick="saveFormula()"/>
			</div>
		</form:form>
		
		<form:form modelAttribute="tIndexSettingVO">
			<br>
			&nbsp;&nbsp;<label><strong>首页显示 指标事件配置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="100px">
				<tr>
					<td align="center" width="100px">
						<label><strong>选择指标</strong></label>
					</td>
					<td>
						<sys:treeselectCallBack id="formulaTypeEvent" name="formulaTypeEvent" cssStyle="width:138px;" value="${formulaType}" labelName="formulaType" labelValue="${formulaType}"
							title="指标" url="/performance/indexsetting/treeDataMMEFormula" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" 
							ajaxCall="true"	ajaxCallMethod="kpiEvent"/>
					</td>
					<td align="center" width="100px" style="display: none">
						 <input type="checkbox" id="chkEvent" onchange="checkEventChange()">
					</td>
					<td colspan="3">
						<!-- <label><strong>将以下事件定义为默认事件</strong></label> -->
					</td>
				</tr>
				<tr>
					<td align="center" width="100px">
						<label><strong>事件</strong></label>
					</td>
					<td>
						<form:select path="kpievent" class="input-xlarge" style="width:168px;" id="kpievent">
				            <form:option value="" label="请选择"/>
							<form:options items="${ebmEventlist}" itemLabel="eventfullname" itemValue="eventname" htmlEscape="false"/>
						</form:select>
					</td>
			</table>
			</div>
			<div class="form-actions">
			<input id="saveBtn" class="btn" type="button" value="保存" onclick="saveKpiEvent()"/>
			</div>
		</form:form>
		</div>
	</div>
</body>
</html>