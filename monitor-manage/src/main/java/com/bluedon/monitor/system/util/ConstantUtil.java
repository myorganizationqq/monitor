package com.bluedon.monitor.system.util;

import com.bluedon.monitor.system.model.util.CurrentUserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author		: 
 * @date		: 2015年1月19日
 * @module		: 系统模块
 * @comment	: 常量类
 */
public class ConstantUtil {
	
	/** 通用:成功返回码 **/
	public static final Integer RESULT_SUCCESS = 0;
	
	/** 通用:失败返回码 **/
	public static final Integer RESULT_FAILED = 1;
	
	/** 用户session信息存放名称 **/
	public static final String SESSION_USER = "LOGINED_USER";
	
	
	/**
	 * 登录时检查类型
	 */
	public static int CHECK_LOGINNAME=0;//检查登录名是否存在	
	public static int CHECK_LOGININFO=1;//检查登录名、单位和用户类别是否匹配
	public static int CHECK_LOGINPWS=2;//检查用户登录信息与密码是否匹配
	
	/** 菜单:菜单等级 **/
	public static final Integer ROOT_MENU_LEVEL = 0;//顶级菜单
	public static final Integer SECOND_MENU_LEVEL = 1;//二级菜单
	public static final Integer THIRD_MENU_LEVEL = 2;//三级菜单
	public static final Integer FOURTH_MENU_LEVEL = 3;//四级菜单
	public static final Integer FIFTH_MENU_LEVEL = 4;//五级菜单
	
	//Map	
	/**
	 * 是否有效
	 */
	public final static Map<Integer, String> IS_VAILD = new TreeMap<Integer, String>() {
		{
			put(0, "无效");
			put(1, "正常");
		};
	};
	
	/**
	 * 用户类别
	 */
	public final static Map<Integer, String> USER_TYPE = new TreeMap<Integer, String>() {
		{
			put(1, "教师");
			put(2, "学生");
			put(3, "家长");
			put(4, "管理员");
			put(5, "其他");
		};
	};
	
	/**
	 * 日志模板
	 */
	public final static Map<String, String> LOG_MODUEL = new TreeMap<String, String>() {
		{
			put("roleManager", "角色管理");
			put("userManager", "用户管理");
			put("orgManager", "组织机构管理");
			//put("", "");
		};
	};
	
	/**
	 * 日子操作类型
	 */
	public final static Map<String, String> LOG_OPERATION = new TreeMap<String, String>() {
		{
			put("query", "查询");
			put("add", "新增");
			put("update", "修改");
			put("delete", "删除");
			put("upload", "上传");
		};
	};
	
	/**
	 * 角色类别
	 */
	public final static Map<Integer, String> ROLE_TYPE = new TreeMap<Integer, String>() {
		{
			put(1, "教师");
			put(2, "学生");
			put(3, "家长");
			put(4, "管理员");
			put(5, "其他");
		};
	};
	
	public final static Map<String,String> FINISH_FLAG = new HashMap<String, String>(){
		{
			put("","全部状态");
			put("0","未开始");
			put("1","进行中");
			put("2","已完成");
		}
	};

	public final static Map<String,String> FILE_TYPE = new HashMap<String, String>(){
		{
			put("","请选择");
			put("AUD","审计文件");
			put("TRX","交易文件");
			put("PRO","收益文件");
			put("REG","寄存器文件");
			put("TCD","金融ic卡交易文件");
		}
	};

	public final static Map<String,String> NEWWORK_EQUIPMENT_TYPE = new HashMap<String, String>(){
		{
			put("","请选择");
			put("JHJ","交换机");
			put("FHQ","防火墙");
			put("FBD","防病毒");
		}
	};
    /*操作系统*/
	public final static Map<Integer,String> OS_TYPE = new HashMap<Integer, String>(){
		{
			put(1,"Linux");
			put(2,"H3C");
		}
	};

	public final static Map<String,String> SNMP_VERSION = new HashMap<String, String>(){
		{
			put("1","1");
			put("2c","2c");
		}
	};
	
	
	
	//登录之后封装保存用户的信息
	public static final String CURRENT_USER_INFO = "CurrentUserInfo";//session名称

	public static final CurrentUserInfo getCurrentUserInfo(){//获取session
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest().getSession();
		CurrentUserInfo u=(CurrentUserInfo)session.getAttribute(CURRENT_USER_INFO);
		return u;
	}
}
