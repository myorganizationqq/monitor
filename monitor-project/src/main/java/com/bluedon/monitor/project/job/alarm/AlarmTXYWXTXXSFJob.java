package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.communication.LogRecvsendService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * 通信业务系统告警消息收发业务逻辑代码
 */
public class AlarmTXYWXTXXSFJob implements Job {

    public static String JOB_NAME = "alarm_txywxtxxsf_job";

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    private LogRecvsendService logRecvsendService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm)iAlarmNoticeManagerService.loadById(Alarm.class,3l);
        if(alarm==null){
            throw new IllegalArgumentException("alarm基础数据异常，通信业务系统消息收发 alarm id是否等于3");
        }
        if(!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTXXSF)){
            throw new IllegalArgumentException("alarm基础数据异常，通信业务系统消息收发 alarm type是否为TXYWXTXXSF");
        }

        List<Map<String, Object>> result = logRecvsendService.alarmCount();

        for(Map<String, Object> map : result) {
            String succ = map.get("SUCCESS").toString();
            String fail = map.get("FAILURE").toString();
            String min_length = map.get("MIN_LENGTH").toString();
            String max_length = map.get("MSG_LENGTH").toString();
            String link_ip = map.get("LINK_IP").toString();
        }

    }
}
