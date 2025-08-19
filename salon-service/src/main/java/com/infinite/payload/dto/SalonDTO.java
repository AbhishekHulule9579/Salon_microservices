package com.infinite.payload.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDTO {

    private Long id;

    private String name;

    private List<String> images;

    private String address;

    private String PhoneNumber;

    private String email;

    private String city;

    private Long ownerId;

    private LocalTime openTime;

    private LocalTime closeTime;
}
