package com.knowledge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.ElementDataReq;
import com.knowledge.body.MetadataFieldReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.ElementData;
import com.knowledge.entity.ElementEnumeration;
import com.knowledge.entity.ElementField;
import com.knowledge.entity.MetadataField;
import com.knowledge.entity.SubjectRelation;
import com.knowledge.repo.AddMetadataRepo;
import com.knowledge.repo.ElementEnumerationRepo;
import com.knowledge.repo.ElementFieldRepo;
import com.knowledge.repo.MetadataFieldRepo;
import com.knowledge.repo.SubjectRelationRepo;
import com.knowledge.service.AddMetadataService;

@Service
public class AddMetadataServiceImpl  implements AddMetadataService{
 

	@Autowired
	 private AddMetadataRepo addMetadataRepo;
	
	@Autowired
	 private ElementEnumerationRepo elementEnumerationRepo;
	
	@Autowired
	 private MetadataFieldRepo metadataFieldRepo;
	
	@Autowired
	 private ElementFieldRepo elementFieldRepo;
	
	@Autowired
	 private SubjectRelationRepo subjectRelationRepo;
	
	
	/**
	 * 新增元数据
	 */
	@Transactional
	@Override
	public Response saveMetadata(ElementDataReq req) {
		
		ElementData elementData = new ElementData();
		
		
          if(null != req.getId()) {  //修改
        	  elementData = addMetadataRepo.getOne(req.getId());
          }
            //存储元数据
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
			//更新人
			elementData.setUpdateBy("admin");
			//更新时间
			elementData.setUpdateTime(new Date());
			//是否逻辑删除 0否 1是
			elementData.setIsDelete("0");
			
			addMetadataRepo.save(elementData);
			
			if(null != elementData) {
				
				//编辑情况下，页面点保存后删除枚举数据，重新保存
				if(null != req.getId()) {
					//这里根据元数据id外键删除枚举
					elementEnumerationRepo.deleteByElementId(elementData.getId());
				}
				
				//枚举表存入信息
				List<ElementEnumeration> elementList = new ArrayList<ElementEnumeration>();
				
				for(Map<String, String> enumMap : req.getEnumList()) {
					ElementEnumeration element = new ElementEnumeration();
					String code = enumMap.get("code");
					String value = enumMap.get("value");
					//元数据ID
					element.setElementId(elementData.getId()); 
					//枚举值code
					element.setCode(code);
					//枚举值value
					element.setValue(value);
					//创建人
					element.setCreateBy("admin");
					//创建时间
					element.setCreatedTime(new Date());
					//更新人
					element.setUpdateBy("admin");
					//更新时间
					element.setUpdateTime(new Date());
					//是否逻辑删除 0否 1是
					element.setIsDelete("0");
					
					elementList.add(element);
				}
				elementEnumerationRepo.saveAll(elementList);
				
				
				//元数据/主题域关系表
				SubjectRelation subject= new SubjectRelation();
				if(null != req.getId()) {
					//编辑元数据/主题域关系表
					subject = subjectRelationRepo.getOne(req.getId());
				}
			
				//主题域ID
				subject.setSubjectId(req.getSubjectId());
				 //元数据ID
				subject.setElementId(elementData.getId()); 
				//区分数据来源：1.元数据、2.元数据组
				subject.setType("1");
				//创建人
				subject.setCreateBy("admin");
				//创建时间
				subject.setCreatedTime(new Date());
				
				subjectRelationRepo.save(subject);
				
			}
		return Response.ok("00","新增元数据成功");
	}
	
	
	/**
	 * 新增元数据组
	 */
	@Transactional
	@Override
	public Response saveMetadataField(MetadataFieldReq req) {
		
		MetadataField metadataField = new MetadataField();
		//类别
		metadataField.setCategory(req.getCategory());
		//元数据组名称
		metadataField.setName(req.getName());
		//创建人
		metadataField.setCreateBy("admin");
		//创建时间
		metadataField.setCreatedTime(new Date());
		
		metadataFieldRepo.save(metadataField);
		
		if(null != metadataField) {
			//TODO 关系表存入信息
			ElementField field = new ElementField();
			//元数据组ID
			field.setMetadataId(metadataField.getId());
			//元数据ID
			field.setElementId(req.getElementId());
			//级别
			field.setLevel(req.getLevel());
			//创建人
			field.setCreateBy("admin");
			//创建时间
			field.setCreatedTime(new Date());
			
			elementFieldRepo.save(field);
			
			//TODO 元数据/主题域关系表
			SubjectRelation subject= new SubjectRelation();
			//主题域ID
			subject.setSubjectId(req.getSubjectId());
			 //元数据ID
			subject.setElementId(req.getElementId()); 
			//区分数据来源：1.元数据、2.元数据组
			subject.setType("2");
			//创建人
			subject.setCreateBy("admin");
			//创建时间
			subject.setCreatedTime(new Date());
			
			subjectRelationRepo.save(subject);
		}
		return Response.ok("00","新增元数据组成功");
	}

}
