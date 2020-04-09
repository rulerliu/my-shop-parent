package com.mayikt.member.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: userLoginLog
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1218:31
 */
@Data
public class UserLoginLogDo {
    Long id;
    Long userId;
    String loginIp;
    Date loginTime;
    String loginToken;
    String channel;
    String equipment;

    /**
     * 是否可以用 1 正常 0冻结
     */
    private char isAvalible;

    public UserLoginLogDo(Long userId, String loginIp, Date loginTime, String loginToken, String channel, String equipment) {
        this.userId = userId;
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.loginToken = loginToken;
        this.channel = channel;
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "UserLoginLogDo{" +
                "userId=" + userId +
                ", loginIp='" + loginIp + '\'' +
                ", loginTime=" + loginTime +
                ", loginToken='" + loginToken + '\'' +
                ", channel='" + channel + '\'' +
                ", equipment='" + equipment + '\'' +
                '}';
    }
}
