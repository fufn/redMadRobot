package com.bootcamp.testTask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();
        http
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .defaultSuccessUrl("/profile")
                            .permitAll();
                });
        return http.build();
    }

}
