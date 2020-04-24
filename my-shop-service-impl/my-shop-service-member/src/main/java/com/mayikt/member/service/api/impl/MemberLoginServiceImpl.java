package com.mayikt.member.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.constants.Constants;
import com.mayikt.member.dto.UserLoginDTO;
import com.mayikt.member.entity.UserDo;
import com.mayikt.member.manage.AsyncLoginLogManage;
import com.mayikt.member.mapper.UserLoginLogMapper;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.service.api.MemberLoginService;
import com.mayikt.member.utils.ChannelUtils;
import com.mayikt.utils.MD5Util;
import com.mayikt.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:51
 * @version: V1.0
 */
@RestController
@RefreshScope
@Slf4j
public class MemberLoginServiceImpl extends BaseApiService implements MemberLoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AsyncLoginLogManage asyncLoginLogManage;

    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Autowired
    private ChannelUtils channelUtils;

    @Override
    public BaseResponse<JSONObject> login(UserLoginDTO userLoginDTO, String sourceIp
            , String channel, String deviceInfor) {
        // 参数验证
        String mobile = userLoginDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("mobile不能为空");
        }
        String passWord = userLoginDTO.getPassWord();
        if (StringUtils.isEmpty(passWord)) {
            return setResultError("password不能为空");
        }

        if (!channelUtils.existChannel(channel)) {
            return setResultError("登陆类型出现错误!");
        }

        // 查询数据库
        UserDo userDo = userMapper.login(mobile, MD5Util.MD5(passWord));
        if (userDo == null) {
            return setResultError("手机号码或密码不正确");
        }

        // 设备信息
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }

        // 创建token
        String token = tokenUtils.createToken(Constants.MEMBER_LOGIN_PREFIX, userDo.getUserId() + "");

        // 异步写入登录日志
        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",流程1");
//        for (int i = 0; i < 12; i++) {
        asyncLoginLogManage.loginLog(userDo.getMobile(), userDo.getWxOpenId(), userDo.getUserId(),
                sourceIp, new Date(), token, channel, deviceInfor);
//        }
        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",流程3");

        // 返回token
        JSONObject data = new JSONObject();
        data.put("userToken", token);
        return setResultSuccess(data);
    }

}
