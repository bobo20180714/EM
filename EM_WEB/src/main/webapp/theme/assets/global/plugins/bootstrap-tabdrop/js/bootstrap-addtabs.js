/**
 * Website: http://git.oschina.net/hbbcs/bootStrap-addTabs
 *
 * Version : 0.6
 *
 * Created by joe on 2016-2-4.
 */
var _rootPath=_rootPath_;
var _obj;
//说明：realId就是tab内容的id，tab标签的id在realId之后加上"_title"字符串
$.fn.addtabs = function (options) {
    _obj = $(this);
    Addtabs.options = $.extend({
        content: '', //直接指定所有页面TABS内容
        close: true, //是否可以关闭
        monitor: 'body', //监视的区域
        iframeUse: false, //使用iframe还是ajax
        iframeHeight: $(document).height() - 107, //固定TAB中IFRAME高度,根据需要自己修改
        method: 'init',
        callback: function () { //关闭后回调函数
        }
    }, options || {});


    $(Addtabs.options.monitor).on('click', '[data-addtab]', function () {
    	var sear='</i>';
        Addtabs.add({
            id: $(this).attr('data-addtab'),
            title: $(this).attr('title') ? $(this).attr('title') : $(this).html().substring($(this).html().indexOf(sear)+4),
            content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
            url: $(this).attr('url'),
            ajax: $(this).attr('ajax') ? true : false
        });
        //Metronic.unblockUI();
    });

    _obj.on('click', '.close-tab', function () {
        var id = $(this).prev("a").attr("aria-controls");
        Addtabs.close(id);
    });


    _obj.on('mouseover', '.close-tab', function () {
        $(this).removeClass('glyphicon-remove').addClass('glyphicon-remove-circle');
    })

    _obj.on('mouseout', '.close-tab', function () {
        $(this).removeClass('glyphicon-remove-circle').addClass('glyphicon-remove');
    })

    $(window).resize(function () {
        _obj.find('iframe').attr('height', Addtabs.options.iframeHeight);
        Addtabs.drop();
    });

};

