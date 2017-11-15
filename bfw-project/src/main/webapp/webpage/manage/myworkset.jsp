<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>webpage/css/syscss/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>webpage/css/syscss/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>webpage/css/syscss/sys_style.css">
<script type="text/javascript" src="<%=basePath %>webpage/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>webpage/js/manage/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="<%=basePath %>webpage/js/manage/amsWindow.js"></script>
</head>
<body>
<script type="text/javascript">
		$(document).ready(function(){
			//弹窗
			$('#show').click(function(){
			 
			    $(this).amsWindow({
			        container:'newWindow',
			        type:'add',
			        para:'',
			        url: 'window/用户修改.html',
			        title:'用户修改',
			        width:785,
			        height:500,
			        top:'15%'
				 
			    });
			});
		});
</script>
</head>
<br/>
<div  style="width:99%; padding-top:10px; padding-left:10px"  id="centerMain"  > 
  <!--input 表格模块-->
  <div class="tahoma_font tableStyle_5_0" style="overflow:hidden">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100" ><label>用户账户：</label></td>
        <td >adminstrator</td>
      </tr>
      <tr>
        <td width="100" ><label>用户名称：</label></td>
        <td >项少羽 <a href="javascript:void(0)" class="easyui-linkbutton" value="open"  id="show">[信息修改]</a></td>
      </tr>
      <tr>
        <td width="100" ><label>用户角色：</label></td>
        <td >内容管理员</td>
      </tr>
      <tr>
        <td width="100" ><label>上次登录时间：</label></td>
        <td >2015-1-13 14:09:12</td>
      </tr>
    </table>
  </div>
  
  <!--上下距间-->
  <div class="easyui-layout" style="height:15px"></div>
  <div class="line"></div>
  <!--gap-->
  <div class="easyui-layout" style="height:15px"></div>
  
  
  <div class="easyui-tabs">
    <div title="我的访问">
      <div class="easyui-layout" style="height:400px; padding:10px" >
        <table id="myviste" class="easyui-datagrid"  style=""
			data-options="singleSelect:true,fit:true,collapsible:false,url:'<%=basePath %>webpage/manage/system/orgManage/json/我的访问.json',method:'get'">
          <thead>
          
          
          </thead>
        </table>
        <script>
$(function(){//生成表格
			$('#myviste').datagrid({
  				fitColumns: true,
				nowrap:false,
				rownumbers:true,
				showFooter:true,
 				columns:[[
					//{field:'itemid',title:'内容序号',width:150,align:'center',checkbox:true},
					{field:'AccessTime',title:'<span class="txtleft">访问时间</span>',width:350,align:'center'},//此方法用来控制文本左对齐
					{field:'listprice',title:'<span class="txtleft">访问操作</span>',width:180,align:'center'},
					{field:'AccessAdress',title:'<span class="txtleft">访问地址</span>',width:160,align:'center'},
  				]],
onLoadSuccess:function(){
$(".txtleft").parent().parent().css({"text-align":"center",'font-weight':'bold','color':'#333'});
} 			});
		});

</script> 
      </div>
    </div>
  </div>
  
  <!--数据表格--> 
  
</div>
<script>
  //全选checkbox
  $('#myviste').datagrid(
   { selectOnCheck:$(this).is(':checked')} )
  //提交表单
 		function submitForm(){
			$('#useManage').form('submit');
		}
		function clearForm(){
			$('#useManage').form('clear');
		}
		
		 
	</script> 
<script>
//控制分页
		function getData(){
			var rows = [];
			for(var i=1; i<=800; i++){
				var amount = Math.floor(Math.random()*1000);
				var price = Math.floor(Math.random()*1000);
				rows.push({
					inv: 'Inv No '+i,
					date: $.fn.datebox.defaults.formatter(new Date()),
					name: 'Name '+i,
					amount: amount,
					price: price,
					cost: amount*price,
					note: 'Note '+i
				});
			}
			return rows;
		}
		
		function pagerFilter(data){
			if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
				data = {
					total: data.length,
					rows: data
				}
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage:function(pageNum, pageSize){
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh',{
						pageNumber:pageNum,
						pageSize:pageSize
					});
					dg.datagrid('loadData',data);
				}
			});
			if (!data.originalRows){
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}
		
		$(function(){
			$('#myviste').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
		});
	</script>

</body>
</html>