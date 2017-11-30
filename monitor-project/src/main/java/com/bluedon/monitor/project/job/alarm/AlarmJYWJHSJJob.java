package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.SendMailUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptService;
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

    @Autowired
    @Qualifier("tradeFileRptService")
    private TradeFileRptService tradeFileRptService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm)iAlarmNoticeManagerService.loadById(Alarm.class,1);
        if(alarm==null){
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易系统和数据 alarm id是否等于1");
        }
        if(!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYWJHSJ)){
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易系统和数据 alarm type是否为JYWJHSJ");
        }
        //最后扫描时间
        Date time = alarm.getCreateDate();

    }
}
