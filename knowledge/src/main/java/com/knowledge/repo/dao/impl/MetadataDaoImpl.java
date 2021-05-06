package com.knowledge.repo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knowledge.body.vo.MetadataVo;
import com.knowledge.repo.dao.MetadataDao;

@Repository
public class MetadataDaoImpl implements MetadataDao {

	private Logger logger = LoggerFactory.getLogger(MetadataDaoImpl.class);

	@Autowired
	private EntityManager entityManager;

	/**
	 * 查询元数据
	 * 
	 * @param category 类别
	 * @param name 模糊查询
	 * @return
	 */
	public List<MetadataVo> getMetadata(String category, String name) {
		List<MetadataVo> metadataList = new ArrayList<MetadataVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
 
		try {
			 StringBuffer sql = new StringBuffer();
		      sql.append(" select e.id,e.name from element_data e where 1=1 ");
		     
		      if(StringUtils.isNotBlank(category)) {
		    	  sql.append(" and e.category =:category");
		      }
		      if(StringUtils.isNotBlank(name)) {
		    	  sql.append(" and e.name like :name");
		      }
		      
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		      if(StringUtils.isNotBlank(category)) {
		    	  query.setParameter("category", category);
		      }
		      if(StringUtils.isNotBlank(name)) {
		    	  query.setParameter("name", "%"+name+"%");
		      }
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  MetadataVo  vo = new MetadataVo();
		          Object[] objects = (Object[]) list.get(i);

		          vo.setElementId(objects[0] == null ? "" : objects[0].toString());
		          vo.setName(objects[1] == null ? "" : objects[1].toString());

		          metadataList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("getMetadata--error", e);
		}
		return metadataList;
	}

}
