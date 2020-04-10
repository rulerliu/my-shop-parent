package com.mayikt.member.manage;

import com.mayikt.member.entity.UserLoginLogDo;
import com.mayikt.member.feign.WeChatLoginTemplateServiceFeign;
import com.mayikt.member.mapper.UserLoginLogMapper;
import com.mayikt.utils.TokenUtils;
import com.mayikt.weixin.dto.LoginTemplateDTO;
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
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private WeChatLoginTemplateServiceFeign weChatLoginTemplateServiceFeign;

    @Async
    public void loginLog(String phone, String openId, Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {

        // 根据userid和channel查询登录log表
        UserLoginLogDo dbUserLoginLogDo = userLoginLogMapper.selectByUserIdAndLoginType(userId, channel);

        // 如果之前有登陆过,则将之前token的状态该为不可用，同时清除Redis
        if (dbUserLoginLogDo != null) {
            String oldLoginToken = dbUserLoginLogDo.getLoginToken();
            // 修改原token的状态
            int result = userLoginLogMapper.updateUserTokenNotQuipment(oldLoginToken);
            if (result > 0) {
                // 清除Redis的缓存
                tokenUtils.delToken(oldLoginToken);
            }
        }

        // 插入当前记录日志
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);

        // 微信登录消息推送
        LoginTemplateDTO loginTemplateDTO = new LoginTemplateDTO(phone, loginTime, loginIp, equipment, openId);
        weChatLoginTemplateServiceFeign.sendLoginTemplate(loginTemplateDTO);

        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() +
                ",流程2");

    }
}
