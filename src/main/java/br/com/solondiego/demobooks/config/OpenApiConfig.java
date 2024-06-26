package br.com.solondiego.demobooks.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

public class OpenApiConfig {

	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("RestFul API with Java 21 and Spring 3.3.1").version("v1")
				.description("Some description about your API").termsOfService("https://")
				.license(new License().name("Apache 2.0").url("https://")));
	}

}
