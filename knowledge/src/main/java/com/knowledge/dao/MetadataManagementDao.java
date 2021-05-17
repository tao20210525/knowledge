package com.knowledge.dao;

import java.util.List;

import com.knowledge.body.MetadataManagementReq;
import com.knowledge.body.vo.MetadataManagementVo;

public  interface MetadataManagementDao {

	
	/**
	 * 首页-元数据管理
	 * 查询元数据/元数据组数据 
	 * 页面列表信息
	 * @return
	 */
	List<MetadataManagementVo> queryData(MetadataManagementReq req);
	
	
	/**
	 * 首页-元数据管理
	 * 点击上方主题域信息，出现主题域对应页面列表信息
	 * @param req
	 * @return
	 */
	List<MetadataManagementVo> queryInfo(MetadataManagementReq req);
}
