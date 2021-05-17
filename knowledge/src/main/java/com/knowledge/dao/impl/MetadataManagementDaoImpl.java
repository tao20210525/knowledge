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

import com.knowledge.body.MetadataManagementReq;
import com.knowledge.body.vo.MetadataManagementVo;
import com.knowledge.dao.MetadataManagementDao;
import com.knowledge.util.PublicUtil;

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
	public List<MetadataManagementVo> queryData(MetadataManagementReq req) {
		List<MetadataManagementVo> metadataList = new ArrayList<MetadataManagementVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		String category = req.getCategory();
 
		try {
			 StringBuffer sql = new StringBuffer();
			  sql.append(" select * from ( ");
		      sql.append(" select el.id,el.name,el.field_name,el.data_type,el.input_type,el.update_by,el.update_time,'元数据' AS identification,category ");
		      sql.append(" from  element_data el ");
		      sql.append(" UNION ");
		      sql.append(" select me.id,me.name,'' AS field_name,'' AS data_type,'' AS input_type,'' AS update_by,'' AS update_time,'元数据组' AS identification,category ");
		      sql.append(" from metadata_field me ");
		      sql.append(" ) a where 1=1 ");
		      //to_char(el.update_time ,'yyyymmdd') 
		      
		      //页面模糊查询
		      if(StringUtils.isNotBlank(req.getType()) && StringUtils.isNotBlank(req.getName())) {
		    	  
				   //下拉类型 1:元数据/组 2:名称 3:标识 4:数据类型 5:输入类型 6:操作人
			      String[] type = req.getType().split(",");
			      sql.append(" and (");
			      String temp = "";
		    	  for (int i = 0; i < type.length; i++) {
		    		  
		    		  //类型枚举值转换成表里字段名
		    		  String typeName = PublicUtil.getType(type[i]);
		    		  
		    		  temp += "or "+typeName+" like :name ";
		    	  }
		    	  if(StringUtils.isNotBlank(temp)) {
		    		  temp = temp.substring(3);
		    	  }
		    	  sql.append(temp+" )");
		      }
		      
		      if(StringUtils.isNotBlank(req.getCategory())) {
		    	  sql.append(" and a.category =:category");
		      }
		      
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		      
		      if(StringUtils.isNotBlank(req.getType()) && StringUtils.isNotBlank(req.getName())) {
		    	  query.setParameter("name", "%"+req.getName()+"%");
		      }
		      
		      if(StringUtils.isNotBlank(req.getCategory())) {
		    	  query.setParameter("category", category);
		      }
		      
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  MetadataManagementVo  vo = new MetadataManagementVo();
		          Object[] objects = (Object[]) list.get(i);

		          vo.setId(objects[0] == null ? "" : objects[0].toString());
		          vo.setName(objects[1] == null ? "" : objects[1].toString());
		          vo.setFieldName(objects[2] == null ? "" : objects[2].toString());
		          vo.setDataType(objects[3] == null ? "" : objects[3].toString());
		          vo.setInputType(objects[4] == null ? "" : objects[4].toString());
		          vo.setUpdateBy(objects[5] == null ? "" : objects[5].toString());
		          vo.setUpdateTime(objects[6] == null ? "" : objects[6].toString());
		          vo.setIdentification(objects[7] == null ? "" : objects[7].toString());

		          metadataList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("queryData--error", e);
		}
		return metadataList;
	}

	
	/**
	 * 首页-元数据管理
	 * 点击上方主题域信息，出现主题域对应页面列表信息
	 * @param req
	 * @return
	 */
	@Override
	public List<MetadataManagementVo> queryInfo(MetadataManagementReq req) {
		
      List<MetadataManagementVo> metadataList = new ArrayList<MetadataManagementVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		String category = req.getCategory();
		
//		select * from (
//				select sr.element_id,ed.name,ed.field_name,ed.data_type,ed.input_type,ed.update_by,ed.update_time,'元数据' as identification,ed.category 
//				from  subject_field su 
//				left join subject_relation sr on su.id = sr.subject_id
//				left join element_data ed on ed.id = sr.element_id
//				union
//				select sr.element_id,mf.name,'' as field_name,'' as data_type,'' as input_type,'' as update_by,'' as update_time,'元数据组' as identification,mf.category 
//				from  subject_field su 
//				left join subject_relation sr on su.id = sr.subject_id
//				left join metadata_field mf on mf.id = sr.element_id
//				)a where a.category ='001'
 
		try {
			 StringBuffer sql = new StringBuffer();
			  sql.append(" select * from ( ");
		      sql.append(" select sr.element_id,ed.name,ed.field_name,ed.data_type,ed.input_type,ed.update_by,ed.update_time,'元数据' as identification,ed.category ");
		      sql.append(" from  subject_field su ");
		      sql.append(" left join subject_relation sr on su.id = sr.subject_id ");
		      sql.append(" left join element_data ed on ed.id = sr.element_id ");
		      sql.append(" union ");
		      sql.append(" select sr.element_id,mf.name,'' as field_name,'' as data_type,'' as input_type,'' as update_by,'' as update_time,'元数据组' as identification,mf.category  ");
		      sql.append(" from  subject_field su ");
		      sql.append(" left join subject_relation sr on su.id = sr.subject_id ");
		      sql.append(" left join metadata_field mf on mf.id = sr.element_id ");
		      sql.append(" ) a where 1=1 ");
		      
		      //页面模糊查询
		      if(StringUtils.isNotBlank(req.getType()) && StringUtils.isNotBlank(req.getName())) {
		    	  
				   //下拉类型 1:元数据/组 2:名称 3:标识 4:数据类型 5:输入类型 6:操作人
			      String[] type = req.getType().split(",");
			      sql.append(" and (");
			      String temp = "";
		    	  for (int i = 0; i < type.length; i++) {
		    		  
		    		  //类型枚举值转换成表里字段名
		    		  String typeName = PublicUtil.getType(type[i]);
		    		  
		    		  temp += "or "+typeName+" like :name ";
		    	  }
		    	  if(StringUtils.isNotBlank(temp)) {
		    		  temp = temp.substring(3);
		    	  }
		    	  sql.append(temp+" )");
		      }
		      
		      if(StringUtils.isNotBlank(req.getCategory())) {
		    	  sql.append(" and a.category =:category");
		      }
		      
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		      
		      if(StringUtils.isNotBlank(req.getType()) && StringUtils.isNotBlank(req.getName())) {
		    	  query.setParameter("name", "%"+req.getName()+"%");
		      }
		      
		      if(StringUtils.isNotBlank(req.getCategory())) {
		    	  query.setParameter("category", category);
		      }
		      
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  MetadataManagementVo  vo = new MetadataManagementVo();
		          Object[] objects = (Object[]) list.get(i);

		          vo.setId(objects[0] == null ? "" : objects[0].toString());
		          vo.setName(objects[1] == null ? "" : objects[1].toString());
		          vo.setFieldName(objects[2] == null ? "" : objects[2].toString());
		          vo.setDataType(objects[3] == null ? "" : objects[3].toString());
		          vo.setInputType(objects[4] == null ? "" : objects[4].toString());
		          vo.setUpdateBy(objects[5] == null ? "" : objects[5].toString());
		          vo.setUpdateTime(objects[6] == null ? "" : objects[6].toString());
		          vo.setIdentification(objects[7] == null ? "" : objects[7].toString());

		          metadataList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("queryInfo--error", e);
		}
		return metadataList;
	}


}
