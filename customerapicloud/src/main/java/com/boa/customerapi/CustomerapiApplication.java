package com.boa.customerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableAutoConfiguration(exclude =DataSourceAutoConfiguration.class)
@EnableEurekaClient
public class CustomerapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerapiApplication.class, args);
	}

}
