package com.ponto.ponto_digital.config.keycloack;

import lombok.Data;

@Data
public class UserKeycloakModelConfig {

  private String client;

  private String secret;

  private String realm;
}
