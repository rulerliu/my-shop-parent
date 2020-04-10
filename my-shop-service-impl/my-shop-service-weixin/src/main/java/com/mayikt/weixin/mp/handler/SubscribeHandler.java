package com.mayikt.weixin.mp.handler;

import com.mayikt.weixin.manage.WxMpServiceManage;
import com.mayikt.weixin.mp.builder.TextBuilder;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private WxMpServiceManage wxMpServiceManage;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) throws WxErrorException {
        String openId = wxMessage.getFromUser();
        this.logger.info("新关注用户 OPENID: " + openId);

        // 获取微信用户基本信息
        WxMpUser userWxInfo = null;
        try {
            userWxInfo = weixinService.getUserService()
                .userInfo(openId, null);
            if (userWxInfo != null) {
                // TODO 可以添加关注用户到本地数据库
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        String eventKey = wxMessage.getEventKey(); // qrscene_3
        if (StringUtils.isEmpty(eventKey)) {
            this.logger.info("eventKey为空！");
            return new TextBuilder().build("感谢关注" + userWxInfo.getNickname(), wxMessage, weixinService);
        }
        Long userId = Long.parseLong(eventKey.replace("qrscene_", ""));
        wxMpServiceManage.handler(userId, openId);

        try {
            return new TextBuilder().build("感谢关注" + userWxInfo.getNickname(), wxMessage, weixinService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

}
