INSERT INTO t_administrator (ADMIN_ID, ADMIN_NO, ADMIN_PWD, ADMIN_NAME, ADMIN_PHONE, Ext1, Ext2, Ext3) VALUES ('010', '100112', 'e10adc3949ba59abbe56e057f20f883e', '测试账户', '15745678789', '测试账户测试备注信息', '2017-04-26 20:24:48', 'admin');
INSERT INTO t_administrator (ADMIN_ID, ADMIN_NO, ADMIN_PWD, ADMIN_NAME, ADMIN_PHONE, Ext1, Ext2, Ext3) VALUES ('011', 'admin', '21232f297a57a5a743894a0e4a801fc3', '超级管理员', '15723562584', 'aadsaklajsdklajdlkasjdlka', '2017-04-18 13:36:10', 'superAdmin');
INSERT INTO t_administrator (ADMIN_ID, ADMIN_NO, ADMIN_PWD, ADMIN_NAME, ADMIN_PHONE, Ext1, Ext2, Ext3) VALUES ('20170414001', '100113', 'e10adc3949ba59abbe56e057f20f883e', '测试管理员新增账户', '15712124545', '备注', '2017-04-26 20:25:07', 'admin');
INSERT INTO t_department (DEP_ID, DEP_NAME, DEP_LEADER) VALUES ('POS20170419205305', '营运部', '1214210136');
INSERT INTO t_department (DEP_ID, DEP_NAME, DEP_LEADER) VALUES ('POS20170419205306', '办公部', '5464646');
INSERT INTO t_department (DEP_ID, DEP_NAME, DEP_LEADER) VALUES ('POS20170419211357', '科技部', '123413412');

INSERT INTO t_politicalstatus (PS_TYPE, PS_Name, Ext1, Ext2, Ext3) VALUES ('101', '党员', '2017-04-18 14:17:17', ' 12313备注1', null);
INSERT INTO t_politicalstatus (PS_TYPE, PS_Name, Ext1, Ext2, Ext3) VALUES ('102', '团员', '2017-04-18 14:17:23', '测试备注2', null);
INSERT INTO t_politicalstatus (PS_TYPE, PS_Name, Ext1, Ext2, Ext3) VALUES ('103', '共青团员', '2017-04-18 14:17:11', '345353', null);
INSERT INTO t_politicalstatus (PS_TYPE, PS_Name, Ext1, Ext2, Ext3) VALUES ('104', '预备党员', '2017-04-09', '阿达来的', null);
INSERT INTO t_politicalstatus (PS_TYPE, PS_Name, Ext1, Ext2, Ext3) VALUES ('123', '群众', '2017-04-18 14:17:28', '测试政治类型', null);


INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('100112001', 'e10adc3949ba59abbe56e057f20f883e', '李三', 'M', '2017-04-26', '123', '15769229463', '海南三亚', '测试数据', '2017-04-11', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1001120012', 'e10adc3949ba59abbe56e057f20f883e', '王海南', 'M', '2017-04-20', '101', '15789654785', '海南岛', '学习java变成', '2017-04-18 14:07:53', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1001120023', 'e10adc3949ba59abbe56e057f20f883e', '王海南', 'M', '2017-04-25', '103', '15789654785', '海南岛', '学习java变成', null, null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1001120088', 'e10adc3949ba59abbe56e057f20f883e', '王二狗', 'F', '2017-04-11', '102', '15789654785', '海南岛4564646', '学习java变成', '2017-04-18 14:06:37', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('12121313', 'e10adc3949ba59abbe56e057f20f883e', '王小坏001', 'F', '2017-03-29', null, '15323654587', '上海市浦东', '', '', '');
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1213210133', 'e10adc3949ba59abbe56e057f20f883e', '王梦', 'F', '2017-04-04', '103', '15789564785', '测试地址', '测试地址测试地址测试地址测23424', '2017-04-17', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210135', 'e10adc3949ba59abbe56e057f20f883e', '诸葛小坏', 'F', '2017-04-12', '104', '15745646546', '海秀东路31号14678686', '这是一个测试账户123131阿里快速的记录卡多久啦55', '2017-04-18 14:05:04', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210136', 'e10adc3949ba59abbe56e057f20f883e', '李斯', 'F', '2017-03-28', '101', '15323654587', '上海市浦东', '', '2017-04-18 14:07:59', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210139', 'e10adc3949ba59abbe56e057f20f883e', '周晓明', 'F', '1993-07-28', '103', '15789898989', '海口市三亚', '测试', '2017-04-11', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210140', 'e10adc3949ba59abbe56e057f20f883e', '王二蛋', 'M', '2017-04-10', '103', '15745646545', '海口市海秀东路三号', '测试', '2017-04-18 14:08:19', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210141', 'e10adc3949ba59abbe56e057f20f883e', '王小坏001', 'F', '2017-03-29', null, '15323654587', '上海市浦东', '', '', '');
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210155', 'e10adc3949ba59abbe56e057f20f883e', '诸葛小坏', 'F', '2017-04-19', '103', '12673627832', '啊杀菌扩大哈加快', '安徽省的空间啊', '2017-04-26 08:29:50', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210188', 'e10adc3949ba59abbe56e057f20f883e', '王小坏001', 'F', '2010-10-10', null, '15323654587', '上海市浦东', '', '', '');
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210199', 'e10adc3949ba59abbe56e057f20f883e', '刘三毛', 'M', '1993-03-24', '103', '12367236872637', '陕西渭南', '阿什顿就卡大就拉开四季度', '2017-04-18 14:17:00', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210210', 'e10adc3949ba59abbe56e057f20f883e', '阿斯达', 'F', '2017-04-12', '103', '123672367', '阿萨大吉的', '安徽科技的萨克', null, null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214210310', 'e10adc3949ba59abbe56e057f20f883e', '王海伦', 'F', '1997-02-05', '101', '15756896547', '海南大学', '海南大学海南大学海南大学海南大学海南大学 海南大学海南大学 奥斯卡的来看洒落的', null, null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('121421232', 'e10adc3949ba59abbe56e057f20f883e', '王小坏2342', 'F', '2017-03-29', null, '15323654587', '上海市浦东', '', null, null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('1214310101', 'e10adc3949ba59abbe56e057f20f883e', '海尔', 'F', '2017-04-26', '103', '12323782378', '安徽省就看到好看', '爱上看来大家快来大家爱离开多久啊', '2017-04-18 14:08:06', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('123413412', 'e10adc3949ba59abbe56e057f20f883e', '马三清', 'M', '2017-04-20', '103', '12367238223', '海口龙华区', '测试', '2017-04-18 14:16:26', null);
INSERT INTO t_employee (EMP_NO, EMP_PWD, EMP_NAME, EMP_SEX, EMP_Birthday, PS_ID, EMP_Phone, EMP_Address, ext1, ext2, ext3) VALUES ('5464646', 'e10adc3949ba59abbe56e057f20f883e', '诸葛小亮', 'F', '2017-04-20', '103', '1236278362873', '阿莎空间大', '丝带大家阿里的', '2017-04-18 14:17:04', null);

INSERT INTO t_notice (NOT_ID, NOT_TITLE, NOT_CONTENT, NOT_DATE, NOT_AUTHOR, EXT1, EXT2, EXT3) VALUES ('20170419204311', 'hadjkasd ', 'aidasdi', '2017-04-19 20:43:11', '011', null, null, null);
INSERT INTO t_notice (NOT_ID, NOT_TITLE, NOT_CONTENT, NOT_DATE, NOT_AUTHOR, EXT1, EXT2, EXT3) VALUES ('456465', '测试公告002', '杀进来就打了卡就打了卡多久', '2017-04-26 20:55:26', '011', null, null, null);

