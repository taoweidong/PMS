/**
 * 功能：修改密码 时 校验旧密码是否在正确
 */
$.extend($.fn.validatebox.defaults.rules, {
	newEqualToOld : {
		validator : function(value, param) {
			console.log(param[0] + "------" + value);
			var resultFlag = false;
			// 向后台发送一个post请求
			$.post("checkPwd", {
				password : value
			}, function(result) {
				console.log(result);

				if (result.success) {
					// $.messager.alert("系统提示","添加成功","info");
					resultFlag = true;
				} else {
					// $.messager.alert("系统提示","添加失败","error");
					resultFlag = false;
				}
			}, "json");
			return resultFlag;
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

/* 菜单的点击事件处理 */
var url;
function addTab(url, text, iconCls) {
	var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='"
			+ url + "'></iframe>";
	$("#tabs").tabs("add", {
		title : text,
		iconCls : iconCls,
		closable : true,
		content : content
	});
}
// 点击左侧属性菜单中的某一项时，触发此事件，在右侧区域打开响应的页面
function openTab(text, url, iconCls) {
	if ($("#tabs").tabs("exists", text)) {
		$("#tabs").tabs("close", text);
		addTab(url, text, iconCls);
		$("#tabs").tabs("select", text);
	} else {
		addTab(url, text, iconCls);
	}
}
/* 打开修改密码的弹出框 */
function openPasswordModifyDialog() {
	$("#dlg").dialog("open").dialog("setTitle", "修改密码");
	url = "passwordModify";

}
function closePasswordModifyDialog() {
	$("#dlg").dialog("close");
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPassword2").val("");
}

function modifyPassword() {
	// alert(url);
	$("#fm").form("submit", {
		url : url,
		onSubmit : function() {
			return $(this).form("validate");
		},
		success : function(result) {
			var result = eval('(' + result + ')');
			if (result.success) {
				$.messager.alert("系统提示", "密码修改成功，下一次登录生效！");
				closePasswordModifyDialog();
			} else {
				$.messager.alert("系统提示", "密码修改失败");
				return;
			}
		}
	});
}

function logout() {
	$.messager.confirm("系统提示", "您确定要退出系统吗", function(r) {
		if (r) {
			window.location.href = "/PMS/logout";
		}
	});
}