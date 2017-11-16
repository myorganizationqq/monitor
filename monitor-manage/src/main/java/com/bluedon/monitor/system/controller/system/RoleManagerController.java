package com.bluedon.monitor.system.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluedon.monitor.system.entity.TbCommonOperation;
import com.bluedon.monitor.system.entity.TbCommonRole;
import com.bluedon.monitor.system.model.system.RoleManagerModel;
import com.bluedon.monitor.system.model.util.ComboTree;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.service.system.IFunctionManagerService;
import com.bluedon.monitor.system.service.system.IRoleManagerService;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.common.util.model.ModelMapper;
import com.bluedon.monitor.common.util.model.ModelMapperFactory;
import com.bluedon.monitor.system.entity.TbCommonFunction;
import com.bluedon.monitor.system.entity.TbCommonRoleFunction;

/**
 * @author : yangdandan
 * @date:2015年4月13日
 * @module: 系统模块 
 * @comment: 角色管理控制器
 */
@Controller
@RequestMapping("/manage/system/roleManagerController")
public class RoleManagerController {
	//日志记录对象
	private static final Logger log = Logger.getLogger(RoleManagerController.class);

	//用户角色逻辑处理对象
	@Autowired
	@Qualifier("roleManagerServiceImpl")
	private IRoleManagerService roleManagerService;

	//用户角色逻辑处理对象
	@Autowired
	@Qualifier("functionManagerServiceImpl")
	private IFunctionManagerService functionManagerService;


	/**
	 * 加载列表页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toPageList")
	public ModelAndView toPageList(HttpServletRequest req){		
		return new ModelAndView("manage/system/roleManage");
	}

	/**
	 * 获取角色列表
	 * @param param
	 * @param pageUtil
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(params="getRoleList")
	public void pageList(RoleManagerModel param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){

		log.debug("获取参数:"+CommonUtil.Object2String(param));
		log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

		//参数解码
		ModelMapper mapper = ModelMapperFactory.getModelMapper(param);
		Object obj = mapper.decodeModel(null);
		RoleManagerModel modelParam = null;
		if(obj instanceof RoleManagerModel){
			modelParam = (RoleManagerModel)obj;
		}

		//获取分页数据
		pageUtil = this.roleManagerService.getPageList(modelParam, pageUtil);

		//返回分页数据
		ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
	}

	/**
	 * 加载新增页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toAdd")
	public ModelAndView toAdd(HttpServletRequest req){		
		return new ModelAndView("manage/system/window/roleDetail");
	}

	/**
	 * 加载修改页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toEdit")
	public ModelAndView toEdit(HttpServletRequest req){	
		String id=req.getParameter("id");
		TbCommonRole role=this.roleManagerService.getRole(Long.parseLong(id));
		req.setAttribute("bkCommonRole", role);

		String load=req.getParameter("load");
		if(!StringUtil.isEmpty(load)){
			req.setAttribute("load", load);
		}
		return new ModelAndView("manage/system/window/roleDetail");
	}

	/**
	 * 保存角色信息
	 * @param model
	 * @param rsp
	 */
	@RequestMapping(params="saveOrUpdate")
	public void saveOrUpdate(TbCommonRole model,HttpServletResponse rsp){
		boolean result = false;
		Long id=model.getId();
		if(id!=null){//修改
			log.debug("操作：修改，id="+id);
			TbCommonRole role=this.roleManagerService.getRole(id);
			model.setCreateTime(role.getCreateTime());
			model.setUpdateTime(new Date());		
			result = roleManagerService.updateRole(model);

		}else{//新增
			log.debug("操作：新增");
			//创建时间
			model.setCreateTime(new Date());
			result = roleManagerService.saveRole(model);

		}		
		log.debug("=========保存结果========="+result);
		OperResult rs = new OperResult();
		if(result){
			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setMsg("操作成功");
		}else{ 
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("操作失败");
		}
		ToolUtil.getData(rsp, rs);
	}

	/**
	 * 删除角色信息
	 * @param model
	 */
	@RequestMapping(params="delete")
	public void delete(HttpServletRequest req,HttpServletResponse rsp){		
		String id=req.getParameter("id");
		OperResult rs = new OperResult();
		
		/* 
		 //强制删除标识
		   String forceDelete = req.getParameter("forceDelete");	
		   if (!StringUtil.isEmpty(forceDelete)){
			boolean result = roleManagerService.deleteRole(Long.parseLong(id));
			if(result){
				rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
				rs.setMsg("删除成功");
			}else{ 
				rs.setResultCode(ConstantUtil.RESULT_FAILED);
				rs.setMsg("删除失败");
			}
		} else {
			boolean existRel = roleManagerService.existRel(Long.parseLong(id));
			if(!existRel){
				boolean result = roleManagerService.deleteRole(Long.parseLong(id));
				if(result){
					rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
					rs.setMsg("删除成功");
				}else{ 
					rs.setResultCode(ConstantUtil.RESULT_FAILED);
					rs.setMsg("删除失败");
				}
			}else{ 
				rs.setResultCode(ConstantUtil.RESULT_FAILED);
				rs.setMsg("delete");
			}
		}*/
		boolean existRel = roleManagerService.existRel(Long.parseLong(id));
		if(!existRel){
			boolean result = roleManagerService.deleteRole(Long.parseLong(id));
			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setMsg("删除成功");
		}else{ 
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("该角色关联了用户或菜单，请先解除关系");
		}
		
		ToolUtil.getData(rsp, rs);
	}


