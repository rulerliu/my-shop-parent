package com.mayikt.member.strategy.impl;

import com.mayikt.http.HttpClientUtils;
import com.mayikt.member.entity.UnionLoginDo;
import com.mayikt.member.strategy.UnionLoginStrategy;
import org.apache.commons.lang3.StringUtils;
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
public class QQUnionLoginStrategy implements UnionLoginStrategy {

    @Value("${mayikt.login.qq.accesstoken}")
    private String accesstokenAddress;
    @Value("${mayikt.login.qq.openid}")
    private String openidAddress;

    @Override
    public String unionLoginCallback(HttpServletRequest request, UnionLoginDo unionLoginDo) {
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        // 1.根据授权码获取accessToken
        String realAccesstokenAddress = accesstokenAddress.replace("{client_id}", unionLoginDo.getAppId())
                .replace("{client_secret}", unionLoginDo.getAppKey())
                .replace("{code}", code)
                .replace("{redirect_uri}", unionLoginDo.getRedirectUri());
        String resultAccessToken = HttpClientUtils.httpGetResultString(realAccesstokenAddress);
        if (!resultAccessToken.contains("access_token=")) {
            return null;
        }
        String[] split = resultAccessToken.split("&");
        String accessToken = split[0].split("=")[1];
        if (StringUtils.isEmpty(accessToken)) {
            return null;
        }

        // 2.根据accessToken获取用户的openid
        String resultQQOpenId = HttpClientUtils.httpGetResultString(openidAddress + accessToken);
        if (StringUtils.isEmpty(resultQQOpenId)) {
            return null;
        }
        boolean openid = resultQQOpenId.contains("openid");
        if (!openid) {
            return null;
        }
        String array[] = resultQQOpenId.replace("callback( {", "")
                .replace("} );", "")
                .split(":");
        String openId = array[2].replace("\"", "").replace("\\n", "");
        return openId;
    }
}
