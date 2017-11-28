package com.bluedon.monitor.project.service.tradeFileRpt;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.tradeFileRpt.TradeFileRpt;

import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/20
 * @Description
 */
public interface TradeFileRptService {
    Map<String, Integer> getFileCountList();
    Map<String, Integer> getHandleCountList();
    Map<String, Integer> getWrongfulCountList();
    Map<String, Integer> getDuplicateCountList();
    Map<String, Integer> getNoPretreatmentCountList();
    TradeFileRpt getOne(long id);
    void insert(TradeFileRpt vo);
    void insertBatch(List<TradeFileRpt> vos);
    void delete(long id);
    void update(TradeFileRpt vo);
    List<TradeFileRpt> packageTradeFileRptVOs(String balanceWaterNo);

    PageUtil getPageList(TradeFileRpt param, PageUtil pageUtil);
}
