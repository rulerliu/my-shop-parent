package com.mayikt.member.service.api;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "基于openid登录服务")
public interface MemberOpenidTokenLoginService {

    /**
     * 会员接口
     * @return
     */
    @PostMapping("/openidTokenLogin")
    @ApiOperation("基于openid登录服务")
    BaseResponse<JSONObject> openidTokenLogin(@RequestParam("openidToken") String openidToken);

}
