package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.SendMailUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 通信业务系统告警业务逻辑代码
 */
public class AlarmTXYWXTJob implements Job {

    public static String JOB_NAME = "alarm_txywxt_job";


    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        SendMailUtil.getInstance().doSendHtmlEmail("通信业务系统", "test test test <a href='www.baidu.com'>百度</a>", "664219802@qq.com");
    }
}
