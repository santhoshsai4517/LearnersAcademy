<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("isValid") == null || !(Boolean) session.getAttribute("isValid"))
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change password</title>
<script>

	function validate(){
		let password = document.getElementById("password").value;
		let isValid = true;
		if(password.length >10){
			isValid = false;
			alert("Password length should be less than or equeal to 10");
		}
		return isValid;
	}

</script>
</head>
<body>
	<h1>Enter a new password</h1>
	<form action="change" method="post" onsubmit="return validate()">
		<table>
			<tr>
				<td>New Password:</td>
				<td><input type="password" name="password"
					placeholder="New password" id="password" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Change password" /></td>
			</tr>
		</table>
	</form>
</body>
</html>