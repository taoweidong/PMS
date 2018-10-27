var url;
/**
 * 功能：查询方法--easyui封装的方法，从table表中进行筛选数据
 */
function searchApplyInduction() {
	$('#wu-datagrid-2').datagrid('load', {
		posName : $('#s_POS_NAME').combobox('getValue'),
		startDate : $('#startDate').datebox('getValue'),
		endDate : $('#endDate').datebox('getValue'),
		approveState : $('#s_approveState').combobox('getValue'),
		indState : $('#s_IND_STATE').combobox('getValue'),
		empNo : $('#s_EMP_NO').combobox('getValue')
	});
}

// 保存修改的和新增的员工信息
function ApproveInduction() {
	$('#wu-form-2').form("submit", {
		url : url,
		onSubmit : function() {
			if ($('#EXT1').combobox("getValue") == "") {
				$.messager.alert("系统提示", "请选择审批操作");
				return false;
			}
			return $(this).form("validate");
		},
		success : function(result) {
			// 把JSON对象转为javascript对象
			var result = eval('(' + result + ')');
			console.log("返回的JSON对象" + result);
			if (result.success) {
				$.messager.alert("系统提示", "保存成功");
				resetValue();
				$('#wu-dialog-2').dialog("close");// 关闭dialog
				$('#wu-datagrid-2').datagrid("reload");// 重新加载表格
			} else {
				$.messager.alert("系统提示", result.errorMsg);
				return;
			}
		}
	});
}

/**
 * 功能： 删除记录
 */
function deleteInduction() {
	var selectedRows = $('#wu-datagrid-2').datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("系统提示", "请选择要删除的数据！");
		return;
	}
	var strIds = [];
	for (var i = 0; i < selectedRows.length; i++) {
		strIds.push(selectedRows[i].id);
	}
	var ids = strIds.join(",");
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length
			+ "</font>条数据吗？", function(r) {
		if (r) {
			$.post("deleteUserApplyInduction", {
				ids : ids
			}, function(result) {
				// 此处已经指定以JSON运行响应，无需再进行转换
				if (result.success) {
					$.messager.alert("系统提示", "删除成功！");
					$('#wu-datagrid-2').datagrid('reload');
				} else {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				}
			}, "json");
		}
	});
}

/**
 * Name 打开审批窗口
 */
function openApplyInductionAddDialog() {
	// 获取审批的信息ID
	var selectedRows = $('#wu-datagrid-2').datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("系统提示", "请选择要提交的申请！");
		return;
	}
	var strIds = [];
	for (var i = 0; i < selectedRows.length; i++) {
		strIds.push(selectedRows[i].id);
	}
	var ids = strIds.join(",");

	$('#wu-form-2').form('clear');
	$('#wu-dialog-2').dialog('open').dialog("setTitle", "审批信息");
	url = "updateAdminApplyApprove&id=" + ids;

}
/**
 * 功能：关闭窗口
 */
function closeNoticesDialog() {
	$('#wu-dialog-2').dialog('close');// 关闭对话框
	resetValue();// 清空
}

/**
 * 功能：清空表单
 */
function resetValue() {
	$('#ext1').combobox('setValue', "");
}
/**
 * Name 打开修改审批状态
 */
function openApproveInductionModifyDialog() {
	// 获取选中的行 getSelections 是EasyUI的一个属性
	// 含义为：取名称为 dg 表格中所有选中行
	var selectedRows = $('#wu-datagrid-2').datagrid('getSelections');
	// 判断是否选中一条数据，为空或者多余一条均失败
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择要一条要编辑的数据！");
		return;
	}
	// 获取选中的员工信息
	var row = selectedRows[0];
	// 打开编辑员工信息的页面 并设置标题
	$('#wu-dialog-2').dialog("open").dialog("setTitle", "编辑审批信息");
	// 把选中行的数据加载到弹出的表单信息中 即把修改编辑的数据塞到表单中
	$('#wu-form-2').form('load', row);
	url = "updateAdminApplyApprove?id=" + row.id;
	// alert(url);

}