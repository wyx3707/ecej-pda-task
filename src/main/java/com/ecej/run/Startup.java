package com.ecej.run;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ecej")
@MapperScan("com.ecej.mapper")
public class Startup    {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}
}
