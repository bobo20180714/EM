var BaseUtils = {
		autoCloseTime : 3000,
		//展示alert框
	    alert: function(msg){
			var m = "<h2>"+msg+"</h2>";
			/*bootbox.alert(m,func); */
			bootbox.alert({ 
				title:'提示',
			    size: 'small',
			    buttons: {  
		               ok: {  
		                    label: '确认'
		                }  
		            },  
			    message: m
			})
			//http://bootboxjs.com/documentation.html#bb-alert-dialog-default
		},
		//展示alert框,点确定后执行func
	    alertWithFunc: function(msg,func){
			var m = "<h2>"+msg+"</h2>";
			/*bootbox.alert(m,func); */
			bootbox.alert({ 
				title:'提示',
			    size: 'small',
			    buttons: {  
		               ok: {  
		                    label: '确认'
		                }  
		            },  
			    message: m,
			    callback: function(){ func(); }
			})
		},
		//展示alert框，点确定后执行func data为func的参数
	    alertWithParamAndFunc: function(msg,data,func){
			var m = "<h2>"+msg+"</h2>";
			/*bootbox.alert(m,func); */
			bootbox.alert({ 
				title:'提示',
			    size: 'small',
			    buttons: {  
		               ok: {  
		                    label: '确认'
		                }  
		            },  
			    message: m,
			    callback: function(){ func(data); }
			})
		},
		
		//-----自动关闭(auto close)start---------------------
		//展示alert框
	    acAlert: function(msg){
	    	
	    	
			var m = "<h2>"+msg+"</h2>";
			var b = bootbox.alertWithNoButtons({ 
				title:'提示',
			    size: 'small',
			    message: m
			});
			setTimeout(function () { 
				b.modal("hide");
		    },BaseUtils.autoCloseTime);
			//http://bootboxjs.com/documentation.html#bb-alert-dialog-default
		},
		//展示alert框,点确定后执行func
		acAlertWithFunc: function(msg,func){
			var m = "<h2>"+msg+"</h2>";
			var b = bootbox.alertWithNoButtons({ 
				title:'提示',
			    size: 'small',
			    message: m
			});
			setTimeout(function () { 
				b.modal("hide");
				func();
		    },BaseUtils.autoCloseTime);
		},
		//展示alert框，点确定后执行func data为func的参数
		acAlertWithParamAndFunc: function(msg,data,func){
			var m = "<h2>"+msg+"</h2>";
			var b = bootbox.alertWithNoButtons({ 
				title:'提示',
			    size: 'small',
			    message: m
			});
			setTimeout(function () { 
				b.modal("hide");
				func(data);
		    },BaseUtils.autoCloseTime);
		},
		//展示alert框,使用json方式
		acAlertWithJsonOption: function(option){
			var msg = "无提示";
			if(option.msg){
				msg = option.msg;
			}
			var data = option.data;
			var time = BaseUtils.autoCloseTime;
			if(option.time){
				time = option.time;
			}
			var m = "<h2>"+msg+"</h2>";
			var b = bootbox.alertWithNoButtons({ 
				title:'提示',
			    size: 'small',
			    message: m
			});
			setTimeout(function () { 
				b.modal("hide");
				if(option.func){
					option.func(data);
				}
		    },time);
		},
		
		//-----自动关闭end---------------------
		
		/*刷新指定id控件*/
		load : function(id,url){
			if(url==''||url==null){
				return;
			}
			Addtabs.getCurrentTabElementById(id).load(BaseUtils.getRootPath()+url);
		},
		//刷新主内容控件
		loadMain : function(url){
			if(url==''||url==null){
				return;
			}
			$('#main_content').load(BaseUtils.getRootPath()+url);
		},
		/*//刷新主内容控件
		loadMainContent : function(url,param){
			if(url==''||url==null){
				return;
			}
			$('#main_content').load(BaseUtils.getRootPath()+url+param);
		},*/
		//刷新主内容控件
		loadMainContent : function(url,param){
			if(url==''||url==null){
				return;
			}
			//$('#tab-content').load(BaseUtils.getRootPath()+url+param);
		},
		//展示正在处理框
		showWaitMsg : function(msg) {
			if (msg) {

			} else {
				msg = '正在处理，请稍候...';
			}
			/*var panelContainer = $("body");
			$("<div id='msg-background' class='wait-mask' style=\"display:block;z-index:10006;\"></div>").appendTo(panelContainer);
			var msgDiv = $("<div id='msg-board' class='wait-mask-msg' style=\"display:block;z-index:10007;left:50%\"></div>").html(msg).appendTo(
					panelContainer);
			msgDiv.css("marginLeft", -msgDiv.outerWidth() / 2);*/
			
			//Metronic.startPageLoading({animate: true});
			
			$.blockUI({ message: '<h3> '+msg+'</h3>' });
			
		},
		//获取项目路径
		getRootPath : function (){
		    return _rootPath_;
		},
		//获取触发事件
		getEvent:function() //同时兼容ie和ff的写法 
	    {  
	        if(document.all)  return window.event;    
	        func=BaseUtils.getEvent.caller;        
	        while(func!=null){  
	            var arg0=func.arguments[0]; 
	            if(arg0) 
	            { 
	              if((arg0.constructor==Event || arg0.constructor ==MouseEvent) || (typeof(arg0)=="object" && arg0.preventDefault && arg0.stopPropagation))
	              {  
	              return arg0; 
	              } 
	            } 
	            func=func.caller; 
	        } 
	        return null; 
	    },
	    /**
	     * 判断ajax请求是否超时，如果超时则做相应处理
	     */
	    ifNeedToSessionOut:function(data){
	    	if(data.msg=='sessionOut'){
	    		var _loginPath = BaseUtils.getRootPath()+"sessionTimeOut.do";
				if(window.top != window && document.referrer){
					 top.location.href = _loginPath;
				}else{
					window.location = _loginPath;
				}
			}
	    },
	    //隐藏正在处理框
		hideWaitMsg : function() {
			/*$('.wait-mask').remove();
			$('.wait-mask-msg').remove();*/
			$.unblockUI();
			//Metronic.stopPageLoading();
			//判断ajax请求是否超时，如果超时则做相应处理
		},
	    /**
	     * 
	     * @param msg 弹窗显示消息
	     * @param confirmFunction 点击确认后执行的方法名
	     */
	    defaultConfirm :function (msg,confirmFunction){
	    	bootbox.confirm({
				title:'提示',
			    size: 'small',
			    message: msg,
			    callback: function(result){ 
			    	if(result){
			    		confirmFunction();
			    	}
			    }
			});
	    	
	    	/*layer.confirm(msg, {
	    	    btn: ['确定','取消'] //按钮
	    	}, function(index){
	    		confirmFunction();
	    	    layer.close(index);
	    	});*/
	    },
	    defaultAjax : function (url,param,callBackFunction){
	    	BaseUtils.showWaitMsg();
	    	$.ajax({
	    		url: this.getNowUrl(url),
	    		type:"post",
	    		data: param,
	    		dataType:"json",
	    		async : false,
	    		success: function(data) {
	    			BaseUtils.hideWaitMsg();
	    			BaseUtils.ifNeedToSessionOut(data);
	    			if(callBackFunction == "" || callBackFunction == undefined || callBackFunction == null){
	    				
	    			}else{
	    				callBackFunction(data);
	    			}
	    			
	    		}
	    	});
	    },
	    getUrlParam : function (url){  
	        //构造一个含有目标参数的正则表达式对象  
	        var reg = new RegExp("(^|&)"+ url +"=([^&]*)(&|$)");  
	        //匹配目标参数  
	        var r = window.location.search.substr(1).match(reg);  
	        //返回参数值  
	        if (r!=null) return unescape(r[2]);  
	        return null;  
	    },
	    defaultLayerModule: function (title,url,width,height){
	    	var index = layer.open({
	    		title:title,
	    	    type: 2,
	    	    area: [width, height],
	    	    fix: false, //不固定
	    	    maxmin: true,
	    	    content: url
	    	});
	    	return index;
	    },
	    defaultMaxLayerModule: function (title,url){
	    	var index = layer.open({
	    		title:title,
	    	    type: 2,
	    	    //area: [width, height],
	    	    fix: false, //不固定
	    	    maxmin: false,
	    	    content: url
	    	});
	    	layer.full(index);
	    	return index;
	    },
	    addTab: function(title,url){
	    	var sear='</i>';
	    	if(!Addtabs){
	    		return;
	    	}
	        Addtabs.add({
	            id: title,
	            title: title,
	            content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
	            url: url,
	            ajax: false
	        });
	    },
	    addTabByFrame: function(title,url){
	    	var sear='</i>';
	    	if(!Addtabs){
	    		return;
	    	}
	        Addtabs.addInIFrame({
	            id: title,
	            title: title,
	            content: Addtabs.options.content ? Addtabs.options.content : $(this).attr('content'),
	            url: url,
	            ajax: false
	        });
	    },
	    /*closeTabByTitle : function(title){
	    	if(!Addtabs){
	    		return;
	    	}
	    	Addtabs.close("tab_"+title);
	    },
	    refreshTab : function(title,url){
	    	if(!Addtabs){
	    		return;
	    	}
	    	Addtabs.refresh(title,url);
	    	
	    },*/
	    refreshCurrentTab : function(){
	    	Addtabs.refreshCurrent();
	    	
	    },
	    redirectCurrentTab : function(url){
	    	Addtabs.redirectCurrent(url);
	    },
	    /*
	     * 获取当前Tab内容div元素对象的id
	     */
	    getCurrentTabContentId : function(){
	    	return Addtabs.getCurrentTabContentId();
	    },
	    /*
	     * 获取当前Tab内容div元素对象
	     */
	    getCurrentTabContentObj : function(){
	    	return Addtabs.getCurrentTabContentObj();
	    },
	    /*
	     * 根据id获取当前Tab内容div元素中的对象
	     */
	    getCurrentTabElementById : function(id){
	    	return Addtabs.getCurrentTabElementById(id);
	    },
	    /*
	     * 获取当前Tab元素加载时的idPrefix
	     */
	    getCurrentTabIdPrefix:function(){
	    	return Addtabs.getCurrentTabIdPrefix();
	    },
	    pointerX : function(event) { 
	    	return event.pageX || (event.clientX + (document.documentElement.scrollLeft || document.body.scrollLeft)); 
	    }, 
	    pointerY : function(event){
		    return event.pageY || (event.clientY + (document.documentElement.scrollTop || document.body.scrollTop)); 
		},
		/**
		 * 给url添加时间戳后缀，可防止ISP服务商缓存
		 */
		getNowUrl:function(url){
			if(url.indexOf("?")!=-1) {
				url=url+"&timeStamp="+jQuery.now();
			}else{
				url=url+"?timeStamp="+jQuery.now();
			}
			return url;
		}
};

