package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.SendMailUtil;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 交易文件和数据告警业务逻辑代码
 */
public class AlarmJYWJHSJJob implements Job {

    public static String JOB_NAME = "alarm_jywjhsj_job";

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //AlarmNotice alarmNotice = new AlarmNotice();
        //alarmNotice.setNoticeName("交易文件和数据");
        //iAlarmNoticeManagerService.add(alarmNotice);
        //SendMailUtil.getInstance().doSendHtmlEmail("交易文件和数据", "test test test <a href='www.baidu.com'>百度</a>", "664219802@qq.com");
    }
}
