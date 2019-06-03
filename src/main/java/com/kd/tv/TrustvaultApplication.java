package com.kd.tv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.kd.tv")
public class TrustvaultApplication {

	public static void main(String[] args) {
		System.out.println("***** TrustvaultApplication.main()");
		SpringApplication.run(TrustvaultApplication.class, args);
	}

}
