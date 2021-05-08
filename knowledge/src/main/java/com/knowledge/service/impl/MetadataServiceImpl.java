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
import com.knowledge.body.vo.MetadataFieldVo;
import com.knowledge.body.vo.MetadataVo;
import com.knowledge.domain.Response;
import com.knowledge.entity.ElementData;
import com.knowledge.entity.ElementEnumeration;
import com.knowledge.entity.ElementField;
import com.knowledge.entity.MetadataField;
import com.knowledge.entity.SubjectRelation;
import com.knowledge.repo.ElementEnumerationRepo;
import com.knowledge.repo.ElementFieldRepo;
import com.knowledge.repo.MetadataFieldRepo;
import com.knowledge.repo.MetadataRepo;
import com.knowledge.repo.SubjectRelationRepo;
import com.knowledge.repo.dao.MetadataDao;
import com.knowledge.service.MetadataService;

@Service
public class MetadataServiceImpl  implements MetadataService{

	@Autowired
	 private MetadataRepo addMetadataRepo;
	
	@Autowired
	 private ElementEnumerationRepo elementEnumerationRepo;
	
	@Autowired
	 private MetadataFieldRepo metadataFieldRepo;
	
	@Autowired
	 private ElementFieldRepo elementFieldRepo;
	
	@Autowired
	 private SubjectRelationRepo subjectRelationRepo;
	
	@Autowired
	 private MetadataDao metadataDao;
	
	
	/**
	 * 查询元数据
	 * 
	 * @param category 类别
	 * @param name 模糊查询
	 * @return
	 */
	@Override
	public List<MetadataVo> queryMetadata(String category, String name) {
		
		List<MetadataVo> list = metadataDao.getMetadata(category,name);
		if(null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}
	
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
				//更新人
				subject.setUpdateBy("admin");
				//更新时间
				subject.setUpdateTime(new Date());
				//是否逻辑删除 0否 1是
				subject.setIsDelete("0");
				
				subjectRelationRepo.save(subject);
			}
		return Response.ok("00","新增/修改元数据成功");
	}
	
	
	
	/**
	 * 查询元数据组(下拉框)-新增主题域页面
	 * 
	 * @param category 类别
	 * @param name 模糊查询-元数据组名称
	 * @return
	 */
	@Override
	public List<MetadataFieldVo> queryMetadataField(String category, String name) {
		
		List<MetadataFieldVo> list = metadataDao.getMetadataField(category,name);
		if(null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}
	
	
	
	/**
	 * 新增元数据组
	 */
	@Transactional
	@Override
	public Response saveMetadataField(MetadataFieldReq req) {
		
		MetadataField metadataField = new MetadataField();
		
		 if(null != req.getElementId()) {  //修改
			 metadataField = metadataFieldRepo.getOne(req.getElementId());
         }
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
			//关系表存入信息
			ElementField field = new ElementField();
			
			if(null != req.getElementId()) {
				//编辑元数据与元数据组关系表
				field = elementFieldRepo.getOne(req.getElementId());
			}
			
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
			
			// 元数据/主题域关系表
			SubjectRelation subject= new SubjectRelation();
			
			if(null != req.getElementId()) {
				//编辑元数据/主题域关系表
				subject = subjectRelationRepo.getOne(req.getElementId());
			}
			
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
		return Response.ok("00","新增/修改元数据组成功");
	}

}
