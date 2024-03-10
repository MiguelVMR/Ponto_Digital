package com.ponto.ponto_digital.business;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ponto.ponto_digital.gateway.UsuarioGateway;
import com.ponto.ponto_digital.model.dto.MarcarPonto;
import com.ponto.ponto_digital.model.dto.UsuarioView;
import com.ponto.ponto_digital.utils.Mapper;

@Component
public class UsuarioBusiness {
    
    private final Mapper mapper = new Mapper();

    private final UsuarioGateway usuarioGateway;

    @Autowired
    public UsuarioBusiness(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    
    public UsuarioView findByUsuario(UUID id){
        
       UsuarioView usuario = usuarioGateway.findByIdAndDisabled(id);

        return usuario;
    }
    
    public void deleteUser(UUID id){
        usuarioGateway.deleteUsuario(id);
    }

    public Page<MarcarPonto> findallPonto(UUID usuario_id,Pageable pageable){
        
        return usuarioGateway.findAllPontos(usuario_id, pageable);
    }

   

}
