package com.amit.moviebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShowTimingDTO {
    private LocalDateTime showTime;

    public ShowTimingDTO(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}

