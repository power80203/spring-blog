package com.springblog.springblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



//這個配置就是spring security 幫你直接配置好相關要的

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	protected void confiqure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http
			 .csrf().disable()
			 .authorizeRequests()
			 .anyRequest()
			 .authenticated()
			 .and()
			 .httpBasic();
		

	}

}
