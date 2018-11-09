<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>智能巡检管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#netType").change(function(){
				$("#netidepc").val($(this).find("option:selected").val());
				
				var netType=$("#netType option:selected").val();
				$.post('${ctx}/autocheck/tAutoCheckLog/queryCheckItems', {
				       "netType": netType
				    }, function(data) {
					    	var itemList = eval(data);
					    	$("#checkItem option:not(:first)").remove();  
					    	$(".select2-container:eq(2) .select2-chosen:eq(0)").html("请选择");
					    	//$("#moduleId").html("<option value=''>请选择</option>")
					    	$.each(itemList, function(index, o) { 
					    		var html = "<option value='"+o.id+"'>"+o.itemName+"</option>";
							    $("#checkItem").append(html);
							}); 
					    	$("#checkItem option:first").prop("selected", 'selected'); 
					 });
				
	   	     });
			
			$("#pastScope").val(24);
			var label = '${fns:getDictLabel(24,'biz_past_time_scope','')}';
			$(".select2-container .select2-chosen:eq(3)").html(label);
			//默认查询全部网元过去24小时的巡检情况
			$("#beginDate").val(getWholePointTime(-1));
			$("#endDate").val(getWholePointTime(0));
			/* $("#checkResult").val('Nok');
			var label2 = '${fns:getDictLabel('Nok','biz_autocheck_result_type','')}';
			$(".select2-container .select2-chosen:eq(2)").html(label2); */
			//默认查询全部网元过去24小时的巡检情况
			sendPost();
			
			$("#pastScope").change(function(){
				//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
				$("#timeScope").hide();
				if(this.value==""){
					$("#beginDate").val("");
					$("#endDate").val("");
					return false;
				}
				if(this.value=="0"){
					$("#beginDate").val("");
					$("#endDate").val("");
					$("#timeScope").show();
					return false;
				}
				$("#beginDate").val(getWholePointTime(-1 * (this.value/24)));
				$("#endDate").val(getWholePointTime(0));
				});
			
			$("#searchForm").validate({
				submitHandler: function(form){
					if ($("#beginDate").val()==""){
	                    $("#beginDate").focus();
	                    top.$.jBox.tip('请选择开始时间','warning');
	                }else if ($("#endDate").val()==""){
	                	$("#endDate").focus();
	                	top.$.jBox.tip('请选择结束时间','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/performance/tTacSuccessRate/exportExcel"){
	                		form.submit();
	                		$("#searchForm").attr('action',"");
	                	}else{
	                		sendPost();
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
		
		//发送post请求
		function sendPost(){
			
			//上次巡检时间赋值
			$.get('${ctx}/autocheck/tAutoCheckLog/queryMaxCheckTime?t='+new Date().getTime(), function(data) {
			    $("#queryTimeScope").html("上次巡检时间："+data); 
		    });
			  
			  //表格初始化
			  $("#contentTable").bootstrapTable('destroy'); 
			  $('#contentTable').bootstrapTable({
					url:'${ctx}/autocheck/tAutoCheckLog/queryList',
			        method:'POST',
			        dataType:'json',
			        cache: false,
			        contentType : "application/x-www-form-urlencoded",
			        striped: true,                      //是否显示行间隔色
			        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
			        pagination:true,
			        minimumCountColumns:2,
			        pageNumber:1,                       //初始化加载第一页，默认第一页
			        pageSize: 10,                 //每页的记录行数（*）
			        pageList: [10, 25, 50, 100],   //可供选择的每页的行数（*）
			        queryParams:  function(params) {
			        	return {
				        	   offset: params.offset,
				        	   limit: params.limit,
				        	   sortName:this.sortName,
		                       sortOrder:this.sortOrder,
		                       netType: $("#netType").val(),
				        	   netid: $("#netidId").val(),
				        	   itemName: $("#checkItem").val(),
				        	   checkResult: $("#checkResult").val(),
				        	   beginDate:"2018-04-24 00:00:00",
						       endDate:"2018-04-24 23:00:00"
				        	  };
			        },
			        formatLoadingMessage: function () {
		        	 return '';
		        	 },
			        onLoadSuccess: function (data) {
			        	 
			        },
			        onClickRow: function (row) {
			        	return false;
			        },
			        columns: [
			        {
			            field: 'netType',
			            title: '网元类型',
			            align : 'center',
			            valign : 'middle',
		            	sortable : true,
		            	formatter:function(value,row,index){
		            		if(value=='1'){
		            			return 'MME';
		            		}else if(value=='2'){
		            			return 'SAEGW'
		            		}else if(value=='3'){
		            			return 'PCRF'
		            		}
			          	}
			        }, {
			            field : 'netName',
			            title : '网元名称',
			            align : 'center',
			            valign : 'middle',
		            	sortable : true
			        }, {
			            field : 'itemName',
			            title : '检查项目',
			            align : 'center',
			            valign : 'middle',
		            	sortable : true
			        }, {
			            field : 'checkResult',
			            title : '巡检结果',
			            align : 'center',
			            valign : 'middle',
			            sortable : true,
		            	formatter:function(value,row,index){
		            		if(value=='ok'){
		            			return '正常';
		            		}else if(value=='Nok'){
		            			return '异常'
		            		}else{
		            			return '出错'
		            		}
			          	}
			        }, {
			            field : 'checkTime',
			            title : '巡检时间',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }, {
			            field : 'checkLog',
			            title : '操作',
			            align : 'center',
			            width : 200,
			            valign : 'middle',
		          	formatter:function(value,row,index){
		          		return '<a href="#" onclick="showCheckLog(' + row.id + ');" >巡检日志查看</a>&nbsp;&nbsp;' +
		          		'<a href="${ctx}/autocheck/tAutoCheckLog/downloadCheckLog?id=' + row.id + '" >巡检日志下载</a>';
		          	}
		      }]
		    });
			  
		   	
		}
		
		function showCheckLog(id) {
			top.$.jBox.open("iframe:${ctx}/autocheck/tAutoCheckLog/showCheckLog?id="+id, "查看巡检日志",1000,500,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/autocheck/tAutoCheckLog/">网元自动智能巡检</a></li>
	</ul>
	<sys:message content="${message}"/>
	<div class="navbar">
      <div class="navbar-inner">
        <span class="icon-f" style="font-size: 45px; float:left;margin-left:-10px;"></span><a style="margin-left:20px;" class="brand" href="${ctx}/autocheck/tAutoCheckLog/">网元自动智能巡检</a>
        <span id="queryTimeScope" style="float:right;padding:15px;"></span>
      </div>
    </div>
	<form:form id="searchForm" modelAttribute="tAutoCheckLog" action="" method="post" class="breadcrumb form-search">
		
		<div class="form-inline">
		   <label style="width:60px;text-align:right;">网元类型:</label>
			<form:select id="netType" path="netType" style="width:150px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
			<label style="width:60px;text-align:right;">网元：</label>
                <sys:treeselect id="netid" name="netid" cssStyle="width:138px;" value="${netid}" labelName="netid" labelValue="${netid}"
					title="网元名称" url="/performance/multipleIndex/treeData" notAllowSelectRoot="true" notAllowSelectParent="true" checked="true" />
			
			<label>检查项目:</label>
			<form:select id="checkItem" path="itemName" style="width:150px;">
              <form:option value="" label="请选择"/>           
         	</form:select>
         	<label>检查结果:</label>
			<form:select id="checkResult" path="checkResult" style="width:150px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_autocheck_result_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
         	<div class="pull-right" style="margin-top:5px;">
         	<input id="btnQuery" class="btn-new btn-search" type="submit" value="查询"/>
			<!-- <input id="btnExport" class="btn-new btn-save"  type="button" value="导出"/> -->
			</div>
		</div>
		<div style="height:5px;"></div>
		<div class="form-inline">
		<label style="width:60px;text-align:right;">时间段:</label>
			<form:select id="pastScope" path="pastScope" style="width:150px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_past_time_scope')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
         	
		<span id="timeScope" class="hide" style="margin-top:2px;">
			<label style="width:60px;text-align:right;margin-left:10px;">开始时间:</label>
			<input id="beginDate" name="beginDate"  type="text" maxlength="20" class="input-medium Wdate" style="width:158px;"
				value="<fmt:formatDate value="${userCdrQueryEntity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			<label style="text-align:right;margin-left:36px;">结束时间:</label>
			<input id="endDate" name="endDate" type="text" maxlength="20" class="input-medium Wdate" style="width:158px;"
				value="<fmt:formatDate value="${userCdrQueryEntity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
		 </span>
		</div>
	</form:form>
	
	<table id="contentTable"></table>
	
</body>
</html>