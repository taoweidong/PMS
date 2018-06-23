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
 - 引入Mybatis框架
 - 引入SpringMVC框架
 
