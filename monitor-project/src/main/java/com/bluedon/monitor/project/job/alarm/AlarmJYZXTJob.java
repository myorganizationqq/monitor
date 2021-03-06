package com.bluedon.monitor.project.job.alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.util.SendMailUtil;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.common.SendMsgUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.stSysFlowCurrentDt.StSysFlowCurrentDtService;
import com.bluedon.monitor.system.entity.TbCommonUser;

/**
 * 清算子系统告警业务逻辑代码
 */
public class AlarmJYZXTJob implements Job {

    public static String JOB_NAME = "alarm_jyzxt_job";

    //日志记录对象
    private static final Logger log = Logger.getLogger(AlarmJYZXTJob.class);

    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService iAlarmNoticeManagerService;

    @Autowired
    @Qualifier("stSysFlowCurrentDtService")
    private StSysFlowCurrentDtService stSysFlowCurrentDtService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Alarm alarm = (Alarm) iAlarmNoticeManagerService.loadById(Alarm.class, 2l);
        if (alarm == null) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查清算子系统 alarm id是否等于2");
        }
        if (!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYZXT)) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查清算子系统 alarm type是否为JYZXT");
        }

        //获取数据
        List <StSysFlowCurrentDt> resultList = stSysFlowCurrentDtService.alarmCount();

        //告警信息
        StringBuffer alarmContent = new StringBuffer();

        for (StSysFlowCurrentDt t : resultList) {
            if (!StringUtil.isEmpty(t.getRemark())) {
                AlarmStringUtil.bbtj(alarm, t, alarmContent);
                AlarmStringUtil.bfbdwj(alarm, t, alarmContent);
                AlarmStringUtil.bql(alarm, t, alarmContent);
                AlarmStringUtil.ccjcqsjtjbb(alarm, t, alarmContent);
                AlarmStringUtil.ccyyssybb(alarm, t, alarmContent);
                AlarmStringUtil.cpwjrk(alarm, t, alarmContent);
                AlarmStringUtil.cshdrkftpwj(alarm, t, alarmContent);
                AlarmStringUtil.cshyctdc(alarm, t, alarmContent);
                AlarmStringUtil.dcjrickjywj(alarm, t, alarmContent);
                AlarmStringUtil.dcjricktkwj(alarm, t, alarmContent);
                AlarmStringUtil.dcyctgfcpukwj(alarm, t, alarmContent);
                AlarmStringUtil.dcyctgfptkwj(alarm, t, alarmContent);
                AlarmStringUtil.dcyctqtxlcpukwj(alarm, t, alarmContent);
                AlarmStringUtil.dcyctqtxlptkwj(alarm, t, alarmContent);
                AlarmStringUtil.dcyctygcpukwj(alarm, t, alarmContent);
                AlarmStringUtil.dcyctygptkwj(alarm, t, alarmContent);
                AlarmStringUtil.djczcwbfcfsj(alarm, t, alarmContent);
                AlarmStringUtil.drllb(alarm, t, alarmContent);
                AlarmStringUtil.fbrzjl(alarm, t, alarmContent);
                AlarmStringUtil.jrickdzbb(alarm, t, alarmContent);
                AlarmStringUtil.jrickhmd(alarm, t, alarmContent);
                AlarmStringUtil.jsmk(alarm, t, alarmContent);
                AlarmStringUtil.jsxwygjrickpj(alarm, t, alarmContent);
                AlarmStringUtil.klcjfx(alarm, t, alarmContent);
                AlarmStringUtil.sjwjxf(alarm, t, alarmContent);
                AlarmStringUtil.sjyctj(alarm, t, alarmContent);
                AlarmStringUtil.sytjmk(alarm, t, alarmContent);
                AlarmStringUtil.xfhmd(alarm, t, alarmContent);
                AlarmStringUtil.yctdzbb(alarm, t, alarmContent);
            }
        }

        //无告警信息则返回
        if (StringUtil.isEmpty(alarmContent.toString())) {
            return;
        }

        String preDay = CommonUtil.getCurrentAndPreTime().get("preDay");
        String currentDay = CommonUtil.getCurrentAndPreTime().get("currentDay");
        String head = "清算子系统(" + preDay + "-" + currentDay + ")告警";

        String content = "时间：" + preDay + "-" + currentDay + "<br><br>" + alarmContent;

        //告警表插入告警数据
        AlarmNotice notice = new AlarmNotice();
        int zg = 30;
        int yc = alarmContent.toString().split("br").length - 1 < 0 ? 0 : alarmContent.toString().split("br").length - 1;
        int zc = zg - yc;

        notice.setNoticeIndex("指标：" + zg + "，正常指标：" + zc + "，异常指标：" + yc);
        notice.setNoticeReason(content);
        notice.setNoticeName(Alarm.ALARM_TYPE_JYZXT);
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
        if(StringUtil.isEmpty(emailsStr)){
            emailUser.clear();
        }

        if(StringUtil.isEmpty(phonesStr)){
            phoneUser.clear();
        }

        //发送短信给
        SendMsgUtil.sendMessage(phoneUser, content);

        SendMailUtil mail = null;
        for (String email : emailUser) {
            log.info("发送邮件给" + email);
            mail = new SendMailUtil();
            mail.doSendHtmlEmail(head, content, email);
        }
    }

}