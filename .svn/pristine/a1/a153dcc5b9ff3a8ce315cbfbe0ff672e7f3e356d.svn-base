<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>网元统计采集设置</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					var ids = [], nodes = tree.getCheckedNodes(true);
					for(var i=0; i<nodes.length; i++) {
						ids.push(nodes[i].id);
					}
					$("#temp_field1").val(ids);//采集的网元ID
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

			var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{
						beforeClick:function(id, node){
							tree.checkNode(node, !node.checked, true, true);
							return false;
						}
						}
					};
			// 用户-菜单
			var zNodes=[
					<c:forEach items="${netelementList}" var="menu">
						{id:"${menu.fid}", pId:"${not empty menu.fid?menu.fnid:0}", name:"${not empty menu.temp_field1?menu.temp_field2:menu.fname}"},
		            </c:forEach>];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#netTree"), setting, zNodes);
			// 不选择父节点
			tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			<c:forEach items="${netelementList}" var="menu">
				if("${menu.temp_field1}"=='check'){
					var node = tree.getNodeByParam("id", "${menu.fid}");
					try{tree.checkNode(node, true, false);}catch(e){}
				}
			</c:forEach>
			<c:forEach items="${netelementList}" var="menu">
				var node = tree.getNodeByParam("id", "${menu.fid}");
				tree.setChkDisabled(node, true);
			</c:forEach>
			// 默认展开全部节点
			tree.expandAll(true);
			
			//参数一致性和自动巡检 不可以选择采集器    后台配置自动回显示
			if('${type}'=='7' || '${type}'=='8'){
				$("#selectCollect").val('${collectorId}');
				$("span[class='select2-chosen']").first().html('${collectorIp}');
				/* $("#selectCollect").attr("readonly","true"); */
				$("#collotordiv").hide();
				selectChange();
			}
			
			
		});
		function selectChange(){
	    	var zTree = $.fn.zTree.getZTreeObj("netTree");
	    	//获取根节点
	    	var nodes = zTree.getNodes();
			var collector = $("#selectCollect").val();
			if(collector == ""){
				//未选择采集器全部网元都不可选
				$(nodes).each(function (i, node) {
		       		var childNodes=node.children;
		       		$(childNodes).each(function (i, childnode) {
			       		zTree.setChkDisabled(childnode, true);
			    	}); 
		    	});
				return;
			}
			 $.post('${ctx}/collectset/elementcollect/showTreeByCollector/', {
			        "collectId": collector,
			        "type":$("#temp_field2").val()
			    }, function(data) {
			        var value=eval(data);
			    	//遍历，未被分配的网元可选择
			    	$(nodes).each(function (i, node) {
			       		var childNodes=node.children;
			       		$(childNodes).each(function (i, childnode) {
					       		if(!childnode.checked){
					       			if(childnode.name.indexOf("【")!= -1){
					       				try{zTree.checkNode(childnode, true, false);}catch(e){}
					       				zTree.setChkDisabled(childnode, true);
					       			}else{
					       				zTree.setChkDisabled(childnode, false);
					       			}
					       		}else{
					       			if(childnode.name.indexOf("【")!= -1){
					       				try{zTree.checkNode(childnode, true, false);}catch(e){}
					       				zTree.setChkDisabled(childnode, true);
					       			}else{
					       				try{zTree.checkNode(childnode, false, false);}catch(e){}
					       				zTree.setChkDisabled(childnode, false);
					       			}
					       		}
				    	}); 
			    	}); 
			    	//对于选择的采集器已经配置网元的可重新选择
			    	for(var m in value){
						var node = zTree.getNodeByParam("id", value[m].fid);
						zTree.setChkDisabled(node, false);
			    	}
			    });
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="">${tabName}采集配置</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tCollectordeploy" action="${ctx}/collectset/elementcollect/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group" id="collotordiv">
			<label class="control-label">采集器名称:</label>
			<div class="controls">
				<form:select id="selectCollect" path="collectorname" cssStyle="width:150px" onchange="selectChange()">
                    <form:option value="" label="请选择"/>           
 					<form:options items="${listTCollectordeploy}" itemLabel="collectorname" itemValue="id" htmlEscape="false"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元:</label>
			<div class="controls">
				<div id="netTree" class="ztree" style="margin-top:3px;float:left;"></div>
				<form:hidden path="temp_field1"/><!-- 采集的网元ID -->
				<form:hidden path="temp_field2"/><!-- 采集类型 -->
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="collectset:elementcollect:configtjview"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>