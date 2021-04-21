package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.ElementEnumeration;

@Repository
public interface ElementEnumerationRepo extends JpaRepository<ElementEnumeration, Long> {

}
