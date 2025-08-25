package com.train.main.mappers;

import com.train.main.DTO.IssueDTO;
import com.train.main.entieties.Issue;
import com.train.main.entieties.Project;
import com.train.main.entieties.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    @Mapping(source="project.id", target="projectId")
    @Mapping(source="assignee.id", target="assigneeId")
    IssueDTO toDto(Issue entity);

    @Mapping(source="projectId", target="project.id")
    @Mapping(source="assigneeId", target="assignee.id")
    Issue toEntity(IssueDTO dto);

    @Named("mapProject")
    default Project mapProject(Long id) {
        if (id == null) return null;
        Project p = new Project();
        p.setId(id);
        return p;
    }

    @Named("mapUser")
    default User mapUser(Long id) {
        if (id == null) return null;
        User u = new User();
        u.setId(id);
        return u;
    }
}
