package com.bluedon.monitor.project.service.transferTable;

import com.bluedon.monitor.project.entity.transferTable.TransferTable;

import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/11/20
 * @Description
 */
public interface TransferTableService {
    List<TransferTable> getList(Map params);
}
