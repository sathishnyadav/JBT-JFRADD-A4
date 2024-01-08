package org.jsp.springempapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springempapp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("all")
@Repository
public class EmployeeDao {
	@Autowired
	private EntityManager manager;

	public Employee saveEmployee(Employee emp) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(emp);
		transaction.begin();
		transaction.commit();
		return emp;
	}

	public Employee updateEmployee(Employee emp) {
		Employee dbEmp = manager.find(Employee.class, emp.getId());
		if (dbEmp != null) {
			EntityTransaction transaction = manager.getTransaction();
			dbEmp.setName(emp.getName());
			dbEmp.setDesg(emp.getDesg());
			dbEmp.setEmail(emp.getEmail());
			dbEmp.setPassword(emp.getPassword());
			dbEmp.setPhone(emp.getPhone());
			dbEmp.setSalary(emp.getSalary());
			transaction.begin();
			transaction.commit();
			return emp;
		}
		return null;
	}

	public Employee findById(int id) {
		return manager.find(Employee.class, id);
	}

	public Employee verifyEmployee(int id, String password) {
		Query q = manager.createQuery("select e from Employee e where e.id=?1 and e.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Employee verifyEmployee(String email, String password) {
		Query q = manager.createQuery("select e from Employee e where e.email=?1 and e.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Employee verifyEmployee(long phone, String password) {
		Query q = manager.createQuery("select e from Employee e where e.phone=?1 and e.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Employee) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Employee> findByDesg(String desg) {
		Query q = manager.createQuery("select e from Employee e where e.desg=?1");
		q.setParameter(1, desg);
		return q.getResultList();
	}
}
