package com.example.employee.request;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AddressRequest {
    private Long id;
    private String addressType;
    private String address;
    private String city;
    private String state;
    private Long pincode;
    private String country;
}
