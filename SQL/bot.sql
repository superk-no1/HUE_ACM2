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

 Date: 30/09/2022 11:37:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bot
-- ----------------------------
DROP TABLE IF EXISTS `bot`;
CREATE TABLE `bot`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `modifytime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bot
-- ----------------------------
INSERT INTO `bot` VALUES (1, 1, 'superk', '我靠', 'package club.superk.hueacm.coderunningsystem.utils;\n\nimport java.util.ArrayList;\nimport java.util.List;\n\npublic class Bot implements club.superk.hueacm.coderunningsystem.utils.BotInterface {\n    static class Cell {\n        public int x, y;\n        public Cell(int x, int y) {\n            this.x = x;\n            this.y = y;\n        }\n    }\n\n    private boolean check_tail_increasing(int step) {  // 检验当前回合，蛇的长度是否增加\n        if (step <= 10) return true;\n        return step % 3 == 1;\n    }\n\n    public List<Cell> getCells(int sx, int sy, String steps) {\n        steps = steps.substring(1, steps.length() - 1);\n        List<Cell> res = new ArrayList<>();\n\n        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};\n        int x = sx, y = sy;\n        int step = 0;\n        res.add(new Cell(x, y));\n        for (int i = 0; i < steps.length(); i ++ ) {\n            int d = steps.charAt(i) - \'0\';\n            x += dx[d];\n            y += dy[d];\n            res.add(new Cell(x, y));\n            if (!check_tail_increasing( ++ step)) {\n                res.remove(0);\n            }\n        }\n        return res;\n    }\n\n    @Override\n    public Integer nextMove(String input) {\n        String[] strs = input.split(\"#\");\n        int[][] g = new int[13][14];\n        for (int i = 0, k = 0; i < 13; i ++ ) {\n            for (int j = 0; j < 14; j ++, k ++ ) {\n                if (strs[0].charAt(k) == \'1\') {\n                    g[i][j] = 1;\n                }\n            }\n        }\n\n        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);\n        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);\n\n        List<Cell> aCells = getCells(aSx, aSy, strs[3]);\n        List<Cell> bCells = getCells(bSx, bSy, strs[6]);\n\n        for (Cell c: aCells) g[c.x][c.y] = 1;\n        for (Cell c: bCells) g[c.x][c.y] = 1;\n\n        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};\n        for (int i = 0; i < 4; i ++ ) {\n            int x = aCells.get(aCells.size() - 1).x + dx[i];\n            int y = aCells.get(aCells.size() - 1).y + dy[i];\n            if (x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0) {\n                return i;\n            }\n        }\n\n        return 0;\n    }\n\n}\n', '2022-08-03 11:00:58', '2022-09-08 17:08:00');
INSERT INTO `bot` VALUES (3, 5, 'superbot', '牛p', '菜鸡', '2022-08-03 11:35:02', '2022-08-03 11:35:02');
INSERT INTO `bot` VALUES (4, 5, 'bot1', '描述', '代码', '2022-08-03 14:52:48', '2022-08-03 14:52:48');
INSERT INTO `bot` VALUES (6, 5, 'bot3', '描述', '代码', '2022-08-03 14:52:56', '2022-08-03 14:52:56');
INSERT INTO `bot` VALUES (7, 1, 'bot3', '描述', '代码', '2022-08-03 14:57:37', '2022-08-19 14:08:48');
INSERT INTO `bot` VALUES (8, 1, 'bot5', '描述', '代码', '2022-08-03 14:57:41', '2022-08-03 14:57:41');
INSERT INTO `bot` VALUES (9, 1, 'botkkk', 'love ln', 'print(\"love\")', '2022-08-03 15:54:37', '2022-08-03 15:54:37');
INSERT INTO `bot` VALUES (10, 1, 'botkkk2', 'love ln！', 'print(\"love\")', '2022-08-03 15:54:41', '2022-08-04 09:38:46');
INSERT INTO `bot` VALUES (15, 1, '擎天柱', '大车', '666', '2022-08-03 15:59:43', '2022-08-03 15:59:43');
INSERT INTO `bot` VALUES (16, 1, '大黄蜂', '小车', '666', '2022-08-03 16:01:09', '2022-08-03 16:01:09');
INSERT INTO `bot` VALUES (19, 1, '阿尔法狗', 'win 柯洁', 'win！', '2022-08-03 16:17:53', '2022-08-03 16:17:53');
INSERT INTO `bot` VALUES (22, 2, 'wawa', 'heihei', 'package club.superk.hueacm.coderunningsystem.utils;\n\nimport java.util.ArrayList;\nimport java.util.List;\n\npublic class Bot implements club.superk.hueacm.coderunningsystem.utils.BotInterface {\n    static class Cell {\n        public int x, y;\n        public Cell(int x, int y) {\n            this.x = x;\n            this.y = y;\n        }\n    }\n\n    private boolean check_tail_increasing(int step) {  // 检验当前回合，蛇的长度是否增加\n        if (step <= 10) return true;\n        return step % 3 == 1;\n    }\n\n    public List<Cell> getCells(int sx, int sy, String steps) {\n        steps = steps.substring(1, steps.length() - 1);\n        List<Cell> res = new ArrayList<>();\n\n        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};\n        int x = sx, y = sy;\n        int step = 0;\n        res.add(new Cell(x, y));\n        for (int i = 0; i < steps.length(); i ++ ) {\n            int d = steps.charAt(i) - \'0\';\n            x += dx[d];\n            y += dy[d];\n            res.add(new Cell(x, y));\n            if (!check_tail_increasing( ++ step)) {\n                res.remove(0);\n            }\n        }\n        return res;\n    }\n\n    @Override\n    public Integer nextMove(String input) {\n        String[] strs = input.split(\"#\");\n        int[][] g = new int[13][14];\n        for (int i = 0, k = 0; i < 13; i ++ ) {\n            for (int j = 0; j < 14; j ++, k ++ ) {\n                if (strs[0].charAt(k) == \'1\') {\n                    g[i][j] = 1;\n                }\n            }\n        }\n\n        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);\n        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);\n\n        List<Cell> aCells = getCells(aSx, aSy, strs[3]);\n        List<Cell> bCells = getCells(bSx, bSy, strs[6]);\n\n        for (Cell c: aCells) g[c.x][c.y] = 1;\n        for (Cell c: bCells) g[c.x][c.y] = 1;\n\n        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};\n        for (int i = 0; i < 4; i ++ ) {\n            int x = aCells.get(aCells.size() - 1).x + dx[i];\n            int y = aCells.get(aCells.size() - 1).y + dy[i];\n            if (x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0) {\n                return i;\n            }\n        }\n\n        return 0;\n    }\n\n}\n', '2022-08-08 16:41:16', '2022-09-08 17:08:48');
INSERT INTO `bot` VALUES (23, 2, 'java', 'java', 'package club.superk.hueacm.coderunningsystem.utils;\n\npublic class Bot implements club.superk.hueacm.coderunningsystem.utils.BotInterface {\n    @Override\n    public Integer nextMove(String input) {\n        return 1;\n    }\n}\n', '2022-08-08 16:42:08', '2022-09-08 16:46:24');

SET FOREIGN_KEY_CHECKS = 1;
