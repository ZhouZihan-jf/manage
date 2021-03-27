/*
Navicat MySQL Data Transfer

Source Server         : DistanceMysql
Source Server Version : 50718
Source Host           : cdb-niufqhd6.bj.tencentcdb.com:10028
Source Database       : jiudianmanage

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-11-08 14:10:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `managesalary` double(10,0) DEFAULT NULL,
  `staffsalary` double(10,0) DEFAULT NULL,
  `cleanerssalary` double(10,0) DEFAULT NULL,
  `manage` double(255,0) DEFAULT NULL,
  `staff` double(255,0) DEFAULT NULL,
  `cleaner` double(255,0) DEFAULT NULL,
  `totalmoney` double(255,0) DEFAULT NULL,
  `totalroom` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('1', '1', '1', '1', '3000', '2000', '1000', '991', '4');

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `householdname` varchar(255) DEFAULT NULL,
  `ID` varchar(255) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `roomid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES ('1', '梨花', '42011719110122354', '2019-11-05 00:00:00', '2019-11-08 00:00:00', '297', '2', '1', '5');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomid` int(11) NOT NULL AUTO_INCREMENT,
  `local` varchar(255) DEFAULT NULL,
  `state` int(255) DEFAULT NULL,
  `type` int(255) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`roomid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '佳源花都', '1', '1', '99');
INSERT INTO `room` VALUES ('2', '申利花苑', '1', '2', '200');
INSERT INTO `room` VALUES ('3', '华科大', '1', '3', '300');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `useraccount` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `power` int(255) DEFAULT NULL,
  `IDnumber` varchar(255) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `photoUrl` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '小枫', '22', '0', '111', null, 'File\\6f0cd7d1-781e-4b42-aeb9-ed78c79d0feadefault.png', '12345678911');
INSERT INTO `user` VALUES ('2', 'manager', '123456', '张伟', '33', '1', '112', null, null, '12345678911');
INSERT INTO `user` VALUES ('5', 'huahua', '123456', '花花', '25', '2', '10008', null, null, '12345678911');
INSERT INTO `user` VALUES ('6', 'xiaoyi', '123456', '小姨', '45', '3', '1009', null, null, '12345678911');
