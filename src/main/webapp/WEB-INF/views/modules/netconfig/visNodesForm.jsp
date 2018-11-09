<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拓扑节点管理</title>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://jeesite.com/"/>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/bootstrap/3.3.7/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
	
	<script src="${ctxStatic}/bootstrap/multiselect/2.1.1/multiselect.min.js" type="text/javascript"></script>
	    
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	
	
	<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
	<!-- customer common start-->
	<script src="${ctxStatic}/common/jeesite.js" type="text/javascript"></script>
	<script src="${ctxStatic}/common/epc.js" type="text/javascript"></script>
	<!-- customer common end-->
	
   <script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
	<script type="text/javascript">
		$(function(){
			
			$("#rooms").change(function(){
				roomId = $(this).find("option:selected").val();
				if(roomId ==""){
					$("select:not(:first) option").remove();
					return;
				}
		   		$.post('${ctx}/netconfig/visNodes/initPool', {
			       "roomId": roomId
			    }, function(data) {
			    	var pool = JSON.parse(data);
			    	
			    	var mmeElements = pool.mmeElements;
			    	var saegwElements = pool.saegwElements;
			    	var pcrfElements = pool.pcrfElements;
			    	var switchElements = pool.switchElements;
			    	var routerElements = pool.routerElements;
			    	var firewallElements = pool.firewallElements;
			    	var pnetElements = pool.pnetElements;
			    	var cgElements = pool.cgElements;
			    	
			    	var mmeElements_to = pool.mmeElements_to;
			    	var saegwElements_to = pool.saegwElements_to;
			    	var pcrfElements_to = pool.pcrfElements_to;
			    	var switchElements_to = pool.switchElements_to;
			    	var routerElements_to = pool.routerElements_to;
			    	var firewallElements_to = pool.firewallElements_to;
			    	var pnetElements_to = pool.pnetElements_to;
			    	var cgElements_to = pool.cgElements_to;
			    	
			    	$("#mmeElements option").remove();
			    	$.each(mmeElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#mmeElements").append(html);
					});
			    	$("#saegwElements option").remove();
			    	$.each(saegwElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#saegwElements").append(html);
					});
			    	$("#pcrfElements option").remove();
			    	$.each(pcrfElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#pcrfElements").append(html);
					});
			    	
			    	$("#switchElements option").remove();
			    	$.each(switchElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#switchElements").append(html);
					});
			    	$("#routerElements option").remove();
			    	$.each(routerElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#routerElements").append(html);
					});
			    	$("#firewallElements option").remove();
			    	$.each(firewallElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#firewallElements").append(html);
					});
			    	$("#pnetElements option").remove();
			    	$.each(pnetElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#pnetElements").append(html);
					});
			    	
			    	$("#cgElements option").remove();
			    	$.each(cgElements, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#cgElements").append(html);
					});
			    	
			    	$("#mmeElements_to option").remove();
			    	$.each(mmeElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#mmeElements_to").append(html);
					});
			    	$("#saegwElements_to option").remove();
			    	$.each(saegwElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#saegwElements_to").append(html);
					});
			    	$("#pcrfElements_to option").remove();
			    	$.each(pcrfElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#pcrfElements_to").append(html);
					});
			    	
			    	$("#switchElements_to option").remove();
			    	$.each(switchElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#switchElements_to").append(html);
					});
			    	$("#routerElements_to option").remove();
			    	$.each(routerElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#routerElements_to").append(html);
					});
			    	$("#firewallElements_to option").remove();
			    	$.each(firewallElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#firewallElements_to").append(html);
					});
			    	$("#pnetElements_to option").remove();
			    	$.each(pnetElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.name+"</option>";
					    $("#pnetElements_to").append(html);
					});
			    	
			    	$("#cgElements_to option").remove();
			    	$.each(cgElements_to, function(index, o) { 
			    		var html = "<option value='"+o.id+"'>"+o.fname+"</option>";
					    $("#cgElements_to").append(html);
					});
			    	
			    	
			    	
				 });
		   	});
			
			$('#cgElements').multiselect({
				keepRenderingSort: true
			});
			$('#mmeElements').multiselect({
				keepRenderingSort: true
			});
			$('#saegwElements').multiselect({
				keepRenderingSort: true
			});
			$('#pcrfElements').multiselect({
				keepRenderingSort: true
			});
			$('#pnetElements').multiselect({
				keepRenderingSort: true
			});
			$('#routerElements').multiselect({
				keepRenderingSort: true
			});
			$('#switchElements').multiselect({
				keepRenderingSort: true
			});
			$('#firewallElements').multiselect({
				keepRenderingSort: true
			});
		
		 });
		
		
		function doSubmit(){
			
		    var idElementsArray = new Array();  //定义数组 
		    $("#cgElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("8_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#mmeElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("1_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#saegwElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("2_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#pcrfElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("3_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#pnetElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("7_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#routerElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("5_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#switchElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("4_"+txt);  //添加到数组中  
		         }  
		    })
		    $("#firewallElements_to option").each(function(){  //遍历所有option  
		         var txt = $(this).val();   //获取option值   
		         if(txt!=''){  
		        	 idElementsArray.push("6_"+txt);  //添加到数组中  
		         }  
		    })
		    var roomId = $('#rooms').val();
		    if(roomId==''){
		    	top.$.jBox.tip('请选择机房','warning');
		    	return false;
		    }
		    if(idElementsArray==''){
		    	top.$.jBox.tip('请至少选择一个设备节点','warning');
		    	return false;
		    }
			document.forms[0].action = "${ctx}/netconfig/visNodes/save?idElements="+idElementsArray + "&roomId="+roomId;
			document.forms[0].method = "POST";
			document.forms[0].submit();
		}
		</script>
