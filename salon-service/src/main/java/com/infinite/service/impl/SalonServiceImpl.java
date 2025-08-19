package com.infinite.service.impl;

import com.infinite.modal.Salon;
import com.infinite.payload.dto.SalonDTO;
import com.infinite.payload.dto.UserDTO;
import com.infinite.repository.SalonRepository;
import com.infinite.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    @Override
    public Salon createSalon(SalonDTO req, UserDTO user) {
        Salon salon = new Salon();
        salon.setName(req.getName());
        salon.setAddress(req.getAddress());
        salon.setEmail(req.getEmail());
        salon.setCity(req.getCity());
        salon.setImages(req.getImages());
        salon.setOwnerId(user.getId());
        salon.setOpenTime(req.getOpenTime());
        salon.setCloseTime(req.getCloseTime());
        salon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(salon);
    }

    @Override
    public Salon updateSalon(SalonDTO req, UserDTO user, Long salonId) throws Exception {
        Salon existingSalon = salonRepository.findById(salonId)
                .orElseThrow(() -> new Exception("Salon does not exist"));

        if (!existingSalon.getOwnerId().equals(user.getId())) {
            throw new Exception("You are not authorized to update this salon");
        }

        existingSalon.setName(req.getName());
        existingSalon.setAddress(req.getAddress());
        existingSalon.setEmail(req.getEmail());
        existingSalon.setCity(req.getCity());
        existingSalon.setImages(req.getImages());
        existingSalon.setOpenTime(req.getOpenTime());
        existingSalon.setCloseTime(req.getCloseTime());
        existingSalon.setPhoneNumber(req.getPhoneNumber());

        return salonRepository.save(existingSalon); // ✅ Always returns
    }


    @Override
    public List<Salon> getAllSalon() {
        return salonRepository.findAll();
    }

    @Override
    public Salon getSalonById(Long salonId) throws Exception {
        return salonRepository.findById(salonId)
                .orElseThrow(() -> new Exception("Salon does not exist"));
    }

    @Override
    public Salon getSalonByOwnerId(Long ownerId) {
        return salonRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Salon> searchSalonByCity(String city) {
        return salonRepository.searchSalons(city);
    }
}
