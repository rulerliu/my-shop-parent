package com.mayikt.member.manage;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.member.entity.UnionLoginDo;
import com.mayikt.member.entity.UserLoginLogDo;
import com.mayikt.member.feign.WeChatLoginTemplateServiceFeign;
import com.mayikt.member.mapper.UnionLoginMapper;
import com.mayikt.member.mapper.UserLoginLogMapper;
import com.mayikt.member.strategy.UnionLoginStrategy;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import com.mayikt.weixin.dto.LoginTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private UnionLoginMapper unionLoginMapper;

    @Async
    public void loginLog(String openidToken, String phone, String openId, Long userId, String loginIp, Date loginTime, String loginToken, String channel,
                         String equipment) {

        // 1 根据userid和channel查询登录log表
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

        // 2 插入当前记录日志
        UserLoginLogDo userLoginLogDo = new UserLoginLogDo(userId, loginIp, loginTime, loginToken, channel, equipment);
        userLoginLogMapper.insertUserLoginLog(userLoginLogDo);

        // 3 微信登录消息推送
        LoginTemplateDTO loginTemplateDTO = new LoginTemplateDTO(phone, loginTime, loginIp, equipment, openId);
        weChatLoginTemplateServiceFeign.sendLoginTemplate(loginTemplateDTO);

        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",userLoginLogDo:" + userLoginLogDo.toString() +
                ",流程2");

        // 4.关联我们的openid
        if (!StringUtils.isEmpty(openidToken)) {
            String openIdJson = tokenUtils.getTokenValue(openidToken);
            JSONObject jsonObject = JSONObject.parseObject(openIdJson);
            String unionPublicId = jsonObject.getString("unionPublicId");
            // 3.根据该渠道id查询bean的id ，从容器中获取
            UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
            if (unionLoginDo == null) {
                return ;
            }
            String unionBeanId = unionLoginDo.getUnionBeanId();
            UnionLoginStrategy unionLoginStrategy = SpringContextUtils.
                    getBean(unionBeanId, UnionLoginStrategy.class);
            if (unionLoginStrategy == null) {
                return ;
            }
            String tempOpenId = jsonObject.getString("openId");
            unionLoginStrategy.updateUserOpenId(userId,tempOpenId);
        }

    }
}
