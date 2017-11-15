package com.bluedon.bfw.system.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bluedon.bfw.common.dao.IBaseDao;
import com.bluedon.bfw.common.util.StringUtil;
import com.bluedon.bfw.system.entity.TbCommonUserOrg;
import com.bluedon.bfw.system.service.system.IUserOrgManagerService;

/** 
 * @author :yangdandan 
 * @date：2015年5月12日
 * @module:系统模块 
 * @comment:用户组织机构关系逻辑处理实现类
 */
@Service("userOrgManagerServiceImpl")
public class UserOrgManagerServiceImpl  implements IUserOrgManagerService{

	//日志记录对象
	private static final Logger log = Logger.getLogger(UserOrgManagerServiceImpl.class);
	
	@Autowired
	@Qualifier("hibernateDao")
	private IBaseDao<TbCommonUserOrg> hibernateDao;

	//获取指定用户和组织的用户组织关系
	@Override
	public TbCommonUserOrg getUserOrg(Map<String,Object> params) {		
		// TODO Auto-generated method stub
		
		List<Criterion> paramsList = new ArrayList<Criterion>();
		if (params.get("userId")!=null && !params.get("userId").equals("")) {
			paramsList.add(Restrictions.eq("userId", params.get("userId")));
		}
		
		if (params.get("orgId")!=null && !params.get("orgId").equals("")) {
			paramsList.add(Restrictions.eq("orgId", params.get("orgId")));
		}
		
		TbCommonUserOrg ur=(TbCommonUserOrg)this.hibernateDao.queryForObject(TbCommonUserOrg.class, paramsList);

		return ur;
	}
}
