//var isPassed=false;
/*var key;
function bodyRSA() {
		setMaxDigits(130);
		key = new RSAKeyPair(
				"10001",
				"",
				"90d67ad7bbc764292a93869e1e4117d78b14b90bdf5008afcf0a10fed5edc2b6060eeff663f0f0cd95322f9ffe5635f0790ec29a4a715c764add68f7ac85c579bd78e7cb92ce4fb99f11b25c8d3862956a792945829f459d40e944d55bd4ee88a0fa0bfde09198579e22c2983bdb63070f62b538d12ffe1743740b609cb92b4b");
	}*/
function submitLoginForm(){
	var userId = $("input[name='username']");
	var password = $("input[name='password']").val();
	/*bodyRSA();
	var password = encryptedString(key, encodeURIComponent(password));*/
	var remember = document.getElementById("remember").checked;
	var validCode = $("input[name='validateCode']").val();
//	if(!isPassed){
//		$('#dangerMessage').html("请拖动滑块进行验证！");
//		$('.alert-danger', $('.login-form')).show();
//		return;
//	}
	
	$.ajax({
		type: "post",
		async:true,
		global:false,
		url: "loginCheck.do",
		dataType:"json",
		data:{"userId":$.trim(userId.val()),"password":password,"remember":remember,"validCode":$.trim(validCode)},
		success: function(data) {
			//BaseUtils.hideWaitMsg();
			
			/* var ret = jQuery.parseJSON(data); */
			if(data.flag){
				//BaseUtils.showWaitMsg();
				window.location.href="main.do";
			}else{
				$('#dangerMessage').html(data.msg);
                $('.alert-danger', $('.login-form')).show();
			}
			
		}
	});
}

//$.fn.drag = function(options){
//    var x, drag = this, isMove = false, defaults = {
//    };
//    var options = $.extend(defaults, options);
//    //添加背景，文字，滑块
//    var html = '<div class="drag_bg"></div>'+
//                '<div class="drag_text" onselectstart="return false;" unselectable="on">拖动滑块验证</div>'+
//                '<div class="handler handler_bg"></div>';
//    this.append(html);
//    
//    var handler = drag.find('.handler');
//    var drag_bg = drag.find('.drag_bg');
//    var text = drag.find('.drag_text');
//    var maxWidth = drag.width() - handler.width();  //能滑动的最大间距
//    
//    //鼠标按下时候的x轴的位置
//    handler.mousedown(function(e){
//        isMove = true;
//        x = e.pageX - parseInt(handler.css('left'), 10);
//    });
//    
//    //鼠标指针在上下文移动时，移动距离大于0小于最大间距，滑块x轴位置等于鼠标移动距离
//    $(document).mousemove(function(e){
//        var _x = e.pageX - x;
//        if(isMove){
//            if(_x > 0 && _x <= maxWidth){
//                handler.css({'left': _x});
//                drag_bg.css({'width': _x});
//            }else if(_x > maxWidth){  //鼠标指针移动距离达到最大时清空事件
//                dragOk();
//            }
//        }
//    }).mouseup(function(e){
//        isMove = false;
//        var _x = e.pageX - x;
//        if(_x < maxWidth){ //鼠标松开时，如果没有达到最大距离位置，滑块就返回初始位置
//            handler.css({'left': 0});
//            drag_bg.css({'width': 0});
//        }
//    });
//    
//    //清空事件
//    function dragOk(){
//        handler.removeClass('handler_bg').addClass('handler_ok_bg');
//        text.text('验证通过');
//        drag.css({'color': '#fff'});
//       // isPassed=true;
//        handler.unbind('mousedown');
//        $(document).unbind('mousemove');
//        $(document).unbind('mouseup');
//    }
//}

var Login = function() {
    var handleLogin = function() {
        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "Username is required."
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   
            	$('#dangerMessage').html("请输入用户名和密码!");
                $('.alert-danger', $('.login-form')).show();
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
            	submitLoginForm();
                //form.submit(); // form validation success, call ajax form submit
            }
        });

        $('.login-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                	
                	submitLoginForm()
                    //$('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    }

    var handleForgetPassword = function() {
        $('.forget-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                email: {
                    required: true,
                    email: true
                }
            },

            messages: {
                email: {
                    required: "Email is required."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   

            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function(form) {
                form.submit();
            }
        });

        $('.forget-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.forget-form').validate().form()) {
                    $('.forget-form').submit();
                }
                return false;
            }
        });

        jQuery('#forget-password').click(function() {
            jQuery('.login-form').hide();
            jQuery('.forget-form').show();
        });

        jQuery('#back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.forget-form').hide();
        });

    }

    var handleRegister = function() {
        function format(state) {
            if (!state.id) return state.text; // optgroup
            return "<img class='flag' src='../../assets/global/img/flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
        }

        if (jQuery().select2) {
	        $("#select2_sample4").select2({
	            placeholder: '<i class="fa fa-map-marker"></i>&nbsp;Select a Country',
	            allowClear: true,
	            formatResult: format,
	            formatSelection: format,
	            escapeMarkup: function(m) {
	                return m;
	            }
	        });

	        $('#select2_sample4').change(function() {
	            $('.register-form').validate().element($(this)); //revalidate the chosen dropdown value and show error or success message for the input
	        });
    	}

        $('.register-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {

                fullname: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                address: {
                    required: true
                },
                city: {
                    required: true
                },
                country: {
                    required: true
                },

                username: {
                    required: true
                },
                password: {
                    required: true
                },
                rpassword: {
                    equalTo: "#register_password"
                },

                tnc: {
                    required: true
                }
            },

            messages: { // custom messages for radio buttons and checkboxes
                tnc: {
                    required: "Please accept TNC first."
                }
            },

            invalidHandler: function(event, validator) { //display error alert on form submit   

            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function(error, element) {
                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
                    error.insertAfter($('#register_tnc_error'));
                } else if (element.closest('.input-icon').size() === 1) {
                    error.insertAfter(element.closest('.input-icon'));
                } else {
                    error.insertAfter(element);
                }
            },

            submitHandler: function(form) {
                form.submit();
            }
        });

        $('.register-form input').keypress(function(e) {
            if (e.which == 13) {
                if ($('.register-form').validate().form()) {
                    $('.register-form').submit();
                }
                return false;
            }
        });

        jQuery('#register-btn').click(function() {
            jQuery('.login-form').hide();
            jQuery('.register-form').show();
        });

        jQuery('#register-back-btn').click(function() {
            jQuery('.login-form').show();
            jQuery('.register-form').hide();
        });
    }

    return {
        //main function to initiate the module
        init: function() {
            handleLogin();
            handleForgetPassword();
            handleRegister();
            $("input[name='username']").focus();

        }

    };

}();
