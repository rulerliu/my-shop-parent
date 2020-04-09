package com.mayikt.member.manage;

import com.mayikt.member.entity.UserLoginLogDo;
import com.mayikt.member.mapper.UserLoginLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 蚂蚁课堂创始人-余胜军QQ644064779
 * @title: AsyncLoginLogManage
 * @description: 每特教育独创微服务电商项目
 * @addres www.mayikt.com
 * @date 2020/3/1222:56
 */
@Component
@Slf4j
public class AsyncLoginLogManage {
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;

    @Async
    public void loginLog(Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);
        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() +
                ",流程2");

    }
}
