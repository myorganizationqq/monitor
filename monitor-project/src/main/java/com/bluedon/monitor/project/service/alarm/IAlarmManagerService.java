package com.bluedon.monitor.project.service.alarm;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.system.service.IBaseService;

/**
 * @author zhouming
 * @date 2017年11月21日
 * @module
 * @comment 告警设置service实现
 */
public interface IAlarmManagerService extends IBaseService {

	public PageUtil getPageList(Alarm param, PageUtil pageUtil);
}
