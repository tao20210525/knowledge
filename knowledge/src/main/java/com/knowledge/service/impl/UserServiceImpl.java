package com.knowledge.service.impl;

import com.knowledge.body.LoginReq;
import com.knowledge.dao.UserDao;
import com.knowledge.domain.Response;
import com.knowledge.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * 用户登录
     * @param loginReq
     * @return
     */
    @Override
    public Response login(LoginReq loginReq) {
        Map<String, Object> login = userDao.login(loginReq);
        if (CollectionUtils.isEmpty(login)){
            return Response.error("01","帐号或密码错误");
        }
        Response response = new Response();
        response.setCode("00");
        response.setMessage("登录成功");
        response.setContent(login);
        return response;
    }
}
