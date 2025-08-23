package com.infinite.controller;

import com.infinite.dto.CategoryDTO;
import com.infinite.dto.SalonDTO;
import com.infinite.dto.ServiceDTO;
import com.infinite.modal.ServiceOffering;
import com.infinite.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/salon-owner")
public class SalonServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    @PostMapping
    public ResponseEntity<ServiceOffering> createService(@RequestBody ServiceDTO serviceDTO){

        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());
        ServiceOffering serviceOfferings=serviceOfferingService.createService(salonDTO,serviceDTO,categoryDTO) ;
        return ResponseEntity.ok(serviceOfferings);

    }

    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(@PathVariable Long id,
                                                         @RequestBody ServiceOffering serviceOffering) throws Exception {


        ServiceOffering serviceOfferings=serviceOfferingService.updateService(id,serviceOffering) ;
        return ResponseEntity.ok(serviceOfferings);

    }
}
