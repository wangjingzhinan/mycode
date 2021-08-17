/*
 Navicat MySQL Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : bytedance

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 17/08/2021 16:06:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Account
-- ----------------------------
DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account` (
  `id` int DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of Account
-- ----------------------------
BEGIN;
INSERT INTO `Account` VALUES (1, '支付宝', 3181);
INSERT INTO `Account` VALUES (2, '银行卡', 13001);
INSERT INTO `Account` VALUES (3, '花呗', 100);
INSERT INTO `Account` VALUES (4, '微信', 2350);
COMMIT;

-- ----------------------------
-- Table structure for Bill
-- ----------------------------
DROP TABLE IF EXISTS `Bill`;
CREATE TABLE `Bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `aspect` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of Bill
-- ----------------------------
BEGIN;
INSERT INTO `Bill` VALUES (1, '收入', 244, '2021-08-12 00:00:00', '工资', '收入', '微信');
INSERT INTO `Bill` VALUES (2, '收入', 234, '2021-08-12 17:03:27', '兼职收入', '收入', '支付宝');
INSERT INTO `Bill` VALUES (3, '支出', 312, '2021-08-12 17:20:19', '饮食', '未超预算', '微信');
INSERT INTO `Bill` VALUES (4, '支出', 461, '2021-08-12 17:20:36', '衣服', '接近预算', '花呗');
INSERT INTO `Bill` VALUES (5, '支出', 534, '2021-08-12 17:20:07', '生活用品', '未超预算', '花呗');
INSERT INTO `Bill` VALUES (6, '收入', 123, '2021-08-12 17:20:36', '兼职收入', '收入', '微信');
INSERT INTO `Bill` VALUES (7, '收入', 144, '2021-08-12 17:20:36', '兼职收入', '收入', '支付宝');
INSERT INTO `Bill` VALUES (8, '收入', 155, '2021-08-12 17:20:36', '兼职收入', '收入', '支付宝');
INSERT INTO `Bill` VALUES (9, '收入', 123, '2021-08-15 13:13:13', '工资', '收入', '微信');
INSERT INTO `Bill` VALUES (10, '支出', 134, '2021-08-15 15:57:56', '娱乐', '未超预算', '银行卡');
INSERT INTO `Bill` VALUES (11, '收入', 123, '2021-08-15 17:22:41', '兼职工资', '收入', '银行卡');
INSERT INTO `Bill` VALUES (12, '支出', 100, '2021-08-16 14:28:24', '衣服', '超过预算', '支付宝');
INSERT INTO `Bill` VALUES (13, '支出', 12, '2021-08-16 14:31:22', '衣服', '超过预算', '微信');
INSERT INTO `Bill` VALUES (14, '支出', 300, '2021-08-16 14:47:26', '饮食', '未超预算', '银行卡');
INSERT INTO `Bill` VALUES (15, '收入', 1000, '2021-08-16 17:18:40', '工资', '收入', '微信');
COMMIT;

-- ----------------------------
-- Table structure for Budget
-- ----------------------------
DROP TABLE IF EXISTS `Budget`;
CREATE TABLE `Budget` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  `rest` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of Budget
-- ----------------------------
BEGIN;
INSERT INTO `Budget` VALUES (1, '生活用品', 2000, 787);
INSERT INTO `Budget` VALUES (2, '饮食', 1231, 619);
INSERT INTO `Budget` VALUES (3, '衣服', 501, -72);
INSERT INTO `Budget` VALUES (5, '娱乐', 1000, 866);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
