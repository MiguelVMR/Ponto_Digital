package com.ponto.ponto_digital.model.Schemas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class UsuarioSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Boolean disabled;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private String turno;

    private String email;

    private String senha;

    @Column(name = "email_confirmado")
    private Boolean emailConfirmado;

    @Column(name = "keycloak_user_id")
    private String keycloakUserId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime disabledAt;

    private String tokenSenha;

    @OneToMany(mappedBy = "usuario" ,cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<MarcarPontoSchema> marcarPontoSchema;

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
