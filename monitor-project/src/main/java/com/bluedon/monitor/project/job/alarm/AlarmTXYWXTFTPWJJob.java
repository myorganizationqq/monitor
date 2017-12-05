package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.communication.LogFtpService;
import com.bluedon.monitor.system.entity.TbCommonUser;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;

/**
 * 通信业务系统FTP文件告警业务逻辑代码
 */
public class AlarmTXYWXTFTPWJJob implements Job {

    public static String JOB_NAME = "alarm_txywxtftpwj_job";

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    private LogFtpService logFtpService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm) iAlarmNoticeManagerService.loadById(Alarm.class, 4l);
        if (alarm == null) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查通信业务系统FTP文件 alarm id是否等于4");
        }
        if (!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTFTPWJ)) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查通信业务系统FTP文件 alarm type是否为TXYWXTFTPWJ");
        }
        List <Map <String, Integer>> result = logFtpService.alarmCount();

        Map <String, Integer> map0 = result.get(0);
        Map <String, Integer> map1 = result.get(1);

        Map <String, String> map = new HashMap();
        Set <String> ips = new HashSet <>();
        ips.addAll(map0.keySet());
        ips.addAll(map1.keySet());
        for (String ip : ips) {
            map.put(ip, "");
        }

        Iterator <Map.Entry <String, Integer>> entries1 = map1.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry <String, Integer> entry = entries1.next();
            String ip = entry.getKey();
            Integer num = entry.getValue();

            StringBuffer alarmContent = new StringBuffer();
            if (alarm.getTxywxt_ftpcgsl_wx() != 0) {
                if (alarm.getTxywxt_ftpcgsl_wx() > num) {
                    alarmContent.append("通信业务系统FTP文件 IP：" + ip + " 危险：成功个数" + num + "<br>");
                }
            } else if (alarm.getTxywxt_ftpcgsl_gz() != 0) {
                if (alarm.getTxywxt_ftpcgsl_gz() > num) {
                    alarmContent.append("通信业务系统FTP文件 IP：" + ip + " 故障：成功个数" + num + "<br>");
                }
            }
            map.put(ip, map.get(ip) + alarmContent.toString());
        }

        Iterator <Map.Entry <String, Integer>> entries0 = map0.entrySet().iterator();
        while (entries0.hasNext()) {
            Map.Entry <String, Integer> entry = entries0.next();
            String ip = entry.getKey();
            Integer num = entry.getValue();

            StringBuffer alarmContent = new StringBuffer();
            if (alarm.getTxywxt_ftpsbsl_wx() != 0) {
                if (alarm.getTxywxt_ftpsbsl_wx() < num) {
                    alarmContent.append("通信业务系统FTP文件 IP：" + ip + " 危险：失败个数" + num + "<br>");
                }
            } else if (alarm.getTxywxt_ftpsbsl_gz() != 0) {
                if (alarm.getTxywxt_ftpsbsl_gz() < num) {
                    alarmContent.append("通信业务系统FTP文件 IP：" + ip + " 故障：失败个数" + num + "<br>");
                }
            }
            map.put(ip, map.get(ip) + alarmContent.toString());
        }


        Set <String> ss = map.keySet();
        String alarmContent = "";
        for (String s : ss) {
            String content = map.get(s);
            if(!StringUtil.isEmpty(content)){
                alarmContent += ("<br>"+content);
            }
        }

        //无告警信息则返回
        if (StringUtil.isEmpty(alarmContent)) {
            return;
        }

        String preDay = CommonUtil.getCurrentAndPreTime().get("preDay");
        String currentDay = CommonUtil.getCurrentAndPreTime().get("currentDay");
        String head = "通信业务系统FTP文件(" + preDay + "-" + currentDay + ")告警";


        String content = "时间：" + preDay + "-" + currentDay + "<br><br>" + alarmContent;

        List <String> emailUser = new ArrayList <>();
        List <String> phoneUser = new ArrayList <>();

        String emailsStr = alarm.getAlarmEmail();
        String phonesStr = alarm.getAlarmMessage();

        if (!StringUtil.isEmpty(emailsStr)) {
            String[] emails = emailsStr.split(",");
            for (String email : emails) {
                TbCommonUser user = (TbCommonUser) iAlarmNoticeManagerService.loadById(TbCommonUser.class, Long.parseLong(email));
                if (user != null && !StringUtil.isEmpty(user.getEmail())) {
                    emailUser.add(user.getEmail());
                }
            }
        }

        if (!StringUtil.isEmpty(phonesStr)) {
            String[] phones = phonesStr.split(",");
            for (String phone : phones) {
                TbCommonUser user = (TbCommonUser) iAlarmNoticeManagerService.loadById(TbCommonUser.class, Long.parseLong(phone));
                if (user != null && !StringUtil.isEmpty(user.getPhone())) {
                    phoneUser.add(user.getPhone());
                }
            }
        }

        AlarmNotice notice = new AlarmNotice();

        int zg = ips.size() * 2;
        int yc = alarmContent.split("br").length;
        int zc = zg - yc;

        notice.setNoticeIndex("ip地址："+ips.size()+"，指标："+zg+"，正常指标："+zc+"，异常指标"+yc);
        notice.setNoticeReason(content);
        notice.setNoticeName(Alarm.ALARM_TYPE_TXYWXTFTPWJ);
        notice.setCreateDate(new Date());
        notice.setUpdateDate(new Date());
        notice.setNoticeStatus("0");
        iAlarmNoticeManagerService.add(notice);
        CommonUtil.sendAlarm(head, content, phoneUser, emailUser);

    }
}
