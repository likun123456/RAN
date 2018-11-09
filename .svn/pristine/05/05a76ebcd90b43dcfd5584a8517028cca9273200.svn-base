<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
window.onload=setInterval(function() {
	mainIndex();
 	}, 300000);
	function queryChart(){
		document.forms[0].action = "${ctx}/performance/index/netelementChart?poolName=${poolName}&datetime=${datetime}";
		document.forms[0].submit();
	}
	function mainIndex(){
		document.forms[0].action = "${ctx}/performance/index/netelementIndex?poolName=${poolName}&datetime=${datetime}";
		document.forms[0].submit();
	}
	function main(){
		document.forms[0].action = "${ctx}/performance/index/mainIndex";
		document.forms[0].submit();
	}
	function openMultipleIndexWindow(kpi,netName,datetime){
		window.location.href="${ctx}/performance/multipleIndex/chart?kpi="+kpi+"&netName="+netName+"&datetime="+datetime;
	}
</script>
</head>
<body>
<form:form id="mainIndex" method="post">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-c" style="font-size: 45px; float:left;margin-left:10px;" onclick="main()"></span>
			<a class="brand" style="font-size: 18px; margin-left: 20px;"  href="${ctx}/performance/index/mainIndex">${poolName}池组指标</a>
			<div style="float: right" class="form-inline">
				<a class="brand" style="font-size: 15px;">粒度:网元</a>
				<span id="queryTimeScope" style="padding: 15px;"></span> <span
					class="icon-5"
					style="font-size: 30px; margin-top: -5px; cursor: pointer; margin-right: 15px"
					onclick="mainIndex()"></span>
				<p
					style="cursor: pointer; display: inline-block; font-size: 15px; margin-right: 10px; margin-top: 10px"
					onclick="mainIndex()">表盘模式</p>
				<span class="icon-h"
					style="font-size: 30px; margin-top: -5px; cursor: pointer; margin-right: 15px"
					onclick="queryChart()"></span>
				<p
					style="cursor: pointer; display: inline-block; font-size: 15px; margin-right: 10px; margin-top: 10px"
					onclick="queryChart()">图表模式</p>
			</div>
			<div style="width: 100%;height:350px;overflow-y:scroll;">
			<table style="width: 100%;height:100%">
				<tr>
					<c:forEach items="${mainData}" var="entry" varStatus="status">
						<c:if test="${status.index%2 == 0 and status.index!=0}">
							<tr>
							</tr>
						</c:if>
						<td style="width: 50%;">
							<table style="width: 99%; height: 100px">
								<tr style="background: #F5F5F5">
									<td class="navbar-inner-main" style="background-color: #eeeeee"><span style="float: left;"><a
											style="font-size: 12px;" >&nbsp;&nbsp;${entry.key}网元指标</a></span> <span
										style="float: right;"><a
											style="font-size: 12px; margin-right: 10px">统计时段：${datetime}</a></span>
									</td>
								</tr>
								<tr style="background: white">
									<td style="background: white">
									<c:forEach items="${entry.value}" var="comment">
									<a href="javascript:void(0);" style="cursor: pointer;" onclick="openMultipleIndexWindow('${comment.kpi}','${entry.key}','${datetime}')">
										<div style="float:left;width:16%;margin-left:3px;text-align:center;height: 100px">
										<span class="${comment.iconName}" style="font-size: 40px; cursor: pointer;"></span>
										 <br>
										 <br>
											<c:choose>
												<c:when test="${fn:startsWith(comment.countertype, 0)}">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(comment.result,0,5)}%
												</c:when>
												<c:when test="${fn:endsWith(comment.result,'.00')}">
													&nbsp;&nbsp;&nbsp;&nbsp;<span class="spa2" style="font-size: 9px;font-weight:bold;">${fn:replace(comment.result, ".00","")}</span>
												</c:when>
												<c:otherwise>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="spa2" style="font-size: 9px;font-weight:bold;">${fn:replace(comment.result, ".00","")}</span>
												</c:otherwise>
											</c:choose> 
										 <br> 
											<c:choose>
												<c:when test="${fn:length(comment.kpiName) >= 8}">
												<div style="margin-top:5px;line-height:0px">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="spa2" style="font-size: 8px;font-weight:bold;line-height:14px"  title="${comment.kpiName}">${fn:substring(comment.kpiName,0,8)}</span>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="spa2" style="font-size: 8px;font-weight:bold;line-height:10px"  title="${comment.kpiName}">${fn:substring(comment.kpiName,8,fn:length(comment.kpiName))}</span>
												</div>
												</c:when>
												<c:otherwise>
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="spa2" style="font-size: 8px;font-weight:bold;" title="${comment.kpiName}">${comment.kpiName}</span>
												</c:otherwise>
											</c:choose>  
										</div>
									</c:forEach>
									</td>
								</tr>
							</table>
						</td>
					</c:forEach>
				</tr>
			</table>
			</div>
		</div>
	</div>
</form:form>
</body>
</html>