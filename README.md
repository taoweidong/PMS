# V1.0版本
### 1. 使用技术：
  	使用MVC框架实现一个基于JavaWeb的人事管理系统
​	JavaBean+Servlet+jsp
​	前端框架使用Easyui
### 2. 开发工具：
	IDE工具：MyEclipse10.0
	数据库使用MySql5.0，图形化工具使用Navicat for MySQL 数据库设计使用PowerDesigner 15
	服务器使用：apache-tomcat-6.0.35 	
### 3. 加密方式
	使用AES进行密码加密
### 4.日志管理
	 引入Log4j日志管理插件	
### 5.请求转发模式
	 使用BaseServlet进行方法调度，目的是同一个Servlet处理不同的请求，根据method进行区分，使用Java的反射技术
### 6.数据库连接
	 引入了C3p0数据库连接池

# V2.0版本
### 引入项目管理功能
 1. 引入Maven框架进行项目管理
 2. 使用PMS.war包部署，可直接运行本项目2.0版本，数据库将连接远程Mysql数据库



# V3.0版本计划

 - 引入Spring框架，使用JdbcTemplate操作数据库，完善日志处理
   - 修改Web.xml配置文件，增加Spring的相关配置
 - 引入Mybatis框架
 - 引入SpringMVC框架

# 2018年9月1日
- 使用Mybatis代码生成器生成Model实体类
-命令：`mybatis-generator:generate`

```xml
<!--通用mapper -->
<dependency>
	<groupId>tk.mybatis</groupId>
	<artifactId>mapper</artifactId>
	<version>${mybatis.mapper}</version>
</dependency>

<!--Mybatis-generater插件 -->
<plugin>
	<groupId>org.mybatis.generator</groupId>
	<artifactId>mybatis-generator-maven-plugin</artifactId>
	<version>1.3.2</version>
	<configuration>
		<configurationFile>../PMS/src/main/resources/ModelGenerator.xml</configurationFile>
		<overwrite>true</overwrite>
		<verbose>true</verbose>
	</configuration>
	<dependencies>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.39</version>
		</dependency>
		<dependency>
			<groupId>tk.mybatis</groupId>
			<artifactId>mapper</artifactId>
			<version>3.3.9</version>
		</dependency>
	</dependencies>
</plugin>

```



# 2018年9月2日

- 增加Eclipse中Java的style文件，分别是：
  - checkstye静态检查配置文件： checkstyle.xml
  - java格式化文件模板： codeformat.xml
  - java代码注释模板： codetemplates.xml
- 整合SSM框架，测试页面导航已完成





## 注意事项
> 不对应可能会报 *Unable to compile class for JSP* 的错误

- jdk:jdk1.8.0
- tomcat: apache-tomcat-6.0.35
- STS: Version: 3.9.5.RELEASE
- maven:apache-maven-3.5.0

# 2018年9月20日
- 注意通用mapper中对于那些非数据库中的属性可以使用：@Transient进行忽略



# 2018年10月17日

> 好几周没有打开电脑开始了，工作太忙，加班回来就不想再开电脑了；最近稍微缓点应该继续开始学习了，学习不能放下，身边的人都很优秀，需要继续努力。完成最近的这个目标，将这个项目重构完，毕竟这个项目是具有纪念意义的。

- 完成员工信息显示页面，政治面貌转中文
- 完成员工信息页面删除用户信息时，检查是否在使用
- 取出员工维护时密码必输项，新增时设置默认密码为：123456，后期优化首次登陆强制提示修改密码。
- 完善岗位列表显示时，部门和领导信息显示；
- 完成岗位页面，部门下拉框数据的显示接口；
- 完成岗位页面查询功能接口以及界面显示功能；



# 2018年10月18日

> 不想加班，提前回家，学点东西，继续完成功能喽。

- 完成岗位管理增加，修改，删除功能接口，完善界面显示;
- 完成公告管理查询功能，显示发布人姓名；
- 完成公告增加，修改，删除功能；完善发布人从session中获取当前登录用户信息
- 公告功能完成发布人信息优化



# 2018年10月22日

- 完成政治面貌参数管理，增加，修改，删除，查询功能；
- 增加新增时检查，是否已经存在同名类型；
- 增加删除时检查，是否在使用；

# 2018年10月24日
- 完成职位管理界面设计

# 2018年10月25日
> 天气有些冷，就这样了，明天继续
- 完成职位管理后台查询功能的逻辑接口代码

# 2018年10月26日
- 完成申请新增接口，增加新增前检查
- 完成申请修改接口
- 完成删除记录接口,增加删除前状态检查
- 完成提交接口

# 2018年10月27日
- 完成审批申请；
- 完成修改审批；
- 完成删除功能；
- 完成三表联合查询合并结果的SQL查询；
- 完成权限状态下各个角色可以查询的页面;

# 2018年10月28日
-  完成个人信息修改之后回显新信息的问题，更新session中的数据;
- 完成密码修改功能;
- 优化主页面session消失，跳转登录页面;

# 2018年10月29日
- 升级fastjson依赖包版本，解决github预警的信息；
- 到此为止pms项目2.0重构完成，新框架采用SSM框架进行后台重构，前台采用easyui，没有进行变化
- 此项目到此结束，不会进行再次重构，预计开始新的项目，使用SpringBoot——vue前后端分离框架完成
