//package com.mayikt.member.feign;
//
//import com.mayikt.weixin.service.api.WeiXinService;
//import org.springframework.cloud.openfeign.FeignClient;
//
///**
// * @description:
// * @author: liuwq
// * @date: 2020/3/27 0027 下午 5:25
// * @version: V1.0
// */
//@FeignClient("mayikt-weixin")
//public interface WeiXinServiceFeign extends WeiXinService {
//
//    /**
//     * 注意：参数前面不加上RequestParam注解，会报FeignException 405错误
//     * @param userId
//     * @return
//     */
////    @GetMapping("appInfo")
////    String appInfo(@RequestParam("userId") Long userId);
//
//}
