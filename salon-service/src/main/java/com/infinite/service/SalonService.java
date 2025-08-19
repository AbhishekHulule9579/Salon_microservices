package com.infinite.service;

import com.infinite.modal.Salon;
import com.infinite.payload.dto.SalonDTO;
import com.infinite.payload.dto.UserDTO;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDTO salon, UserDTO user);

    Salon updateSalon(SalonDTO salon,UserDTO user,Long salonId) throws Exception;

    List<Salon>getAllSalon();

    Salon getSalonById(Long salonId) throws Exception;

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon>searchSalonByCity(String city);


}
