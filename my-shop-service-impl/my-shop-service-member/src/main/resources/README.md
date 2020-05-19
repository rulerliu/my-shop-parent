http://127.0.0.1:7090/member2AppInfo?userId=1

会员注册：
http://127.0.0.1:81/mayikt-member/register
{
	"mobile": "18529103437",
	"passWord": "abc1234"
}

会员登录：
http://127.0.0.1:81/mayikt-member/login
{
	"mobile": "18529103437",
	"passWord": "abc1234"
}

会员信息查询：
http://127.0.0.1:81/mayikt-member/getTokenUser?token=MEMBER_LOGIN_3ea334e2c6984b2da1a5230dca3b0dc6


QQ联合登陆：
1.根据appid和appkey跳转授权链接
https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101410454&redirect_uri=http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_qq&state=test

2.通过重定向回调地址获取授权码
http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_qq&code=B293F2DB1D1038DBBCE4DBE686FD68E9&state=1

3.根据授权码获取accestoken
https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=101410454&client_secret=de56b00427f5970650c4f8ee3cfcfc2d&code=1505747E4B660DD652D703C7A46E60B1&redirect_uri=http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_qq

access_token=CC586C517EA6E482C335249E1F56123E&expires_in=7776000&refresh_token=6B9315511B539C3A01124B996E2AF6E4

4.根据accessToken获取openud
https://graph.qq.com/oauth2.0/me?access_token=CC586C517EA6E482C335249E1F56123E

5.根据openid获取用户信息
https://graph.qq.com/user/get_user_info?access_token=CC586C517EA6E482C335249E1F56123E&oauth_consumer_key=101410454&openid=322CC5247D50EFA6D68296A24B2C69BB


微信联合登陆
1：根据appid和redireci_uri获取授权码code
浏览器点击QQ图标跳转到：http://127.0.0.1:8200/qqAuth
重定向到：https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1cfc856828f3c25b&redirect_uri=http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_weixin&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect

2：根据code获取access_toke和openid
https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1cfc856828f3c25b&secret=15ec9979e10838dff8ee336522f62ee0&code=071evMnM06QDY72ne0nM0Kw3oM0evMnT&state=1&grant_type=authorization_code

3：根据refresh_token刷新access_token（非必须）
https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=wx1cfc856828f3c25b&grant_type=refresh_token&refresh_token=32_aqfMRpTXqNTjbK_69HqLg4GnyHVS7WBRS4DNcU3u3ckb5dTgIIOBB36rIKCeouIiSDhU0Iv7ZPHuNlaLsk9F0Q

4：根据access_token和openid获取用户信息
https://api.weixin.qq.com/sns/userinfo?access_token=32_fTHLIUQpuRPUT4f2aVUQKcv_l4CXLe3DZ2a1vgojKT3qmISZtF-SA9e_sxV-v87IecelYTZjTEJKBoxGl3U9ag&openid=orNsKwYbXpn5brp3qQ_ABzInYsL4&lang=zh_CN

关联页面需要传递token令牌：
1.先根据令牌查询openid有关联过账户，如果关联过账户的话，直接使用openid实现自动登录，返回用户的token给客户端，vue直接跳转到首页

1.接口需要查询联合登陆渠道
2.登录接口改造加上判断，如果有openidToken，应该关联到数据库中
3.openidToken判断是否已经关联过账号，如果关联过直接跳转到首页，没有关联跳转到关联页面