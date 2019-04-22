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

 Date: 23/04/2019 00:25:27
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
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '-1',
  `isApply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '-',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (00000000001, 'admin', 'admin', 'admin', '校级', '-1', '-');
INSERT INTO `admin` VALUES (00000000002, 'Yadmin', 'Yadmin', 'Yadmin', '院级', '电气与信息学院', '-');
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
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of judges
-- ----------------------------
BEGIN;
INSERT INTO `judges` VALUES (1, 'A19151111', '123', '一号评委', '电气与信息学院', 'one');
INSERT INTO `judges` VALUES (2, 'A19152222', '123', '二号评委', '电气与信息学院', 'two');
INSERT INTO `judges` VALUES (3, 'A19153333', '123', '三号评委', '电气与信息学院', 'three');
INSERT INTO `judges` VALUES (4, 'A19154444', '123', '四号评委', '电气与信息学院', 'four');
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
  `oneGrade` int(11) DEFAULT '-1',
  `oneInf` varchar(255) DEFAULT NULL,
  `oneApply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `twoGrade` int(11) DEFAULT '-1',
  `twoInf` varchar(255) DEFAULT NULL,
  `twoApply` varchar(255) DEFAULT NULL,
  `threeGrade` int(11) DEFAULT '-1',
  `threeInf` varchar(255) DEFAULT NULL,
  `threeApply` varchar(255) DEFAULT NULL,
  `fourGrade` int(11) DEFAULT '-1',
  `fourInf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fourApply` varchar(255) DEFAULT NULL,
  `pgAvg` double(20,2) DEFAULT '0.00',
  `level` varchar(255) DEFAULT NULL,
  `cLevel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pGrade
-- ----------------------------
BEGIN;
INSERT INTO `pGrade` VALUES (00000000030, 'A19150251', '', '2018', '立项', -1, NULL, NULL, -1, NULL, NULL, -1, NULL, NULL, -1, NULL, NULL, 0.00, NULL, NULL);
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
  `isConduct` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process
-- ----------------------------
BEGIN;
INSERT INTO `process` VALUES (12, '2018', '立项', '2018-11-01', '2019-02-01', '收取材料', '流程中');
COMMIT;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `pName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `memberNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `memberInf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `tAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `tName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `tApproval` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pSource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pIntroduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `jAccount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `jName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pathFirst` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pathSecond` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pathThird` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `avg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `recordState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
BEGIN;
INSERT INTO `project` VALUES (00000000034, 'test1', 'A19150251', 'aaa', '1', 'bbb', 'A19150000', '李晓明', '', '瞎整的', '502', '啦啦啦', '', '', '2018', '', '', '', '', '1', '已提交', '1');
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
