package com.ponto.ponto_digital.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;

import com.ponto.ponto_digital.model.dto.Usuario;

public class Mapper {

  private final ModelMapper modelMapper = new ModelMapper();

  public <T> T converter(Object from, Class<T> to) {
    if (Objects.isNull(from)) {
      return null;
    }

    return modelMapper.map(from, to);
  }

  public <S, T> List<T> converter(List<S> from, Class<T> to) {
    if (Objects.isNull(from) || from.isEmpty()) {
      return null;
    }

    return from.stream().map(model -> modelMapper.map(model, to)).collect(Collectors.toList());
  }

  public static HashMap<String, List<String>> attributes(Usuario usuario) {
    HashMap<String, List<String>> attributes = new HashMap<>();

    attributes.put("telefone", Collections.singletonList(usuario.getTelefone()));

    return attributes;
  }

  public CredentialRepresentation credentialRepresentation(String senha) {
    CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
    credentialRepresentation.setTemporary(false);
    credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
    credentialRepresentation.setValue(senha);

    return credentialRepresentation;
  }

  public UserRepresentation converter(Usuario usuario, Boolean isNovo) {

    if (Objects.isNull(usuario)) {
      return null;
    }
    UserRepresentation userRepresentation = new UserRepresentation();

    Optional.of(usuario).ifPresent(u -> {
      userRepresentation.setFirstName(u.getNome());
      userRepresentation.setUsername(u.getEmail());
      userRepresentation.setEmail(u.getEmail());
      userRepresentation.setEmailVerified(u.getEmailConfirmado());
      userRepresentation.setEnabled(!u.getDisabled());
      userRepresentation.setAttributes(attributes(u));

      if (isNovo) {
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation(u.getSenha())));
      }
    });

    return userRepresentation;
  }

}
