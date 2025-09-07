package com.train.main.controller;

import com.train.main.DTO.CommentDTO;
import com.train.main.DTO.IssueRequest;
import com.train.main.mappers.CommentMapper;
import com.train.main.entieties.Comment;
import com.train.main.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/issues")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    // GET /api/v1/issues/{issueId}/comments
    @GetMapping("/{issueId}/comments")
    public List<CommentDTO> list(@PathVariable Long issueId) {
        return commentService.listByIssue(issueId).stream()
                .map(commentMapper::toDto)
                .toList();
    }

    // POST /api/v1/issues/{issueId}/comments
    @PostMapping("/{issueId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable Long issueId,
                                             @Valid @RequestBody IssueRequest req,
                                             UriComponentsBuilder ucb) {
        Comment created = commentService.create(issueId, req);
        CommentDTO dto = commentMapper.toDto(created);
        return ResponseEntity
                .created(ucb.path("/api/v1/issues/{issueId}/comments/{id}")
                        .buildAndExpand(issueId, dto.getId()).toUri())
                .body(dto);
    }
}
