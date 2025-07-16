package com.bidgely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerDataGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDataGeneratorApplication.class, args);

		System.out.println("Customer Data Generator Application Started Successfully!");
	}

}
