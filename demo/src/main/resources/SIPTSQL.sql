/*
 Navicat Premium Data Transfer

 Source Server         : 毕业设计
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : SIPT

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 20/04/2019 16:08:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (00000000001, 'admin', 'admin', 'admin', '校级', '-1');
INSERT INTO `admin` VALUES (00000000002, 'Yadmin', 'Yadmin', 'Yadmin', '院级', '电气与信息学院');
COMMIT;

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) DEFAULT NULL,
  `tAccount` varchar(255) DEFAULT NULL,
  `fGrade` varchar(255) DEFAULT NULL,
  `sGrade` varchar(255) DEFAULT NULL,
  `tGrade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for judges
-- ----------------------------
DROP TABLE IF EXISTS `judges`;
CREATE TABLE `judges` (
  `id` int(11) NOT NULL,
  `account` varchar(255) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of judges
-- ----------------------------
BEGIN;
INSERT INTO `judges` VALUES (0, 'A19151111', '123', '评委老师', '电气与信息学院');
COMMIT;

-- ----------------------------
-- Table structure for pGrade
-- ----------------------------
DROP TABLE IF EXISTS `pGrade`;
CREATE TABLE `pGrade` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `sId` varchar(255) DEFAULT NULL,
  `sName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `pStatus` varchar(255) DEFAULT NULL,
  `oneGrade` int(11) DEFAULT '0',
  `twoGrade` int(11) DEFAULT '0',
  `threeGrade` int(11) DEFAULT '0',
  `fourGrade` int(11) DEFAULT '0',
  `pgAvg` double(20,2) DEFAULT '0.00',
  `level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pGrade
-- ----------------------------
BEGIN;
INSERT INTO `pGrade` VALUES (00000000001, 'A19150292', '孙云龙', '2019', '立项', NULL, NULL, NULL, NULL, NULL, 'A');
INSERT INTO `pGrade` VALUES (00000000002, 'A19150251', '李浩然', '2019', '立项', NULL, NULL, NULL, NULL, NULL, 'A');
INSERT INTO `pGrade` VALUES (00000000003, 'A19150292', '孙云龙', '2018', '中期检查', NULL, NULL, NULL, NULL, NULL, 'B');
COMMIT;

-- ----------------------------
-- Table structure for process
-- ----------------------------
DROP TABLE IF EXISTS `process`;
CREATE TABLE `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(255) DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `startTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `endTime` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `isCollect` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process
-- ----------------------------
BEGIN;
INSERT INTO `process` VALUES (1, '2019', '立项', '2019-03-04', '2019-06-04', '已停止收取');
INSERT INTO `process` VALUES (2, '2018', '中期检查', '2018-10-04', '2018-12-04-04', '收取材料');
COMMIT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `pName` varchar(255) DEFAULT NULL,
  `sAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sName` varchar(255) DEFAULT NULL,
  `memberNum` varchar(255) DEFAULT NULL,
  `memberInf` varchar(255) DEFAULT NULL,
  `tAccount` varchar(255) DEFAULT NULL,
  `tName` varchar(255) DEFAULT NULL,
  `tApproval` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1',
  `pSource` varchar(255) DEFAULT NULL,
  `pCode` varchar(255) DEFAULT NULL,
  `pIntroduction` varchar(255) DEFAULT NULL,
  `jAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `jName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pathFirst` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pathSecond` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1',
  `pathThird` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1',
  `avg` varchar(255) DEFAULT NULL,
  `pType` varchar(255) DEFAULT NULL,
  `recordState` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` VALUES (00000000001, 'SIPT项目测试', 'A19150292', '孙云龙', '4', '成员信息', 'A19150000', '李晓明', 'pass', '学生自拟', '00001', '项目介绍', 'A19151111', '评委', '2019', 'c:/test', '‘1’', '\'1\'', '校级优秀项目', '创新项目', '已提交', '电气与信息学院');
INSERT INTO `project` VALUES (00000000002, 'SIPT项目测试', 'A19150251', '李浩然', '4', '成员信息', 'A19150000', '李晓明', '1', '学生自拟', '00001', '项目介绍', 'A19151111', '评委', '2019', NULL, '‘1’', '‘1’', '校级优秀项目', '创新项目', '已提交', '电气与信息学院');
INSERT INTO `project` VALUES (00000000004, 'SIPT项目测试', 'A19150292', '孙云龙', '4', '成员信息', 'A19150000', '李晓明', '1', '学生自拟', '00001', '项目介绍', 'A19151111', '评委', '2018', 'c:/test', '\'1\'', '\'1\'', '校级优秀项目', '创新项目', '已提交', '电气与信息学院');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (3, 'A19150251', '123', '李浩然', '东北农业大学电气与信息学院');
INSERT INTO `student` VALUES (4, 'A19150292', '123', '孙云龙', '东北农业大学电气与信息学院');
INSERT INTO `student` VALUES (6, 'A19150345', '123', '罗金猪', '东北农业大学电气与信息学院');
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (00000000001, 'A19150000', '123', '李晓明', '电气与信息学院', '教授');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
