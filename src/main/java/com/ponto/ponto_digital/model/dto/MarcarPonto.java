package com.ponto.ponto_digital.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ponto.ponto_digital.model.enums.TipoPontoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcarPonto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    private LocalDateTime dataHoraPonto = LocalDateTime.now();

    private TipoPontoEnum tipo; 

    @JsonIgnore
    private Usuario usuario;
}
