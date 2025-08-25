package com.infinite.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingSlotDTO{
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
