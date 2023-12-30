package org.jsp.adminhospitalapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.adminhospitalapp.dao.AdminDao;
import org.jsp.adminhospitalapp.dao.HospitalDao;
import org.jsp.adminhospitalapp.dto.Admin;
import org.jsp.adminhospitalapp.dto.Hospital;

public class AdminHospitalController {
	private static Scanner s = new Scanner(System.in);
	private static AdminDao adminDao = new AdminDao();
	private static HospitalDao hospitalDao = new HospitalDao();

	public static void main(String[] args) {
		System.out.println("1.Save Admin");
		System.out.println("2.Update Admin");
		System.out.println("3.Find Admin By Id");
		System.out.println("4.Verify Admin By Phone and Password");
		System.out.println("5.Verify Admin By Email and password");
		System.out.println("6.Save Hospital");
		System.out.println("7.Update Hospital");
		System.out.println("8.Find Hospital By Admin Id");
		System.out.println("9.Find Hospitals By Admin Phone and password");
		System.out.println("10.Find Hospitals By Admin Email and password");
		switch (s.nextInt()) {
		case 1: {
			saveAdmin();
			break;
		}
		case 2: {
			updateAdmin();
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
			saveHospital();
			break;
		}
		case 7: {
			updateHospital();
			break;
		}
		case 8: {
			findHospitalsByAdminId();
			break;
		}
		case 9: {
			findHospitalsByAdminPhone$Pasword();
			break;
		}
		case 10: {
			findHospitalsByAdminEmail$Pasword();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void saveAdmin() {
		System.out.println("Enter the name,phone,email and password to register");
		Admin a = new Admin();
		a.setName(s.next());
		a.setPhone(s.nextLong());
		a.setEmail(s.next());
		a.setPassword(s.next());
		a = adminDao.saveAdmin(a);
		System.out.println("Admin saved with Id:" + a.getId());
	}

	public static void updateAdmin() {
		System.out.println("Enter the Admin Id to update");
		int id = s.nextInt();
		System.out.println("Enter the name,phone,email and password to update");
		Admin a = new Admin();
		a.setId(id);
		a.setName(s.next());
		a.setPhone(s.nextLong());
		a.setEmail(s.next());
		a.setPassword(s.next());
		a = adminDao.updateAdmin(a);
		if (a != null)
			System.out.println("Admin  with Id:" + a.getId() + " updated");
		else
			System.err.println("Cannot Update Admin as Id is Invalid");
	}

	public static void saveHospital() {
		System.out.println("Enter the Admin Id to add Hospital");
		int admin_id = s.nextInt();
		System.out.println("Enter the name,founder,gst number and year of establishment to add hospital");
		Hospital h = new Hospital();
		h.setName(s.next());
		h.setFounder(s.next());
		h.setGst_number(s.next());
		h.setYear_of_estb(s.nextInt());
		h = hospitalDao.saveHospital(h, admin_id);
		if (h != null)
			System.out.println("Hospital added with Id:" + h.getId());
		else
			System.err.println("cannot add hospital as Admin Id is Invalid");
	}

	public static void findById() {
		System.out.println("Enter the Admin Id to display details");
		int id = s.nextInt();
		Admin a = adminDao.findAdminById(id);
		if (a != null) {
			System.out.println("Admin Id:" + a.getId());
			System.out.println("Name:" + a.getName());
			System.out.println("Phone Number:" + a.getPhone());
			System.out.println("Email Id:" + a.getEmail());
		} else
			System.err.println("You have entered an Invalid Admin Id");
	}

	public static void verifyByPhone() {
		System.out.println("Enter the Phone Number and password to verify Admin");
		long phone = s.nextLong();
		String password = s.next();
		Admin a = adminDao.verifyAdmin(phone, password);
		if (a != null) {
			System.out.println("Verification Succesfull");
			System.out.println("Admin Id:" + a.getId());
			System.out.println("Name:" + a.getName());
			System.out.println("Phone Number:" + a.getPhone());
			System.out.println("Email Id:" + a.getEmail());
		} else
			System.err.println("Invalid Phone Number or Password");
	}

	public static void verifyByEmail() {
		System.out.println("Enter the Email Id and password to verify Admin");
		String email = s.next();
		String password = s.next();
		Admin a = adminDao.verifyAdmin(email, password);
		if (a != null) {
			System.out.println("Verification Succesfull");
			System.out.println("Admin Id:" + a.getId());
			System.out.println("Name:" + a.getName());
			System.out.println("Phone Number:" + a.getPhone());
			System.out.println("Email Id:" + a.getEmail());
		} else
			System.err.println("Invalid Email Id or Password");
	}

	public static void updateHospital() {
		System.out.println("Enter the Hospital Id,name,founder,gst_number and year of establishment to update");
		Hospital h = new Hospital();
		h.setId(s.nextInt());
		h.setName(s.next());
		h.setFounder(s.next());
		h.setGst_number(s.next());
		h.setYear_of_estb(s.nextInt());
		h = hospitalDao.updateHospital(h);
		if (h != null)
			System.out.println("Hospital  with Id:" + h.getId() + " update");
		else
			System.err.println("cannot update hospital as Hospital Id is Invalid");
	}

	public static void findHospitalsByAdminId() {
		System.out.println("Enter the Admin Id to display hospital details");
		int admin_id = s.nextInt();
		List<Hospital> hospitals = hospitalDao.findHospitalByAdminId(admin_id);
		if (hospitals.size() > 0) {
			for (Hospital h : hospitals) {
				System.out.println("Hospital Id:" + h.getId());
				System.out.println("Hospital name:" + h.getName());
				System.out.println("Hospital Founder:" + h.getFounder());
				System.out.println("GST Number:" + h.getGst_number());
				System.out.println("Year of Establishment:" + h.getYear_of_estb());
				System.out.println("---------------------------------");
			}
		} else {
			System.err.println("Invalid Admin Id");
		}
	}

	public static void findHospitalsByAdminPhone$Pasword() {
		System.out.println("Enter the Admin phone and password to display hospital details");
		long phone = s.nextLong();
		String password = s.next();
		List<Hospital> hospitals = hospitalDao.findHospitals(phone, password);
		if (hospitals.size() > 0) {
			for (Hospital h : hospitals) {
				System.out.println("Hospital Id:" + h.getId());
				System.out.println("Hospital name:" + h.getName());
				System.out.println("Hospital Founder:" + h.getFounder());
				System.out.println("GST Number:" + h.getGst_number());
				System.out.println("Year of Establishment:" + h.getYear_of_estb());
				System.out.println("---------------------------------");
			}
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void findHospitalsByAdminEmail$Pasword() {
		System.out.println("Enter the Admin email and password to display hospital details");
		String email = s.next();
		String password = s.next();
		List<Hospital> hospitals = hospitalDao.findHospitals(email, password);
		if (hospitals.size() > 0) {
			for (Hospital h : hospitals) {
				System.out.println("Hospital Id:" + h.getId());
				System.out.println("Hospital name:" + h.getName());
				System.out.println("Hospital Founder:" + h.getFounder());
				System.out.println("GST Number:" + h.getGst_number());
				System.out.println("Year of Establishment:" + h.getYear_of_estb());
				System.out.println("---------------------------------");
			}
		} else {
			System.err.println("Invalid Email Id or Password");
		}
	}
}
