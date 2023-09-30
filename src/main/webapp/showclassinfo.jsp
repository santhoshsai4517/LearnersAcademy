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
<title>Search class</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let cid = document.getElementById("cid").value;
		let isValid = true;

		if (cid.length > 6) {
			alert("Class ID length should be less than or equal to 6. Please enter valid id");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter class id</h1>
	<form action="showclassinfo" method="post" onsubmit="return validateForm()">
		<table>
			<tr>
				<td>Class ID:</td>
				<td><input type="text" name="classid"
					placeholder="Class ID" id="cid" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Search Class info" /></td>
			</tr>
		</table>
	</form>
</body>
</html>