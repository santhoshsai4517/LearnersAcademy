package com.scode.admin.model;

import java.sql.*;

import com.scode.util.ConnectionUtil;

public class Admin {

	private String username;
	private String password;
	
	private Connection con;
	private PreparedStatement stmt,stmt1;

	public Admin() {
		try {
			con = ConnectionUtil.getConnection();
			stmt = con.prepareStatement("select * from admin where username = ? and password = ?");
			stmt1 = con.prepareStatement("update admin set password = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean validate() {
		boolean result = false;
		try {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			result = rs.next();
//			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int updatePassword(String password) {
		int result = 0;
		try {
			stmt1.setString(1, password);
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
