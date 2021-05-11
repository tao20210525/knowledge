package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.SubjectRelation;

@Repository
public interface SubjectRelationRepo extends JpaRepository<SubjectRelation, Long> {
	
	/**
	 * 根据主题域id-外键 删除主题域信息
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value="delete  from SubjectRelation  where subjectId =:id ")
	int deleteBySubjectId(@Param("id") Long id);
	

}
