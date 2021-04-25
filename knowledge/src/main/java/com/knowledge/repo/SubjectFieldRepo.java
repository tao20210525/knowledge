package com.knowledge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.SubjectField;

@Repository
public interface SubjectFieldRepo extends JpaRepository<SubjectField, Long> {

	/**
	 * 查询主题域信息
	 * @return
	 */
	@	Query(value="select * from SUBJECT_FIELD", nativeQuery=true)
	List<SubjectField> getSubjectField();
	
	
}
