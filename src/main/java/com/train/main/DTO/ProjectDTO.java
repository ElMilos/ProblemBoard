package com.train.main.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private boolean archived;
    private Instant createdAt;

}