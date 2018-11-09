<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户话单查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
	</script>
</head>
<body>
  <table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<td>PCRF网元/MME网元/SAEGW网元</td>
				<td title="${capAllPath.netNames}">${fns:abbr(capAllPath.netNames,30)}</td>
			</tr>
			<tr>
				<td>MSISDN</td>
				<td>${capAllPath.msisdn}</td>
			</tr>
			<tr>
				<td>抓包开始时间</td>
				<td>${capAllPath.startTime}</td>
			</tr>
			<tr>
				<td>抓包结束时间</td>
				<td>${capAllPath.endTime}</td>
			</tr>
			<tr>
				<td colspan="2">
				<div align="center">
				    <c:if test="${tCapUserfacePath.pcappath!='0'}">
					   <a href="javascript:void(0);" style="cursor:hand" onclick="downloadCap('${tCapUserfacePath.pcappath}')">用户面抓包文件下载</a>
				       &nbsp;&nbsp;&nbsp;&nbsp;
				    </c:if>
					<a href="javascript:void(0);" style="cursor: hand" onclick="downloadCap('${capAllPath.capPath}')">抓包文件下载</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0);" style="cursor: hand" onclick="showhtml('${capAllPath.htmlPath}')">查看流程图</a>
				</div>
				</td>
			</tr>
	</table>
</body>
</html>
