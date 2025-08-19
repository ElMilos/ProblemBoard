package com.train.main.mappers;

import com.train.main.DTO.IssueDTO;
import com.train.main.DTO.UserDTO;
import com.train.main.entieties.Issue;
import com.train.main.entieties.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User entity);

    User toEntity(UserDTO dto);
}
