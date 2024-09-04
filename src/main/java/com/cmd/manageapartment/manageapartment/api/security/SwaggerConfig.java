package com.cmd.manageapartment.manageapartment.api.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer{
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Manage Apartment API")
                        .version("1.0")
                        .description("API documentation for the Manage Apartment project")
                        .contact(new Contact()
                                .name("CMD")
                                .email("totooria11@example.com")
                                .url("http://myprojectintern.com")
                        )
                );
    }
}
