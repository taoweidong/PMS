<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<!-- 引用CSS样式文件和 javaScript文件 -->
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/wu.css" />
<link rel="stylesheet" type="text/css" href="css/icon.css" />
<link rel="stylesheet" type="text/css" href="css/loginStyle.css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
		equalTo : {
			validator : function(value, param) {
				return $(param[0]).val() == value;
			},
			message : '字段不匹配'
		}
	});
	//打开注册界面
	function openStudentAddDialog() {
		$('#dlg').dialog('open').dialog("setTitle", "注册信息");
		url = "EmployeeRegisterServlet";
	}
	//保存修改的和新增的员工信息
	function saveStudent() {
		$('#fm').form("submit", {
			url : url,
			onSubmit : function() {
				if ($('#sex').combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择性别");
					return false;
				}
				if ($('#ps_id').combobox("getValue") == "") {
					$.messager.alert("系统提示", "请选择政治面貌");
					return false;
				}
				return $(this).form("validate");
			},
			success : function(result) {
				//把JSON对象转为javascript对象
				var result = eval('(' + result + ')');
				//js日志打印
				console.log(result);
				if (result.success) {
					$.messager.alert("系统提示", "注册成功");
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
	//关闭注册 界面
	function closeStudentDialog() {
		$('#dlg').dialog('close');//关闭对话框
		resetValue();//清空
	}
	//清空注册界面表单数据
	function resetValue() {
		$('#stuNo').val("");
		$('#stuName').val("");
		$('#sex').combobox('setValue', "");
		$('#birthday').datebox('setValue', "");
		$('#ps_id').combobox('setValue', "");
		$('#phone').val("");
		$('#address').val("");
		$('#stuDesc').val("");

	}
	//清空登录界面的数据
	function loginIdClear() {
		$('#userName').val("");
		$('#password').val("");
		$('#role').combobox('setValue', "user");
	}
	function login() {
		var userName = $("#userName").val();
		var password = $("#password").val();
		var roleName = $("#role").val();

		if (userName == null || userName == "") {
			alert("用户名不能为空！");
			return;
		}
		if (password == null || password == "") {
			alert("密码不能为空！");
			return;
		}
		if (roleName == null || roleName == "") {
			alert("角色不能为空！");
			return;
		}

		$("#loginId").submit();

	}
</script>

<script type=text/javascript>
	if ('${errorMsg}' != '') {
		alert('${errorMsg}');
	}
</script>
</head>
<body>
	<form id="loginId" method="post" name="loginName" action="login">
		<div></div>
		<table style="margin: auto; width: 100%; height: 100%" border=0 cellSpacing=0 cellPadding=0>
			<tbody>
				<tr>
					<td height=150>&nbsp;</td>
				</tr>
				<tr style="height: 254px">
					<td>
						<div style="margin: 0px auto; width: 936px">
							<img style="display: block" src="${pageContext.request.contextPath}/images/body_03.jpg">
						</div>
						<div style="background-color: #278296">
							<div style="margin: 0px auto; width: 936px">
								<div style="BACKGROUND: url(${pageContext.request.contextPath}/images/body_05.jpg) no-repeat; height: 155px">
									<div style="text-align: left; width: 300px; float: right; height: 125px; _height: 95px">
										<table border=0 cellSpacing=0 cellPadding=0 width="100%">
											<tbody>
												<tr>
													<td style="height: 32px"><label>用户名</label> <input type="text" class="input" value="${userName }" name="userName" id="userName" /></td>
												</tr>
												<tr>
													<td style="height: 32px"><label>密&nbsp;&nbsp;&nbsp;码</label> <input type="password" class="input" value="${password }" name="password" id="password" /></td>
												</tr>
												<tr>
													<td><label>角&nbsp;&nbsp;&nbsp;色</label> <select class="easyui-combobox" id="role" name="role" editable="false" panelHeight="auto" style="width: 100px">
															<option value="user" selected="selected">普通职工</option>
															<option value="admin">管理员</option>
															<option value="superAdmin">超级管理员</option>
													</select></td>

												</tr>
												<tr>
													<td>
														<!-- 错误提示信息 --> <font color="red">${error}</font>
													</td>
												</tr>

											</tbody>
										</table>
									</div>
									<div style="height: 1px; clear: both;"></div>
									<div style="width: 380px; float: right; clear: both">
										<table border=0 cellSpacing=0 cellPadding=0 width=500>
											<tbody>
												<tr>
													<td width=100 align=right><input style="border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px" id=btnLogin src="${pageContext.request.contextPath}/images/btn1.jpg" type=image name=btnLogin onclick="javascript:login();return false;"></td>
													<td width=100 align=middle><input style="border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px" id=btnReset src="${pageContext.request.contextPath}/images/btn2.jpg" type="image" name=btnReset onclick="javascript:loginIdClear();return false;"></td>
													<!-- <td width=100 align=left><a href="javascript:openStudentAddDialog()" class="easyui-linkbutton" iconCls="button" plain="true">注册</a></td> -->
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div style="margin: 0px auto; width: 936px">
							<img src="${pageContext.request.contextPath}/images/body_06.jpg">
						</div>
					</td>
				</tr>
				<tr style="height: 30%">
					<td>&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</form>
	<div id="dlg" class="easyui-dialog" modal="true" style="width: 570px; height: 350px; padding: 10px 20px" closed="true" buttons="#dia-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td width="80" align="right">工号：</td>
					<td><input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" maxlength="10" required="true" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="80" align="right">姓名：</td>
					<td><input type="text" name="stuName" id="stuName" class="easyui-validatebox" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">密码：</td>
					<td><input type="password" name="passwd" id="passwd" class="easyui-validatebox" value="" maxlength="8" required="true" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="80" align="right">确认密码：</td>
					<td><input type="password" name="passwd2" id="passwd2" class="easyui-validatebox" maxlength="8" validType="equalTo['#passwd']" invalidMessage="两次输入的密码不一致" value="" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">性别：</td>
					<td><select class="easyui-combobox" name="sex" id="sex" editable="false" panelHeight="auto" style="width: 135px;">
							<option value="">请选择...</option>
							<option value="M">男</option>
							<option value="F">女</option>
					</select></td>
					<td></td>
					<td width="80" align="right">出生日期：</td>
					<td><input class="easyui-datebox" id="birthday" name="birthday" editable="false" required="true" /></td>
				</tr>
				<tr>
					<td width="80" align="right">政治面貌：</td>
					<td><input class="easyui-combobox" id="ps_id" name="ps_id" data-options=" panelHeight:'auto',editable:false,valueField:'PS_TYPE',textField:'PS_Name',url:'PoliticalStatusServlet?method=PoliticalStatusComboboxInfo'" /></td>
					<td></td>
					<td width="80" align="right">电话：</td>
					<td><input type="text" name="phone" id="phone" class="easyui-validatebox" maxlength="11" required="true" validType="phone" /></td>
				</tr>
				<tr>
					<td width="80" align="right">地址：</td>
					<td colspan="4"><textarea rows="1" cols="60" name="address" id="address"></textarea></td>
				</tr>
				<tr>
					<td width="80" align="right">备注：</td>
					<td colspan="4"><textarea rows="7" cols="60" name="ext1" id="ext1"></textarea></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dia-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

</body>
</html>
