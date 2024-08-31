package com.service.booking.rental.platform.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Booking Rental Platform")
                        .version("1.0.0")
                        .description("Booking service for a property rental platform")
                        .termsOfService("https://rpainsiders.com.br/terms")
                        .license(new License().name("Apache 2.0").url("https://rpainsiders.com.br/license"))
                );
    }

}
