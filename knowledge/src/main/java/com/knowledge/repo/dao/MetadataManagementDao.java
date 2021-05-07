package com.knowledge.repo.dao;

import java.util.List;

import com.knowledge.body.vo.MetadataManagementVo;

public  interface MetadataManagementDao {

	
	/**
	 * 首页-元数据管理
	 * 查询元数据/元数据组数据 
	 * 页面列表信息
	 * @return
	 */
	List<MetadataManagementVo> queryData();
}
