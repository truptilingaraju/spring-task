package com.ty.dto;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ty")
public class EmployeeConfig {
	
	@Bean(value = "scanner")
	public Scanner getScanner() {
		return new Scanner(System.in);
	}

	@Bean
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("vikas").createEntityManager();
	}
	
	
}
