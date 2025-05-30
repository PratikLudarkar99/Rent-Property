package com.example.project.service;

import com.example.project.model.City;
import com.example.project.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    // Create or Save City
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    // Get All Cities
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Get Cities by Status (optional filter)
    public List<City> getCitiesByStatus(City.Status status) {
        return cityRepository.findAll()
                .stream()
                .filter(city -> city.getStatus() == status)
                .toList();
    }
}
