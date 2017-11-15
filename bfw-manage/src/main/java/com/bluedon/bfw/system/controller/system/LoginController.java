package com.bluedon.bfw.system.controller.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluedon.bfw.common.util.StringUtil;
import com.bluedon.bfw.common.util.encrypt.Encrypt3DesUtil;
import com.bluedon.bfw.system.entity.TbCommonDepart;
import com.bluedon.bfw.system.entity.TbCommonFunction;
import com.bluedon.bfw.system.entity.TbCommonOrg;
import com.bluedon.bfw.system.entity.TbCommonRole;
import com.bluedon.bfw.system.entity.TbCommonUser;
import com.bluedon.bfw.system.entity.TbCommonUserOrg;
import com.bluedon.bfw.system.model.system.LoginUserModel;
import com.bluedon.bfw.system.model.util.CurrentUserInfo;
import com.bluedon.bfw.system.model.util.OperResult;
import com.bluedon.bfw.system.service.common.ILoginLogService;
import com.bluedon.bfw.system.service.system.IDepartManagerService;
import com.bluedon.bfw.system.service.system.IFunctionManagerService;
import com.bluedon.bfw.system.service.system.IOrgManagerService;
import com.bluedon.bfw.system.service.system.IRoleManagerService;
import com.bluedon.bfw.system.service.system.IUserManagerService;
import com.bluedon.bfw.system.service.system.IUserOrgManagerService;
import com.bluedon.bfw.system.util.ConstantUtil;
import com.bluedon.bfw.system.util.NumberComparator;
import com.bluedon.bfw.system.util.ToolUtil;

/** 
 * @author :yangdandan 
 * @date：2015年4月16日
 * @module:
 * @comment:
 */

@Controller
@RequestMapping("/manage/system/loginController")
public class LoginController {

	//日志记录对象
	private static final Logger log = Logger.getLogger(LoginController.class);
		
	//用户角色逻辑处理对象
	@Autowired
	@Qualifier("userManagerServiceImpl")
	private IUserManagerService userManagerService;
	
	@Autowired
	@Qualifier("orgManagerServiceImpl")
	private IOrgManagerService orgManagerService;
	
	@Autowired
	@Qualifier("departManagerServiceImpl")
	private IDepartManagerService departManagerService;
		
	@Autowired
	@Qualifier("roleManagerServiceImpl")
	private IRoleManagerService roleManagerService;
	
	@Autowired
	@Qualifier("userOrgManagerServiceImpl")
	private IUserOrgManagerService userOrgManagerService;
	
