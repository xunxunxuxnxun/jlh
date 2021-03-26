package com.bnu.jlh.application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@EnableAutoConfiguration
@MapperScan(basePackages="com.bnu.jlh.application.dao")
public class JlhApplication {
	public static void main(String[] args) {
		SpringApplication.run(JlhApplication.class, args);
	}
}
