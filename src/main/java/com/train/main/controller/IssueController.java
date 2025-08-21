package com.train.main.controller;

import com.train.main.DTO.IssueDTO;
import com.train.main.DTO.IssueRequest;
import com.train.main.entieties.IssuePriority;
import com.train.main.entieties.IssueStatus;
import com.train.main.mappers.IssueMapper;
import com.train.main.service.IssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;
    private final IssueMapper issueMapper;

    @PostMapping("/projects/{pid}/issues")
    public ResponseEntity<IssueDTO> create(@PathVariable Long pid, @Valid @RequestBody IssueRequest req, UriComponentsBuilder uri){
        var created = issueService.create(pid, req);
        var dto = issueMapper.toDto(created);
        var location = uri.path("/api/v1/issues/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("/projects/{pid}/issues")
    public Page<IssueDTO> list(@PathVariable Long pid,
                               @RequestParam Optional<IssueStatus> status,
                               @RequestParam Optional<IssuePriority> priority,
                               @RequestParam Optional<Long> assignee,
                               @PageableDefault(size=20, sort="updatedAt", direction = Sort.Direction.DESC) Pageable pageable){
        return issueService.list(pid, status, priority, assignee, pageable).map(issueMapper::toDto);
    }
}