<%@ page language="java" import="com.pms.model.Administrator"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员管理</title>

<%
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String pwd = "";
        String ro = (String) session.getAttribute("userRole");
        Administrator adminInfo = new Administrator();
        if ("admin".equals(ro) || "superAdmin".equals(ro)) {
            adminInfo = (Administrator) session.getAttribute("currentUser");
            pwd = adminInfo.getAdmin_pwd();

        }
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
<script type="text/javascript" src="js/AdminManager.js"></script>

</head>

<body>
	<div id="tb">
		<div>
			<a href="javascript:openAdminAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openAdminModifyDialog()" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:setSuperAdmin()" class="easyui-linkbutton"
				iconCls="icon-vector-add" plain="true">设置超级管理员</a> <a
				href="javascript:cancelSetSuperAdmin()" class="easyui-linkbutton"
				iconCls="icon-vector-delete" plain="true">取消超级管理员</a> <a
				href="javascript:deleteAdminInfo()" class="easyui-linkbutton"
				iconCls="icon-css-delete" plain="true">删除</a>
		</div>
		<div>
			&nbsp;登录账号：&nbsp; <input type="text" class="wu-text"
				style="width: 100px" name="s_ADMIN_NO" id="s_ADMIN_NO" size="10" />
			&nbsp;姓名：&nbsp; <input type="text" class="wu-text"
				style="width: 100px" name="s_ADMIN_NAME" id="s_ADMIN_NAME" size="20" />
			&nbsp;电话：&nbsp; <input type="text" class="wu-text"
				style="width: 100px" name="s_ADMIN_PHONE" id="s_ADMIN_PHONE"
				size="11" /> <a href="javascript:searchAdminInfo()"
				class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<table id="dg" title="个人信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" fit="true" pageSize="20" rownumbers="true"
		url="AdminServlet?method=AdminInfoList" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="ADMIN_ID" width="80" hidden="true" align="center">ID</th>
				<th field="ADMIN_NO" width="80" align="center">登录账号</th>
				<th field="ADMIN_PWD" hidden="true" width="80" align="center">密码</th>
				<th field="ADMIN_NAME" width="80" align="center">姓名</th>
				<th field="ADMIN_PHONE" width="80" align="center">电话</th>
				<th field="roleName" width="80" align="center">管理员级别</th>
				<th field="Ext1" width="80" align="center">备注</th>
				<th field="Ext2" width="100" align="center">更新日期</th>
				<th field="Ext3" width="150" hidden='true' align="center">管理员级别code</th>


			</tr>
		</thead>
	</table>

	<div id="dlg" class="easyui-dialog"
		style="width: 550px; height: 300px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="100" align="right">账号：</td>
					<td><input style="width: 150px" maxlength="10" type="text"
						name="ADMIN_NO" id="ADMIN_NO" class="easyui-validatebox"
						required="true" /></td>
					<td></td>
					<td width="100" align="right">姓名：</td>
					<td><input style="width: 150px" type="text" name="ADMIN_NAME"
						id="ADMIN_NAME" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>

					<td width="100" align="right">密码：</td>
					<td><input type="password" id="newPassword" name="newPassword"
						class="easyui-validatebox" required="true" style="width: 150px" />
					</td>
					<td></td>
					<td width="100" align="right">确认密码：</td>
					<td><input type="password" id="newPassword2"
						name="newPassword2" class="easyui-validatebox" required="true"
						validType="equalTo['#newPassword']" invalidMessage="两次输入的密码不一致"
						style="width: 150px" /></td>

				</tr>
				<tr>
					<td width="100" align="right">电话：</td>
					<td colspan="4"><input style="width: 150px" maxlength="11"
						type="text" name="ADMIN_PHONE" id="ADMIN_PHONE"
						class="easyui-validatebox" required="true" /></td>
				</tr>

				<tr>
					<td width="100" align="right">备注：</td>
					<td colspan="4"><textarea rows="4" cols="60" name="Ext1"
							id="Ext1"></textarea></td>
				</tr>

			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:saveAdmin()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
