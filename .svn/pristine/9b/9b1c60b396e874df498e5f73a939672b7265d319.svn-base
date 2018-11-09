<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>SAEGW池组抓包详细日志</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
<div >
<!-- 	  <div class="navbar navbar-default navbar-fixed-top">
	      <div class="navbar-inner">
	        <span class="brand" style="padding-left:40px;font-size:14px;">巡检详细日志</span>
	      </div>
	  </div>
 -->	  <div>
		  <div style="float:left;overflow-y :auto;width:100%">
		    <table class="table table-striped table-hover table-bordered table-condensed" >
				<tbody>
				<c:forEach items="${headlist}" var="log">
				   <tr>
					  <td><label style = "font-weight:bold;color: red">${log}</label></td>
				   </tr>
				</c:forEach>
				<c:forEach items="${bodylist}" var="log">
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