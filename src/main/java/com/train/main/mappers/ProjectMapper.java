package com.train.main.mappers;

import com.train.main.DTO.ProjectDTO;
import com.train.main.entieties.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toDto(Project entity);

    Project toEntity(ProjectDTO dto);

}
