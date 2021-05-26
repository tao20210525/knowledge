package com.knowledge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.knowledge.body.QueryTemplateCompositionReq;
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
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			List<TemplateSubjectVo> templateSubjectList = new ArrayList<TemplateSubjectVo>();	//接口返回集合
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
							//templateSubjectVo.setSort(templateElementInfo.get("subject_sort").toString());
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
								templateElementVo.setFieldCode(templateElementInfo.get("fieldCode").toString());
								templateElementVo.setElementId(Long.valueOf(templateElementInfo.get("element_id").toString()));
								templateElementVo.setIsNotNull(templateElementInfo.get("isnotnull").toString());
								templateElementVo.setIsCanAdd(templateElementInfo.get("iscanadd").toString());
								//templateElementVo.setSort(templateElementInfo.get("sort").toString());
								templateSubjectVo.getChildList().add(templateElementVo);
							}else if("2".equals(elementType)){
								TemplateElementGroupVo templateElementGroupVo = new TemplateElementGroupVo();
								templateElementGroupVo.setType("2");
								templateElementGroupVo.setMetadataName(templateElementInfo.get("metadata_name").toString());
								templateElementGroupVo.setMetadataId(Long.valueOf(templateElementInfo.get("metadata_id").toString()));
								templateElementGroupVo.setElementList(new ArrayList<TemplateElementVo>());
								//templateElementGroupVo.setSort(templateElementInfo.get("sort").toString());
								
								String templateElementGroupId = templateElementInfo.get("id").toString();
								templateElementGroupMap.put(templateElementGroupId, templateElementGroupVo);
								
								templateSubjectVo.getChildList().add(templateElementGroupVo);
							}
						}else if("2".equals(level)){
							TemplateElementVo templateElementVo = new TemplateElementVo();
							templateElementVo.setType("1");
							templateElementVo.setFieldName(templateElementInfo.get("fieldname").toString());
							templateElementVo.setFieldCode(templateElementInfo.get("fieldCode").toString());
							templateElementVo.setElementId(Long.valueOf(templateElementInfo.get("element_id").toString()));
							templateElementVo.setIsNotNull(templateElementInfo.get("isnotnull").toString());
							templateElementVo.setIsCanAdd(templateElementInfo.get("iscanadd").toString());
							//templateElementVo.setSort(templateElementInfo.get("sort").toString());
							
							String parentId = templateElementInfo.get("parent_id").toString();
							TemplateElementGroupVo templateElementGroupVo = templateElementGroupMap.get(parentId);
							templateElementGroupVo.getElementList().add(templateElementVo);
						}
					}
					templateSubjectList.addAll(subjectMap.values());
				}
				resultMap.put("templateId", templateId);
				resultMap.put("templateSubjectList", templateSubjectList);
				response.setContent(resultMap);
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
			
			String reqType = StringUtils.isBlank(req.getReqType()) ? "1" : req.getReqType();		//接口请求处理类型 1:新增 2:更新
			Long templateId = 0l;
			
			if("1".equals(reqType)){
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
				templateId = template.getId();
			}else if("2".equals(reqType)){
				templateId = req.getTemplateId();
				/*这里删除模板主题域表 和 模板元素表*/
				templateDao.delTemplateRelationship(templateId);
				templateDao.delTemplateElement(templateId);
			}
			
			/*遍历模板主题域集合*/
			for(TemplateSubjectVo templateSubjectVo : req.getTemplateSubjectList()){
				
				/*新增模板主题域*/
				TemplateRelationship templateRelationship = new TemplateRelationship();
				templateRelationship.setTemplateId(templateId);
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
						templateElement.setTemplateId(templateId);
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
						templateElementGroup.setTemplateId(templateId);
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
							templateElement.setTemplateId(templateId);
							templateElement.setTemplateRelationId(templateRelationship.getId());
							templateElement.setParentId(templateElementGroup.getId());
							templateElement.setLevel("2");
							templateElement.setElementType("1");
							templateElement.setElementId(templateElementVo.getElementId());
							templateElement.setFieldName(templateElementVo.getFieldName());
							templateElement.setIsNotNull(templateElementVo.getIsNotNull());
							templateElement.setIsCanAdd(templateElementVo.getIsCanAdd());
							templateElement.setSort(templateElementVo.getSort());
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
	
	@Override
	public Response queryTemplateField(QueryTemplateReq req) {
		logger.info("查询模板字段Service开始");
		Response response = Response.ok("00", "查询成功");
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			List<TemplateSubjectVo> templateSubjectList = new ArrayList<TemplateSubjectVo>();	//接口返回集合
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
								templateElementVo.setInputType(templateElementInfo.get("input_type").toString());
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
							templateElementVo.setInputType(templateElementInfo.get("input_type").toString());
							
							String parentId = templateElementInfo.get("parent_id").toString();
							TemplateElementGroupVo templateElementGroupVo = templateElementGroupMap.get(parentId);
							templateElementGroupVo.getElementList().add(templateElementVo);
						}
					}
					int i = 1;
					for(TemplateSubjectVo templateSubjectVo : subjectMap.values()){
						i = 1;	//每次循环完重置
						List<Object> templateElenentList = new ArrayList<Object>();
						for(Object obj : templateSubjectVo.getChildList()){
							if(obj instanceof TemplateElementVo){
								TemplateElementVo templateElementVo = (TemplateElementVo) obj;
								templateElementVo.setSort(i+"");
								templateElenentList.add(templateElementVo);
								i++;
							}else if(obj instanceof TemplateElementGroupVo){
								TemplateElementGroupVo templateElementGroupVo = (TemplateElementGroupVo) obj;
								/*遍历保存模板元数据信息*/
								for(TemplateElementVo templateElementVo : templateElementGroupVo.getElementList()){
									templateElementVo.setSort(i+"");
									templateElenentList.add(templateElementVo);
									i++;
								}
							}
						}
						templateSubjectVo.setChildList(templateElenentList);
					}
					
					templateSubjectList.addAll(subjectMap.values());
				}
				resultMap.put("templateId", templateId);
				resultMap.put("templateSubjectList", templateSubjectList);
				response.setContent(resultMap);
			}
		} catch (Exception e) {
			this.logger.info("查询模板字段Service异常："+ e);
			response.setCode("99");
			response.setMessage("查询模板字段异常");
		}
		return response;
	}

	@Override
	public Response queryTemplateComposition(QueryTemplateCompositionReq req) {
		logger.info("查询可组成模板的元素列表Service开始");
		Response response = Response.ok("00", "查询成功");
		try {
			List<Object> resultList = new ArrayList<Object>();
			String elementType = StringUtils.isBlank(req.getElementType()) ? "1" : req.getElementType();		//元素类型 1:元数据 2:元数据组 3:主题域
			if("1".equals(elementType)){
				List<Map<String, Object>> elementList = templateDao.getElementByTypeCode(req.getTypeCode(), req.getKeyword());
				if(null != elementList){
					for(Map<String, Object> elementInfo : elementList){
						TemplateElementVo templateElementVo = new TemplateElementVo();
						templateElementVo.setType("1");
						templateElementVo.setFieldName(elementInfo.get("name").toString());
						templateElementVo.setElementId(Long.valueOf(elementInfo.get("id").toString()));
						resultList.add(templateElementVo);
					}
				}
			}else if("2".equals(elementType)){
				List<Map<String, Object>> elementList = templateDao.getElementGroupByTypeCode(req.getTypeCode(), req.getKeyword());
				if(null != elementList){
					Map<String, TemplateElementGroupVo> elementGroupMap = new HashMap<String, TemplateElementGroupVo>();	//元数据组集合
					for(Map<String, Object> elementInfo : elementList){
						String metadataId = elementInfo.get("metadata_id").toString();
						TemplateElementGroupVo templateElementGroupVo = null;
						if(elementGroupMap.containsKey(metadataId)){
							templateElementGroupVo = elementGroupMap.get(metadataId);
						}else{
							templateElementGroupVo = new TemplateElementGroupVo();
							templateElementGroupVo.setType("2");
							templateElementGroupVo.setMetadataName(elementInfo.get("metadata_name").toString());
							templateElementGroupVo.setMetadataId(Long.valueOf(metadataId));
							templateElementGroupVo.setElementList(new ArrayList<TemplateElementVo>());
							elementGroupMap.put(metadataId, templateElementGroupVo);
						}
						TemplateElementVo templateElementVo = new TemplateElementVo();
						templateElementVo.setType("1");
						templateElementVo.setFieldName(elementInfo.get("element_name").toString());
						templateElementVo.setElementId(Long.valueOf(elementInfo.get("element_id").toString()));
						templateElementGroupVo.getElementList().add(templateElementVo);
					}
					resultList.add(elementGroupMap.values());
				}
			}else if("3".equals(elementType)){
				List<Map<String, Object>> elementList = templateDao.getSubjectByTypeCode(req.getTypeCode(), req.getKeyword());
				if(null != elementList){
					Map<String, TemplateSubjectVo> subjectMap = new HashMap<String, TemplateSubjectVo>();	//元数据组集合
					Map<String, TemplateElementGroupVo> templateElementGroupMap = new HashMap<String, TemplateElementGroupVo>();	//模板元数据组集合
					List<Long> metadataIdList = new ArrayList<Long>();
					for(Map<String, Object> elementInfo : elementList){
						String subjectId = elementInfo.get("subject_id").toString();
						TemplateSubjectVo templateSubjectVo = null;
						if(subjectMap.containsKey(subjectId)){
							templateSubjectVo = subjectMap.get(subjectId);
						}else{
							templateSubjectVo = new TemplateSubjectVo();
							templateSubjectVo.setName(elementInfo.get("subject_name").toString());
							templateSubjectVo.setTypeId(Long.valueOf(subjectId));
							templateSubjectVo.setChildList(new ArrayList<Object>());
							subjectMap.put(subjectId, templateSubjectVo);
						}
						String dataType = elementInfo.get("type").toString();	//区分数据来源：1.元数据 2.元数据组
						if("1".equals(dataType)){
							TemplateElementVo templateElementVo = new TemplateElementVo();
							templateElementVo.setType("1");
							templateElementVo.setFieldName(elementInfo.get("element_name").toString());
							templateElementVo.setElementId(Long.valueOf(elementInfo.get("element_id").toString()));
							templateSubjectVo.getChildList().add(templateElementVo);
						}else{
							Long metadataId = Long.valueOf(elementInfo.get("element_id").toString());
							TemplateElementGroupVo templateElementGroupVo = new TemplateElementGroupVo();
							templateElementGroupVo.setType("2");
							templateElementGroupVo.setMetadataId(metadataId);
							templateElementGroupVo.setElementList(new ArrayList<TemplateElementVo>());
							
							templateElementGroupMap.put(metadataId+"", templateElementGroupVo);
							metadataIdList.add(metadataId);
							templateSubjectVo.getChildList().add(templateElementGroupVo);
						}
						
					}
					
					/*根据元数据组ID集合查询元数据列表*/
					List<Map<String, Object>> elementList2 = templateDao.getElementByMetadataIdList(metadataIdList);
					if(null != elementList2){
						for(Map<String, Object> elementInfo : elementList2){
							String metadataId = elementInfo.get("metadata_id").toString();
							TemplateElementGroupVo templateElementGroupVo = templateElementGroupMap.get(metadataId);
							if(null != templateElementGroupVo){
								if(StringUtils.isBlank(templateElementGroupVo.getMetadataName())){
									templateElementGroupVo.setMetadataName(elementInfo.get("metadata_name").toString());
								}
								TemplateElementVo templateElementVo = new TemplateElementVo();
								templateElementVo.setType("1");
								templateElementVo.setFieldName(elementInfo.get("element_name").toString());
								templateElementVo.setElementId(Long.valueOf(elementInfo.get("element_id").toString()));
								templateElementGroupVo.getElementList().add(templateElementVo);
							}
						}
					}
					
					resultList.add(subjectMap.values());
				}
			}
			
			response.setContent(resultList);
		} catch (Exception e) {
			this.logger.info("查询可组成模板的元素列表Service异常："+ e);
			response.setCode("99");
			response.setMessage("查询可组成模板的元素列表异常");
		}
		return response;
	}

}
