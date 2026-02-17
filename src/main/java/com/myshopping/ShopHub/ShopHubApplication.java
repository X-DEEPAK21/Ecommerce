package com.myshopping.ShopHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ShopHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopHubApplication.class, args);
	}

}
