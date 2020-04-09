package com.mayikt.member.service.api;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "会员信息查询服务")
public interface MemberInfoService {

    /**
     * 根据token查询会员信息
     * @return
     */
    @PostMapping("/getTokenUser")
    @ApiOperation("会员信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录token", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    BaseResponse<JSONObject> getTokenUser(@RequestParam("token") String token);

}
