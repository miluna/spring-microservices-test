package com.mla.invoiceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InvoiceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvoiceServerApplication.class, args);
	}
}
