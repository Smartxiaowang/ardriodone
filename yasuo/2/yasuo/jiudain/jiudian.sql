/*
SQLyog v10.2 
MySQL - 5.5.54 : Database - jiudians
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jiudians` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jiudians`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET gbk COLLATE gbk_bin NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `status` int(2) DEFAULT '1',
  `lend_num` int(11) DEFAULT '0',
  `max_num` int(11) DEFAULT '5',
  PRIMARY KEY (`aid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

/*Data for the table `admin` */

insert  into `admin`(`aid`,`username`,`name`,`password`,`email`,`phone`,`status`,`lend_num`,`max_num`) values (1,'admin','管理员','admin','root@qq.com','13421231231',2,0,5),(6,'123','123','123','123','13421231231',1,2,5);

/*Table structure for table `history` */

DROP TABLE IF EXISTS `history`;

CREATE TABLE `history` (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `card` char(255) DEFAULT NULL,
  `jiudianname` char(255) DEFAULT NULL,
  `adminname` char(255) DEFAULT NULL,
  `username` char(255) DEFAULT NULL,
  `begintime` char(255) DEFAULT NULL,
  `endtime` char(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`hid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `history` */

insert  into `history`(`hid`,`aid`,`bid`,`card`,`jiudianname`,`adminname`,`username`,`begintime`,`endtime`,`status`) values (6,6,6,'123','123','123','123','2022-5-20','2022-5-20',2);

/*Table structure for table `jiudian` */

DROP TABLE IF EXISTS `jiudian`;

CREATE TABLE `jiudian` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(205) NOT NULL,
  `card` varchar(205) CHARACTER SET utf8 NOT NULL,
  `autho` varchar(205) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `press` varchar(205) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `borrow_user` varchar(20) DEFAULT '无',
  `borrow_time` char(255) DEFAULT '无',
  PRIMARY KEY (`bid`),
  UNIQUE KEY `ISBN` (`card`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

/*Data for the table `jiudian` */

insert  into `jiudian`(`bid`,`name`,`card`,`autho`,`num`,`press`,`type`,`borrow_user`,`borrow_time`) values (6,'123','123',NULL,-1,NULL,'大床房','无','无');

/*Table structure for table `jiudiantype` */

DROP TABLE IF EXISTS `jiudiantype`;

CREATE TABLE `jiudiantype` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

/*Data for the table `jiudiantype` */

insert  into `jiudiantype`(`tid`,`name`) values (5,'大床房'),(6,'双人房');

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL,
  `bid` int(11) DEFAULT NULL,
  `card` char(255) DEFAULT NULL,
  `jiudianname` varchar(255) DEFAULT NULL,
  `adminname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `application_time` char(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`rid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `review` */

insert  into `review`(`rid`,`aid`,`bid`,`card`,`jiudianname`,`adminname`,`username`,`application_time`,`status`) values (1,6,6,'123','123','123','123','2022-5-20',1),(2,6,6,'123','123','123','123','2022-5-20',1),(3,6,6,'123','123','123','123','2022-5-20',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
