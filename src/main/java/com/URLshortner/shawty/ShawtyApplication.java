package com.URLshortner.shawty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShawtyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShawtyApplication.class, args);
	}

}
