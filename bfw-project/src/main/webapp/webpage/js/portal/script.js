$(function(){
					 
			$('.top_aside ul li').last().css({'border':'none'}) 
					
					})
					
					
					
	
	//设置Tab
var setTab = function(tabName,m,n){
	var t=setTimeout(function(){
		for(var i=1; i<=n; i++){
			var navTab =document.getElementById(tabName+i);
			var divTab =document.getElementById("c_"+tabName+"_"+i);
			
			if(navTab) navTab.className = i==m ? "current" : "";
			if(divTab) divTab.style.display = i==m ? "block" : "none";
		}
	},300);
	clearTimeout(this.t);
}
//添加Tab的事件
var addTabEvent=function(tabName,n,type){
	
	for(var j=1; j<=n; j++){
		var tabId =document.getElementById(tabName+j);
		addEvent(tabId,type,function(){setTab(tabName,this.id.replace(tabName,""),n);});
	}
}
/*
添加监听事件
obj:对象；
type:事件名，要去掉on;
fn:函数体
*/
function addEvent( obj, type, fn ) { 
	if (obj.addEventListener) obj.addEventListener( type, fn, false ); 
	else if (obj.attachEvent) { 
		obj["e"+type+fn] = fn; 
		obj.attachEvent( "on"+type, function() { obj["e"+type+fn](); } ); 
	} 
}

/*收起/展开*/
function Toggle(objId,obj){
	$(objId).toggle();
	//alert($(this).text());
	if($(objId).is(":hidden")){
		$("#"+obj.id).css({'background-position': '0 bottom'}).text("展开").attr('title', '点击展开');
	}else{
		$("#"+obj.id).css({'background-position': '0 top'}).text("收起").attr('title', '点击收起');
	}
}
function Toggle2(o,obj){
	$(o).toggle();
	if($(o).is(":hidden")){
		$("#"+obj.id).html("更多 &darr;").attr('title', '点击展开更多');
	}else{
		$("#"+obj.id).html("收起 &uarr;").attr('title', '点击收起更多');
	}
}



function Marquee(){
 this.ID = document.getElementById(arguments[0]);
 if(!this.ID){
  alert("您要设置的\"" + arguments[0] + "\"初始化错误\r\n请检查标签ID设置是否正确!");
  this.ID = -1;
  return;
 }
 this.Direction = this.Width = this.Height = this.DelayTime = this.WaitTime = this.Correct = this.CTL = this.StartID = this.Stop = this.MouseOver = 0;
 this.Step = 1;
 this.Timer = 30;
 this.DirectionArray = {"top":0 , "bottom":1 , "left":2 , "right":3};
 if(typeof arguments[1] == "number")this.Direction = arguments[1];
 if(typeof arguments[2] == "number")this.Step = arguments[2];
 if(typeof arguments[3] == "number")this.Width = arguments[3];
 if(typeof arguments[4] == "number")this.Height = arguments[4];
 if(typeof arguments[5] == "number")this.Timer = arguments[5];
 if(typeof arguments[6] == "number")this.DelayTime = arguments[6];
 if(typeof arguments[7] == "number")this.WaitTime = arguments[7];
 if(typeof arguments[8] == "number")this.ScrollStep = arguments[8]
 this.ID.style.overflow = this.ID.style.overflowX = this.ID.style.overflowY = "hidden";
 this.ID.noWrap = true;
 this.IsNotOpera = (navigator.userAgent.toLowerCase().indexOf("opera") == -1);
 if(arguments.length >= 7)this.Start();
}

