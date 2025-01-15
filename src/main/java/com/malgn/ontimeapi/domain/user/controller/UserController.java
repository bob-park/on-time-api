package com.malgn.ontimeapi.domain.user.controller;

import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malgn.ontimeapi.domain.user.entity.User;
import com.malgn.ontimeapi.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping(path = "")
    public User createUser(@RequestBody Map<String, Object> body) {

        User createdUser =
            User.builder()
                .name((String) body.get("name"))
                .build();

        createdUser = userRepository.save(createdUser);

        return createdUser;
    }
}
