<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>节点连线设置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/vis/vis.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/vis/vis-network.min.css" />
<script type="text/javascript">
	$(document).ready(
			function() {
				    $("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
					});
				    $("#equType").change(function(){
				    	var equType;
				    	var roomId;
				    	if ($("#roomId option:selected").val()=="0"){
		                	top.$.jBox.tip('请选择所属机房','warning');
				    	}else if($("#equType option:selected").val()=="0"){
				    		top.$.jBox.tip('请选择设备类型','warning');
						}else{					    	
							equType = $(this).find("option:selected").val();
							roomId=$("#roomId option:selected").val();
					   		$.post('${ctx}/netconfig/tEdges/queryFromEqu', {
						       "equType": equType,
						       "roomId":roomId
						    }, function(data) {
							    	var fromEquList = eval(data);
							    	$("#fromEqu option:not(:first)").remove();  
							    	$("#fromEqu").html("<option value=''>请选择</option>")
							    	$.each(fromEquList, function(index, o) { 
							    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
									    $("#fromEqu").append(html);
									}); 
							    	$("#fromEqu option:first").prop("selected", 'selected'); 
							    });
						}
				    });
				    
				    $("#fromEqu").change(function(){
				    	$("#oidId").val("");
				    	$("#oidName").val("");
						//始端设备信息
						$("#oidepc").val($(this).find("option:selected").val());
				    });
				    $("#equType").change(function(){
				    	$("#oidId").val("");
				    	$("#oidName").val("");
				    });
				    $("#roomId").change(function(){
				    	$("#oidId").val("");
				    	$("#oidName").val("");
						//传递机房信息
						$("#oidpoolName").val($(this).find("option:selected").val());
				    });
			});
	function saveData(){
		$.post('${ctx}/netconfig/tEdges/save', {
	        "roomId": $("#roomId").find("option:selected").val(),
	        "fromEqu":$("#fromEqu").find("option:selected").val(),
	        "toEqu":$("#oidId").val()
	    }, function(dataQuery) {
	    	if(dataQuery=="success"){
	    		top.$.jBox.tip('保存成功','warning');	
	    		location.reload();
	    	}else{
	    		top.$.jBox.tip('保存失败','warning');
	    	}
	    });
	}
	function showTopology(){
		//alert($("#oidName").val());
		//alert($("#fromEqu").find("option:selected").text());
		var nodeArray = [];
		var edgeArray = [];
		var firstNode = {};
		firstNode.id=1;
		firstNode.label=$("#fromEqu").find("option:selected").text();
		nodeArray.push(firstNode);
		var nodes = $("#oidName").val();
		var otherNodes = nodes.split(',');
		for(var i=1; i<=otherNodes.length; i++) {
			var n = {};
			n.id=i+1;
			n.label=otherNodes[i-1];
			nodeArray.push(n);
			
			var edge = {};
			edge.from=firstNode.id;
			edge.to=n.id;
			edgeArray.push(edge);
			
		}
		
		var div = '<div id="topology" style="width:95%;height:400px"></div>';
		$(document.body).append(div); 
		
		var container = document.getElementById('topology');
		  var data = {
		    nodes: nodeArray,
		    edges: edgeArray
		  };
		  var options = {};
		  var network = new vis.Network(container, data, options);
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/netconfig/tEdges/form">节点连线管理</a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="tEdges"
		action="${ctx}/netconfig/tEdges/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label>所属机房:</label>
			<form:select path="roomId" class="input-xlarge required" id="roomId">
				<form:option value="0" label="请选择" />
				<form:options items="${crList}" itemLabel="name" itemValue="id"
					htmlEscape="false" />
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span> 
			<label>设备类型：</label>
			<form:select path="fromType" class="input-xlarge required" id="equType">
				<form:option value="0" label="请选择" />
				<form:options items="${fns:getDictList('biz_vis_net_type')}"
					itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label>始端设备:</label>
			<form:select path="fromEqu" class="input-xlarge required" id="fromEqu">
				<form:option value="0" label="请选择" />
			</form:select>
			<span class="help-inline"><font color="red">*</font> </span> 
			<label>对端设备：</label>
			<sys:treeselect id="oid" name="oid" cssStyle="width:207px;" value="${oid}" labelName="oid" labelValue="${oid}"
					title="设备名称" url="/netconfig/tEdges/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
			<span class="help-inline"><font color="red">*</font> </span>
			<input id="btnCancel" class="btn" type="button" value="预览线路"
				onclick="showTopology()" />
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="netconfig:tEdges:edit">
			
				<input id="btnSubmit" class="btn btn-primary" type="button"
					value="保 存" onclick="saveData()"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
				
		</div>
	</form:form>
</body>
</html>