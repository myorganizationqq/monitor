 //头部search 
 $(function(){
			  var str='记录片'
          	$('.s_i').focus(function(){
				var f_html=$(this).val()
				if(f_html==str){
					$(this).val('')
					
					}
 				}).blur(function(){
				var b_html=$(this).val()
				if(b_html==''){
					$(this).val(str)
 					}	
					
					})
			 })
			 

//tab 切换			 
function tabEvent(myobj,obj,indx){
				 $(myobj).addClass('on').siblings('a').removeClass('on')
				$(this).addClass('on').siblings('a').removeClass('on')
				$('#'+obj).find('.itme').hide(0)
				$('#'+obj).find('.itme').eq(indx).show(0)
 				}		
				
	 			
				
				
		
 $(function(){
	
	 //初始化
 	$('.popbox').hide(0);
		//视频排行榜定位
   	  var offsetleft=$('#videoRank').offset().left;
 	  var popWidth=$('.popbox').outerWidth(true);
	 // alert(offsetleft-popWidth)
	  $('.popbox').css({'left':offsetleft-popWidth+100});
 	  
	    //用户登录定位
  	var useoffsetleft=$('.useLoginHeader').offset().left;	
	var u_width=$('.ManangeUse').outerWidth(true)
 	$('.ManangeUse').css({'left':useoffsetleft-u_width+100});
  		
 	 $('.shopWind').hover(function(){
 		 $(this).children('.popbox').slideDown('fast')
 		 },function(){
			  $(this).children('.popbox').slideUp(0)
			 })
  			
 	 $('.useLoginHeader').hover(function(){
 		 $(this).children('.ManangeUse').slideDown('fast')
 		 },function(){
			  $(this).children('.ManangeUse').slideUp(0)
			 })	 
			 
			 
			 
			 
			 
			 
			 
			 
			 
   				
 	  })
	  
	  
	  
	  

	  
	  
	