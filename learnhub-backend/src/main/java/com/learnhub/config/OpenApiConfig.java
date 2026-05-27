package com.learnhub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        var jwtScheme = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .description("Ingrese el token JWT");

        return new OpenAPI()
            .info(new Info()
                .title("LearnHub API")
                .description("API REST de la plataforma de cursos online LearnHub")
                .version("1.0.0")
                .contact(new Contact().name("LearnHub Team").email("contacto@learnhub.com"))
                .license(new License().name("MIT")))
            .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
            .components(new Components().addSecuritySchemes("bearer-jwt", jwtScheme));
    }
}
