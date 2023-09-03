package com.moviebooking.theatre.theatreonboard.dto;

import java.util.List;

public class TheatreWithShowsDTO {
    private String theatreName;
    private List<ShowTimingDTO> showTimings;

    public TheatreWithShowsDTO(String theatreName, List<ShowTimingDTO> showTimings) {
        this.theatreName = theatreName;
        this.showTimings = showTimings;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public List<ShowTimingDTO> getShowTimings() {
        return showTimings;
    }

    public void setShowTimings(List<ShowTimingDTO> showTimings) {
        this.showTimings = showTimings;
    }
}

