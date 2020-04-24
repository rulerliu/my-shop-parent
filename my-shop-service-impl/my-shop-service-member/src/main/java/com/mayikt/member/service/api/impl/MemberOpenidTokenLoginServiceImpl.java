package com.mayikt.member.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.constants.Constants;
import com.mayikt.member.entity.UnionLoginDo;
import com.mayikt.member.entity.UserDo;
import com.mayikt.member.mapper.UnionLoginMapper;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.service.api.MemberOpenidTokenLoginService;
import com.mayikt.member.strategy.UnionLoginStrategy;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:51
 * @version: V1.0
 */
@RestController
@RefreshScope
@Slf4j
public class MemberOpenidTokenLoginServiceImpl extends BaseApiService implements MemberOpenidTokenLoginService {

    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UnionLoginMapper unionLoginMapper;

    @Override
    public BaseResponse<JSONObject> openidTokenLogin(String openidToken) {
        if (StringUtils.isEmpty(openidToken)) {
            return setResultError("openidToken不能为空");
        }

        // 1 根据openidToken，获取openid
        String tokenValue = tokenUtils.getTokenValue(openidToken);
        if (StringUtils.isEmpty(tokenValue)) {
            return setResultError("token错误或者失效");
        }

        // 2 根据openid查询是否关联用户
        JSONObject jsonObject = JSONObject.parseObject(tokenValue);
        String openid = jsonObject.getString("openid");
        String unionPublicId = jsonObject.getString("unionPublicId");

        UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
        if (unionLoginDo == null) {
            return setResultError("该渠道不存在或已关闭");
        }

        UnionLoginStrategy unionLoginStrategy = SpringContextUtils.getBean(unionLoginDo.getUnionBeanId(), UnionLoginStrategy.class);
        UserDo userDo = unionLoginStrategy.getUserDo(openid);
        if (userDo == null) {
            return setResultError("当前用户没关联，应跳转到关联页面");
        }

        // 3 如果已经关联，自动登录
        // 创建token
        String token = tokenUtils.createToken(Constants.MEMBER_LOGIN_PREFIX, userDo.getUserId() + "");

        // 返回token
        JSONObject data = new JSONObject();
        data.put("userToken", token);
        return setResultSuccess(data);
    }
}
