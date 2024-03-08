package com.ponto.ponto_digital.config;

import com.ponto.ponto_digital.config.auth.AuthenticationConfig;
import com.ponto.ponto_digital.config.keycloack.KeycloakModelConfig;

import lombok.Data;

@Data
public class CustomModelConfig {

  private KeycloakModelConfig keycloak;

  private AuthenticationConfig auth;
}
