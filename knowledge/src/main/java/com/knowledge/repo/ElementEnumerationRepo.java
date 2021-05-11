package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.ElementEnumeration;

@Repository
public interface ElementEnumerationRepo extends JpaRepository<ElementEnumeration, Long> {
	
	/**
	 * 根据元数据id删除枚举值
	 * @param elementId
	 * @return
	 */
	@Modifying
	@Query(value="delete  from ElementEnumeration e where e.elementId =:elementId")
	int  deleteByElementId(@Param("elementId") Long elementId);
	
}
