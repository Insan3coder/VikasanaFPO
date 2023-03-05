package com.Project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
// @EnableAutoConfiguration
@EntityScan(basePackages = "com.Project.demo")
public class ProjectV1Application {

//	@Bean(name = "entityManagerFactory")
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		return sessionFactory;
//	}


	public static void main(String[] args) {
		SpringApplication.run(ProjectV1Application.class, args);
	}

}
