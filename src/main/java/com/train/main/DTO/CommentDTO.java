package com.train.main.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class CommentDTO {

    private Long id;
    private Long issueId;
    private Long authorId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
}