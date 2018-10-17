<%@ page language="java" import="java.util.*,java.lang.*,com.pms.entity.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/commons.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>个人信息管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/PersionInfoManage.js"></script>

</head>
<body>
	<div class="easyui-panel" title="个人信息" style="height: 450">
		<div align="center">
			<form id="ff" method="post">
				<table>
					<tr>
						<c:choose>
							<c:when test="${sessionScope.role eq 'user'}">
								<td><input type="hidden" value="" name="id" id="id" /></td>
							</c:when>
							<c:otherwise>
								<td><input type="hidden" value="${sessionScope.user.id}" name="id" id="id" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<td width="100" align="right">账号：</td>
						<td><input style="width: 150px" maxlength="10" readonly="readonly" value="${sessionScope.user.no}" type="text" name="no" id="no" class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td width="100" align="right">姓名：</td>
						<td><input style="width: 150px" value="${sessionScope.user.name}" type="text" name="name" id="name" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td width="100" align="right">密码：</td>
						<td><input type="password" id="newPassword" name="newPassword" class="easyui-validatebox" required="true" style="width: 150px" /></td>
					</tr>
					<tr>
						<td width="100" align="right">确认密码：</td>
						<td><input type="password" id="newPassword2" name="newPassword2" class="easyui-validatebox" required="true" validType="equalTo['#newPassword']" invalidMessage="两次输入的密码不一致" style="width: 150px" /></td>
					</tr>
					<tr>
						<td width="100" align="right">电话：</td>
						<td colspan="4"><input style="width: 150px" value="${sessionScope.user.phone}" maxlength="11" type="text" name="phone" id="phone" class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td width="100" align="right">备注：</td>
						<td colspan="4"><textarea rows="4" cols="60" name="ext1" id="ext1">${sessionScope.user.ext1}</textarea></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:updatePersion()" class="easyui-linkbutton">保存</a>
				<!-- <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a> -->
			</div>
		</div>
	</div>
	<script>
		function updatePersion() {
			$('#ff').form("submit", {
				url : "updatePersion",
				onSubmit : function() {
					return $(this).form("validate");
				},
				success : function(result) {
					// 把JSON对象转为javascript对象
					var result = eval('(' + result + ')');
					if (result.success) {
						$.messager.alert("系统提示", "保存成功,下次登陆生效!");
						//resetValue();
						//$('#ff').datagrid("reload");// 重新加载表格
						//$('#ff').dialog("close");// 关闭dialog
						location.reload();

					} else {
						$.messager.alert("系统提示", result.errorMsg);
						return;
					}
				}
			});
		}
	</script>

</body>

</html>