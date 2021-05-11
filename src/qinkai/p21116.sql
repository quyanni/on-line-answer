/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : p21116

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-09-26 08:24:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '123', '小明');

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `FK_message` (`mid`),
  CONSTRAINT `FK_message` FOREIGN KEY (`mid`) REFERENCES `message` (`mid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES ('6', 'fdjskfjskljfsdjf', '2018-12-31 21:23:30', '9');
INSERT INTO `answer` VALUES ('8', 'fdsakfjdsjaf', '2019-01-02 10:41:47', '10');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `suname` varchar(20) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  KEY `FK_stu` (`suname`),
  KEY `FK_cour` (`cid`),
  CONSTRAINT `FK_cour` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_stu` FOREIGN KEY (`suname`) REFERENCES `student` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('qinkai', '50001');
INSERT INTO `authority` VALUES ('qinkai', '50002');
INSERT INTO `authority` VALUES ('xiaoming', '50001');
INSERT INTO `authority` VALUES ('xiaoming', '50002');
INSERT INTO `authority` VALUES ('qinkai', '50005');
INSERT INTO `authority` VALUES ('qinkai', '50006');
INSERT INTO `authority` VALUES ('xiaoming', '50005');
INSERT INTO `authority` VALUES ('qinkai', '50003');
INSERT INTO `authority` VALUES ('xiaogang', '50001');
INSERT INTO `authority` VALUES ('cswork', '2');
INSERT INTO `authority` VALUES ('qinkai', '2');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `coid` int(11) NOT NULL AUTO_INCREMENT,
  `coname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('10001', '光电信息与计算机工程学院');
INSERT INTO `college` VALUES ('10002', '出版印刷与艺术设计');
INSERT INTO `college` VALUES ('10003', '材料');
INSERT INTO `college` VALUES ('10004', '数学');
INSERT INTO `college` VALUES ('10005', '英语');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(20) DEFAULT NULL,
  `introduction` varchar(1000) DEFAULT NULL,
  `tuname` varchar(20) DEFAULT NULL,
  `coid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `FK_teacher` (`tuname`),
  KEY `FK_col` (`coid`),
  CONSTRAINT `FK_col` FOREIGN KEY (`coid`) REFERENCES `college` (`coid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_teacher` FOREIGN KEY (`tuname`) REFERENCES `teacher` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50007 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '计算机体系结构', '', 'libai01', '10001');
INSERT INTO `course` VALUES ('2', 'java程序开发', '', 'dufu', '10001');
INSERT INTO `course` VALUES ('50001', 'Web应用开发', '掌握Web开发和运行平台的搭建，熟悉HTML、JavaScript和CSS等静态网页开发技术和以JSP2.0为主的动态网页的开发技术。掌握Java Web开发和运行平台的搭建、静态网页的制作、编写满足要求的JSP页面、开发基于Model 1的简单Web应用，能够连接数据库实现较为复杂的业务逻辑开发基于MVC模式的Web应用，能够连接数据库实现较为复杂的业务逻辑。培养良好的学习态度和学习习惯，培养互帮互助的合作精神。。。。', 'zhangsan', '10001');
INSERT INTO `course` VALUES ('50002', 'java', 'afdsjasfjdlsajfs', 'zhangsan', '10001');
INSERT INTO `course` VALUES ('50003', '数据库', 'fdhsafjdsaf', 'zhao1', '10001');
INSERT INTO `course` VALUES ('50004', '软件工程', 'fdhsahkfjsajfsafhsafsa', 'zhao1', '10001');
INSERT INTO `course` VALUES ('50005', '综合英语', 'fdhsafkjsfsa', 'li4', '10005');
INSERT INTO `course` VALUES ('50006', '交互英语', '', 'li4', '10005');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `suname` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`mid`),
  KEY `FK_course` (`cid`),
  KEY `FK_student` (`suname`),
  CONSTRAINT `FK_course` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_student` FOREIGN KEY (`suname`) REFERENCES `student` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('3', 'javaWeb该怎么学啊', 'javaWeb该怎么学啊', '2018-12-30 19:21:16', '50001', 'qinkai', '0');
INSERT INTO `message` VALUES ('4', '什么是session', 'javaWeb该怎么学啊', '2018-12-30 19:21:44', '50001', 'qinkai', '1');
INSERT INTO `message` VALUES ('5', '英语该怎么学', '英语该怎么学', '2018-12-30 19:22:18', '50005', 'qinkai', '0');
INSERT INTO `message` VALUES ('6', '英语听力怎么办？', '英语听力怎么办？???\r\n', '2018-12-30 19:23:06', '50006', 'qinkai', '0');
INSERT INTO `message` VALUES ('7', 'java有什么优点', 'java有什么优点', '2018-12-30 19:30:48', '50002', 'xiaoming', '0');
INSERT INTO `message` VALUES ('9', 'java？？？', 'java？？？？？\r\n', '2018-12-31 20:56:04', '50002', 'qinkai', '1');
INSERT INTO `message` VALUES ('10', 'java？？？？', 'java？？？？java？？？？', '2018-12-31 21:18:14', '50002', 'qinkai', '1');
INSERT INTO `message` VALUES ('11', 'fjdsajffdsajfjds', 'sfdjkajsfsajfds', '2018-12-31 21:18:35', '50001', 'qinkai', '0');
INSERT INTO `message` VALUES ('13', 'fdjasifh', 'hdfahfhdsajhfhsdah', '2019-01-02 10:36:03', '50001', 'qinkai', '0');
INSERT INTO `message` VALUES ('14', '代码货栈怎么样', '代码货栈怎么样', '2019-09-26 08:15:27', '2', 'cswork', '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `username` varchar(10) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `realname` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('cswork', 'cswork', '代码货栈');
INSERT INTO `student` VALUES ('qinkai', '123', '秦凯');
INSERT INTO `student` VALUES ('xiaogang', '123', '小刚');
INSERT INTO `student` VALUES ('xiaohong', '123', '小红');
INSERT INTO `student` VALUES ('xiaoming', '123', '小明');
INSERT INTO `student` VALUES ('xiaowang', '123', '小王');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `realname` varchar(10) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `introduction` varchar(200) DEFAULT NULL,
  `coid` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_college` (`coid`),
  CONSTRAINT `FK_college` FOREIGN KEY (`coid`) REFERENCES `college` (`coid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('dufu', '123', '杜甫', '副教授', '', '10001');
INSERT INTO `teacher` VALUES ('libai01', '123', '李白', '副教授', '', '10001');
