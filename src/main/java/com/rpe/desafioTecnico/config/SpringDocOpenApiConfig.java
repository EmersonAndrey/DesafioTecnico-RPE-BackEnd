package com.rpe.desafioTecnico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Desafio Técnico RPE")
                        .version("1.0")
                        .description("Documentação da API para controle de clientes e faturas.")
                        .contact(new Contact().name("Emerson Andrey Fausto dos Santos").email("eandrey413@gmail.com")
                                .url("https://github.com/EmersonAndrey"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
