package com.jewellery.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.jewellery.backend.Security.JwtRequestFilter;

@Configuration
public class SecurityConfig {
	
	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    
	}
    @Autowired
    private JwtRequestFilter jwtRequestFilter; // we’ll create this

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // for hashing passwords
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // needed for login
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT = no session
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/uploads/**", "/api/**").permitAll() // <-- THIS IS THE KEY
            		.requestMatchers("/api/admin/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll() // login/register open
                .requestMatchers("/api/products/add").hasRole("ADMIN") // only admin can add
                .requestMatchers("/api/products/delete/**").hasRole("ADMIN") // only admin can delete
                .requestMatchers("/api/products/**").permitAll() // everyone can view products
                .requestMatchers("/api/cart/**").hasRole("CUSTOMER") // only logged in customer
                .anyRequest().authenticated()
                
            )
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); // check JWT first

        return http.build(); // <-- return at the very end
    }
}