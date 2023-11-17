package org.jsp.userapp.controller;

import java.sql.SQLException;
import java.util.Scanner;

import org.jsp.userapp.dao.UserDao;

public class UserController {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		UserDao dao = new UserDao();
		System.out.println("Enter Your Choice");
		boolean flag = true;
		while (flag) {
			System.out.println("1.Save User");
			System.out.println("2.Update User");
			System.out.println("3.Verify User by email and password");
			System.out.println("4.Verify User by phone and password");
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
				String message = dao.saveUser(id, name, phone, email, password);
				System.out.println(message);
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
