package com.infinite.service;

import com.infinite.dto.CategoryDTO;
import com.infinite.dto.SalonDTO;
import com.infinite.dto.ServiceDTO;
import com.infinite.modal.ServiceOffering;

import java.util.List;
import java.util.Set;

public interface ServiceOfferingService {

    ServiceOffering createService(SalonDTO salonDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO);

    ServiceOffering updateService(Long serviceId,ServiceOffering service) throws Exception;

    Set<ServiceOffering>getALlServiceBySalonId(Long salonId,Long categoryId);

    Set<ServiceOffering>getServicesByIds(Set<Long>ids);

    ServiceOffering getServiceById(Long id) throws Exception;
}
