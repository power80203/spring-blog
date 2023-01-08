package com.springblog.springblog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringblogApplication {
	
	// bean沒加就會報錯
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		/*主程式跑起來的entry point*/
		SpringApplication.run(SpringblogApplication.class, args);
		
		/*
		 * 7000*3 = 21000*12 = 210000+ 4200 = 214200 /3
		 * 
		 * */
	}

}
