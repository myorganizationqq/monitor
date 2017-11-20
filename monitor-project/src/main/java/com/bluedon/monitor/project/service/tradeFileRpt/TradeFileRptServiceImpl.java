package com.bluedon.monitor.project.service.tradeFileRpt;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import com.bluedon.monitor.project.entity.transferTable.TransferTable;
import com.bluedon.monitor.project.service.transferTable.TransferTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author JiangFeng
 * @date 2017/11/14
 * @Description
 */
@Service("tradeFileRptService")
public class TradeFileRptServiceImpl {
    @Autowired
    @Qualifier("hibernateDao")
    private IBaseDao<TradeFileRpt> hibernateDao;
    @Autowired
    private TransferTableServiceImpl transferTableService;

    /*public List<TradeFileRpt> getList(TradeFileRpt vo) {

        return tradeFileRptDao.getList(vo);
    }*/

    public TradeFileRpt getOne(long id) {
        TradeFileRpt tradeFileRpt = hibernateDao.queryById(TradeFileRpt.class, id);
        return tradeFileRpt;
    }

    public void insert(TradeFileRpt vo) {
        hibernateDao.save(vo);
    }

    public void insertBatch(List<TradeFileRpt> vos) {
        if (vos != null && vos.size() > 0){
           // tradeFileRptDao.insertBatch(vos);
            hibernateDao.batchInsert(vos,1000);
        }
    }

    public void delete(long id) {
        hibernateDao.delete(TradeFileRpt.class,id);
    }

    public void update(TradeFileRpt vo) {
        hibernateDao.update(vo);
    }
    public List<TradeFileRpt> packageTradeFileRptVOs(String balanceWaterNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("balanceWaterNo",balanceWaterNo);
        List<TradeFileRpt> tradeFileRptVOs = getTradeFileRptsFormLogFile(params);
        if (tradeFileRptVOs == null || tradeFileRptVOs.size() == 0){
            return null;
        }
        Map<String, Object> transferTableMap = new HashMap<>();
        transferTableMap.put("stat",1);
        transferTableMap.put("tableType","BALANCE01");
        List<TransferTable> transferTables = transferTableService.getList(transferTableMap);
        for (TradeFileRpt tradeFileRptVO : tradeFileRptVOs) {
            params.put("fileType",tradeFileRptVO.getFileType());
            int handleCount=0, wrongfulCount = 0,duplicateCount = 0,noPretreatmentCount = 0;
            noPretreatmentCount = getNoPretreatmentCount(params);
            for (TransferTable transferTable : transferTables) {
                params.put("tableName",transferTable.getExpTableName());
                List<Map<String, Object>> errorCountList = null;
                try {
                    errorCountList = getErrorCount(params);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
                for (Map<String, Object> errorCountMap : errorCountList) {
                    Object errorCode = errorCountMap.get("errCode");
                    int count = Integer.valueOf(errorCountMap.get("count")+"");
                    //重复的
                    if ("F0".equals(errorCode)){
                        duplicateCount+=count;
                    }else{
                        wrongfulCount+=count;
                    }
                }
            }
            //这里接入新框架时候需要改造
            tradeFileRptVO.setWrongfulCount(wrongfulCount);
            tradeFileRptVO.setDuplicateCount(duplicateCount);
            tradeFileRptVO.setNoPretreatmentCount(noPretreatmentCount);
        }
        return  tradeFileRptVOs;
    }

    /**
     *获取错误码和错误数据的集合
     * @param params
     * @return
     */
    private List<Map<String, Object>> getErrorCount(Map<String, Object> params) {
        List<Map<String, Object>> errorCountList;
        String hql="SELECT a.ERR_CODE errCode,count(1) count from "+params.get("tableName")+" a\n" +
                "         LEFT JOIN  ST_LOG_FILE_dt b\n" +
                "         ON a.FILE_NAME = b.FILE_NAME\n" +
                "         WHERE a.balance_water_no = "+params.get("balanceWaterNo")+" AND  b.FILE_TYPE = '"+params.get("fileType")+"'\n" +
                "        group by a.ERR_CODE";
        errorCountList = hibernateDao.selectBySql(hql);
        return errorCountList;
    }

    /**
     *获取无法处理数据
     * @param params
     * @return
     */
    private int getNoPretreatmentCount(Map<String, Object> params) {
        int noPretreatmentCount;
        String hql="SELECT count(1) count FROM  st_error_record_dt a\n" +
                "         LEFT JOIN  ST_LOG_FILE_dt b\n" +
                "         ON a.FILE_NAME = b.FILE_NAME\n" +
                "        WHERE a.balance_water_no = "+params.get("balanceWaterNo")+" AND b.FILE_TYPE = '"+params.get("fileType")+"'";
        List<Map<String,Object>> list = hibernateDao.selectBySql(hql);
        Object count = list.get(0).get("count");
        return Integer.valueOf(count.toString());
    }

    /**
     * 根据log_file表汇总tradeFileRpt数据
     * @param params
     * @return
     */
    private List<TradeFileRpt> getTradeFileRptsFormLogFile(Map<String, Object> params) {
        String hql ="select  a.balance_water_no balanceWaterNo,a.file_type fileType,count(distinct a.file_name) fileCount\n" +
                "            from st_log_file_dt a\n" +
                "         where a.balance_water_no = " +params.get("balanceWaterNo")+
                "         group by a.file_type,a.BALANCE_WATER_NO\n" +
                "         ORDER BY a.BALANCE_WATER_NO,a.file_type";
        List<Map<String,Object>> list = hibernateDao.selectBySql(hql);
        ArrayList<TradeFileRpt> tradeFileRpts = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : list) {
            TradeFileRpt tradeFileRpt = new TradeFileRpt();
            tradeFileRpt.setBalanceWaterNo(String.valueOf(stringObjectMap.get("balanceWaterNo")));
            tradeFileRpt.setFileType(String.valueOf(stringObjectMap.get("fileType")));
            tradeFileRpt.setFileCount(Integer.valueOf(String.valueOf(stringObjectMap.get("fileCount"))));
            tradeFileRpts.add(tradeFileRpt);
        }
        return tradeFileRpts;
    }

}
