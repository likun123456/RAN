<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户话单详情</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel:5}).show();
			$("#treeBox").height(document.documentElement.clientHeight);
		});
		function getPrev(index){
			if(index == 0){
				top.$.jBox.tip('已经是第一条','warning');
				return false;
			}
			location = '${ctx}/userquery/cdr/goOther?index='+(index-1);
		}
		function getNext(index,length){
			if(index+1 == length){
				top.$.jBox.tip('已经是最后一条','warning');
				return false;
			}
			location = '${ctx}/userquery/cdr/goOther?index='+(index+1);
		}
	</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
     <div class="navbar-inner">
        <span class="icon-Q" style="font-size: 45px; float:left;margin-left:10px;"></span>
        <span class="brand" style="margin-left:20px;">话单详细信息</span>
        <div class="pull-right" style="padding-right:20px;padding-top:11px;">
	        <span id="numAndTime" style="margin-right:20px;">用户MSISDN:&nbsp;&nbsp;${userCdrDetail.servedmsisdn}&nbsp;&nbsp;话单开始时间:&nbsp;&nbsp;${userCdrDetail.recordopeningtime}</span>
			<input id="btnPrev" class="btn-new btn-search" type="button" value="上一条" 
			    onclick="getPrev(${userCdrDetail.index})"/>
			<input id="btnNext" class="btn-new btn-search" type="button" value="下一条" 
			    onclick="getNext(${userCdrDetail.index},${userCdrDetail.length})"/>   
		</div>
      </div>
    </div>
    <div id="treeBox" style="margin-top:55px;overflow-y:scroll;">
    <table id="treeTable" class="table table-striped table-bordered table-condensed"  style="margin-bottom:55px;">
		<tbody><c:forEach items="${userCdrDetail.cdrDetail}" var="cdrDetail">
			<tr id="${cdrDetail.id}" pId="${cdrDetail.pid}">
				<td>${cdrDetail.name}</td>
				<td>${fns:getDictLabel(cdrDetail.name, 'biz_cdr_tree_node', cdrDetail.name)}</td>
				<td title="${cdrDetail.value}">${fns:abbr(cdrDetail.value,100)}</td>
			</tr>
		</c:forEach></tbody>
	</table>
	</div>
	
</body>
</html>
