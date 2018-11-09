<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>PCRF网元抓包明细</title>
	<meta name="decorator" content="default"/>
</head>
<body>
<script type="text/javascript"> 
	$(document).ready(function() {
		 $("#btn").click(function(){
            var msisdn = $("#msisdn").val();
            if(msisdn==""){
            	top.$.jBox.tip('请输入msisdn号码','warning');
            }
            var p_id = ${pid};
            var pcrfnames = "";
            <c:forEach items="${list}" var="list">
               pcrfnames = pcrfnames + "${list.netNames}" + ",";
            </c:forEach> 
            showMask(); // 显示遮罩
			$.ajax({
				type : "POST",
				url : "${ctx}/userquery/signal/pcrfFilter",
				dataType : "html",
				data : {msisdn:msisdn,pcrfnames:pcrfnames,p_id:parseInt(p_id)},
				success:function(data){
					hideMask();
					if(data == "empty"){
						top.$.jBox.tip("全量包生成，用户包未检索到！");
					}else{
						top.$.jBox.open('iframe:${ctx}/userquery/signal/showCaptureResult?id='+data,'单用户抓包结果',$(top.document).width()-800,$(top.document).height()-700,{
							buttons:{"关闭":true},
							loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
								$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
								$("body", h.find("iframe").contents()).css("margin","10px");
							}
						});
						
					}
				},
				error:function(e){
					hideMask();
					alert("单用户过滤过程中出现错误，请联系管理员");
				}
		   });
		}); 
	});
</script> 
  <div id="test1" style="padding: 0px 15px 50px 15px;">
        <table align="center" class="table table-striped table-bordered table-condensed">
  			<tr>
		    	<td style="border: 1px solid #AACBDB;width: 270px;"><div align="center"><strong>PCRF网元</strong></div></td>
		        <td style="border: 1px solid #AACBDB;width: 270px;"><div align="center"><strong>抓包开始时间</strong></div></td>
		        <td style="border: 1px solid #AACBDB;width: 270px;"><div align="center"><strong>抓包结束时间</strong></div></td>
  			</tr>
  			<c:forEach items="${list}" var="list">
  			<tr>
		    	<td style="border: 1px solid #AACBDB;width: 270px;">
		    	    <div align="center">
		    	       <span>${list.netNames}</span>
		    	    </div>
		    	</td>
			    <td style="border: 1px solid #AACBDB;width: 270px;"><div align="center">&nbsp;${list.startTime}</div></td>
			    <td style="border: 1px solid #AACBDB;width: 270px;"><div align="center">&nbsp;${list.endTime}</div></td>
  			</tr>
  			</c:forEach>
	      	<tr>
		    	<td colspan="2" style="border: 1px solid #AACBDB;border-right: 0">
			      	<div align="right" style="font-weight:bold;float;left;">
			      	           抓包用户MSISDN:
			        	<input type="text" style="width:150px;margin-top:10px;" id="msisdn" />&nbsp;&nbsp;&nbsp;&nbsp;
			        </div>
		      	</td>
		      	<td style="border: 1px solid #AACBDB;border-left: 0">
						<input id="btn" class="btn-new btn-save" type="button" value="执行"/>
				</td>
	      	</tr>																											
        </table>
        <div id="showtime" style="width:200px;margin:0 auto;"></div>
  </div>
</body>
</html>
