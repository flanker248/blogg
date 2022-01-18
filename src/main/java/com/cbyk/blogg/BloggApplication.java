package com.cbyk.blogg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class BloggApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggApplication.class, args);
	}

}
