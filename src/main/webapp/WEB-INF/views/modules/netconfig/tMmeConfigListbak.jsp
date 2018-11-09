<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>MME节点管理</title>
	<meta name="decorator" content="mme_config"/>
	
	<style type="text/css">
	    .menubar {
	    	line-height: 15px;
	    	}
	    .menubar .menuend {
	    	clear: both;
	    }
	    .menuitem
	        {background: #fff; position: relative; float: left; margin-right: 1em;}
	    .menuitem .submenu {
	    	background: #ccc; position: absolute; top: 38px; left: -1px; width: 134px;
	    }
	    .menuitem .submenu ul {
	    	padding-left:0px;
	    	margin-top: 0px;
	    	list-style: none;
	    }
	    .menuitem .submenu ul li {
	    	margin-top: 10px;
	    	margin-left: 5px;
	    }
	    /** 下面的控制显示和隐藏 **/
	    .menuitem .submenu {
	    	display: none;
	    }
	    .menuitem:hover .submenu {
	    	display: block;
	    }
	</style>
</head>

<body>
<% 
String str = (String)request.getParameter("go");
if("g".equals(str)) {
%>
<input id="btnCancel"  class="btn" type="button" value="返 回" onclick="history.go(-1)" style="float:right;" />
<%} %>
	<table width="915" height="700" border="0">
  <tr>
    <td width="430" rowspan="6"><img src="${ctxStatic}/images/untitled.png" alt="pic" width="400" height="800" /></td>
    <td height="187" colspan="3"><div id="left" style="float:left;margin-left:20px;"><p><img src="${ctxStatic}/images/untitled1.png" alt="mme" width="78" height="95" /></p>
      <p style="font-family:Arial, Helvetica, sans-serif; font-size:15px; margin-left:0px;"><strong>QDAMME52</strong></p></div>
       <div id="right" style="float:left;margin-top:10px;margin-left:20px;">
      &nbsp;&nbsp;<span style="font-family:'Microsoft YaHei UI';font-size:11px;"><strong>节点软件版本：16ACP09</strong></span><br/>
      &nbsp;&nbsp;<span style="font-family:'Microsoft YaHei UI';font-size:11px;"><strong>节点硬件版本 : MKVIII</strong></span><br/>
      &nbsp;&nbsp;<span style="font-family:'Microsoft YaHei UI';font-size:11px;"><strong>节点维护IP地址：</strong></span><br/>
      &nbsp;&nbsp;<span style="font-family:'Microsoft YaHei UI';font-size:11px;"><strong>节点当前用户数：</strong></span><br/>
      &nbsp;&nbsp;<span style="font-family:'Microsoft YaHei UI';font-size:11px;"><strong>节点License信息：</strong></span>
    </div>
    </td>
  </tr>
  <tr>
    <%-- <td width="160" height="47" align="center"><img src="${ctxStatic}/images/untitled2.png" alt="用户迁移" width="134" height="43" /></td>
    <td width="147" align="center"><img src="${ctxStatic}/images/untitled3.png" alt="节点隔离" width="135" height="42" /></td>
    <td width="150" height="47" align="center"><img src="${ctxStatic}/images/untitled4.png" alt="配置管理" width="134" height="43" /></td> --%>
 	<td height="47" colspan="3">
 		<div class="menubar">
 			<div class="menuitem">
 				<div><img src="${ctxStatic}/images/untitled2.png" alt="用户迁移" width="134" height="43" /></div>
		        <div class="submenu">
		            <ul>
		            	<li>子菜单1</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            </ul>
		        </div>
 			</div>
 			<div class="menuitem">
 			 	<div><img src="${ctxStatic}/images/untitled3.png" alt="节点隔离" width="135" height="42" /></div>
		        <div class="submenu">
		            <ul>
		            	<li>子菜单1</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            </ul>
		        </div>
 			</div>
 			<div class="menuitem">
 			 	<div><img src="${ctxStatic}/images/untitled4.png" alt="配置管理" width="134" height="43" /></div>
		        <div class="submenu">
		            <ul>
		            	<li>子菜单1</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            	<li>子菜单2</li>
		            </ul>
		        </div>
 			</div>
 		</div>
 	</td>
  </tr>
  <tr>
    <td height="23" colspan="3"><span style="font-family:'Microsoft YaHei UI';font-size:16px;"><strong>网元当前信息管理</strong></span></td>
  </tr>
  <tr>
    <td height="162" colspan="3">
		  <div id="accordion-example" data-collapse="accordion" style="margin-top:0px;" >
		    <h3>网元当前状态信息</h3>
		    <div>Are fun and they make pleasent noises</div>
		    <h3>网元当前健康检查结果</h3>
		    <div>I like fruits</div>
		    <h3>网元当前告警</h3>
		    <div>This is some information</div>
		    <h3>网元当前配置</h3>
		    <div>eh</div>
		    <h3>网元当前备份文件</h3>
		    <div>eh</div>
		  </div>
    </td>
  </tr>
  <tr>
    <td height="23" colspan="3"><span style="font-family:'Microsoft YaHei UI';font-size:16px;"><strong>网元历史信息管理</strong></span></td>
  </tr>
  <tr>
    <td height="242" colspan="3">
    <div style="margin-bottom:20px;margin-top:-30px;">
     <labe style="font-family:'Microsoft YaHei UI';font-size:12px;">查询日期</label>
	 <input id="startTime" type="text" readonly="readonly" maxlength="20" class="txt Wdate" style="width:160px;"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:00',isShowClear:true});"/>
	</div>
    <div id="accordion-example" data-collapse="accordion" >
        <h3>网元历史状态信息</h3>
	    <div>Are fun and they make pleasent noises</div>
	    <h3>网元历史健康检查结果</h3>
	    <div>I like fruits</div>
	    <h3>网元历史告警</h3>
	    <div>This is some information</div>
	    <h3>网元历史配置</h3>
	    <div>eh</div>
	    <h3>网元历史备份文件</h3>
	    <div>eh</div>
	  </div>
    </td>
</table>
</body>
</html>