	/**
	 * 角色列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toAllocationToUser")
	public ModelAndView toAllocationToUser(HttpServletRequest req){	
		String id=req.getParameter("roleId");
		TbCommonRole role=this.roleManagerService.getRole(Long.parseLong(id));
		req.setAttribute("roleInfo", role);

		return new ModelAndView("manage/system/window/allocationToUsers");
	}

	/**
	 * 角色列表页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toAllocationToMenu")
	public ModelAndView toAllocationToMenu(HttpServletRequest req){	
		String id=req.getParameter("roleId");
		TbCommonRole role=this.roleManagerService.getRole(Long.parseLong(id));
		req.setAttribute("roleId", id);

		return new ModelAndView("manage/system/window/allocationToMenu");
	}

	/**
	 * 设置权限
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "setAuthority")
	@ResponseBody
	public List<ComboTree> setAuthority(HttpServletRequest request, ComboTree comboTree) {
		String roleId = request.getParameter("roleId");
		//获取所有菜单
		List<TbCommonFunction> functionList = functionManagerService.getFuncListForRoot();
		//已选权限菜单
		List<TbCommonFunction> selectedlist = functionManagerService.getSelectedFuncList(Long.parseLong(roleId));

		List<ComboTree> functionC = functionManagerService.comTree(functionList);
		List<ComboTree> selectedC = functionManagerService.comTree(selectedlist);

		for (ComboTree c : functionC){
			if(c.getChildren()!=null && c.getChildren().size()!=0){
				for (ComboTree cc : c.getChildren()){
					for (ComboTree dd : selectedC){
						if (cc.getId().equals(dd.getId())){
							cc.setChecked(true);
						}
					}
				}
			}
		}

		return functionC;
	}      

	/**
	 * 设置按钮
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "setOperation")
	@ResponseBody
	public List<ComboTree> setOperation(HttpServletRequest request,ComboTree comboTree) {
		@SuppressWarnings("unused")
		String roleId = request.getParameter("roleId");

		String functionId = request.getParameter("functionId");

		//获取菜单下的所有按钮
		List<TbCommonOperation> functionlist = functionManagerService.getOperationByFunId(Long.parseLong(functionId));
		//获取当前角色当前菜单下的按钮
		List<TbCommonRoleFunction> rf = functionManagerService.getSelectedOperation(Long.parseLong(roleId),Long.parseLong(functionId));
		TbCommonRoleFunction rel = null;
		if (rf!=null && rf.size()==1){
			rel = rf.get(0);
		}

		List<ComboTree> tree = new ArrayList<ComboTree>();
		ComboTree t = null;
		for (TbCommonOperation tb : functionlist){
			t = new ComboTree();
			if (rel!=null && rel.getOperationId()!=null && rel.getOperationId().contains(String.valueOf(tb.getId()))){
				t.setChecked(true);
			}
			t.setId(String.valueOf(tb.getId()));
			t.setText(tb.getOperateName());
			tree.add(t);
		}


		return tree;
	}

	/**
	 * 更新按钮权限
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "updateAuthority")
	@ResponseBody
	public void updateAuthority(HttpServletRequest request) {
		String roleId =request.getParameter("roleId");
		String rolefunctions = request.getParameter("rolefunctions");
		String selFunctionId = request.getParameter("selFunctionId");
		String operationId = request.getParameter("operationId");

		List<Long> oldFunctions = new ArrayList<Long>();
		List<Long> newFunctions = new ArrayList<Long>();

		List<TbCommonFunction> selectedFunctions =functionManagerService.getSelectedFuncList(Long.parseLong(roleId));

		for (TbCommonFunction f : selectedFunctions){
			oldFunctions.add((f.getId()));
		}
		String[] roleFunctions = rolefunctions.split(",");
		Set<Long> newFunctionss = new HashSet<Long>();
		for (String s : roleFunctions) {
			newFunctionss.add(Long.parseLong(s));
		}
		for(Long functionId : newFunctionss){
			newFunctions.add(functionId);
		}

		functionManagerService.updateRoleFunctionRel(Long.parseLong(roleId),oldFunctions, newFunctions, Long.parseLong(selFunctionId), operationId);

	}
}
