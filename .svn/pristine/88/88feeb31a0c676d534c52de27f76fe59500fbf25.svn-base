<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户聚合查询</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
		$(document).ready(function() {
			
		});
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
<div class="container">

<ul class="nav nav-tabs">
	<c:forEach items="${data}" var="item" varStatus="i">
		<c:choose>
			<c:when test="${i.index == 0}">
				<li id="menu${i.index + 1}" class="active" onclick="setTab('menu',${i.index + 1},${count})"><a>${item.key}</a></li>
			</c:when>
			<c:otherwise>
				<li id="menu${i.index + 1}" onclick="setTab('menu',${i.index + 1},${count})"><a>${item.key}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<!-- <li class="active" id="menu1" onclick="setTab('menu',1,3)"><a>单用户话单查询</a></li>
	<li id="menu2" onclick="setTab('menu',2,3)"><a>话单详细信息</a></li>
	<li id="menu3" onclick="setTab('menu',3,3)"><a>1111</a></li> -->
</ul>
<c:forEach items="${data}" var="item" varStatus="i">
	<c:choose>
		<c:when test="${i.index == 0}">
			<div id="con_menu_${i.index + 1}" class="hover"> 		
		</c:when>
		<c:otherwise>
			<div id="con_menu_${i.index + 1}" style="display:none"> 	
		</c:otherwise>
	</c:choose>
		<table class="table table-striped table-bordered table-condensed" style="font-family: Lucida Console;letter-spacing:0px;">
			<tbody>
				<c:forEach items="${item.value}" var="d">
					<tr  height="20px">
						<td style="padding: 0px; border: 0px solid #ddd;">
							${d}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</c:forEach>
</div>
</body>
</html>