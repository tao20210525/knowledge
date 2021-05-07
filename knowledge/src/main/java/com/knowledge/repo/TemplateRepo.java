package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.Template;


@Repository
public interface TemplateRepo extends JpaRepository<Template, Long> {

}
