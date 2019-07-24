package com.hello2pal.socialMediaApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class APIConfiguration {

    @Bean
    public Docket socialMediaAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hello2pal.socialMediaApp.controller"))
                .paths(regex("/api/v1.0.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo("A simple Social Media application(Maven Project).",
                "A simple Social Media application(Maven Project)",
                "v1.0",
                "Terms of Services",
                "Hello 2 pal",
                "Apache Licence",
                "url"
        );

        return apiInfo;
    }
}
