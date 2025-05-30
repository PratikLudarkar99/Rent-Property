package com.example.project.controller;

import com.example.project.dto.SubscriptionDTO;
import com.example.project.enums.Status;
import com.example.project.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/add")
    public SubscriptionDTO create(@Valid @RequestBody SubscriptionDTO dto) {
        return subscriptionService.create(dto);
    }

    @GetMapping("/get")
    public List<SubscriptionDTO> getAll() {
        return subscriptionService.findAll();
    }

    @PutMapping("/update/{id}")
    public SubscriptionDTO update(@PathVariable Long id, @Valid @RequestBody SubscriptionDTO dto) {
        return subscriptionService.update(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        subscriptionService.delete(id);
    }
}
