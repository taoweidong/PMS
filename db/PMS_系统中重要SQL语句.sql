
--检查：该用户是不是部门领导
SELECT * FROM t_department WHERE DEP_LEADER ='1214210136'

--2017年4月25日14:06:40
SELECT * FROM pms.t_administrator WHERE t_administrator.Ext3='admin' and t_administrator.ADMIN_NO='100112' and t_administrator.ADMIN_PWD='123456'

UPDATE pms.t_administrator SET t_administrator.Ext3 = 'superAdmin' WHERE t_administrator.ADMIN_ID='011'

--emp_sex AS sex, (case WHEN emp_sex='F'then '女' WHEN emp_sex='M'then '男' END ) as sexName,
SELECT ADMIN_ID,ADMIN_NO, ADMIN_PWD,ADMIN_NAME,ADMIN_PHONE,Ext1 ,Ext2,Ext3 as roleCode,(case WHEN Ext3='admin'then '管理员' WHEN Ext3='superAdmin'then '超级管理员' END ) as roleName from pms.t_administrator 


SELECT * FROM pms.t_inductioninfo

//入职申请已审批通过
SELECT * FROM t_inductioninfo WHERE EMP_NO =? and EXT3='IN' and EXT1='11'
SELECT * FROM t_inductioninfo WHERE EMP_NO ='1214210135' and EXT3='IN' and EXT1='11'


delete from pms.t_inductioninfo where t_inductioninfo.IND_ID in ( '1213210133')


--全外连接的查询方式
SELECT * FROM pms.t_department dep,pms.t_employee emp where dep.DEP_LEADER=emp.EMP_NO

--左外连接的查询方式
SELECT * FROM pms.t_department dep LEFT JOIN pms.t_employee emp ON dep.DEP_LEADER = emp.EMP_NO

--右外链接的查询
SELECT * FROM pms.t_department dep RIGHT JOIN pms.t_employee emp ON dep.DEP_LEADER=emp.EMP_NO


--查询部门是否正在使用中
SELECT * FROM pms.t_positionsinfo WHERE t_positionsinfo.DEP_ID='004'

--根据管理员删除公告信息
DELETE FROM pms.t_notice WHERE t_notice.NOT_AUTHOR IN (010)
SELECT * FROM pms.t_notice
SELECT * FROM pms.t_notice WHERE NOT_AUTHOR IN (010)

SELECT * FROM pms.t_administrator
DELETE FROM pms.t_administrator WHERE t_administrator.ADMIN_ID ='20170414001'

 SELECT count(*) FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID;
 
 SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.POS_ID='01001'
 
 SELECT pi.POS_ID,pi.POS_NAME,dep.DEP_ID,DEP_NAME,dep.DEP_LEADER,pi.POS_CONTENT,pi.POS_SALARY,pi.POS_ALLOWANCE,pi.POS_PERQUISITES,pi.EXT1,pi.EXT2,pi.EXT3 FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID 
 
delete from pms.t_positionsinfo where DEP_ID in(123131)

SELECT * FROM pms.t_inductioninfo
--查询所有入职人员信息                        00 申请中  11 已审批通过  22 审批未通过                                                                     
SELECT ii.IND_ID,emp.EMP_NO,emp.EMP_NAME,pos.POS_ID,pos.POS_NAME,ii.IND_DATE,ii.IND_STATE AS stateCode,(case WHEN ii.IND_STATE='1'then '入职' WHEN ii.IND_STATE='0'then '离职' END ) as stateName ,ii.IND_ENDDATE,ii.IND_Reasons,ii.EXT1 as approveState,(case WHEN ii.EXT1='11'then '已审批通过' WHEN ii.EXT1='00'then '申请中'  WHEN ii.EXT1='22'then '审批未通过' END ) as approveName ,ii.EXT2,ii.EXT3 FROM pms.t_inductioninfo ii,pms.t_employee emp,pms.t_positionsinfo pos WHERE ii.EMP_NO=emp.EMP_NO and ii.POS_ID=pos.POS_ID
SELECT ii.IND_ID,emp.EMP_NO,emp.EMP_NAME,pos.POS_ID,pos.POS_NAME,ii.IND_DATE,ii.IND_STATE AS stateCode,(case WHEN ii.IND_STATE='1'then '入职' WHEN ii.IND_STATE='0'then '离职' END ) as stateName ,ii.IND_ENDDATE,ii.IND_Reasons,ii.EXT1 as approveState,(case WHEN ii.EXT1='11'then '已审批通过' WHEN ii.EXT1='00'then '申请中'  WHEN ii.EXT1='22'then '审批未通过' END ) as approveName ,ii.EXT2,ii.EXT3 FROM pms.t_inductioninfo ii,pms.t_employee emp,pms.t_positionsinfo pos WHERE ii.EMP_NO=emp.EMP_NO and ii.POS_ID=pos.POS_ID and ii.EXT1='11'  limit 0,10

