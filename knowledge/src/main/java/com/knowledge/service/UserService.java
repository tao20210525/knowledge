package com.knowledge.service;


import com.knowledge.body.LoginReq;
import com.knowledge.domain.Response;

public interface UserService {
    /**
     * 用户登录
     * @param loginReq
     * @return
     */
    Response login(LoginReq loginReq);

}
