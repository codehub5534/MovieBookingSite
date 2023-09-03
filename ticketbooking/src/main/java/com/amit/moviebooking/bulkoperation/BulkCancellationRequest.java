package com.amit.moviebooking.bulkoperation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class BulkCancellationRequest {
    private List<Long> bookingIds;
    // Getters and setters
}

