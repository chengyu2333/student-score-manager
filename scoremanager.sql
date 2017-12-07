# Host: localhost  (Version: 5.5.40)
# Date: 2016-06-13 10:29:19
# Generator: MySQL-Front 5.3  (Build 4.120)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "admin"
#

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `number` varchar(8) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `power` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=gbk;

#
# Data for table "admin"
#

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'00001','程宇','123',2,'858134843@qq.com'),(2,'123','李兴福','123',1,'1'),(4,'00002','哈啊嗯','123456',1,'858@qq.com'),(6,'00003','03','123456',2,''),(7,'0','0','0',0,'0'),(8,'1','1','1',0,'1'),(9,'2','2','2',0,''),(10,'3','3','3',0,'');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

#
# Structure for table "classes"
#

DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `teacher` varchar(8) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;

#
# Data for table "classes"
#

/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'软件三班','123'),(2,'计应一班','123'),(17,'软件二班','00001'),(18,'软件一班','00002'),(20,'班级1','00001');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;

#
# Structure for table "course"
#

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `subject` varchar(64) NOT NULL,
  `credit` int(2) DEFAULT NULL,
  `term` varchar(32) NOT NULL,
  `teacher` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

#
# Data for table "course"
#

/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'java程序设计',5,'2015上','123'),(2,'英语',5,'2016上','123'),(4,'haha',3,'2015上','00001'),(5,'java程序设计',5,'2016上','123');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;

#
# Structure for table "course_classes"
#

DROP TABLE IF EXISTS `course_classes`;
CREATE TABLE `course_classes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `course` int(5) NOT NULL DEFAULT '0',
  `classes` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

#
# Data for table "course_classes"
#

/*!40000 ALTER TABLE `course_classes` DISABLE KEYS */;
INSERT INTO `course_classes` VALUES (1,1,1),(2,2,1),(3,2,2);
/*!40000 ALTER TABLE `course_classes` ENABLE KEYS */;

#
# Structure for table "log"
#

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `person` varchar(8) NOT NULL DEFAULT '',
  `opreate` varchar(32) NOT NULL,
  `target` varchar(32) NOT NULL,
  `oldvalue` varchar(128) NOT NULL,
  `newvalue` varchar(128) NOT NULL,
  `time` datetime NOT NULL,
  `effect` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

#
# Data for table "log"
#

/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'admin','修改','成绩','80','90','2106-06-07 16:00:50',0),(2,'admin','修改','成绩','80','90','2016-06-07 16:01:21',0),(3,'程宇','删除','班级','19','','2016-06-13 09:10:19',0),(4,'程宇','添加','班级','','banji','2016-06-13 09:10:24',0),(5,'程宇','更新','班级','20','班级','2016-06-13 09:16:19',1),(6,'程宇','更新','班级','20','班级','2016-06-13 09:26:58',1),(7,'程宇','更新','班级','20','班级1','2016-06-13 09:45:03',1);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;

#
# Structure for table "score"
#

DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `number` varchar(16) NOT NULL,
  `course` int(5) NOT NULL,
  `score` float(4,1) NOT NULL DEFAULT '0.0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=gbk;

#
# Data for table "score"
#

/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,'201510014124',1,88.0),(11,'123',1,50.0);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;

#
# Structure for table "student"
#

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `number` varchar(16) NOT NULL,
  `password` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `sex` varchar(1) NOT NULL DEFAULT '0',
  `classes` int(5) NOT NULL,
  `idcard` varchar(18) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

#
# Data for table "student"
#

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'201510014124','123456','程宇','男',1,'372924199607101817','17864127312','你猜。。。。'),(2,'123','123','123','女',2,'233','233',''),(4,'444','123456','444','男',2,'444','444','44');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
