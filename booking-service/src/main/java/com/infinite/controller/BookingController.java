package com.infinite.controller;

import com.infinite.domain.BookingStatus;
import com.infinite.dto.*;
import com.infinite.mapper.BookingMapper;
import com.infinite.modal.Booking;
import com.infinite.modal.SalonReport;
import com.infinite.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking>createBooking(@RequestParam Long salonId,
                                                @RequestBody BookingRequest bookingRequest) throws Exception {
        UserDTO userDTO=new UserDTO();
        userDTO.setId(1L);

        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(salonId);
        salonDTO.setOpenTime(LocalTime.now());
        salonDTO.setCloseTime(LocalTime.now().plusHours(12));

        Set<ServiceDTO>serviceDTOSet=new HashSet<>();

        ServiceDTO serviceDTO=new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Hair cut for men");

        serviceDTOSet.add(serviceDTO);

        Booking booking=bookingService.createBooking(bookingRequest,
                userDTO,
                salonDTO,
                serviceDTOSet);
        return ResponseEntity.ok(booking) ;
    }

    @GetMapping("/customer")
    public ResponseEntity<Set<BookingDTO>>getBookingsByCustomer(){

        List<Booking>bookings=bookingService.getBookingsByCustomer(1l);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    @GetMapping("/salon")
    public ResponseEntity<Set<BookingDTO>>getBookingsBySalon(){

        List<Booking>bookings=bookingService.getBookingsBySalon(1l);
        return ResponseEntity.ok(getBookingDTOs(bookings));
    }

    private Set<BookingDTO>getBookingDTOs(List<Booking>bookings){
        return bookings.stream().map(booking -> {
            return BookingMapper.toDTO(booking);
        }).collect(Collectors.toSet());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO>getBookingsById(@PathVariable Long bookingId) throws Exception {
        Booking booking=bookingService.getBookingsById(bookingId);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO>updateBookingsStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status
    ) throws Exception {

        Booking booking=bookingService.updateBooking(bookingId,status);

        return ResponseEntity.ok(BookingMapper.toDTO(booking));
    }

    @GetMapping("/slots/salon/{salonId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>>getBookedSlot(
            @PathVariable Long salonId,
            @RequestParam(required = false) LocalDate date) throws Exception {
        List<Booking> bookings=bookingService.getBookingsByDate(date,salonId);

        List<BookingSlotDTO>slotDTOs=bookings.stream()
                .map(booking -> {
                    BookingSlotDTO slotDTO=new BookingSlotDTO();
                    slotDTO.setStartTime(booking.getStartTime());
                    slotDTO.setEndTime((booking.getEndTime()));
                    return slotDTO;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(slotDTOs);
    }

    @GetMapping("/report")
    public ResponseEntity<SalonReport>getSalonReport() throws Exception {
        SalonReport report =bookingService.getSalonReport(1L);
        return ResponseEntity.ok(report);
    }

}
