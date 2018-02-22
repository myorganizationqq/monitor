package com.bluedon.monitor.project.job.snmp;

import com.bluedon.monitor.project.service.network.SnmpAllService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author JiangFeng
 * @date 2018/2/1
 * @Description
 */
public class MemStatJob implements Job{
    public static String JOB_NAME="MemStatJob";
    private static Logger logger=Logger.getLogger(MemStatJob.class);
    @Autowired
    @Qualifier("snmpAllService")
    private SnmpAllService snmpAllService;
    public MemStatJob(){
        super();
        logger.info("-------------------------------MemStatJob开始了-----------------------------------");
    }
    public void executeJob(){
        snmpAllService.saveMemStat();
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    }


}
