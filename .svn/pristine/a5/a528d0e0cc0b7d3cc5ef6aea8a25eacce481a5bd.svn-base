<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
    <c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
    <script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {padding:0 0 3px;position:static;background:url(${ctxStatic}/jerichotab/images/border.png); background-size:100% 100%} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:'微软雅黑';font-size:26px;padding-left:33px;}
		#footer {margin:0 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;/* border-top:2px solid #0663A2; */background-color: #00285e;}
		#footer, #footer a {color:#fafafa;} #left{overflow-x:hidden;overflow-y:auto;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
		.mask {       
	            position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #999;     
	            z-index: 1002; left: 0px;     
	            opacity:0.5; -moz-opacity:0.5;     
	        }
        .img_wrap{
			width: 768px;
			height: 266px;
			display:none;
			position: absolute;
			top:50%;
			left: 50%;
			margin-left: -384px;
			margin-top: -133px; 
			z-index:1005;
		}
		.img_wrap2{
			display:none;
			position: absolute;
			top:50%;
			left: 50%;
			z-index:1001;
		}
		#showtime{
			color:#fff;
			position: absolute;
			top:60%;
			left:37.5%;
			font-size:42px;
			font-family:幼圆;
			font-weight:bold;
		}
		.inner_div{
			position: relative;
		}
		.inner_div2{
			position: relative;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// <c:if test="${tabmode eq '1'}"> 初始化页签
			$.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': $('#right').height() - tabTitleHeight},
                tabs: [], loadOnce: true, tabWidth: 150, titleHeight: tabTitleHeight
            });//</c:if>
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				var m = $(this);
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$('.accordion-group .accordion-heading').removeClass('accordion-heading1');
				$(this).parent().addClass("active");
				
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// <c:if test="${tabmode eq '1'}"> 隐藏页签
					$(".jericho_tab").hide();
					$("#mainFrame").show();//</c:if>
					return true;
				}
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left .accordion").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if (!$(menuId + " .accordion-body:first").hasClass('in')){
						$(menuId + " .accordion-heading:first a").click();
					}
					if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
						$(menuId + " .accordion-body a:first i").click();
					}
					// 初始化点击第一个三级菜单
					$(menuId + " .accordion-body li:first li:first a:first i").click();
				}else{
					// 获取二级菜单数据
					
					$.get($(this).attr("data-href"), function(data){
						
						if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "${ctx}";
							return false;
						}
						$("#left .accordion").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							/* var navStr = '&nbsp;<i class="icon-desktop icon-white"></i> ' + $('.menu.active a span').html() + ' > ' + $(this).html();
							$('#navdiv').html(navStr); */
							
							
							$('.accordion-group .accordion-heading').removeClass('accordion-heading1');
							//$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							//$(this).parent().addClass('accordion-heading1');
							if(!$($(this).attr('data-href')).hasClass('in')){
								//$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
								$(this).parent().addClass('accordion-heading1');
							} 
							
							
							/* $(menuId + " .accordion-toggle i").removeClass('icon-white');
							$(this).addClass("active");
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").addClass('icon-white');
							} */
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){
							/* var navStr = '&nbsp;<i class="icon-desktop icon-white"></i> ' + $('.menu.active a span').html() + ' > ' + $(this).html();
							$('#navdiv').html(navStr); */
							//$(this).parent().parent().parent().addClass("accordion-heading1");
							$(this).parents('.accordion-body').prev().addClass('accordion-heading1');
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).parent().addClass("active");
							$(this).children("i").addClass("icon-white");
						});
						// 展现三级
						$(menuId + " .accordion-inner a").click(function(){
							/* var navStr = '&nbsp;<i class="icon-desktop icon-white"></i> ' + $('.menu.active a span').html() + ' > ' + $(this).html();
							$('#navdiv').html(navStr); */
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
								return false;
							}
							// <c:if test="${tabmode eq '1'}"> 打开显示页签
							return addTab($(this)); // </c:if>
						});
						// 默认选中第一个菜单
						$(menuId + " .accordion-body a:first i").click();
						$(menuId + " .accordion-body li:first li:first a:first i").click();
						//$('.menu.active a span')
						$('.menu.active a span').click();
						$(menuId + " .accordion-body.collapse.in li:first a").click();
					});
				}
				// 大小宽度调整
				wSizeWidth();
				
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:first span").click();
			// <c:if test="${tabmode eq '1'}"> 下拉菜单以选项卡方式打开
			$("#userInfo .dropdown-menu a").mouseup(function(){
				return addTab($(this), true);
			});// </c:if>
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
			// 获取通知数目  <c:set var="oaNotifyRemindInterval" value="${fns:getConfig('oa.notify.remind.interval')}"/>
			function getNotifyNum(){
				$.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
					var num = parseFloat(data);
					if (num > 0){
						$("#notifyNum,#notifyNum2").show().html("("+num+")");
					}else{
						$("#notifyNum,#notifyNum2").hide()
					}
				});
			}
			getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
			setInterval(getNotifyNum, ${oaNotifyRemindInterval}); //</c:if>
		});
		// <c:if test="${tabmode eq '1'}"> 添加一个页签
		function addTab($this, refresh){
			 $(".jericho_tab").show();
			$("#mainFrame").hide();
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			return false; 
		}// </c:if>
		
	</script>