</head>
	
<body>
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/netconfig/visNodes/form"><strong>拓扑节点管理</strong></a></li>
	</ul>
	<sys:message content="${message}"/>
	<form id="inputForm" method="post">
	
		<div class="col-sm-7">
			<label class="control-label">机房:</label>
			<select id="rooms" class="form-control">
				<option value="">请选择</option>
				<c:forEach items="${rooms}" var="room">
					<option value="${room.id}">${room.name}</option>
				</c:forEach>
				<font color="red">*</font>
			</select>
		</div>
		
		
		<div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">CG节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="cgElements" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="cgElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="cgElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="cgElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="cgElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="cgElements_to" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">MME节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="mmeElements" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="mmeElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="mmeElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="mmeElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="mmeElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="mmeElements_to" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">SAEGW节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="saegwElements" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="saegwElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="saegwElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="saegwElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="saegwElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="saegwElements_to" class="form-control" size="8" multiple="multiple">
						
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">PCRF节点</h4>
				<div class="col-sm-5">
					<select name="from" id="pcrfElements" class="form-control" size="8" multiple="multiple">
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="pcrfElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="pcrfElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="pcrfElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="pcrfElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="pcrfElements_to" class="form-control" size="8" multiple="multiple">
						
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">专网节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="pnetElements" class="form-control" size="8" multiple="multiple">
						<c:forEach items="${pnetElements}" var="pnetElement">
							<option value="${pnetElement.id}">${pnetElement.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="pnetElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="pnetElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="pnetElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="pnetElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="pnetElements_to" class="form-control" size="8" multiple="multiple">
						
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">路由节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="routerElements" class="form-control" size="8" multiple="multiple">
						<c:forEach items="${routerElements}" var="routerElement">
							<option value="${routerElement.id}">${routerElement.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="routerElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="routerElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="routerElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="routerElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="routerElements_to" class="form-control" size="8" multiple="multiple">
						
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">交换机节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="switchElements" class="form-control" size="8" multiple="multiple">
						<c:forEach items="${switchElements}" var="switchElement">
							<option value="${switchElement.id}">${switchElement.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="switchElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="switchElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="switchElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="switchElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="switchElements_to" class="form-control" size="8" multiple="multiple">
						
					</select>
				</div>
			</div>
			
			<div class="col-sm-12">
			<h4 id="with-data-options">防火墙节点</h4>
				<div class="col-sm-5">
				
					<select name="from" id="firewallElements" class="form-control" size="8" multiple="multiple">
						<c:forEach items="${firewallElements}" var="firewallElement">
							<option value="${firewallElement.id}">${firewallElement.name}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-sm-2" >
					<button type="button" id="firewallElements_rightAll" class="btn btn-block btn-sm"><b>全增</b></button>
					<button type="button" id="firewallElements_rightSelected" class="btn btn-block btn-sm"><b>增加</b></button>
					<button type="button" id="firewallElements_leftSelected" class="btn btn-block btn-sm"><b>删除</b></button>
					<button type="button" id="firewallElements_leftAll" class="btn btn-block btn-sm" ><b>全删</b></button>
				</div>
				
				<div class="col-sm-5">
					<select id="firewallElements_to" class="form-control" size="8" multiple="multiple">
						
					</select>
				</div>
			</div>
			
		</div>
			
		
		<div class="col-sm-7" style="margin-top:20px;padding-right:50px;">
		   	<shiro:hasPermission name="netconfig:visNodes:view"><button type="button" id="saveElements" class="btn btn-primary" onclick="doSubmit()"><b>保 存</b></button></shiro:hasPermission>
		</div>
	</form>
   

  </body>
</html>
