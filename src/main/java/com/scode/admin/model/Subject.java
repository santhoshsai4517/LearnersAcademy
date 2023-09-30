package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.scode.util.ConnectionUtil;

public class Subject {
	private String id;
	private String name;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2, stmt3,stmt4;

	public Subject() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from subjects where id = ?");
			stmt1 = con.prepareStatement("select * from subjects where name = ?");
			stmt2 = con.prepareStatement("insert into subjects values(?,?)");
			stmt3 = con.prepareStatement("select * from subjects");
			stmt4 = con.prepareStatement(
					"select * from subjects where id in (select subject_id from classsubjects where class_id = ?)");
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
		return "Subject [id=" + id + ", name=" + name + "]";
	}

	public int addSubject() {
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
			stmt4.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Subject> getSubjects() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		try {
			ResultSet rs = stmt3.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getString(1));
				subject.setName(rs.getString(2));
				subjects.add(subject);
				subject.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}
	
	public ArrayList<Subject> getSubjects(String id) {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		try {
			stmt4.setString(1, id);
			ResultSet rs = stmt4.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getString(1));
				subject.setName(rs.getString(2));
				subjects.add(subject);
				subject.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjects;
	}
}
