package com.mayikt.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: UserLoginRegisterDto
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1221:58
 */
@Data
public class UserLoginDTO {
    /**
     * 手机号码
     * 密码
     * 短信验证码
     */
    /**
     * 手机号码
     */

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
//
//    private String smsCode;

}
