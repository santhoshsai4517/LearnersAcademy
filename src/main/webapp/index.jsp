<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Learner's Academy</title>
</head>
<body>
	<h1>Welcome to Leaner's Academy Admin portal</h1>
	<form action="login" method="post">

		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username" placeholder="Username" required/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" placeholder="password" required/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Login"/></td>
			</tr>
		</table>


	</form>
</body>
</html>