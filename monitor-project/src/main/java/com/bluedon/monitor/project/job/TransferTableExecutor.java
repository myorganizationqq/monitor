package com.bluedon.monitor.project.job;


import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.project.common.Oracle2Mysql;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptServiceImpl;
import com.bluedon.monitor.project.service.transferTable.TransferTableServiceImpl;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/13
 * @Description 数据库传输任务的逻辑
 */
public class TransferTableExecutor implements Runnable{

    private String jobName;
    private ApplicationContext oAC;
    private TransferTableServiceImpl transferTableService;
    private TradeFileRptServiceImpl tradeFileRptService;
    public TransferTableExecutor() {
    }
    public TransferTableExecutor(String jobName,ApplicationContext oAC) {
        this.jobName = jobName;
        this.oAC =oAC;
        transferTableService = oAC.getBean(TransferTableServiceImpl.class);
        tradeFileRptService = oAC.getBean(TradeFileRptServiceImpl.class);
    }

    @Override
    public void run() {
        Map<String, Integer> params = new HashMap<>();
        params.put("stat",1);
        if(transferTableService == null ){
            transferTableService = oAC.getBean(TransferTableServiceImpl.class);
        }
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
        if(tradeFileRptService == null ){
            tradeFileRptService = oAC.getBean(TradeFileRptServiceImpl.class);
        }
        List<TradeFileRpt> tradeFileRptVOs = tradeFileRptService.packageTradeFileRptVOs(balanceWaterNo);
        tradeFileRptService.insertBatch(tradeFileRptVOs);
    }
}