INSERT INTO t_positionsinfo (POS_ID, DEP_ID, POS_NAME, POS_CONTENT, POS_SALARY, POS_ALLOWANCE, POS_PERQUISITES, EXT1, EXT2, EXT3) VALUES ('01001', 'POS20170419205305', '普通职员', '端茶倒水', 4521.00, 1200.00, 1233.00, '备注1', '2017-04-27 13:39:58', null);
INSERT INTO t_positionsinfo (POS_ID, DEP_ID, POS_NAME, POS_CONTENT, POS_SALARY, POS_ALLOWANCE, POS_PERQUISITES, EXT1, EXT2, EXT3) VALUES ('01002', 'POS20170419205305', '局长', '管下属', 8000.00, 5200.00, 1200.88, '测试002', '2017-04-27 13:40:05', null);
INSERT INTO t_positionsinfo (POS_ID, DEP_ID, POS_NAME, POS_CONTENT, POS_SALARY, POS_ALLOWANCE, POS_PERQUISITES, EXT1, EXT2, EXT3) VALUES ('01003', 'POS20170419205305', '部门经理', '管理部门', 4512.56, 456.00, 1200.00, '测试1111', '2017-04-27 13:40:10', null);
INSERT INTO t_positionsinfo (POS_ID, DEP_ID, POS_NAME, POS_CONTENT, POS_SALARY, POS_ALLOWANCE, POS_PERQUISITES, EXT1, EXT2, EXT3) VALUES ('01004', 'POS20170419205305', '文书', '负责部门文职工作', 4000.00, 1200.00, 125.35, '测试001', '2017-04-27 13:40:17', null);
INSERT INTO t_positionsinfo (POS_ID, DEP_ID, POS_NAME, POS_CONTENT, POS_SALARY, POS_ALLOWANCE, POS_PERQUISITES, EXT1, EXT2, EXT3) VALUES ('01005', 'POS20170419205305', '测试岗位001', '测试数据001', 4500.00, 5600.00, 1200.00, '测试修改', '2017-04-11', null);
INSERT INTO t_positionsinfo (POS_ID, DEP_ID, POS_NAME, POS_CONTENT, POS_SALARY, POS_ALLOWANCE, POS_PERQUISITES, EXT1, EXT2, EXT3) VALUES ('01006', 'POS20170419205305', '大堂经理', '测试金额控件限制', 9999999.99, 5600.25, 5610.11, '测试', '2017-04-27 13:40:24', null);


INSERT INTO t_inductioninfo (IND_ID, EMP_NO, POS_ID, IND_DATE, IND_STATE, IND_ENDDATE, IND_Reasons, EXT1, EXT2, EXT3) VALUES ('12001', '1214210136', '01003', '2017-03-28', '0', '2017-03-28', '渎职导致离职', '22', '2017-04-17 13:17:56', 'OUT');
INSERT INTO t_inductioninfo (IND_ID, EMP_NO, POS_ID, IND_DATE, IND_STATE, IND_ENDDATE, IND_Reasons, EXT1, EXT2, EXT3) VALUES ('12002', '1214210135', '01001', '2017-03-28', '1', null, '在职', '22', '2017-04-17 13:17:42', 'IN');
INSERT INTO t_inductioninfo (IND_ID, EMP_NO, POS_ID, IND_DATE, IND_STATE, IND_ENDDATE, IND_Reasons, EXT1, EXT2, EXT3) VALUES ('2CA26253211711E780F03B2507B36D07', '1214210135', '01002', '2017-04-14', '', null, null, '22', '2017-04-17 14:15:12', 'IN');
INSERT INTO t_inductioninfo (IND_ID, EMP_NO, POS_ID, IND_DATE, IND_STATE, IND_ENDDATE, IND_Reasons, EXT1, EXT2, EXT3) VALUES ('7711504820DA11E780F03B2507B36D07', '1214210135', '01003', '2012-01-12', null, null, '', '22', '2017-04-17 13:00:49', 'IN');
