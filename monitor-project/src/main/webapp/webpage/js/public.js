/*var basePath = 'http://localhost:8080/bfw-project/';*/
var basePath = $("#basePath").val();

// 时间转换工具类 把时间格式转成字符串格式
Date.prototype.pattern=function(fmt) {

    var o = {

        "M+" : this.getMonth()+1, //月份

        "d+" : this.getDate(), //日

        "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时

        "H+" : this.getHours(), //小时

        "m+" : this.getMinutes(), //分

        "s+" : this.getSeconds(), //秒

        "q+" : Math.floor((this.getMonth()+3)/3), //季度

        "S" : this.getMilliseconds() //毫秒

    };

    var week = {

        "0" : "\u65e5",

        "1" : "\u4e00",

        "2" : "\u4e8c",

        "3" : "\u4e09",

        "4" : "\u56db",

        "5" : "\u4e94",

        "6" : "\u516d"

    };

    if(/(y+)/.test(fmt)){

        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));

    }

    if(/(E+)/.test(fmt)){

        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);

    }

    for(var k in o){

        if(new RegExp("("+ k +")").test(fmt)){

            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));

        }

    }

    return fmt;
}

function addDate(date,days){
    var d=new Date();
    if(date){
        d=new Date(date);
    }
    d.setDate(d.getDate()+days);
    var month=d.getMonth()+1;
    var day = d.getDate();
    if(month<10){
        month = "0"+month;
    }
    if(day<10){
        day = "0"+day;
    }
    var val = d.getFullYear()+"-"+month+"-"+day;
    return val;
}