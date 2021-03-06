package com.bluedon.monitor.project.controller.network;

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.network.SnmpInfo;
import com.bluedon.monitor.project.service.network.NetworkEquipmentService;
import com.bluedon.monitor.project.service.network.SnmpInfoService;
import com.bluedon.monitor.system.model.util.ComboBox;
import com.bluedon.monitor.system.model.util.OperResult;
import com.bluedon.monitor.system.util.ConstantUtil;
import com.bluedon.monitor.system.util.ToolUtil;
import org.apache.log4j.Logger;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2018/1/30
 * @Description
 */
@Controller
@RequestMapping("/project/network/snmpInfoController")
public class SnmpInfoController {
    @Autowired
    private SnmpInfoService snmpInfoService;
    @Autowired
    private NetworkEquipmentService networkEquipmentService;

    private static Logger logger = Logger.getLogger(SnmpInfoController.class);

    @RequestMapping(params = "toEdit")
    public ModelAndView toEdit(long id){
        SnmpInfo snmpInfo =new SnmpInfo();
        ModelAndView mv = new ModelAndView("project/network/snmpInfoEdit");
        if (id > 0 ){
            snmpInfo=snmpInfoService.get(id);
        }else {
            snmpInfo.setId(0L);
        }
        mv.addObject("obj",snmpInfo);
        return mv;
    }

    /**
     * 加载列表页面
     * @param req
     * @return
     */
    @RequestMapping(params="toPageList")
    public ModelAndView toPageList(HttpServletRequest req){
        return new ModelAndView("project/network/snmpInfoList");
    }
    /**
     * 获取角色列表
     * @param param
     * @param pageUtil
     * @param req
     * @param rsp
     */
    @RequestMapping(params="getPageList")
    public void pageList(SnmpInfo param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){

        logger.debug("获取参数:"+ CommonUtil.Object2String(param));
        logger.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

        //获取分页数据
        pageUtil = this.snmpInfoService.getPageList(param, pageUtil);

        //返回分页数据
        ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
    }
    @RequestMapping(params = "saveOrUpdate")
    public void saveOrUpdate(@ModelAttribute("obj") SnmpInfo obj, HttpServletResponse rsp) {
        OperResult rs = new OperResult();
        try {
            //新增
            if (obj.getId() == 0) {
                snmpInfoService.insert(obj);
                //更新
            } else {
                snmpInfoService.update(obj);
            }
            rs.setResultCode(ConstantUtil.RESULT_SUCCESS);
            rs.setData(obj);
            rs.setMsg("操作成功");
        } catch (Exception e) {
            rs.setResultCode(ConstantUtil.RESULT_FAILED);
            rs.setMsg("操作失败");
            e.printStackTrace();
        }

        ToolUtil.getData(rsp, rs);
    }


    @RequestMapping(params = "getServerInfoIds")
    @Description("获取服务器ID")
    public void getServerInfoIds(HttpServletResponse response){
        Map<Long, String> map = networkEquipmentService.getServerInfoIds();
        List<ComboBox> cList=new ArrayList<ComboBox>();
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            ComboBox c = new ComboBox();
            c.setId(entry.getKey().toString());
            c.setText(entry.getValue());
            cList.add(c);
        }
        ToolUtil.getCombo(response, cList);
    }

    @RequestMapping(params = "getVersions")
    @Description("获取snmp版本")
    public void getVersions(HttpServletResponse response){
        Map<String, String> map = ConstantUtil.SNMP_VERSION;
        List<ComboBox> cList=new ArrayList<ComboBox>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            ComboBox c = new ComboBox();
            c.setId(entry.getKey().toString());
            c.setText(entry.getValue());
            cList.add(c);
        }
        ToolUtil.getCombo(response, cList);
    }
    @RequestMapping(params = "getIpByServerInfoIds")
    @Description("获取服务器IP")
    @ResponseBody
    public Object getIpByServerInfoIds(HttpServletResponse response,long serverInfoId){
        List<String>  ips= networkEquipmentService.getIpById(serverInfoId);
        return ips;    }

    @RequestMapping(params = "del")
    @Description("批量删除")
    public void del(@Param long[] idArr, HttpServletResponse response){
        snmpInfoService.deleteBatch(idArr);
        OperResult operResult = new OperResult();
        operResult.setResultCode(ConstantUtil.RESULT_SUCCESS);
        operResult.setMsg("删除成功");
        ToolUtil.getData(response,operResult);
    }
}
