package com.bluedon.monitor.project.controller.network;

import com.bluedon.monitor.common.util.CommonUtil;
import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.network.NetworkEquipment;
import com.bluedon.monitor.project.service.network.NetworkEquipmentService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/12/13
 * @Description
 */
@Controller
@RequestMapping("/project/network/networkEquipmentController")
public class NetworkEquipmentController {

    @Autowired
    private NetworkEquipmentService networkEquipmentService;

    private  Logger log = Logger.getLogger(NetworkEquipmentController.class);

    @RequestMapping(params = "toEdit")
    public ModelAndView toEdit(long id){
        NetworkEquipment networkEquipment =new NetworkEquipment();
        ModelAndView mv = new ModelAndView("project/network/networkEquipmentEdit");
        if (id > 0 ){
            networkEquipment=networkEquipmentService.get(id);
        }else {
            networkEquipment.setId(0L);
        }
        mv.addObject("obj",networkEquipment);
        return mv;
    }

    /**
     * 加载列表页面
     * @param req
     * @return
     */
    @RequestMapping(params="toPageList")
    public ModelAndView toPageList(HttpServletRequest req){
        return new ModelAndView("project/network/networkEquipmentList");
    }
    /**
     * 获取角色列表
     * @param param
     * @param pageUtil
     * @param req
     * @param rsp
     */
    @RequestMapping(params="getPageList")
    public void pageList(NetworkEquipment param, PageUtil pageUtil, HttpServletRequest req, HttpServletResponse rsp){

        log.debug("获取参数:"+ CommonUtil.Object2String(param));
        log.debug("获取分页:"+CommonUtil.Object2String(pageUtil));

        //获取分页数据
        pageUtil = this.networkEquipmentService.getPageList(param, pageUtil);

        //返回分页数据
        ToolUtil.getDataGrid(rsp, pageUtil.getResultList(), pageUtil.getTotalRecordNumber());
    }
    @RequestMapping(params = "saveOrUpdate")
    public void saveOrUpdate(@ModelAttribute("obj") NetworkEquipment obj, HttpServletResponse rsp) {
        OperResult rs = new OperResult();
        try {
            //新增
            if (obj.getId() == 0) {
                networkEquipmentService.insert(obj);
                //更新
            } else {
                networkEquipmentService.update(obj);
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


    @RequestMapping(params = "getType")
    @Description("获取网络设备类型")
    public void getNetworkEquipmentType(HttpServletResponse response){
        Map<String, String> map = ConstantUtil.NEWWORK_EQUIPMENT_TYPE;
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
    @RequestMapping(params = "del")
    @Description("批量删除")
    public void del(@Param long[] idArr,HttpServletResponse response){
        networkEquipmentService.deleteBatch(idArr);
        OperResult operResult = new OperResult();
        operResult.setResultCode(ConstantUtil.RESULT_SUCCESS);
        operResult.setMsg("删除成功");
        ToolUtil.getData(response,operResult);
    }
}
