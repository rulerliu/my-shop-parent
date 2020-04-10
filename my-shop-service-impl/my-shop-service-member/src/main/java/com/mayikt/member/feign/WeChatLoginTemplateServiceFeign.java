package com.mayikt.member.feign;

import com.mayikt.weixin.service.api.WeChatLoginTemplateService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("mayikt-weixin")
public interface WeChatLoginTemplateServiceFeign extends WeChatLoginTemplateService {
}
