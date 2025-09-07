package com.train.main.controller;

import com.train.main.DTO.IssueRequest;
import com.train.main.DTO.ProjectDTO;
import com.train.main.entieties.Project;
import com.train.main.mappers.ProjectMapper;
import com.train.main.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @GetMapping
    public Page<ProjectDTO> list(@PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        List<ProjectDTO> dtos = projectService.getList(pageable)
                .stream()
                .map(projectMapper::toDto)
                .toList();
        return new PageImpl<>(dtos, pageable, dtos.size());
    }

    @GetMapping("/{id}")
    public ProjectDTO get(@PathVariable Long id) {
        Project p = projectService.get(id);
        return projectMapper.toDto(p);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody IssueRequest req,
                                             UriComponentsBuilder ucb) {
        Project created = projectService.create(req);
        ProjectDTO dto = projectMapper.toDto(created);
        return ResponseEntity
                .created(ucb.path("/api/v1/projects/{id}").buildAndExpand(dto.getId()).toUri())
                .body(dto);
    }


    @PatchMapping("/{id}")
    public ProjectDTO update(@PathVariable Long id,
                             @Valid @RequestBody IssueRequest req) {
        Project updated = projectService.update(id, req);
        return projectMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> archive(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
