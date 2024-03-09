package com.ponto.ponto_digital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioView {
    
    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    private String turno;
}
