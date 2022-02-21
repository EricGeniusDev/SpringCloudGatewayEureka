package com.genius.tokenservice.config;

import java.util.stream.Collectors;

import com.genius.tokenservice.enumertaros.RoleEnum;
import com.genius.tokenservice.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtils jwtUtils;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        final String authToken = authentication.getCredentials().toString();
        final String username = jwtUtils.getUsernameFromToken(authToken);
        return Mono.just(jwtUtils.validateToken(authToken))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(valid -> {
                    var roles = jwtUtils.getAllClaimsFromToken(authToken).get("role").asList(RoleEnum.class);
                    return new UsernamePasswordAuthenticationToken(username,
                            null,
                            roles.stream().map(role -> role.name()).map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList()));
                });
    }

}
