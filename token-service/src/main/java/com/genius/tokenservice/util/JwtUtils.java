package com.genius.tokenservice.util;

import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.genius.tokenservice.model.User;
import com.genius.tokenservice.property.JwtProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("JwtProperty")
public class JwtUtils {

    @Autowired
    JwtProperty property;

    private Algorithm algorithm;

    @PostConstruct
    public void init(){
        this.algorithm  = Algorithm.HMAC256(property.getPrivateKey());
    }

    public String createSignedToken(User user) throws JWTCreationException {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + property.getExpiration() * 1000);

        return JWT.create()
                .withClaim("role", user.getRoles().stream().map(role -> {
                    return String.format("Role_%s",role.name());
                }).collect(Collectors.toList()))
                .withSubject(user.getUsername())
                .withIssuedAt(createdDate)
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public DecodedJWT decodeToken(String token) {
        return JWT.decode(token);
    }

    public Map<String, Claim> getAllClaimsFromToken(String token) {
        return decodeToken(token).getClaims();
    }

    public Date getExpirationDateFromToken(String token) {
        return decodeToken(token).getExpiresAt();
    }

    public String getUsernameFromToken(String token) {
        return decodeToken(token).getSubject();
    }

    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
