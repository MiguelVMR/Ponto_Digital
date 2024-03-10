package com.ponto.ponto_digital.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;    

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean disabled = false;

    @NotBlank(message = "O nome da Pessoa é obrigatório")
    private String nome;
    
    @NotBlank(message = "O sobrenome da Pessoa é obrigatório")
    private String sobrenome;

    private String cpf;

    @NotBlank(message = "O telefone para contato com o usuario  obrigatório")
    @Pattern(regexp = "\\d{10}|\\d{11}", message = "O telefone deve possuir apenas 11 caracteres númericos")
    @Schema(defaultValue = "35987654321")
    private String telefone;

    private String turno;

    private String email;

    private String senha;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean emailConfirmado = false;

    @JsonIgnore
    private String keyCloakUserId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt = LocalDateTime.now();
   
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime disabledAt;

    @JsonIgnore
    private String tokenSenha;

    public void setDisabled(Boolean disabled) {
        if (disabled) {
            this.updatedAt = LocalDateTime.now();
            this.disabledAt = LocalDateTime.now();
        } else {
            this.updatedAt = LocalDateTime.now();
            this.disabledAt = null;
        }

        this.disabled = disabled;
    }
}
