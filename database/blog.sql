/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-09-27 18:00:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  KEY `FK8ky5rrsxh01nkhctmo7d48p82` (`user_id`),
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('13', '\0', '', '1、栈（Stack）：存放的都是方法中的局部变量，方法的运行一定要在栈中运行\r\n    局部变量：方法的参数或者是方法（）内部的变量\r\n    作用域：一旦超出作用域，立刻从栈内存中消失\r\n2、堆（Heap）：凡是 new出来的，都在堆当中\r\n    堆内存里面的东西都有一个地址值：16进制\r\n3、方法区(Method Area)：存储.class相关的信息，包含方法的信息。\r\n4、本地方法栈（Native Method Stack）：与操作系统相关。（主要调用jvm系统中的功能，C语言，主要管理本地方法调用的，内存泄漏，栈溢出）\r\n5、寄存器（pc Register）：存放下一条指令的地址。与CPU相关。\r\n\r\n堆和栈的区别：\r\n    栈内存首先是一片内存区域，存储的都是局部变量，凡是定义在方法中的都是局部变量（方法外的是全局变量），for循环内部定义的也是局部变量，是先加载函数才能进行局部变量的定义，所以方法先进栈，然后再定义变量，变量有自己的作用域，一旦离开作用域，变量就会被释放。栈内存的更新速度很快，因为局部变量的生命周期都很短。\r\n    存储的是数组和对象（其实数组就是对象），凡是new建立的都是在堆中，堆中存放的都是实体（对象），实体用于封装数据，而且是封装多个（实体的多个属性），如果一个数据消失，这个实体也没有消失，还可以用，所以堆是不会随时释放的，但是栈不一样，栈里存放的都是单个变量，变量被释放了，那就没有了。堆里的实体虽然不会被释放，但是会被当成垃圾，Java有垃圾回收机制不定时的收取\r\n\r\n\r\n1.栈内存存储的是局部变量而堆内存存储的是实体；\r\n2.栈内存的更新速度要快于堆内存，因为局部变量的生命周期很短；\r\n3.栈内存存放的变量生命周期一旦结束就会被释放，而堆内存存放的实体会被垃圾回收机制不定时的回收。\r\n4.栈是连续的内存空间，堆是不连续的内存空间\r\n\r\n', '2020-06-14 14:18:49', '第一篇博客，简单介绍本人的学习历程，记录知识', 'https://picsum.photos/id/237/800/650', '原创', '', '', '\0', 'Java内存简介', '2020-06-14 14:20:15', '28', '6', '1');
INSERT INTO `t_blog` VALUES ('14', '\0', '\0', '1、栈（Stack）：存放的都是方法中的局部变量，方法的运行一定要在栈中运行\r\n    局部变量：方法的参数或者是方法（）内部的变量\r\n    作用域：一旦超出作用域，立刻从栈内存中消失\r\n2、堆（Heap）：凡是 new出来的，都在堆当中\r\n    堆内存里面的东西都有一个地址值：16进制\r\n3、方法区(Method Area)：存储.class相关的信息，包含方法的信息。\r\n4、本地方法栈（Native Method Stack）：与操作系统相关。（主要调用jvm系统中的功能，C语言，主要管理本地方法调用的，内存泄漏，栈溢出）\r\n5、寄存器（pc Register）：存放下一条指令的地址。与CPU相关。\r\n\r\n堆和栈的区别：\r\n    栈内存首先是一片内存区域，存储的都是局部变量，凡是定义在方法中的都是局部变量（方法外的是全局变量），for循环内部定义的也是局部变量，是先加载函数才能进行局部变量的定义，所以方法先进栈，然后再定义变量，变量有自己的作用域，一旦离开作用域，变量就会被释放。栈内存的更新速度很快，因为局部变量的生命周期都很短。\r\n    存储的是数组和对象（其实数组就是对象），凡是new建立的都是在堆中，堆中存放的都是实体（对象），实体用于封装数据，而且是封装多个（实体的多个属性），如果一个数据消失，这个实体也没有消失，还可以用，所以堆是不会随时释放的，但是栈不一样，栈里存放的都是单个变量，变量被释放了，那就没有了。堆里的实体虽然不会被释放，但是会被当成垃圾，Java有垃圾回收机制不定时的收取\r\n\r\n\r\n1.栈内存存储的是局部变量而堆内存存储的是实体；\r\n2.栈内存的更新速度要快于堆内存，因为局部变量的生命周期很短；\r\n3.栈内存存放的变量生命周期一旦结束就会被释放，而堆内存存放的实体会被垃圾回收机制不定时的回收。\r\n4.栈是连续的内存空间，堆是不连续的内存空间\r\n\r\n', '2020-06-14 15:36:25', '第一篇博客，简单介绍本人的学习历程，记录知识点', 'https://picsum.photos/id/10/800/650', '原创', '', '\0', '\0', 'java基础', '2020-06-14 15:36:25', '5', '6', '1');

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`),
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES ('13', '8');
INSERT INTO `t_blog_tags` VALUES ('14', '8');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `admin_comment` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`),
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', 'https://picsum.photos/id/237/100/100', '1', '2020-06-14 16:01:33', '65516489@qq.com', 'didi', '13', null, '');
INSERT INTO `t_comment` VALUES ('2', 'https://picsum.photos/id/237/100/100', '嗨', '2020-06-14 16:01:51', '65516489@qq.com', 'didi', '13', '1', '');
INSERT INTO `t_comment` VALUES ('3', 'https://picsum.photos/id/237/100/100', '使得', '2020-06-14 16:02:09', '65516489@qq.com', 'bulabula', '13', null, '');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('5', '集合');
INSERT INTO `t_tag` VALUES ('7', 'hashMap');
INSERT INTO `t_tag` VALUES ('8', 'jvm');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('6', 'JavaSE');
INSERT INTO `t_type` VALUES ('7', 'Java面试题');
INSERT INTO `t_type` VALUES ('8', '逻辑思考');
INSERT INTO `t_type` VALUES ('9', '算法');
INSERT INTO `t_type` VALUES ('10', '数据结构');
INSERT INTO `t_type` VALUES ('11', 'xixi');
INSERT INTO `t_type` VALUES ('12', 'dd');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'https://picsum.photos/id/237/100/100', '2019-11-30 20:33:43', '65516489', 'didi', 'e10adc3949ba59abbe56e057f20f883e', '1', '2019-11-30 20:34:03', 'zzx');
