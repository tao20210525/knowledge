package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.TemplateRelationship;



@Repository
public interface TemplateRelationshipRepo extends JpaRepository<TemplateRelationship, Long> {

}
