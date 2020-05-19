package com.mayikt.member.dto;

import lombok.Data;

@Data
public class UnionLoginDTO {

    /**
     * 登陆名称 比如 腾讯QQ 腾讯支付
     */
    String unionName;
    /**
     * 联合登陆头像
     */
    String unionImgLog;
    /**
     * appId
     */
    String appId;
    /**
     * 联合登陆的id
     */
    String unionPublicId;

    /**
     * 回调地址
     */
    String requestAddress;
}
