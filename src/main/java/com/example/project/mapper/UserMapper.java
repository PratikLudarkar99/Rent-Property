package com.example.project.mapper;

import com.example.project.dto.UserDTO;
import com.example.project.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setContactNumber(user.getContactNumber());
        dto.setRole(user.getRole());
        dto.setActive(user.getActive());
        dto.setCreatedDate(user.getCreatedDate());
        dto.setAgencyName(user.getAgencyName());
        dto.setYearsOfExperience(user.getYearsOfExperience());
        dto.setAboutYou(user.getAboutYou());
        dto.setCustomerStatus(user.getCustomerStatus());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setContactNumber(dto.getContactNumber());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());
        user.setAgencyName(dto.getAgencyName());
        user.setYearsOfExperience(dto.getYearsOfExperience());
        user.setAboutYou(dto.getAboutYou());
        user.setCustomerStatus(dto.getCustomerStatus());
        return user;
    }
}
