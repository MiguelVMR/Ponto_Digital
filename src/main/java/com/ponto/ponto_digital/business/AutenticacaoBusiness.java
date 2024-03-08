package com.ponto.ponto_digital.business;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.keycloak.representations.AccessTokenResponse;
import com.ponto.ponto_digital.exception.PontoException;
import com.ponto.ponto_digital.gateway.KeycloakGateway;
import com.ponto.ponto_digital.model.dto.Usuario;
import com.ponto.ponto_digital.model.record.LoginRecord;
import com.ponto.ponto_digital.utils.Mapper;

import jakarta.ws.rs.core.Response;

@Component
public class AutenticacaoBusiness {
    private final Mapper mapper = new Mapper();

    private final KeycloakGateway keycloakGateway;

    @Autowired
    public AutenticacaoBusiness(KeycloakGateway keycloakGateway) {
        this.keycloakGateway = keycloakGateway;
    }

    public void criarUsuario(Usuario usuario) {
        UserRepresentation userRepresentation = mapper.converter(usuario, true);

        Response response = keycloakGateway.getUsersResource().create(userRepresentation);

        if (response.getStatus() != 201) {
            throw new PontoException("Erro inesperado no Keycloak", HttpStatus.BAD_REQUEST);
        }

        String location = response.getMetadata().get("Location").toString().replace("]", "");
        Integer index = location.lastIndexOf("users/");

        usuario.setKeyCloakUserId(location.substring(index + "users/".length()));
    }

    public AccessTokenResponse login(LoginRecord loginRecord) {

        AccessTokenResponse accessToken = keycloakGateway.getAccessToken(loginRecord.email(), loginRecord.password());

        return accessToken;
    }

}