package com.bluedon.monitor.project.job.alarm;

/**
 * Created by ${Time} ${Day} ming on 2017/11/25.
 */

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.SendMailUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.project.service.stSysFlowCurrentDt.StSysFlowCurrentDtService;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptService;
import com.bluedon.monitor.system.entity.TbCommonUser;
import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 交易子系统告警业务逻辑代码
 */
public class AlarmJYZXTJob implements Job {

    public static String JOB_NAME = "alarm_jyzxt_job";

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
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易子系统 alarm id是否等于2");
        }
        if (!alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYZXT)) {
            throw new IllegalArgumentException("alarm基础数据异常，请检查交易子系统 alarm type是否为JYZXT");
        }
        //获取数据
        List <StSysFlowCurrentDt> resultList = stSysFlowCurrentDtService.alarmCount();

        //毫秒转为分钟的除数
        int m = 1000*60;

        for (StSysFlowCurrentDt t : resultList) {

            StringBuffer alarmContent = new StringBuffer();

            /**
             * 清算子系统_生成寄存器数据统计报表
             */
            if(!StringUtil.isEmpty(t.getRemark())){

                if (alarm.getJyzxt_ccjcqsjtjbb_wx() != 0 && t.getRemark().equals("生成寄存器数据统计报表")) {
                    if(t.getEndTime()==null){
                        alarmContent.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
                    }else if(t.getBeginTime()==null){
                        alarmContent.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
                    }else{
                        long time = t.getEndTime().getTime()/m-t.getBeginTime().getTime()/m;
                        if (alarm.getJywjhsj_bhfsjgs_wx() < time) {
                            alarmContent.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  危险：流程执行时间为" + time + "<br>");
                        }
                    }

                } else if (alarm.getJyzxt_ccjcqsjtjbb_gz() != 0 && t.getRemark().equals("生成寄存器数据统计报表")) {
                    if(t.getEndTime()==null){
                        alarmContent.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程endTime为空<br>");
                    }else if(t.getBeginTime()==null){
                        alarmContent.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  流程beginTime为空<br>");
                    }else{
                        long time = t.getEndTime().getTime()/m-t.getBeginTime().getTime()/m;
                        if (alarm.getJywjhsj_bhfsjgs_gz() < time) {
                            alarmContent.append("清算流程" + t.getRemark() + " 流水号" + t.getBalanceWaterNo() + "  故障：流程执行时间为" + time + "<br>");
                        }
                    }
                }

            }



            //无告警信息则返回
            if (StringUtil.isEmpty(alarmContent.toString())) {
                return;
            }

            String preDay = CommonUtil.getCurrentAndPreTime().get("preDay");
            String currentDay = CommonUtil.getCurrentAndPreTime().get("currentDay");
            String head = "交易子系统(" + preDay + "-" + currentDay + ")告警";


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

            CommonUtil.sendAlarm(head, content, phoneUser, emailUser);

        }
    }

    public static void main(String[] args) {
        DateTime d = new DateTime();
    }
}
