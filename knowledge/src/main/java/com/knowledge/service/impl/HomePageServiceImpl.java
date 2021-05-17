package com.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.MetadataManagementReq;
import com.knowledge.body.vo.MetadataManagementVo;
import com.knowledge.dao.MetadataManagementDao;
import com.knowledge.service.HomePageService;

@Service
public class HomePageServiceImpl  implements HomePageService{

	@Autowired
	 private MetadataManagementDao metadataManagementDao;
	
	/**
	 * 首页-元数据管理
	 * 查询元数据/元数据组数据 
	 * 页面列表信息
	 * @return
	 */
	@Override
	public List<MetadataManagementVo> queryData(MetadataManagementReq req) {
		
		List<MetadataManagementVo> list = metadataManagementDao.queryData(req);
		if(null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}

	
	/**
	 * 首页-元数据管理
	 * 点击上方主题域信息，出现主题域对应页面列表信息
	 * @param req
	 * @return
	 */
	@Override
	public List<MetadataManagementVo> queryInfo(MetadataManagementReq req) {
		
		List<MetadataManagementVo> list = metadataManagementDao.queryInfo(req);
		if(null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}
 


}
