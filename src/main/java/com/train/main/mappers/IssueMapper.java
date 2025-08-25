package com.train.main.mappers;

import com.train.main.DTO.IssueDTO;
import com.train.main.entieties.Issue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueMapper {
//    @Mapping(source="project.id", target="projectId")
//    @Mapping(source="assignee.id", target="assigneeId")
//    IssueDTO toDto(Issue entity);
//
//    @Mapping(source="projectId", target="project.id")
//    @Mapping(source="assigneeId", target="assignee.id")
//    Issue toEntity(IssueDTO dto);
}
