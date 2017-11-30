package com.bluedon.monitor.project.listener;


import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.project.job.TransferTableExecutor;
import com.bluedon.monitor.project.job.TransferTableJob;
import com.bluedon.monitor.project.job.alarm.AlarmJobManager;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by JiangFeng
 * on 2017/11/10.
 */
public class StartupListener extends ContextLoaderListener implements ApplicationContextAware {

    @Override
    public void contextInitialized(ServletContextEvent event) {

        super.contextInitialized(event);
        ApplicationContext oAC =WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());

        SchedulerFactoryBean schedulerFactoryBean=oAC.getBean(SchedulerFactoryBean.class);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        String cron="0 0 1 * * ?";
        AlarmJobManager.addJob(scheduler,TransferTableJob.JOB_NAME,TransferTableJob.GROUP_NAME,TransferTableJob.TRIGGER_NAME,TransferTableJob.GROUP_NAME, TransferTableJob.class,cron);
        System.out.println(TransferTableJob.JOB_NAME+"已启动,执行周期是:"+cron);
    }

    public static void main(String[] args) {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    /**
     * 旧的导数据任务
     * @param oAC
     */
    private void startTransferTableExecutor(ApplicationContext oAC){
         Date currentDate = new Date();
        Date nextDay = DateUtil.addDay(currentDate, 1);
        long interval=0;
        try {
            //第二天凌晨
            Date nextDate =DateUtil.strToDate(DateUtil.dateToString(nextDay,DateUtil.DATE_STYLE_DATE_1),DateUtil.DATE_STYLE_DATE_1);
            //第二天凌晨1点
            Date nextExecuteDate = DateUtil.addHour(nextDate, 1);
            interval= (nextExecuteDate.getTime()-currentDate.getTime())/1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //传输表数据的定时任务
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10,new BasicThreadFactory.Builder().namingPattern("TransferTable-schedule-pool-%d").daemon(true).build());
        scheduledExecutorService.scheduleAtFixedRate(new TransferTableExecutor("TransferTableJob",oAC),interval,24*60*60, TimeUnit.SECONDS);
    }
}
