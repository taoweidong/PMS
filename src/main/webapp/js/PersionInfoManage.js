/**
 * 功能：修改密码 时 校验旧密码是否在正确
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
 * 功能：校验第一次输入的密码和第二次修改的密码是否一致
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
// 查询方法--easyui封装的方法，从table表中进行筛选数据
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
// 删除员工信息
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
	// alert(ids);
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length
			+ "</font>条数据吗？", function(r) {
		if (r) {
			$.post("studentDelete", {
				delIds : ids
			}, function(result) {
				// 此处已经指定以JSON运行响应，无需再进行转换
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
// 保存修改的和新增的员工信息
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
			// 把JSON对象转为javascript对象
			var result = eval('(' + result + ')');
			console.log(result);
			if (result.success) {
				$.messager.alert("系统提示", "更新成功,，下一次登录生效！");
				resetValue();
				$('#dlg').dialog("close");// 关闭dialog
				$('#dg').datagrid("reload");// 重新加载表格
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
			// 把JSON对象转为javascript对象
			var result = eval('(' + result + ')');
			console.log(result);
			if (result.success) {
				$.messager.alert("系统提示", "更新成功,，下一次登录生效！");
				resetValue();
				$('#dlg').dialog("close");// 关闭dialog
				$('#dg').datagrid("reload");// 重新加载表格
			} else {
				$.messager.alert("系统提示", result.errorMsg);
				return;
			}
		}
	});
}

// 清空表单
function resetValue() {
	$('#no').val("");
	$('#name').val("");
	$('#phone').combobox('setValue', "");
	$('#ext1').datebox('setValue', "");

}
function closeStudentDialog() {
	$('#dlg').dialog('close');// 关闭对话框
	resetValue();// 清空
}
// 打开修改员工信息的弹窗，并把选中的员工信息塞进去
function openStudentModifyDialog() {
	// 获取选中的行 getSelections 是EasyUI的一个属性
	// 含义为：取名称为 dg 表格中所有选中行
	var selectedRows = $('#dg').datagrid('getSelections');
	// 判断是否选中一条数据，为空或者多余一条均失败
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择要一条要编辑的数据！");
		return;
	}
	// 获取选中的员工信息
	var row = selectedRows[0];

	// 打开编辑员工信息的页面 并设置标题
	$('#dlg').dialog("open").dialog("setTitle", "编辑职工信息");
	// 把选中行的数据加载到弹出的表单信息中 即把修改编辑的数据塞到表单中
	$('#fm').form('load', row);

	url = "updatePersional";

}