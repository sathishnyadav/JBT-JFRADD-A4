package org.jsp.springempapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.springempapp.EmployeeConfig;
import org.jsp.springempapp.dao.EmployeeDao;
import org.jsp.springempapp.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("all")
public class EmployeeController {
	static EmployeeDao dao;
	static Scanner s = new Scanner(System.in);
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(EmployeeConfig.class);
		dao = context.getBean(EmployeeDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.save Employee");
		System.out.println("2.Update Employee");
		System.out.println("3.Find Employee");
		System.out.println("4.Verify Employee by phone and password");
		System.out.println("5.Verify Employee by email and password");
		System.out.println("6.Verify Employee by Id and password");
		System.out.println("7.Find Employee by desg");
		switch (s.nextInt()) {
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
			verifyByPhone();
			break;
		}
		case 5: {
			verifyByEmail();
			break;
		}
		case 6: {
			verifyById();
			break;
		}
		case 7: {
			findByDesg();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void save() {
		System.out.println("Enter the name,phone,email,salary,designation and password to register");
		Employee e = new Employee();
		e.setName(s.next());
		e.setPhone(s.nextLong());
		e.setEmail(s.next());
		e.setSalary(s.nextDouble());
		e.setDesg(s.next());
		e.setPassword(s.next());
		e = dao.saveEmployee(e);
		System.out.println("Employee saved with Id:" + e.getId());
	}

	public static void update() {
		System.out.println("Enter the Employee Id to update");
		int eid = s.nextInt();
		System.out.println("Enter the name,phone,email,salary,designation and password to Update");
		Employee e = new Employee();
		e.setId(eid);
		e.setName(s.next());
		e.setPhone(s.nextLong());
		e.setEmail(s.next());
		e.setSalary(s.nextDouble());
		e.setDesg(s.next());
		e.setPassword(s.next());
		e = dao.updateEmployee(e);
		if (e != null)
			System.out.println("Employee  with Id:" + e.getId() + " Updated");
		else
			System.err.println("Cannot Update Employee as entered Id is invalid");
	}

	public static void findById() {
		System.out.println("Enter the Employee Id to display details");
		int eid = s.nextInt();
		Employee e = dao.findById(eid);
		if (e != null) {
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesg());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("You have entered an Invalid Employee Id");
		}
	}

	public static void verifyByPhone() {
		System.out.println("Enter Your Phone Number and password to verify");
		long phone = s.nextLong();
		String password = s.next();
		Employee e = dao.verifyEmployee(phone, password);
		if (e != null) {
			System.out.println("verification succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesg());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void verifyByEmail() {
		System.out.println("Enter Your Email Id and password to verify");
		String email = s.next();
		String password = s.next();
		Employee e = dao.verifyEmployee(email, password);
		if (e != null) {
			System.out.println("verification succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesg());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Email Id or password");
		}
	}

	public static void verifyById() {
		System.out.println("Enter Your Employee Id and password to verify");
		int id = s.nextInt();
		String password = s.next();
		Employee e = dao.verifyEmployee(id, password);
		if (e != null) {
			System.out.println("verification succesfull");
			System.out.println("Employee Id:" + e.getId());
			System.out.println("Employee Name:" + e.getName());
			System.out.println("Designation:" + e.getDesg());
			System.out.println("Phone Number:" + e.getPhone());
			System.out.println("Email Id:" + e.getEmail());
			System.out.println("Salary:" + e.getSalary());
		} else {
			System.err.println("Invalid Employee Id or password");
		}
	}

	public static void findByDesg() {
		System.out.println("Enter the designation to find Employees");
		String desg = s.next();
		List<Employee> emps = dao.findByDesg(desg);
		if (emps.size() > 0)
			for (Employee e : emps) {
				System.out.println("Employee Id:" + e.getId());
				System.out.println("Employee Name:" + e.getName());
				System.out.println("Designation:" + e.getDesg());
				System.out.println("Phone Number:" + e.getPhone());
				System.out.println("Email Id:" + e.getEmail());
				System.out.println("Salary:" + e.getSalary());
			}
		else
			System.err.println("No Employee with entered Designation");
	}
}
