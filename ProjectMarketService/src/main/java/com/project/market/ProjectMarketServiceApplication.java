package com.project.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProjectMarketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectMarketServiceApplication.class, args);
	}

}
