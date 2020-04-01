package com.mayikt.weixin.service.api.impl;

import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.weixin.service.api.WeiXinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
public class WeiXinServiceImpl extends BaseApiService implements WeiXinService {

    @Value("${server.port}")
    private String port;

    @Value("${mayikt.name}")
    private String mayiktName;

    @Override
    public String appInfo(Long appId) {
        return "微信接口:" + appId + ">>>" + port + ",mayiktName:" + mayiktName;
    }

    @Override
    public BaseResponse<String> addApp(String appId, String appPwd) {
        if (StringUtils.isEmpty(appId)) {
            return setResultError("appId不能为空！");
        }
        if (StringUtils.isEmpty(appPwd)) {
            return setResultError("appPwd不能为空！");
        }
        return setResultSuccess();
    }
}
