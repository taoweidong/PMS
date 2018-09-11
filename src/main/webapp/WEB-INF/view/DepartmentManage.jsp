<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>部门管理</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/DepartmentManage.js"></script>

</head>

<body>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:openDepartmentAddDialog()"
					class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a
					href="javascript:openDepartmentModifyDialog()"
					class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
					href="javascript:deleteDepartment()" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true">删除</a>
			</div>
			<div class="wu-toolbar-search">
				
				<label>部门名ID：</label> <input name="s_DEP_ID" id="s_DEP_ID"
					class="wu-text" style="width: 100px;"> 
				
				<label>部门名称：</label>
				<input name="s_DEP_NAME" id="s_DEP_NAME" class="wu-text"
					style="width: 100px;"> 
				
				<label>领导姓名：</label> 
					<input class="easyui-combobox" id="s_DEP_LEADER" name="s_DEP_LEADER"
					data-options="panelHeight:'auto',editable:false,valueField:'EMP_NO',textField:'EMP_NAME',url:'comboListEmployee'" />

				<a href="javascript:searchDepartment()" class="easyui-linkbutton"
					iconCls="icon-search">开始检索</a>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-2" class="easyui-datagrid"
			toolbar="#wu-toolbar-2" fitColumns="true" pagination="true"
			fit="true" rownumbers="true" url="queryDepartment">
			<thead>
				<tr>
					<th field="cb" checkbox="true"></th>
					<th field="id" width="80" align="center">部门ID</th>
					<th field="name" width="40" align="center">部门名称</th>
					<th field="leader" width="30" align="center">领导工号</th>
					<th field="empName" width="50" align="center">领导姓名</th>
					<th field="empPhone" width="50" align="center">领导电话</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="wu-dialog-2" modal="true" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 400px; padding: 10px;" buttons="#dia-buttons">
		<form id="wu-form-2" method="post">
			<table>
				<tr>
					<td width="80" align="right">部门名称:</td>
					<td><input type="text" name="DEP_NAME" id="DEP_NAME"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">领导工号:</td>
					<td><input class="easyui-combobox" id="DEP_LEADER"
						name="DEP_LEADER"
						data-options="panelHeight:'auto',editable:false,valueField:'EMP_NO',textField:'EMP_NAME',url:'EmployeeServlet?method=ComboListEmployee'" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:saveDepartment()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeDepartmentDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
