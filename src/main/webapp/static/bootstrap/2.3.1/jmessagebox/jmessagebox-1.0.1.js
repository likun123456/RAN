/**
Name    : jMessageBox
Author  : kingthy
Email   : kingthy@gmail.com
Blog    : http://www.cnblogs.com/kingthy/
Version : 1.0.1
License : LGPL licenses.

Sample:
---------------------------------------------
sample 1 :
    jQuery.jMessageBox.show('Hello word!');

sample 2 :
    jQuery.jMessageBox.show('System Message', 'Hello word!');

sample 3 :
	jQuery.jMessageBox.show({
		width:300,
		title : 'System Message',
		message : 'Hello word!',
		yesButton : {
			click : function(){
				alert('Hello');
				jQuery.jMessageBox.hide();
			}
		},
		cancelButton : {
			click : function(){
				jQuery.jMessageBox.hide();
			}
		}
	});

**/
(function($){
	$.extend({
	jMessageBox : {
		settings : {width: 350, title : '', bottomText : '', yesButtonText : '', noButtonText : '', cancelButtonText : '', focusOnShow : true},
		_init : function(){
			var container = $('#jmessagebox_container');
			if(container.length == 0){
				$('body').append('<iframe style="display:none;position:absolute;z-index:2997;top:0px;left:0px;width:100%;height:100%;background-color:#ffffff;filter:alpha(opacity=0);opacity:0"jmlayer="1" jmconverlayer="1"></iframe>');
				$('body').append('<div style="display:none;position:absolute;z-index:2998;top:0px;left:0px;width:100%;height:100%" jmlayer="1" jmconverlayer="1"></div>');
				$('body').append('<div id="jmessagebox_container" name="jmessagebox_container" style="display:none;position:absolute;z-index:2999;" jmlayer="1"><div id="jmessagebox_title"></div><div id="jmessagebox_message"></div><div id="jmessagebox_buttons"><button id="jmessagebox_yesbutton"></button><button id="jmessagebox_nobutton"></button><button id="jmessagebox_cancelbutton"></button></div><div id="jmessagebox_bottom"></div></div>');
				$(window).scroll($.jMessageBox._fixposition);
				$('#jmessagebox_title').html($.jMessageBox.settings.title);
				$('#jmessagebox_yesbutton').html($.jMessageBox.settings.yesButtonText);
				$('#jmessagebox_nobutton').html($.jMessageBox.settings.noButtonText);
				$('#jmessagebox_cancelbutton').html($.jMessageBox.settings.cancelButtonText);
				$('#jmessagebox_bottom').html($.jMessageBox.settings.bottomText);
			}
		},
		_getposition : function(){
			var p = {};
			var scrollWidth, scrollHeight;
			if(window.innerHeight && window.scrollMaxY){
				scrollWidth = window.innerWidth + window.scrollMaxX;
				scrollHeight = window.innerHeight + window.scrollMaxY;
			}else if(document.body.scrollHeight>document.body.offsetHeight){
				scrollWidth = document.body.scrollWidth;
				scrollHeight = document.body.scrollHeight;
			}else{
				scrollWidth = document.body.offsetWidth;
				scrollHeight = document.body.offsetHeight;
			}
			if(self.innerHeight){
				if(document.documentElement.clientWidth){
					p.windowWidth = document.documentElement.clientWidth;
				}else{
					p.windowWidth = self.innerWidth;
				}
				p.windowHeight = self.innerHeight;
			}else if(document.documentElement && document.documentElement.clientHeight){
				p.windowWidth = document.documentElement.clientWidth;
				p.windowHeight = document.documentElement.clientHeight;
			}else if(document.body){
				p.windowWidth = document.body.clientWidth;
				p.windowHeight = document.body.clientHeight;
			}
			if(scrollWidth < p.windowWidth){
				p.width = scrollWidth;
			}else{
				p.width = p.windowWidth;
			}
			if(scrollHeight < p.windowHeight){
				p.height = scrollHeight;
			}else{
				p.height = p.windowHeight;
			}
			p.windowWidth = Math.max(p.windowWidth, scrollWidth);
			p.windowHeight = Math.max(p.windowHeight, scrollHeight);

			if(typeof(window.pageXOffset) == "number"){
				p.left = window.pageXOffset;
			}else if(document.documentElement && document.documentElement.scrollLeft){
				p.left = document.documentElement.scrollLeft;
			}else if(document.body){
				p.left = document.body.scrollLeft;
			}else if(window.scrollX){
				p.left = window.scrollX;
			}

			if(typeof(window.pageYOffset) == "number"){
				p.top = window.pageYOffset;
			}else if(document.documentElement && document.documentElement.scrollTop){
				p.top = document.documentElement.scrollTop;
			}else if(document.body){
				p.top = document.body.scrollTop;
			}else if(window.scrollY){
				p.top = window.scrollY;
			}
			return p;
		},
		_fixposition : function(){
			var container = $('#jmessagebox_container');
			if(container.length > 0 && container.css('display') != 'none' && container.attr('fixit') == '1'){
				var p = $.jMessageBox._getposition();
				var left = p.left + ((p.width - container.width()) / 2);
				var top = p.top + ((p.height - container.height()) / 2);
				container.css({left:left,top:top});
			}
		},
		_setbutton : function(id, arg){
			var button = $(id);
			if(arg){
				button.show();
				if(arg.text)button.html(arg.text);
				if(arg.click)button.get(0).onclick = arg.click;
				return true;
			}else{
				button.hide();
				return false;
			}
		},
		hide : function(){
			$('[jmlayer]').hide();
		},
		show : function(arg1, arg2){			
			if(arg1 == undefined)return;
			var args = arg1;
			if(typeof(arg1) != 'object'){
				args = {};				
				if(arg2 == undefined){
					args.message = arg1;
				}else{
					args.title = arg1;
					args.message = arg2;					
				}
				args.yesButton = {text:$.jMessageBox.settings.yesButtonText, click : function(){$.jMessageBox.hide();}};
			}
			var focusing = args.focus == undefined ? $.jMessageBox.settings.focusOnShow : args.focus;

			$.jMessageBox._init();
			if(args.title == undefined && $.jMessageBox.settings.title)args.title = $.jMessageBox.settings.title;
			if(args.title){
				$('#jmessagebox_title').html(args.title).show();
			}else{
				$('#jmessagebox_title').hide();
			}
			if(args.message){
				$('#jmessagebox_message').html(args.message).show();
			}else{
				$('#jmessagebox_message').hide();
			}
			var flag = 0, firstButtonId = null;
			if($.jMessageBox._setbutton('#jmessagebox_yesbutton',args.yesButton)){
				flag ++;
				if(firstButtonId == null)firstButtonId = '#jmessagebox_yesbutton';
			}
			if($.jMessageBox._setbutton('#jmessagebox_nobutton',args.noButton)){
				flag ++;
				if(firstButtonId == null)firstButtonId = '#jmessagebox_nobutton';
			}
			if($.jMessageBox._setbutton('#jmessagebox_cancelbutton',args.cancelButton)){
				flag ++;
				if(firstButtonId == null)firstButtonId = '#jmessagebox_cancelbutton';
			}
			if(flag > 0){
				$('#jmessagebox_buttons').show();
			}else{
				$('#jmessagebox_buttons').hide();
			}
			if(args.bottom == undefined && $.jMessageBox.settings.bottomText)args.bottom = {text : $.jMessageBox.settings.bottomText};
			$.jMessageBox._setbutton('#jmessagebox_bottom',args.bottom);

			var container = $('#jmessagebox_container');
			if(args.width == undefined)args.width = $.jMessageBox.settings.width;
			container.width(args.width ? args.width : 'auto');
			container.height(args.height ? args.height : 'auto');
			if(args.left > 0 || args.top > 0){
				if(args.left > 0)container.css('left',args.left + 'px');
				if(args.top > 0)container.css('top',args.top + 'px');
				container.css('filter','alpha(opacity=100)').css('opacity',1).attr('fixit','0');
				container.show();
				if(focusing && firstButtonId)$(firstButtonId).focus();
			}else{
				container.css('filter','alpha(opacity=0)').css('opacity',0).attr('fixit','1');
				container.show();
				window.setTimeout(function(){
					$.jMessageBox._fixposition();
					container.css('filter','alpha(opacity=100)').css('opacity',1);
					if(focusing && firstButtonId)$(firstButtonId).focus();
				}, 10);
			}

			var p = $.jMessageBox._getposition();
			$('[jmconverlayer]').width(p.windowWidth).height(p.windowHeight).show();
		}
	}
	})
})(jQuery);