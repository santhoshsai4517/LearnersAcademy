package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;

public class Student {
	private String id;
	private String name;
	private String classid;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2,stmt3;

	public Student() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from student where id = ?");
			stmt1 = con.prepareStatement("insert into student values(?,?,?)");
			stmt2 = con.prepareStatement("select * from student");
			stmt3 = con.prepareStatement("select * from student where class_id = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", classid=" + classid + "]";
	}

	public int addStudent() {
		int result = 0;
		try {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return -1;

			stmt1.setString(1, id);
			stmt1.setString(2, name);
			stmt1.setString(3, classid);
			result = stmt1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void destroy() {
		try {
			con.close();
			stmt.close();
			stmt1.close();
			stmt2.close();
			stmt3.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			ResultSet rs = stmt2.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setClassid(rs.getString(3));;
				students.add(student);
				student.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
	
	public ArrayList<Student> getStudents(String Id) {
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			stmt3.setString(1, Id);
			ResultSet rs = stmt3.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				students.add(student);
				student.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}
}
