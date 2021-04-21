package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.SubjectField;

@Repository
public interface SubjectFieldRepo extends JpaRepository<SubjectField, Long> {

}
