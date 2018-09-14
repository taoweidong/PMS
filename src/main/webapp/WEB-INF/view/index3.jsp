<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
body {
	background: url(images/background.jpg) no-repeat center center;
	background-size: cover;
	background-attachment: fixed;
	background-color: #CCCCCC;
}
</style>

<title>系统登录</title>

</head>

<body style="padding-left: 35%; padding-top: 6%;">
	<div class="easyui-panel" title="登录" style="width: 400px;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff" action="login" method="post">
				<table cellpadding="5">
					<tr>
						<td>用户名：</td>
						<td><input class="easyui-validatebox" maxlength="15" type="text" name="userName" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
						<td><input class="easyui-validatebox" maxlength="12" type="password" name="password" data-options="required:true"></input></td>
					</tr>

					<tr>
						<td>角&nbsp;&nbsp;&nbsp;&nbsp;色:</td>
						<td><select class="easyui-combobox" name="role" style="width: 130px;">
								<option value="user" selected="selected">普通职工</option>
								<option value="admin">管理员</option>
								<option value="superAdmin">超级管理员</option>

						</select></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
			</div>
		</div>
	</div>

	<script>
		function submitForm() {
			$('#ff').form('submit');
		}

		function clearForm() {
			$('#ff').form('clear');
		}
	</script>
</body>

</html>
