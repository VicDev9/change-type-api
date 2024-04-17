package com.desafio.api.tipocambio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TipocambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipocambioApplication.class, args);
	}

}
