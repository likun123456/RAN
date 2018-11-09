<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>话单欺诈业务报表导出管理</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	
	<script src="${ctxStatic}/bootstrap/multiselect/2.1.1/multiselect.min.js" type="text/javascript"></script>
	    
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	
	
	<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
	<!-- customer common start-->
	<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/epc.js" type="text/javascript"></script>
	<!-- customer common end-->
	
   <script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
	<script type="text/javascript">
		$(function(){
			
			Date.prototype.format = function(format)
			{
			 var o = {
			 "M+" : this.getMonth()+1, //month
			 "d+" : this.getDate(),    //day
			 "h+" : this.getHours(),   //hour
			 "m+" : this.getMinutes(), //minute
			 "s+" : this.getSeconds(), //second
			 "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
			 "S" : this.getMilliseconds() //millisecond
			 }
			 if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
			 (this.getFullYear()+"").substr(4 - RegExp.$1.length));
			 for(var k in o)if(new RegExp("("+ k +")").test(format))
			 format = format.replace(RegExp.$1,
			 RegExp.$1.length==1 ? o[k] :
			 ("00"+ o[k]).substr((""+ o[k]).length));
			 return format;
			}
		   
			$('#cheatReports').multiselect({
				keepRenderingSort: true
			});
			
			$('#netElements').multiselect({
				keepRenderingSort: true
			});
			
			var currDate = new Date();
			var thisYear = currDate.getFullYear();
			var lastYear = thisYear-1;
			
			$('#yearElements').append("<option value='"+thisYear+"' selected >"+thisYear+"年</option>");
			$('#yearElements').append("<option value='"+lastYear+"'>"+lastYear+"年</option>");
			$("#monthElements option[name='"+(currDate.getMonth()+1)+"']").attr("selected","selected");
			$("#dayElements  option[name='"+currDate.getDate()+"']").attr("selected","selected");
			$('input:radio[name="optionsRadiosinline"]').change(function(){
				var value = $(this).val();
				if(value=="day"){
					$('#dayElements').removeAttr("disabled"); 
					$('#hourElements').attr("disabled","disabled");
				}else if(value=="hour"){
					$('#hourElements').removeAttr("disabled"); 
					$('#dayElements').removeAttr("disabled"); 
				}else{
					$('#hourElements').attr("disabled","disabled");
					$('#dayElements').attr("disabled","disabled");
				}
			});
			
			
			setTimeElements($('#pastScope').val());
			$('#manual_div').hide();
			$('input:radio[name="timeMode"]').change(function(){
				$("#timeElements option").remove();
				var value = $(this).val();
				if(value=="auto"){
					$('#auto_div').show();
					setTimeElements($('#pastScope').val());
					$('#manual_div').hide();
				}else if(value=="manual"){
					$('#auto_div').hide();
					$('#manual_div').show();
				}
			});
			
			$('#pastScope').change(function(){
				$("#timeElements option").remove();
				
				setTimeElements($(this).val());
				
			});
			
		     //追加按钮的点击事件
		     $("#timeElement_add").click(function(){
			     $("#timeElements option").remove();
				 var flag = false;
				 var flag_six = new Array();
				 $("#hourElements option:selected").each(function(hour_i,hour_o){
					 if($(hour_o).val()==6){
					    flag = true;
					 }else{
					    if($(hour_o).val()!="08:00:00"&&$(hour_o).val()!="09:00:00"&&$(hour_o).val()!="10:00:00"&&$(hour_o).val()!="18:00:00"&&$(hour_o).val()!="19:00:00"&&$(hour_o).val()!="20:00:00"){
						   flag_six.push(hour_o);
						}
					 }
				 })
				 if(flag){
				     $("#yearElements option:selected").each(function(year_i,year_o){
				        $("#monthElements option:selected").each(function(month_i,month_o){
				        	    var flag_month = $('#radio_month').is(':checked');
				        	    if(flag_month){
				        	    	var html = $(year_o).val()+"-"+$(month_o).val();
									$("#timeElements").append("<option>"+html+"</option>");
				        	    }else{
				        	    	$("#dayElements option:selected").each(function(day_i,day_o){
									   //过滤非法日期
									   if(checkYMD($(year_o).val(),$(month_o).val(),$(day_o).val())==1){
									       return true;
									   }
									   var flag_day = $('#radio_day').is(':checked');
									   if(flag_day){
										   var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val();
									       $("#timeElements").append("<option>"+html+"</option>");
									   }else{
										   for(var i=8;i<10;i++){
										      var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val()+" 0"+i+":00:00";
										      $("#timeElements").append("<option>"+html+"</option>");
										   }
										      var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val()+" 10:00:00";
										      $("#timeElements").append("<option>"+html+"</option>");
										   for(var i=18;i<21;i++){
										      var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val()+" "+i+":00:00";
										      $("#timeElements").append("<option>"+html+"</option>");
										   }     
										   for(var i=0;i<flag_six.length;i++){
										      var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val()+" "+$(flag_six[i]).val();
										      $("#timeElements").append("<option>"+html+"</option>");
										   }
									   }
									})
				        	    }       
						})
				    })
				 }else{
				    $("#yearElements option:selected").each(function(year_i,year_o){
				        $("#monthElements option:selected").each(function(month_i,month_o){
				        	    var flag_month = $('#radio_month').is(':checked');
				        	    if(flag_month){
				        	    	var html = $(year_o).val()+"-"+$(month_o).val();
									$("#timeElements").append("<option>"+html+"</option>");
				        	    }else{
				        	    	$("#dayElements option:selected").each(function(day_i,day_o){
		                               //过滤非法日期
									   if(checkYMD($(year_o).val(),$(month_o).val(),$(day_o).val())==1){
									       return true;
									   }
		                               var flag_day = $('#radio_day').is(':checked');
		                               if(flag_day){
		                            	      var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val();
									          $("#timeElements").append("<option>"+html+"</option>");
		                               }else{
		                            	   $("#hourElements option:selected").each(function(hour_i,hour_o){
									          var html = $(year_o).val()+"-"+$(month_o).val()+"-"+$(day_o).val()+" "+$(hour_o).val();
									          $("#timeElements").append("<option>"+html+"</option>");
									       });
		                               }
									});	
				        	    }
						});
				    })	;		 
				 }		 
			  });
		     //删除按钮的点击事件
			  $("#timeElement_del").click(function(){
			      $("#timeElements option:selected").each(function(i,o){
				        $(o).remove();	  
				  });
			  });
		     //全删按钮的点击事件
			  $("#timeElement_delAll").click(function(){
			      $("#timeElements option").remove();
			  });
		
		 });
		
		function setTimeElements(day){
			var value = parseInt(day);
			var now = new Date();
			for(var i=1;i<(value+1);i++){
				var date = addDate(now,-i);
		        var html = date.format("yyyy-MM-dd");
		        $("#timeElements").append("<option>"+html+"</option>");
		    }
		}
		
		
		function checkYMD(year,month,day){
			 var flag_YMD = 0;
		   if(year%4==0&&year%100!=0||year%400==0){
		      if(month==2&&(day==30||day==31)){
				   flag_YMD = 1;
				}else if((month==4||month==6||month==9||month==11)&&day==31){
				   flag_YMD = 1;
				}
			 }else{
				if(month==2&&(day==29||day==30||day==31)){
				   flag_YMD = 1;
				}else if((month==4||month==6||month==9||month==11)&&day==31){
				   flag_YMD = 1;
				}
			 }
			 return flag_YMD;
		}
		
		function doSubmit(){
			
			var cheatReportsArray = new Array();  //定义数组
		    $("#cheatReports_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 cheatReportsArray.push(txt);  //添加到数组中  
		         }  
		    })
			if(cheatReportsArray.length==0){
				top.$.jBox.tip('请至少添加一个报表类型','warning');
				return;
			}
		    var netElementsArray = new Array();  //定义数组 
		    $("#netElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 netElementsArray.push(txt);  //添加到数组中  
		         }  
		    })
			if(netElementsArray.length==0){
				top.$.jBox.tip('请至少添加一个网元','warning');
				return;
			}
			var timeArray = new Array();  //定义数组
		    $("#timeElements option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 timeArray.push(txt);  //添加到数组中  
		         }  
		    })
			if(timeArray.length==0){
				top.$.jBox.tip('请至少添加一个时间粒度','warning');
				return;
			}
			document.forms[0].action = "${ctx}/cheat/cheatReport/export?cheatReports="+cheatReportsArray +"&netElements="+netElementsArray+"&timeElements="+timeArray;
			document.forms[0].method = "POST";
			document.forms[0].submit();
		}
		</script>
