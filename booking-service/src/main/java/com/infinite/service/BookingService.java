package com.infinite.service;

import com.infinite.domain.BookingStatus;
import com.infinite.dto.BookingRequest;
import com.infinite.dto.SalonDTO;
import com.infinite.dto.ServiceDTO;
import com.infinite.dto.UserDTO;
import com.infinite.modal.Booking;
import com.infinite.modal.SalonReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface BookingService {

    Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon,
                          Set<ServiceDTO>serviceDTOSet) throws Exception;

    List<Booking>getBookingByCustomer(Long CustomerId);
    List<Booking>getBookingBySalon(Long SalonId);
    Booking getBookingId(Long id);
    Booking updateBooking(Long bookingId, BookingStatus status);
    List<Booking>getBookingsByDate(LocalDate date,Long salonId);
    SalonReport getSalonReport(long salonId);

}
