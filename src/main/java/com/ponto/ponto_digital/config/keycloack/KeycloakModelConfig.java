package com.ponto.ponto_digital.config.keycloack;

import lombok.Data;

@Data
public class KeycloakModelConfig {
  
  private String url;
  
  private AdminKeycloakModelConfig admin;

  private UserKeycloakModelConfig user;
}
