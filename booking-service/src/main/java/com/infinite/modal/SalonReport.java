package com.infinite.modal;

import lombok.Data;

@Data
public class SalonReport {

    private Long salonId;

    private String salonName;

    private int totalEarnings;

    private Integer totalBookings;

    private Integer canceledBookings;

    private Double totalRefunds;



}
