package com.mla.productsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsServerApplication.class, args);
	}
}
