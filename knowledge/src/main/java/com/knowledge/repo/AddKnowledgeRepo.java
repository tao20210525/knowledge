package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knowledge.entity.BasicInfo;

public interface AddKnowledgeRepo extends JpaRepository<BasicInfo, Long> {

}
