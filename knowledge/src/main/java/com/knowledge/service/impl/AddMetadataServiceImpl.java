package com.knowledge.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.AddMetadataReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.ElementData;
import com.knowledge.repo.AddMetadataRepo;
import com.knowledge.service.AddMetadataService;

@Service
public class AddMetadataServiceImpl  implements AddMetadataService{
 

	@Autowired
	 private AddMetadataRepo addMetadataRepo;
	
	
	@Transactional
	@Override
	public Response saveMetadata(AddMetadataReq req) {
		
		ElementData elementData = new ElementData();
	
		//类别
		elementData.setCategory(req.getCategory());
		//主题域名称
		elementData.setSubjectName(req.getSubjectName());
		//字段中文名
		elementData.setName(req.getName());
		//字段名称
		elementData.setFieldName(req.getFieldName());
		//数据类型
		elementData.setDataType(req.getDataType());
		//输入类型
		elementData.setInputType(req.getInputType());
		//创建人
		elementData.setCreateBy("admin");
		//创建时间
		elementData.setCreatedTime(new Date());
		
		addMetadataRepo.save(elementData);
		
		return Response.ok("00","新增元数据成功");
	}
	

}
