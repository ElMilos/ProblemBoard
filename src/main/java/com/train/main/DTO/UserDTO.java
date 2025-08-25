package com.train.main.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Instant createdAt;
}

