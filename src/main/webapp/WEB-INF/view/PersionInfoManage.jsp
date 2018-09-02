<%@ page language="java"
	import="java.util.*,java.lang.*,com.pms.entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息管理</title>
<%
	if (session.getAttribute("currentUser") == null) {
		response.sendRedirect("index.jsp");
		return;
	}

	String pwd = "";
	String ro = (String) session.getAttribute("userRole");

	if ("user".equals(ro)) {
		TEmployee userInfo = (TEmployee) session.getAttribute("currentUser");
		pwd = userInfo.getEmpPwd();

	} else if ("admin".equals(ro)) {
		TAdministrator adminInfo = (TAdministrator) session.getAttribute("currentUser");
		pwd = adminInfo.getAdminPwd();

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
<script type="text/javascript">
	/**
	 *功能：修改密码 时 校验旧密码是否在正确
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		newEqualToOld : {
			validator : function(value, param) {
				console.log(param[0] + "------" + value);
				return param[0] == value;
			},
			message : '字段不匹配'
		}
	});
	/**
	 *功能：校验第一次输入的密码和第二次修改的密码是否一致
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		equalTo : {
			validator : function(value, param) {
				return $(param[0]).val() == value;
			},
			message : '字段不匹配'
		}
	});

	var url;
	//查询方法--easyui封装的方法，从table表中进行筛选数据
	function searchStudent() {
		console.log($('#s_EMP_SEX').combobox('getValue'));
		$('#dg').datagrid('load', {
			EMP_NO : $('#s_EMP_NO').val(),
			EMP_NAME : $('#s_EMP_NAME').val(),
			EMP_SEX : $('#s_EMP_SEX').combobox('getValue'),
			bbirthday : $('#s_bbirthday').datebox('getValue'),
			ebirthday : $('#s_ebirthday').datebox('getValue'),
			PS_TYPE : $('#s_PS_TYPE').combobox('getValue')
		});
	}
	//删除员工信息
	function deleteStudent() {
		var selectedRows = $('#dg').datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for (var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].EMP_NO);
		}
		var ids = strIds.join(",");
		//alert(ids);
		$.messager.confirm("系统提示", "您确定要删除这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("studentDelete", {
					delIds : ids
				}, function(result) {
					//此处已经指定以JSON运行响应，无需再进行转换
					if (result.success) {
						$.messager.alert("系统提示", "您已经成功删除了<font color=red>"
								+ result.delNums + "</font>条数据！");
						$('#dg').datagrid('reload');
					} else {
						$.messager.alert("系统提示", result.errorMsg);
					}
				}, "json");
			}
		});
	}

	function openStudentAddDialog() {
		$('#dlg').dialog('open').dialog("setTitle", "添加职工信息");
		url = "EmployeeSave";
	}
	//保存修改的和新增的员工信息
	function saveStudent() {
		$('#fm').form("submit", {
			url : url,
			onSubmit : function() {
				if ($('#EMP_SEX').combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择性别");
					return false;
				}
				if ($('#PS_TYPE').combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择所属岗位");
					return false;
				}
				return $(this).form("validate");
			},
			success : function(result) {
				//把JSON对象转为javascript对象
				var result = eval('(' + result + ')');
				console.log(result);
				if (result.success) {
					$.messager.alert("系统提示", "更新成功,，下一次登录生效！");
					resetValue();
					$('#dlg').dialog("close");//关闭dialog
					$('#dg').datagrid("reload");//重新加载表格
				} else {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				}
			}
		});
	}

	function saveAdmin() {
		$('#fm').form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				//把JSON对象转为javascript对象
				var result = eval('(' + result + ')');
				console.log(result);
				if (result.success) {
					$.messager.alert("系统提示", "更新成功,，下一次登录生效！");
					resetValue();
					$('#dlg').dialog("close");//关闭dialog
					$('#dg').datagrid("reload");//重新加载表格
				} else {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				}
			}
		});
	}

	//清空表单
	function resetValue() {
		$('#EMP_NO').val("");
		$('#EMP_NAME').val("");
		$('#EMP_SEX').combobox('setValue', "");
		$('#EMP_Birthday').datebox('setValue', "");
		$('#PS_TYPE').combobox('setValue', "");
		$('#EMP_Phone').val("");
		$('#EMP_Address').val("");
		$('#ext1').val("");

	}
	function closeStudentDialog() {
		$('#dlg').dialog('close');//关闭对话框
		resetValue();//清空
	}
	//打开修改员工信息的弹窗，并把选中的员工信息塞进去
	function openStudentModifyDialog() {
		//获取选中的行 getSelections 是EasyUI的一个属性 
		//含义为：取名称为 dg 表格中所有选中行
		var selectedRows = $('#dg').datagrid('getSelections');
		//判断是否选中一条数据，为空或者多余一条均失败
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择要一条要编辑的数据！");
			return;
		}
		//获取选中的员工信息
		var row = selectedRows[0];
		//打开编辑员工信息的页面 并设置标题
		$('#dlg').dialog("open").dialog("setTitle", "编辑职工信息");
		//把选中行的数据加载到弹出的表单信息中  即把修改编辑的数据塞到表单中
		$('#fm').form('load', row);
<%if ("user".equals((String) session.getAttribute("userRole"))) {%>
	url = "EmployeeSave?saveFlag=update&EMP_NO=" + row.EMP_NO;
<%} else {%>
	url = "AdminServlet?method=AdminPersionUpdate&ADMIN_ID=" + row.ADMIN_ID;
<%}%>
	}
</script>

</head>
<body style="margin: 5px;">
	<%
		if ("user".equals((String) session.getAttribute("userRole"))) {
	%>
	<table id="dg" title="职工信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" fit="true" pageSize="20" rownumbers="true"
		url="PersionInfo" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="EMP_NO" width="80" align="center">工号</th>
				<th field="EMP_NAME" width="90" align="center">姓名</th>
				<th field="EMP_SEX" hidden="true" width="50" align="center">EMP_SEX</th>
				<th field="sexName" width="50" align="center">性别</th>
				<th field="EMP_Birthday" width="80" align="center">出生日期</th>
				<th field="PS_TYPE" width="100" hidden="true" align="center">岗位ID</th>
				<th field="PS_Name" width="80" align="center">政治面貌</th>
				<th field="EMP_Phone" width="100" align="center">电话</th>
				<th field="EMP_Address" width="150" align="center">地址</th>
				<th field="ext1" width="150" align="center">备注</th>
				<th field="ext2" width="150" align="center">更新日期</th>
			</tr>
		</thead>
	</table>
	<%
		} else {
	%>
	<table id="dg" title="个人信息" class="easyui-datagrid" fitColumns="true"
		pagination="true" fit="true" pageSize="20" rownumbers="true"
		url="PersionInfo" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="ADMIN_ID" width="80" hidden="true" align="center">ID</th>
				<th field="ADMIN_NO" width="90" align="center">登录账号</th>
				<th field="ADMIN_PWD" hidden="true" width="50" align="center">密码</th>
				<th field="ADMIN_NAME" width="80" align="center">姓名</th>
				<th field="ADMIN_PHONE" width="100" align="center">电话</th>
				<th field="Ext1" width="80" align="center">备注</th>
				<th field="Ext2" width="100" align="center">更新日期</th>
				<th field="Ext3" width="150" hidden="true" align="center">备注</th>

			</tr>
		</thead>
	</table>

	<%
		}
	%>
	<div id="tb">
		<div>
			<a href="javascript:openStudentModifyDialog()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
		</div>

	</div>
	<%
		if ("user".equals((String) session.getAttribute("userRole"))) {
	%>
	<div id="dlg" class="easyui-dialog"
		style="width: 570px; height: 350px; padding: 10px 20px" closed="true"
		buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="80" align="right">姓名：</td>
					<td><input type="text" name="EMP_NAME" id="EMP_NAME"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">性别：</td>
					<td><select class="easyui-combobox" name="EMP_SEX"
						id="EMP_SEX" editable="false" panelHeight="auto"
						style="width: 135px;">
							<option value="">请选择...</option>
							<option value="M">男</option>
							<option value="F">女</option>
					</select></td>
					<td></td>
					<td width="80" align="right">出生日期：</td>
					<td><input class="easyui-datebox" id="EMP_Birthday"
						name="EMP_Birthday" editable="false" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">政治面貌：</td>
					<td><input class="easyui-combobox" id="PS_TYPE" name="PS_TYPE"
						data-options=" panelHeight:'auto',editable:false,valueField:'PS_TYPE',textField:'PS_Name',url:'PoliticalStatusComboList'" />
					</td>
					<td></td>
					<td width="80" align="right">电话：</td>
					<td><input type="text" name="EMP_Phone" id="EMP_Phone"
						class="easyui-validatebox" required="true" maxlength="11"
						validType="phone" /></td>
				</tr>
				<tr>
					<td width="80" align="right">地址：</td>
					<td colspan="4"><textarea rows="1" cols="60"
							name="EMP_Address" id="EMP_Address"></textarea></td>
				</tr>
				<tr>
					<td width="80" align="right">备注：</td>
					<td colspan="4"><textarea rows="7" cols="60" name="ext1"
							id="ext1"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dia-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	<%
		} else {
	%>

	<div id="dlg" class="easyui-dialog"
		style="width: 570px; height: 300px; padding: 10px 20px" modal="true"
		closed="true" buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="100" align="right">登录账号：</td>
					<td><input style="width: 150px" readonly="readonly"
						type="text" name="ADMIN_NO" id="ADMIN_NO"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td width="100" align="right">姓名：</td>
					<td><input style="width: 150px" type="text" name="ADMIN_NAME"
						id="ADMIN_NAME" class="easyui-validatebox" required="true" /></td>
					<td></td>
					<td width="100" align="right">电话：</td>
					<td><input style="width: 150px" maxlength="11" type="text"
						name="ADMIN_PHONE" id="ADMIN_PHONE" class="easyui-validatebox"
						required="true" /></td>
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

	<%
		}
	%>

</body>

</html>