</head>
<body>
    <div id="mask" class="mask"></div>
    <div id="img_wrap" class="img_wrap"><div class="inner_div"><img id="loadingImg" src='${ctxStatic}/images/capture1.png'/><div id="showtime"></div></div></div>
    <div id="img_wrap2" class="img_wrap2"><div class="inner_div2"><img id="loadingImg2" src='${ctxStatic}/images/loading.gif'/><div id="showtime2"></div></div></div>
	<div id="main">
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand" style="padding: 10px 50px 5px"><span id="productName" style="font-weight: bold;"><img alt="" src="${ctxStatic}/jerichotab/images/title2.png" style="width: 240px; height: 32px"><%-- ${fns:getConfig('productName')} --%></div>
				<ul id="userControl" class="nav pull-right">
					<%-- <li><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/index-${fnc:getCurrentSiteId()}.html" target="_blank" title="访问网站主页"><i class="icon-home"></i></a></li>
					<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
							<li><a href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
						</ul>
						<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
					</li> --%>
					<li id="userInfo" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">
						<span class="icon-d" style="font-size:28px; margin-right:15px; margin-top:-5px"></span>  
						您好, ${fns:getUser().name}&nbsp;<span id="notifyNum" class="label label-info hide"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
							<li><a href="${ctx}/oa/oaNotify/self" target="mainFrame"><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide"></span></a></li>
						</ul>
					</li>
					<li class="logout"><a href="${ctx}/logout" title="退出登录">
					<span class="icon-m" style="font-size:28px; margin-right:15px; margin-top:-5px"></span>
					退出</a></li>
					<li>&nbsp;</li>
				</ul>
				<%-- <c:if test="${cookie.theme.value eq 'cerulean'}">
					<div id="user" style="position:absolute;top:0;right:0;"></div>
					<div id="logo" style="background:url(${ctxStatic}/images/logo_bg.jpg) right repeat-x;width:100%;">
						<div style="background:url(${ctxStatic}/images/logo.jpg) left no-repeat;width:100%;height:70px;"></div>
					</div>
					<script type="text/javascript">
						$("#productName").hide();$("#user").html($("#userControl"));$("#header").prepend($("#user, #logo"));
					</script>
				</c:if> --%>
				<div class="nav-collapse">
					<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
						<c:set var="firstMenu" value="true"/>
						<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
							<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
								<li class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}" style="border-left:1px solid #e0e0e0; border-right:1px solid #e0e0e0; margin-left:-1px;">
									<c:if test="${empty menu.href}">
										<a class="menu" style="padding: 16px 14px 14px 10px;" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="${menu.icon}" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;${menu.name}</span>
										</a>
									</c:if>
									<c:if test="${not empty menu.href}">
										<a class="menu" style="padding: 16px 14px 14px 10px;" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" data-id="${menu.id}" target="mainFrame">
										<%-- <i class="${menu.icon}"></i> --%>
										<span class="${menu.icon}" style="font-size:32px; margin-right:12px; margin-top:-5px"></span>
										<span style="font-size: 14px; font-weight: bold;">&nbsp;${menu.name}</span>
										</a>
									</c:if>
								</li>
								<c:if test="${firstMenu}">
									<c:set var="firstMenuId" value="${menu.id}"/>
								</c:if>
								<c:set var="firstMenu" value="false"/>
							</c:if>
						</c:forEach><%--
						<shiro:hasPermission name="cms:site:select">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fnc:getSite(fnc:getCurrentSiteId()).name}<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:forEach items="${fnc:getSiteList()}" var="site"><li><a href="${ctx}/cms/site/select?id=${site.id}&flag=1">${site.name}</a></li></c:forEach>
							</ul>
						</li>
						</shiro:hasPermission> --%>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left"><%-- 
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right" style="padding-left:15px; padding-top:8px; padding-right:15px;margin-left:15px;margin-right:15px; margin-top:15px; margin-bottom:30px;  border: 1px solid rgb(255, 255, 255); border-image: none; box-shadow: 0px 0px 10px rgba(0,0,0,0.2); background-color: rgb(255, 255, 255); -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); -moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);">
					<div id="navdiv">
						
					</div>
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
		    <div id="footer" class="row-fluid">
	        	   版权所有 2017 爱立信（中国）通信有限公司 
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = 200; // 左侧窗口大小
		var tabTitleHeight = 75; // 页签的高度
		
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		var paddingSize = 15;
		var marginSize = 15;
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - paddingSize - marginSize - 20 - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() + 62);// <c:if test="${tabmode eq '1'}"> 
			$(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
			wSizeWidth();
		}
		function wSizeWidth(){
			
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5 - paddingSize * 2 - marginSize*2);
			}else{
				$("#right").width("100%");
			}
		}// <c:if test="${tabmode eq '1'}"> 
		function openCloseClickCallBack(b){
			$.fn.jerichoTab.resize();
		} // </c:if>
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>