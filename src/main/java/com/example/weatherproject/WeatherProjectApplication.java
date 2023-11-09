package com.example.weatherproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
@ApiResponses(

)
public class WeatherProjectApplication {

    public static void main(String[] args) {
        System.out.println("Hello");
        SpringApplication.run(WeatherProjectApplication.class, args);
    }

}
