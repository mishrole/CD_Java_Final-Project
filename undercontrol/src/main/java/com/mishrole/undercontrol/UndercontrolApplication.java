package com.mishrole.undercontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mishrole.undercontrol")
public class UndercontrolApplication {

	public static void main(String[] args) {
		SpringApplication.run(UndercontrolApplication.class, args);
	}

}
