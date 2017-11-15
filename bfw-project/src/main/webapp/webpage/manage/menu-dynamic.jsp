<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script>
$(function() {
	menu();	
})
// 菜单加载
var menu = function loadMenu() {
	$.ajax({
		type : "POST",
		//url : basePath+'manage/system/functionManagerController.do?getFuncList',  
		url : basePath+'manage/system/loginController.do?getMenu',  
		success : function(rsData) {
			var json = JSON.parse(rsData)
			var data = json.data;
			var htmlStr = gen(0, data);

			var ulStart = "<ul class=\"easyui-tree\" data-options=\"animate:true\"  id=\"lefttree\" style=\"overflow-y:auto; overflow-x:auto; \">";
			var ulEnd = "</ul>";

			$("#showMenuDiv").html("");
			$("#showMenuDiv").html(ulStart + htmlStr + ulEnd);

			$.parser.parse("#showMenuDiv");
			init();
		}
	}); 

}
// 生成菜单
var gen = function generateMenu(level, children) {
	if (children != null && children != undefined) {
		var html = "";
		for ( var tmp in children) {
			if (level == 0) {// is top menu

				if (children[tmp].children != null
						&& children[tmp].children != undefined
						&& children[tmp].children.length > 0) {// has children
					html += "<li data-options=\"state:'closed'\"><span><a onclick=\"javascript:void(0);\"><em class=\"color_2679b1\">"
					html += children[tmp].functionName;
					html += "</em></a></span><ul>";
					html += gen((level + 1), children[tmp].children);
					html += "</ul></li>";
				} else {// no children
					html += "<li data-options=\"state:'open'\"><span><a onclick=\"addProfessialPanel('"
							+ children[tmp].functionUrl
							+ "',this)\"><em class=\"color_2679b1\">"
					html += children[tmp].functionName;
					html += "</em></a></span></li>";
				}
			} else if (level == 1) {// is second menu
				if (children[tmp].children != null
						&& children[tmp].children != undefined
						&& children[tmp].children.length > 0) {// has children
					html += "<li data-options=\"state:'closed'\"><span><a><em class=\"fnt12\">";
					html += children[tmp].functionName;
					html += "</em></a></span><ul>";
					html += gen((level + 1), children[tmp].children);
					html += "</ul></li>";
				} else {// no children
					html += "<li data-options=\"state:'open'\"><span><a onclick=\"addProfessialPanel('"
							+ children[tmp].functionUrl
							+ "',this);\"><em class=\"fnt12\">";
					html += children[tmp].functionName;
					html += "</em></a></span></li>";
				}
			} else if (level == 2) {// is third menu
				if (children[tmp].children != null
						&& children[tmp].children != undefined
						&& children[tmp].children.length > 0) {// has children
					html += "<li data-options=\"state:'closed'\"><span><a onclick=\"javascript:void(0);\">";
					html += children[tmp].functionName;
					html += "</a><span><ul>";
					html += gen((level + 1), children[tmp].children);
					html += "</ul></li>"
				} else {// no children
					html += "<li data-options=\"state:'open'\"><span><a onclick=\"addProfessialPanel('"
							+ children[tmp].functionUrl + "',this)\">";
					html += children[tmp].functionName;
					html += "</a><span></li>";
				}
			} else if (level == 3) {// is fourth menu
				if (children[tmp].children != null
						&& children[tmp].children != undefined
						&& children[tmp].children.length > 0) {// has children
					html += "<li data-options=\"state:'closed'\"><span><a onclick=\"javascript:void(0);\">";
					html += children[tmp].functionName;
					html += "</a><span><ul>";
					html += gen((level + 1), children[tmp].children);
					html += "</ul></li>"
				} else {// no children
					html += "<li data-options=\"state:'open'\"><span><a onclick=\"addProfessialPanel('"
							+ children[tmp].functionUrl + "',this)\">";
					html += "<i class=\"TreeLayout_4\">";
					html += children[tmp].functionName;
					html += "</i></a><span></li>";
				}
			} else if (level == 4) {// is fivth menu
				if (children[tmp].children != null
						&& children[tmp].children != undefined
						&& children[tmp].children.length > 0) {// has children
					html += "<li data-options=\"state:'closed'\"><span><a href=\"javascript:void(0);\"><i class=\"TreeLayout_4\"><font class=\"TreeLayout_5\">";
					html += children[tmp].functionName;
					html += "</font></i></a></span></li>";
				} else {// no children
					html += "<li data-options=\"state:'open'\"><span><a href=\"addProfessialPanel('"
							+ children[tmp].functionUrl
							+ "',this);\"><i class=\"TreeLayout_4\"><font class=\"TreeLayout_5\">";
					html += children[tmp].functionName;
					html += "</font></i></a></span></li>";
				}
			}
		}
		return html;
	}
	return "";
}
// 菜单切换
function addProfessialPanel(url, click) {
	$('#mainFrame').attr('src', basePath + url);
	menuNav(click);
}
//传入 dom ,然后就通过jquery工具函数一系列调用,最后渲染#locationTips
function menuNav(click){
	var  array=[];
	click = $(click)
	var text = click.text();
	array.push(text);
	while (click.closest("ul").parent().attr('id') != "showMenuDiv") {
		var textVar = click.closest("ul").parent().children().first().text();
		array.push(textVar);
		click = click.closest("ul").parent();
	}
	var b = array.reverse();
	var strTotal='';
	for (var index=0;index<b.length; index++){
		if (index==0){
		strTotal=''+b[index]	;
		}else{
			strTotal=strTotal+'->>'+b[index];
		}
	}
	$('#locationTips').html(strTotal);
}
</script>