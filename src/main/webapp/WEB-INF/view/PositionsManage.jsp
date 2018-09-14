<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>岗位信息管理</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/PositionsManage.js"></script>

</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:openPositionsAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a href="javascript:openPositionsModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a href="javascript:deletePositions()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			</div>

			<div class="wu-toolbar-search">
				<label>添加日期：</label> <input class="easyui-datebox" id="startDate" name="startDate" style="width: 100px" size="10" editable="false"> -> <input class="easyui-datebox" id="endDate" name="endDate" style="width: 100px" size="10" editable="false"> <label>部门：</label> <input class="easyui-combobox" id="DEP_ID" name="DEP_ID" data-options="panelHeight:'auto',editable:false,valueField:'DEP_ID',textField:'DEP_NAME',url:'DepartmentServlet?method=DepartmentComboboxInfo'" /> </select> <label>岗位名称：</label> <input type="text" class="wu-text" style="width: 100px" name="s_POS_NAME" id="s_POS_NAME" size="10" /> <a href="javascript:searchPositions()" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
			</div>
		</div>
		<!-- End of toolbar -->
		<table id="wu-datagrid-2" class="easyui-datagrid" toolbar="#wu-toolbar-2" fitColumns="true" pagination="true" fit="true" rownumbers="true" url="queryPositions">
			<thead>
				<tr>
					<th field="cb" checkbox="true"></th>
					<th field="id" width="80" align="center">ID</th>
					<th field="name" width="90" align="center">岗位名称</th>
					<th field="depId" width="90" hidden="true" align="center">部门编号</th>
					<th field="DEP_NAME" width="90" align="center">部门名称</th>
					<th field="DEP_LEADER" width="90" align="center">部门领导</th>
					<th field="content" width="90" align="center">岗位职责</th>
					<th field="salary" width="90" align="center">岗位工资</th>
					<th field="allowance" width="90" align="center">岗位津贴</th>
					<th field="perquisites" width="90" align="center">特殊津贴</th>
					<th field="ext1" width="50" align="center">备注</th>
					<th field="ext2" width="80" align="center">更新时间</th>
					<th field="ext3" width="100" hidden="true" align="center">备注3</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="dlg" modal="true" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 520px; padding: 10px;" closed="true" buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="100" align="right">岗位ID：</td>
					<td><input type="text" name="POS_ID" id="POS_ID" class="easyui-validatebox" required="true" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="100" align="right">岗位名称：</td>
					<td><input type="text" name="POS_NAME" id="POS_NAME" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td width="100" align="right">部&nbsp;&nbsp;&nbsp;&nbsp;门：</td>
					<td><input class="easyui-combobox" id="DEP_ID" name="DEP_ID" data-options="panelHeight:'auto',editable:false,valueField:'DEP_ID',textField:'DEP_NAME',url:'DepartmentServlet?method=DepartmentComboboxInfo'" /></td>
					<td></td>
					<td width="100" align="right">岗位薪资：</td>
					<td><input type="text" name="POS_SALARY" id="POS_SALARY" class="easyui-numberbox" precision="2" max="9999999.99" style="width: 133px" size="10" maxlength="12" required="true" /></td>
				</tr>
				<tr>
					<td width="100" align="right">岗位补贴：</td>
					<td><input type="text" name="POS_ALLOWANCE" id="POS_ALLOWANCE" class="easyui-numberbox" precision="2" max="9999999.99" style="width: 133px" size="10" maxlength="12" required="true" /></td>
					<td></td>
					<td width="100" align="right">特殊补贴：</td>
					<td><input type="text" name="POS_PERQUISITES" id="POS_PERQUISITES" class="easyui-numberbox" precision="2" max="9999999.99" style="width: 133px" size="10" maxlength="12" required="true" /></td>
				</tr>
				<tr>
					<td width="100" align="right">岗位职责：</td>
					<td colspan="4"><textarea rows="1" cols="60" name="POS_CONTENT" id="POS_CONTENT"></textarea></td>

				</tr>
				<tr>
					<td width="100" align="right">备注：</td>
					<td colspan="4"><textarea rows="7" cols="60" name="EXT1" id="EXT1"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:savePositions()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closeGradeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>