package com.bluedon.monitor.project.service.network;

import com.bluedon.monitor.common.util.PageUtil;
import com.bluedon.monitor.project.entity.network.NetworkEquipment;

import java.util.List;
import java.util.Map;

/**
 * @author JiangFeng
 * @date 2017/12/13
 * @Description
 */
public interface NetworkEquipmentService {
    void insert(NetworkEquipment vo);
    void insertBatch(List<NetworkEquipment> vos);
    void delete(long id);
    void update(NetworkEquipment vo);
    NetworkEquipment get(long id);
    PageUtil getPageList(NetworkEquipment param, PageUtil pageUtil);
    void deleteBatch(long[] ids);
    Map<Long,String> getServerInfoIds();
    List<String> getIpById(long id);
}
