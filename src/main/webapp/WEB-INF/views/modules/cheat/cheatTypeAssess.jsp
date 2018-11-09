<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈类型评估表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/easyui/themes/icon.css" />
	<style type="text/css">
		#table tr td {
			text-overflow:ellipsis;
			-moz-text-overflow:ellipsis;
			overflow:hidden;
			white-space:nowrap;
		}
	</style>
	<script type="text/javascript">
		<%--设置全局变量--%>
		var netid;
		var startTime;
		var endTime;
		var timeGranularity;
		$(document).ready(function() {
			$("#searchForm").validate({
				submitHandler: function(form){
					netid = $("#netid option:selected").val();
					startTime = $("#startTime").val();
					endTime = $("#endTime").val();
					timeGranularity = $("#timeGranularity option:selected").val();
					if (netid==""){
	                	top.$.jBox.tip('请选择查询网元','warning');
	                }else if (startTime==""){
	                    $("#startTime").focus();
	                    top.$.jBox.tip('请选择开始时间','warning');
	                }else if (endTime==""){
	                	$("#endTime").focus();
	                	top.$.jBox.tip('请选择结束时间','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/cheat/cheatTypeAssess/exportExcel"){
	                		form.submit();
	                		$("#searchForm").attr('action',"");
	                	}else{
	                		queryTable()
	                	}
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
		
		function queryTable(){
	        $.post('${ctx}/cheat/cheatTypeAssess/getDynamicColumns', {
	 		       "netid": netid 
	 		 }, function(data) {
	 	    	var cheatChineseList = eval(data);
	 	    	var frozenColumns = [[ 
	 	    	{
	 	            field: 'recordtime',
	                title: '时间',
	                align : 'center',
	                width : 180,
	 	            valign : 'middle',
	 	            formatter:function(value,row,index){
	 	            	return '<span title="'+row.recordtime+'">' + row.recordtime + '</span>';
	 	            }
	 	        }, {
	 	            field : 'proxyip',
	 	            title : '代理服务器',
	 	            align : 'center',
	 	            width : 180,
	 	            valign : 'middle',
	 	            formatter:function(value,row,index){
	 	            	return '<span title="'+row.proxyip+'">' + row.proxyip + '</span>';
	 	            }
	 	        }]];
	 	    	
	 	    	var cols = [[{
		            field : 'deal',
		            title : '操作',
		            align : 'center',
		            width : 150,
		            valign : 'middle',
		            formatter:function(value,row,index){
                        return "<a href='javascript:void(0);' style='cursor:hand' onclick=\"detailInfo('"+row.proxyip+"','"+row.recordtime+"')\">查看欺诈用户</a>";
		          	}
		        }]];
	 	    	for(var i=0; i<cheatChineseList.length; i++) {
	 	    		var colName = cheatChineseList[i].cheatCaseChinese;
	 	    		var col = {
	     				field : colName,
	  		            title : colName,
	  		            align : 'center',
	  		            width : 200,
	  		            valign : 'middle'
	 	    		}
	 	    		cols[0].unshift(col);
	 	    	}
	 	    	   
	 	    	$('#table').datagrid({
	 	    		url : "${ctx}/cheat/cheatTypeAssess/queryList",
	 				width:'100%',
	 				queryParams: { 
	 					'netid' : netid,
	 					'timeGranularity':timeGranularity,
	 					'startTime':startTime,
	 					'endTime':endTime
	 				},
	 				pagination : true,
	 				pageSize : 20,
					pageList : [20, 40 ,80],
					pageNumber : 1,
	                frozenColumns:frozenColumns,
	                columns:cols
	             }); 
	 		});
		}
		
		function detailInfo(proxyip,recordtime){
			top.$.jBox.open('iframe:${ctx}/cheat/cheatTypeAssess/showDetailInfo?netid='+netid+'&proxyip='+proxyip+'&recordtime='+recordtime+
					'&timeGranularity='+timeGranularity,'查看欺诈用户',$(top.document).width()-800,$(top.document).height()-300,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
		}
		
		
		
		
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="cheatTypeAssess" action="" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		    <li>
		        <label>网元</label>
		        <form:select id="netid" path="netid" class="input-xlarge" style="width:180px;" >
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li>
		        <label>时间粒度</label>
		        <form:select id="timeGranularity" path="timeGranularity" class="input-medium" style="width:160px;">
 				   <form:options items="${fns:getDictList('biz_cheat_time_granularity')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
			</li>
			<li>	
				<label>开始时间</label>
				<form:input id="startTime" path="startTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li> 
			<li>
				<label>结束时间</label>
				<form:input id="endTime" path="endTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="queryBtn" class="btn-new btn-search" value="查询" type="submit"/></li>
			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/cheat/cheatTypeAssess/exportExcel';"/></li>
		</ul>
	</form:form>
	<table id="table"></table>
</body>
</html>