window.Addtabs = {
	tempIFrameUrl:'',
    options:{},
    activeTab: function(realId){
    	$("#" + realId).addClass("active");
        $("#" + realId +'_title').addClass("active");
    },
    removeTab: function(realId){
    	$("#" + realId).remove();
    	 $("#" + realId +'_title').remove();
    },
    getRealTabContentId: function(id,url){
    	/**
    	 * id命名规则：
			必须以字母 A-Z 或 a-z 开头
			其后的字符：字母(A-Za-z)、数字(0-9)、连字符("-")、下划线("_")、冒号(":") 以及点号("."),最后经测试冒号点号皆不可出现在id中
			值对大小写敏感
    	 */
    	//url中可能出现的 / . ? & =
    	if(url.indexOf(_rootPath)!=-1){
    		url = url.replace(_rootPath,'');
    	}
    	var urlSuffix = url.replace(/\//g,'-').replace(/\?/g,'--').replace(/\&/g,'--').replace(/=/g,'-').replace(/\./g,'_');
    	return 'tab_'+id+'_'+urlSuffix;
    },
    add: function (opts) {
    	if(opts.url==''||opts.url==null){
    		return;
    	}
    	
    	//创建新TAB的内容
        var optUrl = opts.url;
        if(optUrl.indexOf("?")!=-1) {
        	opts.url=optUrl+"&refreshIdPrefix=yes";
        }else{
        	opts.url=optUrl+"?refreshIdPrefix=yes"
        }
    	
        var realId = Addtabs.getRealTabContentId(opts.id,opts.url);
    	_obj.find(' > ul li.active, > div >.active').removeClass('active');
        //如果TAB不存在，创建一个新的TAB
        if (!$("#" + realId)[0]) {
            //创建新TAB的title
            var title = $('<li>', {
                'role': 'presentation',
                'id': realId+"_title",
                'tabUrl': opts.url,
                'tabType':'div'
            }).append(
                $('<a>', {
                    'href': '#' + realId,
                    'aria-controls': realId,
                    'role': 'tab',
                    'data-toggle': 'tab'
                }).html(opts.title)
            );

            //是否允许关闭
            if (Addtabs.options.close) {
                title.append(
                    $('<i>',{class:'close-tab glyphicon glyphicon-remove'})
                );
            }
            
            var content = $('<div>', {
                'class': 'tab-pane',
                'id': realId,
                'role': 'tabpanel',
                'tabUrl': opts.url,
                'tabType':'div'
            });

//            //是否指定TAB内容
//            if (opts.content) {
//                content.append(opts.content);
//            } else if (Addtabs.options.iframeUse && !opts.ajax) {//没有内容，使用IFRAME打开链接
//                content.append(
//                    $('<iframe>', {
//                        'class': 'iframeClass',
//                        'height': Addtabs.options.iframeHeight,
//                        'frameborder': "no",
//                        'border': "0",
//                        'src': opts.url
//                    })
//                );
//            } else {
//            
//                $.get(opts.url, function (data) {
//                	$('#'+id).load(BaseUtils.getRootPath()+opts.url);
//                });
//            }
            //加入TABS
            _obj.children('.nav-tabs').append(title);
            _obj.children("#tab-content").append(content);
            Metronic.blockUI();
            setTimeout (function(){
            
			//BaseUtils.load(id,opts.url);
            content.load(_rootPath+opts.url,null,function(response,status,xhr) {
            	if(response.indexOf("sessionOut")!=-1){
                	var _loginPath = BaseUtils.getRootPath()+"sessionTimeOut.do";
    				if(window.top != window && document.referrer){
    					 top.location.href = _loginPath;
    				}else{
    					window.location = _loginPath;
    				}
            	}
            	 Metronic.unblockUI();
            });
			},300);
           // $('#'+id).load(BaseUtils.getRootPath()+opts.url);
        }
    	
        
        //如果Tab已打开过，激活TAB
        Addtabs.activeTab(realId);
        
        //增加右键菜单，用的是jquery contextmenu，要保证插件引入
        
        $.contextMenu({
	        selector: "li#"+realId+"_title", 
	        items: {
		        "refresh": {
		        	name: "刷新", 
		        	icon: "fa-refresh", 
		        	callback: function(key, opt){
		        		Addtabs.refresh(opts.id,_rootPath+opts.url);
		        	}
		        },
		        "closeOthers":{
		        	name: "关闭其他并刷新", 
		        	icon: "fa-remove", 
		        	callback: function(key, opt){
		        		if (_obj.find(" > ul li.active").attr('id') !=  (realId+'_title')) {
		            		_obj.find(' > ul li.active, > div >.active').removeClass('active');
		            		 Addtabs.activeTab(realId);
		                }
		        		_obj.find('li').not('.active').each(function(){
		        			var removeTitleId = $(this).attr('id');
		        			if(typeof(removeTitleId)!="undefined"
		        				&&removeTitleId!='tab_主页'
		        				&&removeTitleId.indexOf("_title")!=-1
		        				&&removeTitleId.indexOf("tab")!=-1){
		        				var realRemoveId = removeTitleId.substring(0,removeTitleId.indexOf("_title"));
		        				Addtabs.removeTab(realRemoveId);
		        			}
		                });
		        		Addtabs.refresh(opts.id,_rootPath+opts.url);
		        	}
		        }
		    }
	    });
        //Addtabs.drop();
    },
    close: function (realId) {
        //如果关闭的是当前激活的TAB，激活他的前一个TAB
        if (_obj.find(" > ul li.active").attr('id') ==  (realId+'_title')) {
            $("#" + realId+"_title").prev().addClass('active');
            $("#" + realId).prev().addClass('active');
        }
        //关闭TAB
        Addtabs.removeTab(realId);
        Addtabs.drop();
        Addtabs.options.callback();
    },
    drop: function () {
      
    },
    addInIFrame: function (opts) {
    	if(opts.url==''||opts.url==null){
    		return;
    	}
    	
    	//创建新TAB的内容
        var optUrl = opts.url;
        if(optUrl.indexOf("?")!=-1) {
        	opts.url=optUrl+"&refreshIdPrefix=yes";
        }else{
        	opts.url=optUrl+"?refreshIdPrefix=yes"
        }
    	
        var realId = Addtabs.getRealTabContentId(opts.id,opts.url);
        _obj.find(' > ul li.active, > div >.active').removeClass('active');
        //如果TAB不存在，创建一个新的TAB
        if (!$("#" + realId)[0]) {
            //创建新TAB的title

            var title = $('<li>', {
                'role': 'presentation',
                'id': realId+'_title',
                'tabUrl': opts.url,
                'tabType':'iframe'
            }).append(
                $('<a>', {
                    'href': '#' + realId,
                    'aria-controls': realId,
                    'role': 'tab',
                    'data-toggle': 'tab'
                }).html(opts.title)
            );

            //是否允许关闭
            if (Addtabs.options.close) {
                title.append(
                    $('<i>',{class:'close-tab glyphicon glyphicon-remove'})
                );
            }
            //创建新TAB的内容
            var content = $('<div>', {
                'class': 'tab-pane',
                'id': realId,
                'role': 'tabpanel',
                'tabUrl': opts.url,
                'tabType':'iframe'
            });

            content.append(
                    $('<iframe>', {
                    	'id': realId+'_iframe',
                        'class': 'iframeClass',
                        'height': Addtabs.options.iframeHeight,
                        'frameborder': "no",
                        'border': "0",
                        'scrolling': "no",
                         'src': BaseUtils.getRootPath()+'rightTemplateForIFrame.do'//opts.url
                    })
                );
            
            //加入TABS
            Metronic.blockUI();
            _obj.children("#tab-content").append(content);
            _obj.children('.nav-tabs').append(title);
            
            $.contextMenu({
    	        selector: "li#"+realId+"_title", 
    	        items: {
    		        "refresh": {
    		        	name: "刷新", 
    		        	icon: "fa-refresh", 
    		        	callback: function(key, opt){
    		        		Addtabs.refreshForIFrame(opts.id,_rootPath+opts.url);
    		        	}
    		        },
    		        "closeOthers":{
    		        	name: "关闭其他并刷新", 
    		        	icon: "fa-remove", 
    		        	callback: function(key, opt){
    		        		if (_obj.find(" > ul li.active").attr('id') !=  (realId+'_title')) {
    		            		_obj.find(' > ul li.active, > div >.active').removeClass('active');
    		            		 Addtabs.activeTab(realId);
    		                }
    		        		_obj.find('li').not('.active').each(function(){
    		        			var removeTitleId = $(this).attr('id')
    		        			if(typeof(removeTitleId)!="undefined"
    		        				&&removeTitleId!='tab_主页'
    		        				&&removeTitleId.indexOf("_title")!=-1
    		        				&&removeTitleId.indexOf("tab")!=-1){
    		        				var realRemoveId = removeTitleId.substring(0,removeTitleId.indexOf("_title"));
    		        				Addtabs.removeTab(realRemoveId);
    		        			}
    		                });
    		        		Addtabs.refreshForIFrame(opts.id,_rootPath+opts.url);
    		        	}
    		        }
    		    }
    	    });
        }
    	
        //如果Tab已打开过，激活TAB
        Addtabs.activeTab(realId);
        top.Addtabs.tempIFrameUrl=opts.url;
    },
    refreshForIFrame: function(id,url){
    	var realId = Addtabs.getRealTabContentId(id,url);
    	//location.hash = realId;
    	if (_obj.find(" > ul li.active").attr('id') !=  realId) {
    		_obj.find(' > ul li.active, > div >.active').removeClass('active');
    		Addtabs.activeTab(realId);
        }
    	top.Addtabs.tempIFrameUrl=url;
    	$("#" + realId+'_iframe').attr("src",BaseUtils.getRootPath()+'rightTemplateForIFrame.do');
    },
    refresh: function(id,url){
    	var realId = Addtabs.getRealTabContentId(id,url);
    	
    	if($("#" + realId+'_iframe').length>0){
    		Addtabs.refreshForIFrame(id,url);
    	}else{
    		if (_obj.find(" > ul li.active").attr('id') !=  (realId+'_title')) {
        		_obj.find(' > ul li.active, > div >.active').removeClass('active');
        		Addtabs.activeTab(realId);
            }

        	Metronic.blockUI();
        	$("#" + realId).load(url,null,function(response,status,xhr) {
        		if(response.indexOf("sessionOut")!=-1){
                	var _loginPath = BaseUtils.getRootPath()+"sessionTimeOut.do";
    				if(window.top != window && document.referrer){
    					 top.location.href = _loginPath;
    				}else{
    					window.location = _loginPath;
    				}
            	}
            	 Metronic.unblockUI();
            },300);
    	} 
    },
    redirectCurrent:function(url){
    	var activeTab = _obj.find(" > ul li.active");
    	
    	if(top!=self){
    		var iframe=window.frameElement;
    		var iframeId = $(iframe).attr("id");
    		var tabContentId = iframeId.substring(0,iframeId.indexOf("_iframe"));
    		if(url==''||url==null||typeof(url)=="undefined"){
    			url = parent.$('#'+tabContentId).attr('tabUrl');
    		}
    		//重置tab签的url属性
    		//parent.$('#'+tabContentId).attr('tabUrl',url);
    		//parent.$('#'+tabContentId+"_title").attr('tabUrl',url);
    		//需要重置的属性仍有很多，暂时用不到，待添加
    		
    		//创建新TAB的内容
            if(url.indexOf("?")!=-1) {
            	url=optUrl+"&refreshIdPrefix=yes";
            }else{
            	url=optUrl+"?refreshIdPrefix=yes"
            }
    		
    		top.Addtabs.tempIFrameUrl=url;
    		parent.$("#" + iframeId).attr("src",_rootPath+'rightTemplateForIFrame.do');
    		return;
    	}
    	
    	if(url==''||url==null||typeof(url)=="undefined"){
			url=activeTab.attr("tabUrl");
		}
		var activeTabTitleId = activeTab.attr("id");
		var realId = activeTabTitleId.substring(0,activeTabTitleId.indexOf("_title"));;
		
		Metronic.blockUI();
    	$("#" + realId).load(url,null,function(response,status,xhr) {
    		if(response.indexOf("sessionOut")!=-1){
            	var _loginPath = BaseUtils.getRootPath()+"sessionTimeOut.do";
				if(window.top != window && document.referrer){
					 top.location.href = _loginPath;
				}else{
					window.location = _loginPath;
				}
        	}
        	 Metronic.unblockUI();
        },300);
		return;
    },
    //不要用a标签的href触发 用onclick触发
    refreshCurrent:function(){
    	Addtabs.redirectCurrent('');
    },
    getCurrentTabTitleObj:function(){
    	return _obj.find(' > ul li.active');
    },
    getCurrentTabContentObj:function(){
    	return _obj.find(' > div >.active');
    },
    getCurrentTabContentId:function(){
    	return _obj.find(' > div >.active').attr('id');
    },
    getCurrentTabElementById:function(id){
    	if(_obj.find(' > div >.active').lentgh==0){
    		return null;
    	}
    	return _obj.find(' > div >.active').find("#"+id);
    },
    getCurrentTabIdPrefix:function(){
    	return _obj.find(' > div >.active').attr("tabIdPrefix");
    }
}