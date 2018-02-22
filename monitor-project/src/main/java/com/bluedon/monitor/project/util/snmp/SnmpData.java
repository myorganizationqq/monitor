package com.bluedon.monitor.project.util.snmp;

import com.bluedon.monitor.project.entity.network.SnmpInfo;
import org.apache.log4j.Logger;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author JiangFeng
 * @date 2018/1/17
 * @Description
 */
public class SnmpData {

    private static Logger logger =Logger.getLogger(SnmpData.class);

    public static final int DEFAULT_VERSION = SnmpConstants.version2c;
    public static final String DEFAULT_PROTOCOL = "udp";
    public static final int DEFAULT_PORT = 161;
    public static final long DEFAULT_TIMEOUT = 3 * 1000L;
    public static final int DEFAULT_RETRY = 3;

    /**
     * 创建对象communityTarget，用于返回target
     *
     * @param ip
     * @param community
     * @return CommunityTarget
     */
    public static CommunityTarget createDefault(String ip, String community) {
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + ip
                + "/" + DEFAULT_PORT);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(community));
        target.setAddress(address);
        target.setVersion(DEFAULT_VERSION);
        target.setTimeout(DEFAULT_TIMEOUT); // milliseconds
        target.setRetries(DEFAULT_RETRY);
        return target;
    }

    public static CommunityTarget createDefault(SnmpInfo snmpInfo){
        Address address = GenericAddress.parse(DEFAULT_PROTOCOL + ":" + snmpInfo.getServerIp()
                + "/" + snmpInfo.getPort());
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString(snmpInfo.getPasswd()));
        target.setAddress(address);
        int version;
        if("1".equals(snmpInfo.getVersion())){
            version=SnmpConstants.version1;
        }else if("2c".equals(snmpInfo.getVersion())){
            version=SnmpConstants.version2c;
        }else if ("3".equals(snmpInfo.getVersion())){
            version=SnmpConstants.version3;
        }else{
            version=DEFAULT_VERSION;
        }
        target.setVersion(version);
        target.setTimeout(DEFAULT_TIMEOUT); // milliseconds
        target.setRetries(DEFAULT_RETRY);
        return target;
    }
    /*根据OID，获取单条消息*/
    public static SnmpResult snmpGet(SnmpInfo snmpInfo, String oid) {
        SnmpResult snmpResult = new SnmpResult();
        snmpResult.setSuccess(true);
        CommunityTarget target = createDefault(snmpInfo);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.info("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                snmpResult.setSuccess(false);
                String msg="response is null, request time out";
                snmpResult.setErrorMsg(msg);
                logger.info(msg);
            } else {
                System.out.println("response pdu size is " + response.size());
                List<SnmpDetailResult> snmpDetailResults = new ArrayList<SnmpDetailResult>();
                snmpResult.setDetailResults(snmpDetailResults);
                for (int i = 0; i < response.size(); i++) {
                    SnmpDetailResult snmpDetailResult = getSnmpDetailResult(response, i);
                    snmpDetailResults.add(snmpDetailResult);
                }

            }
            logger.info("SNMP GET one OID value finished !");
        } catch (Exception e) {
            logger.error(e.toString());
            logger.info("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
        return snmpResult;
    }

    /**
     * 根据返回的PDU封装一个SnmpDetailResult
     * @param response
     * @param i
     * @return
     */
    private static SnmpDetailResult getSnmpDetailResult(PDU response, int i) {
        VariableBinding vb = response.get(i);
        Variable variable = vb.getVariable();
        SnmpDetailResult snmpDetailResult = new SnmpDetailResult();
        snmpDetailResult.setResponseOid(vb.getOid()+"");
        snmpDetailResult.setMessage(variable +"");
        snmpDetailResult.setErrorCode(response.getErrorStatus());
        return snmpDetailResult;
    }

    /*根据OID列表，一次获取多条OID数据，并且以List形式返回*/
    public static SnmpResult snmpGetList(SnmpInfo snmpInfo, String[] oidList)
    {
        SnmpResult snmpResult = new SnmpResult();
        snmpResult.setSuccess(true);
        CommunityTarget target = createDefault(snmpInfo);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for(String oid:oidList)
            {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.info("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            ResponseEvent respEvent = snmp.send(pdu, target);
            logger.info("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

            if (response == null) {
                snmpResult.setSuccess(false);
                snmpResult.setErrorMsg("response is null, request time out");
                logger.info("response is null, request time out");
            } else {
                System.out.println("response pdu size is " + response.size());
                List<SnmpDetailResult> snmpDetailResults = new ArrayList<SnmpDetailResult>();
                snmpResult.setDetailResults(snmpDetailResults);
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb = response.get(i);
                    SnmpDetailResult snmpDetailResult = getSnmpDetailResult(response, i);
                    snmpDetailResults.add(snmpDetailResult);
                }

            }
            logger.info("SNMP GET one OID value finished !");
        } catch (Exception e) {
            logger.error("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
        return snmpResult;
    }
    /*根据OID列表，采用异步方式一次获取多条OID数据，并且以List形式返回*/
    public static SnmpResult snmpAsynGetList(SnmpInfo snmpInfo,List<String> oidList)
    {
        final SnmpResult snmpResult = new SnmpResult();
        snmpResult.setSuccess(true);
        CommunityTarget target = createDefault(snmpInfo);
        Snmp snmp = null;
        try {
            PDU pdu = new PDU();

            for(String oid:oidList)
            {
                pdu.add(new VariableBinding(new OID(oid)));
            }

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();
            logger.info("-------> 发送PDU <-------");
            pdu.setType(PDU.GET);
            pdu.setMaxRepetitions(Integer.MAX_VALUE);
            pdu.setNonRepeaters(0);
            ResponseEvent respEvent = snmp.send(pdu, target);
            System.out.println("PeerAddress:" + respEvent.getPeerAddress());
            PDU response = respEvent.getResponse();

          /*异步获取*/
            final CountDownLatch latch = new CountDownLatch(1);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    PDU response = event.getResponse();
                    PDU request = event.getRequest();
                    System.out.println("[request]:" + request);
                    if (response == null) {
                        snmpResult.setSuccess(false);
                        snmpResult.setErrorMsg("[ERROR]: response is null");
                        logger.info("[ERROR]: response is null");
                    } else if (response.getErrorStatus() != 0) {
                        String errorMsg="[ERROR]: response status"
                                + response.getErrorStatus() + " Text:"
                                + response.getErrorStatusText();
                        snmpResult.setSuccess(false);
                        snmpResult.setErrorMsg(errorMsg);
                        logger.info(errorMsg);
                    } else {
                        System.out.println("Received response Success!");
                        List<SnmpDetailResult> snmpDetailResults = new ArrayList<SnmpDetailResult>();
                        snmpResult.setDetailResults(snmpDetailResults);
                        for (int i = 0; i < response.size(); i++) {
                            VariableBinding vb = response.get(i);
                            SnmpDetailResult snmpDetailResult = getSnmpDetailResult(response, i);
                            snmpDetailResults.add(snmpDetailResult);
                        }
                        logger.info("SNMP Asyn GetList OID finished. ");
                        latch.countDown();
                    }
                }
            };

            pdu.setType(PDU.GET);
            snmp.send(pdu, target, null, listener);
            logger.info("asyn send pdu wait for response...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.info("latch.await =:" + wait);

            snmp.close();

            System.out.println("SNMP GET one OID value finished !");
        } catch (Exception e) {
            logger.error("SNMP Get Exception:" + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }

        }
        return snmpResult;
    }
    /*根据targetOID，获取树形数据*/
    public static SnmpResult snmpWalk(SnmpInfo snmpInfo, String targetOid)
    {
        SnmpResult snmpResult = new SnmpResult();
        snmpResult.setSuccess(true);
        CommunityTarget target = createDefault(snmpInfo);
        TransportMapping transport = null;
        Snmp snmp = null;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            transport.listen();

            PDU pdu = new PDU();
            OID targetOID = new OID(targetOid);
            pdu.add(new VariableBinding(targetOID));

            boolean finished = false;
            logger.info("----> snmpwalk start <----");
            List<SnmpDetailResult> snmpDetailResults = new ArrayList<SnmpDetailResult>();
            snmpResult.setDetailResults(snmpDetailResults);
            while (!finished) {
                VariableBinding vb = null;
                ResponseEvent respEvent = snmp.getNext(pdu, target);

                PDU response = respEvent.getResponse();

                if (null == response) {
                    snmpResult.setSuccess(false);
                    snmpResult.setErrorMsg("responsePDU == null");
                    System.out.println("responsePDU == null");
                    finished = true;
                    break;
                } else {
                    vb = response.get(0);
                }
                // check finish
                finished = checkWalkFinished(targetOID, pdu, vb);
                if (!finished) {
                    SnmpDetailResult snmpDetailResult = getSnmpDetailResult(response,0);
                    snmpDetailResults.add(snmpDetailResult);

                    // Set up the variable binding for the next entry.
                    pdu.setRequestID(new Integer32(0));
                    pdu.set(0, vb);
                } else {
                    logger.info("SNMP walk OID has finished.");
                    snmp.close();
                }
            }
            logger.info("----> demo end <----");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SNMP walk Exception: " + e);
        } finally {
            if (snmp != null) {
                try {
                    snmp.close();
                } catch (IOException ex1) {
                    snmp = null;
                }
            }
        }
        return snmpResult;
    }

    private static boolean checkWalkFinished(OID targetOID, PDU pdu,
                                             VariableBinding vb) {
        boolean finished = false;
        if (pdu.getErrorStatus() != 0) {
            logger.info("[true] responsePDU.getErrorStatus() != 0 ");
            logger.info(pdu.getErrorStatusText());
            finished = true;
        } else if (vb.getOid() == null) {
            logger.info("[true] vb.getOid() == null");
            finished = true;
        } else if (vb.getOid().size() < targetOID.size()) {
            logger.info("[true] vb.getOid().size() < targetOID.size()");
            finished = true;
        } else if (targetOID.leftMostCompare(targetOID.size(), vb.getOid()) != 0) {
            logger.info("[true] targetOID.leftMostCompare() != 0");
            finished = true;
        } else if (Null.isExceptionSyntax(vb.getVariable().getSyntax())) {
            logger.info("[true] Null.isExceptionSyntax(vb.getVariable().getSyntax())");
            finished = true;
        } else if (vb.getOid().compareTo(targetOID) <= 0) {
            logger.info("[true] Variable received is not "
                    + "lexicographic successor of requested " + "one:");
            logger.info(vb.toString() + " <= " + targetOID);
            finished = true;
        }
        return finished;

    }
    /*根据targetOID，异步获取树形数据*/
    public static SnmpResult snmpAsynWalk(SnmpInfo snmpInfo, String oid)
    {
        final SnmpResult snmpResult=new SnmpResult();
        snmpResult.setSuccess(true);
        final CommunityTarget target = createDefault(snmpInfo);
        Snmp snmp = null;
        try {
            logger.info("----> snmpAsynWalk start <----");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);
            pdu.add(new VariableBinding(targetOID));

            final List<SnmpDetailResult> snmpDetailResults = new ArrayList<SnmpDetailResult>();
            snmpResult.setDetailResults(snmpDetailResults);
            ResponseListener listener = new ResponseListener() {
                public void onResponse(ResponseEvent event) {
                    ((Snmp) event.getSource()).cancel(event.getRequest(), this);

                    try {
                        PDU response = event.getResponse();
                        // PDU request = event.getRequest();
                        // System.out.println("[request]:" + request);
                        if (response == null) {
                            snmpResult.setSuccess(false);
                            snmpResult.setErrorMsg("[ERROR]: response is null");
                            logger.info("[ERROR]: response is null");
                        } else if (response.getErrorStatus() != 0) {
                            snmpResult.setSuccess(false);
                            String errorMsg="[ERROR]: response status"
                                    + response.getErrorStatus() + " Text:"
                                    + response.getErrorStatusText();
                            snmpResult.setErrorMsg(errorMsg);
                            logger.info(errorMsg);
                        } else {
                            //System.out.println("Received Walk response value :");
                            VariableBinding vb = response.get(0);

                            boolean finished = checkWalkFinished(targetOID,pdu, vb);
                            if (!finished) {
                                SnmpDetailResult snmpDetailResult = getSnmpDetailResult(response, 0);
                                snmpDetailResults.add(snmpDetailResult);
                                pdu.setRequestID(new Integer32(0));
                                pdu.set(0, vb);
                                ((Snmp) event.getSource()).getNext(pdu, target,
                                        null, this);
                            } else {
                                logger.info("SNMP Asyn walk OID value success !");
                                latch.countDown();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        latch.countDown();
                    }

                }
            };

            snmp.getNext(pdu, target, null, listener);
            logger.info("pdu 已发送,等到异步处理结果...");

            boolean wait = latch.await(30, TimeUnit.SECONDS);
            logger.info("latch.await =:" + wait);
            snmp.close();

            logger.info("----> demo end <----");
        } catch (Exception e) {
            logger.info("SNMP Asyn Walk Exception:" + e);
        }
        return snmpResult;
    }
    /*根据OID和指定string来设置设备的数据*/
    public static void setPDU(SnmpInfo snmpInfo,String oid,String val) throws IOException
    {
        CommunityTarget target = createDefault(snmpInfo);
        Snmp snmp = null;
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid),new OctetString(val)));
        pdu.setType(PDU.SET);

        DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        snmp.listen();
        logger.info("-------> setPDU <-------");
        snmp.send(pdu, target);
        snmp.close();
    }

    /*public static SnmpResult walkTableUtils(SnmpInfo snmpInfo,String oid){
        final SnmpResult snmpResult=new SnmpResult();
        snmpResult.setSuccess(true);
        final CommunityTarget target = createDefault(snmpInfo);
        Snmp snmp = null;
        try {
            logger.info("----> snmpAsynWalk start <----");

            DefaultUdpTransportMapping transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            snmp.listen();

            final PDU pdu = new PDU();
            final OID targetOID = new OID(oid);
            final CountDownLatch latch = new CountDownLatch(1);

            final TableUtils tableUtils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
            tableUtils.setMaxNumRowsPerPDU(5);

            new TableListener() {
                @Override
                public boolean next(TableEvent tableEvent) {
                    return false;
                }

                @Override
                public void finished(TableEvent tableEvent) {

                }

                @Override
                public boolean isFinished() {
                    return false;
                }
            };
        }catch (Exception e){

        }
        return null;
    }*/
}
