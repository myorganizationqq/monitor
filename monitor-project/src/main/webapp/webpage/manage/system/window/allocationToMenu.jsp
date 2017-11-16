<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include.jsp"></jsp:include>
<script type="text/javascript"
	src="${requestScope.basePath }webpage/js/manage/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${requestScope.basePath }webpage/js/public.js"></script>
<script type="text/javascript"
	src="${requestScope.basePath }webpage/js/manage/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${requestScope.basePath }webpage/js/manage/logic/system/allocationToUsers.js"></script>
	
<script type="text/javascript">
	$(function() {
		$('#functionid').tree({
			checkbox : true,
			url : 'roleManagerController.do?setAuthority&roleId=${roleId}',
			onLoadSuccess : function(node) {
				expandAll();
			},
			onClick: function(node){
				$('#operationid').tree({
					checkbox : true,
					url : 'roleManagerController.do?setOperation&roleId=${roleId}&functionId='+node.id,
					onLoadSuccess : function(node1) {
						expandAll();
					},
					onCheck: function(node1){
						$('#functionid').tree('check',node.target);  
					}
				})
			}
		});
		$("#functionListPanel").panel({title :"菜单列表",}
		);
		
		$("#operationListPanel").panel({title :"按钮列表",}
		);
	});

	function GetNode() {
		var node = $('#functionid').tree('getChecked');
		var cnodes = '';
		var pnodes = '';
		var pnode = null; //保存上一步所选父节点
		for ( var i = 0; i < node.length; i++) {
			if ($('#functionid').tree('isLeaf', node[i].target)) {
				cnodes += node[i].id + ',';
				pnode = $('#functionid').tree('getParent', node[i].target); //获取当前节点的父节点
				while (pnode!=null) {//添加全部父节点
					pnodes += pnode.id + ',';
					pnode = $('#functionid').tree('getParent', pnode.target); 
				}
			}
		}
		cnodes = cnodes.substring(0, cnodes.length - 1);
		pnodes = pnodes.substring(0, pnodes.length - 1);
		return cnodes + "," + pnodes;
	};
	
	function expandAll() {
		var node = $('#functionid').tree('getSelected');
		if (node) {
			$('#functionid').tree('expandAll', node.target);
		} else {
			$('#functionid').tree('expandAll');
		}
	}
	/* function selecrAll() {
		var node = $('#functionid').tree('getRoots');
		for ( var i = 0; i < node.length; i++) {
			var childrenNode =  $('#functionid').tree('getChildren',node[i].target);
			for ( var j = 0; j < childrenNode.length; j++) {
				$('#functionid').tree("check",childrenNode[j].target);
			}
	    }
	} */
	
	//变更菜单按钮资源
	function changeResource(){
		var arr = 0; //已选择的按钮数组
		var arrayObj = 0;//已选择的按钮数组
		var selFunctionId = 0; //当前选中菜单
		var operationId = 0; //右侧选中按钮
		var rolefunctions = 0;  //获取左侧已选择的菜单
		
		var text = $('#operationid').text();
		if(text!="请点击菜单选择按钮"){
			arr = $('#operationid').tree('getChecked');
			arrayObj = new Array([arr.length]);
			for (var i in arr){
				arrayObj[i] = arr[i].id;
			}
			operationId = arrayObj.join(',');
			selFunctionId = $('#functionid').tree('getSelected').id;
		}
		//获取左侧已选择的菜单
		rolefunctions = GetNode();
		
		$.ajax({
			async : false,
			cache : false,
			timeout:3000,
			type : 'POST',
			url : 'roleManagerController.do?updateAuthority',
			data:{
				rolefunctions : rolefunctions,
				roleId : ${roleId},
				operationId : operationId,
				selFunctionId : selFunctionId
			},
			error : function() {
			},
			success : function(data) {
				$.messager.alert('提示',"更新菜单按钮成功");
			}
		}); 
	}
</script>


<body>
	<div class="tahoma_font  tdstyle"
		style="padding-top: 5px; padding-bottom: 10px; padding-left: 10px;">
		<table cellpadding="5"  width="560px">
			<tr>
				<td colspan="4">
					<table width="570" cellpadding="0" cellspacing="1">
						<tr>
							<!-- 菜单 -->
							<td height="400" width="250px" valign="top">
								<div class="easyui-panel" style="padding: 1px; width: 200px"
									fit="true" border="false" id="functionListPanel">
									<ul id="functionid"></ul>
								</div>
							</td>
							<!-- 按钮 -->
							<td height="400" width="250px" valign="top">
								<div class="easyui-panel" style="padding: 1px;" fit="true"
									border="false" id="operationListPanel">
									<ul id="operationid">请点击菜单选择按钮</ul>
								</div>
							</td>
						</tr>
					</table>
			<tr>
				<td colspan="4">
					<div align="right">
						<a id="saveOperation" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 50px;"
							onclick="changeResource();">&nbsp;&nbsp;保存&nbsp;&nbsp;</a>
						&nbsp;&nbsp; <a id="closeOperation" href="javascript:void(0)"
							class="easyui-linkbutton" style="width: 50px;"
							onclick="closeWin();">&nbsp;&nbsp;关闭&nbsp;&nbsp;</a>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>