package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.SendMailUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.stSysFlowCurrentDt.StSysFlowCurrentDtService;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 交易子系统告警业务逻辑代码
 */
public class AlarmJYZXTJob implements Job {

    public static String JOB_NAME = "alarm_jyzxt_job";

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    @Qualifier("stSysFlowCurrentDtService")
    private StSysFlowCurrentDtService stSysFlowCurrentDtService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm)iAlarmNoticeManagerService.loadById(Alarm.class,2l);
        if(alarm==null){
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易子系统 alarm id是否等于2");
        }
        if(!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYZXT)){
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易子系统 alarm type是否为JYZXT");
        }
        //最后扫描时间
        Date time = alarm.getCreateDate();

    }
}
