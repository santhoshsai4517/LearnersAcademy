package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;

public class Teacher {
	private String id;
	private String name;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2,stmt3;

	public Teacher() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from teachers where id = ?");
			stmt1 = con.prepareStatement("insert into teachers values(?,?)");
			stmt2 = con.prepareStatement("select * from teachers");
			stmt3 = con.prepareStatement(
					"select * from teachers where id in (select teacher_id from teacherassignment inner JOIN classsubjects  on teacherassignment.classsubject_id = classsubjects.id where classsubjects.class_id = ?)");
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

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}

	public int addTeacher() {
		int result = 0;
		try {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return -1;

			stmt1.setString(1, id);
			stmt1.setString(2, name);
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

	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		try {
			ResultSet rs = stmt2.executeQuery();
			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getString(1));
				teacher.setName(rs.getString(2));
				teachers.add(teacher);
				teacher.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}
	
	public ArrayList<Teacher> getTeachers(String id) {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		try {
			stmt3.setString(1, id);
			ResultSet rs = stmt3.executeQuery();
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getString(1));
				teacher.setName(rs.getString(2));
				teachers.add(teacher);
				teacher.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teachers;
	}
}
