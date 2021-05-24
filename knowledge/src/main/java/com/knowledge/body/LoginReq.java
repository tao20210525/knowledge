package com.knowledge.body;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginReq {

    @ApiModelProperty("登录帐号")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;

}
