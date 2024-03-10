package com.ponto.ponto_digital.model.Schemas;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ponto.ponto_digital.model.enums.TipoPontoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "maracar_ponto")
public class MarcarPontoSchema {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "data_hora_ponto")
    private LocalDateTime dataHoraPonto;

    @Enumerated(EnumType.STRING)
    private TipoPontoEnum tipo; 

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioSchema usuario;

    
}
