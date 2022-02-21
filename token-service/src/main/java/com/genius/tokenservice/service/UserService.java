package com.genius.tokenservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import com.genius.tokenservice.enumertaros.RoleEnum;
import com.genius.tokenservice.model.User;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    List<User> users;

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        User user1 = new User(1L, "admin", "admin", Arrays.asList(RoleEnum.ADMIN));
        User user2 = new User(1L, "dev", "dev", Arrays.asList(RoleEnum.DEVELOPER));
        users.add(user1);
        users.add(user2);
    }

    public Mono<User> findUserByUsername(String username) {
        return Mono.just(users)
                .flatMapMany(Flux::fromIterable)
                .filter(user -> user.getUsername().equals(username))
                .collectList()
                .map(list -> list.get(0));
    }
}
