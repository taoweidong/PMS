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
function searchAdminInfo() {
	$('#dg').datagrid('load', {
		no : $('#s_ADMIN_NO').val(),
		name : $('#s_ADMIN_NAME').val(),
		phone : $('#s_ADMIN_PHONE').val(),
	});
}

// 删除员工信息
function deleteAdminInfo() {
	var selectedRows = $('#dg').datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("系统提示", "请选择要删除的数据！");
		return;
	}
	var strIds = [];
	for (var i = 0; i < selectedRows.length; i++) {
		strIds.push(selectedRows[i].ADMIN_ID);
	}
	var ids = strIds.join(",");
	// alert(ids);
	$.messager.confirm("系统提示",
			"您确定要删除这<font color=red>" + ids + "</font>条数据吗？", function(r) {
				if (r) {
					$.post("deleteAdmin", {
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

// 设置超级管理员
function setSuperAdmin() {
	var selectedRows = $('#dg').datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("系统提示", "请选择要设置的管理员！");
		return;
	}
	var strIds = [];
	for (var i = 0; i < selectedRows.length; i++) {
		strIds.push(selectedRows[i].ADMIN_ID);
	}
	var ids = strIds.join(",");
	// alert(ids);
	$.messager.confirm("系统提示", "您确定要将这<font color=red>" + selectedRows.length
			+ "</font>条数据设置为超级管理员吗？", function(r) {
		if (r) {
			$.post("setSuperAdmin", {
				delIds : ids
			}, function(result) {
				// 此处已经指定以JSON运行响应，无需再进行转换
				if (result.success) {
					$.messager.alert("系统提示", "您已经成功设置了<font color=red>"
							+ result.delNums + "</font>个管理员为超级管理员！");
					$('#dg').datagrid('reload');
				} else {
					$.messager.alert("系统提示", result.errorMsg);
				}
			}, "json");
		}
	});
}

// 取消设置
function cancelSetSuperAdmin() {
	var selectedRows = $('#dg').datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("系统提示", "请选择要取消的超级管理员！");
		return;
	}
	var strIds = [];
	for (var i = 0; i < selectedRows.length; i++) {
		strIds.push(selectedRows[i].ADMIN_ID);
	}
	var ids = strIds.join(",");
	$.messager.confirm("系统提示", "您确定要将这<font color=red>" + selectedRows.length
			+ "</font>条数据取消超级管理员设置？", function(r) {
		if (r) {
			$.post("cancelSuperAdmin", {
				delIds : ids
			}, function(result) {
				// 此处已经指定以JSON运行响应，无需再进行转换
				if (result.success) {
					$.messager.alert("系统提示", "您已经成功取消了<font color=red>"
							+ result.delNums + "</font>个超级管理员设置！");
					$('#dg').datagrid('reload');
				} else {
					$.messager.alert("系统提示", result.errorMsg);
				}
			}, "json");
		}
	});
}

function openAdminAddDialog() {
	$('#dlg').dialog('open').dialog("setTitle", "添加管理员信息");
	url = "addAdmin";
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
	$('#newPassword').val("");
	$('#newPassword2').val("");
	$('#phone').val("");
	$('#ext1').val("");

}

function closeStudentDialog() {
	$('#dlg').dialog('close');// 关闭对话框
	resetValue();// 清空
}

function openAdminModifyDialog() {
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
	url = "updateAdmin?id=" + row.id;

}