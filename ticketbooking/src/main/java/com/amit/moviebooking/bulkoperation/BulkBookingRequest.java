package com.amit.moviebooking.bulkoperation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class BulkBookingRequest {
    private Long showId;
    private List<Long> seatIds;
    // Other relevant fields
    // Getters and setters
}

