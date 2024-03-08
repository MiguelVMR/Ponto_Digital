package com.ponto.ponto_digital.model.record;

import jakarta.validation.constraints.NotBlank;

public record LoginRecord(
        @NotBlank(message = "O email é obrigatório") String email,

        @NotBlank(message = "senha é obrigatória") String password) {

}
