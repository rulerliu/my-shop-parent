SELECT @@sql_mode;
SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));

DROP TABLE IF EXISTS `wechat_keywords`;
CREATE TABLE `wechat_keywords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword_name` varchar(255) DEFAULT NULL,
  `keyword_value` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `version` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wechat_keywords
-- ----------------------------
INSERT INTO `wechat_keywords` VALUES ('1', '蚂蚁课堂', '蚂蚁课堂专注于java微服务开发网站www.mayikt.com', '2020-03-10 17:32:15', '2020-03-11 17:32:18', '1');
INSERT INTO `wechat_keywords` VALUES ('2', '余胜军', '男，汉族，中国国籍，1997年10月17日出生，蚂蚁课堂创始人、每特教育创始人&97后互联网创业者，现任武汉每特教育科技有限公司董事长职务、上海每特教育科技有限公司执行董事兼法定代表人 ,主要从事互联网Java架构师培训。', '2020-03-10 17:32:45', '2020-03-10 17:32:49', '2');