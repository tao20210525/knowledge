package com.knowledge.dao.impl;
import com.knowledge.body.vo.KnowledgeConsistVo;
import com.knowledge.dao.AuditingDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


@Repository
@Slf4j
public class AuditingDaoImpl implements AuditingDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<KnowledgeConsistVo> getInfo(Long id) {
        List<KnowledgeConsistVo> metadataList = new ArrayList<KnowledgeConsistVo>();
        List<Object[]>  list = new ArrayList<Object[]> ();
        StringBuffer sql = new StringBuffer();
        sql.append("select K.knowledge_id, K.subject_id, K.subject_name, k.FIELD_NAME, K.ELEMENT_ID, K.INPUT_TYPE, k.CONTENT, K.SORT from knowledge_consist k where k.knowledge_id = ?");
        Query query = this.entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, id);
        list = query.getResultList();

        for (int i = 0; i < list.size(); i++) {
            KnowledgeConsistVo req = new KnowledgeConsistVo();
            Object[] objects = (Object[]) list.get(i);

            req.setKnowledgeId(objects[0] == null ? "" : objects[0].toString());
            req.setSubject_id(objects[1] == null ? "" : objects[1].toString());
            req.setSubject_name(objects[2] == null ? "" : objects[2].toString());
            req.setFieldName(objects[3] == null ? "" : objects[3].toString());
            req.setElementId(Long.valueOf(objects[4] == null ? "" : objects[4].toString()));
            req.setInputType(objects[5] == null ? "" : objects[5].toString());
            req.setContent(objects[6] == null ? "" : objects[6].toString());
            req.setSort(objects[7] == null ? "" : objects[7].toString());

            metadataList.add(req);
        }
        return metadataList;
    }

    @Override
    public void updateInfo(List<KnowledgeConsistVo> list, List<KnowledgeConsistVo> newList) {
        Connection conn = null;
        try {

            List<KnowledgeConsistVo> list1 = new ArrayList<KnowledgeConsistVo>();
            for (int i = 0; i < list.size(); i++) {
                KnowledgeConsistVo kc = list.get(i);
                for (int j = 0; j < list.size(); j++) {
                    KnowledgeConsistVo kcv = newList.get(j);
                    if (kc.getSubject_id().equals(kcv.getSubject_id()) && kc.getInputType().equals(kcv.getInputType())
                            && kc.getElementId().equals(kcv.getElementId()) && kc.getSort().equals(kcv.getSort())
                            && kc.getFieldName().equals(kcv.getFieldName()) && !kc.getContent().equals(kcv.getContent())) {
                        list1.add(newList.get(j));
                    }
                }
            }
            for (int r = 0; r < list1.size(); r++) {
                KnowledgeConsistVo co = list1.get(r);
                String sql = "update knowledge_consist set CONTENT = ? where subject_id = ? and INPUT_TYPE = ? and ELEMENT_ID = ? and SORT = ? and FIELD_NAME = ? and knowledge_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1,co.getContent());
                ps.setString(2,co.getSubject_id());
                ps.setString(3,co.getInputType());
                ps.setString(4, String.valueOf(co.getElementId()).trim());
                ps.setString(5,co.getSort());
                ps.setString(6,co.getFieldName());
                ps.setString(7,co.getKnowledgeId());
                ps.executeUpdate();
            }
        } catch (Exception e) {
            log.info("更新失败", e);
        }
    }

    @Override
    public void updateStatus(Long id) {
        Connection conn = null;
        try {
            String sql = "update knowledge_info set status = 3 where ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id).trim());
            ps.executeUpdate();
        } catch (Exception e) {
            log.info("发布失败", e);
        }
    }
}
