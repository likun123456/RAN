<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SAEGW错误码多维分析</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#startTimeLi").hide();
			$("#endTimeLi").hide();
			var isRedirect = '${isRedirect}';
			if(isRedirect == 'true') {
				var event = '${event}';
				var netId = '${netId}';
				var startTime = '${startTime}';
				var endTime = '${endTime}';
				
				$('#netid').val(netId);
				var netlabel = '${netName}';
				$(".select2-container .select2-chosen:eq(0)").html(netlabel);
				
				var eventlabel = '${eventfullname}';
				if(event != 'null' && eventlabel != 'null') {
					$('#eventType').val(event);
					$(".select2-container .select2-chosen:eq(1)").html(eventlabel);
				}
				
				$('#pastScope').val(0);
				$(".select2-container .select2-chosen:eq(2)").html("手动选择");
				
				$("#startTime").val(startTime);
				$("#endTime").val(endTime);
				
				$("#startTimeLi").show();
				$("#endTimeLi").show();
				if ($("#netid option:selected").val()==""){
                	top.$.jBox.tip('请选择查询网元','warning');
				}else if ($("#eventType option:selected").val()==""){
                    top.$.jBox.tip('请选择事件类型','warning');
                }else if ($("#startTime").val()==""){
                    $("#startTime").focus();
                    top.$.jBox.tip('请选择开始时间','warning');
                }else if ($("#endTime").val()==""){
                	$("#endTime").focus();
                	top.$.jBox.tip('请选择结束时间','warning');
                } else {
					queryTable();
				}
			}
			
			//ebmlog事件类型随网元id联动
			/* $("#netid").change(function(){
		   		netid = $(this).find("option:selected").val();
		   		$.post('${ctx}/performance/tTacSuccessRate/queryEbmEvent', {
			       "netid": netid
			    }, function(data) {
				    	var ebmEventList = eval(data);
				    	$("#eventType option:not(:first)").remove();  
				    	$(".select2-chosen").eq(1).html("请选择");
				    	$("#eventType").html("<option value=''>请选择</option>")
				    	$.each(ebmEventList, function(index, o) { 
				    		var html = "<option value='"+o.eventname+"'>"+o.eventfullname+"</option>";
						    $("#eventType").append(html);
						}); 
				    	$("#eventType option:first").prop("selected", 'selected'); 
				    });
		   	}); */
			 
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
					}else if ($("#eventType option:selected").val()==""){
	                    top.$.jBox.tip('请选择事件类型','warning');
	                }else if ($("#startTime").val()==""){
	                    $("#startTime").focus();
	                    top.$.jBox.tip('请选择开始时间','warning');
	                }else if ($("#endTime").val()==""){
	                	$("#endTime").focus();
	                	top.$.jBox.tip('请选择结束时间','warning');
	                }else{//表单提交
	                	if($("#searchForm").attr('action')=="/epc/a/performance/tEbmlogStatistics/exportExcel"){
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
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/performance/tEbmlogStatistics/queryList',
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
		        	 netid = $("#netid option:selected").val();
					 eventType = $("#eventType option:selected").val();
					 startTime = $("#startTime").val();
					 endTime = $("#endTime").val();
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   netid: netid,
				       eventType:eventType,
				       startTime:startTime,
				       endTime:endTime
		        	  };
		        },
		        onLoadSuccess: function (data) {
		        	$("img").each(function(){
						$(this).css("width", 40); 
						$(this).css("height", 20); 
					})
		        	//列表请求成功后加载图表
		        	$("#chart_div").children().remove();
		        	$("#chart_div2").children().remove();
		        	showChart(data.errorrows);
		        	showChart2(data.errorrows,data.successrows);
		            $("#chart_outside_div ul li").eq(1).html("<b>"+$('#eventType option:selected').text()+"EBMLOG原因值统计</b>")
		        	$("#chart_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>")
		        	$("#table_outside_div ul li").eq(1).html("<b>"+$('#eventType option:selected').text()+"EBMLOG原因值统计</b>")
		        	$("#table_outside_div ul li").eq(2).html("<b>统计时段："+startTime+"-"+endTime+"</b>")
		        },
		        onLoadError: function(){
		        	$("#chart_div").children().remove();
		        	$("#chart_div2").children().remove();
		        },
		        columns: [
				{
		            field : 'eventResult',
		            title : '事件结果',
		            align : 'center',
		            valign : 'middle'
				}, {
		            field: 'causecode',
                    title: 'CC(原因值)',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'cc',
		            title : '原因值描述',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	if(typeof(value)!="undefined"&&value.length>100){
		            		return value.substring(0,100)+"...";
		            	}else{
		            		return value;
		            	}
		            }
		        }, {
		            field : 'subcausecode',
		            title : 'SCC(子原因值)',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'scc',
		            title : '子原因值描述',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	if(typeof(value)!="undefined"&&value.length>100){
		            		return value.substring(0,100)+"...";
		            	}else{
		            		return value;
		            	}
		            }
		        }, {
		            field : 'failures',
		            title : '出现次数',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	var cc = row.causecode.split("(")[0];
		            	var scc = row.subcausecode.split("(")[0];
		            	var ccdes = row.cc;
		            	var sccdes = row.scc;
		            	var action = row.action;
		            	var domain = row.domain;
		            	var eventResult = row.eventResult;
		            	netid = $("#netid option:selected").val();
						eventType = $("#eventType option:selected").val();
						startTime = $("#startTime").val();
						endTime = $("#endTime").val();
						netid = '40';
						startTime = '2018-10-23 01:00:00';
						endTime = '2018-10-23 24:00:00';
						if(eventResult=="SUCCESS"){
							return value;
						}else{
			            	return value+'</br>'+'<a href="${ctx}/performance/tEbmlogStatistics/analysis?cc='+cc+'&scc='+scc+'&netid='+netid+'&startTime='+startTime+
	                        '&endTime='+endTime+'&eventType='+eventType+'&ccdes='+encodeURIComponent(ccdes)+'&sccdes='+encodeURIComponent(sccdes)+
	                        '&action='+action+'&domain='+domain+'&statisticType='+"value"+'" onclick="switchTab(2);"  target="ebmlogFrame" title="多维统计分析"><img src="${ctxStatic}/images/dimension_analysis_s.png" height="30px" width="40px"/></a>'
						}
		            }
		        /* }, {
		            field : 'reject',
		            title : 'REJECT次数',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	var cc = row.causecode.split("(")[0];
		            	var scc = row.subcausecode.split("(")[0];
		            	var ccdes = row.cc;
		            	var sccdes = row.scc;
		            	var action = row.action;
		            	var domain = row.domain;
		            	netid = $("#netid option:selected").val();
						eventType = $("#eventType option:selected").val();
						startTime = $("#startTime").val();
						endTime = $("#endTime").val();
						if(typeof(value)=="undefined"){
							return "-";
						}else if(value == "0"){
							return value;
						}else{
							return value+'</br>'+'<a href="${ctx}/performance/tEbmlogStatistics/analysis?cc='+cc+'&scc='+scc+'&netid='+netid+'&startTime='+startTime+
	                        '&endTime='+endTime+'&eventType='+eventType+'&ccdes='+encodeURIComponent(ccdes)+'&sccdes='+encodeURIComponent(sccdes)+
	                        '&action='+action+'&domain='+domain+'&statisticType='+"reject"+'" onclick="switchTab(2);"  target="ebmlogFrame" title="多维统计分析"><img src="${ctxStatic}/images/dimension_analysis_s.png" height="30px" width="40px"/></a>'
						}
		            }
		        }, {
		            field : 'abort',
		            title : 'ABORT次数',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	var cc = row.causecode.split("(")[0];
		            	var scc = row.subcausecode.split("(")[0];
		            	var ccdes = row.cc;
		            	var sccdes = row.scc;
		            	var action = row.action;
		            	var domain = row.domain;
		            	netid = $("#netid option:selected").val();
						eventType = $("#eventType option:selected").val();
						startTime = $("#startTime").val();
						endTime = $("#endTime").val();
						if(typeof(value)=="undefined"){
							return "-";
						}else if(value == "0"){
							return value;
						}else{
							return value+'</br>'+'<a href="${ctx}/performance/tEbmlogStatistics/analysis?cc='+cc+'&scc='+scc+'&netid='+netid+'&startTime='+startTime+
	                        '&endTime='+endTime+'&eventType='+eventType+'&ccdes='+encodeURIComponent(ccdes)+'&sccdes='+encodeURIComponent(sccdes)+
	                        '&action='+action+'&domain='+domain+'&statisticType='+"abort"+'" onclick="switchTab(2);"  target="ebmlogFrame" title="多维统计分析"><img src="${ctxStatic}/images/dimension_analysis_s.png" height="30px" width="40px"/></a>'
						}
		            }
		        }, {
		            field : 'ignore',
		            title : 'IGNORE次数',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	var cc = row.causecode.split("(")[0];
		            	var scc = row.subcausecode.split("(")[0];
		            	var ccdes = row.cc;
		            	var sccdes = row.scc;
		            	var action = row.action;
		            	var domain = row.domain;
		            	netid = $("#netid option:selected").val();
						eventType = $("#eventType option:selected").val();
						startTime = $("#startTime").val();
						endTime = $("#endTime").val();
						if(typeof(value)=="undefined"){
							return "-";
						}else if(value == "0"){
							return value;
						}else{
							return value+'</br>'+'<a href="${ctx}/performance/tEbmlogStatistics/analysis?cc='+cc+'&scc='+scc+'&netid='+netid+'&startTime='+startTime+
	                        '&endTime='+endTime+'&eventType='+eventType+'&ccdes='+encodeURIComponent(ccdes)+'&sccdes='+encodeURIComponent(sccdes)+
	                        '&action='+action+'&domain='+domain+'&statisticType='+"ignore"+'" onclick="switchTab(2);"  target="ebmlogFrame" title="多维统计分析"><img src="${ctxStatic}/images/dimension_analysis_s.png" height="30px" width="40px"/></a>'
						}
		            } */
		        }, {
		            field : 'domain',
		            title : '问题定界',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
                        var result = switchDomain(value);  
		          		return result;
		          	}
		       /*  }, {
		            field : 'failures',
		            title : '操作',
		            align : 'center',
		            width : '100',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	var cc = row.causecode;
		            	var scc = row.subcausecode;
		            	var ccdes = row.cc;
		            	var sccdes = row.scc;
		            	var action = row.action;
		            	var domain = row.domain;
		            	netid = $("#netid option:selected").val();
						eventType = $("#eventType option:selected").val();
						startTime = $("#startTime").val();
						endTime = $("#endTime").val();
						if(cc=="16"&&scc=="0"){
							return "";
						}else if(cc =="0"&&scc=="256"){
							return "";
						}else{
							return '<a href="${ctx}/performance/tEbmlogStatistics/analysis?cc='+cc+'&scc='+scc+'&netid='+netid+'&startTime='+startTime+
                            '&endTime='+endTime+'&eventType='+eventType+'&ccdes='+encodeURIComponent(ccdes)+'&sccdes='+encodeURIComponent(sccdes)+
                            '&action='+action+'&domain='+domain+'" onclick="switchTab(2);"  target="ebmlogFrame">多维统计分析</a>';
						}
		          	} */
		        }, {
		        	field : 'failures',
		            title : '案列库信息',
		            align : 'center',
		            valign : 'middle',
		            formatter:function(value,row,index){
		            	var cc = parseInt(row.causecode);
		            	var scc = parseInt(row.subcausecode);
		          		return '<a href="${ctx}/performance/tCaseLibrary/list?cc=' + cc + '&scc='+scc+'" onclick="switchTab(3);"  target="caseFrame">查看</a>';
		          	}
		        }]
		    });
		}
		
		function switchDomain(value){
			var result = value;
			if(value=="0"){
				result = "未知";
			}else if(value=="1"){
				result = "SGSM/MME 侧";
			}else if(value=="2"){
				result = "GGSN/SAEGW 侧";
			}else if(value=="3"){
				result = "HLR/HSS侧";
			}else if(value=="4"){
				result = "无线侧";
			}else if(value=="5"){
				result = "终端侧";
			}else if(value=="6"){
				result = "MSC侧";
			}else if(value=="7"){
				result = "DNS侧";
			}else if(value=="4,5"){
				result = "无线侧，终端侧";
			}else if(value=="1,7"){
				result = "SGSM/MME 侧，DNS侧";
			}else if(value=="1,3"){
				result = "SGSM/MME 侧， HLR/HSS侧";
			}
			return result;
		}
		
		function showChart(data){
			$('#chart_div').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		        	text: "" 
		        },
		        tooltip: {
		        	formatter: function() {
		        		/* var o = this.point.name.split("|")[0];
		        		var cc = o.split(" ")[0];
		        		var scc = o.split(" ")[1]; */
		        		return this.point.name.split("|")[0]+" "+this.point.name.split("|")[5]+' 所占比例:<b>'+this.point.percentage.toFixed(2)+'%</b>';
		        	} 
		        },
		        plotOptions: {
		            pie: {
                        size:250,
                        innerSize:'200',
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: 'gray',
		                    connectorColor: '#000000',
		                    formatter:function(){
		                    	return this.point.name.split("|")[0]+" "+this.point.name.split("|")[5]+' 所占比例:<b>'+this.point.percentage.toFixed(2)+'%</b>';
		                    }
		                },
		                events: {
				            click: function(e) {
				            	var o = e.point.name.split("|")[0];
				            	var cc = o.split(" ")[0].split(":")[1];
				            	var scc = o.split(" ")[1].split(":")[1];
				            	var ccdes = e.point.name.split("|")[3];
				            	var sccdes = e.point.name.split("|")[4];
				            	var action = e.point.name.split("|")[5];
				            	var domain = e.point.name.split("|")[6];
				            	netid = $("#netid option:selected").val();
								eventType = $("#eventType option:selected").val();
								startTime = $("#startTime").val();
								endTime = $("#endTime").val();
								netid = '40';
								startTime = '2018-10-23 01:00:00';
								endTime = '2018-10-23 24:00:00';
				window.frames['ebmlogFrame'].document.location	= '${ctx}/performance/tEbmlogStatistics/analysis?cc='+cc+'&scc='+scc+'&netid='+netid+'&startTime='+startTime+
	                               '&endTime='+endTime+'&eventType='+eventType+'&ccdes='+encodeURIComponent(ccdes)+'&sccdes='+encodeURIComponent(sccdes)+
	                               '&action='+action+'&domain='+domain;
								 switchTab(2);
				            	
						    }
						}
		            }
		        },
		        credits: {
                    enabled: false
                },
		        series: [{
	                type: 'pie',
	                name: '所占比例',
	            }]
		    });
			var chartList = eval(data);
	        var len = chartList.length;
	        var arr = new Array();
	        for(var i=0;i<len;i++){
	        	arr[i] = new Array();
	        	arr[i][0] = "CC:" + chartList[i].causecode + " SCC:" + chartList[i].subcausecode + 
	        			    "|" + chartList[i].cc + "|" + chartList[i].scc + "|" + chartList[i].action + "|" + chartList[i].domain+"|"+chartList[i].failures+"次";
	        	arr[i][1] = chartList[i].failures;
	        }
	        $('#chart_div').highcharts().series[0].setData(arr);
		}
		
		function showChart2(error,success){
			$('#chart_div2').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		        	text: "" 
		        },
		        tooltip: {
		        	pointFormat:'<b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
                        size:250,
                        innerSize:'200',
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: 'gray',
		                    connectorColor: '#000000',
		                    formatter:function(){
		                    	return this.point.name.split("|")[0]+' 所占比例:<b>'+this.point.percentage.toFixed(2)+'%</b>';
		                    }
		                }
		            }
		        },
		        credits: {
                    enabled: false
                },
		        series: [{
	                type: 'pie',
	                name: '所占比例',
	            }]
		    });
			var errorchartList = eval(error);
			var successchartList = eval(success);
			debugger;
			var reject_count = 0;
	        var abort_count = 0;
	        var ignore_count = 0;
	        var success_count = 0;
	        for(var i=0;i<errorchartList.length;i++){
	        	reject_count = reject_count+errorchartList[i].reject;
	        	abort_count = abort_count+errorchartList[i].abort;
	        	ignore_count = ignore_count+errorchartList[i].ignore;
	        }
	        for(var i=0;i<successchartList.length;i++){
	        	success_count = success_count+successchartList[i].failures;
	        }
	        var arr = [["SUCCESS次数:"+success_count,success_count],["REJECT次数:"+reject_count,reject_count],["ABORT次数:"+abort_count,abort_count],["IGNORE次数:"+ignore_count,ignore_count]];
	        $('#chart_div2').highcharts().series[0].setData(arr);
		}
		
		//自动切换至detail页
		function switchTab(n){
			if(n==2){
				$("#con_menu_2").show(); 
				$("#con_menu_1").hide();
				
				$("#menu1").removeClass("active"); 
				$("#menu2").addClass("active"); 
				
				
				//显示加载进度
				//获取浏览器页面可见高度和宽度
				var _PageHeight = document.documentElement.clientHeight,
				    _PageWidth = document.documentElement.clientWidth;
				//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
				var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
				    _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
				var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:0.8;alpha(opacity=60);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 25px; background: #1d1b1b url(${ctxStatic}/jerichotab/images/jbox-loading2.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #ffffff; font-family:\'Microsoft YaHei\';">页面加载中，请等待...</div></div>';
				//$('body').html($('body').html()+_LoadingHtml);
				var body = window.frames['ebmlogFrame'].document.body;
				$(body).html(_LoadingHtml);
			}
			if(n==3){
				$("#con_menu_3").show(); 
				$("#con_menu_1").hide();
				
				$("#menu1").removeClass("active"); 
				$("#menu3").addClass("active"); 
			}
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
		<li class="active" id="menu1" onclick="setTab('menu',1,3)"><a>SAEGW错误码多维分析</a></li>
		<li id="menu2" onclick="setTab('menu',2,3)"><a>多维统计分析结果</a></li>
		<li id="menu3" onclick="setTab('menu',3,3)"><a>案例库信息</a></li>
	</ul>
	<div id="con_menu_1" class="hover">
	<form:form id="searchForm" modelAttribute="tEbmlogStatistics" action="" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
		    <li>
		        <label>查询网元</label>
		        <form:select path="netid" class="input-xlarge" style="width:180px;" id="netid">
		            <form:option value="" label="请选择"/>
		            <c:forEach items="${poolNetList}" var="item">
		            	<optgroup label="${item.poolName}">
		            	<form:options items="${item.netList}" itemLabel="fname" itemValue="id" htmlEscape="false"/>
		            	</optgroup>
		            </c:forEach>
				</form:select>
			</li>
			<li>
			   <label>事件类型</label>
		       <form:select path="eventType" class="input-xlarge" style="width:190px;" id="eventType">
		             <form:option value="" label="请选择"/>
		             <form:options items="${ebmEventlist}" itemLabel="eventfullname" itemValue="eventname" htmlEscape="false"/>
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
				<form:input id="startTime" path="startTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
			</li> 
			<li id="endTimeLi">
				<label>结束时间</label>
				<form:input id="endTime" path="endTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'HH:mm:00',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="queryBtn" class="btn-new btn-search" value="查询" type="submit"/></li>
			<li class="btns"><input id="exportBtn" class="btn-new btn-save" value="导出" type="submit" onclick="searchForm.action='${ctx}/performance/tEbmlogStatistics/exportExcel';"/></li>
		</ul>
	</form:form>
	<div class="panel panel-default" id="chart_outside_div">
	   <div class="panel-heading" data-toggle="collapse" data-target="#table_chart_div">
	   <ul style="list-style:none;height:28px;">
	      <li style="float:left;line-height:38px"><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><%-- <img src='${ctxStatic}/images/p_down.png' height='10' width='10' style="float:left;margin-top:15px;"/> --%><span class="icon-h" class="panel-title" style="font-size: 30px; float:left; margin-top: 7px;margin-left:10px;"></span><p class="panel-title" style="font-size: 14px; float:left; margin-left: 15px;">图表模式</p></li>
	      <li style="float:left;margin-left:25%;line-height:38px"></li>
	      <li style="float:right;margin-right:0px;line-height:38px;"></li>
	   </ul>
	   </div>
	   <div class="panel-body" id="chart_panel">
	    <div id="table_chart_div">
	   		<table style="margin: 0 auto;">
	           <tr>
	           <td>
	           <div id="chart_div" >
			</div>
	           </td>
	           <td>
	           <div id="chart_div2" >
			</div>
	           </td>
	           </tr>
	        </table>
	     </div>
	   </div>
	</div>
	<div class="panel panel-default" id="table_outside_div">
	   <div class="panel-heading" data-toggle="collapse" data-target="#table_div">
	   <ul style="list-style:none;height:28px;">
	      <li style="float:left;line-height:38px"><span class="icon-down" style="font-size: 12px;float:left;margin-top:10px;"></span><%-- <img src='${ctxStatic}/images/p_down.png' height='10' width='10' style="float:left;margin-top:15px;"/> --%><span class="icon-q" class="panel-title" style="font-size: 30px; float:left; margin-top: 7px;margin-left:10px;"></span><p class="panel-title" style="font-size: 14px; float:left; margin-left: 15px;">列表模式</p></li>
	      <li style="float:left;margin-left:25%;line-height:38px"></li>
	      <li style="float:right;margin-right:0px;line-height:38px"></li>
	   </ul>
	   </div>
	   <div class="panel-body">
	   		<div id="table_div">
		   		<table id="table">
		        </table>
			</div>
	   </div>
	</div>
	</div>
	<div id="con_menu_2" style="display:none">
		<iframe id="ebmlogFrame" name="ebmlogFrame" scrolling="yes" frameborder="0" style="width:100%;height:600px;"></iframe> 
	</div>
	<div id="con_menu_3" style="display:none">
		<iframe id="caseFrame" name="caseFrame" scrolling="yes" frameborder="0" style="width:100%;height:600px;"></iframe> 
	</div>
</body>
</html>