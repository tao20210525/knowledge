package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.TemplateElement;

@Repository
public interface TemplateElementRepoNew extends JpaRepository<TemplateElement, Long> {

}
