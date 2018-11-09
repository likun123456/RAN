<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户话单查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			/* $(document).on('show.bs.modal','.modal', function(){
				$(this).draggable({
			        handle: ".modal-header"   // 只能点击头部拖动
			    });
			    $(this).css("overflow-y", "scroll");
			}); */
			var isRedirect = '${isRedirect}';
			if('true'==isRedirect) {
				var msisdn = '${msisdn}';
				var pastScope = '${pastScope}';
				var beginDate = '${beginDate}';
				var endDate = '${endDate}';
				$("#number").val(msisdn);
				$("#pastScope").val(pastScope);
				var label = '${fns:getDictLabel(pastScope,'biz_past_time_scope','')}';
				$(".select2-container .select2-chosen:eq(0)").html(label);
				if('0' == pastScope) {
					$("#beginDate").val(beginDate);
					$("#endDate").val(endDate);
					$("#timeScope").show();
				} else {
					$("#beginDate").val(getWholePointTime(-1 * (parseInt(pastScope)/24)));
					$("#endDate").val(getWholePointTime(0));
					//$("#timeScope").show();
				}
				
				sendPost();
			}
			
			
			window.onresize=function(){  
			  changeFrameHeight();  
			} 
			
			
			if($.validator){
			    $.validator.prototype.elements = function () {
			        var validator = this,
			        rulesCache = {};
			        // Select all valid inputs inside the form (no submit or reset buttons)
			        return $( this.currentForm )
			        .find( "input, select, textarea, [contenteditable]" )
			        .not( ":submit, :reset, :image, :disabled" )
			        .not( this.settings.ignore )
			        .filter( function() {
			            var name = this.id || this.name || $( this ).attr( "name" ); // For contenteditable
			            if ( !name && validator.settings.debug && window.console ) {
			                console.error( "%o has no name assigned", this );
			            }
			            // Set form expando on contenteditable
			            if ( this.hasAttribute( "contenteditable" ) ) {
			                this.form = $( this ).closest( "form" )[ 0 ];
			            }
			            // Select only the first element for each name, and only those with rules specified
			            if (name in rulesCache || !validator.objectLength( $( this ).rules() ) ) {
			                return false;
			            }
			            rulesCache[ name ] = true;
			            return true;
			        } );
			    }
			}
			
			$("#numbersInputForm").validate({  
		        rules:{  
		        	msisdn:{  
		                checkMSISDN:true,  
		            }
		        },  
		        //是否在获取焦点时验证  
		        //onfocusout:false,  
		        //是否在敲击键盘时验证  
		        //onkeyup:false,  
		        //提交表单后，（第一个）未通过验证的表单获得焦点  
		        focusInvalid:true,  
		        //当未通过验证的元素获得焦点时，移除错误提示  
		        focusCleanup:true,  
		        /* 设置验证触发事件 */  
		        showErrors:function(errorMap,errorList) {
				    if(errorList.length){
				        errorList[0].element.focus();
				    }
				    this.defaultShowErrors();
				}
		    });  
			
			$.validator.addMethod("checkMSISDN",function(value,element,params){  
	            var checkMSISDN = /^[xX]+$|^\d{6,13}$/;  
	            return this.optional(element)||(checkMSISDN.test(value));  
	        },"手机号码不正确！");
			
			
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
					var number = $("#number").val().split(",");
                	var checkMSISDN = /^[xX]+$|^\d{6,13}$/;
                	for(var i=0;i<number.length;i++){
                		if(number[i]==""){
                			top.$.jBox.tip('请输入用户MSISDN号','warning');
                			return false;
                		}
                		if(!checkMSISDN.test(number[i])){
                			$("#number").focus();
                			top.$.jBox.tip('手机号码不正确','warning');
                			return false;
                		}
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
                        sendPost();
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
			
			//保存按钮
			$("#saveNumbers").click(function(){
				if($("#numbersInputForm").valid()){
					var numbersArray = new Array();  //定义数组
				    $("#numbersInput input").each(function(){  //遍历所有input  
				    	var txt = $(this).val();   //获取input值   
				         if(txt!=''){  
				        	 numbersArray.push(txt);  //添加到数组中  
				         }  
				    });
				    $("#number").val(numbersArray);
				    $('#batchInputModal').modal('hide');
				}
				
			});
			
			$("#btnExport").click(function(){
				searchForm.action='${ctx}/userquery/cdr/exportCdr';
				searchForm.submit();
			});
			
			//批量录入按钮
			$("#batchInputBtn").click(function(){
				var number = $("#number").val().split(",");
				$("#numbersInput input").each(function(index,element){   
					$(this).val($("#number").val().split(",")[index]); //为input赋值
			    });
			});
			
			$("#btnDownLoad").click(function(){
				
				searchForm.action= "${ctx}/userquery/cdr/downloadZip";
				searchForm.submit();
				
			/* 	$.get('${ctx}/userquery/cdr/getFiles',{datetime:new Date().getTime()}, function(data){
					if (data=="success"){
						location = "${ctx}/userquery/cdr/downloadZip";
					}else if(data=="empty"){
						top.$.jBox.tip('未找到对应的话单文件!','warning');
					}
				}); */
		    });
			
		});
		
		
		function changeFrameHeight(){
		    var ifm= $("#detailFrame"); 
		    ifm.height(document.documentElement.clientHeight - 60);
		}
		
		/* function query() {
			//TODO 更换loading图标
			setTimeout(sendPost,500);
    	} */
		
		//var timer=null;
		//发送post请求
		function sendPost(){
			  loading('正在查询，请稍等...');
			  //查询时段赋值
			  $("#queryTimeScope").html("查询时段："+$("#beginDate").val()+" 至 "+$("#endDate").val());
			  $("#btnExport").hide();
			  $("#btnDownLoad").hide();
			  
			  //表格初始化
			  $("#contentTable").bootstrapTable('destroy'); 
			  $('#contentTable').bootstrapTable({
					url:'${ctx}/userquery/cdr/list',
			        method:'POST',
			        dataType:'json',
			        cache: false,
			        contentType : "application/x-www-form-urlencoded",
			        striped: true,                      //是否显示行间隔色
			        sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
			        pagination:true,
			        minimumCountColumns:2,
			        pageNumber:1,                       //初始化加载第一页，默认第一页
			        pageSize: 10,                 //每页的记录行数（*）
			        pageList: [10, 25, 50, 100],   //可供选择的每页的行数（*）
			        queryParams:  function(params) {
			        	var param = $("#searchForm").serializeJsonObject();
			        	param["pageSize"]=params.limit;
			        	param["pageCurrent"]=params.offset/params.limit+1;
			            return param;
			        },
			        formatLoadingMessage: function () {
		        	 return '';
		        	 },
			        onLoadSuccess: function (data) {
			        	//clearInterval(timer);
			        	parent.$('#jbox-states').css("display","none");
			        	if(data!=""){
			        		$("#btnExport").show();
			        		$("#btnDownLoad").show();
			        	}
			        },
			        onClickRow: function (row) {
			        	return false;
			        },
			        columns: [
			        {
			            field: 'servedimsi',
			            title: '用户IMSI',
			            align : 'center',
			            valign : 'middle'
			        }, {
			            field : 'servedmsisdn',
			            title : '用户MSISDN',
			            align : 'center',
			            valign : 'middle'
			        }, {
			            field : 'cdrtype',
			            title : '话单类型',
			            align : 'center',
			            valign : 'middle'
			        }, {
			            field : 'rattype',
			            title : 'RAT类型',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }, {
			            field : 'recordopeningtime',
			            title : '话单开始时间',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }, {
			            field : 'total_datavolumefbcuplink',
			            title : '上行数据总流量(Byte)',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }, {
			            field : 'total_datavolumefbcdownlink',
			            title : '下行数据总流量(Byte)',
			            align : 'center',
			            valign : 'middle',
			            sortable : true
			        }, {
			            field : 'servedimsi',
			            title : '操作',
			            align : 'center',
			            valign : 'middle',
		          	formatter:function(value,row,index){
		          		return '<a href="${ctx}/userquery/cdr/detail?index='+ row.index + '&tableIndex=' + index + ' " onclick="switchTab();"  target="detailFrame">详细信息</a>';
		          	}
		      }]
			    });
			  //timer = setInterval(showProcessText,500);
		}
		
		/* //显示当前执行进度
		function showProcessText(){
			$.get('${ctx}/userquery/cdr/progress?t='+new Date().getTime(), function(data) {
				data = eval("("+data+")");
				showTip(data,'warning',500,0);
		    });
				
		} */
		
		//自动切换至detail页
		function switchTab(){
			$("#con_menu_2").show(); 
			$("#con_menu_1").hide();
			
			$("#menu1").removeClass("active"); 
			$("#menu2").addClass("active"); 
			
		}
		
		//切换标签
		function setTab(name,cursel,n){ 
			for(i=1;i<=n;i++){ 
				var menu=$("#"+name+i); 
				var con=$("#con_"+name+"_"+i); 
				i==cursel?menu.addClass("active"):menu.removeClass("active"); 
				i==cursel?con.show():con.hide(); 
			} 
		} 
		
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active" id="menu1" onclick="setTab('menu',1,2)"><a>单用户话单查询</a></li>
		<li id="menu2" onclick="setTab('menu',2,2)"><a>话单详细信息</a></li>
	</ul>
	<div id="con_menu_1" class="hover"> 
    <div class="navbar">
      <div class="navbar-inner">
        <span class="icon-Q" style="font-size: 45px; float:left;margin-left:-10px;"></span><a class="brand" style="margin-left:20px;" href="${ctx}/userquery/cdr/show">单用户话单查询</a>
        <c:if test="${isRedirect == 'true'}">
        <span style="float: right;line-height: 28px;margin-top: 10px;"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 15px;float: left;margin-top: -2px;">返回</span></a></span>
        </c:if>
        <span id="queryTimeScope" style="float:right;padding:15px;"></span>
      </div>
    </div>
    <div id="processText" style="float:right;padding-right:20px;padding-top:10px;" ></div>
	<form:form id="searchForm" modelAttribute="userCdrQueryEntity" action="${ctx}/userquery/cdr/list" method="post" class="breadcrumb form-search">
		<div class="form-inline">
			<label for="number" title="输入X可查询未知MSISDN用户">用户MSISDN:</label>
			<%-- <form:select path="numberType" items="${fns:getDictList('biz_userquery_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-medium"/> --%>
			<form:input id="number" path="number" htmlEscape="false" class="input-medium" title="输入X可查询未知MSISDN用户" placeholder="输入X可查询未知MSISDN"/>
			<span class="icon-(" style="font-size: 20px; float:left; margin-top: 5px;margin-left:-10px;"></span>
			<a id="batchInputBtn" href="#batchInputModal" class="btn" data-toggle="modal">批量录入</a>
			<label>时间段:</label>
			<form:select id="pastScope" path="pastScope" style="width:177px;">
              <form:option value="" label="请选择"/>           
			  <form:options items="${fns:getDictList('biz_past_time_scope')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
         	</form:select>
         	<div class="pull-right">
         	<input id="btnQuery" class="btn-new btn-search" type="submit" value="查询"/>
			<input id="btnExport" class="btn-new btn-save" style="display:none;" type="button" value="导出Excel"/>
			<input id="btnDownLoad" class="btn-new btn-save" style="display:none;" type="button" value="下载原始话单"/>
			</div>
		</div>
		<div id="timeScope" class="form-inline hide" style="margin-left:4px;margin-top:5px;">
			<label>查询开始时间:</label>
			<input id="beginDate" name="beginDate"  type="text" maxlength="20" class="input-medium Wdate" 
				value="<fmt:formatDate value="${userCdrQueryEntity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
			<label style="margin-left:57px;">查询结束时间:</label>
			<input id="endDate" name="endDate" type="text" maxlength="20" class="input-medium Wdate" 
				value="<fmt:formatDate value="${userCdrQueryEntity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
		 </div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable"></table>
	</div>
	<div id="con_menu_2" style="display:none">
		<iframe id="detailFrame" name="detailFrame" scrolling="no" frameborder="0" style="width:100%;" onload="changeFrameHeight()" ></iframe> 
	</div>
	
 <!-- Modal -->
 <div id="batchInputModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h5 id="myModalLabel">批量录入</h5>
  </div>
  <div class="modal-body">
  <form:form id="numbersInputForm">
  	<div class="form-horizontal">
	    <div id="numbersInput" class="control-group" style="height:240px;overflow-y:scroll;">
      		<label class="control-label" style="margin:2px;">用户MSISDN01:</label>
   			<input type="text" id="msisdn01" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN02:</label>
   			<input type="text" id="msisdn02" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN03:</label>
   			<input type="text" id="msisdn03" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN04:</label>
   			<input type="text" id="msisdn04" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN05:</label>
   			<input type="text" id="msisdn05" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN06:</label>
   			<input type="text" id="msisdn06" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN07:</label>
   			<input type="text" id="msisdn07" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN08:</label>
   			<input type="text" id="msisdn08" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN09:</label>
   			<input type="text" id="msisdn09" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
   			<label class="control-label" style="margin:2px;">用户MSISDN10:</label>
   			<input type="text" id="msisdn10" name="msisdn" maxlength="50" class="input-medium" style="margin:2px;"/>
	     </div>
     </div>
     </form:form>
  </div>
  <div class="modal-footer">
    <button id="saveNumbers" class="btn btn-primary">保存</button>
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
<!-- Modal end-->

</body>
</html>