	@Autowired
	@Qualifier("functionManagerServiceImpl")
	private IFunctionManagerService functionManagerService;
	

	
	/**
	 * 检查用户是否存在
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	public void checkuser(LoginUserModel param,HttpServletRequest req,HttpServletResponse rsp) {
		OperResult rs = new OperResult();
		Object data=null;
		boolean result=true;
		String msg="";
		HttpSession session=req.getSession();
		//查询用户是否存在
		param.setIsValid(1);//有效
		param.setCheckType(ConstantUtil.CHECK_LOGINNAME);//检查登录名
		List<TbCommonUser> userList=this.userManagerService.getUserListByParam(param);
		if(userList.size()==0){//用户不存在
			result=false;
			msg="输入的用户名不存在！";			
		}else{			
			//param.setCheckType(ConstantUtil.CHECK_LOGINPWS);//密码校验
			//param.setPassword(Encrypt3DesUtil.getEncString(param.getPassword()));//加密
			//userList=this.userManagerService.getUserListByParam(param);
			TbCommonUser u = new TbCommonUser();
			u.setId(1L);
			u.setIsValid(1);
			u.setLoginName("admin");
			u.setPassword("123456");
			u.setRealName("admin");

			if(userList.size()==1){
				TbCommonUser user=userList.get(0);
				
				//设置用户信息
				CurrentUserInfo userInfo=new CurrentUserInfo();
				userInfo.setUserId(user.getId());//保存用户id
				userInfo.setLoginName(user.getLoginName());
				userInfo.setRealName(user.getRealName());
				userInfo.setUserType(user.getUserType());
				
				
				//根据用户id查询该用户的机构集合 并设置
				List<TbCommonOrg> orgList=orgManagerService.getOrgListByUserId(user.getId());
				userInfo.setOrgs(orgList);
								
				//根据用户id查询该用户的角色集合 并设置
				//角色设置
				List<TbCommonRole> roleList=roleManagerService.getRoleListByUserId(user.getId());
				userInfo.setRoles(roleList);				
					
				//当该用户只有一个组织机构和一个角色时，直接设置当前登录的机构和角色
				if(orgList.size()==1 && roleList.size()==1){
					userInfo.setOrgId(orgList.get(0).getId());
					userInfo.setRoleId(roleList.get(0).getId());
				}
				//返回前台内容
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("orgs", orgList);
				map.put("roles", roleList);	
				data=JSONObject.fromObject(map).toString();
				
				//保存相关的session
				session.setAttribute(ConstantUtil.CURRENT_USER_INFO, userInfo);												
			}else{
				//提示用户信息输入有误
				result=false;
				msg="密码输入有误，请重新输入！";
			}				
					
		}		
		
        if(result){//成功
        	rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
        	if(data!=null){
        		rs.setData(data);
        	}
        	
        }else{ //失败 输出提示信息
        	rs.setResultCode(ConstantUtil.RESULT_FAILED);
        	rs.setMsg(msg);       	
        }
        ToolUtil.getData(rsp, rs);
	}
	
	//登录
	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletRequest req) {
		String type=req.getParameter("loginType");
		//获取封装的用户信息
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();
		Long orgId=null;
		Long roleId=null;
		if(userInfo!=null){//已经验证过登录信息的
						
			if(type!=null){//登录				
				//插入登录日志  调用日志接口
				LoginUserModel param=new LoginUserModel();
				param.setLoginName(userInfo.getLoginName());
				log.debug(userInfo.getRealName()+"登录成功！");
				
				if(type.equals("0")){//直接登录
					orgId=userInfo.getOrgId();
					roleId=userInfo.getRoleId();
				}else if(type.equals("1")){//选择单位和角色登录
					String orgIdStr=req.getParameter("orgId");
					String roleIdStr=req.getParameter("roleId");
					if(!StringUtil.isEmpty(orgIdStr)){
						orgId=Long.parseLong(orgIdStr);
						userInfo.setOrgId(orgId);
					}
					if(!StringUtil.isEmpty(roleIdStr)){
						roleId=Long.parseLong(roleIdStr);
						userInfo.setRoleId(roleId);
					}				
				}
				
				Long userId=userInfo.getUserId();	
				//组织机构信息设置			
				TbCommonOrg org=orgManagerService.getOrg(orgId);
				userInfo.setOrgCode(org.getOrgCode());
				userInfo.setOrgName(org.getOrgName());
				userInfo.setOrgFullName(org.getFullName());
				
				//部门信息设置
				//根据机构和用户查询部门
				Map<String,Object> params=new HashMap<String,Object>();
				params.put("userId", userId);
				params.put("orgId", orgId);
				TbCommonUserOrg userOrg=this.userOrgManagerService.getUserOrg(params);
				Long departId=userOrg.getDepartId();//部门id
				if(departId!=null){
					TbCommonDepart depart=departManagerService.getDepart(departId);
					userInfo.setDepartCode(depart.getDepartCode());
					userInfo.setDepartName(depart.getDepartName());
				}
				
				//角色信息设置	
				if(roleId!=null){
					TbCommonRole role=roleManagerService.getRole(roleId);
					userInfo.setRoleCode(role.getRolecode());
					userInfo.setRoleName(role.getRolename());
					userInfo.setRoleType(role.getRoleType());	
				}

				//用户登录单位的所有角色列表
				List<TbCommonRole> roleList=this.roleManagerService.getRoleListByUserIdAndOrgId(userId, orgId);
				userInfo.setOrgRoles(roleList);
				
				//设置session
				req.getSession().setAttribute(ConstantUtil.CURRENT_USER_INFO, userInfo);
				
				//用户信息
				req.setAttribute("userInfo", userInfo);
				
				return new ModelAndView("manage/manage-index");			

			}else{//session未过期 直接请求登录的url
				if(userInfo.getOrgId()!=null){
					//用户信息
					req.setAttribute("userInfo", userInfo);
					return new ModelAndView("manage/manage-index");	
				}else{
					return new ModelAndView("manage/login/login");
				}				
			}		
		}else{
			//session过期 登录页面
			return new ModelAndView("manage/login/login");
		}
	}
	
	//获取菜单
	@RequestMapping(params = "getMenu")
	public void getMenus(HttpServletRequest req,HttpServletResponse rsp){			
		List<TbCommonFunction> allList=new ArrayList<TbCommonFunction>();
		
		//获取封装的用户信息
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();
		if(userInfo!=null){
			//菜单动态加载	
		
			//是否登录后第一次加载菜单，如果不是，则取上次加载的菜单
			//确保修改菜单权限后，只有重新登录才能生效
			if(userInfo.getMenus()!=null){
				allList=userInfo.getMenus();
			}else{
				Long role=userInfo.getRoleId();					
				if(role!=null){			
					List<TbCommonFunction> roots=this.functionManagerService.getFuncRootListByRoleId(role);					
					for(TbCommonFunction r:roots){
						r=functionManagerService.getFuncChildrenByRoleId(r,role,true);
						allList.add(r);
					}				
					//菜单排序 一级菜单
					Collections.sort(allList, new NumberComparator());		
					userInfo.setMenus(allList);
				}		
			}		
			
		}
		
		OperResult rs = new OperResult();
		rs.setResultCode(ConstantUtil.RESULT_SUCCESS);	
		rs.setData(JSONArray.fromObject(allList).toArray());
		ToolUtil.getData(rsp,rs);
	}
	
	
	//退出
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session=req.getSession();
		//获取用户
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();
		//清除session
		session.removeAttribute(ConstantUtil.CURRENT_USER_INFO);
		

		log.debug(userInfo.getRealName()+"退出成功！");
		
		return new ModelAndView("manage/login/logout");	
	}
	
	
	//选择组织和角色的页面
	@RequestMapping(params="toChoose")
	public ModelAndView toChoose(HttpServletRequest req){		
		return new ModelAndView("manage/login/choose");
	}
		
	//当用户多个组织时，加载组织列表
	@RequestMapping(params="loadOrgList")
	public void loadOrgList(HttpServletRequest req,HttpServletResponse rsp){		
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();
		List<TbCommonOrg> orgList=userInfo.getOrgs();
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		for(TbCommonOrg o:orgList){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", o.getId());
			map.put("text", o.getOrgName());
			result.add(map);
		}
		ToolUtil.getCombo(rsp, result);
	}
	
	//当用户多个角色时，加载角色列表
	@RequestMapping(params="loadRoleList")
	public void loadRoleList(HttpServletRequest req,HttpServletResponse rsp){		
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();

		String orgId=req.getParameter("orgId");
		Long userId=userInfo.getUserId();
		List<TbCommonRole> roleList=this.roleManagerService.getRoleListByUserIdAndOrgId(userId, Long.parseLong(orgId));
		
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		for(TbCommonRole r:roleList){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", r.getId());
			map.put("text", r.getRolename());
			result.add(map);
		}

		ToolUtil.getCombo(rsp, result);
	}
	
	@RequestMapping(params="changeOrgRole")
	public void changeOrgRole(HttpServletRequest req,HttpServletResponse rsp){	
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();
		
		Long orgId=null;
		Long roleId=null;
		
		String orgIdStr=req.getParameter("orgId");
		String roleIdStr=req.getParameter("roleId");
		if(!StringUtil.isEmpty(orgIdStr)){
			orgId=Long.parseLong(orgIdStr);			
		}
		if(!StringUtil.isEmpty(roleIdStr)){
			roleId=Long.parseLong(roleIdStr);			
		}		
		userInfo.setOrgId(orgId);
		userInfo.setRoleId(roleId);
		
		Long userId=userInfo.getUserId();	
		//组织机构信息设置			
		TbCommonOrg org=orgManagerService.getOrg(orgId);
		userInfo.setOrgCode(org.getOrgCode());
		userInfo.setOrgName(org.getOrgName());
		userInfo.setOrgFullName(org.getFullName());
		
		//部门信息设置
		//根据机构和用户查询部门
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("orgId", orgId);
		TbCommonUserOrg userOrg=this.userOrgManagerService.getUserOrg(params);
		Long departId=userOrg.getDepartId();//部门id
		if(departId!=null){
			TbCommonDepart depart=departManagerService.getDepart(departId);
			userInfo.setDepartCode(depart.getDepartCode());
			userInfo.setDepartName(depart.getDepartName());
		}
		
		//角色信息设置	
		if(roleId!=null){
			TbCommonRole role=roleManagerService.getRole(roleId);
			userInfo.setRoleCode(role.getRolecode());
			userInfo.setRoleName(role.getRolename());
			userInfo.setRoleType(role.getRoleType());	
		}else{
			userInfo.setRoleCode(null);
			userInfo.setRoleName(null);
			userInfo.setRoleType(null);
		}

		//用户登录单位的所有角色列表
		List<TbCommonRole> roleList=this.roleManagerService.getRoleListByUserIdAndOrgId(userId, orgId);
		userInfo.setOrgRoles(roleList);
		
		//需要重新加载菜单
		userInfo.setMenus(null);
		
		//设置session
		req.getSession().setAttribute(ConstantUtil.CURRENT_USER_INFO, userInfo);
		
		//用户信息
		req.setAttribute("userInfo", userInfo);
		
		//return new ModelAndView("manage/manage-index");	
		OperResult rs = new OperResult();
		rs.setResultCode(ConstantUtil.RESULT_SUCCESS);   	
		ToolUtil.getData(rsp, rs);
	}
	
	@RequestMapping(params="getUserRolesByOrg")
	public void getUserRolesByOrg(HttpServletRequest req,HttpServletResponse rsp){		
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();

		String orgId=req.getParameter("orgId");
		Long userId=userInfo.getUserId();
		List<TbCommonRole> roleList=this.roleManagerService.getRoleListByUserIdAndOrgId(userId, Long.parseLong(orgId));		
		
		OperResult rs = new OperResult();
		rs.setResultCode(ConstantUtil.RESULT_SUCCESS); 
		if(roleList.size()>0){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("roles", roleList);	
			Object data=JSONObject.fromObject(map).toString();
			rs.setData(data);
		}
		
		ToolUtil.getData(rsp, rs);
	}
}
