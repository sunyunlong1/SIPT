/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : sipt

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-04-26 10:02:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
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
INSERT INTO `admin` VALUES ('00000000001', 'admin', 'admin', 'admin', '校级', '-1', '-');
INSERT INTO `admin` VALUES ('00000000002', 'Yadmin', 'Yadmin', 'Yadmin', '院级', '电气与信息学院', '-');

-- ----------------------------
-- Table structure for `grade`
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
-- Records of grade
-- ----------------------------

-- ----------------------------
-- Table structure for `judges`
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
INSERT INTO `judges` VALUES ('1', 'A19151111', '123', '一号评委', '电气与信息学院', 'one');
INSERT INTO `judges` VALUES ('2', 'A19152222', '123', '二号评委', '电气与信息学院', 'two');
INSERT INTO `judges` VALUES ('3', 'A19153333', '123', '三号评委', '电气与信息学院', 'three');
INSERT INTO `judges` VALUES ('4', 'A19154444', '123', '四号评委', '电气与信息学院', 'four');

-- ----------------------------
-- Table structure for `pgrade`
-- ----------------------------
DROP TABLE IF EXISTS `pgrade`;
CREATE TABLE `pgrade` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `sId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `sName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `oneGrade` int(11) DEFAULT '-1',
  `oneInf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `oneApply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `twoGrade` int(11) DEFAULT '-1',
  `twoInf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `twoApply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `threeGrade` int(11) DEFAULT '-1',
  `threeInf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `threeApply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `fourGrade` int(11) DEFAULT '-1',
  `fourInf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `fourApply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pgAvg` double(20,2) DEFAULT '0.00',
  `level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `cLevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=439 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pgrade
-- ----------------------------

-- ----------------------------
-- Table structure for `process`
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
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process
-- ----------------------------

-- ----------------------------
-- Table structure for `project`
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
  `trecordState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------

-- ----------------------------
-- Table structure for `student`
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
INSERT INTO `student` VALUES ('3', 'A19150251', '123', '李浩然', '东北农业大学电气与信息学院');
INSERT INTO `student` VALUES ('4', 'A19150292', '123', '孙云龙', '东北农业大学电气与信息学院');
INSERT INTO `student` VALUES ('6', 'A19150345', '123', '罗金猪', '东北农业大学电气与信息学院');

-- ----------------------------
-- Table structure for `teacher`
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
INSERT INTO `teacher` VALUES ('00000000001', 'J19150000', '123', '李晓明', '电气与信息学院', '教授');
