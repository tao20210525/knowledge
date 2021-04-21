package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.SubjectRelation;

@Repository
public interface SubjectRelationRepo extends JpaRepository<SubjectRelation, Long> {

}
