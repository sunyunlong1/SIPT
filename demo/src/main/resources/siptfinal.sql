/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : sipt

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2019-05-21 15:09:10
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
INSERT INTO `judges` VALUES ('1', 'J00001', '123', '一号评委', '电气与信息学院', 'one');
INSERT INTO `judges` VALUES ('2', 'J00002', '123', '二号评委', '电气与信息学院', 'two');
INSERT INTO `judges` VALUES ('3', 'J00003', '123', '三号评委', '电气与信息学院', 'three');
INSERT INTO `judges` VALUES ('4', 'J00004', '123', '四号评委', '电气与信息学院', 'four');

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
) ENGINE=InnoDB AUTO_INCREMENT=443 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pgrade
-- ----------------------------
INSERT INTO `pgrade` VALUES ('00000000439', 'A19150251', '李浩然', '2019', '立项', '22', '啦啦啦', '已提交', '23', '122', '已提交', '22', '阿德撒旦', '已提交', '22', '实打实', '已提交', '22.25', 'A', 'A');
INSERT INTO `pgrade` VALUES ('00000000440', 'A19150251', '李浩然', '2019', '中期检查', '99', '', '已提交', '89', '', '已提交', '87', '', '已提交', '80', '', '已提交', '0.00', 'B', 'B');
INSERT INTO `pgrade` VALUES ('00000000441', 'A19150414', '罗金猪', '2020', '立项', '80', '', '已提交', '89', '', '已提交', '87', '', '已提交', '-1', '', '', '0.00', '', '');
INSERT INTO `pgrade` VALUES ('00000000442', 'A19150292', '孙云龙', '2020', '立项', '80', '', '已提交', '89', '', '已提交', '87', '', '已提交', '-1', '', '', '0.00', '', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of process
-- ----------------------------
INSERT INTO `process` VALUES ('126', '2018', '结题', '2019-05-01', '2019-05-09', '已提交', '流程结束');
INSERT INTO `process` VALUES ('127', '2019', '中期检查', '2019-05-01', '2019-05-09', '已提交', '流程结束');

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
  `firstName` varchar(255) DEFAULT '',
  `pathSecond` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `secondName` varchar(255) DEFAULT '',
  `pathThird` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `thirdName` varchar(255) DEFAULT '',
  `avg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `pType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `recordState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `college` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `trecordState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('00000000208', 'test1', 'A19150251', '李浩然', '1', '张三', 'Z00001', '王艳', 'pass', '学生自拟', '101', '简介', '', '', '2019', 'C:\\Users\\UltramanT1G4\\AppData\\Local\\Temp\\tomcat-docbase.7790891686804787767.8080\\upload', 'af2099b3-ff1c-405e-9623-5612d9262208.doc', 'F:\\bishe\\SIPT', '20190520165102440.rar', '', '', '', '创新训练项目', '已提交', '电气与信息学院', '已审批');
INSERT INTO `project` VALUES ('00000000209', 'test2', 'A19150292', '孙云龙', '1', '张三', 'Z00001', '王艳', 'pass', '学生自拟', '101', '简介', '', '', '2020', 'C:\\Users\\UltramanT1G4\\AppData\\Local\\Temp\\tomcat-docbase.7790891686804787767.8080\\upload', 'af2099b3-ff1c-405e-9623-5612d9262208.doc', '', '', '', '', '', '创新训练项目', '已提交', '电气与信息学院', '已审批');
INSERT INTO `project` VALUES ('00000000210', 'test4', 'A19150414', '罗金猪', '1', '张三', 'Z00001', '王艳', 'pass', '学生自拟', '101', '简介', '', '', '2020', 'C:\\Users\\UltramanT1G4\\AppData\\Local\\Temp\\tomcat-docbase.7790891686804787767.8080\\upload', 'af2099b3-ff1c-405e-9623-5612d9262208.doc', '', '', '', '', '', '创新训练项目', '已提交', '电气与信息学院', '已审批');

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
INSERT INTO `teacher` VALUES ('00000000001', 'Z00001', '123', '王艳', '电气与信息学院', '讲师');
