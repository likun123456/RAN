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
		#table tr td {
			text-overflow:ellipsis;
			-moz-text-overflow:ellipsis;
			overflow:hidden;
			white-space:nowrap;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
			changeTableHeight();
			var isRedirect = '${isRedirect}';
			if('true'==isRedirect) {
				var netId = '${netId}';
				var dateTime = '${dateTime}';
				$("#newNetelementlist").val(netId);
				$("#date").val(dateTime);
				var netMap = JSON.parse('${fns:toJson(netMap)}');
				var label = netMap[netId];
				$(".select2-container .select2-chosen:eq(0)").html(label);
				getParamType(netId,dateTime);
				queryTable();
			}
			
			$('#table tbody').on('click','tr',function(){
				//alert(111);
				
			});
			
			
			window.onresize=function(){
				changeTableHeight();
			}
			
			$("#date").click(function(){
				 WdatePicker({
					 dateFmt:'yyyy-MM-dd',
					 isShowClear:true,
					 onpicked: function () {
						 $("#paramTypeList option:not(:first)").remove();  
						 var netid = $('#newNetelementlist').find("option:selected").val();
						 var date = $("#date").val();
						 if(netid !="" && date !=""){
							 getParamType(netid,date);
						 }
					 }
				 });
			 });
			 
			 $("#searchForm").validate({
				submitHandler: function(form){
					var netid = $('#newNetelementlist').find("option:selected").val();
					var date = $("#date").val();
					if (netid ==""){
	                	top.$.jBox.tip('请选择要查询网元','warning');
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
		
		function getParamType(netid,dateTime){
			$.post('${ctx}/paramconfig/netParamModify/queryParamTypeByNetId', {
			       "netid": netid,"date": dateTime
			 }, function(data) {
		    	var netParamList = eval(data);
		    	//$(".select2-chosen").eq(1).html("请选择");
		    	$("#paramTypeList").html("<option value=''>请选择</option>")
		    	$.each(netParamList, function(index, o) { 
		    		var html = "<option value='"+o+"'>"+o+"</option>";
				    $("#paramTypeList").append(html);
				}); 
		    	$("#paramTypeList option:first").prop("selected", 'selected');
			});
		}
		
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
			var netId = $("#newNetelementlist option:selected").val();
       	 	var date = $("#date").val();
       	 	var paramtype = $("#paramTypeList option:selected").val();
			var keyword = $("#keyword").val();
			
			$('#table').datagrid({
	    		url : "${ctx}/paramconfig/netParamModify/queryList",
				width:'100%',
				queryParams: { 
					'netId': netId,
		        	'date': date,
		        	'paramtype': paramtype,
		        	'keyword': keyword
				},
				pagination : true,
				pageSize : 100,
				pageList : [50, 100],
				pageNumber : 1,
                frozenColumns:[[
                {
    		        field: 'paramid',
    			    title: '参数标识',
    			    align : 'center',
    			    width : 300,
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
                }
                ]],
                columns:[[
                	{
    		            field : 'name',
    		            title : '参数名称',
    		            align : 'center',
    		            width : 300,
    		            valign : 'middle'
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
    		            width : 200,
    		            valign : 'middle',
    	            	formatter:function(value,row,index){
    		            	return '<span title="'+row.endescription+'">' + row.endescription + '</span>';
    		            }
    		        }, {
    		            field : 'applyrange',
    		            title : '参数适用范围',
    		            align : 'center',
    		            width : 300,
    		            valign : 'middle'
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
    		            width : 200,
    		            valign : 'middle'
    		        }, {
    		            field : 'paramvalue',
    		            title : '现网取值',
    		            align : 'center',
    		            width : 400,
    		            valign : 'middle',
    	            	formatter:function(value,row,index){
    		            	return '<span title="'+row.paramvalue+'">' + row.paramvalue + '</span>';
    		            }
    		        }, {
    		            field : 'viewcmd',
    		            title : '提取方法',
    		            align : 'center',
    		            width : 290,
    		            valign : 'middle'
    		        }, {
    		            field : 'modcmd',
    		            title : '修改方法',
    		            align : 'center',
    		            width : 290,
    		            valign : 'middle'
    		        },{
    		            field : 'dummyField',
    		            title : '修改参数',
    		            align : 'center',
    		            valign : 'middle',
    		            width : 100,
    		            formatter:function(value,row,index){
    		            	var t = row.modcmd;
    		            	if(typeof(t) != "undefined") {
    		            		if(t.indexOf("*") > 0) {
    		            			t = t.substr(0,t.length-1);
    		            		}

    		            		return '<a href="javascript:void(0);" onclick="updateParam(\''+row.netid+'\',\''+t+'\',\''+row.valuerange+'\')"> 修改参数 </a>';
    		            	}
    		            }
    		        }
                	
                ]]
            });
		}
		
		function updateParam(netid,modcmd,valuerange){
			top.$.jBox.open("iframe:${ctx}/paramconfig/netParamModify/form?netid=" + netid + "&modcmd=" + modcmd + "&valuerange=" + valuerange, "网元参数修改",650,280,{
				buttons:{"保存":"ok", "关闭":true}, bottomText:"",submit:function(v, h, f){
					//var cmdvalue = h.find("iframe")[0].contentWindow.cmdvalue;
					var doc = h.find("iframe")[0].contentDocument;
					var netid = doc.getElementById("netid").value;
					var modcmd = doc.getElementById("modcmd").value;
					var valuerange = doc.getElementById("valuerange").value;
					var cmdvalue = doc.getElementById("cmdvalue").value;
					var executetype = doc.getElementById("executetype").value;
					var datetime = doc.getElementById("datetime").value;
					
					if (v=="ok"){
						if(cmdvalue == ""){
							top.$.jBox.tip('请输入参数值','warning');
							document.getElementById("cmdvalue").focus();
							return false;
						}
						if(valuerange.indexOf(",")>=0 && valuerange.indexOf("-")==-1 && valuerange.indexOf(":")==-1){
							var rightvalue = valuerange.split(",");
							if($.inArray(cmdvalue,rightvalue)==-1){
								top.$.jBox.tip("输入的参数值不在取值范围内，取值范围是："+valuerange,'warning');
								return false;
							}
						}
						if(valuerange.indexOf("-")>=0 && valuerange.indexOf(",")==-1 && valuerange.indexOf(":")==-1){
							var rightvalue = valuerange.split("-");
							if(rightvalue.length==2 && (parseInt($.trim(rightvalue[0]))>cmdvalue || cmdvalue>parseInt($.trim(rightvalue[1])))){
								top.$.jBox.tip("输入的参数值不在取值范围内，取值范围是："+valuerange,'warning');
								return false;
							}	
						}	
						
						if(executetype == "def"){
							top.$.jBox.tip('请选择执行方式','warning');
							document.getElementById("executetype").focus();
							return false;
						}
						if(executetype == "task" && datetime==""){
							top.$.jBox.tip('请选择执行时间','warning');
							document.getElementById("datetime").focus();
							return false;
						}
						if(executetype == "task" && new Date(datetime.replace("-", "/").replace("-", "/")).getTime() < new Date().getTime()){
							top.$.jBox.tip('计划执行时间不能小于当前时间','warning');
							return false;
						}
						
				    	// 执行保存
				    	loading('正在提交，请稍等...');
				    	$.post('${ctx}/paramconfig/netParamModify/updateParam', {
				    		"netid":netid,"modcmd":modcmd,"cmdvalue":cmdvalue,"executetype":executetype,"date" : datetime
						 }, function(data) {
							 data = eval("("+data+")");
							 $('#jbox-states').remove();
							 if(data.msg=="success"){
								 top.$.jBox.tip('保存成功','warning');
			   				 }else{
			   					top.$.jBox.tip('保存失败','warning');
			   				 }
						});
				    	
				    	return true;
					}
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/paramconfig/netParamModify/">单网元参数设置</a></li>
	</ul>
	<c:if test="${isRedirect == 'true'}">
   <p style="float: right;margin-right: 50px;line-height: 28px"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back1" style="font-size: 24px;font-weight: bold;float: left;margin-top: 10px;"></span><span style="padding-left: 15px;float: left;margin-top: 6px;">返回</span></a></p>
    </c:if>
	<sys:message content="${message}"/>
	<form:form id="searchForm" modelAttribute="paramData" action="" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label style="width:50px">网元：</label>
				<%-- <form:select path="netid"  id="newNetelementlist" class="input-xlarge" style="width:150px;" >
					<form:option value="" label="请选择"/>
					<form:options items="${newnetelementList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
				</form:select> --%>
				<form:select path="netid" class="input-xlarge" style="width:180px;" id="newNetelementlist">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li><label style="width:50px">日期：</label>
				<form:input id="date" path="fetchdate" type="text" readonly="readonly" maxlength="20" class="txt  Wdate" style="width:160px;"/>
			</li>
			<li><label>参数类型：</label>
				<form:select path="type" id="paramTypeList" class="input-xlarge" style="width:140px;" >
					<form:option value="" label="请选择"/>
				</form:select>
			</li>
			<li><label style="width:60px">关键字：</label>
				<form:input path="keyword" id="keyword" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="table"></table>
</body>
</html>