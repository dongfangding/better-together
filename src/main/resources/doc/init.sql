/*
 Navicat Premium Data Transfer

 Source Server         : ucloud
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 106.75.227.151:3306
 Source Schema         : better-together

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 22/04/2021 13:17:16
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_dynamic
-- ----------------------------
DROP TABLE IF EXISTS `user_dynamic`;
CREATE TABLE `user_dynamic`
(
    `id`                bigint                                                         NOT NULL AUTO_INCREMENT,
    `uid`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '用户uid',
    `content`           varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '动态内容',
    `pic_resource_ids`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '图片资源id集合，多个用逗号分隔',
    `video_resource_id` bigint                                                         NOT NULL DEFAULT '0' COMMENT '视频资源id',
    `publish_time`      timestamp                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `view_level`        int                                                            NOT NULL DEFAULT '0' COMMENT '0 仅好友可见 1 仅自己可见',
    `location`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '定位地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户动态';

-- ----------------------------
-- Table structure for user_dynamic_receive_box
-- ----------------------------
DROP TABLE IF EXISTS `user_dynamic_receive_box`;
CREATE TABLE `user_dynamic_receive_box`
(
    `id`           bigint                                                       NOT NULL AUTO_INCREMENT,
    `uid`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
    `dynamic_id`   bigint                                                       NOT NULL COMMENT '动态id',
    `publish_time` timestamp                                                    NOT NULL COMMENT '动态发布时间，冗余字段，方便排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户动态收件箱，推模式下使用,注意发布人自己的动态也会推送一条给自己的数据';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT,
    `uid`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户uid， 内部全部使用这个来关联',
    `nickname`      varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '昵称',
    `email`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '邮箱，当前系统的登陆方式',
    `password`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '密码',
    `register_time` timestamp                                                     NOT NULL COMMENT '注册时间',
    `avatar_url`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1377910299323879427 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户基本信息表';

-- ----------------------------
-- Table structure for user_partner
-- ----------------------------
DROP TABLE IF EXISTS `user_partner`;
CREATE TABLE `user_partner`
(
    `id`                  bigint                                                       NOT NULL AUTO_INCREMENT,
    `uid`                 varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户uid',
    `partner_uid`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '伙伴uid',
    `partner_name_remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '伙伴昵称备注',
    `create_time`         timestamp                                                    NOT NULL COMMENT '成为伙伴时间',
    `status`              int                                                          NOT NULL COMMENT '0 有效伙伴 1 解散申请中 2 已解散',
    `status_update_time`  timestamp                                                    NOT NULL COMMENT '伙伴关系变更时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1377914622418169864 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户伙伴关系表';

-- ----------------------------
-- Table structure for user_partner_apply
-- ----------------------------
DROP TABLE IF EXISTS `user_partner_apply`;
CREATE TABLE `user_partner_apply`
(
    `id`                 bigint                                                        NOT NULL AUTO_INCREMENT,
    `type`               int                                                           NOT NULL DEFAULT '0' COMMENT '0 伙伴申请 1 伙伴关系解散',
    `from_uid`           varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '发起人uid',
    `target_uid`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '对方uid',
    `target_name_remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '好友名称备注， 发起人对伙伴的名称备注',
    `apply_time`         timestamp                                                     NOT NULL COMMENT '发起时间',
    `status`             int                                                           NOT NULL DEFAULT '0' COMMENT '0 已发起 1 已同意 2 已拒绝 3 已过期',
    `deal_time`          timestamp                                                     NOT NULL COMMENT '处理时间',
    `apply_remark`       varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '申请备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1377916311195922435 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户伙伴申请记录表';

-- ----------------------------
-- Table structure for user_resource
-- ----------------------------
DROP TABLE IF EXISTS `user_resource`;
CREATE TABLE `user_resource`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `uid`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户uid',
    `pic_url`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '图片地址',
    `video_url`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '视频地址',
    `extra`       json                                                                   DEFAULT NULL COMMENT '资源扩展信息',
    `create_date` timestamp                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户上传的资源表';

-- ----------------------------
-- Table structure for user_task_definition
-- ----------------------------
DROP TABLE IF EXISTS `user_task_definition`;
CREATE TABLE `user_task_definition`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT,
    `uid`            varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '任务创建人',
    `name`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '任务名称',
    `description`    varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务描述',
    `cycle`          tinyint unsigned NOT NULL COMMENT '任务周期 0 未知  1 一次性任务 2 每日任务 3 每周任务 4 每月任务  5 每年任务',
    `start_time`     timestamp                                                     NOT NULL COMMENT '一次性任务时需要用户自己指定开始时间， 但其他循环任务则系统自动计算',
    `create_time`    timestamp                                                     NOT NULL COMMENT '任务最初创建时间，对于周期循环任务，这个值是保持最初的时间',
    `end_time`       timestamp                                                     NOT NULL COMMENT '一次性任务时需要用户自己指定任务截止时间， 但其他循环任务则系统自动计算',
    `active`         bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '任务是否激活 0 否 1 是 对于进行中的任务，不可停止，如果是循环任务，则下个周期任务才会停止',
    `supervised`     bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '任务是否需要监督，如果需要监督，则指定好友中的一个人作为监督人。这样任务的完成情况，自己只能申请任务完成，只有监督人确认后任务才算完成。当然监督人也可以单方面直接确认任务完成，不需要申请。',
    `supervised_uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '任务监督人',
    `reward_type`    tinyint unsigned NOT NULL COMMENT '任务奖励\r\n0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况\r\n1 积分奖励， 即完成任务有多少积分\r\n2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。\r\n',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户任务定义表，这里作为用户自己定义的任务资源库，一旦任务开始，则会复制数据到user_task_view中，任务的结算等与这个数据挂钩 用户任务定义表';

-- ----------------------------
-- Table structure for user_task_definition_reward
-- ----------------------------
DROP TABLE IF EXISTS `user_task_definition_reward`;
CREATE TABLE `user_task_definition_reward`
(
    `id`                      bigint                                                        NOT NULL AUTO_INCREMENT,
    `user_task_definition_id` bigint                                                        NOT NULL COMMENT '对应的任务定义id',
    `type`                    tinyint unsigned NOT NULL COMMENT '任务奖励 \r\n0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况\r\n1 积分奖励， 即完成任务有多少积分\r\n2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。\r\n',
    `description`             varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '奖励描述',
    `reward_score`            bigint                                                        NOT NULL DEFAULT '0' COMMENT '奖励积分，仅积分类型时可用',
    `obtain`                  bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否获得 0 否 1 是',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_task_view
-- ----------------------------
DROP TABLE IF EXISTS `user_task_view`;
CREATE TABLE `user_task_view`
(
    `id`                bigint                                                        NOT NULL AUTO_INCREMENT,
    `user_task_view_id` bigint                                                        NOT NULL COMMENT '任务视图id,当前表关联时使用这个字段',
    `uid`               varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '任务创建人',
    `name`              varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '任务名称',
    `description`       varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务描述',
    `cycle`             tinyint unsigned NOT NULL COMMENT '任务周期\r\n0 未知  1 一次性任务 2 每日任务 3 每周任务 4 每月任务  5 每年任务',
    `start_time`        timestamp                                                     NOT NULL COMMENT '一次性任务时需要用户自己指定开始时间， 但其他循环任务则系统自动计算',
    `create_time`       timestamp                                                     NOT NULL COMMENT '任务最初创建时间，对于周期循环任务，这个值是保持最初的时间',
    `end_time`          timestamp                                                     NOT NULL COMMENT '一次性任务时需要用户自己指定任务截止时间， 但其他循环任务则系统自动计算',
    `active`            bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '任务是否激活 0 否 1 是 对于进行中的任务，不可停止，如果是循环任务，则下个周期任务才会停止',
    `supervised`        bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '任务是否需要监督，如果需要监督，则指定好友中的一个人作为监督人。这样任务的完成情况，自己只能申请任务完成，只有监督人确认后任务才算完成。当然监督人也可以单方面直接确认任务完成，不需要申请。',
    `supervised_uid`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '任务监督人',
    `reward_type`       tinyint unsigned NOT NULL COMMENT '任务奖励\r\n0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况\r\n1 积分奖励， 即完成任务有多少积分\r\n2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。\r\n',
    `status`            tinyint unsigned NOT NULL DEFAULT '0' COMMENT '0 进行中 1 任务超时（即时间到了，但是任务双方没进行任务操作） 2 任务完成 3 任务失败 4 奖励确认中 5 奖励完成',
    PRIMARY KEY (`id`) USING BTREE,
    KEY                 `idx_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户任务视图表';

-- ----------------------------
-- Table structure for user_task_view_reward
-- ----------------------------
DROP TABLE IF EXISTS `user_task_view_reward`;
CREATE TABLE `user_task_view_reward`
(
    `id`                bigint                                                        NOT NULL AUTO_INCREMENT,
    `user_task_view_id` bigint                                                        NOT NULL COMMENT '对应的任务视图id',
    `type`              tinyint unsigned NOT NULL COMMENT '任务奖励\r\n0 站外奖励，即用文案描述奖励内容，由任务完成者来确认最终任务奖励完成情况\r\n1 积分奖励， 即完成任务有多少积分\r\n2 递进型积分奖励， 递进型的任务奖励，由任务制定者指定奖励等级，每个等级不同的积分奖励，最终由任务制定者或任务监督人来确认奖励等级。\r\n',
    `description`       varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '奖励描述',
    `reward_score`      bigint                                                        NOT NULL DEFAULT '0' COMMENT '奖励积分，仅积分类型时可用',
    `obtain`            bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否获得 0 否 1 是',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

SET
FOREIGN_KEY_CHECKS = 1;
