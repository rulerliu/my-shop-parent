package com.mayikt.weixin.feign;

import com.mayikt.member.service.api.MemberInfoService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/4/10 0010 上午 11:22
 * @version: V1.0
 */
@FeignClient("mayikt-member")
public interface MemberInfoServiceFeign extends MemberInfoService {
}
