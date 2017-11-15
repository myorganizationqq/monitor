package com.bluedon.bfw.system.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluedon.bfw.common.util.CommonUtil;
import com.bluedon.bfw.common.util.PageUtil;
import com.bluedon.bfw.common.util.StringUtil;
import com.bluedon.bfw.common.util.model.ModelMapper;
import com.bluedon.bfw.common.util.model.ModelMapperFactory;
import com.bluedon.bfw.system.entity.TbCommonFunction;
import com.bluedon.bfw.system.entity.TbCommonOperation;
import com.bluedon.bfw.system.entity.TbCommonOrg;
import com.bluedon.bfw.system.model.system.MenuModel;
import com.bluedon.bfw.system.model.system.OrgManagerModel;
import com.bluedon.bfw.system.model.system.RoleManagerModel;
import com.bluedon.bfw.system.model.util.ComboTree;
import com.bluedon.bfw.system.model.util.OperResult;
import com.bluedon.bfw.system.service.system.IFunctionManagerService;
import com.bluedon.bfw.system.util.ConstantUtil;
import com.bluedon.bfw.system.util.ToolUtil;


/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:菜单管理控制器
 */
@Controller
@RequestMapping("/manage/system/functionManagerController")
public class FunctionManagerController {


	//日志记录对象
	private static final Logger log = Logger.getLogger(FunctionManagerController.class);

	//用户角色逻辑处理对象
	@Autowired
	@Qualifier("functionManagerServiceImpl")
	private IFunctionManagerService functionManagerService;

	/**
	 * 加载列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toPageList")
	public ModelAndView toPageList(HttpServletRequest req){		
		return new ModelAndView("manage/system/functionManage");
	}

	/**
	 * 获取菜单列表
	 * @param request
	 */
	@RequestMapping(params="getFuncList")
	public void pageList(OrgManagerModel param,PageUtil pageUtil,HttpServletRequest req,HttpServletResponse rsp){

		log.debug("获取参数:"+CommonUtil.Object2String(param));
		log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

		OperResult rs = new OperResult();
		List<TbCommonFunction> menuList = this.functionManagerService.getFuncListForRoot();
		for (TbCommonFunction node : menuList){
			build(node); 
		}

		rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
		rs.setData(JSONArray.fromObject(menuList).toArray());
		ToolUtil.getData(rsp, rs);
	}

	private void build(TbCommonFunction node){  
		List<TbCommonFunction> children = functionManagerService.getFuncListByParentId(node.getId());
		node.setChildren(children);
		if (!children.isEmpty()) {  
			for (TbCommonFunction child : children) {  
				build(child);  
			}  
		}   
	}  

