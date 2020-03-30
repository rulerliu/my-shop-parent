package com.mayikt.member.service.api;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "会员基本服务")
public interface MemberService {

    /**
     * 会员接口
     * @return
     */
    @GetMapping("member2AppInfo")
    @ApiOperation("会员调用微信接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户的userId", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    String member2AppInfo(Long userId);

}
