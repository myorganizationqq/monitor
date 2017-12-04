package com.bluedon.monitor.project.controller.alarm;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.job.alarm.*;
import com.bluedon.monitor.project.service.alarm.IAlarmManagerService;
import com.bluedon.monitor.system.entity.TbCommonUser;
import com.bluedon.monitor.system.model.system.UserManagerModel;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.service.system.IUserManagerService;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : zhouming
 * @date:2017年11月13日
 * @module: 告警模块
 * @comment: 告警模块管理
 */
@Controller
@RequestMapping("/alarm/alarmManagerController")
public class AlarmManagerController {
    //日志记录对象
    private static final Logger log = Logger.getLogger(AlarmManagerController.class);

    //告警配置逻辑处理对象
    @Autowired
    @Qualifier("alarmServiceImpl")
    private IAlarmManagerService alarmService;
    @Autowired
    @Qualifier("userManagerServiceImpl")
    private IUserManagerService userManagerService;

    //告警定时任务配置
    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 加载列表页面
     *
     * @param req
     * @return
     */
    @RequestMapping(params = "toPageList")
    public ModelAndView toPageList(HttpServletRequest req) {
        return new ModelAndView("alarm/alarmManage");
    }

    /**
     * 获取角色列表
     *
     * @param param
     * @param pageUtil
     * @param req
     * @param rsp
     */
    @RequestMapping(params = "getAlarmList")
    public void pageList(Alarm param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp) {

        //获取分页数据
        pageUtil = alarmService.getPageList(param, pageUtil);

        //返回分页数据
        ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
    }

    /**
     * 跳转新增页面
     *
     * @param req
     * @return
     */
    @RequestMapping(params = "toAdd")
    public ModelAndView toAdd(Alarm param, HttpServletRequest req) {
        if (StringUtil.isEmpty(param.getAlarmType())) {
            throw new IllegalArgumentException("告警配置新增告警配置类型不能为空");
        }

        return new ModelAndView("alarm/alarm" + param.getAlarmType() + "Edit");
    }

    /**
     * 跳转编辑页面
     *
     * @param param
     * @param req
     * @return
     */
    @RequestMapping(params = "toEdit")
    public ModelAndView toEdit(Alarm param, HttpServletRequest req) {
        if (param.getId() == 0) {
            throw new IllegalArgumentException("告警配置编辑跳转id不能为空");
        }
        Alarm alarm = (Alarm) alarmService.loadById(Alarm.class, param.getId());

        req.setAttribute("obj", alarm);
        return new ModelAndView("alarm/alarm" + alarm.getAlarmType() + "Edit");

    }

    /**
     * 跳转告警通知页面
     *
     * @param param
     * @param req
     * @return
     */
    @RequestMapping(params = "toEditNotice")
    public ModelAndView toEditNotice(Alarm param, HttpServletRequest req) {
        if (param.getId() == 0) {
            throw new IllegalArgumentException("告警通知编辑跳转id不能为空");
        }
        Alarm alarm = (Alarm) alarmService.loadById(Alarm.class, param.getId());

        req.setAttribute("obj", alarm);
        return new ModelAndView("alarm/alarmNoticeEdit");


    }

    /**
     * 保存告警配置信息
     *
     * @param param
     * @param rsp
     */
    @RequestMapping(params = "saveOrUpdate")
    public void saveOrUpdate(Alarm param, HttpServletResponse rsp) {

        if (param.getId() == 0) {
            throw new IllegalArgumentException("修改告警设置操作id不能为空");
        }

        OperResult rs = new OperResult();
        try {
            log.info("alarm配置操作：修改，id=" + param.getId());
            param.setUpdateDate(new Date());
            alarmService.update(param);

            rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
            rs.setData(param);
            rs.setMsg("操作成功");
        } catch (Exception e) {
            rs.setResultCode(ConstantUtil.RESULT_FAILED);
            rs.setMsg("操作失败");
            e.printStackTrace();
        }

        ToolUtil.getData(rsp, rs);
    }

    /**
     * 保存告警配置信息
     *
     * @param param
     * @param rsp
     */
    @RequestMapping(params = "updateAlarmNotice")
    public void updateAlarmNotice(Alarm param, HttpServletResponse rsp) {
        if (param.getId() == 0) {
            throw new IllegalArgumentException("修改告警通知操作id不能为空");
        }

        OperResult rs = new OperResult();
        try {
            log.info("修改告警通知操作：修改，id=" + param.getId());
            Alarm alarm = (Alarm) alarmService.loadById(Alarm.class, param.getId());
            alarm.setAlarmUser(param.getAlarmUser());
            alarm.setAlarmEmail(param.getAlarmEmail());
            alarm.setAlarmMessage(param.getAlarmMessage());
            alarm.setUpdateDate(new Date());

            //定时任务配置
            Scheduler sche = schedulerFactoryBean.getScheduler();

            if (StringUtil.isEmpty(param.getAlarmType())) {
                throw new IllegalArgumentException("修改告警通知操作alarmType不能为空");
            }

            Class jobClass = null;
            String jobName = null;
            if (param.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTXXSF)) {
                jobClass = AlarmTXYWXTXXSFJob.class;
                jobName = AlarmTXYWXTXXSFJob.JOB_NAME;
            } else if (param.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTFTPWJ)) {
                jobClass = AlarmTXYWXTFTPWJJob.class;
                jobName = AlarmTXYWXTFTPWJJob.JOB_NAME;
            } else if (param.getAlarmType().equals(Alarm.ALARM_TYPE_JYWJHSJ)) {
                jobClass = AlarmJYWJHSJJob.class;
                jobName = AlarmJYWJHSJJob.JOB_NAME;
            } else if (param.getAlarmType().equals(Alarm.ALARM_TYPE_JYZXT)) {
                jobClass = AlarmJYZXTJob.class;
                jobName = AlarmJYZXTJob.JOB_NAME;
            } else {
                throw new IllegalArgumentException("修改告警通知操作alarmType参数不正确");
            }

