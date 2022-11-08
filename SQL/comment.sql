/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : hue_acm_2

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 30/09/2022 11:37:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '评论者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `pid` int(0) NULL DEFAULT NULL COMMENT '父评论ID',
  `origin_id` int(0) NULL DEFAULT NULL COMMENT '最上级评论ID',
  `article_id` int(0) NULL DEFAULT NULL COMMENT '关联文章ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, '第二条评论', 2, '2022-08-24 16:12:30', 2, 2, 3);
INSERT INTO `comment` VALUES (5, '沙发！', 2, '2022-08-26 11:51:32', 0, 0, 4);
INSERT INTO `comment` VALUES (13, '啥是SpringBoot呀？', 1, '2022-08-26 17:12:17', 0, 0, 2);
INSERT INTO `comment` VALUES (14, '再测一次', 1, '2022-08-26 17:13:36', 0, 0, 2);
INSERT INTO `comment` VALUES (16, '寄！', 1, '2022-08-26 17:16:27', 0, 0, 3);
INSERT INTO `comment` VALUES (19, '111111', 1, '2022-08-27 09:18:44', 0, 0, 6);
INSERT INTO `comment` VALUES (20, '222', 2, '2022-08-27 09:19:12', 0, 0, 6);
INSERT INTO `comment` VALUES (22, '网络协议', 1, '2022-08-27 15:00:14', 0, 0, 5);
INSERT INTO `comment` VALUES (24, '啦啦啦', 5, '2022-08-27 15:02:27', 0, 0, 5);
INSERT INTO `comment` VALUES (26, '2333333333', 1, '2022-08-27 17:18:16', 0, 0, 2);
INSERT INTO `comment` VALUES (27, '听说你很牛', 5, '2022-08-29 09:18:31', 5, 5, 4);
INSERT INTO `comment` VALUES (32, '哈哈哈', 2, '2022-08-29 17:01:40', 27, 5, 4);
INSERT INTO `comment` VALUES (33, '吃瓜~', 1, '2022-08-29 17:16:37', 0, 0, 4);
INSERT INTO `comment` VALUES (36, '自己回复自己', 2, '2022-08-29 17:26:02', 33, 33, 4);
INSERT INTO `comment` VALUES (37, '你谁啊？', 2, '2022-08-30 10:16:55', 0, 0, 3);
INSERT INTO `comment` VALUES (38, '新的风暴已经出现，怎么能够停止不前！', 2, '2022-08-30 14:19:11', 0, 0, 2);
INSERT INTO `comment` VALUES (39, '233', 2, '2022-08-30 14:56:29', 33, 33, 4);
INSERT INTO `comment` VALUES (40, '终于成功啦哈哈哈哈哈哈哈哈哈！', 2, '2022-08-30 14:57:04', 14, 14, 2);
INSERT INTO `comment` VALUES (41, '6啊哥', 1, '2022-08-30 14:58:39', 38, 38, 2);
INSERT INTO `comment` VALUES (42, '教教怎么做的呗', 1, '2022-08-30 14:59:31', 38, 38, 2);
INSERT INTO `comment` VALUES (43, '我靠', 1, '2022-08-30 15:02:58', 40, 14, 2);
INSERT INTO `comment` VALUES (44, '我是你爸爸', 5, '2022-08-30 15:03:40', 42, 38, 2);
INSERT INTO `comment` VALUES (46, '你管我是谁呢', 5, '2022-08-30 15:09:21', 37, 37, 3);
INSERT INTO `comment` VALUES (47, '123', 5, '2022-08-30 22:18:45', 40, 14, 2);
INSERT INTO `comment` VALUES (49, '可以啦', 5, '2022-09-05 10:20:49', 48, 48, 9);
INSERT INTO `comment` VALUES (50, '666', 1, '2022-09-06 09:12:46', 0, 0, 11);
INSERT INTO `comment` VALUES (51, '路过~', 8, '2022-09-07 17:26:54', 0, 0, 2);
INSERT INTO `comment` VALUES (52, '111', 1, '2022-09-09 17:55:58', 43, 14, 2);

SET FOREIGN_KEY_CHECKS = 1;
