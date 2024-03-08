package com.ponto.ponto_digital.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean disabled = false;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private String turno;

    private String email;

    private String senha;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean emailConfirmado = false;

    @JsonIgnore
    private String keyCloakUserId;
}