</head>
	
<body>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cheat/cheatReport/config"><strong>报表导出管理</strong></a></li>
	</ul>
	<sys:message content="${message}"/>
	<form id="inputForm" action="${ctx}/cheat/cheatReport/export" method="post">
		
		
		<div class="col-sm-12">
			<div class="col-sm-7">
			<h4 id="with-data-options">请选择报表粒度</h4>
			
				<div class="col-sm-5">
					
					<select name="from" id="cheatReports" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
				
				<div class="col-sm-2">
					<button type="button" id="cheatReports_rightAll" class="btn btn-block"><b>全增</b></button>
					<button type="button" id="cheatReports_rightSelected" class="btn btn-block"><b>增加</b></button>
					<button type="button" id="cheatReports_leftSelected" class="btn btn-block"><b>删除</b></button>
					<button type="button" id="cheatReports_leftAll" class="btn btn-block"><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="cheatReports_to" class="form-control" size="8" multiple="multiple">
						<c:forEach items="${cheatReports}" var="cheatReport">
							<option value="${cheatReport.cheatCase}">${cheatReport.reportName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-sm-5">
			<h4 id="with-data-options">请选择网元粒度</h4>
				<div class="col-sm-5">
				
					<select name="from" id="netElements" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="netElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="netElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="netElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="netElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="netElements_to" class="form-control" size="8" multiple="multiple">
						<c:forEach items="${netElements}" var="netElement">
							<option value="${netElement.id}">${netElement.fname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
	
	
		<div class="col-sm-12">
		
			    <h4 id="with-data-options">请选择时间粒度</h4>
				<div class="row-sm-4">
				<label class="checkbox-inline">
			    <input id="radio_auto" type="radio" name="timeMode"  value="auto" checked>快速导出
				</label>
			    <label class="checkbox-inline">
				  <input id="radio_manual" type="radio" name="timeMode"  value="manual">手动选择
				</label>
				</div>
				
			<div class="col-xs-2" id="auto_div">		
				<select id="pastScope" name="pastScope" class="form-control">
					<c:forEach items="${fns:getDictList('biz_cheat_report_date')}" var="dict">
						<option value="${dict.value}">${dict.label}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="col-sm-12" id="manual_div">
				
				<div class="row-sm-4">
				<label class="checkbox-inline">
			    <input id="radio_hour" type="radio" name="optionsRadiosinline"  value="hour" checked>小时报
				</label>
			    <label class="checkbox-inline">
				  <input id="radio_day" type="radio" name="optionsRadiosinline"  value="day">日报
				</label>
				<label class="checkbox-inline">
				  <input id="radio_month" type="radio" name="optionsRadiosinline" value="month">月报
				</label>
				</div>
				
				<div class="col-xs-1">
					<select name="from" id="yearElements" class="form-control" size="12" multiple="multiple" style="width:90px;">
					</select>
				</div>
				
				<div class="col-xs-1">
					<select name="from" id="monthElements" class="form-control" size="12" multiple="multiple" style="width:80px;">
						<option value="01" name="1">1月</option>
				        <option value="02" name="2">2月</option>
				        <option value="03" name="3">3月</option>
				        <option value="04" name="4">4月</option>
				        <option value="05" name="5">5月</option>
				        <option value="06" name="6">6月</option>
				        <option value="07" name="7">7月</option>
				        <option value="08" name="8">8月</option>
				        <option value="09" name="9">9月</option>
				        <option value="10" name="10">10月</option>
				        <option value="11" name="11">11月</option>
				        <option value="12" name="12">12月</option>
					</select>
				</div>
				
				<div class="col-xs-1">
					<select name="from" id="dayElements" class="form-control" size="12" multiple="multiple" style="width:80px;">
						<option value="01" name="1">1日</option>
				        <option value="02" name="2">2日</option>
				        <option value="03" name="3">3日</option>
				        <option value="04" name="4">4日</option>
				        <option value="05" name="5">5日</option>
				        <option value="06" name="6">6日</option>
				        <option value="07" name="7">7日</option>
				        <option value="08" name="8">8日</option>
				        <option value="09" name="9">9日</option>
				        <option value="10" name="10">10日</option>
				        <option value="11" name="11">11日</option>
				        <option value="12" name="12">12日</option>
				        <option value="13" name="13">13日</option>
				        <option value="14" name="14">14日</option>
				        <option value="15" name="15">15日</option>
				        <option value="16" name="16">16日</option>
				        <option value="17" name="17">17日</option>
				        <option value="18" name="18">18日</option>
				        <option value="19" name="19">19日</option>
				        <option value="20" name="20">20日</option>
				        <option value="21" name="21">21日</option>
				        <option value="22" name="22">22日</option>
				        <option value="23" name="23">23日</option>
				        <option value="24" name="24">24日</option>
				        <option value="25" name="25">25日</option>
				        <option value="26" name="26">26日</option>
				        <option value="27" name="27">27日</option>
				        <option value="28" name="28">28日</option>
				        <option value="29" name="29">29日</option>
				        <option value="30" name="30">30日</option>
				        <option value="31" name="31">31日</option>
					</select>
				</div>
				
				<div class="col-xs-1" >
					<select name="from" id="hourElements" class="form-control" size="12" multiple="multiple" style="width:80px;">
						<option value="6" selected="selected">六忙时</option>
				        <option value="00:00:00">0时</option>
				        <option value="01:00:00">1时</option>
				        <option value="02:00:00">2时</option>
				        <option value="03:00:00">3时</option>
				        <option value="04:00:00">4时</option>
				        <option value="05:00:00">5时</option>
				        <option value="06:00:00">6时</option>
				        <option value="07:00:00">7时</option>
				        <option value="08:00:00">8时</option>
				        <option value="09:00:00">9时</option>
				        <option value="10:00:00">10时</option>
				        <option value="11:00:00">11时</option>
				        <option value="12:00:00">12时</option>
				        <option value="13:00:00">13时</option>
				        <option value="14:00:00">14时</option>
				        <option value="15:00:00">15时</option>
				        <option value="16:00:00">16时</option>
				        <option value="17:00:00">17时</option>
				        <option value="18:00:00">18时</option>
				        <option value="19:00:00">19时</option>
				        <option value="20:00:00">20时</option>
				        <option value="21:00:00">21时</option>
				        <option value="22:00:00">22时</option>
				        <option value="23:00:00">23时</option>
					</select>
				</div>
				
				<div class="col-sm-1">
					
					<button type="button" id="timeElement_add" class="btn btn-block"><b>增加</b></button>
					<button type="button" id="timeElement_del" class="btn btn-block"><b>删除</b></button>
					<button type="button" id="timeElement_delAll" class="btn btn-block"><b>全删</b></button>
				</div>
				
				<div class="col-sm-7">
					<select id="timeElements" class="form-control" size="12" multiple="multiple"></select>
				</div>
			</div>
			    
		
		</div>
		
		<div class="col-sm-12" style="margin-top:20px;padding-right:50px;">
		    <div  >
			   <shiro:hasPermission name="cheat:cheatReport:export"><button type="button" id="exportReport" class="btn btn-primary" onclick="doSubmit()"><b>导 出</b></button></shiro:hasPermission>
		    </div>
		</div>
	</form>
   

  </body>
</html>
