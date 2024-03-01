package br.com.almarsantana.demokeycloakmfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoKeycloakMfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoKeycloakMfaApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
