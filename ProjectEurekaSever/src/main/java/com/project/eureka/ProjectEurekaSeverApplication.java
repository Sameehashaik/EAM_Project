package com.project.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ProjectEurekaSeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEurekaSeverApplication.class, args);
	}

}
