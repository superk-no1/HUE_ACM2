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

 Date: 30/09/2022 11:37:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `type` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `comment_count` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (2, 2, 'kkk', 'https://cdn.acwing.com/media/user/profile/photo/115868_lg_aa39bdc611.jpg', 'SpringBoot自动装配原理分析', '内容XXX', 1, '2022-08-04 11:29:07', 0);
INSERT INTO `article` VALUES (3, 2, 'kkk', 'https://cdn.acwing.com/media/user/profile/photo/115868_lg_aa39bdc611.jpg', '为什么有公司规定所有接口都用Post？', '携手创作，共同成长！这是我参与「掘金日新计划 · 8 月更文挑战」的第8天，点击查看活动详情\n看到这个标题，你肯定觉得离谱。怎么会有公司规定所有接口都用Post，是架构菜还是开发菜。这可不是夸大其词，这样的公司不少。\n在特定的情况下，规定使用Post可以减少不少的麻烦，一起看看。\nAnswer the question\n我们都知道，get请求一半用来获取服务器信息，post一般用来更新信息。get请求能做的，post都能做，get请求不能做的，post也都能做。\n如果你的团队都是大佬，或者有着良好的团队规范，所有人都在平均水平线之上，并且有良好的纠错机制，那基本不会制定这样的规则。\n但如果团队成员水平参差不齐，尤其是小团队，创业团队，常常上来就开干，没什么规范，纯靠开发者个人素质决定代码质量，这样的团队就不得不制定这样的规范。\n毕竟可以减少非常多的问题，Post不用担心URL长度限制，也不会误用缓存。通过一个规则减少了出错的可能，这个决策性价比极高。\n造成的结果：公司有新人进来，什么lj公司，还有这种要求，回去就在群里讲段子。\n实际上都是有原因的。\n有些外包公司或者提供第三方接口的公司也会选择只用Post，就是图个方便。\n最佳实践\n可能各位大佬都懂了哈，我还是给大家科普下，GET、POST、PUT、DELETE，他们的区别和用法。\nGET\nGET 方法用于从服务器检索数据。这是一种只读方法，因此它没有改变或损坏数据的风险，使用 GET 的请求应该只被用于获取数据。\nGET API 是幂等的。 每次发出多个相同的请求都必须产生相同的结果，直到另一个 API（POST 或 PUT）更改了服务器上资源的状态。\nPOST\nPOST 方法用于将实体提交到指定的资源，通常导致在服务器上的状态变化或创建新资源。POST既不安全也不幂等，调用两个相同的 POST 请求将导致两个不同的资源包含相同的信息（资源 ID 除外）。\nPUT\n主要使用 PUT API更新现有资源（如果资源不存在，则 API 可能决定是否创建新资源）。\nDELETE\nDELETE 方法删除指定的资源。DELETE 操作是幂等的。如果您删除一个资源，它会从资源集合中删除。\n\n作者：正经程序员\n链接：https://juejin.cn/post/7129685508589879327\n来源：稀土掘金\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。', 1, '2022-08-04 11:29:50', 0);
INSERT INTO `article` VALUES (4, 1, 'superk', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', 'VUE入门', '内容XXXX', 2, '2022-08-09 13:56:43', 0);
INSERT INTO `article` VALUES (5, 1, 'superk', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', '图解HTTP', '内容XXXX', 1, '2022-08-17 14:01:55', 0);
INSERT INTO `article` VALUES (6, 1, 'superk', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', '第五篇文章', '内容XXXX', 1, '2022-08-17 15:50:00', 0);
INSERT INTO `article` VALUES (11, 5, 'lulululuk', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', '阿萨德', '<p><span style=\"background-color: rgb(140, 140, 140);\">收到</span></p><p><span style=\"background-color: rgb(140, 140, 140);\">🤓</span></p>', 1, '2022-09-05 15:47:12', 0);
INSERT INTO `article` VALUES (17, 1, 'superk', 'https://cdn.acwing.com/media/user/profile/photo/35170_lg_a032843d53.jpg', '123', '<p style=\"text-align: left;\"><span style=\"color: rgb(44, 62, 80); background-color: rgb(255, 255, 255); font-size: 16px;\">hello大家好，我是</span></p><p style=\"text-align: left;\"><span style=\"color: rgb(44, 62, 80); background-color: rgb(255, 255, 255); font-size: 16px;\">听说</span></p><p style=\"text-align: left;\"><span style=\"color: rgb(44, 62, 80); background-color: rgb(255, 255, 255); font-size: 16px;\">没有</span></p><p style=\"text-align: left;\"><span style=\"color: rgb(44, 62, 80); background-color: rgb(255, 255, 255); font-size: 16px;\">这啥</span></p><p style=\"text-align: left;\"><br></p>', 1, '2022-09-06 09:14:41', 0);
INSERT INTO `article` VALUES (18, 1, 'superk', 'https://superk-bucket.oss-cn-guangzhou.aliyuncs.com/5B104FF8ABED99E8153C5A96DCF8F42F.jpg', '111', '<pre><code class=\"language-cpp\">#include &lt;iostream&gt;</code></pre><p><br></p>', 1, '2022-09-10 16:30:21', 0);
INSERT INTO `article` VALUES (19, 1, 'superk', 'https://superk-bucket.oss-cn-guangzhou.aliyuncs.com/5B104FF8ABED99E8153C5A96DCF8F42F.jpg', 'ss', '<p><span style=\"background-color: rgb(89, 89, 89);\">asad</span><span style=\"background-color: rgb(135, 232, 222);\">11🤣</span></p>', 1, '2022-09-10 16:30:55', 0);
INSERT INTO `article` VALUES (20, 1, 'superk', 'https://superk-bucket.oss-cn-guangzhou.aliyuncs.com/5B104FF8ABED99E8153C5A96DCF8F42F.jpg', 'xz', '<p style=\"text-align: left;\">🤣🤣🤣🤣🤣🤣</p><pre><code class=\"language-cpp\">#include&lt;bits/stdc++.h&gt;</code></pre><p style=\"text-align: left;\"><u><em><strong>撒大声地所多大多过所多</strong></em></u></p><blockquote style=\"text-align: left;\"><u><em><strong>试试</strong></em></u></blockquote><table style=\"width: auto;\"><tbody><tr><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></th></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">g</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">2</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\"></td></tr></tbody></table><p><br></p>', 1, '2022-09-10 16:48:33', 0);

SET FOREIGN_KEY_CHECKS = 1;
