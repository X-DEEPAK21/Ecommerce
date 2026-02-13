package com.myshopping.ShopHub.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity){
      httpSecurity.csrf((scrf)->{
         scrf.disable();
      }).sessionManagement((session)->
           session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authorizeHttpRequests((Request)->{
                  Request.requestMatchers("/app/user/register","/app/user/login").permitAll();
                  Request.requestMatchers("/login/user/**").hasRole("USER");
                  Request.anyRequest().authenticated();
              });
          return httpSecurity.build();

        }
    }

