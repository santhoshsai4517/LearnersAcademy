package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;

public class Classes {
	private String id;
	private String name;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2, stmt3;

	public Classes() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from classes where id = ?");
			stmt1 = con.prepareStatement("select * from classes where name = ?");
			stmt2 = con.prepareStatement("insert into classes values(?,?)");
			stmt3 = con.prepareStatement("select * from classes");
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
		return "Class [id=" + id + ", name=" + name + "]";
	}

	public int addClass() {
		int result = 0;
		try {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				return -1;
			stmt1.setString(1, name);
			rs = stmt1.executeQuery();
			if (rs.next())
				return -2;

			stmt2.setString(1, id);
			stmt2.setString(2, name);
			result = stmt2.executeUpdate();
//			System.out.println(result);
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

	public ArrayList<Classes> getClasses() {
		ArrayList<Classes> allclasses = new ArrayList<Classes>();
		try {
			ResultSet rs = stmt3.executeQuery();
			while (rs.next()) {
				Classes classes = new Classes();
				classes.setId(rs.getString(1));
				classes.setName(rs.getString(2));
				allclasses.add(classes);
				classes.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allclasses;
	}



//	public ArrayList<Subject> getSubjects(String id) {
//
//	}
//
//	public ArrayList<Student> getStudents(String id) {
//
//	}

	public int findClass(String id) {
		int result = 0;
		try {
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
