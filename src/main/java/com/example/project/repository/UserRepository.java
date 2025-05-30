package com.example.project.repository;

import com.example.project.enums.Role;
import com.example.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(Role role);
    boolean existsByEmail(String email);
    boolean existsByContactNumber(String contactNumber);
}
