package com.ponto.ponto_digital.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ponto.ponto_digital.model.dto.Usuario;

@Service
public class JavaMailService {

    private final JavaMailBuilder javaMailBuilder;

    @Autowired
    public JavaMailService(JavaMailBuilder javaMailBuilder) {
        this.javaMailBuilder = javaMailBuilder;
    }

    public void senderEmailToken(Usuario usuario, Integer token) {
        Map<String, Object> params = new HashMap<>();

        params.put("name", usuario.getNome() + " " + usuario.getSobrenome());
        params.put("token", token.toString());

        javaMailBuilder.to(usuario.getEmail())
                .subject("Token para redefinir a senha")
                .fire("autenticacao/redefinir_senha/body.ftl", params);
    }

}
