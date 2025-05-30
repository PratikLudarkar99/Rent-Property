package com.example.project.controller;

import com.example.project.dto.PackageDTO;
import com.example.project.service.PackageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin
public class PackageController {

    @Autowired
    private PackageService packageService;

    @PostMapping("/add")
    public PackageDTO create(@Valid @RequestBody PackageDTO dto) {
        return packageService.create(dto);
    }

    @GetMapping("/get")
    public List<PackageDTO> getAll() {
        return packageService.findAll();

    }

    // Update existing package by id
    @PutMapping("/update/{id}")
    public PackageDTO update(@PathVariable Long id, @Valid @RequestBody PackageDTO dto) {
        dto.setId(id);  // Ensure the DTO has the correct id for update
        return packageService.update(dto);
    }

    // Delete package by id
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        packageService.delete(id);

    }
}
