package com.mayikt.member.service.api.impl;

import com.mayikt.member.service.api.MemberService;
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
public class MemberServiceImpl implements MemberService {

//    @Autowired
//    private WeiXinServiceFeign weiXinServiceFeign;

    @Override
    public String member2AppInfo(Long userId) {
//        return "会员调用微信接口" + weiXinServiceFeign.appInfo(userId);
        return null;
    }
}
