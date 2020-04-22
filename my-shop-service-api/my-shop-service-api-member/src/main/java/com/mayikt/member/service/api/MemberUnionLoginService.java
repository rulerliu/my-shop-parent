package com.mayikt.member.service.api;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "会员联合登录服务")
public interface MemberUnionLoginService {

    /**
     * 根据不同的联合登陆id
     * @return
     */
    @GetMapping("/unionLogin")
    @ApiOperation("根据不同的联合登陆id")
    BaseResponse<String> unionLogin(@RequestParam("unionPublicId") String unionPublicId);

    /**
     * 联合登陆回调接口
     *
     * @return
     */
    @GetMapping("/login/oauth/callback")
    @ApiOperation("联合登陆回调接口")
    BaseResponse<JSONObject> unionLoginCallback(@RequestParam("unionPublicId") String unionPublicId);

}
