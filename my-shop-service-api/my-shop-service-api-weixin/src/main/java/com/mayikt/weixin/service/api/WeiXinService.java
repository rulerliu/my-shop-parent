package com.mayikt.weixin.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
public interface WeiXinService {

    /**
     * 注意：参数前面不加上RequestParam注解，Feign复用机制，会报FeignException 405错误
     * 微信接口
     * @return
     */
    @GetMapping("appInfo")
    String appInfo(@RequestParam("userId") Long userId);

}
