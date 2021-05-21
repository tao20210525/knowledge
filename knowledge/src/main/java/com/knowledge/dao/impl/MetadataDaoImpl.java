package com.knowledge.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knowledge.body.ElementDataReq;
import com.knowledge.body.vo.MetadataFieldVo;
import com.knowledge.body.vo.MetadataVo;
import com.knowledge.dao.MetadataDao;

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
	
	
	/**
	 * 查询类别信息
	 * 
	 * @param category 类别
	 * @return
	 */
	public List<MetadataVo> queryCategory() {
		List<MetadataVo> metadataList = new ArrayList<MetadataVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
 
		try {
			 StringBuffer sql = new StringBuffer();
		      sql.append(" select e.category,e.category_name from element_data e where 1=1 ");
		      
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		     
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  MetadataVo  vo = new MetadataVo();
		          Object[] objects = (Object[]) list.get(i);

		          vo.setCategory(objects[0] == null ? "" : objects[0].toString());
		          vo.setCategoryName(objects[1] == null ? "" : objects[1].toString());

		          metadataList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("queryCategory--error", e);
		}
		return metadataList;
	}
	
	
	/**
	 * 查询元数据组
	 * 
	 * @param category 类别
	 * @param name 模糊查询-元数据组名
	 * @return
	 */
	public List<MetadataFieldVo> getMetadataField(ElementDataReq req) {
		List<MetadataFieldVo> metadataFieldList = new ArrayList<MetadataFieldVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		Long id = req.getId();
		String category = req.getCategory();
		String name = req.getName();
 
		try {
			 StringBuffer sql = new StringBuffer();
			 
		      sql.append(" select el.id as element_id,el.name as element_name,me.id as metdata_id,me.name as metdata_name,me.category ");
		      sql.append(" from  element_data el ");
		      sql.append(" left join element_field ef on ef.element_id = el.id ");
		      sql.append(" left join metadata_field me on me.id = ef.metadata_id where 1=1 ");
		     
		      if(StringUtils.isNotBlank(category)) {
		    	  sql.append(" and me.category =:category");
		      }
		      if(StringUtils.isNotBlank(name)) {
		    	  sql.append(" and me.name like :name");
		      }
		      
		      //元数据组id不为空，则代表查询当前元数据组信息
		      if(null != id) {
		    	  sql.append(" and me.id =:id");
		      }
		      
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		      if(StringUtils.isNotBlank(category)) {
		    	  query.setParameter("category", category);
		      }
		      //元数据组名称
		      if(StringUtils.isNotBlank(name)) {
		    	  query.setParameter("name", "%"+name+"%");
		      }
		      if(null != id) {
		    	  query.setParameter("id", id);
		      }
		      
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  MetadataFieldVo  vo = new MetadataFieldVo();
		          Object[] objects = (Object[]) list.get(i);

		          vo.setElementId(objects[0] == null ? "" : objects[0].toString());
		          vo.setElementName(objects[1] == null ? "" : objects[1].toString());
		          vo.setFieldId(objects[2] == null ? "" : objects[2].toString());
		          vo.setMetdataName(objects[3] == null ? "" : objects[3].toString());

		          metadataFieldList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("getMetadataField--error", e);
		}
		return metadataFieldList;
	}


}