Marquee.prototype.Start = function(){
 if(this.ID == -1)return;
 if(this.WaitTime < 800)this.WaitTime = 800;
 if(this.Timer < 20)this.Timer = 20;
 if(this.Width == 0)this.Width = parseInt(this.ID.style.width);
 if(this.Height == 0)this.Height = parseInt(this.ID.style.height);
 if(typeof this.Direction == "string")this.Direction = this.DirectionArray[this.Direction.toString().toLowerCase()];
 this.HalfWidth = Math.round(this.Width / 2);
 this.BakStep = this.Step;
 this.ID.style.width = this.Width;
 this.ID.style.height = this.Height;
 if(typeof this.ScrollStep != "number")this.ScrollStep = this.Direction > 1 ? this.Width : this.Height;
 var msobj = this;
 var timer = this.Timer;
 var delaytime = this.DelayTime;
 var waittime = this.WaitTime;
 msobj.StartID = function(){msobj.Scroll()}
 msobj.Continue = function(){
     if(msobj.MouseOver == 1){
      setTimeout(msobj.Continue,delaytime);
     }else{ 
	  clearInterval(msobj.TimerID);
      msobj.CTL = msobj.Stop = 0;
      msobj.TimerID = setInterval(msobj.StartID,timer);
     }
    }

 msobj.Pause = function(){
    msobj.Stop = 1;
    clearInterval(msobj.TimerID);
    setTimeout(msobj.Continue,delaytime);
   }

 msobj.Begin = function(){
   msobj.ClientScroll = msobj.Direction > 1 ? msobj.ID.scrollWidth : msobj.ID.scrollHeight;
   if((msobj.Direction <= 1 && msobj.ClientScroll <msobj.Height) || (msobj.Direction > 1 && msobj.ClientScroll <msobj.Width))return;
   msobj.ID.innerHTML += msobj.ID.innerHTML;
   msobj.TimerID = setInterval(msobj.StartID,timer);
   if(msobj.ScrollStep < 0)return;
   msobj.ID.onmousemove = function(event){
       if(msobj.ScrollStep == 0 && msobj.Direction > 1){
        var event = event || window.event;
        if(window.event){
         if(msobj.IsNotOpera){
          msobj.EventLeft = event.srcElement.id == msobj.ID.id ? event.offsetX - msobj.ID.scrollLeft : event.srcElement.offsetLeft - msobj.ID.scrollLeft + event.offsetX;
         }else{
          msobj.ScrollStep = null;
          return;
         }
        }else{
         msobj.EventLeft = event.layerX - msobj.ID.scrollLeft;
        }
        msobj.Direction = msobj.EventLeft > msobj.HalfWidth ? 3 : 2;
        msobj.AbsCenter = Math.abs(msobj.HalfWidth - msobj.EventLeft);
        msobj.Step = Math.round(msobj.AbsCenter * (msobj.BakStep*2) / msobj.HalfWidth);
       }
      }
   msobj.ID.onmouseover = function(){
       if(msobj.ScrollStep == 0)return;
       msobj.MouseOver = 1;
       clearInterval(msobj.TimerID);
      }
   msobj.ID.onmouseout = function(){
       if(msobj.ScrollStep == 0){
        if(msobj.Step == 0)msobj.Step = 1;
        return;
       }
       msobj.MouseOver = 0;
       if(msobj.Stop == 0){
        clearInterval(msobj.TimerID);
        msobj.TimerID = setInterval(msobj.StartID,timer);
       }
      }
  }
 setTimeout(msobj.Begin,waittime);
}

Marquee.prototype.Scroll = function(){
 switch(this.Direction){
  case 0:
   this.CTL += this.Step;
   if(this.CTL >= this.ScrollStep && this.DelayTime > 0){
    this.ID.scrollTop += this.ScrollStep + this.Step - this.CTL;
    this.Pause();
    return;
   }else{
    if(this.ID.scrollTop >= this.ClientScroll){
     this.ID.scrollTop -= this.ClientScroll;
    }
    this.ID.scrollTop += this.Step;
   }
  break;

  case 1:
   this.CTL += this.Step;
   if(this.CTL >= this.ScrollStep && this.DelayTime > 0){
    this.ID.scrollTop -= this.ScrollStep + this.Step - this.CTL;
    this.Pause();
    return;
   }else{
    if(this.ID.scrollTop <= 0){
     this.ID.scrollTop += this.ClientScroll;
    }
    this.ID.scrollTop -= this.Step;
   }
  break;

  case 2:
   this.CTL += this.Step;
   if(this.CTL >= this.ScrollStep && this.DelayTime > 0){
    this.ID.scrollLeft += this.ScrollStep + this.Step - this.CTL;
    this.Pause();
    return;
   }else{
    if(this.ID.scrollLeft >= this.ClientScroll){
     this.ID.scrollLeft -= this.ClientScroll;
    }
    this.ID.scrollLeft += this.Step;
   }
  break;

  case 3:
   this.CTL += this.Step;
   if(this.CTL >= this.ScrollStep && this.DelayTime > 0){
    this.ID.scrollLeft -= this.ScrollStep + this.Step - this.CTL;
    this.Pause();
    return;
   }else{
    if(this.ID.scrollLeft <= 0){
     this.ID.scrollLeft += this.ClientScroll;
    }
    this.ID.scrollLeft -= this.Step;
   }
  break;
 }
}

