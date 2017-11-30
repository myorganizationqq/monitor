package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.communication.LogFtpService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;

/**
 *
 * 通信业务系统FTP文件告警业务逻辑代码
 */
public class AlarmTXYWXTFTPWJJob implements Job {

    public static String JOB_NAME = "alarm_txywxtftpwj_job";

    @Autowired
    private LogFtpService service;

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    private LogFtpService logFtpService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm)iAlarmNoticeManagerService.loadById(Alarm.class,4);
        if(alarm==null){
            throw new IllegalArgumentException("alarm基础数据异常，请检查通信业务系统FTP文件 alarm id是否等于4");
        }
        if(!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTFTPWJ)){
            throw new IllegalArgumentException("alarm基础数据异常，请检查通信业务系统FTP文件 alarm type是否为TXYWXTFTPWJ");
        }
        //最后扫描时间
        Date time = alarm.getCreateDate();

    }
}
