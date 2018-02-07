package com.bluedon.monitor.project.service.network;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.network.SnmpInfo;

import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2018/1/29
 * @Description
 */
public interface SnmpInfoService {
    void insert(SnmpInfo vo);
    void insertBatch(List<SnmpInfo> vos);
    void delete(long id);
    void update(SnmpInfo vo);
    SnmpInfo get(long id);
    PageUtil getPageList(SnmpInfo param, PageUtil pageUtil);
    void deleteBatch(long[] ids);
    List<SnmpInfo> getList(Map<String,Object> params);
}
