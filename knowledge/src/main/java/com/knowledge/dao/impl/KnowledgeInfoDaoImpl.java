package com.knowledge.dao.impl;

import com.knowledge.dao.KnowledgeInfoDao;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class KnowledgeInfoDaoImpl implements KnowledgeInfoDao {
	
	private Logger logger = LoggerFactory.getLogger(KnowledgeInfoDaoImpl.class);

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Map<String, Object>> getKnowledgeInfo(String typeCode, String level, String title, String status) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT TYPE_CODE,LEVEL,status," +
					"case\n" +
					"when `status`=1 THEN Min(CREATED_TIME)\n" +
					"else MAX(CREATED_TIME) end CREATED_TIME,CREATE_BY,count(1) sum\n" +
					" FROM knowledge_info where 1=1 and IS_DELETE = 0");
			if(StringUtils.isNotBlank(typeCode)){
				sql.append(" and TYPE_CODE = :typeCode ");
			}
			if(StringUtils.isNotBlank(level)){
				sql.append(" and level = :level ");
			}
			if(StringUtils.isNotBlank(status)){
				sql.append(" and status = :status ");
			}
			if(StringUtils.isNotBlank(title)){
				sql.append(" and title like :title ");
			}
			sql.append(" GROUP BY TYPE_CODE,LEVEL,status ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("typeCode", typeCode);
			}
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("level", level);
			}
			if(StringUtils.isNotBlank(title)){
				query.setParameter("title", "%"+title+"%");
			}
			if(StringUtils.isNotBlank(status)){
				query.setParameter("status", status);
			}
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> elementInfo = new HashMap<String, Object>();
					elementInfo.put("typeCode", null==objArr[0] ? 0L : objArr[0].toString());
					elementInfo.put("level", null==objArr[1] ? "" : objArr[1].toString());
					elementInfo.put("status", null==objArr[2] ? "" : objArr[2].toString());
					elementInfo.put("date", null==objArr[3] ? "" : objArr[3].toString());
					elementInfo.put("creater", null==objArr[4] ? "" : objArr[4].toString());
					elementInfo.put("sum", null==objArr[5] ? "" : objArr[5].toString());
					returnList.add(elementInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("根据类别查询元数据列表数据异常："+e.getMessage());
		}
		return returnList;
	}

	@Override
	public List<Map<String, Object>> getKnowledgeInfoList(String typeCode, String level, String title, String status) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT TYPE_CODE,LEVEL,title,status,create_by,update_time,id" +
					" FROM knowledge_info where 1=1");
			if(StringUtils.isNotBlank(typeCode)){
				sql.append(" and TYPE_CODE = :typeCode ");
			}
			if(StringUtils.isNotBlank(level)){
				sql.append(" and level = :level ");
			}
			if(StringUtils.isNotBlank(status)){
				sql.append(" and status = :status ");
			}
			if(StringUtils.isNotBlank(title)){
				sql.append(" and title like :title ");
			}
			sql.append(" ORDER BY CREATED_TIME ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("typeCode", typeCode);
			}
			if(StringUtils.isNotBlank(typeCode)){
				query.setParameter("level", level);
			}
			if(StringUtils.isNotBlank(title)){
				query.setParameter("title", "%"+title+"%");
			}
			if(StringUtils.isNotBlank(status)){
				query.setParameter("status", status);
			}
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> elementInfo = new HashMap<String, Object>();
					elementInfo.put("typeCode", null==objArr[0] ? 0L : objArr[0].toString());
					elementInfo.put("level", null==objArr[1] ? "" : objArr[1].toString());
					elementInfo.put("title", null==objArr[2] ? "" : objArr[2].toString());
					elementInfo.put("status", null==objArr[3] ? "" : objArr[3].toString());
					elementInfo.put("create_by", null==objArr[4] ? "" : objArr[4].toString());
					elementInfo.put("update_time", null==objArr[5] ? "" : objArr[5].toString());
					elementInfo.put("id", null==objArr[6] ? 0L : objArr[6].toString());
					returnList.add(elementInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("根据类别查询元数据列表数据异常："+e.getMessage());
		}
		return returnList;
	}
}
