http://127.0.0.1:9090/appInfo?userId=1

微信外网回调地址：
http://t298ps.natappfree.cc/wx/portal/wx1cfc856828f3c25b



根据userId生成二维码ticket：
http://127.0.0.1:9090/getQrUrl?userId=3

根据ticket生成二维码：
https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQHh8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAySlM2VFZ1SXJjYTExQnBGVDF1Y0oAAgRZ3I9eAwQAjScA

登录消息推送：
http://127.0.0.1:9090/sendLoginTemplate
{
  "equipment": "pc",
  "loginIp": "1234",
  "loginTime": "2020-04-10",
  "openId": "orNsKwYbXpn5brp3qQ_ABzInYsL4",
  "phone": "185****3439"
}

SELECT @@sql_mode;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

新用户扫码，进入订阅事件
已关注用户扫码，进入扫码事件
取消关注，进入取消关注事件