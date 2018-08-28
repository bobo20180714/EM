/*
* 版本 V0.1
* 
* Author：刘宇祥
*/

var _easyFormDesign_template_button = function (context) {
  var ui = $.summernote.ui;
  
  // create button
  var button = ui.button({
    contents: '<i class="fa fa-list-alt"/> 模板',
    tooltip: '模板选择',
    click: function () {
      // invoke insertText method with 'hello' on editor module.
      context.invoke('editor.insertText', 'hello');
    }
  });

  return button.render();   // return button as jquery object 
};

var _easyFormDesign_preview_button = function (context) {
	  var ui = $.summernote.ui;
	  
	  // create button
	  var button = ui.button({
	    contents: '<i class="fa fa-list-alt"/> 预览',
	    tooltip: '预览效果',
	    click: function () {
	      // invoke insertText method with 'hello' on editor module.
	      context.invoke('editor.insertText', 'hello');
	    }
	  });

	  return button.render();   // return button as jquery object 
};



var EasyFormDesignComponents = {
	    text: function(id){
	    	var _jqObjInput = $('<input/>',{'type':'text','class':'','placeholder':'默认值','easyFormDesignType':'text','value':'{{value}}'}); 
	    	var _node = _jqObjInput.get(0);
	    	$('#'+id).summernote('insertNode', _node);
	    	$('#'+id).summernote('focus');
	    	
	    	$('.note-editable').mouseover(function( t, evt ){
	    		evt = evt || window.event;
	    	    var el = evt.target || evt.srcElement;
	    	    var easyFormDesignType = el.getAttribute('easyFormDesignType');
	    		if ( /input/ig.test( el.tagName ) && easyFormDesignType=='text') {
	    	    	//alert(el.tagName+":"+easyFormDesignType);
	    	        /* var html = popup.formatHtml(
	    	            '<nobr>多行文本框: <span onclick=$$._edittext() class="edui-clickable">编辑</span>&nbsp;&nbsp;<span onclick=$$._delete() class="edui-clickable">删除</span></nobr>' );
	    	        if ( html ) {
	    	            popup.getDom( 'content' ).innerHTML = html;
	    	            popup.anchorEl = el;
	    	            popup.showAnchor( popup.anchorEl );
	    	        } else {
	    	            popup.hide();
	    	        } */
	    	       
	    	    }
	    	});
		},
		//展示alert框
	    textarea: function(id){
	    	var thisItemName = 'textarea';
	    	var _jqObjCol = $('<div>',{'class':'col-md-5'}); 
	    	var _jqObjInput = $('<textarea/>',{'type':'text','class':'form-control','placeholder':'默认值','easyFormDesignType':thisItemName}); 
	    	_jqObjCol.append(_jqObjInput);
	    	var _node = _jqObjCol.get(0);
	    	//alert(_jqObjCol.html());
	    	$('#'+id).summernote('insertNode', _node);
	    	
	    	
	    	$('.note-editable').mouseover(function( t, evt ){
	    		evt = evt || window.event;
	    	    var el = evt.target || evt.srcElement;
	    	    var easyFormDesignType = el.getAttribute('easyFormDesignType');
	    		if ( /textarea/ig.test( el.tagName ) && easyFormDesignType==thisItemName) {
	    	    	//alert(el.tagName+":"+easyFormDesignType);
	    	        /* var html = popup.formatHtml(
	    	            '<nobr>多行文本框: <span onclick=$$._edittext() class="edui-clickable">编辑</span>&nbsp;&nbsp;<span onclick=$$._delete() class="edui-clickable">删除</span></nobr>' );
	    	        if ( html ) {
	    	            popup.getDom( 'content' ).innerHTML = html;
	    	            popup.anchorEl = el;
	    	            popup.showAnchor( popup.anchorEl );
	    	        } else {
	    	            popup.hide();
	    	        } */
	    	       
	    	    }
	    	});
		}
}
