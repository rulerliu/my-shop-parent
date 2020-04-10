package com.mayikt.weixin.service.api;

import com.alibaba.fastjson.JSONObject;
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
@Api(tags = "微信生成二维码")
public interface WeiXinQrCodeService {

    /**
     * 注意：参数前面不加上RequestParam注解，Feign复用机制，会报FeignException 405错误
     * 根据userId生成二维码
     * @return
     */
    @GetMapping("/getQrUrl")
    @ApiOperation("app信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户的userId", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    BaseResponse<JSONObject> getQrUrl(@RequestParam("userId") Long userId);

}
