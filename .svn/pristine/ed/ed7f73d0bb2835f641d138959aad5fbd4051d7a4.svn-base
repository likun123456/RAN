<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单用户聚合查询</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.querybg{
			width:624px;
			height:240px;
			margin-top:90px;
			margin-left:auto;
			margin-right:auto;
			background:url(${ctxStatic}/jerichotab/images/usersearch1.png);
			background-size:100% 100%;
		}
		.title{
			float:left;
			margin-left:80px;
			margin-top:40px;
			width:100px;
			height:150px;
			/* background:url(${ctxStatic}/jerichotab/images/xiaoren.png);
			background-size:100% 100%; */
		}
		.querybg table {
			float:left;
			width:320px;
			height:80px;
			margin-left: 88px;
			margin-top: 100px;
		}
		.querybtn {
			float: left;
			color:#fff;
			//margin-left: 20px;
			height:30px;
			border:0px;
			padding: 3px 25px;
			background:url(${ctxStatic}/jerichotab/images/querybtn.png);
			background-size:100% 100%;
		}
	</style>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#inputForm").validate({
			submitHandler: function(form){
				loading('正在查询，请稍等...');
				form.submit();
				/* var loadDiv = epcLoading('${ctxStatic}');
				$('body').html($('body').html()+loadDiv); */
			},
			errorContainer: "#messageBox",
			errorPlacement: function(errossr, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
	
	</script>
</head>
<body>

	<form:form id="inputForm" method="post" action="${ctx}/userquery/userQuery/query" class="form-horizontal">
     	<div class="querybg">
     		<div class="title"></div>
     		<table>
     			<!-- <tr>
     				<td colspan="2"><p style="font-size: 20px;color: #fff;letter-spacing:2px;">请输入需要查询的用户号码：</p></td>
     			</tr> -->
  
     			<tr>
     				<td>
	     				<select name="searchType" style="width:91px;float:left;">
	     				<option value="msisdn" selected="selected">MSISDN</option>
	     				<option value="imsi">IMSI</option>
	     				</select>
	     				<input type="text" name="searchValue" style="float:left;height:17px; width:130px;margin: 1px 2px;"/>
	     				<input class="querybtn" type="submit" value="查询"/>
     				</td>
     			</tr>
     		</table>
     	</div>
	</form:form>
</body>
</html>