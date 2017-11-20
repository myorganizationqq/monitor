package com.bluedon.monitor.project.listener;


import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.project.job.TransferTableExecutor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
        //ApplicationContext oAC = (ApplicationContext)event.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        ApplicationContext oAC =WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        /*TradeFileRptService tradeFileRptService = oAC.getBean(TradeFileRptService.class);
        TradeFileRptVO tr = tradeFileRptService.getOne(1);
        System.out.println(tr.getBalanceWaterNo()+"---------------------------------");*/
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
        scheduledExecutorService.scheduleAtFixedRate(new TransferTableExecutor("TransferTableJob",oAC),interval,24*60*60,TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
