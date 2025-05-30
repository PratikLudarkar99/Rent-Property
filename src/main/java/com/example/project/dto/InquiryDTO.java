package com.example.project.dto;

import com.example.project.model.Broker;
import com.example.project.model.Customer;
import com.example.project.model.Property;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InquiryDTO {
        private Long enquiryId;
        private LocalDateTime enquiryDateTime;
        private CustomerDTO customer;
        private BrokerDTO broker;
        private PropertyDTO property;
}

