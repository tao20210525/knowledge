package com.knowledge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.QueryTemplateReq;
import com.knowledge.body.vo.TemplateElementGroupVo;
import com.knowledge.body.vo.TemplateElementVo;
import com.knowledge.body.vo.TemplateSubjectVo;
import com.knowledge.dao.TemplateDao;
import com.knowledge.domain.Response;
import com.knowledge.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	private Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);
	
	@Autowired
	private TemplateDao templateDao;
	
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
							templateSubjectVo.setChildList(new ArrayList<Object>());
							subjectMap.put(templateRelationId, templateSubjectVo);
						}
						
						String level = templateElementInfo.get("level").toString();	//元素所在层级 1：一级 2：二级
						if("1".equals(level)){
							String elementType = templateElementInfo.get("element_type").toString(); //元素类型：1.元数据 2.元数据组
							if("1".equals(elementType)){
								TemplateElementVo templateElementVo = new TemplateElementVo();
								templateElementVo.setFieldName(templateElementInfo.get("fieldname").toString());
								templateElementVo.setElementId(Long.valueOf(templateElementInfo.get("element_id").toString()));
								templateElementVo.setIsNotNull(templateElementInfo.get("isnotnull").toString());
								templateElementVo.setIsCanAdd(templateElementInfo.get("iscanadd").toString());
								templateElementVo.setSort(templateElementInfo.get("sort").toString());
								templateSubjectVo.getChildList().add(templateElementVo);
							}else if("2".equals(elementType)){
								TemplateElementGroupVo templateElementGroupVo = new TemplateElementGroupVo();
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
		}
		
		return response;
	}

}
