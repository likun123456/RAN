<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>定时导出设置</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
		var tree;
		$(document).ready(function(){
			$("#inputForm").validate({
				submitHandler: function(form){
					if(tree!=undefined){
						var ids = [], nodes = tree.getCheckedNodes(true);
						for(var i=0; i<nodes.length; i++) {
							ids.push(nodes[i].id);
						}
						$("#temp_field3").val(ids);//参数标识
					}
					if ($("#temp_field2 option:selected").val()==""){
	                	top.$.jBox.tip('请选择导出时间间隔','warning');
					}else if ($("#netType option:selected").val()==""){
	                    top.$.jBox.tip('请选择网元类型','warning');
	                }else if ($("#netidId").val()==""){
	                    $("#netid").focus();
	                    top.$.jBox.tip('请选择网元','warning');
	                }else if ($("#temp_field3").val==""){
	                	top.$.jBox.tip('请选择参数标识','warning');
	                }else{//表单提交
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
			
		    $("#netType").change(function(){
				$("#netidepc").val($(this).find("option:selected").val());
				$.post('${ctx}/paramconfig/paramExport/findSelectNetElement', {
			        "netType": $(this).find("option:selected").val()
			    }, function(dataQuery) {
			    	var length = dataQuery.length;
			    	var name;
			    	var id;
					for(var k=0;k<length;k++){
						if(k==0){
						name=dataQuery[k].fname;
						id=dataQuery[k].id;
						}else{
							name=name+","+dataQuery[k].fname;
							id=id+","+dataQuery[k].id;
						}
					}
					//查询历史并赋值
					$("#netidName").val(name);
					$("#netidId").val(id);
					queryTree();
			    });
		    });
			
		});
		function queryTree(){
			$.post('${ctx}/paramconfig/paramExport/queryRemarkTree', {
		        "netType": $("#netidepc").val()
		    }, function(dataQuery) {
		    	var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
						data:{simpleData:{enable:true}},callback:{
							beforeClick:function(id, node){
								tree.checkNode(node, !node.checked, true, true);
								return false;
							}
							}
						};
				// 用户-菜单
				var zNodes=eval(dataQuery);
				// 初始化树结构
				tree = $.fn.zTree.init($("#netTree"), setting, zNodes);
				
				// 不选择父节点
				tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
				// 默认展开全部节点
				tree.expandAll(false);
		    });
			}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="">定时导出设置</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="paramData" action="${ctx}/paramconfig/paramExport/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">导出时间间隔</label>
			<div class="controls">
		       <form:select path="temp_field2" class="input-xlarge" style="width:150px;" id="temp_field2">
		            <form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('biz_param_collect_config')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元类型</label>
			<div class="controls">
		       <form:select path="temp_field1" class="input-xlarge" style="width:150px;" id="netType">
		            <form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网元</label>
			<div class="controls">
				 <sys:treeselect id="netid" name="netid" cssStyle="width:138px;" value="${netid}" labelName="netid" labelValue="${netid}"
					title="网元名称" url="/paramconfig/paramExport/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参数标识</label>
			<div class="controls">
				<div id="netTree" class="ztree" style="margin-top:3px;float:left;"></div>
				<form:hidden path="temp_field3"/><!-- 参数标识 -->
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="paramconfig:paramExport:view"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>