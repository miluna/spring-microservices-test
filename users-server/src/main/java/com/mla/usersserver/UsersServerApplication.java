package com.mla.usersserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UsersServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServerApplication.class, args);
	}
}
