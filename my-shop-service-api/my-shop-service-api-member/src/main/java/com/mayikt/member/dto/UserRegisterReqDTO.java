package com.mayikt.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/4/8 0008 下午 3:01
 * @version: V1.0
 */
@Data
@ApiModel(value = "user对象", description = "用户对象user")
public class UserRegisterReqDTO {
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", name = "mobile", required = true)
    private String mobile;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "passWord", required = true)
    private String passWord;
}
