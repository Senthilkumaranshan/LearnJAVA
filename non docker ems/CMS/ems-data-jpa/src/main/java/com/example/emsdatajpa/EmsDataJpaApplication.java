package com.example.emsdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EntityScan(basePackages ="com.example.emsdatajpa.model")
@EnableResourceServer
public class EmsDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmsDataJpaApplication.class, args);
	}

}
