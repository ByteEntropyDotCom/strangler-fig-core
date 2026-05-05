package com.byteentropy.modern_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ModernServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModernServiceApplication.class, args);
	}

}
