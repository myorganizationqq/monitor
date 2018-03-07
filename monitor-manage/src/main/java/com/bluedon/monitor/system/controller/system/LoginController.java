package com.bluedon.monitor.system.controller.system;

import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.common.util.encrypt.Encrypt3DesUtil;
import com.bluedon.monitor.system.entity.*;
import com.bluedon.monitor.system.model.system.LoginUserModel;
import com.bluedon.monitor.system.model.util.CurrentUserInfo;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.service.system.*;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.NumberComparator;
import com.bluedon.monitor.system.util.ToolUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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


	//登录
	@RequestMapping(params = "login")
	public ModelAndView login(HttpServletRequest req,HttpServletResponse response) {
		//richapm跳转判断角色
		String roleParam = req.getParameter("roleParam");

		if(StringUtil.isEmpty(roleParam)){
			return new ModelAndView("manage/login/logout");
		}
		//获取封装的用户信息
		CurrentUserInfo sessionUserInfo=ConstantUtil.getCurrentUserInfo();


			req.getSession().setAttribute("roleParam",roleParam);
			LoginUserModel param = new LoginUserModel();
			param.setIsValid(1);//有效
			param.setCheckType(ConstantUtil.CHECK_LOGINNAME);//检查登录名
			param.setLoginName("admin");
			param.setPassword("123456");
			param.setCheckType(ConstantUtil.CHECK_LOGINPWS);//密码校验
			List<TbCommonUser> userList=this.userManagerService.getUserListByParam(param);

			TbCommonUser user = userList.get(0);

			//设置用户信息
			CurrentUserInfo userInfo = new CurrentUserInfo();
			userInfo.setUserId(user.getId());//保存用户id
			userInfo.setLoginName(user.getLoginName());
			userInfo.setRealName(user.getRealName());
			userInfo.setUserType(user.getUserType());

			//角色信息设置
			if(roleParam.equals("qscxjk")){
				userInfo.setRoleCode("qscxjk");
				userInfo.setRoleName("清算程序监控");
				userInfo.setRoleType(4);
				userInfo.setRoleId(1L);
			}else{
				userInfo.setRoleCode("wlsbjk");
				userInfo.setRoleName("网络设备监控");
				userInfo.setRoleType(4);
				userInfo.setRoleId(2L);
			}

			req.setAttribute("roleName",userInfo.getRoleName());

			//保存相关的session
			req.getSession().setAttribute(ConstantUtil.CURRENT_USER_INFO, userInfo);

			req.setAttribute("roleParam", roleParam);
			return new ModelAndView("manage/manage-index");



	}
	
	//获取菜单
	@RequestMapping(params = "getMenu")
	public void getMenus(HttpServletRequest req,HttpServletResponse rsp){
		String roleParam = (String)req.getSession().getAttribute("roleParam");

		List<TbCommonFunction> allList=new ArrayList<TbCommonFunction>();
		
		//获取封装的用户信息
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();

		//角色信息设置
		if(roleParam.equals("qscxjk")){
			userInfo.setRoleCode("qscxjk");
			userInfo.setRoleName("清算程序监控");
			userInfo.setRoleType(4);
			userInfo.setRoleId(1L);
		}else{
			userInfo.setRoleCode("wlsbjk");
			userInfo.setRoleName("网络设备监控");
			userInfo.setRoleType(4);
			userInfo.setRoleId(2L);
		}

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
	public ModelAndView logout(HttpServletRequest req,HttpServletResponse response) {

		HttpSession session=req.getSession();
		//获取用户
		CurrentUserInfo userInfo=ConstantUtil.getCurrentUserInfo();

		if(userInfo!=null){
			//清除session
			session.removeAttribute(ConstantUtil.CURRENT_USER_INFO);
		}

		Cookie[] cookies=req.getCookies();

			for(int i=0;i<cookies.length;i++){
				Cookie cookie = new Cookie(cookies[i].getName(),null);
				cookie.setMaxAge(0);
				cookie.setPath("/");//根据你创建cookie的路径进行填写
				response.addCookie(cookie);
			}


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
