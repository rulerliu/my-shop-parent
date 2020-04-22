package com.mayikt.member.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.entity.UnionLoginDo;
import com.mayikt.member.mapper.UnionLoginMapper;
import com.mayikt.member.service.api.MemberUnionLoginService;
import com.mayikt.member.strategy.UnionLoginStrategy;
import com.mayikt.utils.SpringContextUtils;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:51
 * @version: V1.0
 */
@RestController
@RefreshScope
public class MemberUnionLoginServiceImpl extends BaseApiService implements MemberUnionLoginService {

    @Autowired
    private UnionLoginMapper unionLoginMapper;

    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public BaseResponse<String> unionLogin(String unionPublicId) {
        if (StringUtils.isEmpty(unionPublicId)) {
            return setResultError("unionPublicId不能为空");
        }

        UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
        if (unionLoginDo == null) {
            return setResultError("该渠道不存在或已经关闭");
        }

        String token = tokenUtils.createToken("member.unionLogin", "");
        String requestAddress = unionLoginDo.getRequestAddress() + "&state=" + token;
        JSONObject data = new JSONObject();
        data.put("requestAddress", requestAddress);

        return setResultSuccess(data);
    }

    @Override
    public BaseResponse<JSONObject> unionLoginCallback(String unionPublicId) {
        if (StringUtils.isEmpty(unionPublicId)) {
            return setResultError("unionPublicId不能为空");
        }

        UnionLoginDo unionLoginDo = unionLoginMapper.selectByUnionLoginId(unionPublicId);
        if (unionLoginDo == null) {
            return setResultError("该渠道不存在或已经关闭");
        }

        UnionLoginStrategy unionLoginStrategy = SpringContextUtils.getBean(unionLoginDo.getUnionBeanId(), UnionLoginStrategy.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String openId = unionLoginStrategy.unionLoginCallback(request, unionLoginDo);
        if (StringUtils.isEmpty(openId)) {
            return setResultError("系统错误");
        }

        // 3.将openid存入到redis中
        String openidToken = tokenUtils.createToken("qq.openid", openId);

        JSONObject data = new JSONObject();
        data.put("openidToken", openidToken);
        return setResultSuccess(data);
    }

}
