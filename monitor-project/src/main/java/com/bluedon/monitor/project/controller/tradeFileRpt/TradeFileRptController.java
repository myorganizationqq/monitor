package com.bluedon.monitor.project.controller.tradeFileRpt;

import com.alibaba.fastjson.JSON;
import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;
import com.bluedon.monitor.project.service.tradeFileRpt.TradeFileRptService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/21
 * @Description
 */
@Controller
@RequestMapping("/project/tradeFileRpt/tradeFileRptController")
public class TradeFileRptController {
    private static final Logger log = Logger.getLogger(TradeFileRptController.class);

    @Autowired
    @Qualifier("tradeFileRptService")
    private TradeFileRptService tradeFileRptService;

    /**
     * 加载列表页面
     * @param req
     * @return
     */
    @RequestMapping(params="toPageList")
    public ModelAndView toPageList(HttpServletRequest req){
        return new ModelAndView("project/tradeFileRpt/tradeFileRptList");
    }
    /**
     * 获取角色列表
     * @param param
     * @param pageUtil
     * @param req
     * @param rsp
     */
    @RequestMapping(params="getPageList")
    public void pageList(TradeFileRpt param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){

        log.debug("获取参数:"+ CommonUtil.Object2String(param));
        log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

        //参数解码
     /*   ModelMapper mapper = ModelMapperFactory.getModelMapper(param);
        Object obj = mapper.decodeModel(null);
        TradeFileRpt modelParam = null;
        if(obj instanceof TradeFileRpt){
            modelParam = (TradeFileRpt)obj;
        }
*/
        //获取分页数据
        pageUtil = this.tradeFileRptService.getPageList(param, pageUtil);

        //返回分页数据
        ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
    }

    @RequestMapping(params = "getFileType")
    public void getFileType(HttpServletResponse response){
        Map<String, String> map = ConstantUtil.FILE_TYPE;
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

    @RequestMapping(params = "getChartData")
    @ResponseBody
    public String getChartData(HttpServletResponse response,@Param String wrongType){
        Map<String,Integer> map=new HashMap<String,Integer>();
        if ("fileCount".equals(wrongType)){
            map=tradeFileRptService.getFileCountList();
        }else if("handleCount".equals(wrongType)){
            map=tradeFileRptService.getHandleCountList();
        }else if("wrongfulCount".equals(wrongType)){
            map=tradeFileRptService.getWrongfulCountList();
        }else if("duplicateCount".equals(wrongType)){
            map=tradeFileRptService.getDuplicateCountList();
        }else if("noPretreatmentCount".equals(wrongType)){
            map=tradeFileRptService.getNoPretreatmentCountList();
        }
        Map<String, String> fileTypeMap = ConstantUtil.FILE_TYPE;
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map.Entry<String, String> entry : fileTypeMap.entrySet()) {
            String key = entry.getKey();
            if(StringUtils.isNotBlank(key)){
                int temp= map.get(key) == null ? 0:map.get(key);
                resultMap.put(entry.getValue(),temp);
            }
        }
       return JSON.toJSONString(resultMap);
    }

    @RequestMapping(params = "getTotalCountData")
    @ResponseBody
    public String getTotalCountData(HttpServletResponse response){
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("故障",4);
        map.put("未知",8);
        return JSON.toJSONString(map);
    }
}
