package com.bluedon.monitor.project.service.stSysFlowCurrentDt;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.stSysFlowCurrentDt.StSysFlowCurrentDt;

import java.util.List;

/**
 * @author JiangFeng
 * @date 2017/11/20
 * @Description
 */
public interface StSysFlowCurrentDtService {
     List<StSysFlowCurrentDt> getPageList();
     PageUtil getPageList(StSysFlowCurrentDt param, PageUtil pageUtil);
}
