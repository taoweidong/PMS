<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>公告管理</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/NoticManager.js"></script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:openNoticesAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a href="javascript:openNoticesModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a href="javascript:deleteNotices()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			</div>
			<div class="wu-toolbar-search">
				<label>更新日期：</label> <input class="easyui-datebox" id="startDate" name="startDate" style="width: 100px" size="10" editable="false"> -> <input class="easyui-datebox" id="endDate" name="endDate" style="width: 100px" size="10" editable="false">
				<!-- 	<label>添加人：</label>
				<input class="easyui-combobox" id="DEP_LEADER" name="DEP_LEADER"
					data-options="panelHeight:'auto',editable:false,valueField:'EMP_NO',textField:'EMP_NAME',url:'EmployeeComboList'" />
 				-->
				<label>标题：</label> <input id="s_NOT_TITLE" name="s_NOT_TITLE" class="wu-text" style="width: 100px;"> <label>发布人：</label> <input id="s_ADMIN_NAME" name="s_ADMIN_NAME" class="wu-text" style="width: 100px;"> <a href="javascript:searchNotices()" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-2" class="easyui-datagrid" toolbar="#wu-toolbar-2" fitColumns="true" pagination="true" fit="true" rownumbers="true" url="queryNotice">
			<thead>
				<tr>
					<th field="cb" checkbox="true"></th>
					<th field="id" width="80" align="center">ID</th>
					<th field="title" width="90" align="center">标题</th>
					<th field="content" width="100" align="center">内容</th>
					<th field="date" width="80" align="center">更新日期</th>
					<th field="author" width="100" hidden="true" align="center">发布人ID</th>
					<th field="ADMIN_NAME" width="100" align="center">发布人</th>
					<th field="EXT1" width="100" hidden="true" align="center">备注</th>
					<th field="EXT2" width="100" hidden="true" align="center">备注3</th>
					<th field="EXT3" width="100" hidden="true" align="center">备注3</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- Begin of easyui-dialog -->
	<div id="wu-dialog-2" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" modal="true" style="width: 400px; padding: 10px;" buttons="#dia-buttons">
		<form id="wu-form-2" method="post">
			<table>
				<tr>
					<td align="right">标 题:</td>
					<td><input type="text" name="title" id="title" class="wu-text" /></td>
				</tr>
				<tr>
					<td valign="top" align="right">内 容:</td>
					<td><textarea name="content" id="content" rows="6" class="wu-textarea" style="width: 260px"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:NoticeSave()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closeNoticesDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
