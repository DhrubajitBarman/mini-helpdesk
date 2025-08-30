package com.minihelpdesk.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI miniHelpdeskOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Mini Helpdesk API").description("API documentation for Mini Helpdesk project")
						.version("1.0.0").license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}
