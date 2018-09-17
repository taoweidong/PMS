<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>职工信息管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/EmployeeManage.js"></script>

</head>
<body>
	<table id="dg" class="easyui-datagrid" fitColumns="true" pagination="true" fit="true" pageSize="10" rownumbers="true" url="queryEmployee" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="no" width="80" align="center">工号</th>
				<th field="name" width="90" align="center">姓名</th>
				<th field="sex" width="50" align="center">性别</th>
				<th field="birthday" width="80" align="center">出生日期</th>
				<th field="psId" width="80" align="center">政治面貌</th>
				<th field="phone" width="100" align="center">电话</th>
				<th field="address" width="150" align="center">地址</th>
				<th field="ext1" width="150" align="center">备注</th>
				<th field="ext2" width="150" align="center">更新日期</th>

			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a href="javascript:openStudentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a href="javascript:deleteStudent()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;工号：&nbsp; <input type="text" class="wu-text" style="width: 100px" name="s_EMP_NO" id="s_EMP_NO" size="10" /> &nbsp;姓名：&nbsp; <input type="text" class="wu-text" style="width: 100px" name="s_EMP_NAME" id="s_EMP_NAME" size="10" /> &nbsp;性别：&nbsp; <select class="easyui-combobox" style="width: 100px" name="s_EMP_SEX" id="s_EMP_SEX" editable="false" panelHeight="auto">
				<option value="">请选择...</option>
				<option value="M">男</option>
				<option value="F">女</option>
			</select> &nbsp;出生日期：&nbsp; <input class="easyui-datebox" id="s_bbirthday" style="width: 100px" name="s_bbirthday" size="10" editable="false" /> -> <input class="easyui-datebox" id="s_ebirthday" style="width: 100px" name="s_ebirthday" size="10" editable="false" /> &nbsp;政治面貌：&nbsp; 
			<input class="easyui-combobox" id="s_PS_TYPE" name="s_PS_TYPE" style="width: 120px;" size="10" data-options="panelHeight:'auto',editable:false,valueField:'psType',textField:'psName',url:'queryPoliticalStatusList'" /> 
			<a href="javascript:searchStudent()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" modal="true" style="width: 570px; height: 350px; padding: 10px 20px" closed="true" buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="80" align="right">工号：</td>
					<td><input type="text" name="no" id="no" class="easyui-validatebox" required="true" maxlength="10" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="80" align="right">姓名：</td>
					<td><input type="text" name="name" id="name" class="easyui-validatebox" required="true" /></td>
				</tr>
				<!-- 手动增加员工信息时不需要输入密码 ，默认密码为员工号，员工首次登陆时需强制修改密码 -->
				<tr>
					<td width="80" align="right">密码：</td>
					<td><input type="password" name="passwd" id="passwd" class="easyui-validatebox" value="" maxlength="8" required="true" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="80" align="right">确认密码：</td>
					<td><input type="password" name="passwd2" id="passwd2" class="easyui-validatebox" maxlength="8" validType="equalTo['#passwd']" invalidMessage="两次输入的密码不一致" value="" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">性别：</td>
					<td><select class="easyui-combobox" name="sex" id="sex" editable="false" panelHeight="auto" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="M">男</option>
							<option value="F">女</option>
					</select></td>
					<td></td>
					<td width="80" align="right">出生日期：</td>
					<td><input class="easyui-datebox" id="birthday" name="birthday" editable="false" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">政治面貌：</td>
					<td><input class="easyui-combobox" id="psId" name="psId" data-options=" panelHeight:'auto',editable:false,valueField:'psType',textField:'psName',url:'queryPoliticalStatusList'" /></td>
					<td></td>
					<td width="80" align="right">电话：</td>
					<td><input type="text" name="phone" id="phone" class="easyui-validatebox" required="true" maxlength="11" validType="phone" /></td>
				</tr>
				<tr>
					<td width="80" align="right">地址：</td>
					<td colspan="4"><textarea rows="1" cols="60" name="address" id="address"></textarea></td>
				</tr>
				<tr>
					<td width="80" align="right">备注：</td>
					<td colspan="4"><textarea rows="7" cols="60" name="ext1" id="ext1"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

</body>
</html>