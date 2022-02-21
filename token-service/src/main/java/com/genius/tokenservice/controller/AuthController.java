package com.genius.tokenservice.controller;

import com.genius.tokenservice.encoder.PBKDF2Encoder;
import com.genius.tokenservice.request.TokenRequest;
import com.genius.tokenservice.response.TokenResponse;
import com.genius.tokenservice.service.UserService;
import com.genius.tokenservice.util.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final PBKDF2Encoder encoder;

    @PostMapping("/token")
    public Mono<ResponseEntity<TokenResponse>> generateToken(@RequestBody TokenRequest request) {
        return userService.findUserByUsername(request.getUsername())
                .filter(user -> encoder.encode(user.getPassword()).equals(request.getPassword()))
                .map(user -> new TokenResponse(jwtUtils.createSignedToken(user)))
                .map(response -> ResponseEntity.ok(response))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
