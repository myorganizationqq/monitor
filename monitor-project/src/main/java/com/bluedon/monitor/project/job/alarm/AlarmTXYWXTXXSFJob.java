package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.communication.LogRecvsendService;
import com.bluedon.monitor.project.util.SendMailUtil;
import com.bluedon.monitor.system.entity.TbCommonUser;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 通信业务系统告警消息收发业务逻辑代码
 */
public class AlarmTXYWXTXXSFJob implements Job {

    public static String JOB_NAME = "alarm_txywxtxxsf_job";

    //日志记录对象
    private static final Logger log = Logger.getLogger(AlarmTXYWXTXXSFJob.class);

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    private LogRecvsendService logRecvsendService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm) iAlarmNoticeManagerService.loadById(Alarm.class, 3l);
        if (alarm == null) {
            throw new IllegalArgumentException("alarm基础数据异常，通信业务系统消息收发 alarm id是否等于3");
        }
        if (!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTXXSF)) {
            throw new IllegalArgumentException("alarm基础数据异常，通信业务系统消息收发 alarm type是否为TXYWXTXXSF");
        }

        List <Map <String, Object>> result = logRecvsendService.alarmCount();

        StringBuffer alarmContent = new StringBuffer();

        for (Map <String, Object> map : result) {
            long succ = Long.parseLong(map.get("SUCCESS").toString());
            long fail = Long.parseLong(map.get("FAILURE").toString());
            long min_length = Long.parseLong(map.get("MIN_LENGTH").toString());
            long max_length = Long.parseLong(map.get("MSG_LENGTH").toString());
            String link_ip = map.get("LINK_IP").toString();

            if (alarm.getTxywxt_cggs_wx() != 0) {
                if (alarm.getTxywxt_cggs_wx() > succ) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 危险：成功个数" + succ + "<br>");
                }
            } else if (alarm.getTxywxt_cggs_gz() != 0) {
                if (alarm.getTxywxt_cggs_gz() > succ) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 故障：成功个数" + succ + "<br>");
                }
            }

            if (alarm.getTxywxt_sbgs_wx() != 0) {
                if (alarm.getTxywxt_sbgs_wx() < fail) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 危险：失败个数" + fail + "<br>");
                }
            } else if (alarm.getTxywxt_sbgs_gz() != 0) {
                if (alarm.getTxywxt_sbgs_gz() < fail) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 故障：失败个数" + fail + "<br>");
                }
            }

            if (alarm.getTxywxt_jszdll_wx() != 0) {
                if (alarm.getTxywxt_jszdll_wx() < max_length) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 危险：接收最大流量" + max_length + "<br>");
                }
            } else if (alarm.getTxywxt_jszdll_gz() != 0) {
                if (alarm.getTxywxt_jszdll_gz() < max_length) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 故障：接收最大流量" + max_length + "<br>");
                }
            }

            if (alarm.getTxywxt_jszxll_wx() != 0) {
                if (alarm.getTxywxt_jszxll_wx() > min_length) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 危险：接收最小流量" + min_length + "<br>");
                }
            } else if (alarm.getTxywxt_jszxll_gz() != 0) {
                if (alarm.getTxywxt_jszxll_gz() > min_length) {
                    alarmContent.append("通信业务系统消息收发 IP：" + link_ip + " 故障：接收最小流量" + min_length + "<br>");
                }
            }
        }

        //无告警信息则返回
        if (StringUtil.isEmpty(alarmContent.toString())) {
            return;
        }

        String preDay = CommonUtil.getCurrentAndPreTime().get("preDay");
        String currentDay = CommonUtil.getCurrentAndPreTime().get("currentDay");
        String head = "通信业务系统消息收发(" + preDay + "-" + currentDay + ")告警";

        String content = "时间：" + preDay + "-" + currentDay + "<br><br>" + alarmContent;

        //告警表插入告警数据
        AlarmNotice notice = new AlarmNotice();
        int zg = result.size() * 4;
        int yc = alarmContent.toString().split("br").length - 1 < 0 ? 0 : alarmContent.toString().split("br").length - 1;
        int zc = zg - yc;

        notice.setNoticeIndex("ip地址：" + result.size() + "，指标：" + zg + "，正常指标：" + zc + "，异常指标：" + yc);
        notice.setNoticeReason(content);
        notice.setNoticeName(Alarm.ALARM_TYPE_TXYWXTXXSF);
        notice.setCreateDate(new Date());
        notice.setUpdateDate(new Date());
        notice.setNoticeStatus("0");
        iAlarmNoticeManagerService.add(notice);

        List <String> emailUser = new ArrayList <>();
        List <String> phoneUser = new ArrayList <>();

        String emailsStr = alarm.getAlarmEmail();
        String phonesStr = alarm.getAlarmMessage();
        String alarmUser = alarm.getAlarmUser();

        //是否发送告警通知
        if (StringUtil.isEmpty(emailsStr) && StringUtil.isEmpty(phonesStr)) {
            return;
        }

        //查询告警通知对象的phone 和 email
        String[] userIds = alarmUser.split(",");
        for (String userId : userIds) {
            TbCommonUser user = (TbCommonUser) iAlarmNoticeManagerService.loadById(TbCommonUser.class, Long.parseLong(userId));
            if (user != null && !StringUtil.isEmpty(user.getEmail())) {
                emailUser.add(user.getEmail());
            }
            if (user != null && !StringUtil.isEmpty(user.getPhone())) {
                phoneUser.add(user.getPhone());
            }
        }

        //发送告警通知
        if (StringUtil.isEmpty(emailsStr)) {
            emailUser.clear();
        }

        if (StringUtil.isEmpty(phonesStr)) {
            phoneUser.clear();
        }

        for (String phone : phoneUser) {
            log.info("发送短信给" + phone);
        }

        SendMailUtil mail = null;
        for (String email : emailUser) {
            log.info("发送邮件给" + email);
            mail = new SendMailUtil();
            mail.doSendHtmlEmail(head, content, email);
        }

    }
}
