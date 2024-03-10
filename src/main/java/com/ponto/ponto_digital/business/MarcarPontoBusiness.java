package com.ponto.ponto_digital.business;

import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.ponto.ponto_digital.exception.PontoException;
import com.ponto.ponto_digital.gateway.MarcarPontoGateway;
import com.ponto.ponto_digital.gateway.UsuarioGateway;
import com.ponto.ponto_digital.model.dto.MarcarPonto;
import com.ponto.ponto_digital.model.dto.Usuario;

@Component
public class MarcarPontoBusiness {
    private final UsuarioGateway usuarioGateway;

    private final MarcarPontoGateway marcarPontoGateway;

    @Autowired
    public MarcarPontoBusiness(UsuarioGateway usuarioGateway, MarcarPontoGateway marcarPontoGateway) {
        this.usuarioGateway = usuarioGateway;
        this.marcarPontoGateway = marcarPontoGateway;
    }

    public MarcarPonto baterPonto(MarcarPonto marcarPonto, UUID usuario_id){

        Usuario usuarioDb = usuarioGateway.findById(usuario_id);

        if (Objects.isNull(usuarioDb)) {
            throw new PontoException("Não existe usuario com este id",
                    HttpStatus.BAD_REQUEST);
        }

        marcarPonto.setUsuario(usuarioDb);
        return marcarPontoGateway.baterPonto(marcarPonto);
    }

    public Page<MarcarPonto> findAllPontos(Pageable pageable){
        return marcarPontoGateway.findAll(pageable);
    }

}
