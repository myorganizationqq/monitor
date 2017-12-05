package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import com.bluedon.monitor.project.model.tradeFileRpt.TradeFileRptVO;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptService;
import com.bluedon.monitor.system.entity.TbCommonUser;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 交易文件和数据告警业务逻辑代码
 */
public class AlarmJYWJHSJJob implements Job {

    public static String JOB_NAME = "alarm_jywjhsj_job";

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    @Qualifier("tradeFileRptService")
    private TradeFileRptService tradeFileRptService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm) iAlarmNoticeManagerService.loadById(Alarm.class, 1l);
        if (alarm == null) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易系统和数据 alarm id是否等于1");
        }
        if (!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYWJHSJ)) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易系统和数据 alarm type是否为JYWJHSJ");
        }

        PageUtil pageUtil = new PageUtil();
        pageUtil.setRows(Integer.MAX_VALUE);
        pageUtil = tradeFileRptService.getPageList(new TradeFileRptVO(), pageUtil);
        List <TradeFileRpt> resultList = (List <TradeFileRpt>) pageUtil.getResultList();

        StringBuffer alarmContent = new StringBuffer();

        for (TradeFileRpt t : resultList) {


            if (alarm.getJywjhsj_bhfsjgs_wx() != 0) {
                if (alarm.getJywjhsj_bhfsjgs_wx() < t.getWrongfulCount()) {
                    alarmContent.append("交易文件类型" + t.getFileType() + " 流水号" + t.getBalanceWaterNo() + "  危险：不合法数据个数为" + t.getWrongfulCount() + "<br>");
                }
            } else if (alarm.getJywjhsj_bhfsjgs_gz() != 0) {
                if (alarm.getJywjhsj_bhfsjgs_gz() < t.getWrongfulCount()) {
                    alarmContent.append("交易文件类型" + t.getFileType() + " 流水号" + t.getBalanceWaterNo() + "  故障：不合法数据个数为" + t.getWrongfulCount() + "<br>");
                }
            }

            if (alarm.getJywjhsj_cfsjgs_wx() != 0) {
                if (alarm.getJywjhsj_cfsjgs_wx() < t.getDuplicateCount()) {
                    alarmContent.append("交易文件类型" + t.getFileType() + " 流水号" + t.getBalanceWaterNo() + "  危险：重复数据个数为" + t.getDuplicateCount() + "<br>");
                }
            } else if (alarm.getJywjhsj_cfsjgs_gz() != 0) {
                if (alarm.getJywjhsj_cfsjgs_gz() < t.getDuplicateCount()) {
                    alarmContent.append("交易文件类型" + t.getFileType() + " 流水号" + t.getBalanceWaterNo() + "  故障：重复数据个数为" + t.getDuplicateCount() + "<br>");
                }
            }

            if (alarm.getJywjhsj_wfyclwjgs_wx() != 0) {
                if (alarm.getJywjhsj_wfyclwjgs_wx() < t.getNoPretreatmentCount()) {
                    alarmContent.append("交易文件类型" + t.getFileType() + "  流水号" + t.getBalanceWaterNo() + "  危险：无法预处理数据个数为" + t.getNoPretreatmentCount() + "<br>");
                }
            } else if (alarm.getJywjhsj_wfyclwjgs_gz() != 0) {
                if (alarm.getJywjhsj_wfyclwjgs_gz() < t.getNoPretreatmentCount()) {
                    alarmContent.append("交易文件类型" + t.getFileType() + "  流水号" + t.getBalanceWaterNo() + "  故障：无法预处理数据个数为" + t.getNoPretreatmentCount() + "<br>");
                }
            }

        }

        //无告警信息则返回
        if (StringUtil.isEmpty(alarmContent.toString())) {
            return;
        }

        String preDay = CommonUtil.getCurrentAndPreTime().get("preDay");
        String currentDay = CommonUtil.getCurrentAndPreTime().get("currentDay");
        String head = "交易文件和数据(" + preDay + "-" + currentDay + ")告警";


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
        int zg = 3 * resultList.size();
        int yc = alarmContent.toString().split("br").length - 1 < 0 ? 0 : alarmContent.toString().split("br").length - 1;
        int zc = zg - yc;

        notice.setNoticeIndex("指标：" + zg + "，正常指标：" + zc + "，异常指标：" + yc);
        notice.setNoticeReason(content);
        notice.setNoticeName(Alarm.ALARM_TYPE_JYWJHSJ);
        notice.setCreateDate(new Date());
        notice.setUpdateDate(new Date());
        notice.setNoticeStatus("0");
        iAlarmNoticeManagerService.add(notice);
        CommonUtil.sendAlarm(head, content, phoneUser, emailUser);

    }

}
