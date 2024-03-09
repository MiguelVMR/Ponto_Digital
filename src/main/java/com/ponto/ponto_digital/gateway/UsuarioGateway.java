package com.ponto.ponto_digital.gateway;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ponto.ponto_digital.exception.PontoException;
import com.ponto.ponto_digital.model.Schemas.UsuarioSchema;
import com.ponto.ponto_digital.model.dto.Usuario;
import com.ponto.ponto_digital.model.dto.UsuarioView;
import com.ponto.ponto_digital.model.repository.UsuarioRepository;
import com.ponto.ponto_digital.utils.Mapper;

@Component
public class UsuarioGateway {
    private final Mapper mapper = new Mapper();

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioGateway(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioView findByIdAndDisabled(UUID id){
        UsuarioSchema usuarioSchema = usuarioRepository.findById(id)
            .orElseThrow(() -> new PontoException("Usuario não encontrado", HttpStatus.NOT_FOUND));
    
        if (usuarioSchema.getDisabled() == true) {
            throw new PontoException("Usuario está desabilitado", HttpStatus.UNAUTHORIZED);
        }
    
        return mapper.converter(usuarioSchema, UsuarioView.class);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(mapper.converter(usuario, UsuarioSchema.class));
    }

    public void deleteUsuario(UUID id){
        UsuarioSchema usuarioSchema = usuarioRepository.findById(id)
        .orElseThrow(() -> new PontoException("Usuario não encontrado", HttpStatus.NOT_FOUND));

        usuarioSchema.setDisabled(true);

        usuarioRepository.save(usuarioSchema);

    }

}
