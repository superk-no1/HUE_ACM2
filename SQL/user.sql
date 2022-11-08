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

 Date: 30/09/2022 11:37:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `rating` int(0) NULL DEFAULT 1500 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'superk', '$2a$10$iCpkzyvvPmNjiBeyqzIW8OWmjfcE7ZBv1.r4Xn15QKptO.ylH6lyi', 'https://superk-bucket.oss-cn-guangzhou.aliyuncs.com/5B104FF8ABED99E8153C5A96DCF8F42F.jpg', 1505);
INSERT INTO `user` VALUES (2, 'kkk', '$2a$10$X/vi4fpZtARL11tNJWg0DuVhK.sohpOpe/6m050W4pTFiUlTfXVAq', 'https://superk-bucket.oss-cn-guangzhou.aliyuncs.com/avatar/2022-09-14/d7b8eadd-0c20-4ce7-a891-30d818e1e127.jpg', 1512);
INSERT INTO `user` VALUES (3, 'lululu', '123456', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', 1500);
INSERT INTO `user` VALUES (4, 'lulululu', '123456', 'https://img-qn.51miz.com/Element/00/88/57/68/41049d87_E885768_96b5cecc.png', 1500);
INSERT INTO `user` VALUES (5, 'lulululuk', '$2a$10$0pDRI1L.SucahRVEbnf6u.cK/pvgKAORAIUbd/eSqsK4O36PtwzAe', 'https://img-qn.51miz.com/Element/00/88/57/68/41049d87_E885768_96b5cecc.png', 1501);
INSERT INTO `user` VALUES (6, 'superkkk', '$2a$10$WNtTlhJcUpleMHi18DkUX.KuaZNoBIFjY/reMzh2vHZge1XXB9ILq', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', 1500);
INSERT INTO `user` VALUES (7, 'superkkkk', '$2a$10$8L3nL7EgGxiSfOlxlksx2uBUJf5vFW7bR5Y5U8eDkzeT6qVcAZSGC', 'https://img-qn.51miz.com/Element/00/88/57/68/41049d87_E885768_96b5cecc.png', 1500);
INSERT INTO `user` VALUES (8, 'superk233', '$2a$10$kw2G8F.yWnrJMpeKGa3TKuGXInpH1Ljd65ny4I6Cwydf5AElT/JpS', 'https://img-qn.51miz.com/Element/00/88/57/68/41049d87_E885768_96b5cecc.png', 1500);
INSERT INTO `user` VALUES (9, 'xiaxia', '$2a$10$eZWlchqaT0Y.9iQIk8WmZuoRATvzUVjimWQe5pwlifjmYhGROKAP2', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', 1500);
INSERT INTO `user` VALUES (10, 'ln', '$2a$10$tdEEMqe7emfDsfe/eRU8Re5AJuQWnjEKwYYUJJ.IVu6W0hpJkOmOC', 'https://img-qn.51miz.com/Element/00/88/57/68/41049d87_E885768_96b5cecc.png', 1500);

SET FOREIGN_KEY_CHECKS = 1;
