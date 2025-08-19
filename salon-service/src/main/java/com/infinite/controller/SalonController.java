package com.infinite.controller;

import com.infinite.mapper.SalonMapper;
import com.infinite.modal.Salon;
import com.infinite.payload.dto.SalonDTO;
import com.infinite.payload.dto.UserDTO;
import com.infinite.service.SalonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {

    private final SalonService salonService;

    @PostMapping
    public ResponseEntity<SalonDTO> createSalon(@RequestBody SalonDTO salonDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L); // Temporary until authentication is implemented

        Salon salon = salonService.createSalon(salonDTO, userDTO);
        return ResponseEntity.ok(SalonMapper.mapToDTO(salon));
    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDTO> updateSalon(
            @PathVariable Long salonId,
            @RequestBody SalonDTO salonDTO) throws Exception {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        Salon salon = salonService.updateSalon(salonDTO, userDTO, salonId);
        return ResponseEntity.ok(SalonMapper.mapToDTO(salon));
    }

    @GetMapping
    public ResponseEntity<List<SalonDTO>> getSalons() {
        List<SalonDTO> salonDTOS = salonService.getAllSalon()
                .stream()
                .map(SalonMapper::mapToDTO)
                .toList();

        return ResponseEntity.ok(salonDTOS);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDTO> getSalonByID(@PathVariable Long salonId) throws Exception {
        Salon salon = salonService.getSalonById(salonId);
        return ResponseEntity.ok(SalonMapper.mapToDTO(salon));
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDTO>> searchSalons(@RequestParam("city") String city) throws Exception {
        List<Salon> salons = salonService.searchSalonByCity(city);

        List<SalonDTO> salonDTOS = salons.stream()
                .map(SalonMapper::mapToDTO)
                .toList();

        return ResponseEntity.ok(salonDTOS); // ✅ Must return the response
    }


    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<SalonDTO> getSalonByOwnerID(@PathVariable Long ownerId) {
        Salon salon = salonService.getSalonByOwnerId(ownerId);
        return ResponseEntity.ok(SalonMapper.mapToDTO(salon));
    }
}
