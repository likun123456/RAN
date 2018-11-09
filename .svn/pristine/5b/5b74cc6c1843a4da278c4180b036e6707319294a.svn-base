/*!
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 * 
 * 通用公共函数
 */


/*
 * 获取小时所在整点,向后推迟1小时
 * @params addDay 加减天  
 * @author zhuguangrui
 */
function getWholePointTime(addDay){
	var now = new Date();
	now = addDate(now,addDay);
    now.setHours(now.getHours() + 1);	
    var year = now.getFullYear();       //年   
    var month = now.getMonth() + 1;     //月   
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
  
    var clock = year + "-";   
    if(month < 10){
        clock += "0";
    }
    clock += month + "-";	         
    if(day < 10){
    	clock += "0";
    }   
    clock += day + " ";   
     
    if(hh < 10){
    	clock += "0";
    }   
    return clock += hh + ":00:00";   
}

/*
 * 获取TAC前台选择的条件，赋值给开始时间
 * @params selectValue 选择值  
 * @author yanghai
 */
function getTacStartTime(selectValue){
	var arrTemp=selectValue.split('_');
	var now = new Date();
	now = addDate(now,-arrTemp[0]);	
    var year = now.getFullYear();       //年   
    var month = now.getMonth() + 1;     //月   
    var day = now.getDate();            //日
    var hh = arrTemp[1];            //时
  
    var clock = year + "-";   
    if(month < 10){
        clock += "0";
    }
    clock += month + "-";	         
    if(day < 10){
    	clock += "0";
    }   
    clock += day + " ";   
     
    if(hh < 10){
    	clock += "0";
    }   
    return clock += hh + ":00:00";   
}

/*
 * @params selectValue 选择值  
 * @author yanghai
 */
function getCheatEndTime(selectValue){
	var arrTemp=selectValue.split('_');
    
	var now = new Date();
	now = addDate(now,-arrTemp[0]);	
    var year = now.getFullYear();       //年   
    var month = now.getMonth() + 1;     //月   
    var day = now.getDate();            //日
    var hh = arrTemp[2];            //时
    var clock = year + "-";   
    if(month < 10){
        clock += "0";
    }
    clock += month + "-";	         
    if(day < 10){
    	clock += "0";
    }   
    clock += day + " "; 
    if(hh < 10){
    	clock += "0";
    }   
    return clock += hh + ":00:00";   
}

/*
 * 获取TAC前台选择的条件，赋值给结束时间
 * @params selectValue 选择值  
 * @author yanghai
 */
function getTacEndTime(selectValue){
	var arrTemp=selectValue.split('_');
    var hh = arrTemp[2];            //时
    var clock="";
    if(hh < 10){
    	clock += "0";
    }   
    return clock += hh + ":00:00";   
}

/*
 * bootstrap-table表单参数序列化json格式
 * @author zhuguangrui
 */
$.fn.serializeJsonObject = function() {  
    var json = {};  
    var form = this.serializeArray();  
    $.each(form, function() {  
        if (json[this.name]) {  
            if (!json[this.name].push) {  
                json[this.name] = [ json[this.name] ];  
            }  
            json[this.name].push( );  
        } else {  
            json[this.name] = this.value || '';  
        }  
    });  
    return json;  
}

/*
 * 折叠组件三角小图标切换
 * @author liuliang
 */
function  triangleIconChange(){
	
	$("div[class='panel panel-default']").each(function(){
		var $outthis= $(this);
		var $epcdiv = $(this).find("div[class='panel-body']").children();
		$epcdiv.on('hidden.bs.collapse', function (){
			//$outthis.find("img").attr("src",ctxStatic+"/images/p_right.png");
			$outthis.find("span:first-child").attr("class","icon-right");
		});
		$epcdiv.on('shown.bs.collapse', function (){
			//$outthis.find("img").attr("src",ctxStatic+"/images/p_down.png");
			$outthis.find("span:first-child").attr("class","icon-down");
		});
		
	});
}

/**
 * 全选
 * @author wangjingshi
 */
function checkAll(o, obj){
  var ids = document.getElementsByName(obj);
  var allIds = document.getElementsByName("allIds");
  if(ids.length != undefined){
    for(i = 0; i < ids.length; i++){
      ids[i].checked = o.checked;
    }
  }
  if(allIds.length != undefined){
    for(i = 0; i < allIds.length; i++){
      allIds[i].checked = o.checked;
    }
  }
}
/**
 * 取消
 * @author wangjingshi
 */
function checkNode(o){
  if(!o.checked){
	  var ids = document.getElementsByName("allIds");
	  if(ids.length!=undefined){
	  	for(i=0;i<ids.length;i++){
			ids[i].checked=false;
		}
	  }
  }
}

/*
 * 显示遮罩层
 * @author zhuguangrui
 */
function showMask(){
	$('#mask', parent.document).css("height",$(parent.document).height());     
    $('#mask', parent.document).css("width",$(parent.document).width());     
    $('#mask', parent.document).show();  
}


/*
 * 关闭遮罩层
 * @author zhuguangrui
 */
function hideMask(){
	$("#mask", window.parent.parent.document).hide();
}


/**
 * 浏览器打开新窗口
 * @author wangjingshi
 */
function openNewWindow(url,iWidth,iHeight){
	var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	var r = window.open(url,'','height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');
	return r;
}
/**
 * 浏览器最大化打开新窗口
 * @author wangjingshi
 */
function openMaxWindow(url){
	window.open(url,"","toolbar=no,menubar=no,scrollbars=yes,resizable=yes,location=no,status=no,left=0,top=0,width="+(screen.availWidth-10)+",height="+(screen.availHeight-30)); 
}

function epcLoading(dir) {
	//获取浏览器页面可见高度和宽度
	var _PageHeight = document.documentElement.clientHeight,
	    _PageWidth = document.documentElement.clientWidth;
	//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
	var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
	    _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;
	//在页面未加载完毕之前显示的loading Html自定义内容
	var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:0.8;alpha(opacity=60);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width: auto; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 25px; background: #1d1b1b url('+dir+'/jerichotab/images/jbox-loading2.gif) no-repeat scroll 5px 10px; border: 2px solid #95B8E7; color: #ffffff; font-family:\'Microsoft YaHei\';">页面加载中，请等待...</div></div>';
	//$('body').html($('body').html()+_LoadingHtml);
	return _LoadingHtml;
}

