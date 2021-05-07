package com.knowledge.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knowledge.dao.TemplateDao;

@Repository
public class TemplateDaoImpl implements TemplateDao {
	
	private Logger logger = LoggerFactory.getLogger(TemplateDaoImpl.class);

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Map<String, Object> getTemplate(String typeCode, String level) {
		Map<String, Object> resultMap = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT id,type_code,LEVEL FROM template WHERE type_code = :typeCode AND LEVEL = :level AND is_delete = '0' ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			query.setParameter("typeCode", typeCode);
			query.setParameter("level", level);
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				Object[] objArr = resultList.get(0);
				resultMap = new HashMap<String, Object>();
				resultMap.put("id", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
				resultMap.put("type_code", null==objArr[1] ? "" : objArr[1].toString());
				resultMap.put("level", null==objArr[2] ? "" : objArr[2].toString());
			}
			
		} catch (Exception e) {
			this.logger.info("查询模板基本信息数据异常："+e.getMessage());
		}
		return resultMap;
	}
	
	@Override
	public List<Map<String, Object>> getTemplateElement(Long templateId) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT e.id,e.template_id,e.template_relation_id,r.subject_name,e.parent_id,e.level,e.element_type,e.element_id,e.fieldName,e.isnotnull,e.iscanadd,e.sort,e.metadata_id,e.metadata_name ");
			sql.append(" FROM template_relationship r, template_element e ");
			sql.append(" WHERE e.template_relation_id = r.id AND r.template_id = :templateId AND r.is_delete = '0' AND e.is_delete = '0' ");
			sql.append(" ORDER BY r.sort,e.level,e.sort ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			query.setParameter("templateId", templateId);
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> templateInfo = new HashMap<String, Object>();
					templateInfo.put("id", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
					templateInfo.put("template_id", null==objArr[1] ? 0L : Long.valueOf(objArr[1].toString()));
					templateInfo.put("template_relation_id", null==objArr[2] ? 0L : Long.valueOf(objArr[2].toString()));
					templateInfo.put("subject_name", null==objArr[3] ? "" : objArr[3].toString());
					templateInfo.put("parent_id", null==objArr[4] ? 0L : Long.valueOf(objArr[4].toString()));
					templateInfo.put("level", null==objArr[5] ? "" : objArr[5].toString());
					templateInfo.put("element_type", null==objArr[6] ? "" : objArr[6].toString());
					templateInfo.put("element_id", null==objArr[7] ? 0L : Long.valueOf(objArr[7].toString()));
					templateInfo.put("fieldname", null==objArr[8] ? "" : objArr[8].toString());
					templateInfo.put("isnotnull", null==objArr[9] ? "" : objArr[9].toString());
					templateInfo.put("iscanadd", null==objArr[10] ? "" : objArr[10].toString());
					templateInfo.put("sort", null==objArr[11] ? "" : objArr[11].toString());
					templateInfo.put("metadata_id", null==objArr[12] ? 0L : Long.valueOf(objArr[12].toString()));
					templateInfo.put("metadata_name", null==objArr[13] ? "" : objArr[13].toString());
					returnList.add(templateInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("查询模板元素集合数据异常："+e.getMessage());
		}
		return returnList;
	}
	
}
