package com.mayikt.weixin.mp.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mayikt.http.HttpClientUtils;
import com.mayikt.weixin.entity.WechatKeyword;
import com.mayikt.weixin.mapper.KeywordMapper;
import com.mayikt.weixin.mp.builder.TextBuilder;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
@RefreshScope
public class MsgHandler extends AbstractHandler {

    @Value("${mayikt.wx.defaultMsg}")
    private String defaultMsg;
    @Value("${mayikt.wx.weatherUrl}")
    private String weatherUrl;

    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }
        String content = wxMessage.getContent();
        if (StringUtils.isEmpty(content)) {
            return new TextBuilder().build(defaultMsg, wxMessage, weixinService);
        }

        // 1 查询数据库
        WechatKeyword keyword = keywordMapper.findByKeyword(content);
        if (keyword != null) {
            String keywordValue = keyword.getKeywordValue();
            return new TextBuilder().build(StringUtils.isEmpty(keywordValue) ? defaultMsg : keywordValue, wxMessage, weixinService);
        }

        // 2 调用三方天气预报接口
        if (content.endsWith("天气")) {
            content = content.replace("天气", "");
        }
        JSONObject resultJsonObject = HttpClientUtils.httpGet(weatherUrl.replace("#location", content));
        if (resultJsonObject != null) {
            JSONArray results = resultJsonObject.getJSONArray("results");
            JSONObject resultsZeroJSONObject = results.getJSONObject(0);
            // 地址
            JSONObject nowJSONObject = resultsZeroJSONObject.getJSONObject("now");

            String text = nowJSONObject.getString("text");
            String temperature = nowJSONObject.getString("temperature");
            String lastUpdate = resultsZeroJSONObject.getString("last_update");
            String resultMsg = "您当前查询的城市" + content + "，天气" + text + "，实时温度为:" + temperature + "℃，" +
                    "最后更新的时间为：" +lastUpdate;
            return new TextBuilder().build(resultMsg, wxMessage, weixinService);
        }

        // 3 回复默认消息
        return new TextBuilder().build(defaultMsg, wxMessage, weixinService);
    }

}
