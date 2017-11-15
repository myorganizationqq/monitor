package com.bluedon.bfw.system.controller.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluedon.bfw.common.util.CommonUtil;
import com.bluedon.bfw.common.util.ConfigReader;
import com.bluedon.bfw.common.util.PageUtil;
import com.bluedon.bfw.common.util.StringUtil;
import com.bluedon.bfw.common.util.encrypt.Encrypt3DesUtil;
import com.bluedon.bfw.common.util.model.ModelMapper;
import com.bluedon.bfw.common.util.model.ModelMapperFactory;
import com.bluedon.bfw.system.entity.TbCommonUser;
import com.bluedon.bfw.system.model.system.UserManagerModel;
import com.bluedon.bfw.system.model.util.CurrentUserInfo;
import com.bluedon.bfw.system.model.util.OperResult;
import com.bluedon.bfw.system.service.system.IDepartManagerService;
import com.bluedon.bfw.system.service.system.IOrgManagerService;
import com.bluedon.bfw.system.service.system.IRoleManagerService;
import com.bluedon.bfw.system.service.system.IUserManagerService;
import com.bluedon.bfw.system.service.system.IUserRoleManagerService;
import com.bluedon.bfw.system.util.ConstantUtil;
import com.bluedon.bfw.system.util.ToolUtil;


/**
 * @author  : jason
 * @date    : 2015年3月10日
 * @module  : 系统模块 
 * @comment : 用户管理控制器
 */
@Controller
@RequestMapping("/manage/system/userManagerController")
public class UserManagerController {
	
	//日志记录对象
	private static final Logger log = Logger.getLogger(UserManagerController.class);
	
	//逻辑处理类对象
	@Autowired
	@Qualifier("userManagerServiceImpl")
	private IUserManagerService userManagerService;
	
	@Autowired
	@Qualifier("orgManagerServiceImpl")
	private IOrgManagerService orgManagerService;
		
	@Autowired
	@Qualifier("roleManagerServiceImpl")
	private IRoleManagerService roleManagerService;
	
	@Autowired
	@Qualifier("userRoleManagerServiceImpl")
	private IUserRoleManagerService userRoleManagerService;
	
	@Autowired
	@Qualifier("departManagerServiceImpl")
	private IDepartManagerService departManagerService;
	
