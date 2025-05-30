package com.example.project.service;

import com.example.project.dto.UserDTO;
import com.example.project.enums.CustomerStatus;
import com.example.project.enums.Role;
import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import com.example.project.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // Create User (Customer / Broker / Admin)
    public UserDTO createUser(UserDTO userDTO, MultipartFile profileImage) throws IOException {
        // ✅ Validate email
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required and cannot be null or blank.");
        }

        if (userDTO.getRole() == Role.BROKER) {
            // ✅ Validate broker-specific fields
            if (userDTO.getAgencyName() == null || userDTO.getYearsOfExperience() == null || userDTO.getAboutYou() == null) {
                throw new IllegalArgumentException("Broker-specific fields (agencyName, yearsOfExperience, aboutYou) are required for brokers.");
            }

            // ✅ Validate profile image for broker
            if (profileImage == null || profileImage.isEmpty()) {
                throw new IllegalArgumentException("Profile image is required for brokers.");
            }
        } else {
            // Nullify broker-specific fields for other roles
            userDTO.setAgencyName(null);
            userDTO.setYearsOfExperience(null);
            userDTO.setAboutYou(null);
            userDTO.setProfileImage(null);
        }

        User user = userMapper.toEntity(userDTO);

        // ✅ Set default active status if not provided
        if (user.getActive() == null) {
            user.setActive(true); // default to true
        }

        if (userDTO.getRole() == Role.BROKER && profileImage != null && !profileImage.isEmpty()) {
            user.setProfileImage(profileImage.getBytes());
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    // Get All Users by Role
    public List<UserDTO> getUsersByRole(Role role) {
        return userRepository.findByRole(role)
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get User By ID
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    // Update User
    public UserDTO updateUser(Long id, UserDTO userDTO, MultipartFile profileImage) throws IOException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Validate email
        if (userDTO.getEmail() == null || userDTO.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required and cannot be null or blank.");
        }

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setContactNumber(userDTO.getContactNumber());
        user.setCustomerStatus(userDTO.getCustomerStatus());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        if (userDTO.getRole() == Role.BROKER) {
            // ✅ Validate broker-specific fields
            if (userDTO.getAgencyName() == null || userDTO.getYearsOfExperience() == null || userDTO.getAboutYou() == null) {
                throw new IllegalArgumentException("Broker-specific fields (agencyName, yearsOfExperience, aboutYou) are required for brokers.");
            }

            // ✅ Validate profile image if not already set
            if ((user.getProfileImage() == null || user.getProfileImage().length == 0) &&
                    (profileImage == null || profileImage.isEmpty())) {
                throw new IllegalArgumentException("Profile image is required for brokers.");
            }

            user.setAgencyName(userDTO.getAgencyName());
            user.setYearsOfExperience(userDTO.getYearsOfExperience());
            user.setAboutYou(userDTO.getAboutYou());

            if (profileImage != null && !profileImage.isEmpty()) {
                user.setProfileImage(profileImage.getBytes());
            }

        } else {
            // Nullify broker fields for non-broker roles
            user.setAgencyName(null);
            user.setYearsOfExperience(null);
            user.setAboutYou(null);
            user.setProfileImage(null);
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    // Delete User
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Update Customer Status
    public UserDTO updateCustomerStatus(Long id, String status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setCustomerStatus(Enum.valueOf(CustomerStatus.class, status));
        return userMapper.toDTO(userRepository.save(user));
    }
}
