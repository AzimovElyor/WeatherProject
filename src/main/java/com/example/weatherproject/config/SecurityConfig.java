package com.example.weatherproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final String[] WHILE_LIST = {
            "/auth/**",
            "/css/**",
            "/weather/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
      return   http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request->
                                request.requestMatchers(WHILE_LIST).permitAll()
                                        .anyRequest().authenticated()
                ).build();

    }
}
