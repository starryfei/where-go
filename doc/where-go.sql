/*
 Navicat Premium Data Transfer

 Source Server         : user
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : where-go

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 04/11/2019 23:55:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `comment` text NOT NULL,
  `doc_id` varchar(20) NOT NULL,
  `comment_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doc_index` (`doc_id`),
  KEY `user_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for fabulous
-- ----------------------------
DROP TABLE IF EXISTS `fabulous`;
CREATE TABLE `fabulous` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `doc_id` varchar(20) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `doc_index` (`doc_id`,`status`),
  KEY `user_index` (`user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for flow
-- ----------------------------
DROP TABLE IF EXISTS `flow`;
CREATE TABLE `flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `flower_id` varchar(20) NOT NULL,
  `flower_time` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `flow_index` (`user_id`,`flower_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `content_label` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `create_date` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `status_index` (`status`) USING BTREE,
  KEY `entity_index` (`user_id`,`doc_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of travel
-- ----------------------------
BEGIN;
INSERT INTO `travel` VALUES (1, '4ca21349-2', 'hello world', 'yafeiiiiiiiiiiiiiii', 'mmmmmmmm', '1', 'yafei', 'aaa', 'beijing', '..................', 1);
INSERT INTO `travel` VALUES (2, 'e418dd11-1', 'hello world', 'yafeiiiiiiiiiiiiiii', 'mmmmmmmm', '1', 'yafei', 'aaa', 'beijing', '..................', 1);
INSERT INTO `travel` VALUES (3, 'b1d80b9d-c', 'hello world', 'yafeiiiiiiiiiiiiiii', 'mmmmmmmm', '1', 'yafei', 'aaa', 'beijing', '..................', 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_pwd` varchar(50) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `user_icon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_user_name_IDX` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'yafei', '123', 'aa@gmail.com', '123', '111');
INSERT INTO `user` VALUES (2, 'yafei1', '123', '11', '222', 'dddd');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