	/**
	 * 加载新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toAdd")
	public ModelAndView toAdd(HttpServletRequest req){	
		//选中节点id传递作为其父节点
		String parentId=req.getParameter("parentId");
		if(!StringUtil.isEmpty(parentId)){
			req.setAttribute("funcParentId", parentId);
		}		
		return new ModelAndView("manage/system/window/functionDetail");
	}



	/**
	 * 加载修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toEdit")
	public ModelAndView toEdit(HttpServletRequest req){	
		String id=req.getParameter("id");
		TbCommonFunction func=this.functionManagerService.getFunc(Long.parseLong(id));
		req.setAttribute("commonFunc", func);

		String load=req.getParameter("load");
		if(!StringUtil.isEmpty(load)){
			req.setAttribute("load", load);
		}
		return new ModelAndView("manage/system/window/functionDetail");
	}

	/**
	 * 保存菜单信息
	 * @param model
	 */
	@RequestMapping(params="saveOrUpdate")
	public void saveOrUpdate(TbCommonFunction model,HttpServletResponse rsp){
		boolean result = false;
		Long id=model.getId();
		if(id!=null){//修改
			log.debug("操作：修改，id="+id);
			Long parentId=model.getParentFunctionId();
			if(parentId!=null){
				TbCommonFunction parentFunc=this.functionManagerService.getFunc(parentId);
				model.setFunctionLevel(parentFunc.getFunctionLevel()+1);//下级菜单的等级（父菜单等级+1）
				model.setParentFunctionId(parentId);
			}else{
				//菜单没有父菜单，即为一级菜单
				model.setFunctionLevel(ConstantUtil.ROOT_MENU_LEVEL);
			}
			result = functionManagerService.updateFunc(model);
		}else{//新增
			log.debug("操作：新增");
			Long parentId=model.getParentFunctionId();
			if(parentId!=null){
				TbCommonFunction parentFunc=this.functionManagerService.getFunc(parentId);
				model.setFunctionLevel(parentFunc.getFunctionLevel()+1);//下级菜单的等级（父菜单等级+1）
				model.setParentFunctionId(parentId);
			}else{
				//菜单没有父菜单，即为一级菜单
				model.setFunctionLevel(ConstantUtil.ROOT_MENU_LEVEL);
			}			
			result = functionManagerService.saveFunc(model);			
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
	 * 删除菜单信息
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
			boolean result = functionManagerService.deleteFunc(Long.parseLong(id));
			if(result){
				rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
				rs.setMsg("删除成功");
			}else{ 
				rs.setResultCode(ConstantUtil.RESULT_FAILED);
				rs.setMsg("删除失败");
			}
		} else {
			boolean existRel = functionManagerService.existRel(Long.parseLong(id));
			if(!existRel){
				boolean result = functionManagerService.deleteFunc(Long.parseLong(id));
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
		
		boolean existRel = functionManagerService.existRel(Long.parseLong(id));
		if(!existRel){
			boolean result = functionManagerService.deleteFunc(Long.parseLong(id));
			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setMsg("删除成功");
		}else{ 
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("该菜单关联了角色或下级菜单，请先解除关系");
		}
		ToolUtil.getData(rsp, rs);
	}

	/**
	 * 菜单树形下拉选择框
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(params="getFuncComboTree")
	public void getFuncComboTree(HttpServletRequest req,HttpServletResponse rsp){
		//获取所有根节点的机构集合
		List<TbCommonFunction> list = this.functionManagerService.getFuncListForRoot();
		//机构集合转成ComboTree集合
		List<ComboTree> cList=this.functionManagerService.comTree(list);
		//ComboTree集合转成json并返回
		ToolUtil.getCombo(rsp, cList);
	}

	/**
	 * 获取角色列表
	 * @param request
	 */
	@RequestMapping(params="getFunctionList")
	public void getFunctionList(OrgManagerModel param,PageUtil pageUtil,HttpServletRequest req,HttpServletResponse rsp){

		log.debug("获取参数:"+CommonUtil.Object2String(param));
		log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

		//参数解码
		ModelMapper mapper = ModelMapperFactory.getModelMapper(param);
		Object obj = mapper.decodeModel(null);
		OrgManagerModel modelParam = null;
		if(obj instanceof OrgManagerModel){
			modelParam = (OrgManagerModel)obj;
		}

		//获取分页数据
		pageUtil = this.functionManagerService.getPageList(pageUtil);

		//返回分页数据		
		ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
	}

	/**
	 * 获取按钮列表
	 * @param request
	 */
	@RequestMapping(params="toOperationList")
	public ModelAndView toOperationList(HttpServletRequest req,HttpServletResponse rsp){
		String functionId = req.getParameter("functionId");
		req.setAttribute("functionId", functionId);
		return new ModelAndView("manage/system/window/operationList");
	}

	/**
	 * 获取按钮列表
	 * @param request
	 */
	@RequestMapping(params="getOperationList")
	public void getOperationList(HttpServletRequest req,HttpServletResponse rsp){
		String functionId = req.getParameter("functionId");
		List<TbCommonOperation> buttonList = this.functionManagerService.getOperationByFunId(Long.parseLong(functionId));	
		ToolUtil.getDataGrid(rsp, buttonList, 0);
	}

	@RequestMapping(params="saveOperation")
	public void saveOperation(HttpServletRequest req,HttpServletResponse rsp){
		String id = req.getParameter("id");
		String functionId = req.getParameter("functionId");
		String operateCode = req.getParameter("operateCode");
		String operateName = req.getParameter("operateCode");
		TbCommonOperation oper = null;
		boolean result;
		
		if (StringUtil.isEmpty(id)){
			oper = new TbCommonOperation();
			oper.setFunctionId(Long.parseLong(functionId));
			oper.setOperateCode(operateCode);
			oper.setOperateName(operateName);
			result = this.functionManagerService.saveOperation(oper);
		} else {
			oper = this.functionManagerService.getOperationById(Long.parseLong(id));
			oper.setFunctionId(Long.parseLong(functionId));
			oper.setOperateCode(operateCode);
			oper.setOperateName(operateName);
			result = this.functionManagerService.saveOperation(oper);
		}
		
		OperResult rs = new OperResult();
		if(result){
			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setMsg("增加成功");
		}else{ 
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("增加失败");
		}
		ToolUtil.getData(rsp, rs);
	}   
	
	/**
	 * 获取按钮列表
	 * @param request
	 */
	@RequestMapping(params="toEditFunction")
	public ModelAndView toEditFunction(HttpServletRequest req,HttpServletResponse rsp){
		String functionId = req.getParameter("functionId");
		String operationId = req.getParameter("operationId");
		req.setAttribute("functionId", functionId);
		if (!StringUtil.isEmpty(operationId)){
			TbCommonOperation commonOper = functionManagerService.getOperationById(Long.parseLong(operationId));
			req.setAttribute("commonOper", commonOper);
		}
		return new ModelAndView("manage/system/window/operationDetail");
	}       
	
	@RequestMapping(params="deleteOperation")
	public void deleteOperation(HttpServletRequest req,HttpServletResponse rsp){
		String id = req.getParameter("id");
		boolean result = this.functionManagerService.deleteOperationById(Long.parseLong(id));
		
		OperResult rs = new OperResult();
		if(result){
			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setMsg("删除成功");
		}else{ 
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("删除失败");
		}
		ToolUtil.getData(rsp, rs);
	}
}
