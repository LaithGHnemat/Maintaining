package com.maintaining.bsae;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com")).build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Maintaining Cars App REST API",
                "This is the Backend Maintaining cars APIs proved by spring boot ",
                "API TOS",
                "Terms of service",
                new Contact("(Developer name)  ", "Information about My Company ", "Developer.someone3210@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
