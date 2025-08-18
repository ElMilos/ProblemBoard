package com.train.main.DTO;

import java.time.Instant;

public class CommentDTO {

    private Long id;
    private Long issueId;
    private Long authorId;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
}