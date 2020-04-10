package com.mayikt.weixin.service.api;

import com.mayikt.base.BaseResponse;
import com.mayikt.weixin.dto.LoginTemplateDTO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description:
 * @author: liuwq
 * @date: 2020/3/27 0027 下午 4:40
 * @version: V1.0
 */
@Api(tags = "微信模版消息推送")
public interface WeChatLoginTemplateService {

    /**
     * 注意：参数前面不加上RequestParam注解，Feign复用机制，会报FeignException 405错误
     * 根据userId生成二维码
     * @return
     */
    @PostMapping("/sendLoginTemplate")
    @ApiOperation("微信模版消息推送")
    BaseResponse<Object> sendLoginTemplate(@RequestBody LoginTemplateDTO loginTemplateDto);

}
