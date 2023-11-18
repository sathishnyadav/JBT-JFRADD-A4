package org.jsp.userapp.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.jsp.userapp.dto.User;

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

	public String saveUser(User u) {
		String qry = "insert into user values(?,?,?,?,?)";
		try {
			pst = con.prepareStatement(qry);
			pst.setInt(1, u.getId());
			pst.setString(2, u.getName());
			pst.setLong(3, u.getPhone());
			pst.setString(4, u.getEmail());
			pst.setString(5, u.getPassword());
			pst.executeUpdate();
			return "user saved";
		} catch (SQLException e) {
			return "Unable to save user";
		}

	}

	public String updateUser(User u) {
		String qry = "update user set name=?,phone=?,email=?,password=? where id=?";
		try {
			pst = con.prepareStatement(qry);
			pst.setInt(5, u.getId());
			pst.setString(1, u.getName());
			pst.setLong(2, u.getPhone());
			pst.setString(3, u.getEmail());
			pst.setString(4, u.getPassword());
			pst.executeUpdate();
			return "user updated";
		} catch (SQLException e) {
			return "Unable to update user";
		}

	}

	public User verifyUser(long phone, String password) {
		String qry = "select * from user where phone=? and password=?";
		try {
			pst = con.prepareStatement(qry);
			pst.setLong(1, phone);
			pst.setString(2, password);
			rs = pst.executeQuery();
			User u = new User();
			if (rs.next()) {
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPhone(rs.getLong(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				return u;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public User verifyUser(String email, String password) {
		String qry = "select * from user where email=? and password=?";
		try {
			pst = con.prepareStatement(qry);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			User u = new User();
			if (rs.next()) {
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPhone(rs.getLong(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				return u;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteUser(int id) {
		String sql = "delete from user where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			int r = pst.executeUpdate();
			if (r == 1)
				return true;
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User findUserById(int id) {
		String sql = "select * from user where id=?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			User u = new User();
			if (rs.next()) {
				u.setId(rs.getInt(1));
				u.setName(rs.getString(2));
				u.setPhone(rs.getLong(3));
				u.setEmail(rs.getString(4));
				u.setPassword(rs.getString(5));
				return u;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
