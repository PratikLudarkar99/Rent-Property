
package com.example.project.service;

import com.example.project.dto.BrokerDTO;
import com.example.project.model.Broker;
import com.example.project.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrokerService {

    @Autowired
    private BrokerRepository brokerRepository;

    public BrokerDTO registerBroker(BrokerDTO brokerDTO, MultipartFile profileImage) throws IOException {
        if (profileImage == null || profileImage.isEmpty()) {
            throw new IllegalArgumentException("Profile image must be uploaded");
        }

        Optional<Broker> existingEmail = brokerRepository.findByEmail(brokerDTO.getEmail());
        if (existingEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        Optional<Broker> existingPhone = brokerRepository.findByContactNumber(brokerDTO.getContactNumber());
        if (existingPhone.isPresent()) {
            throw new IllegalArgumentException("Contact number already exists");
        }

        Broker broker = new Broker();
        broker.setFirstName(brokerDTO.getFirstName());
        broker.setLastName(brokerDTO.getLastName());
        broker.setEmail(brokerDTO.getEmail());
        broker.setContactNumber(brokerDTO.getContactNumber());
        broker.setAgencyName(brokerDTO.getAgencyName());
        broker.setYearsOfExperience(brokerDTO.getYearsOfExperience());
        broker.setAboutYou(brokerDTO.getAboutYou());
        broker.setProfileImage(profileImage.getBytes());

        Broker saved = brokerRepository.save(broker);
        return convertToDTO(saved);
    }

    public List<BrokerDTO> getAllBrokers() {
        return brokerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BrokerDTO convertToDTO(Broker broker) {
        BrokerDTO dto = new BrokerDTO();
        dto.setBrokerId(broker.getBrokerId());
        dto.setFirstName(broker.getFirstName());
        dto.setLastName(broker.getLastName());
        dto.setEmail(broker.getEmail());
        dto.setContactNumber(broker.getContactNumber());
        dto.setAgencyName(broker.getAgencyName());
        dto.setYearsOfExperience(broker.getYearsOfExperience());
        dto.setAboutYou(broker.getAboutYou());
        dto.setProfileImage(broker.getProfileImage());
        return dto;
    }
}
