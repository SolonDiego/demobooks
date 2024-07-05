package br.com.solondiego.demobooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("RestFul API com Java 21 e Spring 3.3.1").version("v1")
				.description("Descrições sobre a API").termsOfService("https://")
				.license(new License().name("Apache 2.0").url("https://")));
	}

}
