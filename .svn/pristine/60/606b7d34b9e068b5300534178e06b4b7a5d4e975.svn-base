<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单网元参数设置管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript" src="${ctxStatic}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/easyui/themes/icon.css" />
	
	<style type="text/css">
		table tr td {
			text-overflow:ellipsis;
			-moz-text-overflow:ellipsis;
			overflow:hidden;
			white-space:nowrap;
		}
	</style>
	<script type="text/javascript">
	$(document).ready(function() {
		
		changeTableHeight();
		window.onresize=function(){
			changeTableHeight();
		}
		
		$("#netType").change(function(){
			$("#netidepc").val($(this).find("option:selected").val());
			$("#formulaTypeepc").val($(this).find("option:selected").text());
	   	   });
		/*  $("#netType").change(function(){
			 
			 $("#newNetelementlist").empty(); 
			 $(".select2-search-choice").remove();
			 $("#paramTypeList option:not(:first)").remove();
	  		 $("#date").val("");
	  		 var netType = $(this).val();
	  		 if(netType!=""){
				 $.post('${ctx}/paramconfig/netParamCompare/getNewNetelementsByType', {
				       "netType": netType
				 }, function(data) {
					
			    	var data = eval(data);
			    	var tempOption = "";
			    	if(data.length==0){
  					   alert("目前没有此类型的网元");
  					   $("#netSelect").html("");
  					   return;
  				   	}
			    	var obj = document.getElementById("newNetelementlist");
				    for(var i=0;i<data.length;i++){
				    	obj.options[i] = new Option(data[i].fname,data[i].id);
				    }
				});
	  		 }
		
		 }); */
		 
		 $("#date").click(function(){
			 WdatePicker({
				 dateFmt:'yyyy-MM-dd',
				 isShowClear:true,
				 onpicked: function () {
					 $("#paramTypeList option:not(:first)").remove();
					 var netid = $("#netidId").val();
					 var date = $("#date").val();
					 var netType = $("#netType").val();
					  if(netid !="" && date !=""){
						 $.post('${ctx}/paramconfig/netParamCompare/getParamTypeByNetId', {
						       "netid": netid.toString(),"date": date,"nettype": netType
						 }, function(data) {
					    	var netParamList = eval(data);
					    	//$(".select2-chosen").eq(1).html("请选择");
					    	$("#paramTypeList").html("<option value=''>请选择</option>")
					    	$.each(netParamList, function(index, o) { 
					    		var html = "<option value='"+o+"'>"+o+"</option>";
							    $("#paramTypeList").append(html);
							}); 
						});
					 } 
				 }
			 });
		 });
		 
		 
		 $("#searchForm").validate({
				submitHandler: function(form){
					var netType = $("#netType").val();
					var netid = $("#netidId").val();
					var date = $("#date").val();
					if (netType ==""){
	                	top.$.jBox.tip('请选择网元类型','warning');
					}else if (netid =="" || netid==null){
	                    top.$.jBox.tip('请选择网元','warning');
	                }else if (date ==""){
	                    top.$.jBox.tip('日期不允许为空','warning');
	                }else{
	                	queryTable();
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
	});
	
	function changeTableHeight(){
	    var table= $("#table"); 
	    var height = document.documentElement.clientHeight;
	    if(height < 500){
	    	table.height(document.documentElement.clientHeight * 0.67);
	    }else if(height >=500){
	    	table.height(document.documentElement.clientHeight * 0.86);
	    }
	}
	
	function queryTable(){
		
		var netType = $("#netType").val();
		var netid = $("#netidId").val();
		var date = $("#date").val();
		var paramtype = $("#paramTypeList option:selected").val();
		var keyword = $("#keyword").val();
		var removeFlag = $('input:radio[name="removeFlag"]:checked').val(); 
		
		$.post('${ctx}/paramconfig/netParamCompare/getDynamicColumns', {
		       "netType": netType ,"netid": netid 
		 }, function(data) {
	    	var netParamList = eval(data);
	    	
	    	var frozenColumns = [[ 
	    	{
	            field: 'paramid',
                title: '参数标识',
                align : 'center',
                width : 200,
	            valign : 'middle',
	            formatter:function(value,row,index){
	            	return '<span title="'+row.paramid+'">' + row.paramid + '</span>';
	            }
	        }, {
	            field : 'cnname',
	            title : '参数中文名称',
	            align : 'center',
	            width : 200,
	            valign : 'middle',
	            formatter:function(value,row,index){
	            	return '<span title="'+row.cnname+'">' + row.cnname + '</span>';
	            }
	        }]];
	    	
	    	var cols = [[
		       {
		            field : 'name',
		            title : '参数名称',
		            align : 'center',
		            width : 260,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.name+'">' + row.name + '</span>';
		            }
		        }, {
		            field : 'version',
		            title : '版本',
		            align : 'center',
		            width : 100,
		            valign : 'middle'
		        }, {
		            field : 'type',
		            title : '参数类型',
		            align : 'center',
		            width : 150,
		            valign : 'middle'
		        },  {
		            field : 'description',
		            title : '参数说明',
		            align : 'center',
		            width : 300,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.description+'">' + row.description + '</span>';
		            }
		        }, {
		            field : 'endescription',
		            title : '参数英文说明',
		            align : 'center',
		            width : 300,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.endescription+'">' + row.endescription + '</span>';
		            }
		        }, {
		            field : 'applyrange',
		            title : '参数适用范围',
		            align : 'center',
		            width : 280,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.applyrange+'">' + row.applyrange + '</span>';
		            }
		        }, {
		            field : 'valuerange',
		            title : '参数取值范围',
		            align : 'center',
		            width : 300,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.valuerange+'">' + row.valuerange + '</span>';
		            }
		        }, {
		            field : 'memo',
		            title : '备注',
		            align : 'center',
		            width : 200,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.memo+'">' + row.memo + '</span>';
		            }
		        }, {
		            field : 'defaultvalue',
		            title : '参数默认取值',
		            align : 'center',
		            width : 200,
		            valign : 'middle'
		        }, {
		            field : 'suggestvalue',
		            title : '参数建议取值',
		            align : 'center',
		            width : 300,
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<span title="'+row.suggestvalue+'">' + row.suggestvalue + '</span>';
		            }
		        }]];
	    	for(var i=0; i<netParamList.length; i++) {
	    		var colName = netParamList[i];
	    		var col = {
    				field : colName,
 		            title : colName+'_取值',
 		            align : 'center',
 		            width : 300,
 		            valign : 'middle'
	    		}
	    		cols[0].push(col);
	    	}
	    	
	    	
	    	
	    	$('#table').datagrid({
	    		url : "${ctx}/paramconfig/netParamCompare/queryList",
				//height:300,
				width:'100%',
				queryParams: { 
					'netType' : netType,
					'netid' : netid,
					'date' : date,
					'paramtype' : paramtype,
					'keyword' :keyword,
					'removeFlag' : removeFlag
				},
				pagination : true,
				pageSize : 100,
				pageList : [50, 100],
				pageNumber : 1,
                frozenColumns:frozenColumns,
                columns:cols
            });
	    	
		});
		
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/paramconfig/netParamCompare/">单网元参数设置</a></li>
	</ul>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="paramData"  action="" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>网元类型：</label>
				<!-- <select id="netType" class="input-xlarge" style="width:150px;" >
					<option value="">--请选择--</option>
					<option value="1">MME</option>
					<option value="2">EPG</option>
					<option value="3">PCRF</option>
				</select> -->
				 <form:select path="type" class="input-xlarge" style="width:150px;" id="netType">
		            <form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				
			</li>
			<li id="netSelect"><label>网元：</label>
				<!-- <select id="newNetelementlist" style="min-width: 150px; height: 32px" multiple="multiple">
                </select> -->
                
                <sys:treeselect id="netid" name="netid" cssStyle="width:138px;" value="${netid}" labelName="netid" labelValue="${netid}"
					title="网元名称" url="/performance/multipleIndex/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
                
			</li>
			<li><label>日期：</label>
			<input id="date" type="text" readonly="readonly" maxlength="20" class="txt  Wdate" style="width:160px;"/>
			</li>
			<li><label>参数类型：</label>
				<select id="paramTypeList" style="min-width: 150px;">
					<option value="">--请选择--</option>
				</select>
			</li>
			<li><label>关键字：</label>
			<form:input path="keyword" id="keyword" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li>
				<input type="radio" checked="checked" name="removeFlag" value="no"/>全部对比
					<input type="radio"  name="removeFlag" value="yes"/>不一致对比 
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="table"></table>
</body>
</html>