package com.example.project.controller;

import com.example.project.dto.PropertyDTO;
import com.example.project.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin("*")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/add")
    public PropertyDTO createProperty(@RequestBody PropertyDTO dto) {
        return propertyService.saveProperty(dto);
    }

    @PutMapping("/update/{id}")
    public PropertyDTO updateProperty(@PathVariable Long id, @RequestBody PropertyDTO dto) {
        dto.setPropertyId(id);// Ensure the DTO has the ID set
        return propertyService.saveProperty(dto);
    }

    @GetMapping("/get")
    public List<PropertyDTO> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @GetMapping("/get/{id}")
    public PropertyDTO getProperty(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }
}
