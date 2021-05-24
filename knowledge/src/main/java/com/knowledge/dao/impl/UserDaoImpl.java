package com.knowledge.dao.impl;

import com.knowledge.body.LoginReq;
import com.knowledge.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Map<String, Object> login(LoginReq loginReq) {
        Map<String, Object> resultMap = null;
        try {
            List<Object[]> resultList = null;
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT user.id AS userId,user.role_id AS roleId,user.login_name AS loginName," +
                    "user.`password` AS password,user.user_name AS userName,user.mobile AS mobile," +
                    "user.email AS email,role.role_name AS roleName,role.description FROM user " +
                    "LEFT JOIN role ON user .role_id = role.id  WHERE user.login_name = :loginName AND user.password = :password");
            Query query = this.entityManager.createNativeQuery(sql.toString());
            query.setParameter("loginName", loginReq.getLoginName());
            query.setParameter("password", loginReq.getPassword());
            resultList = query.getResultList();
            if(null != resultList && resultList.size() > 0){
                Object[] objArr = resultList.get(0);
                resultMap = new HashMap<String, Object>();
                resultMap.put("userId", null==objArr[0] ? 0L : Long.valueOf(objArr[0].toString()));
                resultMap.put("roleId", null==objArr[1] ?  0L : Long.valueOf(objArr[1].toString()));
                resultMap.put("loginName", null==objArr[2] ? "" : objArr[2].toString());
                resultMap.put("password", null==objArr[3] ? "" : objArr[3].toString());
                resultMap.put("userName", null==objArr[4] ? "" : objArr[4].toString());
                resultMap.put("mobile", null==objArr[5] ? "" : objArr[5].toString());
                resultMap.put("email", null==objArr[6] ? "" : objArr[6].toString());
                resultMap.put("roleName", null==objArr[7] ? "" : objArr[7].toString());
                resultMap.put("description", null==objArr[8] ? "" : objArr[8].toString());
            }
        } catch (Exception e) {
            log.info("获取登录数据异常");
        }
        return resultMap;
    }
}
