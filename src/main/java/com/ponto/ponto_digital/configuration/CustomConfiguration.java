package com.ponto.ponto_digital.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ponto.ponto_digital.config.CustomModelConfig;

@Configuration
public class CustomConfiguration {
  
  @Bean
    @ConfigurationProperties(prefix = "custom")
    public CustomModelConfig customModelConfig() {
        return new CustomModelConfig();
    }
}
