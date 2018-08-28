var formEditor = UE.getEditor('formEditor',{
    toolleipi:true,//是否显示，设计器的 toolbars
    textarea: 'design_content',
    //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
    toolbars:[[
        'fullscreen', 'source', '|', 'undo', 'redo', '|','bold', 'italic', 'underline', 'fontborder', 'strikethrough',  'removeformat', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist','|', 'fontfamily', 'fontsize', '|', 'indent', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',  'link', 'unlink',  '|',  'horizontal',  'spechars',  'wordimage', '|', 'inserttable', 'deletetable',  'mergecells',  'splittocells', 'drafts']],
    //focus时自动清空初始化时的内容
    //autoClearinitialContent:true,
    //关闭字数统计
    wordCount:false,
    //关闭elementPath
    elementPathEnabled:false,
    allowDivTransToP: false, //用于做响应式布局
    //默认的编辑区域高度
    initialFrameHeight:300/*,
    iframeCssUrl:"../theme/assets/global/plugins/bootstrap/css/bootstrap.min.css"*/ //引入自身 css使编辑器兼容你网站css
    //更多其他参数，请参考ueditor.config.js中的配置项
});

var leipiFormDesign = {
    /*执行控件*/
    exec : function (method) {
    	formEditor.execCommand(method);
    },
    
   parse_form:function(template,fields){
       //正则  radios|checkboxs|select 匹配的边界 |--|  因为当使用 {} 时js报错 (plugins|fieldname|fieldflow)
       var preg =  /(\|-<span(((?!<span).)*plugins=\"(radios|checkboxs|select)\".*?)>(.*?)<\/span>-\||<(img|input|textarea|select).*?(<\/select>|<\/textarea>|\/>))/gi,preg_attr =/(\w+)=\"(.?|.+?)\"/gi,preg_group =/<input.*?\/>/gi;
       if(!fields) fields = 0;

       var template_parse = template,template_data = new Array(),add_fields=new Object(),checkboxs=0;

       var pno = 0;
       template.replace(preg, function(plugin,p1,p2,p3,p4,p5,p6){
           var parse_attr = new Array(),attr_arr_all = new Object(),name = '', select_dot = '' , is_new=false;
           var p0 = plugin;
           var tag = p6 ? p6 : p4;
           //alert(tag + " \n- t1 - "+p1 +" \n-2- " +p2+" \n-3- " +p3+" \n-4- " +p4+" \n-5- " +p5+" \n-6- " +p6);

           if(tag == 'radios' || tag == 'checkboxs')
           {
               plugin = p2;
           }else if(tag == 'select')
           {
               plugin = plugin.replace('|-','');
               plugin = plugin.replace('-|','');
           }
           plugin.replace(preg_attr, function(str0,attr,val) {
               if(attr=='name')
               {
                   if(val=='NEWFIELD')
                   {
                       is_new=true;
                       fields++;
                       val = 'data_'+fields;
                   }
                   name = val;
               }

               if(tag=='select' && attr=='value')
               {
                   if(!attr_arr_all[attr]) attr_arr_all[attr] = '';
                   attr_arr_all[attr] += select_dot + val;
                   select_dot = ',';
               }else
               {
                   attr_arr_all[attr] = val;
               }
               var oField = new Object();
               oField[attr] = val;
               parse_attr.push(oField);
           })
          // alert(JSON.stringify(parse_attr));
           return;
           if(tag =='checkboxs') //复选组  多个字段 
           {
               plugin = p0;
               plugin = plugin.replace('|-','');
               plugin = plugin.replace('-|','');
               var name = 'checkboxs_'+checkboxs;
               attr_arr_all['parse_name'] = name;
               attr_arr_all['name'] = '';
               attr_arr_all['value'] = '';

               attr_arr_all['content'] = '<span plugins="checkboxs"  title="'+attr_arr_all['title']+'">';
               var dot_name ='', dot_value = '';
               p5.replace(preg_group, function(parse_group) {
                   var is_new=false,option = new Object();
                   parse_group.replace(preg_attr, function(str0,k,val) {
                       if(k=='name')
                       {
                       	if(val=='NEWFIELD')
                           {
                               is_new=true;
                               fields++;
                               val = 'data_'+fields;
                           }

                           attr_arr_all['name'] += dot_name + val;
                           dot_name = ',';

                       }
                       else if(k=='value')
                       {
                           attr_arr_all['value'] += dot_value + val;
                           dot_value = ',';

                       }
                       option[k] = val;
                   });

                   if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                   attr_arr_all['options'].push(option);
                   if(!option['checked']) option['checked'] = '';
                   var checked = option['checked'] ? 'checked="checked"' : '';
                   attr_arr_all['content'] +='<input type="checkbox" name="'+option['name']+'" value="'+option['value']+'" fieldname="' + attr_arr_all['fieldname'] + option['fieldname'] + '" fieldflow="' + attr_arr_all['fieldflow'] + '" '+checked+'/>'+option['value']+'&nbsp;';

                   if(is_new)
                   {
                       var arr = new Object();
                       arr['name'] = option['name'];
                       arr['plugins'] = attr_arr_all['plugins'];
                       arr['fieldname'] = attr_arr_all['fieldname'] + option['fieldname'];
                       arr['fieldflow'] = attr_arr_all['fieldflow'];
                       add_fields[option['name']] = arr;
                   }

               });
               attr_arr_all['content'] += '</span>';

               //parse
               template = template.replace(plugin,attr_arr_all['content']);
               template_parse = template_parse.replace(plugin,'{'+name+'}');
               template_parse = template_parse.replace('{|-','');
               template_parse = template_parse.replace('-|}','');
               template_data[pno] = attr_arr_all;
               checkboxs++;

           }else if(name)
           {
               if(tag =='radios') //单选组  一个字段
               {
                   plugin = p0;
                   plugin = plugin.replace('|-','');
                   plugin = plugin.replace('-|','');
                   attr_arr_all['value'] = '';
                   attr_arr_all['content'] = '<span plugins="radios" name="'+attr_arr_all['name']+'" title="'+attr_arr_all['title']+'">';
                   var dot='';
                   p5.replace(preg_group, function(parse_group) {
                       var option = new Object();
                       parse_group.replace(preg_attr, function(str0,k,val) {
                           if(k=='value')
                           {
                               attr_arr_all['value'] += dot + val;
                               dot = ',';
                           }
                           option[k] = val;
                       });
                       option['name'] = attr_arr_all['name'];
                       if(!attr_arr_all['options']) attr_arr_all['options'] = new Array();
                       attr_arr_all['options'].push(option);
                       if(!option['checked']) option['checked'] = '';
                       var checked = option['checked'] ? 'checked="checked"' : '';
                       attr_arr_all['content'] +='<input type="radio" name="'+attr_arr_all['name']+'" value="'+option['value']+'"  '+checked+'/>'+option['value']+'&nbsp;';

                   });
                   attr_arr_all['content'] += '</span>';

               }else
               {
                   attr_arr_all['content'] = is_new ? plugin.replace(/NEWFIELD/,name) : plugin;
               }
               //attr_arr_all['itemid'] = fields;
               //attr_arr_all['tag'] = tag;
               template = template.replace(plugin,attr_arr_all['content']);
               template_parse = template_parse.replace(plugin,'{'+name+'}');
               template_parse = template_parse.replace('{|-','');
               template_parse = template_parse.replace('-|}','');
               if(is_new)
               {
                   var arr = new Object();
                   arr['name'] = name;
                   arr['plugins'] = attr_arr_all['plugins'];
                   arr['title'] = attr_arr_all['title'];
                   arr['orgtype'] = attr_arr_all['orgtype'];
                   arr['fieldname'] = attr_arr_all['fieldname'];
                   arr['fieldflow'] = attr_arr_all['fieldflow'];
                   add_fields[arr['name']] = arr;
               }
               template_data[pno] = attr_arr_all;


           }
           pno++;
       })
       var view = template.replace(/{\|-/g,'');
       view = view.replace(/-\|}/g,'');
       var parse_form = new Object({
           'fields':fields,//总字段数
           'template':template,//完整html
           'parse':view,
           'data':template_data,//控件属性
           'add_fields':add_fields//新增控件
       });
       return JSON.stringify(parse_form);
   },
   /*type  =  save 保存设计 versions 保存版本  close关闭 */
   fnCheckForm : function ( type ) {
	   
       if(formEditor.queryCommandState( 'source' ))
    	   formEditor.execCommand('source');//切换到编辑模式才提交，否则有bug
           
       if(formEditor.hasContents()){
    	   formEditor.sync();/*同步内容*/
           
           var type_value='',formid=0,fields=$("#fields").val(),formeditor='';

           if( typeof type!=='undefined' ){
               type_value = type;
           }
           //获取表单设计器里的内容
           formeditor=formEditor.getContent();
           //解析表单设计器控件
           var parse_form = this.parse_form(formeditor,fields);
           
           $("#leipi_type").val(type_value);
           $("#leipi_parse_form").val(parse_form);
           
           $("#saveform").attr("target","_blank");
           $("#saveform").attr("action","/index/parse.html");
           $("#saveform").submit();

            /*//异步提交数据
            $.ajax({
               type: 'POST',
               url : '/index/parse.html',
               //dataType : 'json',
               data : {'type' : type_value,'formid':formid,'parse_form':parse_form},
               success : function(data){
                   if(confirm('查看js解析后，提交到服务器的数据，请临时允许弹窗'))
                   {
                       win_parse=window.open('','','width=800,height=600');
                       //这里临时查看，所以替换一下，实际情况下不需要替换  
                       data  = data.replace(/<\/+textarea/,'&lt;textarea');
                       win_parse.document.write('<textarea style="width:100%;height:100%">'+data+'</textarea>');
                       win_parse.focus();
                   }
                   
                   /*
                 if(data.success==1){
                     alert('保存成功');
                     $('#submitbtn').button('reset');
                 }else{
                     alert('保存失败！');
                 }* /
               }
           });*/
           
       } else {
           alert('表单内容不能为空！')
           $('#submitbtn').button('reset');
           return false;
       }
   } ,
   /*预览表单*/
   fnReview : function (){
	   
       if(formEditor.queryCommandState( 'source' )) {
    	  
    	   formEditor.execCommand('source');/*切换到编辑模式才提交，否则部分浏览器有bug*/
       }
           
       if(formEditor.hasContents()){
    	   formEditor.sync();       /*同步内容*/
           
    	   var type_value='',formid=0,fields=$("#fields").val(),formeditor='';
    	   
           //获取表单设计器里的内容
           formeditor=formEditor.getContent();
           //解析表单设计器控件
           var parse_form = this.parse_form(formeditor,fields);
 	   
    	   $("input[name=JSON_CONTENT]").val(parse_form);
    	   
           /*设计form的target 然后提交至一个新的窗口进行预览*/
    	   //定义显示预览的窗口
    	   document.formDesignForm.target="previewWindow";
    	   //弹出预览窗口
           window.open('','previewWindow',"menubar=0,toolbar=0,status=0,resizable=1,left=0,top=0,scrollbars=1,width=" +(screen.availWidth-10) + ",height=" + (screen.availHeight-50) + "\"");
           //表单值传递给spring mvc controller进行解析
           document.formDesignForm.action="preview.do";
           document.formDesignForm.submit(); //提交表单
           
       } else {
           alert('表单内容不能为空！');
           return false;
       }
   },
   /*保存表单*/
   fnSave : function (){
	   
       if(formEditor.queryCommandState( 'source' )) {
    	  
    	   formEditor.execCommand('source');/*切换到编辑模式才提交，否则部分浏览器有bug*/
       }
           
       if(formEditor.hasContents()){
    	   formEditor.sync();       /*同步内容*/
           
    	   var type_value='',formid=0,fields=$("#fields").val(),formeditor='';
    	   
           //获取表单设计器里的内容
           formeditor=formEditor.getContent();
           //解析表单设计器控件
           var parse_form = this.parse_form(formeditor,fields);
 	   
    	   $("input[name=JSON_CONTENT]").val(parse_form);
    	   
           document.formDesignForm.action="update.do";
           
       } else {
           alert('表单内容不能为空！');
           return false;
       }
   }
    
};


//var a=$(window).height()-155;
//a-=96;(a=a-96)
//$("#appDetailDiv").css("height",a);

//设置提交规则
$("#formDesignForm").validate({
	rules: {
		HTML_CONTENT: {
			required: true
		},
		FORM_DESIGN_NAME: {
			required: true
		},
		FORM_DESIGN_CODE: {
			required: true
		},
		MODEL_CODE: {
			required: true
		}
	},
	//改变默认的提交方法
	submitHandler:function(form){
		//leipiFormDesign.fnCheckForm('save');
		
		if(formEditor.queryCommandState( 'source' ))
            formEditor.execCommand('source');//切换到编辑模式才提交，否则有bug
		 if(formEditor.hasContents()){
			 formEditor.sync();/*同步内容*/ 
		 }else {
		       alert('表单内容不能为空！')
		       return false;
		 }
		
		defaultAjaxSubmit('formDesignForm',function(data){
			//BaseUtils.alert(data.msg); 
			//阻滞写法
			BaseUtils.alertWithParamAndFunc(data.msg,data,function(data){
				if (data.flag) {
					$("#ajax_lg").modal('hide');
					parent.formDesign_dataTable.ajax.reload( null, false);//第一个参数是回调函数，第二个是是否重置页面第一页
					
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
					
				}
			}); 
		});
	}
 });