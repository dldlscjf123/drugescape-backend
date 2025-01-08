package com.lee.drugescape.drugescape;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DrugescapeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DrugescapeApplication.class, args);
	}

}
