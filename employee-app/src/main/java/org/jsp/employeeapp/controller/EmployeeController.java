package org.jsp.employeeapp.controller;

import java.util.Scanner;

import org.jsp.employeeapp.dao.EmployeeDao;
import org.jsp.employeeapp.dto.Employee;

public class EmployeeController {
	static EmployeeDao dao = new EmployeeDao();
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1.Save Employee");
		System.out.println("2.Update Employee");
		System.out.println("3.Find Employee By Id");
		System.out.println("4.delete Employee By id");
		System.out.println("5.Verify Employee By Phone and password");

		int choice = s.nextInt();
		switch (choice) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			delete();
			break;
		}
		case 5: {
			verify();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void save() {
		System.out.println("Enter the name,phone,designation,salary and password of employee to save");
		Employee e = new Employee();
		e.setName(s.next());
		e.setPhone(s.nextLong());
		e.setDesg(s.next());
		e.setSalary(s.nextDouble());
		e.setPassword(s.next());
		e = dao.saveEmployee(e);
		System.out.println("Employee saved with Id:" + e.getId());
	}

	public static void update() {
		System.out.println("Enter the Employee Id to update");
		int id = s.nextInt();
		System.out.println("Enter the name,phone,designation,salary and password of employee to save");
		Employee e = new Employee();
		e.setId(id);
		e.setName(s.next());
		e.setPhone(s.nextLong());
		e.setDesg(s.next());
		e.setSalary(s.nextDouble());
		e.setPassword(s.next());
		e = dao.updateEmployee(e);
		if (e != null)
			System.out.println("Employee Updated");
		else
			System.err.println("Cannot update as Id is Invalid");
	}

	public static void findById() {
		System.out.println("Enter the Employee Id to delete record");
		int eid = s.nextInt();
		Employee e = dao.findById(eid);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Designation:" + e.getDesg());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("You have entered an Invalid Id");
		}
	}

	public static void delete() {
		System.out.println("Enter the Employee Id to delete record");
		int eid = s.nextInt();
		boolean deleted = dao.deleteEmployee(eid);
		if (deleted) {
			System.out.println("Employee deleted");
		} else {
			System.err.println("You have entered an Invalid Id");
		}
	}

	public static void verify() {
		System.out.println("Enter the Phone Number and password to verify Employee");
		long phone = s.nextLong();
		String password = s.next();
		Employee e = dao.verifyEmployee(phone, password);
		if (e != null) {
			System.out.println("Verification Succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Designation:" + e.getDesg());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}
}
