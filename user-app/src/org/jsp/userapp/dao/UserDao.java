package org.jsp.userapp.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserDao {
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	{
		Properties p = new Properties();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream("D:\\Sathish\\JBT-JFRADD-A4\\jdbc.properties");
			p.load(fin);
			con = DriverManager.getConnection(p.getProperty("url"), p);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String saveUser(int id, String name, long phone, String email, String password) {
		String qry = "insert into user values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(qry);
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setLong(3, phone);
			pst.setString(4, email);
			pst.setString(5, password);
			pst.executeUpdate();
			return "user saved";
		} catch (SQLException e) {
			return "Unable to save user";
		}

	}
	public String exit() throws SQLException {
		if (con != null)
			con.close();
		if (pst != null)
			pst.close();
		if (rs != null)
			rs.close();
		return "Application Closed!!!";
	}
}
