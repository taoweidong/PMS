<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>人事管理系统主界面</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>


</head>
<body class="easyui-layout">
	<div region="north" style="height: 80px; background-color: #E0EDFF">
		<div align="left" style="width: 70%; float: left">
			<img src="images/main.jpg" />
		</div>
		<div style="font-size: 20px; color: #8B8B8B; font-family: '楷体'; padding-top: 50px">
			<font size="3"> <strong>当前用户：</strong>【${sessionScope.user.name}】--
			<strong>角色</strong>【
			<c:choose>
					<c:when test="${sessionScope.role eq 'admin'}">
						<c:out value="管理员">
						</c:out>
					</c:when>
					<c:when test="${sessionScope.role eq 'superAdmin'}">
						<c:out value="超级管理员">
						</c:out>
					</c:when>
					<c:otherwise>
						<c:out value="普通用户">
						</c:out>
					</c:otherwise>
				</c:choose>】
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
	<div region="west" style="width: 200px; height: 500px" title="导航菜单" split="true">
		<div class="easyui-accordion">
			<div title="个人信息管理" data-options="iconCls:'icon-user'" style="padding: 10px">
				<a href="javascript:openTab('个人信息管理','persionInfoManage','icon-user-edit')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user-edit'" style="width: 150px;">个人信息管理</a>
			</div>
			<div title="部门管理" data-options="iconCls:'icon-user-business-boss'" style="padding: 10px">
				<a href="javascript:openTab('部门信息管理','departmentManage','icon-user-business-boss')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-user-business-boss'" style="width: 150px;">部门管理</a>
			</div>
			<div title="岗位管理" data-options="iconCls:'icon-report-key'" style="padding: 10px">
				<a href="javascript:openTab('岗位信息管理','positionsManage','icon-report-disk')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-report-disk'" style="width: 150px;">岗位管理</a>
			</div>
			<div title="职工管理" data-options="iconCls:'icon-user-group'" style="padding: 10px">

				<a href="javascript:openTab(' 职工信息管理','employeeManage','icon-group-edit')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-group-edit'" style="width: 150px;">职工管理</a>
			</div>
			<div title="公告管理" data-options="iconCls:'icon-bricks'" style="padding: 10px">
				<a href="javascript:openTab('公告管理','noticManager','icon-camera-edit')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-camera-edit'" style="width: 150px;">公告管理</a>
			</div>
			<div title="系统管理" data-options="iconCls:'icon-wrench'" style="padding: 10px; border: none;">

				<a href="javascript:openTab('政治面貌管理','politicalStatusManager','icon-group-edit')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-group-edit'" style="width: 150px;"> 政治面貌管理</a> 
				<a href="javascript:openTab('管理员管理','adminManager','icon-group-key')" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-group-key'" style="width: 150px;">管理员管理</a> 
				<a href="javascript:openPasswordModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-advancedsettings'" style="width: 150px;"> 修改密码</a>
				 <a href="javascript:logout()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'" style="width: 150px;"> 安全退出</a>
			</div>
		</div>

	</div>
	<div region="south" style="height: 25px;" align="center">
		版权所有 <a href="#">XXXXXXXXX</a>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 400px; height: 250px; padding: 10px 20px" modal="true" closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table cellspacing="8px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="userName" name="userName" value="XXXXXX" readonly="readonly" style="width: 200px" /></td>
				</tr>
				<tr>
					<td>原密码：</td>
					<td><input type="password" id="oldPassword" class="easyui-validatebox" maxlength="12" validType="newEqualToOld['XXX']" invalidMessage="密码验证失败" value="" required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 200px" /></td>
				</tr>
				<tr>
					<td>确认新密码：</td>
					<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" validType="equalTo['#newPassword']" invalidMessage="两次输入的密码不一致" style="width: 200px" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:modifyPassword()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closePasswordModifyDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>