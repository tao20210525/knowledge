package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.BasicInfo;

@Repository
public interface AddKnowledgeRepo extends JpaRepository<BasicInfo, Long> {

}
