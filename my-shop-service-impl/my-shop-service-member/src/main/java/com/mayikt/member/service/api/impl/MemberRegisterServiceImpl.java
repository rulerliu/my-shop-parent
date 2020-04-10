package com.mayikt.member.service.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.mayikt.base.BaseApiService;
import com.mayikt.base.BaseResponse;
import com.mayikt.member.dto.UserRegisterReqDTO;
import com.mayikt.member.entity.UserDo;
import com.mayikt.member.mapper.UserMapper;
import com.mayikt.member.service.api.MemberRegisterService;
import com.mayikt.utils.MD5Util;
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
public class MemberRegisterServiceImpl extends BaseApiService implements MemberRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<JSONObject> register(UserRegisterReqDTO userRegisterReqDTO) {
        // 参数验证
        String mobile = userRegisterReqDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("mobile不能为空");
        }
        String passWord = userRegisterReqDTO.getPassWord();
        if (StringUtils.isEmpty(passWord)) {
            return setResultError("password不能为空");
        }

        // 先检查该手机号码是否存在
        UserDo userDbDo = userMapper.existMobile(mobile);
        if (userDbDo != null) {
            return setResultError("该号码 " + mobile + "已存在");
        }

        // dto转vo
        UserDo userDo = dtoToDo(userRegisterReqDTO, UserDo.class);
        userDo.setPassWord(MD5Util.MD5(passWord));

        // 插入数据库
        int result = userMapper.register(userDo);

        return setResultDb(result, "注册成功", "注册失败");
    }
}
