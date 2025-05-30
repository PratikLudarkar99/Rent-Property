package com.example.project.repository;
import com.example.project.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    // You can add custom queries if needed
}
