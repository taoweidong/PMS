# V1.0版本
### 1. 使用技术：
  	使用MVC框架实现一个基于JavaWeb的人事管理系统
	JavaBean+Servlet+jsp
	前端框架使用Easyui
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





# 注意事项
> 不对应可能会报 *Unable to compile class for JSP* 的错误

- jdk:jdk1.8.0
- tomcat: apache-tomcat-6.0.35
- STS: Version: 3.9.5.RELEASE
- maven:apache-maven-3.5.0

# 2018年9月20日
- 注意通用mapper中对于那些非数据库中的属性可以使用：@Transient进行忽略

