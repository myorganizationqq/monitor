package com.bluedon.monitor.project.service.netio.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.monitor.common.dao.IBaseDao;
import com.bluedon.monitor.project.entity.communication.CmLogFtpDT;
import com.bluedon.monitor.project.service.netio.NetioService;
import com.bluedon.monitor.system.service.BaseServiceImpl;

@Service
public class NetioServiceImpl extends BaseServiceImpl implements NetioService {
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<CmLogFtpDT> hibernateDao;

	@SuppressWarnings("unchecked")
	public Map<String, Object> queryDataByTime(String time) {
		// 最近2小时（2H）、最近24小时（24H）、最近7天（7D）、最近30天（30D）
		String sql = "SELECT * FROM netio_stat_minute WHERE create_date >= NOW() - INTERVAL 7 DAY LIMIT 7;";
		List<Map<String, Object>> list = hibernateDao.selectBySql(sql);
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
	

}
