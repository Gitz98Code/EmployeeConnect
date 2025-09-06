package com.employeeConnect.Conig_Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConigServerApplication.class, args);
	}

}
