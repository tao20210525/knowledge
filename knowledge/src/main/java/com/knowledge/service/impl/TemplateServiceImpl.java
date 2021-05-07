package com.knowledge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.knowledge.body.QueryTemplateReq;
import com.knowledge.body.SaveTemplateReq;
import com.knowledge.body.vo.TemplateElementGroupVo;
import com.knowledge.body.vo.TemplateElementVo;
import com.knowledge.body.vo.TemplateSubjectVo;
import com.knowledge.dao.TemplateDao;
import com.knowledge.domain.Response;
import com.knowledge.entity.Template;
import com.knowledge.entity.TemplateElement;
import com.knowledge.entity.TemplateRelationship;
import com.knowledge.repo.TemplateElementRepoNew;
import com.knowledge.repo.TemplateRelationshipRepo;
import com.knowledge.repo.TemplateRepo;
import com.knowledge.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	private Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	@Autowired
	private TemplateDao templateDao;
	
	@Autowired
	private TemplateRepo templateRepo;
	
	@Autowired
	private TemplateRelationshipRepo templateRelationshipRepo; 
	
	@Autowired
	private TemplateElementRepoNew TemplateElementRepoNew;
	
	@Override
	public Response queryTemplate(QueryTemplateReq req) {
		logger.info("查询模板信息Service开始");
		Response response = Response.ok("00", "查询成功");
		try {
			List<TemplateSubjectVo> returnList = new ArrayList<TemplateSubjectVo>();	//接口返回集合
			Map<String, TemplateSubjectVo> subjectMap = new HashMap<String, TemplateSubjectVo>();		//主题域集合
			Map<String, TemplateElementGroupVo> templateElementGroupMap = new HashMap<String, TemplateElementGroupVo>();	//模板元数据组集合
			Map<String, Object> templateInfo = templateDao.getTemplate(req.getTypeCode(), req.getLevel());
			if(null != templateInfo){
				Long templateId = (Long) templateInfo.get("id");
				List<Map<String, Object>> templateElementList = templateDao.getTemplateElement(templateId);
				if(null != templateElementList){
					for(Map<String, Object> templateElementInfo : templateElementList){
						String templateRelationId = templateElementInfo.get("template_relation_id").toString();
						TemplateSubjectVo templateSubjectVo = null;
						if(subjectMap.containsKey(templateRelationId)){
							templateSubjectVo = subjectMap.get(templateRelationId);
						}else{
							templateSubjectVo = new TemplateSubjectVo();
							templateSubjectVo.setName(templateElementInfo.get("subject_name").toString());
							Long typeId = null==templateElementInfo.get("subject_type_id") ? null : Long.valueOf(templateElementInfo.get("subject_type_id").toString());
							templateSubjectVo.setTypeId(typeId);
							templateSubjectVo.setSort(templateElementInfo.get("subject_sort").toString());
							templateSubjectVo.setChildList(new ArrayList<Object>());
							subjectMap.put(templateRelationId, templateSubjectVo);
						}
						
						String level = templateElementInfo.get("level").toString();	//元素所在层级 1：一级 2：二级
						if("1".equals(level)){
							String elementType = templateElementInfo.get("element_type").toString(); //元素类型：1.元数据 2.元数据组
							if("1".equals(elementType)){
								TemplateElementVo templateElementVo = new TemplateElementVo();
								templateElementVo.setType("1");
								templateElementVo.setFieldName(templateElementInfo.get("fieldname").toString());
								templateElementVo.setElementId(Long.valueOf(templateElementInfo.get("element_id").toString()));
								templateElementVo.setIsNotNull(templateElementInfo.get("isnotnull").toString());
								templateElementVo.setIsCanAdd(templateElementInfo.get("iscanadd").toString());
								templateElementVo.setSort(templateElementInfo.get("sort").toString());
								templateSubjectVo.getChildList().add(templateElementVo);
							}else if("2".equals(elementType)){
								TemplateElementGroupVo templateElementGroupVo = new TemplateElementGroupVo();
								templateElementGroupVo.setType("2");
								templateElementGroupVo.setMetadataName(templateElementInfo.get("metadata_name").toString());
								templateElementGroupVo.setMetadataId(Long.valueOf(templateElementInfo.get("metadata_id").toString()));
								templateElementGroupVo.setElementList(new ArrayList<TemplateElementVo>());
								templateElementGroupVo.setSort(templateElementInfo.get("sort").toString());
								
								String templateElementGroupId = templateElementInfo.get("id").toString();
								templateElementGroupMap.put(templateElementGroupId, templateElementGroupVo);
								
								templateSubjectVo.getChildList().add(templateElementGroupVo);
							}
						}else if("2".equals(level)){
							TemplateElementVo templateElementVo = new TemplateElementVo();
							templateElementVo.setType("1");
							templateElementVo.setFieldName(templateElementInfo.get("fieldname").toString());
							templateElementVo.setElementId(Long.valueOf(templateElementInfo.get("element_id").toString()));
							templateElementVo.setIsNotNull(templateElementInfo.get("isnotnull").toString());
							templateElementVo.setIsCanAdd(templateElementInfo.get("iscanadd").toString());
							templateElementVo.setSort(templateElementInfo.get("sort").toString());
							
							String parentId = templateElementInfo.get("parent_id").toString();
							TemplateElementGroupVo templateElementGroupVo = templateElementGroupMap.get(parentId);
							templateElementGroupVo.getElementList().add(templateElementVo);
						}
					}
					returnList.addAll(subjectMap.values());
					response.setContent(returnList);
				}
			}
		} catch (Exception e) {
			this.logger.info("查询模板信息Service异常："+ e);
			response.setCode("99");
			response.setMessage("查询模板信息异常");
		}
		return response;
	}
	
	@Transactional
	@Override
	public Response saveTemplate(SaveTemplateReq req) {
		logger.info("保存模板信息Service开始");
		Response response = Response.ok("00", "查询成功");
		try {
			
			/*保存模板基本信息*/
			Template template = new Template();
			template.setTypeCode(req.getTypeCode());
			template.setLevel(req.getLevel());
			template.setCreateBy("system");
			template.setCreatedTime(new Date());
			template.setUpdateBy("system");
			template.setUpdateTime(new Date());
			template.setIsDelete("0");
			templateRepo.save(template);
			
			/*遍历模板主题域集合*/
			for(TemplateSubjectVo templateSubjectVo : req.getTemplateSubjectList()){
				
				/*新增模板主题域*/
				TemplateRelationship templateRelationship = new TemplateRelationship();
				templateRelationship.setTemplateId(template.getId());
				templateRelationship.setType("3");
				templateRelationship.setTypeId(templateSubjectVo.getTypeId());
				templateRelationship.setCreateBy("system");
				templateRelationship.setCreatedTime(new Date());
				templateRelationship.setUpdateBy("system");
				templateRelationship.setUpdateTime(new Date());
				templateRelationship.setIsDelete("0");
				templateRelationship.setSubjectName(templateSubjectVo.getName());
				templateRelationship.setSort(templateSubjectVo.getSort());
				templateRelationshipRepo.save(templateRelationship);
				
				/*遍历模板主题域的子集合*/
				for(Object obj : templateSubjectVo.getChildList()){
					LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) obj;
					String type = map.get("type").toString();
					if("1".equals(type)){
						/*元素类型 1:元数据  则直接保存模板元数据信息*/
						TemplateElementVo templateElementVo = JSON.parseObject(JSON.toJSONString(obj), TemplateElementVo.class);
						TemplateElement templateElement = new TemplateElement();
						templateElement.setTemplateId(template.getId());
						templateElement.setTemplateRelationId(templateRelationship.getId());
						templateElement.setParentId(templateRelationship.getId());
						templateElement.setLevel("1");
						templateElement.setElementType("1");
						templateElement.setElementId(templateElementVo.getElementId());
						templateElement.setFieldName(templateElementVo.getFieldName());
						templateElement.setIsNotNull(templateElementVo.getIsNotNull());
						templateElement.setIsCanAdd(templateElementVo.getIsCanAdd());
						templateElement.setSort(templateElementVo.getSort());
						templateElement.setCreateBy("system");
						templateElement.setCreatedTime(new Date());
						templateElement.setUpdateBy("system");
						templateElement.setUpdateTime(new Date());
						templateElement.setIsDelete("0");
						TemplateElementRepoNew.save(templateElement);
					}else if("2".equals(type)){
						/*元素类型 2:元数据组  则先保存模板元数据组信息, 然后遍历模板元数据组里的元数据保存信息*/
						
						/*保存摸吧元数据组信息*/
						TemplateElementGroupVo templateElementGroupVo = JSON.parseObject(JSON.toJSONString(obj), TemplateElementGroupVo.class);
						TemplateElement templateElementGroup = new TemplateElement();
						templateElementGroup.setTemplateId(template.getId());
						templateElementGroup.setTemplateRelationId(templateRelationship.getId());
						templateElementGroup.setParentId(templateRelationship.getId());
						templateElementGroup.setLevel("1");
						templateElementGroup.setElementType("2");
						templateElementGroup.setSort(templateElementGroupVo.getSort());
						templateElementGroup.setMetadataId(templateElementGroupVo.getMetadataId());
						templateElementGroup.setMetadataName(templateElementGroupVo.getMetadataName());
						templateElementGroup.setCreateBy("system");
						templateElementGroup.setCreatedTime(new Date());
						templateElementGroup.setUpdateBy("system");
						templateElementGroup.setUpdateTime(new Date());
						templateElementGroup.setIsDelete("0");
						TemplateElementRepoNew.save(templateElementGroup);
						
						/*遍历保存模板元数据信息*/
						for(TemplateElementVo templateElementVo : templateElementGroupVo.getElementList()){
							TemplateElement templateElement = new TemplateElement();
							templateElement.setTemplateId(template.getId());
							templateElement.setTemplateRelationId(templateRelationship.getId());
							templateElement.setParentId(templateElementGroup.getId());
							templateElement.setLevel("2");
							templateElement.setElementType("1");
							templateElement.setElementId(templateElementVo.getElementId());
							templateElement.setFieldName(templateElementVo.getFieldName());
							templateElement.setIsNotNull(templateElementVo.getIsNotNull());
							templateElement.setIsCanAdd(templateElementVo.getIsCanAdd());
							templateElement.setMetadataId(templateElementGroupVo.getMetadataId());
							templateElement.setMetadataName(templateElementGroupVo.getMetadataName());
							templateElement.setCreateBy("system");
							templateElement.setCreatedTime(new Date());
							templateElement.setUpdateBy("system");
							templateElement.setUpdateTime(new Date());
							templateElement.setIsDelete("0");
							TemplateElementRepoNew.save(templateElement);
						}
						
					}
				}
				
			}
		} catch (Exception e) {
			this.logger.info("保存模板信息Service异常："+ e);
			response.setCode("99");
			response.setMessage("保存模板信息异常");
		}
		return response;
	}

}
