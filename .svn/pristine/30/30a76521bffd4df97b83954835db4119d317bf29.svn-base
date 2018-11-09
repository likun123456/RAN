<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>自动巡检模板配置管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		.container1 {
			width: 900px;
			height : 370px;
			margin: 0 auto;
			border: 1px solid #000;
		}
		
		.title {
			width: 890px;
			margin-top: 10px;
		}
		
		.content {
			width: 100%;
			margin-top: 10px;
		}
		
		.left {
			margin-left:20px;
			width : 300px;
		}
		
		.right {
			width : 380px;
			border: 1px solid #000;
		}
		
		.seldata {
			width: 500px;
			height: 300px;
			border: 1px solid #ccc;
			background: #fff;
			overflow:auto;
			
		}
		
		.seldata ul {
			list-style: none;
			margin: 5px 0 10px 0;
		}
		
		.seldata ul li {
			float:left;
			padding-left: 10px;
			width: 472px;
			cursor: pointer;
		}
		
		.active {
			background: #e5e5e5;
			font-weight: bold;
		}
		
		.seldata ul li div:nth-child(1){
			float:left;
		}
		
		.seldata ul li div:nth-child(2){
			float:right;
			padding-left: 0px;
			width:20px;
			height:20px;
			background:url(${ctxStatic}/images/close.gif);
		}
		
	
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#excelId").change(function(){
				debugger;
	    	    var excelId=$("#excelId option:selected").val();
		   		$.post('${ctx}/netconfig/excelTemplateDebug/queryModuleList', {
			       "excelId": excelId
			    }, function(data) {
				    	var moduleList = eval(data);
				    	$("#moduleId option:not(:first)").remove();  
				    	$(".select2-container:eq(2) .select2-chosen:eq(0)").html("请选择");
				    	//$("#moduleId").html("<option value=''>请选择</option>")
				    	$.each(moduleList, function(index, o) { 
				    		var html = "<option value='"+o.id+"'>"+o.moduleName+"</option>";
						    $("#moduleId").append(html);
						}); 
				    	$("#moduleId option:first").prop("selected", 'selected'); 
				    });
			});
			$('.seldata ul').on("mouseover","li", function(){
				$(this).addClass("active");
			});
			
			$('.seldata ul').on("mouseout","li", function(){
				$(this).removeClass("active");
			});
			
			$('.seldata ul').on("click","li div:nth-child(2)", function(){
				$(this).parent().remove();
			});
			
			$('#selBtn').click(function(){
				var excelId=$("#excelId option:selected").val();
				var exceltext=$("#excelId option:selected").text();
				
				var moduleId=$("#moduleId option:selected").val();
				var moduletext=$("#moduleId option:selected").text();
				
				var netTypeId=$("#netType option:selected").val();
				var netTypetext=$("#netType option:selected").text();
				
				if('0' == excelId || '0' == moduleId || '0' == netTypeId) {
					top.$.jBox.tip('请选择下拉内容','warning');
					return;
				}
				
				if($('#'+excelId+"_"+moduleId+"_"+netTypeId).length > 0) {
					debugger;
					top.$.jBox.tip('不允许重复添加元素','warning');
					return;
				}
				
				var text = exceltext + '--' + moduletext + '--' + netTypetext;
				var value = excelId + '_' + moduleId + '_' + netTypeId;
				var liStr = "<li><div id='"+value+"'>"+text+"</div><div></div></li>";
				$('.seldata ul').append(liStr);
				
			});
			
			$('#saveBtn').click(function(){
				var collecttime=$("#collecttime option:selected").val();
				var data = [];
				var liNum = $('.seldata ul li').length;
				if(liNum == 0) {
					top.$.jBox.tip('内容为空不允许保存','warning');
					return;
				}
				$('.seldata ul li div:nth-child(1)').each(function(){
					var id = $(this).attr("id");
					var val = $(this).html();
					var obj = {};
					obj.ids = id;
					obj.names = val;
					obj.collecttime = collecttime;
					data.push(obj);
				});
				
				$.ajax({
					type : "POST",
					url : "${ctx}/autocheck/tAutoCheckConfig/saveTemplate",
					dataType : "html",
					traditional:true, //阻止深度序列化
					data : {"dataArray":JSON.stringify(data)}, // 转成字符串形式
					success:function(dataQuery){
						debugger;
						if(dataQuery=="success"){
				    		top.$.jBox.tip('保存成功','warning');	
				    	}else{
				    		top.$.jBox.tip('保存失败','warning');
				    	}
					}
			   });
				
				
			});
			
		});
		
	</script>
</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-inner">
			<span class="icon-j" style="font-size: 45px; float:left;margin-left:10px;"></span>
			<a class="brand" style="font-size: 15px; margin-left: 30px;">自动巡检模板配置</a>
		</div>
		<form:form id="inputForm" modelAttribute="tAutoTemplateCheckConfig" action="${ctx}/autocheck/tAutoCheckConfig/save" method="post" class="breadcrumb form-search">
			<div class="container1">
				<div class="title">
					<label><strong>巡检时长配置</strong></label>
						<form:select path="collecttime" items="${fns:getDictList('biz_autocheck_interval')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-medium" style="margin-left:26px;width:120px;"/>
					<input id="saveBtn" type="button" class="btn btn-primary" style="float: right;margin-top: 0px" value="保存">
				</div>
				<div class="content">
					<table>
						<tr>
							<td style="vertical-align: top;width: 390px">
								<label style="width: 100px;margin-top: 20px"><strong>选择模板</strong></label>
								<form:select path="excelId" class="required input-xlarge" style="width:250px;" id="excelId">
									<form:option value="0" label="请选择" />
									<form:options items="${visExcelList}" itemLabel="name" itemValue="id" htmlEscape="false" />
								</form:select>
								
								<label style="width: 100px;margin-top: 20px"><strong>选择模块</strong></label>
								<form:select path="moduleId" class="required input-xlarge" style="width:250px;" id="moduleId">
									<form:option value="0" label="请选择" />
								</form:select>
								
								<label style="width: 100px;margin-top: 20px"><strong>网元类型</strong></label>
								<form:select path="netType" class="input-xlarge" style="width:250px;" id="netType">
						            <form:option value="0" label="请选择"/>
									<form:options items="${fns:getDictList('biz_net_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
								</form:select>
								<input id="selBtn" type="button" class="btn btn-primary" style="float: right;margin-right: 30px" value="选择">
							</td>
							<td style="vertical-align: top;">
								
								<div class="seldata">
									<ul>
										<c:forEach items="${templateList }" var="item">
											<li><div id="${item.ids }">${item.names }</div><div></div></li>
										</c:forEach>
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>