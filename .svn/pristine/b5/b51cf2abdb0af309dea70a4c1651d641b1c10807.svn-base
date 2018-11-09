<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欺诈用户评估表</title>
	<meta name="decorator" content="blank"/>
	<script type="text/javascript">
		$(document).ready(function() {
			queryTable();			
		});
		
		function queryTable(){
			$('#table').bootstrapTable("destroy");
			$('#table').bootstrapTable({
				url:'${ctx}/cheat/cheatUserAssess/showCheatlog',
		        method:'POST',
		        dataType:'json',
		        cache:false,
		        contentType : "application/x-www-form-urlencoded",
		        striped: true,                      //是否显示行间隔色
		        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		        pagination:true,
		        minimumCountColumns:2,
		        pageNumber:1,                       //初始化加载第一页，默认第一页
		        pageSize: 200,                 //每页的记录行数（*）
		        pageList: [200],   //可供选择的每页的行数（*）
		        queryParams:  function(params) {
					 var netId='${netId}'; 
					 var servedIMSI='${servedIMSI}'; 
					 var startTime='${startTime}';
					 var endTime='${endTime}';
					 var cheatCase='${cheatCase}';
		        	 return {
		        	   offset: params.offset,
		        	   limit: params.limit,
		        	   netId: netId,
		        	   servedIMSI: servedIMSI,
				       startTime:startTime,
				       endTime:endTime,
				       cheatCase:cheatCase
		        	  };
		        },
		        columns: [ 
		         {
		            field : 'tempstamp',
		            title : '时间戳',
		            align : 'center',
		            valign : 'middle'
		        },
		        {
		            field: 'ip',
                    title: '用户私网IP地址',
                    align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'cheatCase',
		            title : '欺诈类型',
		            align : 'center',
		            valign : 'middle'
		        }, {
		            field : 'cheatNote',
		            title : '欺诈日志',
		            align : 'center',
		            valign : 'middle'
		        }]
		    });
		}
		
		function download(servedIMSI,startTime,endTime,netId) {
			window.location.href= "${ctx}/cheat/cheatUserAssess/downLoadZip?servedIMSI=" + servedIMSI + "&startTime=" + startTime + "&endTime=" + endTime + "&netId=" + netId;
		}
	</script>
</head>
<body>
	<div style="padding: 8px 15px;
			    margin: 0 0 20px;
			    background-color: #f5f5f5;
			    -webkit-border-radius: 4px;
			    -moz-border-radius: 4px;
			    border-radius: 4px;
			    font-size: 18px;
			    font-weight: bold;
			    text-align: center;">
		  用户(IMSI ${servedIMSI})流量欺诈分析报告
	</div>
	<div style="float: right;">
		<input type="button" value="抓包下载" onClick="download('${servedIMSI}','${startTime}','${endTime}','${netId}')">
	</div>
	 
	 <table id="table">
     </table>
</body>
</html>