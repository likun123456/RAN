<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户信令追踪历史查询管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	   /*
	   * 查看流程图  
	   */
	   function showhtml(path){
	      openNewWindow(path,1200,800);
	   } 
	   /*
	   * 下载cap包
	   */
	   function downloadCap(path){
		   window.location.href= "${ctx}/userquery/signal/downloadCap.do?path=" + path;
	   }
	   /*
	   * PCRF二次滤包
	   */
	   function queryPcrfInfo(id){
		   top.$.jBox.open('iframe:${ctx}/userquery/tCapAllPath/pcrfList?id='+id,'PCRF网元抓包明细',$(top.document).width()-800,$(top.document).height()-700,{
				buttons:{"关闭":true},
				loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
					$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
					$("body", h.find("iframe").contents()).css("margin","10px");
				}
			});
	   }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/userquery/tCapAllPath/">单用户信令追踪历史</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tCapAllPath" action="${ctx}/userquery/tCapAllPath/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>MSISDN:</label>
				<form:input path="msisdn" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>PCRF网元/MME网元/SAEGW网元</th>
		    	<th>MSISDN</th>
		    	<th>抓包开始时间</th>
		    	<th>抓包结束时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="t">
			<tr>
			    <td title="${t.netNames}">${fns:abbr(t.netNames,30)}</td>
			    <td>${t.msisdn}</td>
			    <td>${t.startTime}</td>
			    <td>${t.endTime}</td>
				<td width="30%">
    				<a href="javascript:void(0);" style="cursor: hand;" onclick="downloadCap('${t.capPath}')">抓包下载</a>
    				&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" style="cursor: hand;" onclick="showhtml('${t.htmlPath}')">查看流程图</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<c:choose>
					   <c:when test="${fn:contains(t.netNames,'PCRF')}">
					       <a href="javascript:void(0);" style="cursor: hand;" onclick="queryPcrfInfo('${t.id}')">PCRF二次滤包</a>
					   </c:when>
					   <c:otherwise>    
     				   </c:otherwise> 
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>