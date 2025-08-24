package com.infinite.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class BookingRequest {

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    Set<Long>serviceIds;
}
