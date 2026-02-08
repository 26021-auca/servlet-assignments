package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application Class
 * This is the entry point of the Spring Boot application
 */
@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
        System.out.println("Spring Boot RESTful API Application Started Successfully!");
        System.out.println("Access the application at: http://localhost:8080");
    }
}
