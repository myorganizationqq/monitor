package com.bluedon.monitor.system.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bluedon.monitor.system.model.system.DepartManagerModel;
import com.bluedon.monitor.system.model.util.ComboTree;
import com.bluedon.monitor.system.service.system.IDepartManagerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.common.util.model.ModelMapper;
import com.bluedon.monitor.common.util.model.ModelMapperFactory;
import com.bluedon.monitor.system.entity.TbCommonDepart;
import com.bluedon.monitor.system.entity.TbCommonOrg;
import com.bluedon.monitor.system.entity.TbCommonUser;
import com.bluedon.monitor.system.model.system.OrgManagerModel;
import com.bluedon.monitor.system.model.system.UserManagerModel;
import com.bluedon.monitor.system.model.util.ComboBox;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.service.system.IOrgManagerService;
import com.bluedon.monitor.system.service.system.IUserManagerService;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;


/** 
 * @author :yangdandan 
 * @date：2015年4月15日
 * @module:系统模块 
 * @comment:组织机构管理控制器
 */
@Controller
@RequestMapping("/manage/system/orgManagerController")
public class OrgManagerController {

	
	//日志记录对象
	private static final Logger log = Logger.getLogger(OrgManagerController.class);
		
	//用户角色逻辑处理对象
	@Autowired
	@Qualifier("orgManagerServiceImpl")
	private IOrgManagerService orgManagerService;
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
		return new ModelAndView("manage/system/orgManage");
	}
	
	/**
	 * 获取角色列表
	 * @param request
	 */
	@RequestMapping(params="getOrgList")
	public void pageList(OrgManagerModel param,PageUtil pageUtil,HttpServletRequest req,HttpServletResponse rsp){
		
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
		pageUtil = this.orgManagerService.getPageList(modelParam, pageUtil);
		
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
		String parentId=req.getParameter("parentId");
		if(!StringUtil.isEmpty(parentId)){
			req.setAttribute("orgParentId", parentId);
		}		
		return new ModelAndView("manage/system/window/orgDetail");
	}
	
	/**
	 * 加载修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toEdit")
	public ModelAndView toEdit(HttpServletRequest req){	
		String id=req.getParameter("id");
		TbCommonOrg org=this.orgManagerService.getOrg(Long.parseLong(id));
		req.setAttribute("commonOrg", org);
		
		String load=req.getParameter("load");
		if(!StringUtil.isEmpty(load)){
			req.setAttribute("load", load);
		}
		return new ModelAndView("manage/system/window/orgDetail");
	}
	
	/**
	 * 保存角色信息
	 * @param model
	 */
	@RequestMapping(params="saveOrUpdate")
	public void saveOrUpdate(TbCommonOrg model,HttpServletResponse rsp){
		boolean result = false;
		Long id=model.getId();
		if(id!=null){//修改
			log.debug("操作：修改，id="+id);
			result = orgManagerService.updateOrg(model);
		}else{//新增
			log.debug("操作：新增");
			result = orgManagerService.saveOrg(model);			
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
	 * 组织机构的启用/停用
	 * @param model
	 */
	@RequestMapping(params="isEnable")
	public void isEnable(HttpServletRequest req, HttpServletResponse rsp){
		boolean result = false;
		String orgIdArr = req.getParameter("orgIdArr");
		String isEnableStatus = req.getParameter("status");
		String haveChildren = req.getParameter("haveChildren");
		if(!StringUtil.isEmpty(orgIdArr)){//修改
			if ("0".equals(isEnableStatus)) {
				log.debug("操作：将组织机构设置为停用，idArr=" + orgIdArr);
			} else if ("1".equals(isEnableStatus)) {
				log.debug("操作：将组织机构设置为启用，idArr=" + orgIdArr);
			}
			if (!StringUtil.isEmpty(haveChildren) && haveChildren.equals("true")) {
				result = this.orgManagerService.updateOrgEnableStatus(orgIdArr, isEnableStatus, true);
			} else {
				result = this.orgManagerService.updateOrgEnableStatus(orgIdArr, isEnableStatus, false);
			}
		}		
		log.debug("=========设置结果========="+result);
		OperResult rs = new OperResult();
        if(result){
        	rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
        	rs.setMsg("设置成功");
        }else{ 
        	rs.setResultCode(ConstantUtil.RESULT_FAILED);
        	if (rs.getMsg() == null) {
        		rs.setMsg("设置失败");
        	}
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
		Long orgId = Long.parseLong(id);
		boolean result = false;
		OperResult rs = new OperResult();
		DepartManagerModel departParam = new DepartManagerModel();
		departParam.setOrgId(orgId);
		List<TbCommonDepart> departList = departManagerService.getDepartByParams(departParam);
		UserManagerModel userParam = new UserManagerModel();
		userParam.setOrgId(orgId);
		List<TbCommonUser> userList = userManagerService.getUserListByParam(userParam);
		boolean departFlag = departList.size() <= 0;
		boolean userFlag = userList.size() <= 0;
		if (!departFlag) {
			rs.setMsg("删除失败，请先删除该组织机构下的部门！");
		} else if (!userFlag) {
			rs.setMsg("删除失败，请先删除该组织机构下的用户！");
		}
		if (departFlag && userFlag) {
			result = orgManagerService.deleteOrg(orgId);
		}
 		log.debug("=========删除结果=========" + result);
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
	@RequestMapping(params="getOrgComboTree")
	public void getOrgComboTree(HttpServletRequest req,HttpServletResponse rsp){
		//获取所有根节点的机构集合
		List<TbCommonOrg> list = this.orgManagerService.getOrgListForRoot();
		//机构集合转成ComboTree集合
		List<ComboTree> cList=this.orgManagerService.comTree(list);
		//ComboTree集合转成json并返回
        ToolUtil.getCombo(rsp, cList);
	}
	
	
	/**
	 * 所有有效的组织机构下拉列表
	 * @param model
	 */
	@RequestMapping(params="getEnabledOrgList")
	public void getEnabledOrgList(HttpServletRequest req,HttpServletResponse rsp){
		//获取所有有效的组织机构集合
		List<TbCommonOrg> list = this.orgManagerService.getEnabledOrgList();
		List<ComboBox> bList=new ArrayList<ComboBox>();
		for(TbCommonOrg org:list){
			ComboBox b=new ComboBox();
			b.setId(String.valueOf(org.getId()));
			b.setText(org.getOrgName());
			bList.add(b);
		}
		//List集合转成json并返回
        ToolUtil.getCombo(rsp, bList);
	}
	
	
	/**
	 * 判断机构下的子机构
	 * @param model
	 */
	@RequestMapping(params="getOrgListByParentId")
	public void getOrgListByParentId(HttpServletRequest req,HttpServletResponse rsp){
		String parentIdArr = req.getParameter("idArr");
		//获取所有有效的组织机构集合
		List<TbCommonOrg> list = this.orgManagerService.getOrgListByParentIdArr(parentIdArr);
		OperResult rs = new OperResult();
    	rs.setResultCode(list.size());
        ToolUtil.getData(rsp, rs);
	}
	/**
	 * 判断机构下的子机构
	 * @param model
	 */
	@RequestMapping(params="getOrgByOrgCode")
	public void getOrgByOrgCode(HttpServletRequest req,HttpServletResponse rsp){
		String orgCode = req.getParameter("orgCode");
		OrgManagerModel orgParam = new OrgManagerModel();
		orgParam.setOrgCode(orgCode);
		//获取所有有效的组织机构集合
		List<TbCommonOrg> list = this.orgManagerService.getOrgListByParams(orgParam);
		OperResult rs = new OperResult();
    	rs.setResultCode(list.size());
        ToolUtil.getData(rsp, rs);
	}
	

}