/**
 * 提交form表单
 * 
 * @param url 
 * @param param 请求参数
 * @param callBackFunction 回调函数，处理返回data
 * 
 * 2018.4.16 增加防止XSS攻击功能，字段isEncode用于是否开启内容编码，如果为true则表示表单内容需要encode
 * 如果为false获取不填则表示表单内容不需要encode，建议使用true
 */
function defaultAjaxSubmit(formId, callBackFunction, isEncode){
	BaseUtils.showWaitMsg();
	var currentObj = Addtabs.getCurrentTabElementById(formId);
	if(typeof(currentObj)=="undefined"||currentObj.length==0){
		currentObj = $('#'+formId);
	}
	if(typeof(isEncode) == "undefined" || isEncode == null || isEncode.length == 0 || isEncode == '') {
		
	} else {
		var form = document.getElementById(formId); 
		for (var i = 0; i < form.length; i++) {
			var orgValue = form.elements[i].value;
			form.elements[i].value = HtmlEncode(orgValue);
		}
	}
	//获取token，防止csrf攻击
	var _csrftoken = document.getElementsByName("csrftoken")[0].value; 

	$.ajax({
		cache:false,
		beforeSend: function(request) {
            request.setRequestHeader("csrftoken", _csrftoken);
        },
		url: BaseUtils.getNowUrl(currentObj.attr("action")),
		type:"post",
		data: currentObj.serialize(),
		dataType:"json",
		success: function(data) {
			BaseUtils.hideWaitMsg();
			BaseUtils.ifNeedToSessionOut(data);
			if(callBackFunction == "" || callBackFunction == undefined || callBackFunction == null){
				
			}else{
				callBackFunction(data);
			}
		}
	});
}

