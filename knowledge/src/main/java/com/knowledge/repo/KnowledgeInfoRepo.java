package com.knowledge.repo;

import com.knowledge.body.QueryRepositoryReq;
import com.knowledge.entity.KnowledgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface KnowledgeInfoRepo extends JpaRepository<KnowledgeInfo, Long> {

    /**
     * 根据id查询当前知识库信息
     * @param id
     * @return
     */
    @Query("select e from KnowledgeInfo e where e.id =:id")
    List<KnowledgeInfo> getKnowledgeInfo(@Param("id") String id);
}
