package com.knowledge.service;

import com.knowledge.body.ElementDataReq;
import com.knowledge.body.MetadataFieldReq;
import com.knowledge.domain.Response;

public abstract interface AddMetadataService {
	
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