/**
 * 注意：使用此方法时，必须在url后面跟上参数csrftoken=${csrftoken}。
 * 比如 defaultAjax("${ctx}/acRoleFunc/queryRoleFunc.do?csrftoken=${csrftoken}",param,acRoleCallBack);
 * @param url 请求链接
 * @param param 请求参数
 * @param callBackFunction 回调函数，处理返回data
 */
function defaultAjax(url,param,callBackFunction){
	var qs = getQueryString(url); 
	var _csrftoken = qs["csrftoken"];
	BaseUtils.showWaitMsg();
	$.ajax({
		url: BaseUtils.getNowUrl(url),
		type:"post",
		data: param,
		beforeSend: function(request) {
            request.setRequestHeader("csrftoken", _csrftoken);
        },
		dataType:"json",
		async : false,
		success: function(data) {
			BaseUtils.hideWaitMsg();
			BaseUtils.ifNeedToSessionOut(data);
			if(callBackFunction == "" || callBackFunction == undefined || callBackFunction == null){
				
			}else{
				callBackFunction(data);
			}
			
		}
	});
};

function getQueryString(url) {
	var indexOfQues = url.indexOf("?")
	var length = url.length;
	var qs = url.substring(indexOfQues + 1, length - 1);
	// var qs = url, // 获取url中"?"符后的字串
	args = {}, // 保存参数数据的对象
	items = qs.length ? qs.split("&") : [], // 取得每一个参数项,
	item = null, len = items.length;

	for (var i = 0; i < len; i++) {
		item = items[i].split("=");
		var name = decodeURIComponent(item[0]), value = decodeURIComponent(item[1]);
		if (name) {
			args[name] = value;
		}
	}
	return args;
}

