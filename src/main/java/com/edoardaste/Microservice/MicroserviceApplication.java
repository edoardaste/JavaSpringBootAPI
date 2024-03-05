package com.edoardaste.Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@SpringBootApplication
@ComponentScan({"com.edoardaste.Microservice.clientController", "com.edoardaste.Microservice.repositories"})
@EnableJpaRepositories(basePackages = "com.edoardaste.Microservice.repositories")
public class MicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://:3306/CLIENT?useSSL=false");
		dataSource.setUsername("admin");
		dataSource.setPassword("");
		return dataSource;
	}
}