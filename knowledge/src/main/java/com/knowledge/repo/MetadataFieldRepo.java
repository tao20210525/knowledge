package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.MetadataField;

@Repository
public interface MetadataFieldRepo extends JpaRepository<MetadataField, Long> {

}
