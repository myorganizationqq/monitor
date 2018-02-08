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
		String sql = "SELECT * FROM netio_stat_minute WHERE create_date >= NOW() - INTERVAL 7 DAY LIMIT 7;";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		Map<String, Object> map = new HashMap<>();
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
            ifinoctetsArr[i] = ifinoctets;
            ifoutoctetsArr[i] = ifoutoctets;
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
	 * 查询1分钟数据
	 * @param serverInfoId
	 * @return
	 */
	public List<Map<String, Object>> queryData(String serverInfoId) {
//		String sql = "SELECT * FROM netio_stat_minute WHERE server_info_id = ? create_date >= NOW()-INTERVAL 1 MINUTE";
		String sql = "SELECT * FROM netio_stat_minute WHERE server_info_id = ? AND create_date >= '2018-02-07 10:00:00'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { serverInfoId });
		return list;
	}

	@Override
	public Map<String, Object> querySNMPInfo(String serverInfoId) {
		return jdbcTemplate.queryForMap("SELECT * FROM snmp_info WHERE server_info_id = ?", new Object[] { serverInfoId });
	}

}
