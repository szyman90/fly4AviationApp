package com.example.fly4AviationApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //( exclude = JpaRepositoriesAutoConfiguration.class)
public class Fly4AviationAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(Fly4AviationAppApplication.class, args);
	}
}
