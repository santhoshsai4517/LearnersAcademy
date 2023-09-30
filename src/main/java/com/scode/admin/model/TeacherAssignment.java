package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.scode.util.ConnectionUtil;

public class TeacherAssignment {
	private String tid;
	private String sid;
	private String cid;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2, stmt3, stmt4, stmt5, stmt6;

	public TeacherAssignment() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from subjects where id = ?");
			stmt1 = con.prepareStatement("select * from classes where id = ?");
			stmt2 = con.prepareStatement("select * from teachers where id = ?");
			stmt3 = con.prepareStatement("select * from classsubjects where class_id = ? and subject_id=?");
			stmt4 = con.prepareStatement("select * from teacherassignment where classsubject_id = ?");
			stmt5 = con.prepareStatement("insert into teacherassignment values (?,?,?)");
			stmt6 = con.prepareStatement(
					"select teacher_id,class_id,subject_id from classsubjects inner join teacherassignment on classsubjects.id = teacherassignment.classsubject_id");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	@Override
	public String toString() {
		return "TeacherAssignment [Teacher " + tid + ", is assigned to subject = " + sid + ", for calss =" + cid + "]";
	}

	public int assignSubject() {
		int result = 0;
		String id = null;
		try {
			stmt.setString(1, sid);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return -1;
			stmt1.setString(1, cid);
			rs = stmt1.executeQuery();
			if (!rs.next())
				return -2;
			stmt2.setString(1, tid);
			rs = stmt2.executeQuery();
			if (!rs.next())
				return -3;
			stmt3.setString(1, cid);
			stmt3.setString(2, sid);
			ResultSet rs1 = stmt3.executeQuery();
			if (!rs1.next())
				return -4;
			if (rs1 != null)
				id = rs1.getString(1);

			stmt4.setString(1, id);
			rs = stmt4.executeQuery();
			if (rs.next())
				return -5;

			Random random = new Random();
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			int length = 6;
			StringBuilder randomText = new StringBuilder();
			for (int i = 0; i < length; i++) {
				int randomIndex = random.nextInt(characters.length());
				randomText.append(characters.charAt(randomIndex));
			}

			stmt5.setString(1, randomText.toString());
			stmt5.setString(2, tid);
			stmt5.setString(3, id);
			result = stmt5.executeUpdate();
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
			stmt5.close();
			stmt6.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<TeacherAssignment> getAssignments() {
		ArrayList<TeacherAssignment> tas = new ArrayList<TeacherAssignment>();
		try {
			ResultSet rs = stmt6.executeQuery();
			while (rs.next()) {
				TeacherAssignment ta = new TeacherAssignment();
				ta.setTid(rs.getString(1));
				ta.setCid(rs.getString(2));
				ta.setSid(rs.getString(3));
				tas.add(ta);
				ta.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tas;
	}
}
