package com.ponto.ponto_digital.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class PontoException extends RuntimeException{

  private final String message;

  private final HttpStatus httpStatus;

  public PontoException(String message, HttpStatus httpStatus) {
      this.message = message;
      this.httpStatus = httpStatus;
  }
}
