<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>kpi计算结果详情</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/easyui/themes/icon.css" />
	<style type="text/css">
		#newtable tr td {
			text-overflow:ellipsis;
			-moz-text-overflow:ellipsis;
			overflow:hidden;
			white-space:nowrap;
			
		}
		#oldtable tr td {
			text-overflow:ellipsis;
			-moz-text-overflow:ellipsis;
			overflow:hidden;
			white-space:nowrap;
			
		}
		#base{
			width:1175px;
			table-layout:fixed; 
            word-break: break-all; 
            word-wrap: break-word; 
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			queryTable();
			$('#newPanel').panel({
			    width:1175,
			    collapsible:true,
			    
			    title:'本次值'
			}); 
			$('#oldPanel').panel({
			    width:1175,
			    collapsed:true,
			    collapsible:true,
			    title:'上一次值'
			}); 
		});
		
		//按数字类型排序 自定义排序
		function sort_int(a,b){  
			if(parseFloat(a)){
				a = parseFloat(a);
			}
			if(parseFloat(b)){
				b = parseFloat(b);
			}
			return (a>b?1:-1);
		}  
		//客户端分页
		function pagerFilter(data){
           if (typeof data.length == 'number' && typeof data.splice == 'function'){    // 判断数据是否是数组
               data = {
                   total: data.length,
                   rows: data
               }
           }
           var dg = $(this);
           var opts = dg.datagrid('options');
           var pager = dg.datagrid('getPager');
           pager.pagination({
               onSelectPage:function(pageNum, pageSize){
                   opts.pageNumber = pageNum;
                   opts.pageSize = pageSize;
                   pager.pagination('refresh',{
                       pageNumber:pageNum,
                       pageSize:pageSize
                   });
                   dg.datagrid('loadData',data);
               }
           });
           if (!data.originalRows){
               data.originalRows = (data.rows);
           }
           var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
           var end = start + parseInt(opts.pageSize);
           data.rows = (data.originalRows.slice(start, end));
           return data;
       }
  
		
		function queryTable(){
	        $.post('${ctx}/performance/multipleIndex/getDynamicColumns', {
	 		       'netType': $('#netType').val(),
	 		       'netId': $('#netId').val(),
	 		       'formulaId': $('#formulaId').val(),
	 		       'recordTime': $('#recordTime').val()
	 		 }, function(data) {
	 	    	var dynamicColumns = eval(data);
	 	    	
	 	    	
	 	    	var frozenColumns = [[ 
	 		    	{
	 		            field: 'OBJTYPE',
	 	                title: 'OBJTYPE',
	 	                align : 'center',
	 	                width : 200,
	 		            valign : 'middle',
	 		            formatter:function(value,row,index){
	 		            	return '<span title="'+row.OBJTYPE+'">' + row.OBJTYPE + '</span>';
	 		            }
	 		        }, {
	 		            field : 'INST',
	 		            title : 'INST',
	 		            align : 'center',
	 		            width : 200,
	 		            valign : 'middle',
	 		            formatter:function(value,row,index){
	 		            	return '<span title="'+row.INST+'">' + row.INST + '</span>';
	 		            }
	 		        }]];
				
	 	    	var columns = new Array();
				$.each(dynamicColumns, function (i, item) {
					  if(item !='OBJTYPE' && item !='INST' && item !='flag'){
						  columns.push({ "field": item, "title": item, "width": 180, "align" : "center","valign" : "middle", "sorter":sort_int ,"sortable": true});
					  }
					  
					});
				
	 	        var cols = new Array(columns);
	 	    	$('#newtable').datagrid({
	 	    		url : "${ctx}/performance/multipleIndex/queryCurrentTable",
	 				width:'100%',
	 				queryParams: { 
	 					'netType': $('#netType').val(),
	 	 		        'netId': $('#netId').val(),
	 	 		        'formulaId': $('#formulaId').val(),
	 	 		        'recordTime': $('#recordTime').val()
	 				},
	 				remoteSort:false,
	 				pagination : false,
	 				//loadFilter:pagerFilter,
	 				//pageSize : 20,
					//pageList : [20, 40 ,80],
					//pageNumber : 1,
					//collapsible:true,
					frozenColumns:frozenColumns,
					rownumbers:true,
	                columns:cols,
	                
	                rowStyler:function(index,row){    
		    	        if (row.flag == 1){    
		    	            return 'color:red;';    
		    	        }    
		    	    }
	                
	 	    	});
	               
	 	    	
	 	    	    $('#oldtable').datagrid({
		 	    		url : "${ctx}/performance/multipleIndex/queryLastTable",
		 				width:'100%',
		 				queryParams: { 
		 					'netType': $('#netType').val(),
		 	 		        'netId': $('#netId').val(),
		 	 		        'formulaId': $('#formulaId').val(),
		 	 		        'recordTime': $('#recordTime').val()
	 				},
	 				remoteSort:false,
	 				pagination : false,
	 				//loadFilter:pagerFilter,
	 				//pageSize : 20,
					//pageList : [20, 40 ,80],
					//pageNumber : 1,
					frozenColumns:frozenColumns,
					rownumbers:true,
	                columns:cols,
	                
	                rowStyler:function(index,row){    
		    	        if (row.flag == 1){    
		    	            return 'color:red;';    
		    	        }    
		    	    }
	                
	             }); 
	 		});
		}
	</script>
</head>
<body>
	<input id="netType" name="netType" type="hidden" value="${KpiDetail.netType}"/>
	<input id="netId" name="netId" type="hidden" value="${KpiDetail.netId}"/>
	<input id="formulaId" name="formulaId" type="hidden" value="${KpiDetail.formulaId}"/>
	<input id="recordTime" name="recordTime" type="hidden" value="${KpiDetail.recordTime}"/>
	<div style="padding:10px;">
		<table id="base" class="table table-striped table-bordered table-condensed">
			<tr>
				<th>网元名称</th>
				<th>公式名称</th>
				<th>开始时间</th>
				<th>采集间隔</th>
			</tr>
			<tr>
				<td width="400px">${KpiDetail.netName}</td>
				<td width="400px">${KpiDetail.formulaName}</td>
				<td width="200px">${KpiDetail.beginTime}</td>
				<td>${KpiDetail.period}</td>
			</tr>
			<tr>
				<th>公式</th>
				<th>公式带入明细</th>
				<th>指标值</th>
				<th>记录时间</th>
			</tr>
			<tr>
				<td width="400px">${KpiDetail.formulaText}</td>
				<td width="400px">${KpiDetail.resultText}</td>
				<td width="200px">${KpiDetail.result}</td>
				<td>${KpiDetail.recordTime}</td>
			</tr>
		</table>
			
	
		<div id="oldPanel">
		<table id="oldtable"></table>
		</div>
		<div id="newPanel">
			<table id="newtable"></table>
		</div>
	</div>
	
	
</body>
</html>
