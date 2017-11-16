$(document).ready(function() {	
	
	$.extend($.fn.validatebox.defaults.rules, { 
		//最小字符长度校验
        minLength: {     
            validator: function(value, param){
                return value.length >= param[0];     
            },     
            message: '输入内容长度不能小于{0}位.'    
        } ,  
       //最大字符长度校验
        maxLength: {     
            validator: function(value, param){     
                return param[0] >= value.length;     
            },     
            message: '输入内容长度不能大于{0}位.'    
        },
        //菜单的url校验
        urlCheck: {     
			 validator: function (value, param) {
					 return true;
			},
			message: 'url只能由字母、数字、斜杠或下划线组成.'    
	    } 
    }); 
	
});