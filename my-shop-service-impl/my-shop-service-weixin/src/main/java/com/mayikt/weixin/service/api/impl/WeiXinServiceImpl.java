package com.mayikt.weixin.service.api.impl;

import com.mayikt.weixin.service.api.WeiXinService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:51
 * @version: V1.0
 */
@RestController
public class WeiXinServiceImpl implements WeiXinService {

    @Value("${server.port}")
    private String port;

    @Override
    public String appInfo(Long userId) {
        return "微信接口" + userId + ">>>" + port;
    }
}
