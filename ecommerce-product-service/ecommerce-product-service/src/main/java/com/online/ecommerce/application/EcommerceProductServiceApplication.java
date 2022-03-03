package com.online.ecommerce.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.online.ecommerce.application.entity")
@SpringBootApplication
public class EcommerceProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceProductServiceApplication.class, args);
	}

}
