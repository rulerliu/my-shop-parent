package com.mayikt.member.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.constants.Constants;
import com.mayikt.member.dto.UserLoginDTO;
import com.mayikt.member.entity.UserDo;
import com.mayikt.member.manage.AsyncLoginLogManage;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.service.api.MemberLoginService;
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

    @Override
    public BaseResponse<JSONObject> login(UserLoginDTO userLoginDTO) {
        // 参数验证
        String mobile = userLoginDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("mobile不能为空");
        }
        String passWord = userLoginDTO.getPassWord();
        if (StringUtils.isEmpty(passWord)) {
            return setResultError("password不能为空");
        }

        // 查询数据库
        UserDo userDo = userMapper.login(mobile, MD5Util.MD5(passWord));
        if (userDo == null) {
            return setResultError("手机号码或密码不正确");
        }

        // 创建token
        String token = tokenUtils.createToken(Constants.MEMBER_LOGIN_PREFIX, userDo.getUserId() + "");

        // 异步写入登录日志
        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",流程1");
        for (int i = 0; i < 12; i++) {
            asyncLoginLogManage.loginLog(userDo.getUserId(), "ip", new Date(), token, "channel", "equipment");
        }
        System.out.println(">>>>>线程名称:" + Thread.currentThread().getName() + ",流程3");

        // 返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userToken", token);
        return setResultSuccess(jsonObject);
    }

}