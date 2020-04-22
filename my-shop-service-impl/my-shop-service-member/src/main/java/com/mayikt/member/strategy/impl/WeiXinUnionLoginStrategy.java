package com.mayikt.member.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.http.HttpClientUtils;
import com.mayikt.member.entity.UnionLoginDo;
import com.mayikt.member.strategy.UnionLoginStrategy;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/4/22 0022 下午 12:29
 * @version: V1.0
 */
@Component
public class WeiXinUnionLoginStrategy implements UnionLoginStrategy {

    @Value("${mayikt.login.wx.accesstoken}")
    private String accesstokenAddress;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo) {
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        // 获取微信newWeixinAccessTokenAddres
        String newWeixinAccessTokenAddres = accesstokenAddress.replace("APPID", unionLoginDo.getAppId())
                .replace("SECRET", unionLoginDo.getAppKey())
                .replace("CODE", code);
        JSONObject accessTokenResult = HttpClientUtils.httpGet(newWeixinAccessTokenAddres);
        if (accessTokenResult == null) {
            return null;
        }
        boolean errcode = accessTokenResult.containsKey("errcode");
        if (errcode) {
            return null;
        }
        // 获取openid
        String openid = accessTokenResult.getString("openid");
        if (StringUtils.isEmpty(openid)) {
            return null;
        }

        // 3.将openid存入到redis中
        String openidToken = tokenUtils.createToken("wx.openid", openid);
        return openidToken;
    }
}
