package com.cachenote.server.security.config;


import com.cachenote.server.security.filter.ExceptionHandlerFilter;
import com.cachenote.server.security.filter.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true) //todo: set the value in prod
public class SecurityConfig {
    private static final String[] SWAGGER_PATHS = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**", "/webjars/swagger-ui/**", "/context-path/swagger-ui.html", "/context-path/v3/api-docs", "/api-docs"};


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final ExceptionHandlerFilter exceptionHandlerFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        //todo: this should be set as environment variable
                        config.setAllowedOrigins(Collections.singletonList("http://localhost"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(List.of("Authorization")); //todo: what does this ean?
                        config.setMaxAge(3600L);
                        return config;
                    }
                })
                .and().csrf().disable()//todo: set csrf() in prod: ignoreingRequestMatch
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll() //authorized all the target here
                .requestMatchers(SWAGGER_PATHS).permitAll()
                .anyRequest().authenticated()

                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // do not store any state

                .and().authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionHandlerFilter, DisableEncodeUrlFilter.class);

        return http.build();
    }


}
