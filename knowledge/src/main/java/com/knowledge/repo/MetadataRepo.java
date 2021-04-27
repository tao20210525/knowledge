package com.knowledge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.knowledge.entity.ElementData;

@Repository
public interface MetadataRepo extends JpaRepository<ElementData, Long> {
	
	/**
	 * 根据id查询当前元数据信息
	 * 暂时无用
	 * @param id
	 * @return
	 */
	@Query("select e from ElementData e where e.id =:id")
	List<ElementData> getElementDataInfo(@Param("id") String id);
	
	
}
