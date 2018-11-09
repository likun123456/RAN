<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>详细日志</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
<div >
	  <div class="navbar navbar-default navbar-fixed-top">
	      <div class="navbar-inner">
	        <span class="brand" style="padding-left:40px;font-size:14px;">MME详细信息</span>
	      </div>
	  </div>
	  <div style="margin-top:60px;">
		  <div style="float:left;overflow-y :auto;height:500px;width:100%">
		    <table class="table table-striped table-hover table-bordered table-condensed" >
				<tbody>
				<c:forEach items="${dl.mmeLogList}" var="log">
				   <tr>
					  <td><label>${log}</label></td>
				   </tr>
				</c:forEach>
				</tbody>
			</table>
		 </div>
	 </div>
</body>
</html>