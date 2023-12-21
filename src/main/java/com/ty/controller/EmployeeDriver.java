package com.ty.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ty.dao.EmployeeDao;
import com.ty.dto.Employee;
import com.ty.dto.EmployeeConfig;

public class EmployeeDriver {

	@Autowired
	static Scanner scanner;
	
	static Employee employee;
	static EmployeeDao dao;
	static ApplicationContext context;
	static {
	  context=new AnnotationConfigApplicationContext(EmployeeConfig.class);
	}
	
	public static void main(String[] args) {
//		ApplicationContext context=new AnnotationConfigApplicationContext(EmployeeConfig.class);
		scanner=(Scanner)context.getBean("scanner");
		dao=context.getBean(EmployeeDao.class);
		boolean flag=true;
		while (flag) {
			System.out.println("1.save employee");
			System.out.println("2.update employee");
			System.out.println("3.find employee");
			System.out.println("4.delete employee");
			System.out.println("5.verify employee by email and password");
			switch (scanner.nextInt()) {
			case 1:
				save();
				break;
			case 2:
				update();
				break;
			case 3:
				find();
				break;
			case 4:
				delete();
				break;
			case 5:
				verify();
				break;
			default:
				break;
			}
		}
	}
	
	public static void save() {
//		ApplicationContext context=new AnnotationConfigApplicationContext(EmployeeConfig.class);
		employee=(Employee)context.getBean("employee");
		System.out.println("enter employee details name,email,password,salary");
		String name=scanner.next();
		String email=scanner.next();
		String password=scanner.next();
		int salary=scanner.nextInt();
		
	   employee.setName(name);
	   employee.setEmail(email);
	   employee.setPassword(password);
	   employee.setSalary(salary);
	   
	   Employee employee1 =dao.saveUser(employee);
	   System.out.println("user saved successfully:" + employee1 );
	}
	
	
	public static void find() {
		System.out.println("enter an id to search");
		int id=scanner.nextInt();
		Employee employee=dao.findUserById(id);
		if(employee!=null) {
			System.out.println(employee);
		}
		else
			System.out.println("employee not found with id");
		
	}
	
	public static void delete() {
		System.out.println("enter an id to delete");
		int id=scanner.nextInt();
		boolean delete=	dao.deleteUser(id);
		if(delete)
			System.out.println("deleted successfully");
		else
			System.out.println("employee not found with id");
		
	}
	
	public static void verify() {
		System.out.println("enter an email and password to verify");
		String email=scanner.next();
		String password=scanner.next();
		Employee employee=dao.verifyUser(email, password);
		if(employee!=null) {
			System.out.println(employee);
		}
		else {
			System.out.println("invalid email or password");
		}
	}
	
	public static void update() {
		System.out.println("enter an id to update");
		int id=scanner.nextInt();
		Employee employee=dao.findUserById(id);
		if(employee!=null) {
			System.out.println("enter employee details name,email,password,salary");
			String name=scanner.next();
			String email=scanner.next();
			String password=scanner.next();
			int salary=scanner.nextInt();
			
			 employee.setName(name);
			 employee.setEmail(email);
			 employee.setPassword(password);
			 employee.setSalary(salary);
			 
			 Employee employee2=dao.updateUser(employee);
			 if(employee2!=null) {
				 System.out.println("updated successfully");
				 System.out.println(employee2);
			 }
			 else
				 System.out.println("employee not found with the id");
		}
		else
			 System.out.println("employee not found with the id");
		
	}
}
