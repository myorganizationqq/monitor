package com.bluedon.monitor.project.job;

import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.project.common.Oracle2Mysql;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptService;
import com.bluedon.monitor.project.service.transferTable.TransferTableService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/29
 * @Description
 */
public class TransferTableJob implements Job{
    public static String JOB_NAME="transferTableJob";
    public static String GROUP_NAME="transferTableGroup";
    public static String TRIGGER_NAME="transferTableTrigger";
    @Autowired
    @Qualifier("transferTableService")
    private TransferTableService transferTableService;
    @Autowired
    @Qualifier("tradeFileRptService")
    private TradeFileRptService tradeFileRptService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Map<String, Integer> params = new HashMap<>();
        params.put("stat",1);
        List<TransferTable> list = transferTableService.getList(params);
        //每天跑昨天的数据
        Date beforeDate = DateUtil.addDay(new Date(), -1);
        String balanceWaterNo=DateUtil.dateToString(beforeDate,"yyyyMMdd")+"01";
        //balanceWaterNo="2017111501";
        String sql = " BALANCE_WATER_NO = "+ balanceWaterNo;
        for (TransferTable transferTable : list) {
            transferTable.setExtraSql(sql);
        }
        try {
           Oracle2Mysql.oracle2mysql(list);
           packageAndInsert(balanceWaterNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * 组装交易文件和数据报表,并且落表
     * @param balanceWaterNo 清算流水号
     */
    private void packageAndInsert(String balanceWaterNo) throws Exception{
        List<TradeFileRpt> tradeFileRptVOs = tradeFileRptService.packageTradeFileRptVOs(balanceWaterNo);
        tradeFileRptService.insertBatch(tradeFileRptVOs);
    }
}
