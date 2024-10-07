package com.example.rpa.Config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.reactive.function.client.WebClient;

import java.rmi.registry.Registry;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final WebClient userInfoClient;
    public SecurityConfig(WebClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http)throws Exception{



        return http
                .cors(Customizer.withDefaults())
                .exceptionHandling(customizer->customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))

                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(registry->{
            registry.requestMatchers("/","/auth/**","/public").permitAll();
            registry.anyRequest().authenticated();
        }).oauth2ResourceServer(c->c.opaqueToken(Customizer.withDefaults()))

                .build();

    }

    @Bean
    public OpaqueTokenIntrospector interspector(){
        return new GoogleOpaqueTokenIntrospector(userInfoClient);
    }
}
