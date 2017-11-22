package com.bluedon.monitor.project.controller.alarm;

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.common.util.StringUtil;
import com.bluedon.monitor.common.util.model.ModelMapper;
import com.bluedon.monitor.common.util.model.ModelMapperFactory;
import com.bluedon.monitor.project.entity.alarm.Alarm;
import com.bluedon.monitor.project.service.alarm.IAlarmManagerService;
import com.bluedon.monitor.system.entity.TbCommonFunction;
import com.bluedon.monitor.system.entity.TbCommonOperation;
import com.bluedon.monitor.system.entity.TbCommonRole;
import com.bluedon.monitor.system.entity.TbCommonRoleFunction;
import com.bluedon.monitor.system.model.system.RoleManagerModel;
import com.bluedon.monitor.system.model.util.ComboTree;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.service.system.IFunctionManagerService;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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



	/**
	 * 加载列表页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toPageList")
	public ModelAndView toPageList(HttpServletRequest req){		
		return new ModelAndView("alarm/alarmManage");
	}

	/**
	 * 获取角色列表
	 * @param param
	 * @param pageUtil
	 * @param req
	 * @param rsp
	 */
	@RequestMapping(params="getAlarmList")
	public void pageList(Alarm param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){

		//获取分页数据
		pageUtil = alarmService.getPageList(param, pageUtil);

		//返回分页数据
		ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
	}

	/**
	 * 跳转新增页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toAdd")
	public ModelAndView toAdd(Alarm param,HttpServletRequest req){
		if(StringUtil.isEmpty(param.getAlarmType())){
			throw new IllegalArgumentException("告警配置新增告警配置类型不能为空");
		}

		return new ModelAndView("alarm/alarm"+param.getAlarmType()+"Edit");
	}

	/**
	 * 跳转编辑页面
	 * @param param
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toEdit")
	public ModelAndView toEdit(Alarm param,HttpServletRequest req){
	    if(param.getId()==0){
	        throw new IllegalArgumentException("告警配置编辑跳转id不能为空");
        }
		Alarm alarm = (Alarm) alarmService.loadById(Alarm.class,param.getId());

	    req.setAttribute("obj",alarm);
		return new ModelAndView("alarm/alarm"+alarm.getAlarmType()+"Edit");

	}

	/**
	 * 跳转告警通知页面
	 * @param param
	 * @param req
	 * @return
	 */
	@RequestMapping(params="toEditNotice")
	public ModelAndView toEditNotice(Alarm param,HttpServletRequest req){
		if(param.getId()==0){
			throw new IllegalArgumentException("告警通知编辑跳转id不能为空");
		}
		Alarm alarm = (Alarm) alarmService.loadById(Alarm.class,param.getId());

		req.setAttribute("obj",alarm);
		return new ModelAndView("alarm/alarm"+param.getAlarmType()+"EditNotice");


	}

	/**
	 * 保存告警配置信息
	 * @param param
	 * @param rsp
	 */
	@RequestMapping(params="saveOrUpdate")
	public void saveOrUpdate(Alarm param,HttpServletResponse rsp){

		OperResult rs = new OperResult();
		try {
			if(param.getId()!=0){
				log.debug("alarm配置操作：修改，id="+param.getId());
				param.setUpdateDate(new Date());
				alarmService.update(param);

			}else{
				log.debug("alarm配置操作：新增");
				//创建时间
				param.setCreateDate(new Date());
				param.setUpdateDate(new Date());

				alarmService.add(param);
			}

			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setData(param);
			rs.setMsg("操作成功");
		}catch (Exception e){
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("操作失败");
			e.printStackTrace();
		}

		ToolUtil.getData(rsp, rs);
	}

	/**
	 * 删除告警配置
	 * @param param
	 * @param rsp
	 */
	@RequestMapping(params="delete")
	public void delete(Alarm param,HttpServletResponse rsp){
		if(param.getId()==0){
			throw new IllegalArgumentException("告警删除操作id不能为空");
		}

		OperResult rs = new OperResult();

		try {
			alarmService.del(param);
			log.debug("告警配置删除操作：id="+param.getId());
			rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
			rs.setMsg("删除成功");
		}catch (Exception e){
			rs.setResultCode(ConstantUtil.RESULT_FAILED);
			rs.setMsg("删除失败");
			e.printStackTrace();
		}

		ToolUtil.getData(rsp, rs);
	}


}
