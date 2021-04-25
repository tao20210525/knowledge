package com.knowledge.repo.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.knowledge.repo.dao.CeshiDao;

@Repository
public class CeshiDaoImpl  implements CeshiDao{
	
	private Logger logger = LoggerFactory.getLogger(CeshiDaoImpl.class);

	  @Autowired
	  private EntityManager entityManager;
	  
	  

}
