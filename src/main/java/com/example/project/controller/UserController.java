package com.example.project.controller;

import com.example.project.dto.UserDTO;
import com.example.project.enums.Role;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // Common create API for broker or customer
    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<?> createUser(
            @RequestPart("user") UserDTO userDTO,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        try {
            UserDTO createdUser = userService.createUser(userDTO, profileImage);
            return ResponseEntity.ok(createdUser);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing profile image: " + e.getMessage());
        }
    }

    // Update User API
    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestPart("user") UserDTO userDTO,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) {
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO, profileImage);
            return ResponseEntity.ok(updatedUser);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing profile image: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Get all brokers or customers by role
    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getUsersByRole(@RequestParam Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
