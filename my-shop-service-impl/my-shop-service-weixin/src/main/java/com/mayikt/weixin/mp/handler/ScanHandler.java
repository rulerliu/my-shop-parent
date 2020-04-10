package com.mayikt.weixin.mp.handler;

import com.mayikt.weixin.manage.WxMpServiceManage;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class ScanHandler extends AbstractHandler {

    @Autowired
    private WxMpServiceManage wxMpServiceManage;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        // 扫码事件处理
        String eventKey = wxMpXmlMessage.getEventKey(); // 3
        String openId = wxMpXmlMessage.getFromUser(); //
        this.logger.info("新关注用户 OPENID: " + openId);

        if (!StringUtils.isEmpty(eventKey)) {
            wxMpServiceManage.handler(Long.parseLong(eventKey), openId);
        }

        return null;
    }
}
