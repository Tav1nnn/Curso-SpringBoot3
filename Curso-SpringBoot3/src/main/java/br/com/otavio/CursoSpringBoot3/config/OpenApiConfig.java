package br.com.otavio.CursoSpringBoot3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenApi() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Curso Spring 3")
						.version("v1")
						.termsOfService("Api Rest Full com spring 3")
						.license(new License().name("Apache 2.0").url(""))
						);
	}
}
