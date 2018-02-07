package com.bluedon.monitor.project.service.network.impl;

import com.bluedon.monitor.common.dao.HibernateDao;
import com.bluedon.monitor.project.common.Mysql_con;
import com.bluedon.monitor.project.entity.network.NetioStatMinute;
import com.bluedon.monitor.project.entity.network.NetworkEquipment;
import com.bluedon.monitor.project.entity.network.SnmpInfo;
import com.bluedon.monitor.project.service.network.NetworkEquipmentService;
import com.bluedon.monitor.project.service.network.SnmpAllService;
import com.bluedon.monitor.project.service.network.SnmpInfoService;
import com.bluedon.monitor.project.util.snmp.OidConstant;
import com.bluedon.monitor.project.util.snmp.SnmpData;
import com.bluedon.monitor.project.util.snmp.SnmpDetailResult;
import com.bluedon.monitor.project.util.snmp.SnmpResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * @author JiangFeng
 * @date 2018/2/1
 * @Description
 */
@Service("snmpAllService")
public class SnmpAllServiceImpl implements SnmpAllService {
    @Autowired
    private NetworkEquipmentService networkEquipmentService;
    @Autowired
    private SnmpInfoService snmpInfoService;
    @Autowired
    private HibernateDao hibernateDao;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static Logger logger =Logger.getLogger(SnmpAllServiceImpl.class);
    @Override
    public void saveNetIO() {
        Map<String, Object> map = new HashMap<>();
        //启用状态的
        map.put("state",1);
        List<SnmpInfo> list = snmpInfoService.getList(map);
        for (SnmpInfo snmpInfo : list) {
            long serverInfoId = snmpInfo.getServerInfoId();
            //这里注意不能先删除有snmp的网络设备
            NetworkEquipment networkEquipment = networkEquipmentService.get(serverInfoId);
            //华三
            if(networkEquipment.getOsType() == 2){
                List<NetioStatMinute> netioStatMinutes = new ArrayList<>();
                Date beginDate = new Date();
                //接口数目
                int num=0;
                /*SnmpResult snmpResultIfDescr=null;
                SnmpResult snmpResultIfSpeed=null;
                SnmpResult snmpResultIfInOctets=null;
                SnmpResult snmpResultIfOutOctets=null;
                SnmpResult snmpResultIfInUcastPkts=null;
                SnmpResult snmpResultIfOutUcastPkts=null;
                SnmpResult snmpResultIfInDiscards=null;
                SnmpResult snmpResultIfOutDiscards=null;
                SnmpResult snmpResultIfInErrors=null;
                SnmpResult snmpResultIfOutErrors=null;
                try {
                    snmpResultIfDescr=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifDescr.getValue())).get();
                    snmpResultIfSpeed=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.IfSpeed.getValue())).get();
                    snmpResultIfInOctets=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifInOctets.getValue())).get();
                    snmpResultIfOutOctets=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifOutOctets.getValue())).get();
                    snmpResultIfInUcastPkts=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifInUcastPkts.getValue())).get();
                    snmpResultIfOutUcastPkts=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifOutUcastPkts.getValue())).get();
                    snmpResultIfInDiscards=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifInDiscards.getValue())).get();
                    snmpResultIfOutDiscards=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifOutDiscards.getValue())).get();
                    snmpResultIfInErrors=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifInErrors.getValue())).get();
                    snmpResultIfOutErrors=(SnmpResult)threadPoolTaskExecutor.submit(new SnmpTaskThread<SnmpResult>(snmpInfo, OidConstant.Linux.ifOutErrors.getValue())).get();
                } catch (Exception e) {
                    logger.error(e.toString());
                    continue;
                }*/
                SnmpResult snmpResultIfDescr = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifDescr.getValue());
                SnmpResult snmpResultIfSpeed = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.IfSpeed.getValue());
                SnmpResult snmpResultIfInOctets = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifInOctets.getValue());
                SnmpResult snmpResultIfOutOctets = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifOutOctets.getValue());
                SnmpResult snmpResultIfInUcastPkts = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifInUcastPkts.getValue());
                SnmpResult snmpResultIfOutUcastPkts = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifOutUcastPkts.getValue());
                SnmpResult snmpResultIfInDiscards = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifInDiscards.getValue());
                SnmpResult snmpResultIfOutDiscards = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifOutDiscards.getValue());
                SnmpResult snmpResultIfInErrors = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifInErrors.getValue());
                SnmpResult snmpResultIfOutErrors = SnmpData.snmpAsynWalk(snmpInfo, OidConstant.Linux.ifOutErrors.getValue());

                if(snmpResultIfDescr.isSuccess()){
                    num = snmpResultIfDescr.getDetailResults().size();
                    /**
                     * 需要做特殊处理
                      *192.168.88.10 这台交换机相对于总的接口数
                     * ifOutUcastPkts和ifInUcastPkts和ifInErrors和ifOutErrors  缺少18670,18671,18672 (第237,238,239个位置)
                     * ifInDiscards 缺少18673,18681,18682,18683,18687接口 (第240,246,247,248,251个位置)
                     */
                    SnmpDetailResult snmpDetailResult = new SnmpDetailResult();
                    snmpDetailResult.setMessage("0");
                    if("192.168.88.10".equals(snmpInfo.getServerIp())){
                        if(snmpResultIfInUcastPkts.isSuccess()){
                            final List<SnmpDetailResult> detailResults = snmpResultIfInUcastPkts.getDetailResults();
                            addElement(snmpDetailResult, detailResults,236,237,238);
                        }
                        if(snmpResultIfOutUcastPkts.isSuccess()){
                            final List<SnmpDetailResult> detailResults = snmpResultIfOutUcastPkts.getDetailResults();
                            addElement(snmpDetailResult, detailResults,236,237,238);
                        }
                        if(snmpResultIfInErrors.isSuccess()){
                            final List<SnmpDetailResult> detailResults = snmpResultIfInErrors.getDetailResults();
                            addElement(snmpDetailResult, detailResults,236,237,238);
                        }
                        if(snmpResultIfOutErrors.isSuccess()){
                            final List<SnmpDetailResult> detailResults = snmpResultIfOutErrors.getDetailResults();
                            addElement(snmpDetailResult, detailResults,236,237,238);
                        }
                        if(snmpResultIfInDiscards.isSuccess()){
                            final List<SnmpDetailResult> detailResults = snmpResultIfInDiscards.getDetailResults();
                            addElement(snmpDetailResult, detailResults,239,245,246,247,251);
                        }
                    }
                }else {
                    continue;
                }

                 Date endDate = new Date();
                 logger.info("网络snmp拿到数据用时:"+(endDate.getTime()-beginDate.getTime())/1000);
                for (int i=0;i<num;i++) {
                    NetioStatMinute netioStatMinute = new NetioStatMinute();
                    netioStatMinute.setServerInfoId(serverInfoId);
                    netioStatMinute.setStatBeginTm(beginDate);
                    netioStatMinute.setCreateDate(beginDate);
                    netioStatMinute.setStatEndTm(endDate);
                    if (snmpResultIfDescr.isSuccess()){
                        String message = snmpResultIfDescr.getDetailResults().get(i).getMessage();
                        netioStatMinute.setNetioname(message);
                    }
                    if (snmpResultIfSpeed.isSuccess()){
                        double ifSpeed = getIfSpeed(snmpResultIfSpeed, i);
                        netioStatMinute.setIfspeed(ifSpeed);
                    }
                    if (snmpResultIfInOctets.isSuccess()){
                        double v = getValue(snmpResultIfInOctets, i);
                        netioStatMinute.setIfinoctets(v);
                    }
                    if (snmpResultIfOutOctets.isSuccess()){
                        double v = getValue(snmpResultIfOutOctets, i);
                        netioStatMinute.setIfoutoctets(v);
                    }
                    if (snmpResultIfInUcastPkts.isSuccess()){
                        long v = getIntValue(snmpResultIfInUcastPkts, i);
                        netioStatMinute.setIfinucastpkts(v);
                    }
                    if (snmpResultIfOutUcastPkts.isSuccess()){
                        long v = getIntValue(snmpResultIfOutUcastPkts, i);
                        netioStatMinute.setIfoutucastpkts(v);
                    }

                    if (snmpResultIfInDiscards.isSuccess()){
                        long v = getIntValue(snmpResultIfInDiscards, i);
                        netioStatMinute.setIfInDiscards(v);
                    }
                    if (snmpResultIfOutDiscards.isSuccess()){
                        long v = getIntValue(snmpResultIfOutDiscards, i);
                        netioStatMinute.setIfOutDiscards(v);
                    }
                    if (snmpResultIfInErrors.isSuccess()){
                        long v = getIntValue(snmpResultIfInErrors, i);
                        netioStatMinute.setIfinerrors(v);
                    }
                    if (snmpResultIfOutErrors.isSuccess()){
                        long v = getIntValue(snmpResultIfOutErrors, i);
                        netioStatMinute.setIfouterrors(v);
                    }
                    //过滤掉没有进出流量的端口
                    if(netioStatMinute.getIfinoctets()+netioStatMinute.getIfoutoctets()>0){
                        netioStatMinutes.add(netioStatMinute);
                    }
                }
                //hibernateDao.batchInsert(netioStatMinutes,1000);
                try {
                    executeManySql(netioStatMinutes);
                } catch (SQLException e) {
                    logger.error(e.toString());
                    continue;
                }
            }
        }
    }

    /**
     * 给list集合指定位置填空对象
     * @param snmpDetailResult
     * @param detailResults
     * @param e
     */
    private void addElement(SnmpDetailResult snmpDetailResult, List<SnmpDetailResult> detailResults,int... e) {
        for (int i : e) {
            detailResults.add(i,snmpDetailResult);
        }
    }

    /**
     * 转换包个数相关的值
     * @param snmpResultIfInUcastPkts
     * @param i
     * @return
     */
    private long getIntValue(SnmpResult snmpResultIfInUcastPkts, int i) {
        String message = snmpResultIfInUcastPkts.getDetailResults().get(i).getMessage();
        long v=0;
        try {
            BigDecimal bigdecimal = new BigDecimal(message);
            v=bigdecimal.longValue();
        } catch (NumberFormatException e) {
            logger.error(e.toString());
            v=0L;
        }
        return v;
    }

    private double getIfSpeed(SnmpResult snmpResultIfSpeed, int i) {
        String message = snmpResultIfSpeed.getDetailResults().get(i).getMessage();
        Double aDouble;
        try {
            BigDecimal val = new BigDecimal(message);
            final BigDecimal bigDecimal = new BigDecimal(1024);
            // bigdecimal.
            aDouble=val.divide(bigDecimal,2,BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (NumberFormatException e) {
            logger.error(e.toString());
            aDouble=0d;
        }
        //bps单位换成kbps
        return aDouble;
    }

    /**
     * 转换速率相关的值
     * @param snmpResultIfInOctets
     * @param i
     * @return
     */
    private double getValue(SnmpResult snmpResultIfInOctets, int i) {
        String message = snmpResultIfInOctets.getDetailResults().get(i).getMessage();
        Double aDouble;
        try {
            BigDecimal val = new BigDecimal(message);
            final BigDecimal bigDecimal = new BigDecimal(1024);
            final BigDecimal bigDecimal2 = new BigDecimal(8);
            aDouble=val.divide(bigDecimal,2,BigDecimal.ROUND_HALF_UP).multiply(bigDecimal2).doubleValue();
        } catch (NumberFormatException e) {
            logger.error(e.toString());
            aDouble=0d;
        }
        //byte单位换成kbps
        return aDouble;
    }


    private  void executeManySql(List<NetioStatMinute> findList) throws SQLException {

        Connection mysqlConn = Mysql_con.createIfNon(null);
        mysqlConn.setAutoCommit(false);
        Statement stat = null;
        String pstSql = "insert into netio_stat_minute(server_info_id,netioname,ifmtu,ifspeed,ifinoctets,ifoutoctets,ifinucastpkts,ifoutucastpkts,ifinerrors,ifouterrors,ifOutDiscards,ifInDiscards,stat_begin_tm,stat_end_tm,create_date) values (";
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            tempList.add("?");
        }
        pstSql += StringUtils.join(tempList, ",") + ")";
        PreparedStatement pst = (PreparedStatement) mysqlConn.prepareStatement(pstSql);
        for (NetioStatMinute netioStatMinute : findList) {
            pst.setLong(1,netioStatMinute.getServerInfoId());
            pst.setString(2,netioStatMinute.getNetioname());
            pst.setLong(3,netioStatMinute.getIfmtu());
            pst.setDouble(4,netioStatMinute.getIfspeed());
            pst.setDouble(5,netioStatMinute.getIfinoctets());
            pst.setDouble(6,netioStatMinute.getIfoutoctets());
            pst.setLong(7,netioStatMinute.getIfinucastpkts());
            pst.setLong(8,netioStatMinute.getIfoutucastpkts());
            pst.setLong(9,netioStatMinute.getIfinerrors());
            pst.setLong(10,netioStatMinute.getIfouterrors());
            pst.setLong(11,netioStatMinute.getIfOutDiscards());
            pst.setLong(12,netioStatMinute.getIfInDiscards());
            pst.setTimestamp(13,new java.sql.Timestamp(netioStatMinute.getStatBeginTm().getTime()));
            pst.setTimestamp(14,new java.sql.Timestamp(netioStatMinute.getStatEndTm().getTime()));
            pst.setTimestamp(15,new java.sql.Timestamp(netioStatMinute.getCreateDate().getTime()));
            // 把一个SQL命令加入命令列表
            pst.addBatch();
        }
        // 执行批量更新
        pst.executeBatch();
        // 语句执行完毕，提交本事务
        mysqlConn.commit();
        pst.close();
        mysqlConn.close();// 一定要记住关闭连接，不然mysql回应为too many connection自我保护而断开。
    }


}
