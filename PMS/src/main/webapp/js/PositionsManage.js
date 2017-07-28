var url;
function searchPositions() {
	$('#wu-datagrid-2').datagrid('load', {
		POS_NAME : $('#s_POS_NAME').val(),
		DEP_ID : $('#DEP_ID').combobox('getValue'),
		startDate : $('#startDate').datebox('getValue'),
		endDate : $('#endDate').datebox('getValue'),
	});
}

function deletePositions() {
	var selectedRows = $('#wu-datagrid-2').datagrid('getSelections');
	if (selectedRows.length == 0) {
		$.messager.alert("系统提示", "请选择要删除的数据！");
		return;
	}
	var strIds = [];
	for ( var i = 0; i < selectedRows.length; i++) {
		strIds.push(selectedRows[i].POS_ID);
	}
	var ids = strIds.join(",");
	// alert(ids);
	$.messager.confirm("系统提示", "您确定要删除这<font color=red>" + selectedRows.length
			+ "</font>条数据吗？", function(r) {
		if (r) {
			$.post("PositionsServlet?method=DeletePositions", {
				delIds : ids
			}, function(result) {
				if (result.success) {
					$.messager.alert("系统提示", "您已经成功删除了<font color=red>"
							+ result.delNums + "</font>条数据！");
					$('#wu-datagrid-2').datagrid('reload');
				} else {
					$.messager.alert("系统提示", '<font color="red">'
							+ selectedRows[result.errorIndex].gradeName
							+ '</font>' + result.errorMsg);
				}
			}, "json");
		}
	});
}

function openPositionsAddDialog() {
	$('#dlg').dialog('open').dialog("setTitle", "添加岗位信息");
	url = "PositionsServlet?method=AddPositions";
}
function openPositionsModifyDialog() {
	var selectedRows = $('#wu-datagrid-2').datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择要一条要编辑的数据！");
		return;
	}
	var row = selectedRows[0];// 取得要编辑的那条记录
	$('#dlg').dialog("open").dialog("setTitle", "编辑岗位信息");
	$('#fm').form('load', row);// 把修改编辑的数据塞到表单中
	url = "PositionsServlet?method=UpdatePositions&POS_ID=" + row.POS_ID;
}
function closeGradeDialog() {
	$('#dlg').dialog("close");
	resetValue();
}
function resetValue() {
	$('#POS_ID').val("");
	$('#POS_NAME').val("");
	$('#DEP_LEADER').val("");
	$('#POS_CONTENT').val("");
	$('#POS_SALARY').val("");
	$('#POS_ALLOWANCE').val("");
	$('#POS_PERQUISITES').val("");
	$('#EXT1').val("");
}
function savePositions() {
	$('#fm').form("submit", {
		url : url,
		onSubmit : function() {
			return $(this).form("validate");
		},
		success : function(result) {
			// 把JSON对象转为javascript对象
			var result = eval('(' + result + ')');
			// alert(result.success);
			console.log(result);
			if (result.success) {
				$.messager.alert("系统提示", "保存成功");
				resetValue();
				$('#dlg').dialog("close");
				$('#wu-datagrid-2').datagrid("reload");
			} else {
				$.messager.alert("系统提示", result.errorMsg);
				return;
			}
		}
	});
}