<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈用户评估表</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
	$(document).ready(function() {
		$("#startTimeLi").hide();
		$("#endTimeLi").hide();
		
		/**折叠组件三角图标切换**/
		triangleIconChange();
		
		$("#pastScope").change(function(){
			//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
			$("#startTimeLi").hide();
			$("#endTimeLi").hide();
			if(this.value==""){
				$("#beginDate").val("");
				$("#endDate").val("");
				return false;
			}
			if(this.value=="0"){
				$("#beginDate").val("");
				$("#endDate").val("");
				$("#startTimeLi").show();
				$("#endTimeLi").show();
				return false;
			}
			$("#startTime").val(getTacStartTime(this.value));
			$("#endTime").val(getTacEndTime(this.value));
		});
		
		$("#searchForm").validate({
			submitHandler: function(form){
				if ($("#netid option:selected").val()==""){
                	top.$.jBox.tip('请选择查询网元','warning');
				}else if ($("#startTime").val()==""){
                    $("#startTime").focus();
                    top.$.jBox.tip('请选择时间段','warning');
                }else if ($("#endTime").val()==""){
                	$("#endTime").focus();
                	top.$.jBox.tip('请选择时间段','warning');
                }else{//表单提交
                	/* if($("#searchForm").attr('action')=="/epc/a/performance/tTacSuccessRate/exportExcel"){
                		form.submit();
                		$("#searchForm").attr('action',"");
                	}else{ */
                		queryTable();
                	//}
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
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/cheat/cheatUserAssess/queryList',
		        method:'POST',
		        dataType:'json',
		        cache:false,
		        contentType : "application/x-www-form-urlencoded",
		        striped: true,                      //是否显示行间隔色
		        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		        pagination:true,
		        minimumCountColumns:2,
		        pageNumber:1,                       //初始化加载第一页，默认第一页
		        pageSize: 10,                 //每页的记录行数（*）
		        pageList: [10, 25, 50, 100],   //可供选择的每页的行数（*）
		        queryParams:  function(params) {
		        	 netId = $("#netId option:selected").val();
					 startTime = $("#startTime").val();
					 endTime = $("#endTime").val();
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   sortName:this.sortName,
                       sortOrder:this.sortOrder,
		        	   netId: netId,
				       startTime:startTime,
				       endTime:endTime
		        	  };
		        },
		        columns: [ 
		         {
		            field : 'servedIMSI',
		            title : '欺诈用户IMSI',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        },
		        {
		            field: 'freetotal',
                    title: '免费流量(Mb)',
                    align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'total',
		            title : '总流量(Mb)',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'percent',
		            title : '免费流量占比',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'cheatCaseChinese',
		            title : '计费欺诈类型',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'field1',
		            title : '计费欺诈详细信息',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<a href="javascript:void(0);" onclick="analysis(\''+row.servedIMSI+'\',\''+row.startTime+'\',\''+row.endTime+'\',\''+row.cheatCase+'\',\''+row.netId+'\')"> 详细信息 </a>';
		            }
		        }, {
		            field : 'field1',
		            title : '欺诈用户流量分析',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	return '<a href="javascript:void(0);" onclick="ratingGroupanalysis(\''+row.servedIMSI+'\',\''+row.startTime+'\',\''+row.endTime+'\',\''+row.netId+'\')"> 流量分析 </a>';
		            }
		        }]
		    });
		}
		
		function analysis(servedIMSI,startTime,endTime,cheatCase,netId) {
			//alert(servedIMSI +"," + startTime + "," + endTime + "," + cheatCase);
			openNewWindow("${ctx}/cheat/cheatUserAssess/showCheatlogPage?servedIMSI=" + servedIMSI + "&startTime=" + startTime + "&endTime=" + endTime + "&cheatCase=" + cheatCase+ "&netId=" + netId,1200,800);
		}
		
		function ratingGroupanalysis(servedIMSI,startTime,endTime,netId){
	    	openNewWindow('${ctx}/cheat/cheatUserAssess/showPieChart?netId='+netId+'&servedIMSI='+servedIMSI+"&startTime="+startTime+"&endTime="+endTime,800,600);
	    }
	</script>
</head>
<body>
	<form:form id="searchForm" name="searchForm" modelAttribute="tCheatType" class="breadcrumb form-search" action="">
		<ul class="ul-form">
		    <li>
		        <label>网元</label>
		        <form:select path="netId" class="input-xlarge" style="width:180px;" id="netId">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li><label>时间段</label>
		       <form:select id="pastScope" path="temp_field1" class="input-medium" style="width:160px;">
                   <form:option value="" label="请选择"/>           
 				   <form:options items="${fns:getDictList('biz_past_time_tac')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
               </form:select>
			</li>
			<li id="startTimeLi">	
				<label>开始时间</label>
				<form:input id="startTime" path="temp_field2" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li> 
			<li id="endTimeLi">
				<label>结束时间</label>
				<form:input id="endTime" path="temp_field3" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="queryBtn" class="btn-new btn-search" value="查询" type="submit"/></li>
<%-- 			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/performance/tTacSuccessRate/exportExcel';"/></li> --%>
			</ul>
	</form:form>
	
		   		<table id="table">
		        </table>

</body>
</html>