<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户EBM查询</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
	.ebmMask {       
	            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #999;     
	            z-index: 1002; left: 0px;     
	            opacity:0.5; -moz-opacity:0.5;     
	        }
	</style>
	<script type="text/javascript">
	    //定义全局变量
	    var netType;
	    var imsi_msisdn;
	    var startTime;
	    var endTime;
	    var searchType;
	    var flag = true;
	    var isExport = false;
	    var nType = "";
	    var eventType = "";
	    var eventResult = "";
	    var eventTypeSearchFlag = true;
		$(document).ready(function() {
			$("#startTimeLi").hide();
			$("#endTimeLi").hide();
			var start_time = '${startTime}';
            if(start_time!=""){
            	$("input[name='checkboxbutton']").get(0).checked=true; 
            	$("input[name='checkboxbutton']").get(1).checked=true; 
            	var isImsi = '${isImsi}';
            	var imsi = '${imsi}';
    			var msisdn = '${msisdn}';
    			var end_time = start_time.substring(0,10)+" ${endTime}";
    			if(isImsi=="true"){
    				$("input[name='radiobutton']").get(0).checked=true; 
    				$("#msisdn-span").hide();
    	        	$("#imsi-span").show();
    	        	$("#imsi_msisdn_i").val(imsi);
    			}else{
    				$("#imsi_msisdn_m").val(msisdn);
    			}
    			$("#txtBeginDay").val(start_time);
    			$("#txtEndDay").val(end_time);
    			$("#startTimeLi").show();
    			$("#endTimeLi").show();
    			$("#timeScopeLi").hide();
    			queryTable();
            }
            
            var isRedirect = '${isRedirect}';
            if('true'==isRedirect) {
            	//searchType
            	var netType = '${netType}';
            	var netArray = netType.split(",");
            	$("input:checkbox").each(function(index,domEle) {
            		var dom = $(domEle).val();
            		for(var i =0; i<netArray.length; i++) {
            			if(netArray[i] == dom) {
            				$(domEle).prop("checked",true);
            			}
            		}
  			  	});
            	
            	var searchType = '${searchType}';
            	var searchValue = '${searchValue}';
            	if(searchType == 'imsi') {
            		$("input[name='radiobutton']").get(0).checked=true; 
            		$("#imsi_msisdn_i").val(searchValue);
            		$("#msisdn-span").hide();
		        	$("#imsi-span").show();
            		
            	} else {
            		$("input[name='radiobutton']").get(1).checked=true; 
            		$("#imsi_msisdn_m").val(searchValue);
            		$("#msisdn-span").show();
		        	$("#imsi-span").hide();
            	}
            	var pastScope = '${pastScope}';
            	$("#pastScope").val(pastScope);
				var label = '${fns:getDictLabel(pastScope,'biz_past_time_tac','')}';
				$(".select2-container .select2-chosen:eq(0)").html(label);
				var beginDate = '${beginDate}';
				var endDate = '${endDate}';
				if('0' == pastScope) {
					$("#txtBeginDay").val(beginDate);
					$("#txtEndDay").val(endDate);
					$("#startTimeLi").show();
					$("#endTimeLi").show();
				} else {
					$("#txtBeginDay").val(getTacStartTime(pastScope));
					$("#txtEndDay").val(getTacEndTime(pastScope));
					//$("#timeScope").show();
				}
				queryTable();
            }
			
			
			//动态显示IMSI输入框或者MSISDN输入框
			$("input[name='radiobutton']").change(function(){
		        var o = $("input[name='radiobutton']:checked").val();
		        if(o=="IMSI"){
		        	$("#msisdn-span").hide();
		        	$("#imsi-span").show();
		        }else{
		        	$("#msisdn-span").show();
		        	$("#imsi-span").hide();
		        }
		     });
			 //执行查询
			 $("#btnQuery").click(function(){
				 eventTypeSearchFlag = true;
				 //flag = true;
				 queryTable();
			 });
			 
			 $("#btnExport").click(function(){
				 if(isExport){
					 exportTable();
				 }else{
					 showTip("“先执行查询才能进行导出”");
				 }
			 });
			 
			 $("#pastScope").change(function(){
					//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
					$("#startTimeLi").hide();
					$("#endTimeLi").hide();
					if(this.value==""){
						$("#txtBeginDay").val("");
						$("#txtEndDay").val("");
						return false;
					}
					if(this.value=="0"){
						$("#txtBeginDay").val("");
						$("#txtEndDay").val("");
						$("#startTimeLi").show();
						$("#endTimeLi").show();
						return false;
					}
					$("#txtBeginDay").val(getTacStartTime(this.value));
					$("#txtEndDay").val(getTacEndTime(this.value));
			});
			 
			 
			$("#queryChoose").click(function(){
				eventTypeSearchFlag = false;
				queryTable();
			});
			
			
		});
		/**
		* 表单验证  
		*/
		function validate(){
			  var str = "";
		      var i = 0;
		      $("input:checkbox").each(function(index,domEle) {
				  if($(domEle).prop("checked")==true) {
					 if(i==index){
						 str = $(domEle).val();
					 }else{
						 str = str + "," + ($(domEle).val());
					 } 
				  }else{
					 i++;
				  }
			  });
	          netType = str;
	          searchType = $("input[name='radiobutton']:checked").val();
	          if(searchType == "IMSI"){
	        	  imsi_msisdn = $("#imsi_msisdn_i").val();
	          }else{
	        	  imsi_msisdn = $("#imsi_msisdn_m").val();
	          }
		      startTime = $("#txtBeginDay").val();
		      endTime = $("#txtEndDay").val();
		      if(endTime.length<10){
		    	  endTime = startTime.substring(0,10)+" "+endTime;
		      }
		      if(netType==""){
		    	 showTip("“网元类型不能为空”");
		    	 return "error";
		      }
		      if(imsi_msisdn==""){
		    	  if(searchType == "IMSI"){
		    		  showTip("“IMSI不能为空”");
		          }else{
		        	  showTip("“MSISDN不能为空”");
		          }
		    	  return "error";
		      }
		      if(isNaN(imsi_msisdn)){
		    	  if(searchType == "IMSI"){
		    		  showTip("“IMSI必须全是数字”");
		          }else{
		        	  showTip("“MSISDN必须全是数字”");
		          }
		    	  return "error";
		      }
		      if(startTime==""){
		    	 showTip("“开始时间不能为空”");
		    	 return "error";
		      }
		      if(endTime==""){
		    	 showTip("“结束时间不能为空”");
		    	 return "error";
		      }
		}
		/**
		* 查询ebm单用户列表
		*/
		function queryTable(){
			var r = validate();
			if(r=="error"){
				return;
			}
			//查询时段赋值
			$("#queryTimeScope").html("查询时段："+$("#txtBeginDay").val()+" 至 "+$("#txtEndDay").val());
			flag = true;
			showEbmMask();
			$("#queryChooseDiv").hide();
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/userquery/tEbmSingleSearch/list',
		        method:'POST',
		        dataType:'json',
		        cache:false,
		        contentType : "application/x-www-form-urlencoded",
		        striped: true,                      //是否显示行间隔色
		        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		        pagination:true,
		        minimumCountColumns:2,
		        pageNumber:1,                       //初始化加载第一页，默认第一页
		        pageSize: 15,                 //每页的记录行数（*）
		        pageList: [15, 30, 50, 100],   //可供选择的每页的行数（*）
		        queryParams:  function(params) {
		        	 if(flag&&eventTypeSearchFlag){
		        		 result = "1";
		        		 $("#eventTypeSearch option:not(:first)").remove();  
		        	 }else{
		        		 result = "2";
		        		 nType = $("#ntype option:selected").val();
		     			 eventType = $("#eventTypeSearch option:selected").val();
		     			 eventResult = $("#eventResultType option:selected").val();
		     			eventTypeSearchFlag = false;
		        	 }
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   sortName:this.sortName,
                       sortOrder:this.sortOrder,
                       netType: netType,
                       searchType:searchType,
                       imsi_msisdn:imsi_msisdn,
				       startTime:startTime,
				       endTime:endTime,
				       nType:nType,
				       eventType:eventType,
				       eventResult:eventResult,
				       result:result
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	debugger;
		        	flag = false;
		        	isExport = true;
		        	if(eventTypeSearchFlag&&(data.total!=0)){
		        		/* $.each(data.eventTypeDicList, function(index, o){ 
				    		var html = "<option value='"+o+"'>"+o+"</option>";
						    $("#eventTypeSearch").append(html);
					    });  */
		        	}
		        	$("#queryChooseDiv").show();
		        	hideEbmMask();
		        },
		        onLoadError:function(){
		        	hideEbmMask();
		        },
		        columns: [
		        {   field : 'date',
		            title : '时间',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		            
		        }, {
		            field: 'ntype',
                    title: '网元类型',
                    align : 'center',
		            valign : 'middle',
		            sortable:true
		        }, {
		            field : 'eventId',
		            title : '事件类型',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'eventResult',
		            title : '事件结果',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'imsi',
		            title : 'IMSI号码',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'msisdn',
		            title : 'MSISDN号码',
		            align : 'center',
		            valign : 'middle',
		            sortable : true
		        }, {
		            field : 'id',
		            title : '操作',
		            align : 'center',
		            valign : 'middle',
		          	formatter:function(value,row,index){
		          		var imsi = row.imsi;
		          		var cc = row.cc;
		          		var scc = row.scc;
		          		var eci = row.eci;
		          		var date = row.date;
		          		var eventId = row.eventId;
		          		var eventResult = row.eventResult;
		          		return '<a href="${ctx}/userquery/tEbmSingleSearch/detail?id='+value+'&imsi='+imsi+'&cc='+cc+
		          				'&scc='+scc+'&eci='+eci+'&date='+date+'&eventId='+eventId+'&eventResult='+eventResult+
		          				'" onclick="switchTab();"  target="detailFrame">详细信息</a>';
		          	}
	            }]
		    });  
		}
		
		function exportTable(){
			window.location.href= "${ctx}/userquery/tEbmSingleSearch/exportExcel.do"
					
		}
		
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
		
		function showEbmMask(){
			$('#ebmMask').css("height",$(window).height());     
		    $('#ebmMask').css("width",$(window).width());     
		    $('#ebmMask').show();  
		}
		
		function hideEbmMask(){
			$("#ebmMask").hide();
		}
	</script>
