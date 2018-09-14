<%@ page language="java" import="java.util.*,java.lang.*,com.pms.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人信息管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/PersionInfoManage.js"></script>

</head>
<body style="margin: 5px;">
	<table id="dg" title="个人信息" class="easyui-datagrid" fitColumns="true" pagination="true" fit="true" pageSize="20" rownumbers="true" url="PersionInfo" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="80" hidden="true" align="center">ID</th>
				<th field="no" width="90" align="center">登录账号</th>
				<th field="pwd" hidden="true" width="50" align="center">密码</th>
				<th field="name" width="80" align="center">姓名</th>
				<th field="phone" width="100" align="center">电话</th>
				<th field="ext1" width="80" align="center">备注</th>
				<th field="ext2" width="100" align="center">更新日期</th>
				<th field="ext3" width="150" hidden="true" align="center">预备字段3</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 570px; height: 300px; padding: 10px 20px" modal="true" closed="true" buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="100" align="right">登录账号：</td>
					<td><input style="width: 150px" readonly="readonly" type="text" name="no" id="no" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td width="100" align="right">姓名：</td>
					<td><input style="width: 150px" type="text" name="name" id="name" class="easyui-validatebox" required="true" /></td>
					<td></td>
					<td width="100" align="right">电话：</td>
					<td><input style="width: 150px" maxlength="11" type="text" name="phone" id="phone" class="easyui-validatebox" required="true" /></td>
				</tr>

				<tr>
					<td width="100" align="right">备注：</td>
					<td colspan="4"><textarea rows="4" cols="60" name="ext1" id="ext1"></textarea></td>
				</tr>

			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:saveAdmin()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

</body>

</html>