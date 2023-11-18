package org.jsp.userapp.controller;

import java.sql.SQLException;
import java.util.Scanner;

import org.jsp.userapp.dao.UserDao;
import org.jsp.userapp.dto.User;

public class UserController {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		UserDao dao = new UserDao();
		System.out.println("Enter Your Choice");
		boolean flag = true;
		while (flag) {
			System.out.println("1.Save User");
			System.out.println("2.Update User");
			System.out.println("3.Verify User by phone and password");
			System.out.println("4.Verify User by email and password");
			System.out.println("5.Find user By Id");
			System.out.println("6.delete User By Id");
			System.out.println("7.Exit");
			switch (s.nextInt()) {
			case 1: {
				System.out.println("Enter the use id,name,phone,email and password to save user");
				int id = s.nextInt();
				String name = s.next();
				long phone = s.nextLong();
				String email = s.next();
				String password = s.next();
				User u = new User(id, name, phone, email, password);
				String message = dao.saveUser(u);
				System.out.println(message);
				break;
			}
			case 2: {
				System.out.println("Enter the use id,name,phone,email and password to update user");
				int id = s.nextInt();
				String name = s.next();
				long phone = s.nextLong();
				String email = s.next();
				String password = s.next();
				User u = new User(id, name, phone, email, password);
				String message = dao.updateUser(u);
				System.out.println(message);
				break;
			}

			case 3: {
				System.out.println("Enter the phone Number and password to verify user");
				long phone = s.nextLong();
				String password = s.next();
				User u = dao.verifyUser(phone, password);
				if (u != null) {
					System.out.println("User Verified Succesfully");
					System.out.println("User Id:" + u.getId());
					System.out.println("User Name:" + u.getName());
					System.out.println("Email Id:" + u.getEmail());
					System.out.println("Phone Number:" + u.getPhone());
				} else {
					System.err.println("Invalid Phone Number or Password");
				}
				break;
			}
			case 4: {
				System.out.println("Enter the Email Id and password to verify user");
				String email = s.next();
				String password = s.next();
				User u = dao.verifyUser(email, password);
				if (u != null) {
					System.out.println("User Verified Succesfully");
					System.out.println("User Id:" + u.getId());
					System.out.println("User Name:" + u.getName());
					System.out.println("Email Id:" + u.getEmail());
					System.out.println("Phone Number:" + u.getPhone());
				} else {
					System.err.println("Invalid Email Id or Password");
				}
				break;
			}
			case 5: {
				System.out.println("Enter the User id to find the user");
				int id = s.nextInt();
				User u = dao.findUserById(id);
				if (u != null) {
					System.out.println("User Found");
					System.out.println("User Id:" + u.getId());
					System.out.println("User Name:" + u.getName());
					System.out.println("Email Id:" + u.getEmail());
					System.out.println("Phone Number:" + u.getPhone());
				} else {
					System.err.println("Invalid Id");
				}
				break;
			}
			case 6: {
				System.out.println("Enter the User id to delete the record");
				int id = s.nextInt();
				boolean deleted = dao.deleteUser(id);
				if (deleted) {
					System.out.println("User deleted");
				} else {
					System.err.println("cannot delete user");
				}
				break;
			}
			case 7: {
				flag = false;
				try {
					System.out.println(dao.exit());
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}
		}

	}
}