	/**
	 * 加载列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toPageList")
	public ModelAndView toPageList(HttpServletRequest req){
		req.setAttribute("p_orgId", req.getParameter("orgId"));
		return new ModelAndView("manage/system/userManage");
	}
	
	/**
	 * 获取角色列表
	 * @param request
	 */
	@RequestMapping(params="getUserList")
	public void pageList(UserManagerModel param,PageUtil pageUtil,HttpServletRequest req,HttpServletResponse rsp){
		
		log.debug("获取参数:"+CommonUtil.Object2String(param));
		log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));
		
		//参数解码
		ModelMapper mapper = ModelMapperFactory.getModelMapper(param);
		Object obj = mapper.decodeModel(null);
		UserManagerModel modelParam = null;
		if(obj instanceof UserManagerModel){
			modelParam = (UserManagerModel)obj;
		}
		CurrentUserInfo user = ConstantUtil.getCurrentUserInfo();
		modelParam.setOrgId(user.getOrgId());
		modelParam.setDepartId(user.getDepartId());
		//获取分页数据
		pageUtil = this.userManagerService.getPageList(modelParam, pageUtil);
		
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
		return new ModelAndView("manage/system/window/userDetail");
	}
	
	/**
	 * 加载修改页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="toEdit")
	public ModelAndView toEdit(HttpServletRequest req){	
		String id = req.getParameter("id");
		TbCommonUser user = this.userManagerService.getUser(Long.parseLong(id));		
		req.setAttribute("commonUser", user);
		
		String load = req.getParameter("load");
		if (!StringUtil.isEmpty(load)) {
			req.setAttribute("load", load);
		}
		return new ModelAndView("manage/system/window/userDetail");
	}
	
	/**
	 * 保存用户信息
	 * @param model
	 */
	@RequestMapping(params="saveOrUpdate")
	public void saveOrUpdate(TbCommonUser model,HttpServletResponse rsp){
		boolean result = false;
		Long id=model.getId();
		OperResult rs = new OperResult();
		if(id!=null){//修改
			log.debug("操作：修改，id="+id);
			result = userManagerService.updateUser(model);
		}else{//新增
			TbCommonUser tempUser = this.userManagerService.getUserByLoginName(model.getLoginName());
			if (tempUser == null) {
				log.debug("操作：新增");
				//设置默认密码
				String defaultPw=ConfigReader.getProperty("config", "default_password");
				//加密
				String pw=Encrypt3DesUtil.getEncString(defaultPw);
				model.setPassword(pw);			
				result = true;//userManagerService.saveUser(model);	
			} else {
				rs.setMsg("登录名已存在！");
			}
			
		}		
		log.debug("=========保存结果========="+result);
        if(result){
        	rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
        	rs.setMsg("操作成功");
        }else{ 
        	rs.setResultCode(ConstantUtil.RESULT_FAILED);
        	if (StringUtils.isEmpty(rs.getMsg())) {
            	rs.setMsg("操作失败");
        	}
        }
        ToolUtil.getData(rsp, rs);
	}

	
	/**
	 * 删除用户信息
	 * @param model
	 */
	@RequestMapping(params="delete")
	public void delete(HttpServletRequest req,HttpServletResponse rsp){		
		String id=req.getParameter("id");
		boolean result = this.userManagerService.deleteUser(Long.parseLong(id));
 		log.debug("=========删除结果========="+result);
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

	
	/**
	 * 用户 停用/启用
	 * @param model
	 */
	@RequestMapping(params="userValid")
	public void userValid(HttpServletRequest req,HttpServletResponse rsp){		
		String idArr = req.getParameter("userIdArr");
		String status = req.getParameter("isValid");
		boolean result = false;
		if ("0".equals(status)) {
			log.debug("操作：将用户设置为正常使用，idArr=" + idArr);
		} else {
			log.debug("操作：将用户设置为无效，idArr=" + idArr);
		}
		result = this.userManagerService.updateUserValid(idArr, status);
 		log.debug("=========设置结果=========" + result);
		OperResult rs = new OperResult();
        if(result){
        	rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
        	rs.setMsg("设置成功");
        }else{ 
        	rs.setResultCode(ConstantUtil.RESULT_FAILED);
        	rs.setMsg("设置失败");
        }
        ToolUtil.getData(rsp, rs);
	}
	
	/**
	 * 获取待选用户列表
	 * @param model
	 */
	@RequestMapping(params="getSelectingUsers")
	public void getSelectingUsers(UserManagerModel param,HttpServletRequest req, HttpServletResponse rsp){
		param.setIsValid(1);//有效
		
		List<TbCommonUser> list= this.userManagerService.getUserListByParam(param);
		
		String roleId=req.getParameter("roleId");//页面传递参数，选中角色的角色id

		List<TbCommonUser> selectList= this.userManagerService.getSelectUserListByParam(param,roleId);
			
		list.removeAll(selectList);//移除已经选中的元素	
		
		ToolUtil.getCombo(rsp, list);
	}
	
	/**
	 * 获取已选用户列表
	 * @param model
	 */
	@RequestMapping(params="getSelectedUsers")
	public void getSelectedUsers(UserManagerModel param,HttpServletRequest req, HttpServletResponse rsp){
		param.setIsValid(1);//有效
		
		String roleId=req.getParameter("roleId");
		List<TbCommonUser> list= this.userManagerService.getSelectUserListByParam(param,roleId);
		
		ToolUtil.getCombo(rsp, list);
	}
	
	
	
	/**
	 * 保存用户角色关系 批量
	 * @param model
	 */
	@RequestMapping(params="changeUserRoles")
	public void changeUserRoles(HttpServletRequest req, HttpServletResponse rsp){
		boolean result=false;
		String roleId=req.getParameter("roleId");//角色id		
		String userIdsStr=req.getParameter("selectedUser");//保存提交的用户列表		
		String oldUserIdsStr=req.getParameter("oldSelectedUser");//原来该角色的所有用户
		//将字符串转为数组
		String[] userIds=null;
		if(!StringUtil.isEmpty(userIdsStr)){
			userIds=userIdsStr.split(",");	
		}
		String[] oldUserIds=null;
		if(!StringUtil.isEmpty(oldUserIdsStr)){
			oldUserIds=oldUserIdsStr.split(",");	
		}
		
		//将要数组转为集合
		List<String> userIdList=new ArrayList<String>();	
		if(userIds!=null){
			userIdList=new ArrayList<String>(Arrays.asList(userIds));	
		}
		List<String> oldUserIdList=new ArrayList<String>();
		if(oldUserIds!=null){
			oldUserIdList=new ArrayList<String>(Arrays.asList(oldUserIds));	
		}

		//交集 指定角色，保存时提交的用户列表和原来的用户列表都有的用户
		List<String> mixed=new ArrayList<String>();
		mixed.addAll(userIdList);		
		mixed.retainAll(oldUserIdList);	
		log.debug("保存该角色用户中，不变的用户数量:"+mixed.size());
		
		//差集 指定角色，保存时提交的用户列表比原来新增加的用户集合（A-B）
		List<String> newElementList=new ArrayList<String>();		
		newElementList.addAll(userIdList);
		newElementList.removeAll(mixed);
		log.debug("保存该角色用户中，新增加的用户数量:"+newElementList.size());
		
		//差集 指定角色，保存时提交的用户列表比原来被删掉的用户集合（B-A）
		List<String> oldElementList=new ArrayList<String>();
		oldElementList.addAll(oldUserIdList);
		oldElementList.removeAll(mixed);
		log.debug("保存该角色用户中，被删掉的用户数量:"+oldElementList.size());
		
		
		//操作
		if(newElementList.size()==0 && oldElementList.size()==0){//无新增 无删除 不操作
			result=true;
		}else{
			//新增用户角色关系
			if(newElementList.size()>0){
				result=this.userRoleManagerService.saveUserRoles(Long.parseLong(roleId), newElementList);
			}
			//删除用户角色关系
			if(oldElementList.size()>0){
				result=this.userRoleManagerService.deleteUserRoles(Long.parseLong(roleId), oldElementList);
			}
		}		

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
	 * 删除用户信息
	 * @param model
	 */
	@RequestMapping(params="setDefaultPwd")
	public void setDefaultPwd(HttpServletRequest req,HttpServletResponse rsp){		
		String id = req.getParameter("userId");
		boolean result = false;
		if (!StringUtils.isEmpty(id)) {
			log.debug("操作：用户密码重置，id=" + id);
			TbCommonUser user = this.userManagerService.getUser(Long.parseLong(id));
			//设置默认密码
			String defaultPw = ConfigReader.getProperty("config", "default_password");
			//加密
			String pw = Encrypt3DesUtil.getEncString(defaultPw);
			user.setPassword(pw);
			result = this.userManagerService.updateUser(user);
		}
		
 		log.debug("=========重置结果=========" + result);
		OperResult rs = new OperResult();
        if(result){
        	rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
        	rs.setMsg("重置成功");
        }else{ 
        	rs.setResultCode(ConstantUtil.RESULT_FAILED);
        	rs.setMsg("重置失败");
        }
        ToolUtil.getData(rsp, rs);
	}
}
