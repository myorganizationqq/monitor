package com.bluedon.monitor.system.service.common.impl;

import java.util.Date;

import com.bluedon.monitor.system.entity.TbLoginLog;
import com.bluedon.monitor.system.model.system.LoginUserModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.system.service.common.ILoginLogService;

/**
 * @author liangliwen
 * @date 2015年4月21日
 * @module
 * @comment 用户登录日志管理实现类
 */
@Service("loginLogServiceImpl")
public class LoginLogServiceImpl implements ILoginLogService {

	//日志记录对象
	private static final Logger log = Logger.getLogger(LoginLogServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbLoginLog> hibernateDao;
	
	/**
	 * 插入用户登录日志
	 */
	@Override
	public TbLoginLog insertLoginLog(LoginUserModel userModel, String ip) {
		log.debug("新增" + TbLoginLog.class + "开始");
		TbLoginLog loginLog = null;
		if (userModel != null) {
			loginLog = new TbLoginLog();
			loginLog.setOperateLoginname(userModel.getLoginName());
			loginLog.setLoginTime(new Date());
			loginLog.setIp(ip);
			this.hibernateDao.save(loginLog);
			log.debug("新增" + TbLoginLog.class + "结束");
		} else {
			log.debug("新增" + TbLoginLog.class + "失败");
		}
		return loginLog;
	}

	/**
	 * 编辑用户退出日志
	 */
	@Override
	public boolean editLogoutLog(TbLoginLog loginLog) {
		boolean result = false;
		log.debug("记录" + TbLoginLog.class + "退出时间开始");
		if (loginLog != null) {
			loginLog.setLogoutTime(new Date());
			this.hibernateDao.merge(loginLog);
			log.debug("记录" + TbLoginLog.class + "退出时间结束");
			result = true;
		} else {
			log.debug("记录" + TbLoginLog.class + "退出时间失败");
		}
		return result;
	}

	/**
	 * 根据ID查询登录日志
	 */
	@Override
	public TbLoginLog queryLoginLogById(long logId) {
		TbLoginLog loginLog = this.hibernateDao.queryById(TbLoginLog.class, logId);
		return loginLog;
	}

}
