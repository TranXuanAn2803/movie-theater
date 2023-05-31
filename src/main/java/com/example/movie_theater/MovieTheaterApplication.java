package com.example.movie_theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class MovieTheaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

}
