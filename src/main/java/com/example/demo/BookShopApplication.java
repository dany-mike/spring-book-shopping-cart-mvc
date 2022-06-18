package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookShopApplication implements CommandLineRunner {

	// TODO: follow this tutorial
	// https://www.codejava.net/frameworks/spring-boot/spring-security-forgot-password-tutorial
	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello Spring");
	}
}
