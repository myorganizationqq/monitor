package com.bluedon.monitor.project.service.alarm;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.system.service.IBaseService;

/**
 * @author zhouming
 * @date 2017年11月21日
 * @module
 * @comment 告警通知service实现
 */
public interface IAlarmNoticeManagerService extends IBaseService {

	public PageUtil getPageList(AlarmNotice param, PageUtil pageUtil);
}
