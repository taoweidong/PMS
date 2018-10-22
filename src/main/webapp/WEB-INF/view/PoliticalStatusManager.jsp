<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>政治面貌管理</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/PoliticalStatusManager.js"></script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:openPoliticalStatustAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a href="javascript:openPoliticalStatustModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a href="javascript:deletePoliticalStatust()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>

			</div>
			<div class="wu-toolbar-search">
				<label>类型标识：</label> <input name="s_PS_TYPE" id="s_PS_TYPE" class="wu-text" style="width: 100px"> <label>类型名称：</label> <input name="s_PS_Name" id="s_PS_Name" class="wu-text" style="width: 100px"> <a href="javascript:searchPoliticalStatus()" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-2" class="easyui-datagrid" toolbar="#wu-toolbar-2" fitColumns="true" pagination="true" fit="true" rownumbers="true" url="queryPoliticalStatus">
			<thead>
				<tr>
					<th field="cb" checkbox="true"></th>
					<th field="type" width="80" align="center">类型标志</th>
					<th field="name" width="90" align="center">类型名称</th>
					<th field="ext1" width="50" align="center">更新日期</th>
					<th field="ext2" width="80" align="center">备注</th>
					<th field="Ext3" width="100" hidden="true" align="center">备注3</th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- Begin of easyui-dialog -->
	<div id="wu-dialog-2" modal="true" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 400px; padding: 10px;" buttons="#dia-buttons">
		<form id="wu-form-2" method="post">
			<table>
				<tr>
					<td width="80" align="right">类型名称:</td>
					<td><input type="text" name="name" id="name" required="true" class="easyui-validatebox" class="wu-text" /></td>
				</tr>
				<tr>
					<td width="80" align="right">备注:</td>
					<td><textarea name="ext2" id="ext2" rows="6" class="wu-textarea" style="width: 260px"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:savePoliticalStatus()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closePoliticalStatusDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
