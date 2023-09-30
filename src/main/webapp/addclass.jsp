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
<title>Add Class</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let cid = document.getElementById("cid").value;
		let cname = document.getElementById("cname").value;
		let isValid = true;

		if (cid.length > 6) {
			alert("Class ID length should be less than or equal to 6. Please enter valid id");
			isValid = false;
		}
		if (cname.length > 10) {
			alert("Class name length should be less than or equal to 10. Please enter valid name");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter class details to add</h1>
	<form action="addclass" method="post" onsubmit="return validateForm()">
		<table>
			<tr>
				<td>New Class ID:</td>
				<td><input type="text" name="classid"
					placeholder="Class ID" id="cid" required /></td>
			</tr>
			<tr>
				<td>Subject name:</td>
				<td><input type="text" name="classname"
					placeholder="Class Name" id="cname" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Class" /></td>
			</tr>
		</table>
	</form>
</body>
</html>