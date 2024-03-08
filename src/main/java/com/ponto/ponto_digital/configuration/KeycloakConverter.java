package com.ponto.ponto_digital.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KeycloakConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    
    @Override
    public AbstractAuthenticationToken convert(Jwt source) {

        Map<String, Collection<String>> realmAccess = source.getClaim("realm_access");
        Collection<String> rolesKeycloak = realmAccess.get("roles");

        List<SimpleGrantedAuthority> grants = rolesKeycloak.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase())).toList();

        return new JwtAuthenticationToken(source, grants);
    }
}
