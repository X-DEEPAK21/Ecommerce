package com.myshopping.ShopHub.Configuration;

import com.myshopping.ShopHub.Security.JwtVerify;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
public class SpringSecurityConfig {
    @Autowired
    JwtAuthentication jwtAuthentication;
    @Autowired
    jwtAccesshandler jwtAccesshandler;
    @Autowired
    JwtVerify jwtVerify;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity){
      httpSecurity.csrf((scrf)->{
         scrf.disable();
      }).sessionManagement((session)->
           session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .exceptionHandling((exception)->{
                 exception.authenticationEntryPoint(jwtAuthentication)
                         .accessDeniedHandler(jwtAccesshandler);
              })
              .authorizeHttpRequests((Request)->{
                  Request.requestMatchers("/app/user/register","/app/user/login","/app/user/getUser").permitAll();
                  Request.requestMatchers("/login/user/**").hasRole("USER");
                  Request.anyRequest().authenticated();
              })
              .addFilterBefore(jwtVerify, UsernamePasswordAuthenticationFilter.class);
          return httpSecurity.build();

        }

        @Bean
        public AuthenticationManager getAuthentication(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();
        }

    }

