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
	// console.log($('#s_EMP_SEX').combobox('getValue'));
	$('#dg').datagrid('load', {
		no : $('#s_EMP_NO').val(),
		name : $('#s_EMP_NAME').val(),
		sex : $('#s_EMP_SEX').combobox('getValue'),
		startBirthday : $('#s_bbirthday').datebox('getValue'),
		endBirthday : $('#s_ebirthday').datebox('getValue'),
		cboPsType : $('#s_PS_TYPE').combobox('getValue')
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
		strIds.push(selectedRows[i].no);
	}
	var ids = strIds.join(",");
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length
			+ "</font>条数据吗？", function(r) {
		if (r) {
			$.post("deleteEmployee", {
				ids : ids
			}, function(result) {
				// 此处已经指定以JSON运行响应，无需再进行转换
				if (result.success) {
					$.messager.alert("系统提示", "操作成功！");
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
	url = "addEmployee";
}
// 保存修改的和新增的员工信息
function saveStudent() {
	$('#fm').form("submit", {
		url : url,
		onSubmit : function() {
			if ($('#sex').combobox("getValue") == "") {
				$.messager.alert("系统提示", "请选择性别");
				return false;
			}
			if ($('#psId').combobox("getValue") == "") {
				$.messager.alert("系统提示", "请选择所属岗位");
				return false;
			}
			return $(this).form("validate");
		},
		success : function(result) {
			// 把JSON对象转为javascript对象
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.alert("系统提示", "保存成功");
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
	$('#passwd').val("");
	$('#passwd2').val("");
	$('#sex').combobox('setValue', "");
	$('#birthday').datebox('setValue', "");
	$('#psId').combobox('setValue', "");
	$('#phone').val("");
	$('#address').val("");
	$('#ext1').val("");

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

	// $('#EMP_NO').attr("disabled", "disabled");
	url = "updateEmployee";
}