package com.knowledge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.ElementEnumeration;

@Repository
public interface ElementEnumerationRepo extends JpaRepository<ElementEnumeration, Long> {
	
	
	@Query(value="delete * from ElementEnumeration e where e.elementId =:elementId", nativeQuery=true)
	List<ElementEnumeration> deleteByElementId(@Param("elementId") Long elementId);

	
}
