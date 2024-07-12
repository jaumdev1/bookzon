package com.example.bookzon;

import com.example.bookzon.Main.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BookzonApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookzonApplication.class, args);
	}

}
