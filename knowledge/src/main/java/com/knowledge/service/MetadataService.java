package com.knowledge.service;

import java.util.List;

import com.knowledge.body.ElementDataReq;
import com.knowledge.body.MetadataFieldReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.ElementData;

public abstract interface MetadataService {
	
	/**
	 * 查询元数据信息
	 * 
	 */
	List<ElementData> queryMetadata();
	
	/**
	 * 添加元数据信息
	 * 
	 */
	Response saveMetadata(ElementDataReq request);
	
	
	/**
	 * 添加元数据组信息
	 * 
	 */
	Response saveMetadataField(MetadataFieldReq request);
	
}
