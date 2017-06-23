-------------------------------如果存在旧表则进行删除，不存在则略过-------------------------------
--删除时顺序必须是以下顺序
DROP TABLE t_notice;
DROP TABLE t_inductioninfo;
DROP TABLE t_positionsinfo;
DROP TABLE t_department;
DROP TABLE t_employee;
DROP TABLE t_politicalstatus;
DROP TABLE t_administrator;

------------------------------------------------以下为创建数据库表---------------------------------------
--创建管理员表
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

--政治面貌信息表
CREATE TABLE `t_politicalstatus` (
  `PS_TYPE` varchar(50) NOT NULL,
  `PS_Name` varchar(100) DEFAULT NULL,
  `Ext1` varchar(100) DEFAULT NULL,
  `Ext2` varchar(100) DEFAULT NULL,
  `Ext3` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`PS_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储政治面貌数据字典';

--创建员工信息表
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


--创建部门表
CREATE TABLE `t_department` (
  `DEP_ID` varchar(50) NOT NULL,
  `DEP_NAME` varchar(100) DEFAULT NULL,
  `DEP_LEADER` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`DEP_ID`),
  KEY `FK_Reference_5` (`DEP_LEADER`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`DEP_LEADER`) REFERENCES `t_employee` (`EMP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='存储部门信息';

--岗位信息表
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

--员工职位信息表
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
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`POS_ID`) REFERENCES `t_positionsinfo` (`POS_ID`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`EMP_NO`) REFERENCES `t_employee` (`EMP_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工的入职信息，职工的岗位信息等。';

--公告信息表
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

-----------------------------------------------------------以下为插入数据(只有管理员基础数据)--------------------------------------------------
--密码均为：admin
INSERT INTO t_administrator (ADMIN_ID, ADMIN_NO, ADMIN_PWD, ADMIN_NAME, ADMIN_PHONE, Ext1, Ext2, Ext3) VALUES ('20170414003', '100112', '21232f297a57a5a743894a0e4a801fc3', '测试账户', '15745678789', '测试账户测试备注信息', '2017-04-26 20:24:48', 'admin');
INSERT INTO t_administrator (ADMIN_ID, ADMIN_NO, ADMIN_PWD, ADMIN_NAME, ADMIN_PHONE, Ext1, Ext2, Ext3) VALUES ('20170414002', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', '15723562584', 'aadsaklajsdklajdlkasjdlka', '2017-04-18 13:36:10', 'superAdmin');
INSERT INTO t_administrator (ADMIN_ID, ADMIN_NO, ADMIN_PWD, ADMIN_NAME, ADMIN_PHONE, Ext1, Ext2, Ext3) VALUES ('20170414001', '100113', '21232f297a57a5a743894a0e4a801fc3', '测试管理员新增账户', '15712124545', '备注', '2017-04-26 20:25:07', 'admin');