/**
 * layer弹出框中调用ajax默认回调函数
 * 
 * 执行结果：提示是否保存成功，并且如果成功则关闭当前弹出框
 * 
 * @param data
 */
function defaultAjaxCallBackInLayerModule(data){
	//var ret = jQuery.parseJSON(data);
	BaseUtils.hideWaitMsg();
	BaseUtils.ifNeedToSessionOut(data);
	alert(data.msg);
	if (data.flag) {
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		parent.layer.close(index);
	}
}

/**
 * 
 * @param url 请求链接
 * @param param 请求参数
 * @param callBackFunction 回调函数，处理返回data
 */
function defaultDialog(title,url,width,height){
	layer.open({
		title:title,
	    type: 2,
	    area: [width, height],
	    fix: false, //不固定
	    maxmin: true,
	    content: url
	});
}

/***
 * html encode
 */
var HtmlEncode = function(str){

    var hex = new Array('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');
    var preescape = str;
    var escaped = "";
    for(var i = 0; i < preescape.length; i++){
        var p = preescape.charAt(i);
        escaped = escaped + escapeCharx(p);
    }
    
    return escaped;
                    
    function escapeCharx(original){
        var found=true;
        var thechar=original.charCodeAt(0);
        switch(thechar) {
            case 10: return "<br/>"; break; //newline
            case 32: return "&nbsp;"; break; //space
            case 34:return "&quot;"; break; //"
            case 38:return "&amp;"; break; //&
            case 39:return "&#x27;"; break; //'
            case 47:return "&#x2F;"; break; // /
            case 60:return "&lt;"; break; //<
            case 62:return "&gt;"; break; //>
            case 198:return "&AElig;"; break;
            case 193:return "&Aacute;"; break;
            case 194:return "&Acirc;"; break; 
            case 192:return "&Agrave;"; break; 
            case 197:return "&Aring;"; break; 
            case 195:return "&Atilde;"; break; 
            case 196:return "&Auml;"; break; 
            case 199:return "&Ccedil;"; break; 
            case 208:return "&ETH;"; break;
            case 201:return "&Eacute;"; break; 
            case 202:return "&Ecirc;"; break; 
            case 200:return "&Egrave;"; break; 
            case 203:return "&Euml;"; break;
            case 205:return "&Iacute;"; break;
            case 206:return "&Icirc;"; break; 
            case 204:return "&Igrave;"; break; 
            case 207:return "&Iuml;"; break;
            case 209:return "&Ntilde;"; break; 
            case 211:return "&Oacute;"; break;
            case 212:return "&Ocirc;"; break; 
            case 210:return "&Ograve;"; break; 
            case 216:return "&Oslash;"; break; 
            case 213:return "&Otilde;"; break; 
            case 214:return "&Ouml;"; break;
            case 222:return "&THORN;"; break; 
            case 218:return "&Uacute;"; break; 
            case 219:return "&Ucirc;"; break; 
            case 217:return "&Ugrave;"; break; 
            case 220:return "&Uuml;"; break; 
            case 221:return "&Yacute;"; break;
            case 225:return "&aacute;"; break; 
            case 226:return "&acirc;"; break; 
            case 230:return "&aelig;"; break; 
            case 224:return "&agrave;"; break; 
            case 229:return "&aring;"; break; 
            case 227:return "&atilde;"; break; 
            case 228:return "&auml;"; break; 
            case 231:return "&ccedil;"; break; 
            case 233:return "&eacute;"; break;
            case 234:return "&ecirc;"; break; 
            case 232:return "&egrave;"; break; 
            case 240:return "&eth;"; break; 
            case 235:return "&euml;"; break; 
            case 237:return "&iacute;"; break; 
            case 238:return "&icirc;"; break; 
            case 236:return "&igrave;"; break; 
            case 239:return "&iuml;"; break; 
            case 241:return "&ntilde;"; break; 
            case 243:return "&oacute;"; break;
            case 244:return "&ocirc;"; break; 
            case 242:return "&ograve;"; break; 
            case 248:return "&oslash;"; break; 
            case 245:return "&otilde;"; break;
            case 246:return "&ouml;"; break; 
            case 223:return "&szlig;"; break; 
            case 254:return "&thorn;"; break; 
            case 250:return "&uacute;"; break; 
            case 251:return "&ucirc;"; break; 
            case 249:return "&ugrave;"; break; 
            case 252:return "&uuml;"; break; 
            case 253:return "&yacute;"; break; 
            case 255:return "&yuml;"; break;
            case 162:return "&cent;"; break; 
            case '\r': break;
            default:
                found=false;
                break;
        }
        if(!found){
//            if(thechar>127) {
//                var c=thechar;
//                var a4=c%16;
//                c=Math.floor(c/16); 
//                var a3=c%16;
//                c=Math.floor(c/16);
//                var a2=c%16;
//                c=Math.floor(c/16);
//                var a1=c%16;
//                return "&#x"+hex[a1]+hex[a2]+hex[a3]+hex[a4]+";";        
//            }
//            else{
                return original;
 //           }
        }    
    }
}