--管理员信息维护
SELECT * FROM pms.t_administrator WHERE admin_no = '100112'

INSERT INTO pms.t_administrator VALUES('20170414001','100113','100113','测试管理员新增账户','15712124545','备注','2012-12-12','c5665456')

SELECT * from pms.t_administrator WHERE t_administrator.ADMIN_NO = '100112' 

--普通用修改信息
SELECT * FROM pms.t_employee WHERE emp_no='1214210135'
SELECT  emp.EMP_NO,EMP_PWD,EMP_NAME,emp_sex AS sex, (case WHEN emp_sex='F'then '女' WHEN emp_sex='M'then '男' END ) as sexName,emp.EMP_Birthday,zzmm.PS_TYPE,zzmm.PS_Name,EMP_Phone, emp.EMP_Address, emp.ext1,emp.ext2 FROM pms.t_politicalstatus zzmm, pms.t_employee emp WHERE zzmm.PS_TYPE = emp.PS_ID AND emp.emp_no LIKE '%1214210135%' limit 0,20
SELECT * FROM pms.t_notice
UPDATE pms.t_notice SET t_notice.NOT_TITLE=' 测试公告01',t_notice.NOT_CONTENT='sadada',t_notice.NOT_DATE='2017-04-18 14:18:09',t_notice.NOT_AUTHOR ='admin',t_notice.EXT1=null,t_notice.EXT2=null,t_notice.EXT3=null WHERE t_notice.NOT_ID='345353'
UPDATE pms.t_notice SET t_notice.NOT_TITLE=' 测试公告01',t_notice.NOT_CONTENT='测试公告内容的数据',t_notice.NOT_DATE='2017-04-18 14:19:53',t_notice.NOT_AUTHOR ='010',t_notice.EXT1=null,t_notice.EXT2=null,t_notice.EXT3=null WHERE t_notice.NOT_ID='345353'
UPDATE pms.t_politicalstatus SET t_politicalstatus.PS_Name='团员',t_politicalstatus.Ext1='2017-04-18 14:17:23',t_politicalstatus.Ext2='测试备注2',t_politicalstatus.Ext3=null WHERE t_politicalstatus.PS_TYPE='102'

--入职申请
--ext1:00 申请中  11 已审批通过  22 审批未通过  33 未提交 为空时【员工已增加 但是未提交】可删除   ind_state  1入职  0 离职     ext2 审批日期  ext3  申请类型  IN 入职申请  OUT 离职申请
INSERT INTO pms.t_inductioninfo() VALUES(upper(replace(uuid(),'-','')),'1214210135','01001','2012-01-12',null,null,'','00','2017-01-01',null);
SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.POS_ID='01005'
--修改申请信息
UPDATE pms.t_inductioninfo SET t_inductioninfo.POS_ID='01003' WHERE t_inductioninfo.IND_ID='12001'

SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.IND_ID = '7711504820D811E345F03B2507B36D07';
delete from pms.t_inductioninfo where t_inductioninfo.IND_ID in ('7711504820D811E345F03B2507B36D07')
delete from pms.t_inductioninfo where t_inductioninfo.IND_ID in('7711504820DA11E780F03B2507B36D07','7711504820DG11E780F03B2507B36D07')

SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.POS_ID='01003' 

--查看申请是否已经提交
SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.EMP_NO='1214210135' and t_inductioninfo.EXT1 ='33'

SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.IND_ID='BB0AF387210A11E780F03B2507B36D07' and t_inductioninfo.EXT1 ='33' 
SELECT * FROM pms.t_inductioninfo WHERE t_inductioninfo.EMP_NO='1214210135' and t_inductioninfo.EXT1 ='33' 

--审批
UPDATE pms.t_inductioninfo SET t_inductioninfo.EXT3='00' WHERE t_inductioninfo.IND_ID='12001'

SELECT pi.POS_ID,pi.POS_NAME,dep.DEP_ID,DEP_NAME,dep.DEP_LEADER,pi.POS_CONTENT,pi.POS_SALARY,pi.POS_ALLOWANCE,pi.POS_PERQUISITES,pi.EXT1,pi.EXT2,pi.EXT3 FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID 

SELECT ii.IND_ID,emp.EMP_NO,emp.EMP_NAME,pos.POS_ID,pos.POS_NAME,ii.IND_DATE,ii.IND_STATE AS stateCode,(case WHEN ii.IND_STATE='1'then '入职' WHEN ii.IND_STATE='0'then '离职' END ) as stateName ,ii.IND_ENDDATE,ii.IND_Reasons,ii.EXT1 as approveState,(case WHEN ii.EXT1='11'then '已审批通过' WHEN ii.EXT1='00'then '申请中'  WHEN ii.EXT1='22'then '审批未通过' WHEN ii.EXT1='33'then '未提交' END ) as approveName ,ii.EXT2,(case WHEN ii.EXT3='IN'then '入职申请' WHEN ii.EXT3='OUT'then '离职申请' END ) as EXT3  FROM pms.t_inductioninfo ii,pms.t_employee emp,pms.t_positionsinfo pos WHERE ii.EMP_NO=emp.EMP_NO and ii.POS_ID=pos.POS_ID AND ii.EXT1!='33'   limit 0,10

-------------------------
--查询政治面貌
SELECT * FROM pms.t_politicalstatus;
--查询职员的信息
SELECT * FROM pms.t_employee;
SELECT emp.EMP_NO,EMP_PWD,EMP_NAME,emp.EMP_SEX,EMP_Birthday,zzmm.PS_TYPE,zzmm.PS_Name ,EMP_Phone,emp.EMP_Address FROM pms.t_politicalstatus zzmm,pms.t_employee emp where zzmm.PS_TYPE = emp.PS_ID;
--查询所有员工信息，并将性别转成汉字
SELECT  emp.EMP_NO,EMP_PWD,EMP_NAME,(case WHEN emp_sex='F'then '女' WHEN emp_sex='M'then '男' END ) as EMP_SEX,emp.EMP_Birthday,zzmm.PS_TYPE,zzmm.PS_Name,EMP_Phone, emp.EMP_Address, emp.ext1 FROM pms.t_politicalstatus zzmm, pms.t_employee emp WHERE zzmm.PS_TYPE = emp.PS_ID
SELECT * FROM pms.t_employee WHERE emp.PS_ID
--检查密码
SELECT t_employee.EMP_NO,t_employee.EMP_PWD,t_employee.EMP_NAME,t_employee.EMP_SEX,t_employee.EMP_Birthday,t_employee.PS_ID,t_employee.EMP_Phone,t_employee.EMP_Address FROM pms.t_employee WHERE t_employee.EMP_NO ='1214210135' and t_employee.EMP_PWD='1214210135';
--查询员工信息
SELECT  emp.EMP_NO,    EMP_PWD,    EMP_NAME,    emp.EMP_SEX, emp.EMP_Birthday, zzmm.PS_TYPE,    zzmm.PS_Name,    EMP_Phone,    emp.EMP_Address FROM    pms.t_politicalstatus zzmm,    pms.t_employee emp WHERE   zzmm.PS_TYPE = emp.PS_ID
AND emp.emp_name LIKE '%三%'
AND emp.emp_no LIKE '%0135%'
AND emp.emp_sex LIKE '%M%'
AND emp.EMP_Phone LIKE '%8%'
AND emp.EMP_Address LIKE '%海口%'
AND TO_DAYS(emp.EMP_Birthday) >= TO_DAYS('2012-12-12')
AND TO_DAYS(emp.EMP_Birthday) <= TO_DAYS('2017-12-12');
SELECT t_employee.ext1 FROM pms.t_employee WHERE t_employee.EMP_NO='12121313'
SELECT * FROM pms.t_employee WHERE t_employee.EMP_NO='1214210135';
UPDATE pms.t_employee SET t_employee.EMP_PWD='2342424234',t_employee.EMP_NAME='按时大大',t_employee.EMP_SEX='M',t_employee.EMP_Birthday ='2017-03-21',t_employee.PS_ID='102',t_employee.EMP_Phone='123233232323',t_employee.EMP_Address='哈空间的撒娇扩大',t_employee.ext1='奥斯卡的来看洒落的',t_employee.ext2=null,t_employee.ext3=null WHERE t_employee.EMP_NO='2342424234'

