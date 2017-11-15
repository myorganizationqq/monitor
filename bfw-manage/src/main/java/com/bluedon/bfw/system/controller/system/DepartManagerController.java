package com.bluedon.bfw.system.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bluedon.bfw.system.entity.TbCommonDepart;
import com.bluedon.bfw.system.entity.TbCommonUser;
import com.bluedon.bfw.system.model.system.DepartManagerModel;
import com.bluedon.bfw.system.model.system.UserManagerModel;
import com.bluedon.bfw.system.model.util.ComboTree;
import com.bluedon.bfw.system.model.util.CurrentUserInfo;
import com.bluedon.bfw.system.model.util.OperResult;
import com.bluedon.bfw.system.service.system.IDepartManagerService;
import com.bluedon.bfw.system.service.system.IUserManagerService;
import com.bluedon.bfw.system.util.ConstantUtil;
import com.bluedon.bfw.system.util.ToolUtil;

/**
 * @author  : liangliwen
 * @date    : 2015年4月27日
 * @module  : 系统模块 
 * @comment : 部门管理控制器
 */
@Controller
@RequestMapping("/manage/system/departManagerController")
public class DepartManagerController {
	
	//日志记录对象
	private static final Logger log = Logger.getLogger(DepartManagerController.class);
	
	@Autowired
	@Qualifier("departManagerServiceImpl")
	private IDepartManagerService departManagerService;
	@Autowired
	@Qualifier("userManagerServiceImpl")
	private IUserManagerService userManagerService;
	/**
	 * 加载列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toPageList")
	public ModelAndView toPageList(HttpServletRequest req){
		req.setAttribute("p_orgId", req.getParameter("orgId"));
		return new ModelAndView("manage/system/departManage");
	}

	/**
	 * 获取角色列表
	 * @param request
	 */
	@RequestMapping(params="getDepartList")
	public void pageList(DepartManagerModel param,PageUtil pageUtil,HttpServletRequest req,HttpServletResponse rsp){
		
		log.debug("获取参数:"+CommonUtil.Object2String(param));
		log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));
		
		//参数解码
		ModelMapper mapper = ModelMapperFactory.getModelMapper(param);
		Object obj = mapper.decodeModel(null);
		DepartManagerModel modelParam = null;
		if(obj instanceof DepartManagerModel){
			modelParam = (DepartManagerModel)obj;
		}
		CurrentUserInfo user = ConstantUtil.getCurrentUserInfo();
		modelParam.setOrgId(user.getOrgId());
		//获取分页数据
		pageUtil = this.departManagerService.getPageList(modelParam, pageUtil);
		
		//返回分页数据		
		ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
	}
	

	/**
	 * 加载新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toAdd")
	public ModelAndView toAdd(HttpServletRequest req){
		//选中节点id传递作为其父节点
		String parentId = req.getParameter("parentId");
		if(!StringUtil.isEmpty(parentId)){
			req.setAttribute("departParentId", parentId);
		}
		return new ModelAndView("manage/system/window/departDetail");
	}
	
	/**
	 * 加载修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toEdit")
	public ModelAndView toEdit(HttpServletRequest req){	
		String id = req.getParameter("id");
		TbCommonDepart depart = this.departManagerService.getDepart(Long.parseLong(id));
		req.setAttribute("commonDepart", depart);
		
		String load = req.getParameter("load");
		if(!StringUtil.isEmpty(load)){
			req.setAttribute("load", load);
		}
		return new ModelAndView("manage/system/window/departDetail");
	}
	
	/**
	 * 保存部门信息
	 * @param model
	 */
	@RequestMapping(params="saveOrUpdate")
	public void saveOrUpdate(TbCommonDepart model,HttpServletResponse rsp){
		boolean result = false;
		Long id = model.getId();
		if(id!=null){//修改
			log.debug("操作：修改，id="+id);
			TbCommonDepart depart = this.departManagerService.getDepart(id);
			model.setCreateTime(depart.getCreateTime());
			model.setUpdateTime(new Date());
			result = this.departManagerService.updateDepart(model);
		}else{//新增
			log.debug("操作：新增");
			result = this.departManagerService.saveDepart(model);			
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
	 * 删除部门信息
	 * @param model
	 */
	@RequestMapping(params="delete")
	public void delete(HttpServletRequest req,HttpServletResponse rsp){		
		String id=req.getParameter("id");
		Long departId = Long.parseLong(id);
		boolean result = false;
		OperResult rs = new OperResult();
		UserManagerModel userParam = new UserManagerModel();
		userParam.setDepartId(departId);
		List<TbCommonUser> userList = this.userManagerService.getUserListByParam(userParam);
		boolean userFlag = userList.size() <= 0;
		if (userFlag) {
			result = this.departManagerService.deleteDepart(departId);
		} else {
			rs.setMsg("删除失败，请先删除该部门下的用户！");
		}
 		log.debug("=========删除结果========="+result);
        if(result){
        	rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
        	rs.setMsg("删除成功");
        }else{ 
        	rs.setResultCode(ConstantUtil.RESULT_FAILED);
        	if (StringUtil.isEmpty(rs.getMsg())) {
        		rs.setMsg("删除失败");
        	}
        }
        ToolUtil.getData(rsp, rs);
	}
	
	/**
	 * 组织机构树形下拉选择框
	 * @param model
	 */
	@RequestMapping(params="getDepartComboTree")
	public void getDepartComboTree(HttpServletRequest req,HttpServletResponse rsp){
		//获取所有根节点的机构集合
		List<TbCommonDepart> list = this.departManagerService.getDepartListForRoot();
		//机构集合转成ComboTree集合
		List<ComboTree> cList=this.departManagerService.comTree(list);
		//ComboTree集合转成json并返回
        ToolUtil.getCombo(rsp, cList);
	}
}
