package com.knowledge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.ElementData;

@Repository
public interface AddMetadataRepo extends JpaRepository<ElementData, Long> {

}
