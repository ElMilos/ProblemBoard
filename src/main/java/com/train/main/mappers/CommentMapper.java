package com.train.main.mappers;

import com.train.main.DTO.CommentDTO;
import com.train.main.entieties.Comment;
import org.mapstruct.Mapping;

public interface CommentMapper {
        @Mapping(source = "issue.id", target = "issueId")
        @Mapping(source = "author.id", target = "authorId")
        CommentDTO toDto(Comment entity);

        @Mapping(source = "issueId", target = "issue.id")
        @Mapping(source = "authorId", target = "author.id")
        Comment toEntity(CommentDTO dto);
}