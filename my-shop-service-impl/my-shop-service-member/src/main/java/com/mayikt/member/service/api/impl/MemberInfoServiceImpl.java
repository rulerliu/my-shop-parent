package com.mayikt.member.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.dto.UserRespDTO;
import com.mayikt.member.entity.UserDo;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.service.api.MemberInfoService;
import com.mayikt.utils.DesensitizationUtil;
import com.mayikt.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:51
 * @version: V1.0
 */
@RestController
@RefreshScope
public class MemberInfoServiceImpl extends BaseApiService implements MemberInfoService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public BaseResponse<JSONObject> getTokenUser(String token) {
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空");
        }

        // 从redis中查询userId
        String tokenValue = tokenUtils.getTokenValue(token);
        if (StringUtils.isEmpty(tokenValue)) {
            return setResultError("token错误或已过期");
        }

        // 根据userId查询数据库
        long userId = Long.parseLong(tokenValue);
        UserDo userDo = userMapper.findByUser(userId);
        if (userDo == null) {
            return setResultError("用户:" + userId + "不存在数据库");
        }


        // 返回user信息
        UserRespDTO userRespDTO = doToDto(userDo, UserRespDTO.class);
        userRespDTO.setMobile(DesensitizationUtil.mobileEncrypt(userDo.getMobile()));
        return setResultSuccess(userRespDTO);
    }

    @Override
    public BaseResponse<Object> updateUseOpenId(Long userId, String openId) {
        int reuslt = userMapper.updateUseOpenId(userId, openId);
        return setResultDb(reuslt, "关联成功", "关联失败");
    }

    @Override
    public BaseResponse<UserRespDTO> selectByOpenId(String openId) {
        UserDo userDo = userMapper.selectByOpenId(openId);
        if (userDo == null) {
            return setResultError("根据openId查询该用户没有关注过");
        }
        // 需要将do转换成dto
        UserRespDTO userRespDto = doToDto(userDo, UserRespDTO.class);
        String mobile = userRespDto.getMobile();
        userRespDto.setMobile(DesensitizationUtil.mobileEncrypt(mobile));
        return setResultSuccess(userRespDto);
    }

    @Override
    public BaseResponse<Object> cancelFollowOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return setResultError("openId不能为空");
        }
        UserDo userDo = userMapper.selectByOpenId(openId);
        if (userDo == null) {
            return setResultError("根据openId查询该用户没有关注过");
        }
        // 已经关注过，则将对应的用户的openid 变为空
        int result = userMapper.cancelFollowOpenId(openId);
        return setResultDb(result, "取消关注成功", "取消关注成功失败!");
    }

}
