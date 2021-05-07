package com.knowledge.repo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knowledge.body.vo.MetadataManagementVo;
import com.knowledge.repo.dao.MetadataManagementDao;

@Repository
public class MetadataManagementDaoImpl implements MetadataManagementDao {

	private Logger logger = LoggerFactory.getLogger(MetadataManagementDaoImpl.class);

	@Autowired
	private EntityManager entityManager;

	/**
	 * 首页-元数据管理
	 * 查询元数据/元数据组数据 
	 * 页面列表信息
	 * @return
	 */
	public List<MetadataManagementVo> queryData() {
		List<MetadataManagementVo> metadataList = new ArrayList<MetadataManagementVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
 
		try {
			 StringBuffer sql = new StringBuffer();
			 
		      sql.append(" select el.id as element_id,el.name as element_name,me.id as metdata_id,me.name as metdata_name,me.category ");
		      sql.append(" from  element_data el ");
		      sql.append(" left join element_field ef on ef.element_id = el.id ");
		      sql.append(" left join metadata_field me on me.id = ef.metadata_id where 1=1 ");
		     
		    
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		   
		      
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  MetadataManagementVo  vo = new MetadataManagementVo();
		          Object[] objects = (Object[]) list.get(i);

		      

		          metadataList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("getMetadataField--error", e);
		}
		return metadataList;
	}


}
