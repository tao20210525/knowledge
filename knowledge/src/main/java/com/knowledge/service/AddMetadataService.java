package com.knowledge.service;

import com.knowledge.body.AddMetadataReq;
import com.knowledge.domain.Response;

public abstract interface AddMetadataService {
	
	/**
	 * 添加元数据信息
	 * 
	 */
	Response saveMetadata(AddMetadataReq request);
	
}
