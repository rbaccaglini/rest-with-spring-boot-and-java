package br.com.roger.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java and Springbboot")
                        .version("v1")
                        .description("RESTful API with Java 21 and Springbboot 3")
                        .termsOfService("https://pub.roger.com.br/termsOfServices")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://pub.roger.com.br/license")
                        )
                );
    }

}
