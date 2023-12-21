package com.ty.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ty.dto.Employee;

@Component
public class EmployeeDao {

	@Autowired
	EntityManager entityManager;
	
	 public Employee saveUser(Employee employee) {
			EntityTransaction t = entityManager.getTransaction();
			entityManager.persist(employee);
			t.begin();
	     	t.commit();
			return employee;
		}
		
		public Employee updateUser(Employee employee) {
			EntityTransaction t = entityManager.getTransaction();
			entityManager.merge(employee);
			t.begin();
	     	t.commit();
			return employee;
		}
		
		public Employee findUserById(int id) {
			Employee employee= entityManager.find(Employee.class, id);
			if(employee!=null)
				return employee;
			else
				return null;
		}
		
		public boolean deleteUser(int id) {
			Employee employee= findUserById(id);
			if(employee!=null) {
				entityManager.remove(employee);
				EntityTransaction t = entityManager.getTransaction();
				t.begin();
		     	t.commit();
			return true;
			}
			return false;
		}
		
		public Employee verifyUser(String email, String password) {
			
	        Query q = entityManager.createQuery("select e from Employee e where e.email=?1 and e.password=?2");
			q.setParameter(1, email);
			q.setParameter(2, password);
			try {
				return (Employee) q.getSingleResult();
			}catch(NoResultException e) {
				return null;
			}
	}
}
