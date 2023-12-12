package org.jsp.employeeapp.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsp.employeeapp.dto.Employee;

public class EmployeeDao {
	Session s = new Configuration().configure().buildSessionFactory().openSession();

	public Employee saveEmployee(Employee employee) {
		Transaction t = s.beginTransaction();
		s.save(employee);
		t.commit();
		return employee;
	}

	public Employee updateEmployee(Employee employee) {
		Employee dbEmployee = s.get(Employee.class, employee.getId());
		if (dbEmployee != null) {
			Transaction t = s.beginTransaction();
			dbEmployee.setName(employee.getName());
			dbEmployee.setDesg(employee.getDesg());
			dbEmployee.setPhone(employee.getPhone());
			dbEmployee.setSalary(employee.getSalary());
			dbEmployee.setPassword(employee.getPassword());
			t.commit();
			return dbEmployee;
		}
		return null;
	}

	public Employee findById(int id) {
		return s.get(Employee.class, id);
	}

	public boolean deleteEmployee(int id) {
		Employee e = findById(id);
		if (e != null) {
			s.delete(e);
			Transaction t = s.beginTransaction();
			t.commit();
			return true;
		}
		return false;
	}

	public Employee verifyEmployee(long phone, String password) {
		String hql = "select e from Employee e where e.phone=?1 and e.password=?2";
		Query<Employee> q = s.createQuery(hql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