--插入一条职员信息
INSERT INTO pms.t_employee() VALUES("67576575","121212","王小坏001","F",'2010-10-10',null,"15323654587","上海市浦东","","","");
SELECT * FROM pms.t_employee

--删除员工信息
DELETE FROM pms.t_employee WHERE t_employee.EMP_NO IN("67576575")

SELECT * FROM pms.t_employee WHERE t_employee.EMP_NO = '67576575'

--修改员工信息
UPDATE pms.t_employee SET t_employee.EMP_PWD='101010',t_employee.EMP_NAME='11111',t_employee.EMP_SEX='M',t_employee.EMP_Birthday = now(),t_employee.PS_ID='101',t_employee.EMP_Phone='15411111111',t_employee.EMP_Address='asdhada',t_employee.ext1='adsadasd',t_employee.ext2='asad',t_employee.ext3='13213' WHERE t_employee.EMP_NO='1214210143';

--查询政治面貌
SELECT t_politicalstatus.PS_TYPE,t_politicalstatus.PS_Name FROM pms.t_politicalstatus;
SELECT t_politicalstatus.PS_TYPE,t_politicalstatus.PS_Name FROM pms.t_politicalstatus
SELECT * FROM pms.t_employee WHERE t_politicalstatus.PS_TYPE ='101'
--插入数据
INSERT INTO pms.t_politicalstatus VALUES("103","共青团员","测试11","测试11","测试11");
--修改政治面貌
UPDATE pms.t_politicalstatus SET t_politicalstatus.PS_Name='共青团员',t_politicalstatus.Ext1='2017-04-09',t_politicalstatus.Ext2='345353',t_politicalstatus.Ext3='3435' WHERE t_politicalstatus.PS_TYPE='103'
--校验管理员密码
SELECT * from pms.t_administrator WHERE t_administrator.ADMIN_NO = '100112' and t_administrator.ADMIN_PWD ='100112'

--部门表的相关操作
SELECT * FROM pms.t_department WHERE t_department.DEP_ID  and t_department.DEP_NAME and t_department.DEP_LEADER
SELECT * FROM pms.t_employee
INSERT INTO pms.t_department VALUES('003','人事部','201112');

SELECT pi.POS_ID,pi.POS_NAME,dep.DEP_ID,DEP_NAME,dep.DEP_LEADER,pi.POS_CONTENT,pi.POS_SALARY,pi.POS_ALLOWANCE,pi.POS_PERQUISITES,pi.EXT1,pi.EXT2,pi.EXT3 FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID and pi.POS_NAME like '%办公%' limit 0,10

