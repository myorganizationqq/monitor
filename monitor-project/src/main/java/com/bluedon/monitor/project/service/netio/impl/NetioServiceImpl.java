package com.bluedon.monitor.project.service.netio.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.project.service.netio.NetioService;
import com.bluedon.monitor.system.service.BaseServiceImpl;

@Service
public class NetioServiceImpl extends BaseServiceImpl implements NetioService {
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	public Map<String, Object> queryDataByTime(String time) {
		String sql = "SELECT * FROM netio_stat_minute WHERE create_date >= NOW() - INTERVAL 30 DAY LIMIT 7;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Map<String, Object> map = new HashMap<>();
		if(list == null || list.size() == 0) return map;
		String[] dateArr = new String[ list.size() ];
		double[] ifinoctetsArr = new double[ list.size() ];
		double[] ifoutoctetsArr = new double[ list.size() ];
		double[] ifinucastpktsArr = new double[ list.size() ];
		double[] ifoutucastpktsArr = new double[ list.size() ];
		int i = 0;
        for (Map<String, Object> data : list) {
            String createDate = String.valueOf(data.get("create_date"));
            double ifinoctets = Double.valueOf(String.valueOf(data.get("ifinoctets")));
            double ifoutoctets = Double.valueOf(String.valueOf(data.get("ifoutoctets")));
            double ifinucastpkts = Double.valueOf(String.valueOf(data.get("ifinucastpkts")));
            double ifoutucastpkts = Double.valueOf(String.valueOf(data.get("ifoutucastpkts")));
            dateArr[i] = createDate.substring(14, 19);
            ifinoctetsArr[i] = ifinoctets / 1024;
            ifoutoctetsArr[i] = ifoutoctets / 1024;
            ifinucastpktsArr[i] = ifinucastpkts;
            ifoutucastpktsArr[i] = ifoutucastpkts;
            i ++;
        }
        map.put("dateArr", dateArr);
        map.put("ifinoctetsArr", ifinoctetsArr);
        map.put("ifoutoctetsArr", ifoutoctetsArr);
        map.put("ifinucastpktsArr", ifinucastpktsArr);
        map.put("ifoutucastpktsArr", ifoutucastpktsArr);
		return map;
	}
	
	/**
	 * 接口信息列表（查询1分钟数据）
	 * @param serverInfoId
	 * @return
	 */
	public List<Map<String, Object>> queryData(String serverInfoId) {
//		String sql = "SELECT * FROM netio_stat_minute WHERE server_info_id = ? create_date >= NOW()-INTERVAL 1 MINUTE";
		String sql = "SELECT * FROM netio_stat_minute WHERE server_info_id = ? AND create_date >= '2018-02-07 10:00:00'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { serverInfoId });
		return list;
	}

	/**
	 * 设备信息
	 */
	@Override
	public Map<String, Object> querySNMPInfo(String serverInfoId) {
		return jdbcTemplate.queryForMap("SELECT * FROM snmp_info WHERE server_info_id = ?", new Object[] { serverInfoId });
	}
	
	/**
	 * 数据包使用情况
	 * @param serverInfoId
	 * @return
	 */
	public Map<String, Object> queryPackageData(String serverInfoId) {
		String sql = "SELECT netioname,ifinucastpkts,stat_begin_tm FROM netio_stat_minute WHERE server_info_id = ?";
		
//		String sql = "SELECT netioname,ifinucastpkts,stat_begin_tm FROM netio_stat_minute WHERE server_info_id = ? AND stat_begin_tm >= NOW() - INTERVAL 30 MINUTE;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { serverInfoId });
		Map<String, Double> map = new HashMap<>();
		int i = 0;
		for (Map<String, Object> data : list) {
			double ifinucastpkts = Double.parseDouble(data.get("ifinucastpkts").toString());
			String netioname = data.get("netioname").toString();
			if(!map.containsKey(netioname)) {
				map.put(netioname, ifinucastpkts);
			} else {
				map.put(netioname, map.get(netioname) + ifinucastpkts);
			}
			i ++;
		}
		System.out.println(map);
		return null;
	}
	
	/**
	 * CPU使用率
	 * @param serverInfoId
	 */
	public Map<String, Object> queryCPUData(String serverInfoId) {
		String sql = "SELECT user,system,create_date FROM cpu_stat_minute WHERE server_info_id = ? LIMIT 5;";
		
//		String sql = "SELECT user,system,stat_begin_tm FROM cpu_stat_minute WHERE server_info_id = ? AND stat_begin_tm >= NOW() - INTERVAL 30 MINUTE;";
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { serverInfoId });
		if(list == null || list.size() == 0) return null;
		String[] dateArr = new String[ list.size() ];
		double[] systemArr = new double[ list.size() ];
		int i = 0;
		for (Map<String, Object> data : list) {
			String createDate = String.valueOf(data.get("create_date"));
            double system = Double.parseDouble(data.get("system").toString());
            dateArr[i] = createDate.substring(14, 19);
            systemArr[i] = system;
            i ++;
		}
		map.put("dateArr", dateArr);
        map.put("systemArr", systemArr);
        return map;
	}
	
	/**
	 * 内存使用率
	 * @param serverInfoId
	 */
	public Map<String, Object> queryMemData(String serverInfoId) {
		String sql = "SELECT userage_rate,create_date FROM mem_stat_minute WHERE server_info_id = ? LIMIT 5;";
		
//		String sql = "SELECT userage_rate,stat_begin_tm FROM mem_stat_minute WHERE server_info_id = ? AND stat_begin_tm >= NOW() - INTERVAL 30 MINUTE;";
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { serverInfoId });
		if(list == null || list.size() == 0) return null;
		String[] dateArr = new String[ list.size() ];
		double[] userageRateArr = new double[ list.size() ];
		int i = 0;
		for (Map<String, Object> data : list) {
            String createDate = String.valueOf(data.get("create_date"));
            double userage_rate = Double.parseDouble(data.get("userage_rate").toString());
            dateArr[i] = createDate.substring(14, 19);
            userageRateArr[i] = userage_rate;
            i ++;
		}
		map.put("dateArr", dateArr);
        map.put("userageRateArr", userageRateArr);
        return map;
	}

}