</head>
<body>
    <div id="ebmMask" class="ebmMask"></div>
	<ul class="nav nav-tabs">
		<li class="active" id="menu1" onclick="setTab('menu',1,2)"><a>单用户EBM查询列表</a></li>
		<li id="menu2" onclick="setTab('menu',2,2)"><a>单用户EBM详细信息</a></li>
	</ul>
	<div id="con_menu_1" class="hover"> 
	<div class="navbar">
      <div class="navbar-inner">
        <span class="icon-R" style="font-size: 45px; float:left;margin-left:-10px;"></span><a style="margin-left:20px;" class="brand" href="${ctx}/userquery/cdr/show">单用户EBM列表</a>
       	<c:if test="${isRedirect == 'true'}">
        <span style="float: right;line-height: 28px;margin-top: 10px;"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 15px;float: left;margin-top: -2px;">返回</span></a></span>
       	</c:if>
        <span id="queryTimeScope" style="float:right;padding:15px;"></span>
      </div>
    </div>
	<form:form id="searchForm" class="breadcrumb form-search" >
		<ul class="ul-form">
		    <li>
		        <label>网元类型：</label>
		        <input type="checkbox" name="checkboxbutton" value="MME" />MME  
                <input type="checkbox" name="checkboxbutton" value="EPG" />SAEGW  
                <input type="checkbox" name="checkboxbutton" value="PCRF" />PCRF  
			</li>
			<li>
		        <label>查询类型：</label>
		        <input type="radio" value="IMSI" name="radiobutton"/>IMSI
                <input type="radio" value="MSISDN" name="radiobutton" checked style=""/>MSISDN
                <span id="msisdn-span" style="margin-left:20px;">MSISDN：<input type="text" id="imsi_msisdn_m" style="width:135px;"/></span>
			    <span style="display:none;margin-left:20px;" id="imsi-span" >IMSI：<input type="text" id="imsi_msisdn_i" style="width:135px;"/></span>  
			</li>
			<li id="timeScopeLi"><label style="width:50px">时间段</label>
               <select id="pastScope" class="input-medium" style="width:100px">
				<option value="">请选择</option>
				<c:forEach items="${fns:getDictList('biz_past_time_tac')}" var="dict">
					<option value="${dict.value}">${dict.label}</option>
				</c:forEach>
			</select>
			</li>
			<li id="startTimeLi">
		        <label>开始时间：</label>
		        <input type="text" style="width:160px;" class="Wdate" value="${beginday}" id="txtBeginDay" name="beginday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,maxDate:'#F{$dp.$D(\'txtEndDay\')}'});"/>
			</li>
			<li id="endTimeLi">
		        <label>结束时间：</label>
		        <input type="text" style="width:160px;" class="Wdate" value="${endday}" id="txtEndDay" name="endday" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss' ,minDate:'#F{$dp.$D(\'txtBeginDay\')}'});" />
			</li>
			<li class="btns"><input id="btnQuery" class="btn-new btn-search" type="button" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn-new btn-save" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<div id="queryChooseDiv" style="display:none;">
	<select id="ntype" class="input-medium">
	    <option value=''>网元类型</option>
	    <c:forEach items="${fns:getDictList('biz_pool_type')}" var="dict">
			<option value="${dict.value}">${dict.label}</option>
		</c:forEach>
	</select>
	<select id="eventTypeSearch" class="input-medium" style="width:200px;">
	    <option value=''>事件类型</option>
	</select>
	<select id="eventResultType" class="input-medium">
	    <option value=''>事件结果</option>
	    <c:forEach items="${fns:getDictList('biz_eventResult_type')}" var="dict">
			<option value="${dict.value}">${dict.label}</option>
		</c:forEach>
	</select>
	<input id="queryChoose" class="btn-new btn-search" type="button" value="筛选"/>
	</div>
	<table id="table" class="table table-striped table-bordered table-condensed">
	</table>
	</div>
	<div id="con_menu_2" style="display:none">
		<iframe id="detailFrame" name="detailFrame" scrolling="yes" frameborder="0" style="width:100%;height:600px;" ></iframe> 
	</div>
</body>
</html>