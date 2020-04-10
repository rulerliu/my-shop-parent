package com.mayikt.member.service.api;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.dto.UserRespDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/getTokenUser")
    @ApiOperation("会员信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "登录token", required = true)
    })
    @ApiResponse(code = 200, message = "响应成功")
    BaseResponse<JSONObject> getTokenUser(@RequestParam("token") String token);

    /**
     * 关联用户的openid
     *
     * @param userId
     * @param openId
     * @return
     */
    @PostMapping("/updateUseOpenId")
    @ApiOperation("关联用户的openid")
    BaseResponse<Object> updateUseOpenId(@RequestParam("userId") Long userId,
                                         @RequestParam(name = "openId", required = false) String openId);

    /**
     * 根据openid  查询用户信息
     *
     * @param openId
     * @return
     */
    @GetMapping("/selectByOpenId")
    @ApiOperation("根据openid 查询用户信息")
    BaseResponse<UserRespDTO> selectByOpenId(
            @RequestParam("openId") String openId);


    /**
     * 取消关注
     *
     * @param openId
     * @return
     */
    @GetMapping("/cancelFollowOpenId")
    BaseResponse<Object> cancelFollowOpenId(@RequestParam("openId") String openId);

}
