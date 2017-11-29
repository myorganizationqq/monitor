package com.bluedon.monitor.project.controller.alarm;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.entity.alarm.AlarmNotice;
import com.bluedon.monitor.project.service.alarm.IAlarmNoticeManagerService;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author : zhouming
 * @date:2017年11月13日
 * @module: 告警模块
 * @comment: 告警通知模块管理
 */
@Controller
@RequestMapping("/alarm/alarmNoticeManagerController")
public class AlarmNoticeManagerController {
    //日志记录对象
    private static final Logger log = Logger.getLogger(AlarmNoticeManagerController.class);

    //告警配置逻辑处理对象
    @Autowired
    @Qualifier("alarmNoticeServiceImpl")
    private IAlarmNoticeManagerService alarmNoticeService;


    /**
     * 加载列表页面
     *
     * @param req
     * @return
     */
    @RequestMapping(params = "toPageList")
    public ModelAndView toPageList(HttpServletRequest req) {
        return new ModelAndView("alarm/alarmNoticeManage");
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
    public void pageList(AlarmNotice param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp) {

        //获取分页数据
        pageUtil = alarmNoticeService.getPageList(param, pageUtil);

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
    public ModelAndView toEdit(AlarmNotice param, HttpServletRequest req) {
        if (param.getId() == 0) {
            throw new IllegalArgumentException("告警配置编辑跳转id不能为空");
        }
        AlarmNotice alarm = (AlarmNotice) alarmNoticeService.loadById(Alarm.class, param.getId());

        req.setAttribute("obj", alarm);
        return new ModelAndView("alarm/alarmNoticeEdit");

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
        Alarm alarm = (Alarm) alarmNoticeService.loadById(Alarm.class, param.getId());

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
    public void saveOrUpdate(AlarmNotice param, HttpServletResponse rsp) {

        if (param.getId() == 0) {
            throw new IllegalArgumentException("告警通知编辑跳转id不能为空");
        }

        OperResult rs = new OperResult();
        try {
            log.debug("alarm配置操作：修改，id=" + param.getId());
            param.setUpdateDate(new Date());
            alarmNoticeService.update(param);

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


}