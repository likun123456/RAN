<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>PCRF网元抓包详细日志</title>
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
	        <span class="brand" style="padding-left:40px;font-size:14px;">PCRF网元抓包详细日志</span>
	      </div>
	  </div>
	  <div style="margin-top:60px;">
		  <c:forEach items="${map}" var="m">
		    <div style="float:left;margin-left:20px;width:40%;">
			    <table class="table table-striped table-hover table-bordered table-condensed" >
			        <caption align="top" >${m.key}</caption>
					<tbody>
					<c:forEach items="${m.value.pcrfLogList}" var="log">
					   <tr>
						  <td><label>${log}</label></td>
					   </tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		  </c:forEach>	
	 </div>
</body>
</html>