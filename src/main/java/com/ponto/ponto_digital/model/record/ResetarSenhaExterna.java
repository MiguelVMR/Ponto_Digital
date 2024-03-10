package com.ponto.ponto_digital.model.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetarSenhaExterna(
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "Email está no formado invalido")
        String email,

        @NotBlank(message = "O token é obrigatório")
        String token,

        @NotBlank(message = "A senha nova é obrigatória")
        String senha
) {
    
}
