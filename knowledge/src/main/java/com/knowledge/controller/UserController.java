package com.knowledge.controller;


import com.knowledge.body.LoginReq;
import com.knowledge.domain.Response;
import com.knowledge.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;


@RestController
@Api(value = "用户相关", tags = {"用户controller"})
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    private Response login(@RequestBody LoginReq loginReq) {
        if (StringUtils.isBlank(loginReq.getLoginName()) || StringUtils.isBlank(loginReq.getPassword())){
            return Response.error("01","帐号或密码不能为空");
        }
        return userService.login(loginReq);
    }

}