/**
 * 与共通业务有关的方法
 */
var CommonBizUtils = {
	/**
	 * 多机构人员选择树，带排序模块
	 * 
	 * @param width 宽度 px
	 * @param height 高度 px
	 * @param max 是否最大化，有此项则高度宽度不需要
	 * @param withOrderModule 是否需要排序 yes:no
	 * @param callBackFuncName 回调函数名，用来处理点击保存后的json数组数据
	 * @param rootOrgId 定义的根机构节点主键，无则填''
	 * @param selectedEmpWithPidJson 默认选中的内容,格式[{id:xxxxxx,pId:xxxxxxxx},{id:xxxxxx,pId:xxxxxxxx}]
	 * @return index layer弹出窗的索引 可以据此关闭弹窗
	 */
	loadMultiOrgEmpCheckTree : function(option){
		var width = option.width;
		var height = option.height;
		var max = option.max;
		var rootPath = BaseUtils.getRootPath();
		var selectedEmpWithPidJson = encodeURIComponent(option.selectedEmpWithPidJson);
		if(width==null){
			width = '320px';
			height = '400px';
		}
		var index = BaseUtils.defaultLayerModule(
				'人员选择',
				rootPath+'omEmployee/forMultiOrgEmpCheckTree.do?rootOrgId='+option.rootOrgId+'&withOrderModule='+option.withOrderModule+'&callBackFuncName='+option.callBackFuncName+"&selectedEmpWithPidJson="+selectedEmpWithPidJson,
				option.width,
				option.height);
		if(max!=null&&max){
			layer.full(index);
		}
		return index;
	},

	/**
	 * 仅机构选择树，带排序模块
	 * 
	 * @param width 宽度 px
	 * @param height 高度 px
	 * @param max 是否最大化，有此项则高度宽度不需要
	 * @param withOrderModule 是否需要排序 yes:no
	 * @param callBackFuncName 回调函数名，用来处理点击保存后的json数组数据
	 * @param rootOrgId 定义的根机构节点主键，无则填''
	 * @param selectedOrgIdJson 默认选中的内容,格式[{id:xxxxxx},{id:xxxxxx}]
	 * @param orgLevel 要选择的树节点的级别
	 * @return index layer弹出窗的索引 可以据此关闭弹窗
	 */
	loadOrgOnlyCheckTree : function(option){
		var width = option.width;
		var height = option.height;
		var max = option.max;
		var rootPath = BaseUtils.getRootPath();
		var selectedOrgIdJson = encodeURIComponent(option.selectedOrgIdJson);
		var orgLevel = option.orgLevel;
		if(width==null){
			width = '320px';
			height = '400px';
		}
		var index = BaseUtils.defaultLayerModule(
				'机构选择',
				rootPath+'omOrganization/forOnlyOrgNodesCheckTree.do?rootOrgId='+option.rootOrgId+'&withOrderModule='+option.withOrderModule+'&callBackFuncName='+option.callBackFuncName+"&selectedOrgIdJson="+selectedOrgIdJson+"&orgLevel="+orgLevel,
				option.width,
				option.height);
		if(max!=null&&max){
			layer.full(index);
		}
		return index;
	}
}
