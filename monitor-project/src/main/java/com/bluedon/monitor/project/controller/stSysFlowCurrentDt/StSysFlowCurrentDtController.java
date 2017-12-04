package com.bluedon.monitor.project.controller.stSysFlowCurrentDt;

import com.alibaba.fastjson.JSON;
import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.DateUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.model.stSysFlowCurrentDt.StSysFlowCurrentDtVO;
import com.bluedon.monitor.project.service.stSysFlowCurrentDt.StSysFlowCurrentDtService;
import com.bluedon.monitor.system.model.util.ComboBox;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jboss.logging.Param;
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
 * @author JiangFeng
 * @date 2017/11/21
 * @Description
 */
@Controller
@RequestMapping("/project/stSysFlowCurrentDt/stSysFlowCurrentDtController")
public class StSysFlowCurrentDtController {
    private static final Logger log = Logger.getLogger(StSysFlowCurrentDtController.class);

    @Autowired
    @Qualifier("stSysFlowCurrentDtService")
    private StSysFlowCurrentDtService stSysFlowCurrentDtService;

    /**
     * 加载列表页面
     * @param req
     * @return
     */
    @RequestMapping(params="toPageList")
    public ModelAndView toPageList(HttpServletRequest req){
        return new ModelAndView("project/stSysFlowCurrentDt/stSysFlowCurrentDtList");
    }
    /**
     * 获取角色列表
     * @param param
     * @param pageUtil
     * @param req
     * @param rsp
     */
    @RequestMapping(params="getPageList")
    public void pageList(StSysFlowCurrentDtVO param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){

        log.debug("获取参数:"+ CommonUtil.Object2String(param));
        log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

        //参数解码
       /* ModelMapper mapper = ModelMapperFactory.getModelMapper(param);
        Object obj = mapper.decodeModel(null);
        StSysFlowCurrentDt modelParam = null;
        if(obj instanceof StSysFlowCurrentDt){
            modelParam = (StSysFlowCurrentDt)obj;
        }*/
        String beginDate=param.getBeginDate();
        String endDate=param.getEndDate();

        //1.如果开始时间为空,则开始时间默认结束时间
        if(StringUtils.isBlank(beginDate)){
            beginDate=endDate;
            //3.如果结束时间为空,则结束时间默认为开始时间
        }else if(StringUtils.isBlank(endDate)) {
            endDate = beginDate;
        }
        if(StringUtils.isNotBlank(beginDate)){
            param.setBeginDate(beginDate.replace("-","")+"01");
            param.setEndDate(endDate.replace("-","")+"01");
        }

        //获取分页数据
        pageUtil = this.stSysFlowCurrentDtService.getPageList(param, pageUtil);

        //返回分页数据
        ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
    }
    @RequestMapping(params = "getFinishFlag")
    public void finishFlag(HttpServletResponse response){
        Map<String, String> map = ConstantUtil.FINISH_FLAG;
        List<ComboBox> cList=new ArrayList<ComboBox>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            ComboBox c = new ComboBox();
            c.setId(entry.getKey());
            c.setText(entry.getValue());
            if ("".equals(entry.getKey())){
                c.setSelected(true);
            }
            cList.add(c);
        }

        ToolUtil.getCombo(response, cList);
    }

    @RequestMapping(params = "getDaultTimeData")
    @ResponseBody
    public String getDaultTimeData(@Param String beginDate,@Param String endDate){
        Map<String,Object> params=new HashMap();
        //1.如果开始时间和结束时间都没有选择,就默认查询昨天的流水号数据
        if(StringUtils.isBlank(beginDate) && StringUtils.isBlank(endDate)){
            Date yesterday= DateUtil.addDay(new Date(),-1);
            String dateStr = DateUtil.dateToString(yesterday, "yyyy-MM-dd");
            beginDate=dateStr;
            endDate=dateStr;
            //2.如果开始时间为空,则开始时间默认结束时间
        }else if(StringUtils.isBlank(beginDate)){
            beginDate=endDate;
            //3.如果结束时间为空,则结束时间默认为开始时间
        }else if(StringUtils.isBlank(endDate)){
            endDate=beginDate;
        }
        params.put("beginBalanceWaterNo",beginDate.replace("-","")+"01");
        params.put("endBalanceWaterNo",endDate.replace("-","")+"01");
        Map<String, Object> timeMap = stSysFlowCurrentDtService.getTime(params);
        return JSON.toJSONString(timeMap);
    }

}
