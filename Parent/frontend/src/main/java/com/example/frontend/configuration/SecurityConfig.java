package com.example.frontend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/register").permitAll() // Permet l'accès sans authentification
                                .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                )
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/home", true) // Redirige vers /home après une connexion réussie
                                .permitAll() // Permet l'accès à la page de connexion à tout le monde
                )
                .logout(logout ->
                        logout
                                .permitAll() // Permet à tout le monde de se déconnecter
                );

        return http.build();
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }     }