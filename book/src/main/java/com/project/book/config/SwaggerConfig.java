package com.project.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI openAPI() {
		String auth = "JWT";
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(auth);

		Components components = new Components()
			.addSecuritySchemes(
				auth,
				new SecurityScheme()
					.name(auth)
					.type(SecurityScheme.Type.HTTP)
					.scheme("Bearer")
					.bearerFormat("JWT")
			);

		return new OpenAPI()
			.components(components)
			.info(apiInfo())
			.addSecurityItem(securityRequirement);
	}

	private Info apiInfo() {
		return new Info()
			.title("개인프로젝트 Swagger API")
			.description("API 명세서")
			.version("1.0.0");
	}
}
