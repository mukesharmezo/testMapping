package com.armezo.util.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class OpenAPIConfig {
	
	@Bean
	public OpenAPI openApiBean() {
		final String security = "bearerAuth";
		return new OpenAPI()
				.info(new Info()
						.title("Test Mapping API")
						.description("Get Test Link By Providing Profile Details")
						.termsOfService("https://www.armezosolutions.com/privacy-policy.jsp")
						.contact(new Contact().email("contact@armezosolutions.com"))
						.license(new License().url("https://www.armezosolutions.com/privacy-policy.jsp"))
						.version("1.0")
						.summary("API Summary")
						)
				.addSecurityItem(new SecurityRequirement()
						.addList(security))
				.components(new Components()
						.addSecuritySchemes(security, new SecurityScheme()
								.name(security)
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")
								));
	}

}
