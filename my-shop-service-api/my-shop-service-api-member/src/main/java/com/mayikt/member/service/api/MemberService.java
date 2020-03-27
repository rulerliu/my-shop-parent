package com.mayikt.member.service.api;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
public interface MemberService {

    /**
     * 会员接口
     * @return
     */
    @GetMapping("member2AppInfo")
    String member2AppInfo(Long userId);

}
