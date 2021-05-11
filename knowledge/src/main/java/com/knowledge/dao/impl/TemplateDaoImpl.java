package com.knowledge.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
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
			sql.append(" SELECT e.id,e.template_id,e.template_relation_id,r.subject_name,e.parent_id,e.level,e.element_type,e.element_id,ed.NAME,e.isnotnull,e.iscanadd,e.sort,e.metadata_id,e.metadata_name, ");
			sql.append(" r.type_id,r.sort subject_sort,ed.data_type,ed.input_type ");
			sql.append(" FROM template_relationship r, template_element e ");
			sql.append(" LEFT JOIN element_data ed ON e.ELEMENT_ID = ed.ID ");
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
					templateInfo.put("metadata_id", null==objArr[12] ? null : Long.valueOf(objArr[12].toString()));
					templateInfo.put("metadata_name", null==objArr[13] ? "" : objArr[13].toString());
					templateInfo.put("subject_type_id", null==objArr[14] ? null : Long.valueOf(objArr[14].toString()));
					templateInfo.put("subject_sort", null==objArr[15] ? "" : objArr[15].toString());
					templateInfo.put("data_type", null==objArr[16] ? "" : objArr[16].toString());
					templateInfo.put("input_type", null==objArr[17] ? "" : objArr[17].toString());
					returnList.add(templateInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("查询模板元素集合数据异常："+e.getMessage());
		}
		return returnList;
	}
	
	@Override
	public int delTemplateRelationship(Long templateId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM template_relationship t WHERE t.template_id = :templateId ");
		Query query = this.entityManager.createNativeQuery(sql.toString());
		query.setParameter("templateId", templateId);
		return query.executeUpdate();
	}
	
	@Override
	public int delTemplateElement(Long templateId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" DELETE FROM template_element t WHERE t.template_id = :templateId ");
		Query query = this.entityManager.createNativeQuery(sql.toString());
		query.setParameter("templateId", templateId);
		return query.executeUpdate();
	}

	@Override
	public List<Map<String, Object>> getElementByTypeCode(String typeCode, String keyword) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT id,NAME FROM element_data where 1=1 ");
			if(StringUtils.isNotBlank(typeCode)){
				sql.append(" and CATEGORY = :typeCode ");
			}
			if(StringUtils.isNotBlank(keyword)){
				sql.append(" and name like :keyword ");
			}
			sql.append(" ORDER BY update_time ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("typeCode", typeCode);
			}
			if(StringUtils.isNotBlank(keyword)){
				query.setParameter("keyword", "%"+keyword+"%");
			}
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> elementInfo = new HashMap<String, Object>();
					elementInfo.put("id", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
					elementInfo.put("name", null==objArr[1] ? "" : objArr[1].toString());
					returnList.add(elementInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("根据类别查询元数据列表数据异常："+e.getMessage());
		}
		return returnList;
	}
	
	@Override
	public List<Map<String, Object>> getElementGroupByTypeCode(String typeCode, String keyword) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT m.id metadata_id,m.NAME metadata_name,e.id element_id,e.NAME element_name FROM metadata_field m, element_field ef, element_data e ");
			sql.append(" WHERE m.ID = ef.METADATA_ID AND ef.ELEMENT_ID = e.ID ");
			if(StringUtils.isNotBlank(typeCode)){
				sql.append(" and m.CATEGORY = :typeCode ");
			}
			if(StringUtils.isNotBlank(keyword)){
				sql.append(" and m.name like :keyword ");
			}
			sql.append(" ORDER BY m.update_time,ef.LEVEL ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("typeCode", typeCode);
			}
			if(StringUtils.isNotBlank(keyword)){
				query.setParameter("keyword", "%"+keyword+"%");
			}
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> elementInfo = new HashMap<String, Object>();
					elementInfo.put("metadata_id", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
					elementInfo.put("metadata_name", null==objArr[1] ? "" : objArr[1].toString());
					elementInfo.put("element_id", null==objArr[2] ? 0L : Long.valueOf(objArr[2].toString()));
					elementInfo.put("element_name", null==objArr[3] ? "" : objArr[3].toString());
					returnList.add(elementInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("根据类别查询元数据组列表数据异常："+e.getMessage());
		}
		return returnList;
	}
	
	@Override
	public List<Map<String, Object>> getSubjectByTypeCode(String typeCode, String keyword) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT s.id,s.SUBJECT_NAME,sr.ELEMENT_ID,sr.type,e.name FROM subject_field s,subject_relation sr ");
			sql.append(" LEFT JOIN element_data e ON sr.ELEMENT_ID = e.id AND sr.type = '1' ");
			sql.append(" WHERE s.id = sr.subject_id  ");
			if(StringUtils.isNotBlank(typeCode)){
				sql.append(" and s.CATEGORY = :typeCode ");
			}
			if(StringUtils.isNotBlank(keyword)){
				sql.append(" and s.SUBJECT_NAME like :keyword ");
			}
			sql.append(" ORDER BY s.UPDATE_TIME,sr.sort ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("typeCode", typeCode);
			}
			if(StringUtils.isNotBlank(keyword)){
				query.setParameter("keyword", "%"+keyword+"%");
			}
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> elementInfo = new HashMap<String, Object>();
					elementInfo.put("subject_id", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
					elementInfo.put("subject_name", null==objArr[1] ? "" : objArr[1].toString());
					elementInfo.put("element_id", null==objArr[2] ? 0L : Long.valueOf(objArr[2].toString()));
					elementInfo.put("type", null==objArr[3] ? "" : objArr[3].toString());
					elementInfo.put("element_name", null==objArr[4] ? "" : objArr[4].toString());
					returnList.add(elementInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("根据类别查询主题域列表数据异常："+e.getMessage());
		}
		return returnList;
	}
	
	@Override
	public List<Map<String, Object>> getElementByMetadataIdList(List<Long> metadataIdList) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT m.id metadata_id,m.NAME metadata_name,e.id element_id,e.NAME element_name FROM metadata_field m, element_field ef, element_data e ");
			sql.append(" WHERE m.ID = ef.METADATA_ID AND ef.ELEMENT_ID = e.ID and m.ID IN :metadataIdList ");
			sql.append(" ORDER BY ef.LEVEL ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			query.setParameter("metadataIdList", metadataIdList);
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> elementInfo = new HashMap<String, Object>();
					elementInfo.put("metadata_id", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
					elementInfo.put("metadata_name", null==objArr[1] ? "" : objArr[1].toString());
					elementInfo.put("element_id", null==objArr[2] ? 0L : Long.valueOf(objArr[2].toString()));
					elementInfo.put("element_name", null==objArr[3] ? "" : objArr[3].toString());
					returnList.add(elementInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("根据元数据组ID集合查询元数据列表数据异常："+e.getMessage());
		}
		return returnList;
	}
}
