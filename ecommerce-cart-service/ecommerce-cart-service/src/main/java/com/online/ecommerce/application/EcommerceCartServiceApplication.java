package com.online.ecommerce.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EntityScan("com.online.ecommerce.application.entity")
@SpringBootApplication
@EnableEurekaClient
public class EcommerceCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceCartServiceApplication.class, args);
	}

}
