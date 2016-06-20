package com.fashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.fashion")
public class FashionRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FashionRentApplication.class, args);
	}
}
