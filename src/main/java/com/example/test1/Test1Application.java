package com.example.test1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.example.test1")
public class Test1Application {

	public static void main(String[] args) {
		SpringApplication.run(Test1Application.class, args);
	}

}
