package com.knowledge.dao;

import com.knowledge.body.LoginReq;

import java.util.Map;

public interface UserDao {

    Map<String, Object> login(LoginReq loginReq);
}
