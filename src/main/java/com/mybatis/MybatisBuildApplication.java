package com.mybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@SpringBootApplication
@ComponentScan("com.mybatis")
public class MybatisBuildApplication{

	public static void main(String[] args) {
		SpringApplication.run(MybatisBuildApplication.class, args);
	}

}
