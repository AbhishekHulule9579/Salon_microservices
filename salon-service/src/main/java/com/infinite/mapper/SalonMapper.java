package com.infinite.mapper;

import com.infinite.modal.Salon;
import com.infinite.payload.dto.SalonDTO;

public class SalonMapper {

    public static SalonDTO mapToDTO(Salon salon){
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(salon.getId());
        salonDTO.setCity(salon.getCity());
        salonDTO.setAddress(salon.getAddress());
        salonDTO.setName(salon.getName());
        salonDTO.setEmail(salon.getEmail());
        salonDTO.setImages(salon.getImages());
        salonDTO.setCloseTime(salon.getCloseTime());
        salonDTO.setOpenTime(salon.getOpenTime());
        salonDTO.setOwnerId(salon.getOwnerId());
        salonDTO.setPhoneNumber(salon.getPhoneNumber());
        return salonDTO;
    }
}