/*弹出层*/
/*遮罩层*/
function popMask(){
	if($('#jsMask').length){
		$('#jsMask').animate({opacity:0.5,background:"#000"}).show();	
	}else{
		$("<div id=\"jsMask\"></div>").appendTo("body").css({"height":$("body").height(),opacity:0}).animate({opacity:0.5}).show();
	}
	/*
	$('#jsMask').dblclick(function(){
		$('#jsMask').animate({opacity:0.1}).hide();
	})
	*/
}

function popWin(obj,strTitle,strContent){
	var windowWidth =$(window).width();   
	var windowHeight =$(window).height();   
	var popupHeight = $('#jsPopup').height();   
	var popupWidth = $('#jsPopup').width();
	if($('#jsPopup').length){
		$('#jsPopup').fadeIn();
	}else{
		$("<div class=\"winPopup\" id=\"jsPopup\"></div>").appendTo(obj).fadeIn().html("<div class=\"bar_tit\"><strong id=\"jsWinTitle\">"+strTitle+"</strong><span><a href=\"javascript:;\" id=\"jsCloseButton\"><img src=\"/web_images/b.gif\" width=\"19\" height=\"19\" /></a></span></div><div class=\"cont\" id=\"jsWinContent\"></div>").css({"top":(windowHeight-popupHeight)/2+$(document).scrollTop(),"left":(windowWidth-popupWidth)/2});
	}
	$('#jsCloseButton').click(function(){
		closeWin();
	});
	$("#jsWinTitle").html(strTitle);
	$("#jsWinContent").html(strContent);
}
function closeWin(){
	$('#jsPopup').fadeOut().remove();
	if($('#jsMask').length){
		$('#jsMask').animate({opacity:0}).hide().remove();
	}
}

function msgWin(){
	popMask();
	var strHtml="";
	popWin("body","取消"+type+"提示",strHtml);
}

//取消收藏提示
function notCollect(obj,type,url){
	$(obj).click(function(){
		popMask();
		var id=$(this).attr("id");
		var itemObj=$(this).parent();
		//alert($(this).attr("id"))
		var strHtml="";
		strHtml+="<div class=\"not_collect\"><p class=\"tip_1\">您确定要取消收藏该"+type+"吗？</p></div>";
		strHtml+="<p class=\"btnArea\"><input type=\"button\" class=\"btn_1\" value=\"确 定\" id=\"jsBtnOK\" />&nbsp;&nbsp;&nbsp;<a class=\"bule u\" href=\"javascript:;\" onclick=\"closeWin()\">取 消</a></p>";
		popWin("body","取消"+type+"提示",strHtml);
		$("#jsBtnOK").click(function(){
			
			$.ajax({
			   type: "POST",
			   url: url,
			   data: "id="+id,
			   success: function(msg){
					if(msg=="1"){
						$("#jsWinContent").html("<div class=\"not_collect\"><p class=\"tip_2\">取消成功！</p></div>");
						itemObj.remove();//移除页面数据
					}else{
						$("#jsWinContent").html("<div class=\"not_collect\"><p class=\"tip_2\">取消失败！</p></div>");
					}
			   },
			   error: function(){
				   $("#jsWinContent").html("<div class=\"not_collect\"><p class=\"tip_2\">系统出错了！</p></div>");
				}
			});
		});
	});
}

//收藏操作
function notCollect00(obj,type,url){
	
}
