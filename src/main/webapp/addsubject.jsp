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
<title>Add Subject</title>
<script>
	//    alert(new Date());
	function validateForm() {
		let sid = document.getElementById("sid").value;
		let sname = document.getElementById("sname").value;
		let isValid = true;

		if (sid.length > 6) {
			alert("Subject ID length should be less than or equal to 6. Please enter valid id");
			isValid = false;
		}
		if (sname.length > 25) {
			alert("Subject name length should be less than or equal to 25. Please enter valid name");
			isValid = false;
		}
		return isValid;
	}
</script>

</head>
<body>
	<h1>Enter subject details to add</h1>
	<form action="addsubject" method="post" onsubmit="return validateForm()">
		<table>
			<tr>
				<td>New Subject ID:</td>
				<td><input type="text" name="subjectid"
					placeholder="Subject ID" id="sid" required /></td>
			</tr>
			<tr>
				<td>Subject name:</td>
				<td><input type="text" name="subjectname"
					placeholder="Subject Name" id="sname" required /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Subject" /></td>
			</tr>
		</table>
	</form>
</body>
</html>