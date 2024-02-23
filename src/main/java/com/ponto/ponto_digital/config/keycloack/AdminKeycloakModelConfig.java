package com.ponto.ponto_digital.config.keycloack;

import lombok.Data;

@Data
public class AdminKeycloakModelConfig {

  private String client;

  private String secret;

  private String username;

  private String password;

  private String realm;
}
