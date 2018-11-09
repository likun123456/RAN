<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单网元参数修改</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#executetype").change(function(){
				var exectype = $(this).val();
				if(exectype=="task"){
					$("#datetime").css("display","inline");
				}else{
					$("#datetime").css("display","none");
				}
			});
		});
	</script>
</head>
<body>
	<form:form id="inputForm" method="post" class="form-horizontal">
		
		<input type="hidden" id="netid" name="netid" value="${paramData.netid }">
		<input type="hidden" id="modcmd" name="modcmd" value="${paramData.modcmd }">
		<input type="hidden" id="valuerange" name="valuerange" value="${paramData.valuerange }">
		
		<div class="control-group">
			<label class="control-label">指令内容：</label>
			<div class="controls">
				${paramData.modcmd }&nbsp;<input type="text" style="width:100px;" name="cmdvalue" id="cmdvalue"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">指令执行时间：</label>
			<div class="controls">
				<select id="executetype" style="width: 200px">
			       <option value="def">--请选择--</option>
			       <option value="now">立即执行</option>
			       <option value="task">计划任务执行</option>
			   </select>
			   <input id="datetime" type="text"  style="display:none;" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</div>
		</div>
	</form:form>
</body>
</html>