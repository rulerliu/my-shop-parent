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