--查询
SELECT * FROM pms.t_positionsinfo WHERE t_positionsinfo.POS_ID = '01001'
SELECT * FROM pms.t_positionsinfo WHERE t_positionsinfo.POS_NAME like '%普通%' 
--新增
INSERT INTO pms.t_positionsinfo VALUES('01002','003','经理','管理部门',6500,1200,2300,'备注1','备注2','备注3');
--修改
UPDATE pms.t_positionsinfo SET t_positionsinfo.DEP_ID='002',t_positionsinfo.POS_NAME='局长',t_positionsinfo.POS_CONTENT='管下属',t_positionsinfo.POS_SALARY=8000,t_positionsinfo.POS_ALLOWANCE=5200,t_positionsinfo.POS_PERQUISITES=1200,t_positionsinfo.EXT1='asd',t_positionsinfo.EXT2='4564',t_positionsinfo.EXT3='asdad' WHERE t_positionsinfo.POS_ID='01002'


--公告
SELECT * FROM pms.t_notice WHERE t_notice.NOT_ID like '%8%' and t_notice.NOT_TITLE like '%01%' and t_notice.NOT_CONTENT like '%内容%'
and t_notice.NOT_DATE like '%2017-03-28%' and t_notice.NOT_AUTHOR and t_notice.EXT1
--查询公告信息
SELECT notic.NOT_ID,notic.NOT_TITLE,notic.NOT_CONTENT,notic.NOT_DATE,admin.ADMIN_ID,admin.ADMIN_NAME,notic.EXT1,notic.EXT2,notic.EXT3 FROM pms.t_notice notic,pms.t_administrator admin WHERE notic.NOT_AUTHOR=admin.ADMIN_ID

SELECT notic.NOT_ID,notic.NOT_TITLE,notic.NOT_CONTENT,notic.NOT_DATE,admin.ADMIN_ID,admin.ADMIN_NAME,notic.EXT1,notic.EXT2,notic.EXT3 FROM pms.t_notice notic,pms.t_administrator admin WHERE notic.NOT_AUTHOR=admin.ADMIN_ID  AND admin.ADMIN_NAME like '%1%' limit 0,10

SELECT notic.NOT_ID,notic.NOT_TITLE,notic.NOT_CONTENT,notic.NOT_DATE,admin.ADMIN_ID,admin.ADMIN_NAME,notic.EXT1,notic.EXT2,notic.EXT3 FROM pms.t_notice notic,pms.t_administrator admin WHERE notic.NOT_AUTHOR=admin.ADMIN_ID  AND admin.ADMIN_NAME like '%测试%' limit 0,10

SELECT  count(*) as total FROM pms.t_notice;

SELECT * FROM pms.t_politicalstatus WHERE t_politicalstatus.PS_TYPE like '%10%' and t_politicalstatus.PS_Name like '%党%'

--查询
SELECT * FROM pms.t_positionsinfo WHERE t_positionsinfo.POS_NAME like '%普通%' 

SELECT pi.POS_ID,pi.POS_NAME,dep.DEP_ID,DEP_NAME,dep.DEP_LEADER,pi.POS_CONTENT,pi.POS_SALARY,pi.POS_ALLOWANCE,pi.POS_PERQUISITES,pi.EXT1,pi.EXT2,pi.EXT3 FROM pms.t_positionsinfo pi,pms.t_department dep WHERE pi.DEP_ID = dep.DEP_ID
--新增
INSERT INTO pms.t_positionsinfo VALUES('01002','003','经理','管理部门',6500,1200,2300,'备注1','备注2','备注3');
--修改
UPDATE pms.t_positionsinfo SET t_positionsinfo.DEP_ID='002',t_positionsinfo.POS_NAME='局长',t_positionsinfo.POS_CONTENT='管下属',t_positionsinfo.POS_SALARY=8000,t_positionsinfo.POS_ALLOWANCE=5200,t_positionsinfo.POS_PERQUISITES=1200,t_positionsinfo.EXT1='asd',t_positionsinfo.EXT2='4564',t_positionsinfo.EXT3='asdad' WHERE t_positionsinfo.POS_ID='01002'

