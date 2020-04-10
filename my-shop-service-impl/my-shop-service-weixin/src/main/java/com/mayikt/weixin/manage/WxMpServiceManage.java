package com.mayikt.weixin.manage;

import com.mayikt.base.BaseResponse;
import com.mayikt.constants.HttpConstant;
import com.mayikt.member.dto.UserRespDTO;
import com.mayikt.weixin.feign.MemberInfoServiceFeign;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/4/10 0010 上午 11:28
 * @version: V1.0
 */
@Component
public class WxMpServiceManage {

    @Autowired
    private MemberInfoServiceFeign memberInfoServiceFeign;

    public WxMpXmlOutMessage handler(Long userId, String openId) {
        // 先根据openId查询是否已经关联
        BaseResponse<UserRespDTO> userRespDTOBaseResponse = memberInfoServiceFeign.selectByOpenId(openId);
        if (HttpConstant.RPC_RESULT_SUCCESS.equals(userRespDTOBaseResponse.getCode())) {
            return null;
        }

        // 如果没有关联 update
        memberInfoServiceFeign.updateUseOpenId(userId, openId);
        return null;
    }

}
