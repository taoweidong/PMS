<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>职工信息管理</title>

<script type="text/javascript">
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
				$.post("EmployeeServlet?method=DeleteEmployee", {
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
		url = "EmployeeServlet?method=AddEmployee";
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
					$.messager.alert("系统提示", "保存成功");
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
		$('#passwd').val("");
		$('#passwd2').val("");
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

		//$('#EMP_NO').attr("disabled", "disabled");
		url = "EmployeeServlet?method=UpdateEmployee&EMP_NO=" + row.EMP_NO;
	}
</script>
</head>
<body>
	<table id="dg" class="easyui-datagrid" fitColumns="true"
		pagination="true" fit="true" pageSize="10" rownumbers="true"
		url="queryEmployee" toolbar="#tb">
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
			<a href="javascript:openStudentAddDialog()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">添加</a> <a
				href="javascript:openStudentModifyDialog()"
				class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
				href="javascript:deleteStudent()" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;工号：&nbsp; <input type="text" class="wu-text"
				style="width: 100px" name="s_EMP_NO" id="s_EMP_NO" size="10" />
			&nbsp;姓名：&nbsp; <input type="text" class="wu-text"
				style="width: 100px" name="s_EMP_NAME" id="s_EMP_NAME" size="10" />
			&nbsp;性别：&nbsp; <select class="easyui-combobox" style="width: 100px"
				name="s_EMP_SEX" id="s_EMP_SEX" editable="false" panelHeight="auto">
				<option value="">请选择...</option>
				<option value="M">男</option>
				<option value="F">女</option>
			</select> &nbsp;出生日期：&nbsp; <input class="easyui-datebox" id="s_bbirthday"
				style="width: 100px" name="s_bbirthday" size="10" editable="false" />
			-> <input class="easyui-datebox" id="s_ebirthday"
				style="width: 100px" name="s_ebirthday" size="10" editable="false" />
			&nbsp;政治面貌：&nbsp; <input class="easyui-combobox" id="s_PS_TYPE"
				name="s_PS_TYPE" style="width: 120px;" size="10"
				data-options="panelHeight:'auto',editable:false,valueField:'PS_TYPE',textField:'PS_Name',url:'PoliticalStatusServlet?method=PoliticalStatusComboboxInfo'" />
			<a href="javascript:searchStudent()" class="easyui-linkbutton"
				iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" modal="true"
		style="width: 570px; height: 350px; padding: 10px 20px" closed="true"
		buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="80" align="right">工号：</td>
					<td><input type="text" name="EMP_NO" id="EMP_NO"
						class="easyui-validatebox" required="true" maxlength="10" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="80" align="right">姓名：</td>
					<td><input type="text" name="EMP_NAME" id="EMP_NAME"
						class="easyui-validatebox" required="true" /></td>
				</tr>
				<!-- 手动增加员工信息时不需要输入密码 ，默认密码为员工号，员工首次登陆时需强制修改密码 -->
				<tr>
					<td width="80" align="right">密码：</td>
					<td><input type="password" name="passwd" id="passwd"
						class="easyui-validatebox" value="" maxlength="8" required="true" />
					</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="80" align="right">确认密码：</td>
					<td><input type="password" name="passwd2" id="passwd2"
						class="easyui-validatebox" maxlength="8"
						validType="equalTo['#passwd']" invalidMessage="两次输入的密码不一致"
						value="" required="true" /></td>
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
						data-options=" panelHeight:'auto',editable:false,valueField:'PS_TYPE',textField:'PS_Name',url:'PoliticalStatusServlet?method=PoliticalStatusComboboxInfo'" />
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

</body>
</html>