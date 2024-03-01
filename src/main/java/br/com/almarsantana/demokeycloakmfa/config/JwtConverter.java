package br.com.almarsantana.demokeycloakmfa.config;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import org.springframework.security.oauth2.jwt.Jwt;

public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(@Nullable Jwt source) {

        if (source == null) {
            return new JwtAuthenticationToken(source, List.of());
        }

        Map<String, Collection<String>> realmAccess = source.getClaim("realm_access");
        Collection<String> roles = realmAccess.get("roles");
        var grants = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();

        return new JwtAuthenticationToken(source, grants);
    }

}
