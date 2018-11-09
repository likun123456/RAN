<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抓包历史记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			if($("#pastScope").val()=="0"){
				$("#timeScope").show();
			}else{
				$("#timeScope").hide();
			}
			
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
					if($("#netid").val()==""){
                		top.$.jBox.tip('请选择网元','warning');
                        return false;
					}
                	if($("#pastScope").val()==""){
                		top.$.jBox.tip('请选择时间段','warning');
                        return false;
					}
                	if ($("#beginDate").val()==""){
                        $("#beginDate").focus();
                        top.$.jBox.tip('请选择开始时间','warning');
                        return false;
                    }else if ($("#endDate").val()==""){
                    	$("#endDate").focus();
                    	top.$.jBox.tip('请选择结束时间','warning');
                    	return false;
                    }else if ($("#endDate").val() < $("#beginDate").val()){
                    	top.$.jBox.tip('开始时间不能小于结束时间','warning');
                    	return false;
                    }else{
                        //表单提交
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
		 	
		 	$("#btnExport").click(function(){
		 		var length = $("#contentTable tbody").children().length;
				if(length==0){
					top.$.jBox.tip('没有可以导出的数据','warning');
                    return false;
				}
				location = '${ctx}/cheat/cheatHistory/export?netid='+$("#netid").val()+'&beginDate='+$("#beginDate").val()+ 
                '&endDate='+$("#endDate").val()+'&cheatStatus='+$("#verifyStatus").val();
		   });
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		
		function detailInfo(imsi,recordtime){
			top.$.jBox.open('iframe:${ctx}/cheat/cheatHistory/showCheatlog?netid='+$("#netid").val()+'&imsi='+imsi+'&recordtime='+recordtime,
				'流量欺诈分析报告',$(top.document).width()-400,$(top.document).height()-130,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
		}
		
		function showPie(imsi,recordtime,cheatCase){
			top.$.jBox.open('iframe:${ctx}/cheat/cheatHistory/showPie?netid='+$("#netid").val()+'&imsi='+imsi+'&beginDate='+$("#beginDate").val()+ 
                    '&endDate='+$("#endDate").val()+'&recordtime='+recordtime+'&cheatCase='+cheatCase,
				'欺诈用户流量分析',$(top.document).width()-400,$(top.document).height()-130,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
		}
		
		function downloadCap(imsi,recordtime,cheatCase){
			location='${ctx}/cheat/cheatHistory/downloadCap?netid='+$("#netid").val()+'&imsi='+imsi+'&recordTime='+recordtime+'&cheatCase='+cheatCase;
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cheat/cheatHistory/show">抓包历史记录列表</a></li>
		<shiro:hasPermission name="cheat:cheatHistory:edit"><li><a href="${ctx}/cheat/cheatHistory/form">抓包历史记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cheatHistory" action="${ctx}/cheat/cheatHistory/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
		        <label>网 元 </label>
		        <form:select path="netid" style="width:180px;" id="netid">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			
			<li>
				<label>核实状态 </label>
				<form:select id="verifyStatus" path="status" style="width:180px;">
					<form:option value="" label="请选择"/>           
					<form:options items="${fns:getDictList('biz_cheat_verify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn-new btn-search" type="submit" value="查询"/>
			<input id="btnExport" class="btn-new btn-save" type="button" value="导出"/>
			</li>
			<li class="clearfix"></li>
		 </ul>
		 <ul class="ul-form">
			<li>
				<label style="width:80px;text-align:right;">时间段 </label>
				<form:select id="pastScope" path="temp_field1" style="width:180px;">
		            <form:option value="" label="请选择"/>           
				    <form:options items="${fns:getDictList('biz_past_time_scope')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		       	</form:select>
			       	
				<span id="timeScope" style="margin-top:2px;">
					<label style="width:75px;text-align:right;margin-left:10px;">开始时间 </label>
					
					<form:input id="beginDate" path="beginDate" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
					
					<label style="text-align:right;margin-left:36px;">结束时间 </label>
					<form:input id="endDate" path="endDate" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			    </span>
			</li>
			
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期时间</th>
				<th>欺诈用户IMSI</th>
				<th>免费流量(MB)</th>
				<th>总流量(MB)</th>
				<th>免费流量占比</th>
				<th>计费欺诈核实状态</th>
				<th>计费欺诈类型</th>
				<th>计费欺诈备注</th>
				<th>欺诈用户流量分析</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cheatHistory">
			<tr>
				<td>${fn:substring(cheatHistory.recordTime, 0, 19)}</td>
				<td>${cheatHistory.servedImsi}</td>
				<td><fmt:formatNumber type="number" value="${cheatHistory.freeTotal/(1024*1024)}" pattern="0.00" maxFractionDigits="2"/></td>
				<td><fmt:formatNumber type="number" value="${cheatHistory.total/(1024*1024)}" pattern="0.00" maxFractionDigits="2"/></td>
				<td><fmt:formatNumber type="number" value="${cheatHistory.percent * 100}" pattern="0.00" maxFractionDigits="2"/>%</td>
				<td>${fns:getDictLabel(cheatHistory.status, 'biz_cheat_verify_status', '')}</td>
				<td>${cheatHistory.name}</td>
				
				<c:if test="${cheatHistory.cheatCase ne '' and cheatHistory.cheatCase ne 'UNKNOWN' }">
					<td><a href='javascript:void(0);' onclick="detailInfo('${cheatHistory.servedImsi}','${fn:substring(cheatHistory.recordTime, 0, 19)}')">详细信息</a></td>
				</c:if>
				<c:if test="${cheatHistory.cheatCase ne '' and cheatHistory.cheatCase eq 'UNKNOWN' }">
					<td><a href='javascript:void(0);' onclick="downloadCap('${cheatHistory.servedImsi}','${fn:substring(cheatHistory.recordTime, 0, 19)}','${cheatHistory.cheatCase}')">抓包下载</a></td>
				</c:if>
				<c:if test="${cheatHistory.cheatCase eq ''}">
					<td></td>
				</c:if>
				<td><a href='javascript:void(0);' onclick="showPie('${cheatHistory.servedImsi}','${fn:substring(cheatHistory.recordTime, 0, 19)}','${cheatHistory.cheatCase}')">流量分析</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>