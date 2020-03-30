package com.mayikt.weixin.service.api;

import com.mayikt.base.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "微信基本服务接口")
public interface WeiXinService {

    /**
     * 注意：参数前面不加上RequestParam注解，Feign复用机制，会报FeignException 405错误
     * 微信接口
     * @return
     */
    @GetMapping("/appInfo")
    @ApiOperation("app信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "用户的appId", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    String appInfo(@RequestParam("appId") Long appId);

    @GetMapping("/addApp")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "用户的appId", required = true),
            @ApiImplicitParam(name = "appPwd", value = "用户的appPwd")
    })
    @ApiResponse(code = 200, message = "响应成功")
    BaseResponse<String> addApp(@RequestParam("appId") String appId, @RequestParam(value = "appPwd", required = false) String appPwd);

}
