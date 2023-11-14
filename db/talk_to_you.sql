/*
 Navicat Premium Data Transfer

 Source Server         : wsl2
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : talk_to_you

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 15/11/2023 01:17:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容名称',
  `images` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容封面',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文章内容',
  `is_show` int NOT NULL DEFAULT 0 COMMENT '是否展示',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章标签',
  `author_id` bigint NOT NULL COMMENT '发布者',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `content_user`(`author_id` ASC) USING BTREE,
  CONSTRAINT `article_user` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户内容' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for article_collects
-- ----------------------------
DROP TABLE IF EXISTS `article_collects`;
CREATE TABLE `article_collects`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_collect_per_user_article`(`user_id` ASC, `article_id` ASC) USING BTREE,
  INDEX `article_id`(`article_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_colletcs_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_colletcs_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_collects
-- ----------------------------

-- ----------------------------
-- Table structure for article_comments
-- ----------------------------
DROP TABLE IF EXISTS `article_comments`;
CREATE TABLE `article_comments`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `deleted` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id_2`(`article_id` ASC) USING BTREE,
  INDEX `user_id_3`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_article_id_3` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_comments
-- ----------------------------

-- ----------------------------
-- Table structure for article_likes
-- ----------------------------
DROP TABLE IF EXISTS `article_likes`;
CREATE TABLE `article_likes`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_like_per_user_article`(`user_id` ASC, `article_id` ASC) USING BTREE,
  INDEX `article_id`(`article_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_likes
-- ----------------------------

-- ----------------------------
-- Table structure for article_views
-- ----------------------------
DROP TABLE IF EXISTS `article_views`;
CREATE TABLE `article_views`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_article_id_2` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章浏览表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_views
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'common' COMMENT '类别',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '通知栏信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 'common', '如果让你可以回到过去你想改变什么？', NULL);
INSERT INTO `notice` VALUES (2, 'common', '仙人抚我顶，结发受长生。', NULL);
INSERT INTO `notice` VALUES (3, 'common', '今人不见古时月，今月曾经照古人。', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户密码',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '用户昵称' COMMENT '用户昵称',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '用户头像',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '还没有简介' COMMENT '用户简介',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `role` int NOT NULL DEFAULT 0 COMMENT '角色权限',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`account` DESC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1715011999173201922 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$FneCjf3N6wB2opEk3tbnjevWBNcvJWj5Qwbi4goUCHFMTnvAivru2', 'example@qq.com', 'admin', 'data:image/png;base64,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', '没有简介', '2023-11-14 17:17:08', 1, b'0');

SET FOREIGN_KEY_CHECKS = 1;
