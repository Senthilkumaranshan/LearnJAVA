package com.example.springsecurityjwt.configuration;


import com.example.springsecurityjwt.security.JwtAuthenticationEntryPoint;
import com.example.springsecurityjwt.security.JwtAuthenticationProvider;
import com.example.springsecurityjwt.security.JwtAuthenticationTokenFilter;
import com.example.springsecurityjwt.security.JwtSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;


//inorder to enable web security we have to do some configuration
@EnableGlobalMethodSecurity( prePostEnabled = true) //to enable method level security
@EnableWebSecurity
@Configuration  //because it's an configuration class
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter { //extend because whatever we done here will be overridden in the default security

    private JwtAuthenticationProvider authenticationProvider;
    private JwtAuthenticationEntryPoint entryPoint;

    //we need to create custom authentication manager
    @Bean
    public AuthenticationManager authenticationManager(){

        //we need to override authentication provider with the custom provider
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    //need to create custom filter
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){

        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        //we created custom authentication provider so it will inject cutom method as authentication manager
        filter.setAuthenticationManager(authenticationManager());
        //add cutom scuccess message so we can redirect success to this handler and do any process if we needed
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());

        return filter;

    }

    //override default http security
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()  //for this instance we disable it
        .authorizeRequests().antMatchers("**/rest**").authenticated() //we authenticate all url which have  rest
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint) //we need to handle the exception if we are not authorised
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //making our http session as stateless

        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl(); // we can added default headers to the request
    }
}
