package com.infinite.service.impl;

import com.infinite.domain.BookingStatus;
import com.infinite.dto.BookingRequest;
import com.infinite.dto.SalonDTO;
import com.infinite.dto.ServiceDTO;
import com.infinite.dto.UserDTO;
import com.infinite.modal.Booking;
import com.infinite.modal.SalonReport;
import com.infinite.repository.BookingRepository;
import com.infinite.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, SalonDTO salon,
                                 Set<ServiceDTO> serviceDTOSet) throws Exception {
        int totalDuration=serviceDTOSet.stream().mapToInt(ServiceDTO::getDuration).sum();

        LocalDateTime bookingStartTime=booking.getStartTime();
        LocalDateTime bookingEndTime=bookingStartTime.plusMinutes(totalDuration);

        Boolean isSlotAvailable=isTimeSlotAvailable(salon,bookingStartTime,bookingEndTime);

        int totalPrice=serviceDTOSet.stream().mapToInt(ServiceDTO::getPrice).sum();

        Set<Long>idList=serviceDTOSet.stream().map(ServiceDTO::getId).collect(Collectors.toSet());

        Booking newBooking=new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setSalonId(salon.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotaPrice(totalPrice);

        return bookingRepository.save(newBooking);
    }
    public Boolean isTimeSlotAvailable(SalonDTO salonDTO,LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {
        List<Booking>existingBookings=getBookingsBySalon(salonDTO.getId());

       LocalDateTime salonOpenTime=salonDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());

       LocalDateTime salonCloseTime=salonDTO.getCloseTime().atDate(bookingStartTime.toLocalDate());

       if(bookingStartTime.isBefore(salonOpenTime) || bookingEndTime.isAfter(salonCloseTime)){
           throw new Exception("Time must be within Salon's working hours");
       }
       for(Booking existingBooking:existingBookings){
            LocalDateTime existingBookingStartTime=existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime=existingBooking.getEndTime();

             if(bookingStartTime.isBefore(existingBookingEndTime)
                     && bookingEndTime.isAfter(existingBookingEndTime)){

                 throw new Exception("Slot not available choose different time");
             }
             if(bookingStartTime.isEqual(existingBookingStartTime)
                     || bookingEndTime.isEqual(existingBookingEndTime)){

                 throw new Exception("Slot not available choose different time");
             }
        }
        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsBySalon(Long salonId) {
        return bookingRepository.findBySalonId(salonId);
    }

    @Override
    public Booking getBookingsById(Long id) throws Exception {
        Booking booking=bookingRepository.findById(id).orElse(null);
        if(booking==null){
            throw new Exception("Booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking=getBookingsById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long salonId) {
        List<Booking>allBooking=getBookingsBySalon(salonId);
        if(date==null){
            return allBooking;
        }
        return allBooking.stream().filter(booking->isSameDate(booking.getStartTime(),date)
                || isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());

    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public SalonReport getSalonReport(long salonId) {
        List<Booking>bookings=getBookingsBySalon(salonId);

        int totalEarnings=bookings.stream().mapToInt(Booking::getTotaPrice).sum();
        Integer totalBooking= bookings.size();
        List<Booking>canceledBookings=bookings.stream().
                filter(booking -> booking.getStatus().
                        equals(BookingStatus.CANCELLED)).collect(Collectors.toList());

        Double totalRefund=canceledBookings.stream().mapToDouble(Booking::getTotaPrice).sum();

        SalonReport report=new SalonReport();
        report.setSalonId(salonId);
        report.setCanceledBookings(canceledBookings.size());
        report.setTotalBookings(totalBooking);
        report.setTotalEarnings(totalEarnings);
        report.setTotalRefunds(totalRefund);

        return report;
    }
}
