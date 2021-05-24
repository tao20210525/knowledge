package com.knowledge.repo;

import com.knowledge.entity.KnowledgeConsist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KnowledgeConsistRepo extends JpaRepository<KnowledgeConsist, Long> {

}
