package com.bluedon.monitor.project.job.alarm;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.service.alarm.IAlarmManagerService;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

public class AlarmStart {
    //告警配置逻辑处理对象
    @Autowired
    @Qualifier("alarmServiceImpl")
    private IAlarmManagerService alarmService;

    //告警定时任务配置
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    //日志记录对象
    private static final Logger log = Logger.getLogger(AlarmStart.class);

    private static List <Alarm> alarmList = null;

    protected void execute() {

        if (alarmList != null) {
            return;
        } else {
            PageUtil pageUtil = new PageUtil();
            pageUtil.setRows(100);
            Alarm alarmParam = new Alarm();
            alarmParam.setAlarmStatus("Y");
            PageUtil pageList = alarmService.getPageList(alarmParam, pageUtil);
            alarmList = (List <Alarm>) pageList.getResultList();

            if (alarmList == null && alarmList.size() == 0) {
                log.debug("spring启动alarm配置列表为空");
                return;
            }

            log.debug("查询所有alarm告警任务，size：" + alarmList.size());

            //定时任务配置
            Scheduler sche = schedulerFactoryBean.getScheduler();

            log.debug("spring启动alarm所有状态为Y的定时任务");
            for (Alarm param : alarmList) {

                if (StringUtil.isEmpty(param.getAlarmType())) {
                    throw new IllegalArgumentException("spring启动alarm告警alarmType不能为空");
                }

                Class jobClass = null;
                String jobName = null;
                if (param.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTXXSF)) {
                    jobClass = AlarmTXYWXTXXSFJob.class;
                    jobName =  AlarmTXYWXTXXSFJob.JOB_NAME;
                } else if (param.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTFTPWJ)) {
                    jobClass = AlarmTXYWXTFTPWJJob.class;
                    jobName = AlarmTXYWXTFTPWJJob.JOB_NAME;
                } else if (param.getAlarmType().equals(Alarm.ALARM_TYPE_JYWJHSJ)) {
                    jobClass = AlarmJYWJHSJJob.class;
                    jobName = AlarmJYWJHSJJob.JOB_NAME;
                } else if (param.getAlarmType().equals(Alarm.ALARM_TYPE_JYZXT)) {
                    jobClass = AlarmJYZXTJob.class;
                    jobName = AlarmJYZXTJob.JOB_NAME;
                } else {
                    throw new IllegalArgumentException("修改告警通知操作alarmType参数不正确");
                }

                log.debug("tomcat启动 Alarm定时任务：任务名称" + jobName + "任务周期" + param.getAlarmCronTrigger());

                AlarmJobManager.addJob(sche, jobName, jobClass, param.getAlarmCronTrigger());

            }
        }

    }
}