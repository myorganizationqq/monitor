package com.bluedon.monitor.project.service.stSysFlowCurrentDt;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;

import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/20
 * @Description
 */
public interface StSysFlowCurrentDtService {
     List<StSysFlowCurrentDt> getPageList();
     PageUtil getPageList(StSysFlowCurrentDt param, PageUtil pageUtil);
     Map<String,Object> getTime(Map<String,Object> param);

     /**
      * 获取当天0点和前天0点之间的数据
      * @return
      */
     public List <StSysFlowCurrentDt> alarmCount();
}
