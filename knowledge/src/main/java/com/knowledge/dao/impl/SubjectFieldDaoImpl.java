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

import com.knowledge.body.SubjectFieldReq;
import com.knowledge.body.vo.SubjectFieldVo;
import com.knowledge.dao.SubjectFieldDao;

@Repository
public class SubjectFieldDaoImpl implements SubjectFieldDao {

	private Logger logger = LoggerFactory.getLogger(SubjectFieldDaoImpl.class);

	@Autowired
	private EntityManager entityManager;

	
	/**
	 *  查询元数据管理左边菜单栏下所对应的主题域信息
	 * 
	 * @param category 类别
	 * @return
	 */
	@Override
	public List<SubjectFieldVo> getSubjectInfo(SubjectFieldReq req) {
		
		List<SubjectFieldVo> subjectFieldList = new ArrayList<SubjectFieldVo>();
		
		List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			 StringBuffer sql = new StringBuffer();
			 
		      sql.append(" select id,subject_name,update_by,update_time,( ");
		      sql.append(" select count(0) from subject_relation sr where sr.subject_id = s.id ");
		      sql.append("  ) from subject_field s where 1=1 ");
		     
		      if(StringUtils.isNotBlank(req.getCategory())) {
		    	  sql.append(" and category =:category");
		      }
		     
		      
		      Query query = this.entityManager.createNativeQuery(sql.toString());
		      if(StringUtils.isNotBlank(req.getCategory())) {
		    	  query.setParameter("category", req.getCategory());
		      }
		     
		       list = query.getResultList();
		      
		      for (int i = 0; i < list.size(); i++) {
		    	  SubjectFieldVo  vo = new SubjectFieldVo();
		          Object[] objects = (Object[]) list.get(i);

		          vo.setId(objects[0] == null ? "" : objects[0].toString());
		          vo.setSubjectName(objects[1] == null ? "" : objects[1].toString());
		          vo.setUpdateBy(objects[2] == null ? "" : objects[2].toString());
		          vo.setUpdateTime(objects[3] == null ? "" : objects[3].toString());
		          vo.setCount(objects[4] == null ? "" : objects[4].toString());

		          subjectFieldList.add(vo);
		        }

		} catch (Exception e) {
			this.logger.info("getMetadataField--error", e);
		}
		return subjectFieldList;
	}
	
}
