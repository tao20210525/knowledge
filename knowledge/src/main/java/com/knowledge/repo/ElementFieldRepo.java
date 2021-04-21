package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.ElementField;

@Repository
public interface ElementFieldRepo extends JpaRepository<ElementField, Long> {

}
