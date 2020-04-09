package com.mayikt.member.service.api;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.dto.UserRegisterReqDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "会员注册服务")
public interface MemberRegisterService {

    /**
     * 会员接口
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("会员注册服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userRegisterReqDTO", value = "用户的注册信息", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    BaseResponse<JSONObject> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO);

}
