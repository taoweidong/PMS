<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>入职审批</title>

<%
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("index.jsp");
		return;
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
<script type="text/javascript" src="js/ApplyInductionApprove.js"></script>


</head>

<body>
	<div class="easyui-layout" data-options="fit:true">
		<!-- Begin of toolbar -->
		<div id="wu-toolbar-2">
			<div class="wu-toolbar-button">
				<a href="javascript:openApplyInductionAddDialog()"
					class="easyui-linkbutton" iconCls="icon-add" plain="true">审批申请</a>
				<a href="javascript:openApproveInductionModifyDialog()"
					class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改审批</a>
				<a href="javascript:deleteInduction()" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true">删除</a>
			</div>

			<div class="wu-toolbar-search">
				<label>申请日期：</label> <input class="easyui-datebox" id="startDate"
					name="startDate" style="width: 100px" size="10" editable="false">
				-> <input class="easyui-datebox" id="endDate" name="endDate"
					style="width: 100px" size="10" editable="false"> <label>工号：</label>
				<input class="easyui-combobox" id="s_EMP_NO" name="s_EMP_NO"
					data-options="panelHeight:'auto',editable:false,valueField:'EMP_NO',textField:'EMP_NAME',url:'EmployeeComboList'" />
				</select> <label>审批状态：</label> <select class="easyui-combobox"
					style="width: 100px" name="s_approveState" id="s_approveState"
					editable="false" panelHeight="auto">
					<option value="">请选择...</option>
					<option value="00">申请中</option>
					<option value="11">审批已通过</option>
					<option value="22">审批未通过</option>
				</select> <label>在职状态：</label> <select class="easyui-combobox"
					style="width: 100px" name="s_IND_STATE" id="s_IND_STATE"
					editable="false" panelHeight="auto">
					<option value="">请选择...</option>
					<option value="1">入职</option>
					<option value="0">离职</option>
				</select> <label>岗位名称：</label> <input id="s_POS_NAME" name="s_POS_NAME"
					class="wu-text" style="width: 100px;"> <a
					href="javascript:searchApplyInduction()" class="easyui-linkbutton"
					iconCls="icon-search">开始检索</a>
			</div>
		</div>

		<!-- End of toolbar -->
		<table id="wu-datagrid-2" class="easyui-datagrid"
			toolbar="#wu-toolbar-2" fitColumns="true" pagination="true"
			fit="true" rownumbers="true"
			url="ApplyInductionServlet?method=AdminApproveInfoList">
			<thead>
				<tr>
					<th field="cb" checkbox="true"></th>
					<th field="IND_ID" hidden="true" width="80" align="center">ID</th>
					<th field="EMP_NO" width="90" align="center">员工工号</th>
					<th field="EMP_NAME" width="50" align="center">员工姓名</th>
					<th field="POS_ID" hidden="true" width="80" align="center">岗位编号</th>
					<th field="POS_NAME" width="100" align="center">岗位名称</th>
					<th field="IND_DATE" width="100" align="center">申请日期</th>
					<th field="IND_STATE" hidden="true" width="100" align="center">申请状态</th>
					<th field="stateName" width="100" align="center">员工状态</th>
					<th field="IND_ENDDATE" width="100" align="center">离职时间</th>
					<th field="IND_Reasons" width="100" align="center">离职原因</th>
					<th field="EXT1" width="100" hidden="true" align="center">审批状态</th>
					<th field="approveName" width="100" align="center">审批状态</th>
					<th field="EXT2" width="100" align="center">审批日期</th>
					<th field="typeName" width="100" align="center">申请类别</th>
					<th field="EXT3" width="100" hidden="true" align="center">申请类别code</th>

				</tr>
			</thead>
		</table>
	</div>
	<!-- Begin of easyui-dialog -->
	<div id="wu-dialog-2" class="easyui-dialog" modal="true"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 500px; padding: 10px;" buttons="#dia-buttons">
		<form id="wu-form-2" method="post">
			<table>
				<tr>
					<td width="120" align="right">审批操作:</td>
					<td><select class="easyui-combobox" style="width: 100px"
						name="EXT1" id="EXT1" editable="false" panelHeight="auto">
							<option value="">请选择...</option>
							<option value="11">审批通过</option>
							<option value="22">审批未通过</option>
							<option value="00">审批中</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:ApproveInduction()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeNoticesDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
