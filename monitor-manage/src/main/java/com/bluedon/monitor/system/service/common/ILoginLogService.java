package com.bluedon.monitor.system.service.common;

import com.bluedon.monitor.system.entity.TbLoginLog;
import com.bluedon.monitor.system.model.system.LoginUserModel;

/**
 * @author liangliwen
 * @date 2015年4月21日
 * @module
 * @comment 用户登录日志管理实现接口
 */
public interface ILoginLogService {

	//插入用户登录日志
	public TbLoginLog insertLoginLog(LoginUserModel userModel, String ip);
	
	//根据Id查询登录日志
	public TbLoginLog queryLoginLogById(long logId);
		
	//编辑用户退出日志
	public boolean editLogoutLog(TbLoginLog loginLog);
}
