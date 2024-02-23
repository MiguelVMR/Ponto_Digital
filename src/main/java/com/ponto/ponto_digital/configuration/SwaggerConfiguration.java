package com.ponto.ponto_digital.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
  
  @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(this.info());
    }

    private Info info() {
        return new Info()
                .title("Ponto Digital Swagger Application")
                .description("Ponto descrição")
                .version("1.0.0");
    }
}
