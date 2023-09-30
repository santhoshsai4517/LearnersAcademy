package com.scode.admin.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.scode.util.ConnectionUtil;

public class SubjectAssignment {
	private String sid;
	private String cid;

	private Connection con;
	private PreparedStatement stmt, stmt1, stmt2, stmt3, stmt4;

	public SubjectAssignment() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from subjects where id = ?");
			stmt1 = con.prepareStatement("select * from classes where id = ?");
			stmt2 = con.prepareStatement("select * from classsubjects where subject_id = ?");
			stmt3 = con.prepareStatement("insert into classsubjects values (?,?,?)");
			stmt4 = con.prepareStatement("select * from classsubjects");
//			stmt3 = con.prepareStatement("select * from subjects");

		} catch (SQLException e) {
			e.printStackTrace();
		}

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
		return "SubjectAssignment [Subjetc ID=" + sid + ", is assigned to Class ID=" + cid + "]";
	}

	public int assignSubject() {
		int result = 0;
		try {
			stmt.setString(1, sid);
			ResultSet rs = stmt.executeQuery();
			if (!rs.next())
				return -1;
			stmt1.setString(1, cid);
			rs = stmt1.executeQuery();
			if (!rs.next())
				return -2;
			stmt2.setString(1, sid);
			rs = stmt2.executeQuery();
			if (rs.next())
				return -3;
			Random random = new Random();
			String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			int length = 6;
			StringBuilder randomText = new StringBuilder();
			for (int i = 0; i < length; i++) {
				int randomIndex = random.nextInt(characters.length());
				randomText.append(characters.charAt(randomIndex));
			}

			stmt3.setString(1, randomText.toString());
			stmt3.setString(2, cid);
			stmt3.setString(3, sid);
			result = stmt3.executeUpdate();
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

	public ArrayList<SubjectAssignment> getAssignments() {
		ArrayList<SubjectAssignment> sas = new ArrayList<SubjectAssignment>();
		try {
			ResultSet rs = stmt4.executeQuery();
			while (rs.next()) {
				SubjectAssignment sa = new SubjectAssignment();
				sa.setCid(rs.getString(2));
				sa.setSid(rs.getString(3));
				sas.add(sa);
				sa.destroy();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sas;
	}
}
