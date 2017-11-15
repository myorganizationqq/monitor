
 function popupDiv(div_id) {
  	var div_obj = $("#"+div_id);  
	var windowWidth =$(window).width();
	var windowHeight =$(window).height();
	var popupHeight = div_obj.height();       
	var popupWidth = div_obj.width();    
	//添加并显示遮罩层   
	$("<div id='backgroundPopup'></div>").css({"height": windowHeight,"opacity": "0.7"}) 
							  .click(function() {hideDiv(div_id); })   
							  .appendTo("body")   
							  .fadeIn(200);
	div_obj.css({"position": "absolute"})   
		   .animate({left: windowWidth*0.5-popupWidth*0.5,    
					 top: windowHeight*0.5-popupHeight*0.5, opacity: "show" }, "slow");	
					 
				$('.close').click(function(){
					hideDiv(div_id)
					})	 
}

 
function hideDiv(div_id) {   
	$("#backgroundPopup").remove();   
	$("#" + div_id).animate({left: 0, top: 0, opacity: "hide" }, "slow");   
} 
 
 
