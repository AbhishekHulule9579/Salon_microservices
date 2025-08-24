package com.infinite.service.impl;

import com.infinite.domain.BookingStatus;
import com.infinite.dto.BookingRequest;
import com.infinite.dto.SalonDTO;
import com.infinite.dto.ServiceDTO;
import com.infinite.dto.UserDTO;
import com.infinite.modal.Booking;
import com.infinite.modal.SalonReport;
import com.infinite.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon, Set<ServiceDTO> serviceDTOSet) {
        return null;
    }

    @Override
    public List<Booking> getBookingByCustomer(Long CustomerId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingBySalon(Long SalonId) {
        return List.of();
    }

    @Override
    public Booking getBookingId(Long id) {
        return null;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        return List.of();
    }

    @Override
    public SalonReport getSalonReport(long salonId) {
        return null;
    }
}
