package com.infinite.service.impl;

import com.infinite.dto.CategoryDTO;
import com.infinite.dto.SalonDTO;
import com.infinite.dto.ServiceDTO;
import com.infinite.modal.ServiceOffering;
import com.infinite.repository.ServiceOfferingRepository;
import com.infinite.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    @Override
    public ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering=new ServiceOffering();
         serviceOffering.setImage(serviceDTO.getImage());
         serviceOffering.setName(serviceDTO.getName());
         serviceOffering.setDuration(serviceDTO.getDuration());
         serviceOffering.setCategoryId(categoryDTO.getId());
         serviceOffering.setSalonId(salonDTO.getId());
         serviceOffering.setPrice(serviceDTO.getPrice());
         serviceOffering.setDescription(serviceDTO.getDescription());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingRepository.findById(serviceId).orElse(null);

        if(serviceOffering==null){
            throw new Exception("service not exist with id "+serviceId);
        }
        serviceOffering.setImage(service.getImage());
        serviceOffering.setName(service.getName());
        serviceOffering.setDuration(service.getDuration());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDescription(service.getDescription());

        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getALlServiceBySalonId(Long salonId, Long categoryId) {
        Set<ServiceOffering>services= serviceOfferingRepository.findBySalonId(salonId);

        if(categoryId!=null){
            services=services.stream().filter((service)->service.getCategoryId()!=null &&
                    service.getCategoryId()==categoryId).collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering>services=serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(services);
    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingRepository.findById(id).orElse(null);

        if(serviceOffering==null){
            throw new Exception("service not exist with id "+id);
        }
        return serviceOffering;
    }
}