            String alarmCronTrigger = "0 "+ param.getAlarmCronTriggerStart()+" " +  param.getAlarmCronTriggerHour() + " * * ?";


            if (alarm.getAlarmStatus().equals("Y") && !alarm.getAlarmCronTrigger().equals(alarmCronTrigger)) {
                log.info("Alarm修改定时任务：任务名称" + jobName + "任务周期" + alarmCronTrigger);
                AlarmJobManager.removeJob(sche, jobName);
                AlarmJobManager.addJob(sche, jobName, jobClass, alarmCronTrigger);
            }

            alarm.setAlarmCronTrigger(alarmCronTrigger);
            alarm.setAlarmCronTriggerHour(param.getAlarmCronTriggerHour());
            alarm.setAlarmCronTriggerStart(param.getAlarmCronTriggerStart());
            alarmService.update(alarm);

            rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
            rs.setData(param);
            rs.setMsg("操作成功");
        } catch (Exception e) {
            rs.setResultCode(ConstantUtil.RESULT_FAILED);
            rs.setMsg("操作失败");
            e.printStackTrace();
        }

        ToolUtil.getData(rsp, rs);
    }

    /**
     * 修改告警状态
     *
     * @param param
     * @param rsp
     */
    @RequestMapping(params = "editAlarmStatus")
    public void editAlarmStatus(Alarm param, HttpServletResponse rsp) {
        if (param.getId() == 0) {
            throw new IllegalArgumentException("修改告警状态操作id不能为空");
        }

        OperResult rs = new OperResult();

        try {
            Alarm alarm = (Alarm) alarmService.loadById(Alarm.class, param.getId());

            //定时任务配置
            Scheduler sche = schedulerFactoryBean.getScheduler();
            Class jobClass = null;
            String jobName = null;
            if (alarm.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTXXSF)) {
                jobClass = AlarmTXYWXTXXSFJob.class;
                jobName = AlarmTXYWXTXXSFJob.JOB_NAME;
            } else if (alarm.getAlarmType().equals(Alarm.ALARM_TYPE_TXYWXTFTPWJ)) {
                jobClass = AlarmTXYWXTFTPWJJob.class;
                jobName = AlarmTXYWXTFTPWJJob.JOB_NAME;
            } else if (alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYWJHSJ)) {
                jobClass = AlarmJYWJHSJJob.class;
                jobName = AlarmJYWJHSJJob.JOB_NAME;
            } else if (alarm.getAlarmType().equals(Alarm.ALARM_TYPE_JYZXT)) {
                jobClass = AlarmJYZXTJob.class;
                jobName = AlarmJYZXTJob.JOB_NAME;
            } else {
                throw new IllegalArgumentException("修改告警通知操作alarmType参数不正确");
            }

            alarm.setUpdateDate(new Date());
            if (alarm.getAlarmStatus().equals("Y")) {
                alarm.setAlarmStatus("N");

                log.info("Alarm关闭定时任务：任务名称" + jobName + "任务周期" + alarm.getAlarmCronTrigger());
                AlarmJobManager.removeJob(sche, jobName);
            } else {
                alarm.setAlarmStatus("Y");

                log.info("Alarm启动定时任务：任务名称" + jobName + "任务周期" + alarm.getAlarmCronTrigger());
                AlarmJobManager.addJob(sche, jobName, jobClass, alarm.getAlarmCronTrigger());
            }
            alarmService.update(alarm);

            log.info("修改告警状态操作操作：id=" + param.getId());
            rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
            rs.setMsg("操作成功");
        } catch (Exception e) {
            rs.setResultCode(ConstantUtil.RESULT_FAILED);
            rs.setMsg("操作失败");
            e.printStackTrace();
        }

        ToolUtil.getData(rsp, rs);
    }

    /**
     * 获取待选用户列表
     *
     * @param model
     */
    @RequestMapping(params = "getSelectingUsers")
    public void getSelectingUsers(UserManagerModel param, HttpServletRequest req, HttpServletResponse rsp) {
        param.setIsValid(1);//有效

        List <TbCommonUser> list = this.userManagerService.getUserListByParam(param);

        List <TbCommonUser> selectList = new ArrayList <>();
        Alarm alarm = (Alarm) alarmService.loadById(Alarm.class, param.getId());
        String ids = alarm.getAlarmUser();
        if (!StringUtil.isEmpty(ids)) {
            String[] users = ids.split(",");
            TbCommonUser u = null;
            for (String user : users) {
                u = (TbCommonUser) userManagerService.loadById(TbCommonUser.class, Long.parseLong(user));
                selectList.add(u);

            }

            list.removeAll(selectList);//移除已经选中的元素
        }


        ToolUtil.getCombo(rsp, list);
    }

    /**
     * 获取已选用户列表
     *
     * @param model
     */
    @RequestMapping(params = "getSelectedUsers")
    public void getSelectedUsers(UserManagerModel param, HttpServletRequest req, HttpServletResponse rsp) {
        param.setIsValid(1);//有效

        List <TbCommonUser> selectList = new ArrayList <>();
        Alarm alarm = (Alarm) alarmService.loadById(Alarm.class, param.getId());
        String ids = alarm.getAlarmUser();
        if (!StringUtil.isEmpty(ids)) {
            String[] users = ids.split(",");
            TbCommonUser u = null;
            for (String user : users) {
                u = (TbCommonUser) userManagerService.loadById(TbCommonUser.class, Long.parseLong(user));
                selectList.add(u);

            }
        }

        ToolUtil.getCombo(rsp, selectList);
    }
}
