<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
	html,body{ height:100%;margin:0px; padding:0px; text-align:left} /*这个高度很重要*/
	html,body,img{border:0px;}
	.login_wrap{width:100%;min-width:678px;height:100%;background:#1c77ad;}
	.login_bg{width:100%;height:100%;background:url(${ctxStatic}/jerichotab/images/bg.png);background-size:100% 100%}
	/* .login_bg{width:100%;height:100%;background:url(${ctxStatic}/jerichotab/images/bg.jpg) no-repeat center 0;position:relative; text-align:center} */
	.login_top{min-width:678px;height:46px; background:#17577C url(${ctxStatic}/jerichotab/images/login_03.jpg) no-repeat 20px center; line-height:46px; padding-left:40px; font-size:12px; color:#AFC5D2;text-align:left;}
	.login_bom{width:100%;min-width:678px;background:#448FBB; font-size:12px; color:#2C6589; text-align:center; padding:15px 0;position:absolute;bottom:0; }
	.login_box{width:699px;height:321px;margin-top:20px; margin-left:auto; margin-right:auto;background:url(${ctxStatic}/jerichotab/images/bg2.png);background-size:100% 100%}
	.login_box1{width:699px;height:321px;margin-top:20px; margin-left:auto; margin-right:auto;background:url(${ctxStatic}/jerichotab/images/bg1x.png);background-size:100% 100%}
	.login_btn{ width:314px; float:right; height:48px; margin-top:10px; margin-right:0px;font-size: 16px;}
	.log_title{width:318px;height:69px;margin-top:80px;margin-left:auto; margin-right:auto;background:url(${ctxStatic}/jerichotab/images/log_title.png);background-size:100% 100%}
	.log_title1{width:513px;height:69px;margin-top:80px;margin-left:auto; margin-right:auto;background:url(${ctxStatic}/jerichotab/images/EPC_title2.png);background-size:100% 100%}
	.hide{display:none}
	
	.btn-login {
	float:right;
	margin-right:30px;
	margin-top:10px;
	  border-radius: 4px;
	  display: inline-block;
	  padding: 4px 12px;
	  margin-bottom: 0;
	  font-size: 14px;
	  line-height: 35px;
	  color: #fff;
	  padding: 0 20px 0 20px;
	  vertical-align: middle;
	  cursor: pointer;
	  background-color: #1f4e79;
	  border: 1px solid #ccc;
	  border-color: #e6e6e6 #e6e6e6 #bfbfbf;
	  border-color: rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
	  border-bottom-color: #b3b3b3;
	  -webkit-border-radius: 4px;
	  -moz-border-radius: 4px;
	  border-radius: 4px;
	}
	
	input:-webkit-autofill {  
	    -webkit-box-shadow: 0 0 0px 1000px #f2f2f2 inset !important;  
	}  
	</style>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules: {
				validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
			},
			messages: {
				username: {required: "请填写用户名."},password: {required: "请填写密码."},
				validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
			},
			errorLabelContainer: "#messageBox",
			errorPlacement: function(error, element) {
				error.appendTo($("#loginError").parent());
			} 
		});
	});
	// 如果在框架或在对话框中，则弹出提示并跳转到首页
	if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
		alert('未登录或登录超时。请重新登录，谢谢！');
		top.location = "${ctx}";
	}
	
	function close() {
		console.log(111);
	}
	
</script>


  <body>
  
    
 <div class="login_wrap">
    <div class="login_bg">
        <!-- <div class="login_top">欢迎登录北京联通RAN无限运维专家系统</div> -->
        <div class="header" style="height: 50px">
			<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}" style="margin: 0 auto;width: 300px;"><button data-dismiss="alert" class="close" onclick="close();">×</button>
				<label id="loginError" class="error">${message}</label>
			</div>
		</div>
        <form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
        <%-- <c:if test="${skin==1}">
        <div class="log_title">
        </c:if>
        <c:if test="${skin==2}">
        <div class="log_title1">
        </c:if> --%>
        <div class="log_title">
        </div>
        
       <%--  <c:if test="${skin==1}">
        <div class="login_box">
        </c:if>
        <c:if test="${skin==2}">
        <div class="login_box1">
        </c:if> --%>
        <div class="login_box">
        	<table style="float:right; margin-right: 30px; margin-top: 100px;">
        		<tr>
        			<td>
	        			<div class="login_btn">
						 	<span style="float: left; margin-top: 6px;">用户名</span><input name="username" type="text" class="in1"  id="username" size="20" style="border: 1px solid #1f4e79; height: 25px;float: left; margin-left: 12px; background-color: #f2f2f2">
						 </div>
        			</td>
        		</tr>
        		
        		<tr>
        			<td>
        				 <div class="login_btn">
						 	<span style="float: left; margin-top: 6px; padding-left: 10px; padding-right: 6px">密码</span> <input name="password" type="password" id="password" class="in2" id="username" size="20" style="border: 1px solid #1f4e79;height: 25px; float: left; margin-left: 12px;; background-color: #f2f2f2">
						 </div>
        			</td>
        		</tr>
        		<tr>
        			<td>
        				<input class="btn-login" type="submit" value="登 录"/>
        			</td>
        		</tr>
        	</table>
		</div>
		</form>
        <!-- <div class="login_bom">版权所有 2014 爱立信(中国)通信有限公司</div> -->
    </div>
</div>
<%-- <script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script> --%>
</body>
</html>
