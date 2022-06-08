/*
 Navicat Premium Data Transfer

 Source Server         : 3308mysql
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : localhost:3308
 Source Schema         : jiudians

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 03/06/2022 12:36:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET gbk COLLATE gbk_bin NOT NULL,
  `name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `status` int(2) NULL DEFAULT 1,
  `lend_num` int(11) NULL DEFAULT 0,
  `max_num` int(11) NULL DEFAULT 5,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '管理员', 'admin', 'root@qq.com', '13421231231', 2, 0, 5);
INSERT INTO `admin` VALUES (6, '123', '123', '123', '123', '13421231231', 1, 4, 5);
INSERT INTO `admin` VALUES (7, '111', '111', '111', '111', '11111111111', 2, 0, 5);

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NULL DEFAULT NULL,
  `bid` int(11) NULL DEFAULT NULL,
  `card` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jiudianname` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adminname` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `begintime` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `endtime` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (6, 6, 6, '123', '123', '123', '123', '2022-5-20', '2022-5-20', 2);
INSERT INTO `history` VALUES (7, 6, 6, '123', '123', '123', '123', '2022-5-20', '2022-5-20', 2);
INSERT INTO `history` VALUES (8, 6, 7, '豪华房1', '豪华房1', '123', '123', '2022-5-20', '2022-5-20', 2);
INSERT INTO `history` VALUES (9, 6, 8, '6666', '豪华房', '123', '123', '2022-5-20', '2022-6-20', 1);
INSERT INTO `history` VALUES (10, 6, 9, '147258', '普通房1', '123', '123', '2022-5-27', '2022-6-27', 1);

-- ----------------------------
-- Table structure for jiudian
-- ----------------------------
DROP TABLE IF EXISTS `jiudian`;
CREATE TABLE `jiudian`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(205) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `card` varchar(205) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `autho` varchar(205) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `num` int(11) NOT NULL,
  `press` varchar(205) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `borrow_user` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT '无',
  `borrow_time` char(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT '无',
  PRIMARY KEY (`bid`) USING BTREE,
  UNIQUE INDEX `ISBN`(`card`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiudian
-- ----------------------------
INSERT INTO `jiudian` VALUES (9, '普通房1', '147258', NULL, 9, NULL, '大床房', '123', '2022-5-27');
INSERT INTO `jiudian` VALUES (7, '豪华房1', '豪华房1', NULL, 12, NULL, '豪华房', '无', '无');
INSERT INTO `jiudian` VALUES (8, '豪华房', '6666', NULL, 29, NULL, '豪华房', '123', '2022-5-20');
INSERT INTO `jiudian` VALUES (10, '总统房1', '1469', NULL, 3, NULL, '总统房', '管理员', '2022-05-30');

-- ----------------------------
-- Table structure for jiudiantype
-- ----------------------------
DROP TABLE IF EXISTS `jiudiantype`;
CREATE TABLE `jiudiantype`  (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jiudiantype
-- ----------------------------
INSERT INTO `jiudiantype` VALUES (5, '大床房');
INSERT INTO `jiudiantype` VALUES (6, '双人房');
INSERT INTO `jiudiantype` VALUES (7, '豪华房11');
INSERT INTO `jiudiantype` VALUES (8, '单人房');
INSERT INTO `jiudiantype` VALUES (9, '总统房');

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NULL DEFAULT NULL,
  `bid` int(11) NULL DEFAULT NULL,
  `card` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `jiudianname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adminname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `application_time` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (1, 6, 6, '123', '123', '123', '123', '2022-5-20', 1);
INSERT INTO `review` VALUES (2, 6, 6, '123', '123', '123', '123', '2022-5-20', 1);
INSERT INTO `review` VALUES (3, 6, 6, '123', '123', '123', '123', '2022-5-20', 1);
INSERT INTO `review` VALUES (4, 6, 6, '123', '123', '123', '123', '2022-5-20', 1);
INSERT INTO `review` VALUES (5, 6, 7, '豪华房1', '豪华房1', '123', '123', '2022-5-20', 1);
INSERT INTO `review` VALUES (6, 6, 8, '6666', '豪华房', '123', '123', '2022-5-20', 1);
INSERT INTO `review` VALUES (7, 6, 9, '147258', '普通房1', '123', '123', '2022-5-27', 1);

SET FOREIGN_KEY_CHECKS = 1;
