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
public class NetIOJob implements Job{
    public static String JOB_NAME="NetIOJob";
    public static String GROUP_NAME="snmpGroup";
    public static String TRIGGER_NAME="NetIOTrigger";
    private static Logger logger=Logger.getLogger(NetIOJob.class);
    @Autowired
    @Qualifier("snmpAllService")
    private SnmpAllService snmpAllService;
    public NetIOJob(){
        super();
        logger.info("-------------------------------NetIOJob开始了-----------------------------------");
    }
    public void executeJob(){
        snmpAllService.saveNetIO();
    }
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    }


}
