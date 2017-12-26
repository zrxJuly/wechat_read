/*
Navicat MySQL Data Transfer

Source Server         : zrx
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : wechat_read

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-12-26 17:14:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sharebook
-- ----------------------------
DROP TABLE IF EXISTS `sharebook`;
CREATE TABLE `sharebook` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `bookName` varchar(20) DEFAULT NULL,
  `bookAuthor` varchar(30) DEFAULT NULL,
  `doubanScore` int(2) DEFAULT NULL,
  `bookContent` text,
  `imgUrl` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`bookId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `openid` varchar(28) NOT NULL,
  `subscribe` int(1) DEFAULT NULL,
  `subscribetime` datetime DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `language` varchar(10) DEFAULT NULL,
  `headImgUrl` varchar(200) DEFAULT NULL,
  `latitude` varchar(20) DEFAULT NULL,
  `longitude` varchar(20) DEFAULT NULL,
  `precisions` varchar(20) DEFAULT NULL,
  `phoneNumber` char(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
