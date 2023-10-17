package com.example.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration
{
    @Configuration
    @Order(1)
    public static class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http.requestMatchers(matcher -> matcher.mvcMatchers("/actuator/**")).authorizeRequests().anyRequest().authenticated(); // -> /actuator/jolokia
            // endpoint is open
            // http.requestMatchers(matcher -> matcher.antMatchers("/actuator/**")).authorizeRequests().anyRequest().authenticated(); -> /actuator/jolokia
            // endpoint is secured
        }
    }

    @Configuration
    public static class DefaultSecurityConfig extends WebSecurityConfigurerAdapter
    {
        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http.requestMatchers(matcher -> matcher.mvcMatchers("/**")).authorizeRequests().anyRequest().permitAll();
        }
    }
}
