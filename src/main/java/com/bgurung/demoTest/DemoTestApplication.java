package com.bgurung.demoTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bgurung.demoTest.util.MailUtil;
import com.bgurung.demoTest.util.TimeUtil;
import com.bgurung.demoTest.HelperFunction.HelperFunction;

//import com.bgurung.demoTest.util.MailUtil;

@SpringBootApplication
public class DemoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTestApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
	public ActiveUserStore activeUserStore(){
	    return new ActiveUserStore();
	}
	@Bean
	public MailUtil mailUtil() {
		return new MailUtil();
	}
	@Bean
	public TimeUtil timeUtil() {
		return new TimeUtil();
	}
	@Bean
	public HelperFunction helperFunction() {
		return new HelperFunction();
	}
}
