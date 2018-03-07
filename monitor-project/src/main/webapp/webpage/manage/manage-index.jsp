<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>

	<style type="text/css">

		.panel-header{
			display: none;
		}
	</style>
<meta charset="UTF-8">
<title>Monitor</title>
<jsp:include page="header.jsp"></jsp:include> 
<script type="text/javascript" src="${requestScope.basePath }webpage/js/manage/amsWindow.js"></script>
<script type="text/javascript" src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript">
function changeOrg(orgId){
	//获取该orgId的角色列表
	$.ajax({
        url:basePath+'manage/system/loginController.do?getUserRolesByOrg',
        type:"POST",
        dataType:"json",
        data:{'orgId':orgId},
        success: function(d){
        	if(d.resultCode==0){
        		var data=d.data;
        		if(data!=null){
        			var roles=data.roles;
            		if(roles.length==1){//切换单位和角色都只有一个            			
           				var roleId=roles[0].id;
           				change(orgId,roleId);           			
            		}else{//多个角色，选择框
            			$(this).amsWindow({
            				container : 'changeWindow',
            				type : 'add',
            				para:'{"orgId":"'+orgId+'"}',
            				url : basePath+'webpage/manage/login/change.jsp',
            				title : '选择切换角色',
            				width : 400,
            				height : 320,
            				top : '15%'
            			});
            		}
        		}else{//角色0个
        			change(orgId,null);
        		}        		
        	}           
        }
    });		
}

function change(orgId,roleId){
	$.ajax({
        url:basePath+'manage/system/loginController.do?changeOrgRole',
        type:"POST",
        dataType:"json",
        data:{'orgId':orgId,'roleId':roleId},
        success: function(d){
        	if(d.resultCode==0){
        		window.parent.location.href=basePath+"manage/system/loginController.do?login";
        	}else{
        		$.messager.alert("提示",'单位或角色切换出错');
        	}             
        }
    });	
}
</script>
<style type="text/css">

</style>
</head>
<body class="bg">
<div class="easyui-layout main" fit="true" >

  
  <!--框架头部-->
  <div data-options="region:'north'" style="height:100px;  overflow:hidden; width:99%; " id="north_blk">
    <div id="header">
      <div class="nav">
        <div class="logo"><a ><img src="${requestScope.basePath }webpage/image/sysimages/logo_v04.png" /></a></div>
        <div class="SetBord"> 
        	<a onclick="openPath('manage/system/loginController.do?logout')" class="exit">
        		<img src="${requestScope.basePath }webpage/image/sysimages/exit.png" /> 安全退出 &nbsp;
        	</a>
        	<span class="useArea" style="font-size: larger;color: rgb(255,255,255)">
        		<!-- #4abae3 -->

				${roleName }

        	</span> 
        </div>
      </div>
    </div>
  </div>
  
   <!--按纽控制左侧导航伸缩--> 
  <div id="LeftM" data-options="region:'west',split:false" title=" " style="width:200px;border-right:5px solid #e6eef8; overflow-y:auto; overflow-x:hidden">
    <div id="showMenuDiv" style="width:194px">
      <ul class="easyui-tree" data-options="animate:true"  id="lefttree" style="overflow-y:auto;overflow-x:auto;">

	   		<!-- 菜单动态加载 -->
      			<jsp:include page="menu-dynamic.jsp"></jsp:include>

      </ul>
    </div>
  </div>


  <!--main-->
  <div data-options="region:'center',border:false"   id="rightCompoent"  >
    <IFRAME id="mainFrame"  name="mainFrame" src="${requestScope.basePath }webpage/manage/myworkset.jsp" frameBorder=no  width="99%" height="100%"  scrolling="no" onLoad="" > </IFRAME>
    <div id="tt" class="easyui-tabs maincenter"  data-options=""  fit="true"  style="display:none" > </div>
  </div>
</div>

<script>
	function openPath(path){
	    $('#mainFrame').attr('src', "${requestScope.basePath }"+path);
	}

	$(function(){
	 	$('#tt').height(0)
 		winWidth=$(window).width()
		winHeight=$(window).height()
		leftWidth=$('#LeftM').outerWidth(true)
 	 	$('.layout-split-west').height(winHeight-80)
	});

	function init() {
		winWidth = $(window).width();
		winHeight = $(window).height();
		leftWidth = $('#LeftM').outerWidth(true);
		$('#lefttree').height(winHeight - 100);
	}
</script> 
</body>
</html>