package com.mayikt.member.service.api;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.dto.UserLoginDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "会员登录服务")
public interface MemberLoginService {

    /**
     * 会员接口
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("会员登录服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userLoginDTO", value = "用户的登录信息", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    BaseResponse<JSONObject> login(@RequestBody UserLoginDTO userLoginDTO);

}
