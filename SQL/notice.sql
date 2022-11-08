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

 Date: 30/09/2022 11:37:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 2, '紧急通知！！！', 'hue_acm2.0公告功能即将推出，敬请期待！', '2022-08-04 16:35:03');
INSERT INTO `notice` VALUES (2, 2, '紧急通知（二）！！！', 'hue_acm2.0公告功能即将推出，敬请期待！', '2022-08-04 16:35:28');
INSERT INTO `notice` VALUES (3, 2, '紧急通知（三）！！！', 'hue_acm2.0公告功能即将推出，敬请期待！', '2022-08-04 16:35:32');
INSERT INTO `notice` VALUES (4, 1, 'helloworld', '哈哈哈', '2022-08-05 09:41:44');
INSERT INTO `notice` VALUES (5, 1, 'admin', '立冬，是岁月深处的静好 不觉然间就立冬了，温度却没有太大的变化。\r\n\r\n杭州生活这些年来，感觉这座城市只有冷热之分而无季节之别。据说在北方，立冬是一道坎，是个令世间万物遮蔽起锋芒隐藏起辉煌的坎。\r\n\r\n南方立冬，像是个略施粉黛的女子，不艳不素，还有一丝秋天犹存的温婉。 初冬就这样悄无声息地莅临，脚步有些迟疑，是怜惜枝头上一朵花的孤寂与伶仃么？天空蒙蒙，使得这种灰暗的色调，变得单一而又深沉，像心灵中那些某个空白的时段。\r\n\r\n只是这灰色的天空也无法释怀一缕怅然。晚秋的忧伤还隐隐约约，实在是想抬手挥去，迎向新的季节，却终走不出那段梦境深处的迷惘。\r\n\r\n暮云过了，秋光老尽。秋天最后一抹色彩在昨夜的梦里无声溜走，可我还未来得及悟够它的温婉和古韵，未来得及悟尽它的寂寥与闲愁，就被催促着来到冬天。\r\n\r\n多情便悲秋，这世间的人有谁像我一样，有着痴恋清秋的情结？尚未将秋天堆积的惆怅付于一弯冷月，偏偏又被这一缕寒风触动了万千心事。这不期而遇的寒风吹得生活凌乱不堪，也许只有等到一场飘雪之后才能归于平息，得以释然。\r\n\r\n女儿醒来啼哭不已，早已习惯了这种无常的惊醒，在这初冬的午夜竟然也让我一时无语。却也就在这一刻，那份疼爱情不自禁的洋溢着，流过我心底最柔软的角落。\r\n\r\n起身，抱起爱女揽在怀中，拍打着她的后背，试图让她再度入眠。怎奈爱女并不配合，愈发哭的稀里哗啦，只得摇晃着抱到阳台哄睡，费尽心机才让她闭上眼睛安静下来，我却再无睡意。\r\n\r\n夜在细雨中静寂无声，楼下小巷空无行人。初冬冷落的此刻，数落着跌进那悠悠往事，在无限的隽永里怀念着那抹之不去的感伤。\r\n\r\n立冬一到，令这些感伤的往事纷纷从窗外飘进窗内，拥挤在一堆相互取暖，于是，很多冰凉的往事便有了温度。 雨，不懂人事的飘飞，爱女粉红的脸蛋在昏黄的灯光下酣然。\r\n\r\n俯身，忍不住亲吻她的额头，顿时甜蜜盈满心房，将凌乱的思绪丢在夜雨里。 枯秋也罢，寒冬也罢，其实又如何？还得学会让心情安暖舒适。\r\n\r\n那树下凋零的满地枯叶，虽不铺成殇却也不尽寂然，至少冬已聆听它飘落的声音。 立冬一过，便是小雪大雪了，也许文字会变得越来越薄凉，但心不会，因为怀中抱着一个安放我灵魂的爱女。\r\n\r\n泅渡悠悠岁月，有些人，有些事，需等到隔了山高水长，隔了流年，方可悟得透，看得清。人生须和季节一样分明，遵循自然规律便能平和心态，得以安然。\r\n\r\n安然，是过尽千帆之后的稳定，是百转千回后的从容，是岁月深处的静好。 既已立冬，离飘雪的日子渐行渐近，待到残雪消融，想来春天也为期不远了。', '2022-08-05 10:27:39');

SET FOREIGN_KEY_CHECKS = 1;
