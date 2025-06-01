package com.example.SpringSecurity.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private addJwtFilter addJwtfilter;

    @Bean
    public SecurityFilterChain Filter(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Register/**","/login/**").permitAll()
                        .anyRequest().authenticated()
                ).sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(addJwtfilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}
