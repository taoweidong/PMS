/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : pms

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-10-27 21:51:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_administrator
-- ----------------------------
DROP TABLE IF EXISTS `t_administrator`;
CREATE TABLE `t_administrator` (
  `ADMIN_ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `ADMIN_NO` varchar(20) DEFAULT NULL COMMENT '登录账号',
  `ADMIN_PWD` varchar(50) DEFAULT NULL COMMENT '密码，采用ASE加密',
  `ADMIN_NAME` varchar(50) DEFAULT NULL COMMENT '管理员姓名',
  `ADMIN_PHONE` varchar(20) DEFAULT NULL COMMENT '电话',
  `Ext1` varchar(100) DEFAULT NULL COMMENT '备注',
  `Ext2` varchar(100) DEFAULT NULL COMMENT '更新时间',
  `Ext3` varchar(200) DEFAULT NULL COMMENT 'admin 普通管理员;superAdmin 超级管理员;',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储管理员信息';

-- ----------------------------
-- Records of t_administrator
-- ----------------------------
INSERT INTO `t_administrator` VALUES ('1fba6ae328004629945fe361a736fc59', '100115', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李三三', '18293892838', '敖德萨所大大所大所多爱看的骄傲的看见爱上了肯德基爱打架埃里克的爱神的箭看拉家带口啦搭建卡丢了敬爱的卡拉数据的', '2018-09-16 23:11:48', '超级管理员');
INSERT INTO `t_administrator` VALUES ('20170414001', '100113', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '测试001ddddd', '15878909876', '测试修改2123131测试9999', '2018-09-16 23:11:01', '超级管理员');
INSERT INTO `t_administrator` VALUES ('20170414002', 'admin', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李三', '18999999999', '测试444ggggg', '2018-10-05 20:38:05', '超级管理员');
INSERT INTO `t_administrator` VALUES ('5318ce088af545d88852628fdd01f217', '100118', '9AF23FC3DA0A8E4B6955C63E3A50AD53', 'asddad', '15878909876', 'asdadasdasda', '2018-09-21 23:24:07', '超级管理员');
INSERT INTO `t_administrator` VALUES ('72b62b394e8d4cc293c98b6b26ea2aa5', '909090', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '栗子', '18293892383', '测试2222', '2018-09-16 22:25:07', '超级管理员');
INSERT INTO `t_administrator` VALUES ('c19e27174cee436cbae738929d9a7ca7', '100117', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '909090', '18239239829', '测试ppp', '2018-09-16 23:12:32', '超级管理员');
INSERT INTO `t_administrator` VALUES ('cb1cb0414d324ac5b2b9175a5d9e6ab2', '100116', '9AF23FC3DA0A8E4B6955C63E3A50AD53', 'asd', '18989892323', 'asdad', '2018-09-21 23:09:10', '普通管理员');
INSERT INTO `t_administrator` VALUES ('ed254b9528e24448924ec89e4104f3c1', '100114', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李思思', '18928392839', '测试001号2222', '2018-09-16 23:12:14', '超级管理员');
INSERT INTO `t_administrator` VALUES ('F904BCBBD18043019DA6E31FD1C2ACEE', '9090909090', '46AFBD7EA598488158E30C79DFB8D16C', '测试', '17878787878', '阿克苏加大了肯德基是两块多骄傲了扩大施蒂利克撒娇的考拉的就撒考虑到安康大师看电视剧阿卡丽打卡类设计打卡时间大啊啥的卡垃圾打开了SDK沙拉酱大', '2018-10-24 22:47:30', '普通管理员');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `DEP_ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `DEP_NAME` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `DEP_LEADER` varchar(20) DEFAULT NULL COMMENT '部门领导',
  PRIMARY KEY (`DEP_ID`),
  KEY `FK_Reference_5` (`DEP_LEADER`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`DEP_LEADER`) REFERENCES `t_employee` (`EMP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储部门信息';

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('EGOIDMBS', '交通运输部', '1001120088');
INSERT INTO `t_department` VALUES ('FS8TV3VY', '硬件部门', '1214210139');
INSERT INTO `t_department` VALUES ('IWGJI9Q9', '金融事业部', '1001120088');
INSERT INTO `t_department` VALUES ('PM31054F', 'IT事业部', '1214210139');
INSERT INTO `t_department` VALUES ('POS20170419205305', '营运部', '1214210136');
INSERT INTO `t_department` VALUES ('POS20170419205306', '办公部', '5464646');
INSERT INTO `t_department` VALUES ('POS20170419211357', '科技部', '123413412');
INSERT INTO `t_department` VALUES ('VNJWYMDD', '金融事业部', '1214210136');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `EMP_NO` varchar(50) NOT NULL COMMENT '登录账号',
  `EMP_PWD` varchar(50) DEFAULT NULL COMMENT '密码，采用ASE加密',
  `EMP_NAME` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `EMP_SEX` varchar(2) DEFAULT NULL COMMENT '性别：M-男;F-女',
  `EMP_Birthday` date DEFAULT NULL COMMENT '生日',
  `PS_ID` varchar(20) DEFAULT NULL COMMENT '政治面貌',
  `EMP_Phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `EMP_Address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `ext1` varchar(100) DEFAULT NULL COMMENT '备注',
  `ext2` varchar(100) DEFAULT NULL COMMENT '更新时间',
  `ext3` varchar(200) DEFAULT NULL COMMENT '暂未使用',
  PRIMARY KEY (`EMP_NO`),
  KEY `FK_Reference_1` (`PS_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`PS_ID`) REFERENCES `t_politicalstatus` (`PS_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储员工的基本信息';

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('100112004', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '谢园', 'F', '2018-10-09', '102', '15754649988', '西安高新区', '测试0001阿撒娇的考拉建档立卡建档立卡就阿里山打卡机了', '2018-10-07 21:53:30', null);
INSERT INTO `t_employee` VALUES ('1001120088', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '王恒旺', 'M', '2017-04-11', '103', '18989898989', '海南岛4564646123131', '学习java变成1231321312313', '2018-09-22 22:57:41', null);
INSERT INTO `t_employee` VALUES ('1214210135', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李潇潇', 'F', '2017-04-14', '104', '15745646546', '海秀东路31号14678686', '测试222', '2018-09-20 23:42:26', null);
INSERT INTO `t_employee` VALUES ('1214210136', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '张珊珊', 'F', '2018-09-03', '102', '18990908989', '上海市浦东新区uuuuuuuu', '测试咔咔咔咔咔咔扩扩扩扩扩扩扩', '2018-10-17 21:25:07', null);
INSERT INTO `t_employee` VALUES ('1214210139', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '刘三', 'M', '1993-07-28', '103', '15789898989', '海口市三亚', '测试3333', '2018-09-21 20:47:45', null);
INSERT INTO `t_employee` VALUES ('1214210140', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '靳嘉', 'M', '2017-04-10', '103', '15745646545', '海口市海秀东路三号', '测试', '2017-04-18 14:08:19', null);
INSERT INTO `t_employee` VALUES ('1214210141', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '任伟', 'F', '2017-03-29', '103', '15323654587', '上海市浦东', '测试999', '2018-09-21 20:47:22', null);
INSERT INTO `t_employee` VALUES ('1214210155', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李三三', 'M', '2017-04-19', '103', '12673627832', '啊杀菌扩大哈加快', '安徽省的空间啊', '2017-04-26 08:29:50', null);
INSERT INTO `t_employee` VALUES ('1214210210', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '姜思思', 'F', '2017-04-12', '103', '18978767656', '阿萨大吉的', '安徽科技的萨克', '2018-09-21 20:48:26', null);
INSERT INTO `t_employee` VALUES ('1214210310', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '丽丽', 'F', '1997-02-05', '101', '15756896547', '海南大学', '测试0099', '2018-09-21 20:44:30', null);
INSERT INTO `t_employee` VALUES ('1214210888', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李四', 'M', '2017-04-19', '103', '15754654654', 'sadkadm;al三足鼎立', null, '2017-04-28 21:40:55', null);
INSERT INTO `t_employee` VALUES ('123413412', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '杨娜一', 'F', '2017-04-20', '103', '12367238223', '海口龙华区', '测试a32424324234', '2017-04-28 20:42:22', null);
INSERT INTO `t_employee` VALUES ('1312210144', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '马晓', 'M', '2017-04-26', '123', '15769229463', '海南三亚', '测试数据', '2018-09-21 20:49:31', null);
INSERT INTO `t_employee` VALUES ('1312210145', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '李月', 'M', '2017-04-20', '101', '15789654785', '海南岛', '学习java变成345435', '2017-04-28 20:45:57', null);
INSERT INTO `t_employee` VALUES ('1312210146', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '刘永涛', 'M', '2017-04-25', '103', '15789654785', '海南岛', '学习java变成大萨达撒多', '2018-10-17 21:51:33', null);
INSERT INTO `t_employee` VALUES ('5464646', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '马小妹', 'F', '2017-04-20', '103', '1236278362873', '阿莎空间大', '丝带大家阿里的', '2017-04-18 14:17:04', null);
INSERT INTO `t_employee` VALUES ('9000000', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '六三2', 'M', '2018-09-20', '102', '15767889870', '渭南222', '备注信息6662222', '2018-09-21 22:07:59', null);
INSERT INTO `t_employee` VALUES ('9999999999', 'A9CBADCCCEAC5887D2518403369CD9AD', '99', 'M', '2018-10-03', '102', '15723562584', '西安市高新科技八路', '删掉了卡机了的空间阿来得及案例的沙拉的几率', '2018-10-17 21:51:13', null);

-- ----------------------------
-- Table structure for t_inductioninfo
-- ----------------------------
DROP TABLE IF EXISTS `t_inductioninfo`;
CREATE TABLE `t_inductioninfo` (
  `IND_ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `EMP_NO` varchar(100) DEFAULT NULL COMMENT '员工工号',
  `POS_ID` varchar(100) DEFAULT NULL COMMENT '职位ID',
  `IND_DATE` datetime DEFAULT NULL COMMENT '申请时间',
  `IND_STATE` varchar(2) DEFAULT NULL COMMENT '状态：0--在职；1--解聘',
  `IND_ENDDATE` datetime DEFAULT NULL COMMENT '离职时间',
  `IND_Reasons` text COMMENT '离职原因',
  `EXT1` varchar(100) DEFAULT NULL COMMENT '申请状态：00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除',
  `EXT2` varchar(100) DEFAULT NULL COMMENT '审批日期',
  `EXT3` varchar(200) DEFAULT NULL COMMENT '申请类型  IN 入职申请  OUT 离职申请',
  PRIMARY KEY (`IND_ID`),
  KEY `FK_Reference_2` (`EMP_NO`),
  KEY `FK_Reference_3` (`POS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工的入职信息，职工的岗位信息等。';

-- ----------------------------
-- Records of t_inductioninfo
-- ----------------------------
INSERT INTO `t_inductioninfo` VALUES ('12001', '1214210136', '01003', '2017-03-28 00:00:00', '0', '2017-03-28 00:00:00', '渎职导致离职', '22', '2017-04-17 13:17:56', 'OUT');
INSERT INTO `t_inductioninfo` VALUES ('478110572', '20170414002', '01002', '2018-10-26 22:40:29', null, null, null, '33', null, 'IN');
INSERT INTO `t_inductioninfo` VALUES ('530248724', '1214210136', '01004', '2018-10-27 21:40:05', null, null, null, '33', null, 'IN');
INSERT INTO `t_inductioninfo` VALUES ('704242818', '20170414002', '01003', '2018-10-27 21:04:10', null, null, null, '33', null, 'IN');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `NOT_ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `NOT_TITLE` varchar(50) DEFAULT NULL COMMENT '标题',
  `NOT_CONTENT` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `NOT_DATE` datetime DEFAULT NULL COMMENT '添加时间',
  `NOT_AUTHOR` varchar(20) DEFAULT NULL COMMENT '作者',
  `EXT1` varchar(100) DEFAULT NULL COMMENT '暂未使用',
  `EXT2` varchar(100) DEFAULT NULL COMMENT '暂未使用',
  `EXT3` varchar(200) DEFAULT NULL COMMENT '暂未使用',
  PRIMARY KEY (`NOT_ID`),
  KEY `FK_Reference_4` (`NOT_AUTHOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告发布信息表，管理员操作';

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('111150502', '测试新增', 'asdasdsdsad', '2018-10-18 21:43:50', '20170414002', null, null, null);
INSERT INTO `t_notice` VALUES ('128315917', 'adadad', 'dasdavzxzvzv', '2018-10-18 21:43:56', '20170414002', null, null, null);
INSERT INTO `t_notice` VALUES ('612699335', '阿打算撒', 'saddad', '2018-10-18 21:37:29', '20170414002', null, null, null);
INSERT INTO `t_notice` VALUES ('620768883', '测试新增', '测试新增测试新增测试新增测试新增测试新增', '2018-10-18 21:30:36', null, null, null, null);
INSERT INTO `t_notice` VALUES ('732796458', '阿达大', 'adsadsad', '2018-10-18 21:29:55', '1214210141', null, null, null);
INSERT INTO `t_notice` VALUES ('863663178', 'asdadsad', 'ddddddddddddd', '2018-10-18 21:30:50', '1312210144', null, null, null);

-- ----------------------------
-- Table structure for t_politicalstatus
-- ----------------------------
DROP TABLE IF EXISTS `t_politicalstatus`;
CREATE TABLE `t_politicalstatus` (
  `PS_TYPE` varchar(50) NOT NULL COMMENT '政治面貌ID',
  `PS_Name` varchar(100) DEFAULT NULL COMMENT '政治面貌名称',
  `Ext1` varchar(100) DEFAULT NULL COMMENT '更新时间',
  `Ext2` varchar(100) DEFAULT NULL COMMENT '备注',
  `Ext3` varchar(200) DEFAULT NULL COMMENT '暂未使用',
  PRIMARY KEY (`PS_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储政治面貌数据字典';

-- ----------------------------
-- Records of t_politicalstatus
-- ----------------------------
INSERT INTO `t_politicalstatus` VALUES ('101', '党员', '2018-10-22 23:11:28', '测试一下修改', null);
INSERT INTO `t_politicalstatus` VALUES ('102', '团员', '2017-04-18 14:17:23', '测试备注2', null);
INSERT INTO `t_politicalstatus` VALUES ('103', '共青团员', '2018-10-22 23:21:06', '测试', null);
INSERT INTO `t_politicalstatus` VALUES ('104', '预备党员', '2018-10-22 23:11:13', '测试修改功能', null);
INSERT INTO `t_politicalstatus` VALUES ('123', '群众', '2017-04-18 14:17:28', '测试政治类型', null);
INSERT INTO `t_politicalstatus` VALUES ('900', '部长', '2018-10-22 23:09:55', '测试新增功能', null);
INSERT INTO `t_politicalstatus` VALUES ('SFWAUPDO', '测试一号', '2018-10-24 22:33:31', '刷卡了肯德基案例的开始', null);

-- ----------------------------
-- Table structure for t_positionsinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_positionsinfo`;
CREATE TABLE `t_positionsinfo` (
  `POS_ID` varchar(50) NOT NULL COMMENT '唯一标识',
  `DEP_ID` varchar(20) DEFAULT NULL COMMENT '部门ID',
  `POS_NAME` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `POS_CONTENT` text COMMENT '岗位职责',
  `POS_SALARY` decimal(10,2) DEFAULT NULL COMMENT '岗位基本工资',
  `POS_ALLOWANCE` decimal(10,2) DEFAULT NULL COMMENT '岗位津贴',
  `POS_PERQUISITES` decimal(10,2) DEFAULT NULL COMMENT '特殊津贴',
  `EXT1` varchar(100) DEFAULT NULL COMMENT '备注',
  `EXT2` datetime DEFAULT NULL COMMENT '更新时间',
  `EXT3` varchar(200) DEFAULT NULL COMMENT '暂未使用',
  PRIMARY KEY (`POS_ID`),
  KEY `FK_Reference_6` (`DEP_ID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`DEP_ID`) REFERENCES `t_department` (`DEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储岗位信息。';

-- ----------------------------
-- Records of t_positionsinfo
-- ----------------------------
INSERT INTO `t_positionsinfo` VALUES ('01001', 'POS20170419205305', '普通职员', '端茶倒水', '4521.00', '1200.00', '1233.00', '备注1爱好的金卡是的哈卡接收到很快就阿萨德', '2018-10-22 22:33:20', null);
INSERT INTO `t_positionsinfo` VALUES ('01002', 'IWGJI9Q9', '实施顾问', '管下属sdadsadadasd', '77700.00', '9900.00', '1200.88', '测试002埃里克森教大家的萨达克拉建档立卡', '2018-10-25 23:05:15', null);
INSERT INTO `t_positionsinfo` VALUES ('01003', 'EGOIDMBS', '部门经理', '管理部门asda', '4512.56', '456.00', '1200.00', '测试1111adsadasdsadadad', '2018-10-18 20:09:45', null);
INSERT INTO `t_positionsinfo` VALUES ('01004', 'POS20170419205305', '文书', '负责部门文职工作', '4000.00', '1200.00', '125.35', '测试001', '2018-10-17 13:40:17', null);
INSERT INTO `t_positionsinfo` VALUES ('01005', 'POS20170419205305', '测试岗位', '测试数据001', '4500.00', '5600.00', '1200.00', '测试修改', '2018-10-25 23:05:05', null);
INSERT INTO `t_positionsinfo` VALUES ('01006', 'FS8TV3VY', '大堂经理', '测试金额控件限制', '324.89', '5600.25', '5610.11', '测试测试9090909900搭建了卡萨丁', '2018-10-18 20:13:19', null);
