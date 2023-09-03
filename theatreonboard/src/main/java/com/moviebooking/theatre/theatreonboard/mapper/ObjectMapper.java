package com.moviebooking.theatre.theatreonboard.mapper;

import com.moviebooking.theatre.theatreonboard.dto.TheatreRequestDTO;
import com.moviebooking.theatre.theatreonboard.dto.TheatreResponseDTO;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    public Theatre mapTheatreRequestDToToTheatre(
            TheatreRequestDTO theatreRequestDTO){
         Theatre theatre=new Theatre();
        theatre.setCity(theatreRequestDTO.getCity());
        theatre.setName(theatreRequestDTO.getName());
        return theatre;
    }

    public TheatreResponseDTO mapTheatreToTheatreResponseDTO(
            Theatre theatre){
        TheatreResponseDTO theatreResponseDTO=new TheatreResponseDTO();
        theatreResponseDTO.setCity(theatre.getCity());
        theatreResponseDTO.setName(theatre.getName());
        theatreResponseDTO.setId(theatre.getId());
        return theatreResponseDTO;
    }
}
