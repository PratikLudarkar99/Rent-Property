package com.example.project.controller;

import com.example.project.model.City;
import com.example.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cities")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/post/city")
    public City addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

    @GetMapping("/get/city")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    // Optional: filter cities by status
    @GetMapping("/status/{status}")
    public List<City> getCitiesByStatus(@PathVariable City.Status status) {
        return cityService.getCitiesByStatus(status);
    }
}

