<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户聚合查询</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.main {
			width: 99%;
		}
		.maintitle {
			float:left;
			width: 100%;
			height:35px;
			border: 1px solid #f4f4f4;
			border-radius: 5px 5px 0 0;
			background-image: -webkit-linear-gradient(top,#fefefe,#f6f6f6);
			background-image: -ms-linear-gradient(top,#fefefe,#f6f6f6);
		}
		.maintitle p {
			float:left;
			margin-left:20px;
			margin-top:7px;
			font-size: 15px;
			color:#000;
			font-weight: bold;
			font-family: 微软雅黑;
		}
		.mainbody {
			float:left;
			width: 100%;
			border: 1px solid #f4f4f4;
		}
		.mainbody table{
			float:left;
			width: 100%;
			color:#000;
			font-size: 10px;
		}
		.mainbody table tr {
			background-image: -webkit-linear-gradient(top,#f8f8f8,#f0f0f0);
			background-image: -ms-linear-gradient(top,#f8f8f8,#f0f0f0);
		}
		.mainbody table tr td{
			width: 50%;
			height: 100%;
			vertical-align: top;
			margin-top: 10px;
		}
		.mainbody table tr td div{
			float: left;
			margin-bottom:8px;
			width: 50%;
			min-height:70px;
		}
		.xian {
			background:url('${ctxStatic}/jerichotab/images/xian.png') bottom right no-repeat;
			background-size:1px 80%;
		}
		.icon {
			float:left;
			font-size: 50px; 
			margin-top: 8px; 
			margin-left: 3px;
		}
		.ls {
			float: left;
			width:73%;
			margin-left: 40px;
			margin-top:10px;
			text-align:left;
		}
		.currentbtn {
			float: left; 
			margin-top: 20px;
			font-family: 微软雅黑;
		}
	</style>
	<script type="text/javascript">
			
		$(document).ready(function() {
			parent.$('#jbox-states').css("display","none");
			$("#pastScope").change(function(){
				//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
				$("#timeScope").hide();
				if(this.value==""){
					$("#beginDate").val("");
					$("#endDate").val("");
					return false;
				}
				if(this.value=="0"){
					$("#beginDate").val("");
					$("#endDate").val("");
					$("#timeScope").show();
					return false;
				}
			});
			
			$("#pastScope1").change(function(){
				//获取选择的值，计算开始时间点及当前时间点（整点），然后将值传递给日期控件
				debugger;
				$("#timeScope1").hide();
				if(this.value==""){
					$("#beginDate1").val("");
					$("#endDate1").val("");
					return false;
				}
				if(this.value=="0"){
					$("#beginDate1").val("");
					$("#endDate1").val("");
					$("#timeScope1").show();
					return false;
				}
			});
		})
		
		function showMMEDig() {
			top.$.jBox.open("iframe:${ctx}/userquery/userQuery/showmme?type=mme", "查看MME侧用户详细信息",1000,500,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function showPCRFDig() {
			var searchType = $('#searchType').val();
			var searchValue = $('#searchValue').val();
			top.$.jBox.open("iframe:${ctx}/userquery/userQuery/showpcrf", "查看PCRF侧用户详细信息",1000,500,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function showsgw(sgw) {
			var imsi = $("#imsi").val();
			top.$.jBox.open("iframe:${ctx}/userquery/userQuery/showsgw?sgw="+sgw+"&imsi="+imsi, "查看SGW侧用户详细信息",1000,500,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function showpgw(pgw) {
			var imsi = $("#imsi").val();
			top.$.jBox.open("iframe:${ctx}/userquery/userQuery/showpgw?pgw="+pgw+"&imsi="+imsi, "查看PGW侧用户详细信息",1000,500,{
				buttons:{"关闭":true}, bottomText:"",submit:function(v, h, f){
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
	</script>
</head>
<body>
	<div class="main">
		<div class="maintitle">
			<p>单用户聚合查询</p>
			<p style="float: right;margin-right: 50px;line-height: 28px"><a href="javascript:" onclick="history.go(-1);"><span class="icon-back" style="font-size: 24px;font-weight: bold;float: left;"></span><span style="padding-left: 15px;float: left;margin-top: -2px;">返回</span></a></p>
		</div>
		<div class="mainbody">
			<table>
				<tr>
					<td class="xian">
						<div class="xian">
							<span class="icon-d icon"></span>
							<span class="ls">
								用户MSISDN:&nbsp;8615805420099</br>
								用户IMSI:&nbsp;&nbsp; 460023639700000
								<input type="hidden" id="imsi" value="460023639700000" />
							</span>
						</div>
						<div>
							<span class="icon-o icon"></span>
							<span class="ls">
								<%-- 终端品牌:&nbsp;${dataMap.imei}<br> --%>
								终端型号:China Mobile Group Device</br>Co Ltd N1<%-- <span title="${dataMap.imei}">&nbsp;${fns:abbr(dataMap.imei,18)}</span> --%>
							</span>
						</div>
					</td>
					<td>
						<div class="xian">
							<span class="icon-9 icon"></span>
							<span class="ls">
								<span style="float: left">用户状态:</span><span style="float: left; margin-left: 4px;">EMM-REGISTERED</br>ECM-CONNECTED</span><%-- <span title="${dataMap.state}">&nbsp;${fns:abbr(dataMap.state,18)}</span> --%>
							</span>
						</div>
						<div>
							<span class="icon-a1 icon"></span>
							<span class="ls">
								用户属性: VOLTE 用户<br>
								用户当前NAM信息 : </br>Information not available<%-- <span title="${dataMap.nam}">&nbsp;${fns:abbr(dataMap.nam,10)}</span> --%>
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="xian">
						<div>
							<span class="icon-a2 icon"></span>
							<span class="ls" style="margin-top: 22px">
								用户所在MME:XXMME07BER
							</span>
						</div>
						<div>
							<input id="btnSubmit" class="btn-new btn-search currentbtn" type="button" value="查看MME侧用户详细信息" onclick="showMMEDig()"/>
						</div>
					</td>
					<td>
						<div>
							<span class="icon-a6 icon"></span>
							<span class="ls" style="margin-top: 22px">
								用户所在PCRF:<br>XXPCRF10FE01BER<br>XXPCRF01FE02BER
							</span>
						</div>
						<div>
							<input id="btnSubmit" class="btn-new btn-search currentbtn" type="submit" value="查看PCRF侧用户详细信息" onclick="showPCRFDig()"/>
						</div>
					</td>
				</tr>
				
				<%-- <c:forEach items="${dataMap.connList}" var="conn"> --%>
				<tr>
					<td colspan="2" style="background: #fff; height: 25px; padding-left: 20px;color: #000">用户使用APN : ims UE IP : ipv4:N/A / ipv6: 2409:8807:A280:C536:2772:DD80:321:E01E</td>
				</tr>
				<tr>
					<td class="xian">
						<div>
							<span class="icon-a4 icon"></span>
							<span class="ls" style="margin-top: 22px">
								所在SGW : XXSAEGW24BER<%-- <span title="${conn.sgw}">&nbsp;${fns:abbr(conn.sgw,11)}</span> --%>
							</span>
						</div>
						<div>
							<input id="btnSubmit" class="btn-new btn-search currentbtn" type="submit" value="查看SGW侧用户详细信息" onclick="showsgw('${conn.sgw}')"/>
						</div>
					</td>
					<td>
						<div>
							<span class="icon-a5 icon"></span>
							<span class="ls" style="margin-top: 22px">
								所在PGW : XXSAEGW24BER<%-- <span title="${conn.pgw}">&nbsp;${fns:abbr(conn.pgw,11)}</span> --%>
							</span>
						</div>
						<div>
							<input id="btnSubmit" class="btn-new btn-search currentbtn" type="submit" value="查看PGW侧用户详细信息" onclick="showpgw('${conn.pgw}')"/>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="background: #fff; height: 25px; padding-left: 20px;color: #000">用户使用APN : cmnet UE IP : ipv4:10.201.203.76 / ipv6:N/A</td>
				</tr>
				<tr>
					<td class="xian">
						<div>
							<span class="icon-a4 icon"></span>
							<span class="ls" style="margin-top: 22px">
								所在SGW : XXSAEGW24BER<%-- <span title="${conn.sgw}">&nbsp;${fns:abbr(conn.sgw,11)}</span> --%>
							</span>
						</div>
						<div>
							<input id="btnSubmit" class="btn-new btn-search currentbtn" type="submit" value="查看SGW侧用户详细信息" onclick="showsgw('${conn.sgw}')"/>
						</div>
					</td>
					<td>
						<div>
							<span class="icon-a5 icon"></span>
							<span class="ls" style="margin-top: 22px">
								所在PGW : XXSAEGW18BER<%-- <span title="${conn.pgw}">&nbsp;${fns:abbr(conn.pgw,11)}</span> --%>
							</span>
						</div>
						<div>
							<input id="btnSubmit" class="btn-new btn-search currentbtn" type="submit" value="查看PGW侧用户详细信息" onclick="showpgw('${conn.pgw}')"/>
						</div>
					</td>
				</tr>
				<%-- </c:forEach> --%>
				
				<tr>
					<td class="xian">
						<div>
							<span class="icon-a3 icon"></span>
							<span class="ls" style="width:100%;margin-top: 2px; margin-left: 56px;">
								用户所在HSS:&nbsp;&nbsp;XXHSS19FE03BER
							</span>
						</div>
					</td>
					<td>
						<div>
							<span class="icon-a7 icon"></span>
							<span class="ls" style="width:100%;margin-top: 2px; margin-left: 56px;">
								用户所在MSC:&nbsp;&nbsp;XXGS14
							</span>
						</div>
						
					</td>
				</tr>
				<c:if test="${show == 'true' }">
				<tr>
					<td class="xian">
						<div>
							<span class="icon-a8 icon"></span>
							<span class="ls" style="width:100%;margin-top: -6px; margin-left: 56px;">
								用户当前TA:&nbsp;&nbsp;460-00-21637<br>
								Last TA:460-00-21637<%-- <span title="${dataMap.last_ta}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${fns:abbr(dataMap.last_ta,50)}</span> --%>
							</span>
						</div>
					</td>
					<td>
						<div style="border:1px;">
							<span class="icon-a9 icon"></span>
							<span class="ls" style="width:480px;margin-top: -6px; margin-left: 56px;">
								用户当前eNodeB:&nbsp;&nbsp;LXN221054H<br>
								Last eNodeB列表:460-00-Macro-352938,460-00-Macro-193697,460-00-Macro-352923,460-00-Macro-193733,460-00-Macro-789051,460-00-Macro-789051,460-00-Macro-352931,460-00-Macro-194008,460-00-Macro-352748,460-00-Macro-352678,460-00-Macro-193519<%-- <span title="${dataMap.last_enb_list}">&nbsp;${fns:abbr(dataMap.last_enb_list,50)}</span> --%>
							</span>
						</div>
					</td>
				</tr>
				</c:if>
				<tr>
				<form:form id="searchForm" action="${ctx}/userquery/cdr/redirectCdr" method="post" class="breadcrumb form-search">
					<td class="xian">
						<div class="xian">
							<span class="icon-Q icon"></span>
							<span class="ls" style="margin-top: 22px">
								用户话单查询
								<input type="hidden" name="msisdn" value="${dataMap.msisdn}" >
							</span>
						</div>
						<div>
							<span style="float: left; margin-left: 10px;margin-top: 25px;color: #000">时间段:</span>
							<select id="pastScope" name="pastScope" class="required input-medium" style="float: left;margin-left: 15px;margin-top: 22px;min-height: 30px;">
								<c:forEach items="${fns:getDictList('biz_past_time_scope')}" var="dict">
									<option value="${dict.value}">${dict.label}</option>
								</c:forEach>
							</select>
						</div>
					</td>
					<td>
						<div style="margin-top: 1px;margin-bottom: 0px;line-height: 33px; width: 65%">
							<div id="timeScope" class="form-inline hide" style="padding-left:30px;margin-top:5px;width: 100%;min-height: 0px;margin-bottom: 0px;">
								<label>查询开始时间:</label>
								<input id="beginDate" name="beginDate"  type="text" maxlength="20" class="input-medium Wdate" 
									value="<fmt:formatDate value="${userCdrQueryEntity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/><br>
								<label>查询结束时间:</label>
								<input id="endDate" name="endDate" type="text" maxlength="20" class="input-medium Wdate" 
									value="<fmt:formatDate value="${userCdrQueryEntity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
							 </div>
						</div>
						<div style="width: 35%">
							<input id="btnSubmit" class="btn-new btn-search currentbtn"  style="margin-left: 0px;" type="submit" value="话单查询"/>
						</div>
					</td>
					</form:form>
				</tr>
				<tr>
					<form:form id="searchForm" action="${ctx}/userquery/tEbmSingleSearch/redirectEbm" method="post" class="breadcrumb form-search">
						<td class="xian">
							<div class="xian">
								<span class="icon-R icon"></span>
								<span class="ls" style="margin-top: 22px">
									用户EBM查询
									<input type="hidden" name="searchType" value="imsi" >
									<input type="hidden" name="searchValue" value="${dataMap.imsi}" >
								</span>
							</div>
							<div style="margin-bottom: 8px">
								<div style="margin-left: 10px;margin-top: 10px; width: 100%;min-height: 25px">
									<span style="float: left; color: #000">网元类型：</span>
									<span style="float: left; margin-left: 0px;">
										<input type="checkbox" name="netType" value="MME" />MME  
						                <input type="checkbox" name="netType" value="EPG" />SAEGW  
						                <input type="checkbox" name="netType" value="PCRF" />PCRF  
					                </span>
				                </div>
				                <div style="margin-left: 10px;margin-top: 0px; width: 100%;min-height: 30px;margin-bottom: 0px;">
				                	<span style="float: left; color: #000">时间段:</span>
									<select id="pastScope1" name="pastScope" class="required input-medium" style="float: left;margin-left: 15px;min-height: 20px;">
										<c:forEach items="${fns:getDictList('biz_past_time_tac')}" var="dict">
											<option value="${dict.value}">${dict.label}</option>
										</c:forEach>
									</select>
				                </div>
							</div>
							
						</td>
						
						<td>
							<div style="margin-top: 1px;margin-bottom: 0px;line-height: 33px; width: 65%">
								<div id="timeScope1" class="form-inline hide" style="padding-left:30px;margin-top:5px;width: 100%;min-height: 0px;margin-bottom: 0px;">
									<label>查询开始时间:</label>
									<input id="beginDate1" name="beginDate"  type="text" maxlength="20" class="input-medium Wdate" 
										value="<fmt:formatDate value="${userCdrQueryEntity.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/><br>
									<label>查询结束时间:</label>
									<input id="endDate1" name="endDate" type="text" maxlength="20" class="input-medium Wdate" 
										value="<fmt:formatDate value="${userCdrQueryEntity.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
								 </div>
							</div>
							<div style="width: 35%">
								<input id="btnSubmit" class="btn-new btn-search currentbtn"  style="margin-left: 0px;" type="submit" value="EBM查询"/>
							</div>
						</td>
					</form:form>
				</tr>
				<tr>
					<form:form id="searchForm" action="${ctx}/userquery/signal/redirectSignal" method="post" class="breadcrumb form-search">
						<td class="xian">
							<div class="xian">
								<span class="icon-g icon"></span>
								<span class="ls" style="margin-top: 22px">
									用户信令追踪
									<input type="hidden" name="searchType1" value="${searchType}" >
									<input type="hidden" name="searchValue1" value="${searchValue}" >
								</span>
							</div>
							<div>
								<span style="float: left; margin-left: 10px;margin-top: 25px;color: #000">抓包时长:</span>
								<select id="captureDuration" name="captureDuration" class="required input-medium" style="float: left;margin-left: 15px;margin-top: 22px;min-height: 30px;">
									<c:forEach items="${fns:getDictList('biz_capture_duration')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</div>
						</td>
						<td>
							<div style="margin-top: 1px;margin-bottom: 0px;line-height: 33px; width: 65%">
								<span style="float: left; margin-left: 30px;margin-top: 20px;color: #000">抓包范围:</span>
								<select id="captureScope" name="captureScope" class="required input-medium" style="float: left;margin-left: 15px;margin-top: 22px;min-height: 30px;">
									<c:forEach items="${fns:getDictList('biz_capture_scope')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</div>
							<div style="width: 35%">
								<input id="btnSubmit" class="btn-new btn-search currentbtn" style="margin-left: 0px;" type="submit" value="信令追踪"/>
							</div>
						</td>
					</form:form>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>