<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自动巡检配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		
	</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-j" style="font-size: 45px; float:left;margin-left:10px;"></span>
			<a class="brand" style="font-size: 15px; margin-left: 30px;">自动巡检门限配置</a>
		</div>
		<form:form id="inputForm" modelAttribute="paramCollectConfig" action="${ctx}/autocheck/tAutoCheckConfig/save" method="post" class="breadcrumb form-search">
		<div  style="height:410px;overflow-y:scroll;margin-left:30px;">
			<sys:message content="${message}"/>	
			<div style="margin-top: 30px">
			<label><strong>巡检时长配置</strong></label>
			<form:select path="collecttime" items="${fns:getDictList('biz_autocheck_interval')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-medium" style="margin-left:26px;width:120px;"/>
			</div>
			</br>
			&nbsp;&nbsp;<label><strong>MME门限配置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="50px">
				<tr>
					<td align="center" width="100px">
						<label><strong>CPU利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[0].id" value="${data.mme[0].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[0].itemLimit" value="${data.mme[0].itemLimit }">
					</td>
					<td align="center" width="100px">
						<label style="width: 120px"><strong>磁盘空间利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[1].id" value="${data.mme[1].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[1].itemLimit" value="${data.mme[1].itemLimit }">
					</td>
					<td align="center" width="100px">
						<label><strong>内存利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[2].id" value="${data.mme[2].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[2].itemLimit" value="${data.mme[2].itemLimit }">
					</td>
				</tr>
			</table>
			</div>
			
			</br>
			&nbsp;&nbsp;<label><strong>SAEGW门限配置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="50px">
				<tr>
					<td align="center" width="100px">
						<label><strong>CPU利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[3].id" value="${data.saegw[0].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[3].itemLimit" value="${data.saegw[0].itemLimit }">
					</td>
					<td align="center" width="100px">
						<label style="width: 120px"><strong>磁盘空间利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[4].id" value="${data.saegw[1].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[4].itemLimit" value="${data.saegw[1].itemLimit }">
					</td>
					<td align="center" width="100px">
						<label><strong>内存利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[5].id" value="${data.saegw[2].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[5].itemLimit" value="${data.saegw[2].itemLimit }">
					</td>
				</tr>
			</table>
			</div>
			
			</br>
			&nbsp;&nbsp;<label><strong>PCRF门限配置</strong></label>
			</br>
			<div style="border: 1px solid;width:95%;">
			<table align="center" width="100%" height="50px">
				<tr>
					<td align="center" width="100px">
						<label><strong>CPU利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[6].id" value="${data.pcrf[0].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[6].itemLimit" value="${data.pcrf[0].itemLimit }">
					</td>
					<td align="center" width="100px">
						<label style="width: 120px"><strong>磁盘空间利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[7].id" value="${data.pcrf[1].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[7].itemLimit" value="${data.pcrf[1].itemLimit }">
					</td>
					<td align="center" width="100px">
						<label><strong>内存利用率</strong></label>
					</td>
					<td>
						<input type="hidden" name="autoCheckConfig[8].id" value="${data.pcrf[2].id }">
						<input type="text" style="width: 100px" name="autoCheckConfig[8].itemLimit" value="${data.pcrf[2].itemLimit }">
					</td>
				</tr>
			</table>
			</div>
			
			<div>
			<input id="saveBtn" class="btn" type="submit" value="保存" />
			</div>
			</form:form>
	</div>
</body>
</html>