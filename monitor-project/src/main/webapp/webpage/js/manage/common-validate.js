$.extend($.fn.validatebox.defaults.rules, {
    length:{validator:function(value,param){
        var len=$.trim(value).length;
        return len>=param[0]&&len<=param[1];
    },
        message:"输入内容长度必须介于{0}和{1}之间."
    },
    email : {// 验证邮箱
        validator : function(value) {
            return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/i.test(value);
        },
        message : '邮箱的格式不正确,请使用下面的邮箱格式:zhangsan@sian.com'
    },
    phone : {// 验证电话号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '格式不正确,请使用下面格式:020-88888888'
    },
    mobile : {// 验证手机号码
        validator : function(value) {
            return /^\d{11}$/i.test(value);
        },
        message : '手机号码格式不正确'
    },

    integer : {// 验证整数
        validator : function(value) {
            console.log(value);
            return /^[0-9]+$/i.test(value);
        },
        message : '请输入整数'
    }
});