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

import com.knowledge.dao.KnowledgeDao;

@Repository
public class KnowledgeDaoImpl implements KnowledgeDao {

	private Logger logger = LoggerFactory.getLogger(KnowledgeDaoImpl.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Map<String, Object>> getEnumData(Long elementId) {
		List<Map<String, Object>> returnList = null;
		try {
			List<Object[]> resultList = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT CODE,VALUE FROM element_enumeration WHERE element_id = :elementId ORDER BY CREATED_TIME ");
			Query query = this.entityManager.createNativeQuery(sql.toString());
			query.setParameter("elementId", elementId);
			resultList = query.getResultList();
			if(null != resultList && resultList.size() > 0){
				returnList = new ArrayList<Map<String, Object>>();
				for(Object[] objArr : resultList){
					Map<String, Object> enumInfo = new HashMap<String, Object>();
					enumInfo.put("code", null==objArr[0] ? "" : objArr[0].toString());
					enumInfo.put("value", null==objArr[1] ? "" : objArr[1].toString());
					returnList.add(enumInfo);
				}
			}
		} catch (Exception e) {
			this.logger.info("查询枚举值数据异常："+e.getMessage());
		}
		return returnList;
	}

}
