<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.pms.entity.*,com.pms.util.AESUtil" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人事管理系统主界面</title>
<%
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("index.jsp");
		return;
	}

	//后期优化  在这里检查用户的密码是否为初始密码，如果是初始密码（即密码和工号相同），就强制让其修改密码
	String userName = "";
	String role = "";
	String pwd = "";
	String ro = (String) session.getAttribute("userRole");
	String userId = "";
	TEmployee userInfo = new TEmployee();
	TAdministrator adminInfo = new TAdministrator();
	//System.out.println(ro);

	if ("user".equals(ro)) {
		userInfo = (TEmployee) session.getAttribute("currentUser");
		userName = userInfo.getEmpName();
		pwd = userInfo.getEmpPwd();
		role = "普通用户";
		userId = userInfo.getEmpNo();

	} else if ("admin".equals(ro)) {
		adminInfo = (TAdministrator) session.getAttribute("currentUser");
		userName = adminInfo.getAdminName();
		pwd = adminInfo.getAdminPwd();
		role = "管理员";
		userId = adminInfo.getAdminNo();
	} else {
		adminInfo = (TAdministrator) session.getAttribute("currentUser");
		userName = adminInfo.getAdminName();
		pwd = adminInfo.getAdminPwd();
		role = "超级管理员";
		userId = adminInfo.getAdminNo();
	}
	//将数据库中的密文转换成明文
	pwd = new String(AESUtil.decrypt(AESUtil.parseHexStr2Byte(pwd)));

	//System.out.println(pwd);
%>
<!-- 引用CSS样式文件和 javaScript文件 -->
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/wu.css" />
<link rel="stylesheet" type="text/css" href="css/icon.css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/main.js"></script>

</head>
<body class="easyui-layout">
	<div region="north" style="height: 80px; background-color: #E0EDFF">
		<div align="left" style="width: 70%; float: left">
			<img src="images/main.jpg" />
		</div>
		<div
			style="font-size: 20px; color: #8B8B8B; font-family: '楷体'; padding-top: 50px">
			<font size="3"> <strong>当前用户：</strong>【<%=userName%>】--<strong>角色</strong>【<%=role%>】
			</font>
		</div>
	</div>

	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页">
				<div align="center" style="padding-top: 100px;">
					<font color="red" size="10">欢迎使用</font>
				</div>
			</div>
		</div>
	</div>
	<!-- 主页面左侧区域---导航菜单 -->
	<div region="west" style="width: 200px; height: 500px" title="导航菜单"
		split="true">
		<div class="easyui-accordion">
			<div title="个人信息管理" data-options="iconCls:'icon-user'"
				style="padding: 10px">
				<a
					href="javascript:openTab(' 个人信息管理','PersionInfoManage.jsp','icon-user-edit')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user-edit'"
					style="width: 150px;">个人信息管理</a>
			</div>
			<div title="部门管理" data-options="iconCls:'icon-user-business-boss'"
				style="padding: 10px">
				<a
					href="javascript:openTab('部门信息管理','DepartmentManage.jsp','icon-user-business-boss')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-user-business-boss'"
					style="width: 150px;">部门管理</a>
			</div>
			<div title="岗位管理" data-options="iconCls:'icon-report-key'"
				style="padding: 10px">

				<%
					if ("user".equals((String) session.getAttribute("userRole"))) {
				%>
				<a
					href="javascript:openTab('个人职位管理','ApplyInduction.jsp','icon-report-edit')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-report-edit'"
					style="width: 150px;">个人职位管理</a>
				<%
					} else if ("admin".equals((String) session.getAttribute("userRole"))
							|| "superAdmin".equals((String) session.getAttribute("userRole"))) {
				%>
				<a
					href="javascript:openTab('岗位信息管理','PositionsManage.jsp','icon-report-disk')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-report-disk'"
					style="width: 150px;">岗位管理</a> <a
					href="javascript:openTab('岗位申请审批','ApplyInductionApprove.jsp','icon-report')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-report'"
					style="width: 150px;">岗位申请审批</a>
				<%
					}
				%>
			</div>
			<%
				if ("admin".equals((String) session.getAttribute("userRole"))
						|| "superAdmin".equals((String) session.getAttribute("userRole"))) {
			%>
			<div title="职工管理" data-options="iconCls:'icon-user-group'"
				style="padding: 10px">

				<a
					href="javascript:openTab(' 职工信息管理','EmployeeManage.jsp','icon-group-edit')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-group-edit'"
					style="width: 150px;">职工管理</a>
			</div>
			<%
				}
			%>
			<div title="公告管理" data-options="iconCls:'icon-bricks'"
				style="padding: 10px">
				<a
					href="javascript:openTab(' 公告管理','NoticManager.jsp','icon-camera-edit')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-camera-edit'"
					style="width: 150px;">公告管理</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-wrench'"
				style="padding: 10px; border: none;">
				<%
					if ("admin".equals((String) session.getAttribute("userRole"))
							|| "superAdmin".equals((String) session.getAttribute("userRole"))) {
				%>
				<a
					href="javascript:openTab(' 政治面貌管理','PoliticalStatusManager.jsp','icon-group-edit')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-group-edit'"
					style="width: 150px;"> 政治面貌管理</a>
				<%
					}
				%>
				<%
					if ("superAdmin".equals((String) session.getAttribute("userRole"))
							&& "superAdmin".equals(adminInfo.getExt3())) {
				%>
				<a
					href="javascript:openTab('管理员管理','AdminManager.jsp','icon-group-key')"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-group-key'"
					style="width: 150px;">管理员管理</a>
				<%
					}
				%>
				<a href="javascript:openPasswordModifyDialog()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-advancedsettings'"
					style="width: 150px;"> 修改密码</a> <a href="javascript:logout()"
					class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon-undo'" style="width: 150px;">
					安全退出</a>
			</div>
		</div>

	</div>
	<div region="south" style="height: 25px;" align="center">
		版权所有 <a href="#">信息工程学院</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 250px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="userName" name="userName"
						value="<%=userName%>" readonly="readonly" style="width: 200px" />
					</td>
				</tr>
				<tr>
					<td>原密码：</td>
					<td><input type="password" id="oldPassword"
						class="easyui-validatebox" maxlength="12"
						validType="newEqualToOld['<%=pwd%>']" invalidMessage="密码验证失败"
						value="" required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newPassword" name="newPassword"
						class="easyui-validatebox" required="true" style="width: 200px" />
					</td>
				</tr>
				<tr>
					<td>确认新密码：</td>
					<td><input type="password" id="newPassword2"
						name="newPassword2" class="easyui-validatebox" required="true"
						validType="equalTo['#newPassword']" invalidMessage="两次输入的密码不一致"
						style="width: 200px" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:modifyPassword()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a
			href="javascript:closePasswordModifyDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>