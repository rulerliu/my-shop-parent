SELECT @@sql_mode;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

CREATE TABLE `meite_user` (
  `USER_ID` int(12) NOT NULL AUTO_INCREMENT COMMENT 'user_id',
  `MOBILE` varchar(11) NOT NULL COMMENT '手机号',
  `PASSWORD` varchar(64) NOT NULL COMMENT '密码',
  `USER_NAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `SEX` tinyint(1) DEFAULT '0' COMMENT '性别  1男  2女',
  `AGE` tinyint(3) DEFAULT '0' COMMENT '年龄',
  `CREATE_TIME` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `IS_AVALIBLE` tinyint(1) DEFAULT '1' COMMENT '是否可用 1正常  2冻结',
  `PIC_IMG` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `QQ_OPENID` varchar(50) DEFAULT NULL COMMENT 'QQ联合登陆id',
  `WX_OPENID` varchar(50) DEFAULT NULL COMMENT '微信公众号关注id',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `MOBILE_UNIQUE` (`MOBILE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户会员表';


CREATE TABLE `user_login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_token` varchar(255) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `equipment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET="utf8";


DROP TABLE IF EXISTS `meite_union_login`;
CREATE TABLE `meite_union_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `union_name` varchar(255) DEFAULT NULL,
  `union_img_log` varchar(255) DEFAULT NULL,
  `union_public_id` varchar(255) DEFAULT NULL,
  `union_bean_id` varchar(255) DEFAULT NULL,
  `app_id` varchar(255) DEFAULT NULL,
  `app_key` varchar(255) DEFAULT NULL,
  `redirect_uri` varchar(255) DEFAULT NULL,
  `request_address` varchar(255) DEFAULT NULL,
  `is_availability` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of meite_union_login
-- ----------------------------
INSERT INTO `meite_union_login` VALUES ('1', '腾讯QQ联合登陆', 'mayikt_qq', 'QQUnionLoginStrategy', '101410454', 'de56b00427f5970650c4f8ee3cfcfc2d', 'http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_qq', 'https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101410454&redirect_uri=http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_qq&state=1', '1');
INSERT INTO `meite_union_login` VALUES ('2', '腾讯微信联合登陆', 'mayikt_weixin', 'weiXinUnionLoginStrategy', 'wx1cfc856828f3c25b', '15ec9979e10838dff8ee336522f62ee0', 'http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_weixin', 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1cfc856828f3c25b&redirect_uri=http://www.itmayiedu.com:7070/login/oauth/callback?unionPublicId=mayikt_weixin&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect', '1');