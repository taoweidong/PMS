/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50137
Source Host           : 127.0.0.1:3306
Source Database       : pms

Target Server Type    : MYSQL
Target Server Version : 50137
File Encoding         : 65001

Date: 2017-05-02 21:07:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_administrator
-- ----------------------------
DROP TABLE IF EXISTS `t_administrator`;
CREATE TABLE `t_administrator` (
  `ADMIN_ID` varchar(50) NOT NULL,
  `ADMIN_NO` varchar(20) DEFAULT NULL,
  `ADMIN_PWD` varchar(50) DEFAULT NULL,
  `ADMIN_NAME` varchar(50) DEFAULT NULL,
  `ADMIN_PHONE` varchar(20) DEFAULT NULL,
  `Ext1` varchar(100) DEFAULT NULL COMMENT '备注',
  `Ext2` varchar(100) DEFAULT NULL COMMENT '更新时间',
  `Ext3` varchar(200) DEFAULT NULL COMMENT 'admin 普通管理员;superAdmin 超级管理员;',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储管理员信息';

-- ----------------------------
-- Records of t_administrator
-- ----------------------------
INSERT INTO `t_administrator` VALUES ('010', '100112', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '测试账户', '15745678789', '测试账户测试备注信息32453', '2017-04-28 20:41:04', 'admin');
INSERT INTO `t_administrator` VALUES ('011', 'admin', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '超级管理员', '15723562584', '测试asdadasdasdad\r\nas2189317839279', '2017-04-28 21:34:40', 'superAdmin');
INSERT INTO `t_administrator` VALUES ('20170414001', '100113', '9AF23FC3DA0A8E4B6955C63E3A50AD53', '测试管理员新增账户', '15712124545', '备注', '2017-04-26 20:25:07', 'superAdmin');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `DEP_ID` varchar(50) NOT NULL,
  `DEP_NAME` varchar(100) DEFAULT NULL,
  `DEP_LEADER` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DEP_ID`),
  KEY `FK_Reference_5` (`DEP_LEADER`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`DEP_LEADER`) REFERENCES `t_employee` (`EMP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储部门信息';

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('POS20170419205305', '营运部', '1214210136');
INSERT INTO `t_department` VALUES ('POS20170419205306', '办公部', '5464646');
INSERT INTO `t_department` VALUES ('POS20170419211357', '科技部', '123413412');

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee` (
  `EMP_NO` varchar(50) NOT NULL,
  `EMP_PWD` varchar(50) DEFAULT NULL,
  `EMP_NAME` varchar(50) DEFAULT NULL,
  `EMP_SEX` varchar(2) DEFAULT NULL COMMENT 'M-男;F-女',
  `EMP_Birthday` date DEFAULT NULL,
  `PS_ID` varchar(20) DEFAULT NULL,
  `EMP_Phone` varchar(20) DEFAULT NULL,
  `EMP_Address` varchar(200) DEFAULT NULL,
  `ext1` varchar(100) DEFAULT NULL,
  `ext2` varchar(100) DEFAULT NULL,
  `ext3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`EMP_NO`),
  KEY `FK_Reference_1` (`PS_ID`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`PS_ID`) REFERENCES `t_politicalstatus` (`PS_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储员工的基本信息';

-- ----------------------------
-- Records of t_employee
-- ----------------------------
INSERT INTO `t_employee` VALUES ('100112001', 'A9CBADCCCEAC5887D2518403369CD9AD', '李三', 'M', '2017-04-26', '123', '15769229463', '海南三亚', '测试数据', '2017-04-11', null);
INSERT INTO `t_employee` VALUES ('1001120012', 'A9CBADCCCEAC5887D2518403369CD9AD', '王海南', 'M', '2017-04-20', '101', '15789654785', '海南岛', '学习java变成345435', '2017-04-28 20:45:57', null);
INSERT INTO `t_employee` VALUES ('1001120023', 'A9CBADCCCEAC5887D2518403369CD9AD', '王海南', 'M', '2017-04-25', '103', '15789654785', '海南岛', '学习java变成', null, null);
INSERT INTO `t_employee` VALUES ('100112004', 'A9CBADCCCEAC5887D2518403369CD9AD', '陶伟东', 'M', '2017-05-04', '102', '15754645646', '阿斯达克啦简单快乐', 'dkaaskdlakl啥的就爱看打开了撒大家啊杀菌打卡啦就爱看领导大会上将爱的发咖啡', '2017-05-02 20:01:26', null);
INSERT INTO `t_employee` VALUES ('1001120088', 'A9CBADCCCEAC5887D2518403369CD9AD', '王二狗', 'F', '2017-04-11', '102', '15789654785', '海南岛4564646', '学习java变成', '2017-04-28 20:42:48', null);
INSERT INTO `t_employee` VALUES ('12121313', 'A9CBADCCCEAC5887D2518403369CD9AD', '王小坏001', 'F', '2017-03-29', null, '15323654587', '上海市浦东', '', '', '');
INSERT INTO `t_employee` VALUES ('1213210133', 'A9CBADCCCEAC5887D2518403369CD9AD', '王梦', 'F', '2017-04-04', '103', '15789564785', '测试地址', '测试地址测试地址测试地址测23424', '2017-04-17', null);
INSERT INTO `t_employee` VALUES ('1214210132', 'A9CBADCCCEAC5887D2518403369CD9AD', '测试新增', 'M', '2017-04-19', '103', '15754646545', '海南银行总部大厦', '手机看垃圾的老师看见 ', '2017-04-28 20:56:15', null);
INSERT INTO `t_employee` VALUES ('1214210135', 'A9CBADCCCEAC5887D2518403369CD9AD', '诸葛小坏', 'F', '2017-04-14', '104', '15745646546', '海秀东路31号14678686', null, '2017-04-28 20:54:28', null);
INSERT INTO `t_employee` VALUES ('1214210136', 'A9CBADCCCEAC5887D2518403369CD9AD', '李斯', 'F', '2017-03-28', '101', '15323654587', '上海市浦东', '', '2017-04-18 14:07:59', null);
INSERT INTO `t_employee` VALUES ('1214210139', 'A9CBADCCCEAC5887D2518403369CD9AD', '周晓明', 'F', '1993-07-28', '103', '15789898989', '海口市三亚', '测试', '2017-04-11', null);
INSERT INTO `t_employee` VALUES ('1214210140', 'A9CBADCCCEAC5887D2518403369CD9AD', '王二蛋', 'M', '2017-04-10', '103', '15745646545', '海口市海秀东路三号', '测试', '2017-04-18 14:08:19', null);
INSERT INTO `t_employee` VALUES ('1214210141', 'A9CBADCCCEAC5887D2518403369CD9AD', '王小坏001', 'F', '2017-03-29', null, '15323654587', '上海市浦东', '', '', '');
INSERT INTO `t_employee` VALUES ('1214210155', 'A9CBADCCCEAC5887D2518403369CD9AD', '诸葛小坏', 'F', '2017-04-19', '103', '12673627832', '啊杀菌扩大哈加快', '安徽省的空间啊', '2017-04-26 08:29:50', null);
INSERT INTO `t_employee` VALUES ('1214210188', 'A9CBADCCCEAC5887D2518403369CD9AD', '王小坏001', 'F', '2010-10-10', null, '15323654587', '上海市浦东', '', '', '');
INSERT INTO `t_employee` VALUES ('1214210199', 'A9CBADCCCEAC5887D2518403369CD9AD', '刘三毛', 'M', '1993-03-24', '103', '12367236872637', '陕西渭南', '阿什顿就卡大就拉开四季度', '2017-04-18 14:17:00', null);
INSERT INTO `t_employee` VALUES ('1214210210', 'A9CBADCCCEAC5887D2518403369CD9AD', '阿斯达', 'F', '2017-04-12', '103', '123672367', '阿萨大吉的', '安徽科技的萨克', null, null);
INSERT INTO `t_employee` VALUES ('1214210310', 'A9CBADCCCEAC5887D2518403369CD9AD', '王海伦', 'F', '1997-02-05', '101', '15756896547', '海南大学', '海南大学海南大学海南大学海南大学海南大学 海南大学海南大学 奥斯卡的来看洒落的', null, null);
INSERT INTO `t_employee` VALUES ('1214210523', 'A9CBADCCCEAC5887D2518403369CD9AD', 'daskjd', 'M', '2017-04-18', '102', '15754646545', 'asjkdlad', null, '2017-04-28 21:19:09', null);
INSERT INTO `t_employee` VALUES ('1214210888', 'C417AF4EE3DE41F3973B73561EF784D7', 'shdakj', 'M', '2017-04-19', '103', '15754654654', 'sadkadm;al三足鼎立', null, '2017-04-28 21:40:55', null);
INSERT INTO `t_employee` VALUES ('121421232', 'A9CBADCCCEAC5887D2518403369CD9AD', '王小坏2342', 'F', '2017-03-29', null, '15323654587', '上海市浦东', '', null, null);
INSERT INTO `t_employee` VALUES ('1214310101', 'A9CBADCCCEAC5887D2518403369CD9AD', '海尔', 'F', '2017-04-26', '103', '12323782378', '安徽省就看到好看', '爱上看来大家快来大家爱离开多久啊', '2017-04-18 14:08:06', null);
INSERT INTO `t_employee` VALUES ('123413412', 'A9CBADCCCEAC5887D2518403369CD9AD', '马三清', 'M', '2017-04-20', '103', '12367238223', '海口龙华区', '测试a32424324234', '2017-04-28 20:42:22', null);
INSERT INTO `t_employee` VALUES ('5464646', 'A9CBADCCCEAC5887D2518403369CD9AD', '诸葛小亮', 'F', '2017-04-20', '103', '1236278362873', '阿莎空间大', '丝带大家阿里的', '2017-04-18 14:17:04', null);

-- ----------------------------
-- Table structure for t_inductioninfo
-- ----------------------------
DROP TABLE IF EXISTS `t_inductioninfo`;
CREATE TABLE `t_inductioninfo` (
  `IND_ID` varchar(50) NOT NULL,
  `EMP_NO` varchar(10) DEFAULT NULL,
  `POS_ID` varchar(10) DEFAULT NULL,
  `IND_DATE` varchar(50) DEFAULT NULL,
  `IND_STATE` varchar(2) DEFAULT NULL COMMENT '0--在职；1--解聘',
  `IND_ENDDATE` varchar(50) DEFAULT NULL,
  `IND_Reasons` text,
  `EXT1` varchar(100) DEFAULT NULL COMMENT '00 申请中;11 已审批通过;22 审批未通过;33 未提交【员工已增加但是未提交】可删除',
  `EXT2` varchar(100) DEFAULT NULL COMMENT '审批日期',
  `EXT3` varchar(200) DEFAULT NULL COMMENT '申请类型  IN 入职申请  OUT 离职申请',
  PRIMARY KEY (`IND_ID`),
  KEY `FK_Reference_2` (`EMP_NO`),
  KEY `FK_Reference_3` (`POS_ID`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`EMP_NO`) REFERENCES `t_employee` (`EMP_NO`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`POS_ID`) REFERENCES `t_positionsinfo` (`POS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工的入职信息，职工的岗位信息等。';

-- ----------------------------
-- Records of t_inductioninfo
-- ----------------------------
INSERT INTO `t_inductioninfo` VALUES ('09EB68CB2EEB11E7BA1FF33F6331CA66', '1214210135', '01005', '2017-05-02 11:54:27', null, null, null, '33', null, 'IN');
INSERT INTO `t_inductioninfo` VALUES ('12001', '1214210136', '01003', '2017-03-28', '0', '2017-03-28', '渎职导致离职', '22', '2017-04-17 13:17:56', 'OUT');
INSERT INTO `t_inductioninfo` VALUES ('12002', '1214210135', '01001', '2017-03-28', '1', null, '在职', '22', '2017-04-17 13:17:42', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('1BCF4B862F2F11E7B9121CDE058071B6', '100112004', '01002', '2017-05-02 20:01:43', '1', null, null, '11', '2017-05-02 20:58:54', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('212F15272F2F11E7B9121CDE058071B6', '100112004', '01001', '2017-05-02 20:01:52', '0', null, null, '11', '2017-05-02 20:59:19', 'OUT');
INSERT INTO `t_inductioninfo` VALUES ('2CA26253211711E780F03B2507B36D07', '1214210135', '01002', '2017-04-14', '1', null, null, '11', '2017-05-02 20:40:55', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('2DF4037D2F3111E7B9121CDE058071B6', '100112004', '01006', '2017-05-02 20:16:32', null, null, null, '33', null, 'OUT');
INSERT INTO `t_inductioninfo` VALUES ('39233B2B2F3011E7B9121CDE058071B6', '100112004', '01003', '2017-05-02 20:09:42', '0', null, null, '11', '2017-05-02 20:31:15', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('7711504820DA11E780F03B2507B36D07', '1214210135', '01003', '2012-01-12', '1', null, '', '11', '2017-05-02 20:40:42', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('7C9184362F3011E7B9121CDE058071B6', '100112004', '01005', '2017-05-02 20:11:35', '1', null, null, '11', '2017-05-02 20:40:08', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('BC77B13A2F3011E7B9121CDE058071B6', '100112004', '01006', '2017-05-02 20:13:22', '0', null, null, '11', '2017-05-02 20:26:26', 'IN');
INSERT INTO `t_inductioninfo` VALUES ('C0C3892E2F3011E7B9121CDE058071B6', '100112004', '01003', '2017-05-02 20:13:29', '0', null, null, '11', '2017-05-02 20:40:15', 'OUT');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `NOT_ID` varchar(50) NOT NULL,
  `NOT_TITLE` varchar(50) DEFAULT NULL,
  `NOT_CONTENT` varchar(2000) DEFAULT NULL,
  `NOT_DATE` varchar(20) DEFAULT NULL,
  `NOT_AUTHOR` varchar(20) DEFAULT NULL,
  `EXT1` varchar(100) DEFAULT NULL,
  `EXT2` varchar(100) DEFAULT NULL,
  `EXT3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`NOT_ID`),
  KEY `FK_Reference_4` (`NOT_AUTHOR`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`NOT_AUTHOR`) REFERENCES `t_administrator` (`ADMIN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告发布信息表，管理员操作';

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('20170419204311', 'hadjkasd ', 'aidasdi', '2017-04-19 20:43:11', '011', null, null, null);
INSERT INTO `t_notice` VALUES ('20170427141354', 'ajdsha', 'kasldjakldjla', '2017-04-27 14:13:54', '011', null, null, null);
INSERT INTO `t_notice` VALUES ('20170427141357', '测试公告002', '杀进来就打了卡就打了卡多久', '2017-04-26 20:55:26', '011', null, null, null);

-- ----------------------------
-- Table structure for t_politicalstatus
-- ----------------------------
DROP TABLE IF EXISTS `t_politicalstatus`;
CREATE TABLE `t_politicalstatus` (
  `PS_TYPE` varchar(50) NOT NULL,
  `PS_Name` varchar(100) DEFAULT NULL,
  `Ext1` varchar(100) DEFAULT NULL,
  `Ext2` varchar(100) DEFAULT NULL,
  `Ext3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`PS_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储政治面貌数据字典';

-- ----------------------------
-- Records of t_politicalstatus
-- ----------------------------
INSERT INTO `t_politicalstatus` VALUES ('101', '党员', '2017-04-18 14:17:17', ' 12313备注1', null);
INSERT INTO `t_politicalstatus` VALUES ('102', '团员', '2017-04-18 14:17:23', '测试备注2', null);
INSERT INTO `t_politicalstatus` VALUES ('103', '共青团员', '2017-04-18 14:17:11', '345353', null);
INSERT INTO `t_politicalstatus` VALUES ('104', '预备党员', '2017-04-09', '阿达来的', null);
INSERT INTO `t_politicalstatus` VALUES ('123', '群众', '2017-04-18 14:17:28', '测试政治类型', null);

-- ----------------------------
-- Table structure for t_positionsinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_positionsinfo`;
CREATE TABLE `t_positionsinfo` (
  `POS_ID` varchar(50) NOT NULL,
  `DEP_ID` varchar(20) DEFAULT NULL,
  `POS_NAME` varchar(100) DEFAULT NULL,
  `POS_CONTENT` text,
  `POS_SALARY` decimal(10,2) DEFAULT NULL,
  `POS_ALLOWANCE` decimal(10,2) DEFAULT NULL,
  `POS_PERQUISITES` decimal(10,2) DEFAULT NULL,
  `EXT1` varchar(100) DEFAULT NULL,
  `EXT2` varchar(100) DEFAULT NULL,
  `EXT3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`POS_ID`),
  KEY `FK_Reference_6` (`DEP_ID`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`DEP_ID`) REFERENCES `t_department` (`DEP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储岗位信息。';

-- ----------------------------
-- Records of t_positionsinfo
-- ----------------------------
INSERT INTO `t_positionsinfo` VALUES ('01001', 'POS20170419205305', '普通职员', '端茶倒水', '4521.00', '1200.00', '1233.00', '备注1', '2017-04-27 13:39:58', null);
INSERT INTO `t_positionsinfo` VALUES ('01002', 'POS20170419205305', '局长', '管下属', '8000.00', '5200.00', '1200.88', '测试002', '2017-04-27 13:40:05', null);
INSERT INTO `t_positionsinfo` VALUES ('01003', 'POS20170419205305', '部门经理', '管理部门', '4512.56', '456.00', '1200.00', '测试1111', '2017-04-27 13:40:10', null);
INSERT INTO `t_positionsinfo` VALUES ('01004', 'POS20170419205305', '文书', '负责部门文职工作', '4000.00', '1200.00', '125.35', '测试001', '2017-04-27 13:40:17', null);
INSERT INTO `t_positionsinfo` VALUES ('01005', 'POS20170419205305', '测试岗位001', '测试数据001', '4500.00', '5600.00', '1200.00', '测试修改', '2017-04-11', null);
INSERT INTO `t_positionsinfo` VALUES ('01006', 'POS20170419205305', '大堂经理', '测试金额控件限制', '9999999.99', '5600.25', '5610.11', '测试', '2017-04-27 13:40:24', null);
