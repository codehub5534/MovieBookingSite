package com.moviebooking.theatre.theatreonboard.controller;

import com.moviebooking.theatre.theatreonboard.dto.CreateTheatreRequest;
import com.moviebooking.theatre.theatreonboard.dto.TheatreRequestDTO;
import com.moviebooking.theatre.theatreonboard.dto.TheatreResponseDTO;
import com.moviebooking.theatre.theatreonboard.dto.TheatreWithScreensAndSeatsDTO;
import com.moviebooking.theatre.theatreonboard.entity.Theatre;
import com.moviebooking.theatre.theatreonboard.exception.TheatreNotFoundException;
import com.moviebooking.theatre.theatreonboard.mapper.ObjectMapper;
import com.moviebooking.theatre.theatreonboard.service.TheatreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<TheatreResponseDTO> createTheatre(@RequestBody @Valid TheatreRequestDTO theatreRequestDTO) {

        Theatre theatre =objectMapper.mapTheatreRequestDToToTheatre(theatreRequestDTO);
        Theatre createdTheatre = theatreService.createTheatre(theatre);
       TheatreResponseDTO theatreResponseDTO = objectMapper.mapTheatreToTheatreResponseDTO(createdTheatre);
        return ResponseEntity.ok(theatreResponseDTO);
    }

    @PostMapping("/create-theatre")
    public ResponseEntity<String> createTheatreWithScreensAndSeats(@RequestBody CreateTheatreRequest request) {
        try {
            theatreService.createTheatreWithScreensAndSeats(request);
            return ResponseEntity.ok("Theatre, screens, and seats created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating theatre: " + e.getMessage());
        }
    }


    @GetMapping("/{city}")
    public List<TheatreWithScreensAndSeatsDTO> getAllTheatres(@PathVariable String city) throws TheatreNotFoundException {
        List<TheatreWithScreensAndSeatsDTO> theatres =theatreService.getAllTheatresInCityWithScreensAndSeats(city);

        if(theatres!=null&&!theatres.isEmpty()) {
            return theatres;
        }
        throw new TheatreNotFoundException("There is no theatre onboarded");
    }

    @GetMapping("/details/{theatreId}")
    public ResponseEntity<TheatreWithScreensAndSeatsDTO> getTheatreById(@PathVariable Long theatreId) throws TheatreNotFoundException {
        TheatreWithScreensAndSeatsDTO theatre = theatreService.findTheatreById(theatreId);

        if (theatre == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(theatre, HttpStatus.OK);
    }




}

