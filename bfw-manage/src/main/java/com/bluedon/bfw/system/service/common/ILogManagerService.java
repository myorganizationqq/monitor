package com.bluedon.bfw.system.service.common;

import com.bluedon.bfw.system.model.system.LoginUserModel;

/**
 * @author liangliwen
 * @date 2015年4月21日
 * @module 日志管理
 * @comment 日志管理实现接口
 */
public interface ILogManagerService {

	public boolean insertLog(String module, String operation, LoginUserModel user, String tbName, String tbId, String ip);
}
