package com.moviebooking.theatre.theatreonboard.bulkoperation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class BulkCancellationRequest {
    private List<Long> bookingIds;
}

