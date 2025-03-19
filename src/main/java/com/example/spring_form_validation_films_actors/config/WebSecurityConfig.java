package com.example.spring_form_validation_films_actors.config;

import com.example.spring_form_validation_films_actors.services.CustomAccessDeniedHandler;
import com.example.spring_form_validation_films_actors.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/films/get","/access-denied").permitAll()
                        .requestMatchers("/films/add", "/films/submit").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                        .requestMatchers("/countries/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/actors/**").hasAuthority("ROLE_ADMIN")//ASK WHY ADMIN DON`T WORK!
                        .requestMatchers("/results").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .usernameParameter("usernameOrEmail")
                        .permitAll()
                        .defaultSuccessUrl("/profile")
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler(accessDeniedHandler())); // Use injected instance

        return http.build();
    }
}
